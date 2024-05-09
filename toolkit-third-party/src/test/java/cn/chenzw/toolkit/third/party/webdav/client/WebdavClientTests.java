package cn.chenzw.toolkit.third.party.webdav.client;

import cn.chenzw.toolkit.third.party.webdav.entity.MultiStatusEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.jackrabbit.webdav.DavConstants;
import org.apache.jackrabbit.webdav.lock.LockInfo;
import org.apache.jackrabbit.webdav.property.*;
import org.apache.jackrabbit.webdav.search.SearchInfo;
import org.apache.jackrabbit.webdav.xml.Namespace;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author chenzw
 */
@Slf4j
@RunWith(JUnit4.class)
public class WebdavClientTests {

    private static WebdavClient client;

    @BeforeClass
    public static void before() {
        client = WebdavClients.custom()
                 .serverURI("https://dav.jianguoyun.com/dav/")
                 .username("656469722@qq.com")
                 .password("aip69vqtzrx5r2ha22")
                .build();
    }

    @Test
    public void testList() throws IOException {
        MultiStatusEntity entity = client.list("/dav/%e6%88%91%e7%9a%84%e5%9d%9a%e6%9e%9c%e4%ba%91/");
        log.info("files => {}", entity.getItems());
    }

    @Test
    public void testListCustom() throws IOException {
        MultiStatusEntity entity = client.list("/dav/%e6%88%91%e7%9a%84%e5%9d%9a%e6%9e%9c%e4%ba%91", DavConstants.PROPFIND_BY_PROPERTY, DavConstants.DEPTH_1);
        log.info("files => {}", entity.getItems());
    }

    @Test
    public void testListChild() throws IOException {
        MultiStatusEntity entity = client.listChild("/dav/%e6%88%91%e7%9a%84%e5%9d%9a%e6%9e%9c%e4%ba%91");
        log.info("files => {}", entity.getItems());
    }

    @Test
    public void testGetFileContent() throws IOException {
        try (InputStream is = client.getFileContent("/dav/%e6%88%91%e7%9a%84%e5%9d%9a%e6%9e%9c%e4%ba%91/%e3%80%9001%e3%80%91%e5%9d%9a%e6%9e%9c%e4%ba%91%e5%85%a5%e9%97%a8%e5%9f%ba%e7%a1%80%e7%9f%a5%e8%af%86.pdf")) {
            IOUtils.copy(is, new FileOutputStream("a.pdf"));
        }
    }

    @Test
    public void testCheckConnectable() throws IOException {
        boolean success = client.checkConnectable();
        log.info("success => {}", success);
    }

    @Test
    public void testExists() throws IOException {
        boolean exist = client.exist("/dav/我的坚果云/test.txt");
        log.info("exist => {}", exist);
    }

    @Test
    public void testUpload() throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("webdav/test.txt");
        client.upload("/dav/我的坚果云/test.txt", is);
    }

    @Test
    public void testDelete() throws IOException {
        client.delete("/dav/test.txt");
    }

    @Test
    public void testMkdir() throws IOException {
        client.mkdir("/dav/我的坚果云/my-mkdir");
    }

    @Test
    public void testLock() throws IOException {
        LockInfo lockInfo = new LockInfo(10000);
        String token = client.lock("/dav/我的坚果云/test.txt", lockInfo);
        log.info("token => {}", token);
    }

    @Test
    public void testSearch() throws IOException {
        SearchInfo searchInfo = new SearchInfo("zh", Namespace.XML_NAMESPACE, "test");
        MultiStatusEntity entity = client.search("/dav/我的坚果云/", searchInfo);
        log.info("search => {}", entity);
    }

    @Test
    public void testGetAllowMethods() throws IOException {
        List<String> allowMethods = client.getAllowMethods("/dav/我的坚果云/");
        log.info("allowMethods => {}", allowMethods);
    }

    @Test
    public void testProppatch() throws IOException {
        DavPropertySet setPropertySet = new DavPropertySet();
        DavProperty davProperty = new DefaultDavProperty(DavPropertyName.DISPLAYNAME, "xx.txt", false);
        setPropertySet.add(davProperty);
        DavPropertyNameSet removePropertySet = new DavPropertyNameSet();
        boolean success = client.proppatch("/dav/我的坚果云/test.txt", setPropertySet, removePropertySet);
        log.info("success => {}", success);
    }

}
