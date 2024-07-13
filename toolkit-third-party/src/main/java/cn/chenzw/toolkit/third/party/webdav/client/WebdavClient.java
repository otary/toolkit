package cn.chenzw.toolkit.third.party.webdav.client;

import cn.chenzw.toolkit.third.party.webdav.client.enums.AuthType;
import cn.chenzw.toolkit.third.party.webdav.client.handler.*;
import cn.chenzw.toolkit.third.party.webdav.entity.MultiStatusEntity;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.jackrabbit.webdav.DavConstants;
import org.apache.jackrabbit.webdav.bind.BindInfo;
import org.apache.jackrabbit.webdav.bind.UnbindInfo;
import org.apache.jackrabbit.webdav.client.methods.*;
import org.apache.jackrabbit.webdav.client.methods.HttpDelete;
import org.apache.jackrabbit.webdav.lock.LockInfo;
import org.apache.jackrabbit.webdav.observation.SubscriptionInfo;
import org.apache.jackrabbit.webdav.property.DavPropertyNameSet;
import org.apache.jackrabbit.webdav.property.DavPropertySet;
import org.apache.jackrabbit.webdav.search.SearchInfo;
import org.apache.jackrabbit.webdav.version.LabelInfo;
import org.apache.jackrabbit.webdav.version.report.ReportInfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author chenzw
 */
public class WebdavClient {

    private String serverURI;

    private AuthType authType;

    private String username;

    private String password;

    private static final Supplier<HttpClient> httpClientSupplier = () -> {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        return HttpClients.custom().setConnectionManager(cm).build();
    };

    private HttpClientContext httpClientContext;

    public WebdavClient(String serverURI, AuthType authType, String username, String password) {
        this.serverURI = serverURI;
        this.authType = authType;
        this.username = username;
        this.password = password;
        this.httpClientContext = createHttpClientContext();
    }

    private HttpClientContext createHttpClientContext() {
        URI serverURI = URI.create(this.serverURI);
        HttpHost serverHost = new HttpHost(serverURI.getHost(), serverURI.getPort());

        BasicCredentialsProvider cp = new BasicCredentialsProvider();
        UsernamePasswordCredentials upc = new UsernamePasswordCredentials(this.username, this.password);
        cp.setCredentials(new AuthScope(serverHost.getHostName(), serverHost.getPort()), upc);

        // Basic认证
        AuthScheme authScheme = new BasicScheme();

        AuthCache authCache = new BasicAuthCache();
        authCache.put(serverHost, authScheme);

        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(cp);
        context.setAuthCache(authCache);
        return context;
    }

    public <T> T execute(HttpRequestBase request, HttpClientContext context, ResponseHandler<T> responseHandler) throws IOException {
        try {
            return (T) this.httpClientSupplier.get().execute(request, responseHandler, context);
        } finally {
            context.setAttribute(HttpClientContext.USER_TOKEN, context.getAttribute(HttpClientContext.USER_TOKEN));
        }
    }

    public <T> T execute(HttpRequestBase request, HttpClientContext context) throws IOException {
        try {
            return (T) this.httpClientSupplier.get().execute(request, context);
        } finally {
            context.setAttribute(HttpClientContext.USER_TOKEN, context.getAttribute(HttpClientContext.USER_TOKEN));
        }
    }

    public MultiStatusEntity list(String path, int propfindType, int depth) throws IOException {
        HttpPropfind propfind = new HttpPropfind(this.buildRequestURL(path), propfindType, depth);
        return execute(propfind, this.httpClientContext, new MultiStatusResponseHandler(propfind));
    }

    /**
     * 获取文件列表
     *
     * @param path
     * @return
     * @throws IOException
     */
    public MultiStatusEntity list(String path) throws IOException {
        return list(path, DavConstants.PROPFIND_ALL_PROP_INCLUDE, DavConstants.DEPTH_1);
    }

    /**
     * 列举目录下文件（不包含自身）
     *
     * @param path
     * @return
     * @throws IOException
     */
    public MultiStatusEntity listChild(String path) throws IOException {
        MultiStatusEntity entity = list(path);
        if (entity.getItems().size() > 0) {
            entity.getItems().remove(0);
        }
        return entity;
    }

    /**
     * 判断文件是否存在
     *
     * @return
     * @throws IOException
     */
    public boolean exist(String path) throws IOException {
        MultiStatusEntity entity = null;
        try {
            entity = list(path, DavConstants.PROPFIND_BY_PROPERTY, DavConstants.DEPTH_1);
        } catch (Exception e) {
            return false;
        }
        return entity.getItems().size() > 0;
    }


    /**
     * 获取文件内容
     *
     * @param path
     * @return
     * @throws IOException
     */
    public InputStream getFileContent(String path) throws IOException {
        HttpGet httpGet = new HttpGet(this.buildRequestURL(path));
        return execute(httpGet, this.httpClientContext, new GetContentResponseHandler());
    }

    /**
     * 检查是否可连通
     *
     * @return
     * @throws IOException
     */
    public boolean checkConnectable() throws IOException {
        HttpOptions httpOptions = new HttpOptions(this.buildRequestURL(""));
        return execute(httpOptions, this.httpClientContext, new CheckConnectableResponseHandler());
    }

    /**
     * 删除
     */
    public boolean delete(String path) throws IOException {
        HttpDelete httpDelete = new HttpDelete(this.buildRequestURL(path));
        return execute(httpDelete, this.httpClientContext, new BooleanResponseHandler(httpDelete));
    }

    /**
     * 上传文件
     *
     * @param path
     * @param is
     * @throws IOException
     */
    public void upload(String path, InputStream is) throws IOException {
        HttpPut httpPut = new HttpPut(this.buildRequestURL(path));
        InputStreamEntity requestEntity = new InputStreamEntity(is);
        httpPut.setEntity(requestEntity);
        execute(httpPut, this.httpClientContext, new VoidResponseHandler());
    }


    /**
     * 创建文件夹
     *
     * @param path
     * @return
     */
    public boolean mkdir(String path) throws IOException {
        HttpMkcol httpMkcol = new HttpMkcol(this.buildRequestURL(path));
        return execute(httpMkcol, this.httpClientContext, new BooleanResponseHandler(httpMkcol));
    }

    /**
     * 锁定
     *
     * @param path
     * @param lockInfo
     * @return
     * @throws IOException
     */
    @Deprecated
    public String lock(String path, LockInfo lockInfo) throws IOException {
        HttpLock httpLock = new HttpLock(this.buildRequestURL(path), lockInfo);
        return execute(httpLock, this.httpClientContext, new LockResponseHandler());
    }

    /**
     * 解锁
     *
     * @param path
     * @param lockToken
     * @return
     * @throws IOException
     */
    public boolean unlock(String path, String lockToken) throws IOException {
        HttpUnlock lockInfo = new HttpUnlock(this.buildRequestURL(path), lockToken);
        return execute(lockInfo, this.httpClientContext, new BooleanResponseHandler(lockInfo));
    }

    /**
     * 文件搜索
     *
     * @param path
     * @param searchInfo
     * @return
     * @throws IOException
     */
    public MultiStatusEntity search(String path, SearchInfo searchInfo) throws IOException {
        HttpSearch httpSearch = new HttpSearch(this.buildRequestURL(path), searchInfo);
        return execute(httpSearch, this.httpClientContext, new MultiStatusResponseHandler(httpSearch));
    }

    /**
     * 获取支持的方法
     *
     * @param path
     * @return
     * @throws IOException
     */
    public List<String> getAllowMethods(String path) throws IOException {
        HttpOptions httpOptions = new HttpOptions(this.buildRequestURL(path));
        HttpResponse response = execute(httpOptions, this.httpClientContext);
        Header[] allHeaders = response.getHeaders("Allow");
        String allowMethod = Arrays.stream(allHeaders).findFirst().map(Header::getValue).orElse(StringUtils.EMPTY);
        return Splitter.on(",").splitToList(allowMethod);
    }

    /**
     * 复制
     *
     * @param srcPath   源路径
     * @param destPath  目标路径
     * @param overwrite 是否覆盖
     * @param shallow   是否浅复制
     */
    public boolean copy(String srcPath, String destPath, boolean overwrite, boolean shallow) throws IOException {
        HttpCopy httpCopy = new HttpCopy(this.buildRequestURL(srcPath), this.buildRequestURL(destPath), overwrite, shallow);
        return execute(httpCopy, this.httpClientContext, new BooleanResponseHandler(httpCopy));
    }

    /**
     * 设置属性
     *
     * @param path
     * @param setProperties    设置属性集
     * @param removeProperties 移除属性集
     * @return
     */
    public boolean proppatch(String path, DavPropertySet setProperties, DavPropertyNameSet removeProperties) throws IOException {
        HttpProppatch httpProppatch = new HttpProppatch(this.buildRequestURL(path), setProperties, removeProperties);
        return execute(httpProppatch, this.httpClientContext, new BooleanResponseHandler(httpProppatch));
    }

    /**
     * 移动
     *
     * @param srcPath
     * @param destPath
     * @param overwrite
     * @return
     */
    public boolean move(String srcPath, String destPath, boolean overwrite) throws IOException {
        HttpMove httpMove = new HttpMove(this.buildRequestURL(srcPath), this.buildRequestURL(destPath), overwrite);
        return execute(httpMove, this.httpClientContext, new BooleanResponseHandler(httpMove));
    }

    /**
     * 修改资源标签
     *
     * @param path
     * @param labelInfo
     * @param labelInfo.type  标签类型（TYPE_SET = 0：修改；TYPE_REMOVE = 1：移除；TYPE_ADD = 2：添加）
     * @param labelInfo.depth （0：只对当前资源打标签；1：对当前资源和子资源打标签）
     * @return
     */
    public boolean label(String path, LabelInfo labelInfo) throws IOException {
        HttpLabel httpLabel = new HttpLabel(this.buildRequestURL(path), labelInfo);
        return execute(httpLabel, this.httpClientContext, new BooleanResponseHandler(httpLabel));
    }


    /**
     * 报告
     *
     * @param path
     * @param reportInfo
     * @return
     * @throws IOException
     */
    public boolean report(String path, ReportInfo reportInfo) throws IOException {
        HttpReport httpReport = new HttpReport(this.buildRequestURL(path), reportInfo);
        return execute(httpReport, this.httpClientContext, new BooleanResponseHandler(httpReport));
    }

    /**
     * 订阅
     *
     * @param path
     * @param subscriptionInfo.eventTypes: 事件类型
     * @param subscriptionInfo.filters:    过滤器
     * @param subscriptionInfo.noLocal:    是否本地
     * @param subscriptionInfo.isDeep:     是否深度
     * @param subscriptionId               订阅ID
     * @return
     * @throws IOException
     */
    public boolean subscribe(String path, SubscriptionInfo subscriptionInfo, String subscriptionId) throws IOException {
        HttpSubscribe httpSubscribe = new HttpSubscribe(this.buildRequestURL(path), subscriptionInfo, subscriptionId);
        return execute(httpSubscribe, this.httpClientContext, new BooleanResponseHandler(httpSubscribe));
    }

    /**
     * 取消订阅
     *
     * @param path
     * @param subscriptionId 订阅ID
     * @return
     * @throws IOException
     */
    public boolean unSubscribe(String path, String subscriptionId) throws IOException {
        HttpUnsubscribe unsubscribe = new HttpUnsubscribe(this.buildRequestURL(path), subscriptionId);
        return execute(unsubscribe, this.httpClientContext, new BooleanResponseHandler(unsubscribe));
    }

    /**
     * 创建虚拟工作区
     *
     * @param path
     * @return
     * @throws IOException
     */
    public boolean mkWorkspace(String path) throws IOException {
        HttpMkworkspace httpMkworkspace = new HttpMkworkspace(this.buildRequestURL(path));
        return execute(httpMkworkspace, this.httpClientContext, new BooleanResponseHandler(httpMkworkspace));
    }

    /**
     * 给资源加锁
     *
     * @param path
     * @return
     * @throws IOException
     */
    public boolean checkOut(String path) throws IOException {
        HttpCheckout httpCheckout = new HttpCheckout(this.buildRequestURL(path));
        return execute(httpCheckout, this.httpClientContext, new BooleanResponseHandler(httpCheckout));
    }

    /**
     * 给资源解锁
     *
     * @param path
     * @return
     * @throws IOException
     */
    public boolean checkIn(String path) throws IOException {
        HttpCheckin httpCheckin = new HttpCheckin(this.buildRequestURL(path));
        return execute(httpCheckin, this.httpClientContext, new BooleanResponseHandler(httpCheckin));
    }

    /**
     * 版本控制
     *
     * @param path
     * @return
     */
    public boolean versionControl(String path) throws IOException {
        HttpVersionControl versionControl = new HttpVersionControl(
                this.buildRequestURL(path)
        );
        return execute(versionControl, this.httpClientContext, new BooleanResponseHandler(versionControl));
    }

    /**
     * 通过添加从 BIND 主体中指定的段到 BIND 主体中标识的资源的新绑定来修改 Request-URI 标识的集合
     *
     * @param path
     * @param bindInfo
     * @return
     * @throws IOException
     */
    public boolean bind(String path, BindInfo bindInfo) throws IOException {
        HttpBind httpBind = new HttpBind(this.buildRequestURL(path), bindInfo);
        return execute(httpBind, this.httpClientContext, new BooleanResponseHandler(httpBind));
    }

    /**
     * 通过从 Request-URI 标识的集合中删除指定的段来修改 Request-URI 标识的集合
     *
     * @param path
     * @param unbindInfo
     * @return
     * @throws IOException
     */
    public boolean unbind(String path, UnbindInfo unbindInfo) throws IOException {
        HttpUnbind httpBind = new HttpUnbind(this.buildRequestURL(path), unbindInfo);
        return execute(httpBind, this.httpClientContext, new BooleanResponseHandler(httpBind));
    }

    private String buildRequestURL(String path) throws MalformedURLException {
        URI serverURI = URI.create(this.serverURI);
        String requestURL = new URL(serverURI.getScheme(), serverURI.getHost(), path).toString();
        /*if (!StringUtils.endsWith(requestURL, "/")) {
            requestURL += "/";
        }*/
        return requestURL;
    }
}
