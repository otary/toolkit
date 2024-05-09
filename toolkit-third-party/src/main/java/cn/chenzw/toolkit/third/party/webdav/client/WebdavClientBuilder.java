package cn.chenzw.toolkit.third.party.webdav.client;

import cn.chenzw.toolkit.third.party.webdav.client.enums.AuthType;
import org.apache.commons.lang3.StringUtils;

/**
 * @author chenzw
 */
public class WebdavClientBuilder {

    private String serverURI;

    private AuthType authType;

    private String username;

    private String password;


    public static WebdavClientBuilder create() {
        return new WebdavClientBuilder();
    }

    public final WebdavClientBuilder serverURI(String serverURI) {
        this.serverURI = serverURI;
        return this;
    }

    public final WebdavClientBuilder username(String username) {
        this.username = username;
        return this;
    }

    public final WebdavClientBuilder password(String password) {
        this.password = password;
        return this;
    }

    public final WebdavClientBuilder authType(AuthType authType) {
        this.authType = authType;
        return this;
    }

    public WebdavClient build() {
        if (StringUtils.isEmpty(this.serverURI)) {
            throw new IllegalArgumentException("serverURI may not be null");
        }
        return new WebdavClient(this.serverURI, this.authType, this.username, this.password);
    }
}
