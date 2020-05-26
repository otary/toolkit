package cn.chenzw.toolkit.ws;

import cn.chenzw.toolkit.ws.util.SoapUtils;
import okhttp3.*;
import okhttp3.internal.Util;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author chenzw
 */
public class SoapOkHttpClient {

    private static final String MEDIA_TYPE_XML = "text/xml;charset=";

    private final OkHttpClient okHttpClient;

    /**
     * @param soapRequest
     * @return
     */
    public Call newCall(SoapRequest soapRequest) {
        MediaType mediaType = MediaType.parse(MEDIA_TYPE_XML + soapRequest.getCharset().name());
        return this.okHttpClient.newCall(new Request.Builder()
                .url(soapRequest.getUrl())
                .post(RequestBody.create(mediaType, SoapUtils.toString(soapRequest.getMessage(), soapRequest.getCharset())))
                .build());
    }

    public static Builder create() {
        return new Builder();
    }


    private SoapOkHttpClient(Builder builder) {

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .dispatcher(builder.dispatcher)
                .proxy(builder.proxy);

        if (builder.protocols != null) {
            okHttpClientBuilder.protocols(builder.protocols);
        }
        if (builder.connectionSpecs != null) {
            okHttpClientBuilder.connectionSpecs(builder.connectionSpecs);
        }

        okHttpClientBuilder.proxySelector(builder.proxySelector)
                .cookieJar(builder.cookieJar)
                .cache(builder.cache)
                .socketFactory(builder.socketFactory)
                .hostnameVerifier(builder.hostnameVerifier)
                .proxyAuthenticator(builder.proxyAuthenticator)
                .authenticator(builder.authenticator)
                .connectionPool(builder.connectionPool)
                .dns(builder.dns)
                .followRedirects(builder.followRedirects)
                .followSslRedirects(builder.followSslRedirects)
                .retryOnConnectionFailure(builder.retryOnConnectionFailure)
                .connectTimeout(builder.connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(builder.readTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(builder.writeTimeout, TimeUnit.MILLISECONDS);

        for (Interceptor interceptor : builder.interceptors) {
            okHttpClientBuilder.addInterceptor(interceptor);
        }
        for (Interceptor networkInterceptor : builder.networkInterceptors) {
            okHttpClientBuilder.addNetworkInterceptor(networkInterceptor);
        }

        this.okHttpClient = okHttpClientBuilder.build();

    }


    public static final class Builder {
        Dispatcher dispatcher;
        java.net.Proxy proxy;
        List<Protocol> protocols;
        List<ConnectionSpec> connectionSpecs;
        final List<Interceptor> interceptors = new ArrayList<>();
        final List<Interceptor> networkInterceptors = new ArrayList<>();
        ProxySelector proxySelector;
        CookieJar cookieJar;
        Cache cache;
        InternalCache internalCache;
        SocketFactory socketFactory;
        SSLSocketFactory sslSocketFactory;
        CertificateChainCleaner certificateChainCleaner;
        HostnameVerifier hostnameVerifier;
        Authenticator proxyAuthenticator;
        Authenticator authenticator;
        ConnectionPool connectionPool;
        Dns dns;
        boolean followSslRedirects;
        boolean followRedirects;
        boolean retryOnConnectionFailure;
        int connectTimeout;
        int readTimeout;
        int writeTimeout;

        public Builder() {
            dispatcher = new Dispatcher();
            proxySelector = ProxySelector.getDefault();
            cookieJar = CookieJar.NO_COOKIES;
            socketFactory = SocketFactory.getDefault();
            hostnameVerifier = OkHostnameVerifier.INSTANCE;
            proxyAuthenticator = Authenticator.NONE;
            authenticator = Authenticator.NONE;
            connectionPool = new ConnectionPool();
            dns = Dns.SYSTEM;
            followSslRedirects = true;
            followRedirects = true;
            retryOnConnectionFailure = true;
            connectTimeout = 10_000;
            readTimeout = 10_000;
            writeTimeout = 10_000;
        }

        /**
         * Sets the default connect timeout for new connections. A value of 0 means no timeout,
         * otherwise values must be between 1 and {@link Integer#MAX_VALUE} when converted to
         * milliseconds.
         */
        public Builder connectTimeout(long timeout, TimeUnit unit) {
            if (timeout < 0) throw new IllegalArgumentException("timeout < 0");
            if (unit == null) throw new NullPointerException("unit == null");
            long millis = unit.toMillis(timeout);
            if (millis > Integer.MAX_VALUE) throw new IllegalArgumentException("Timeout too large.");
            if (millis == 0 && timeout > 0) throw new IllegalArgumentException("Timeout too small.");
            connectTimeout = (int) millis;
            return this;
        }

        /**
         * Sets the default read timeout for new connections. A value of 0 means no timeout, otherwise
         * values must be between 1 and {@link Integer#MAX_VALUE} when converted to milliseconds.
         */
        public Builder readTimeout(long timeout, TimeUnit unit) {
            if (timeout < 0) throw new IllegalArgumentException("timeout < 0");
            if (unit == null) throw new NullPointerException("unit == null");
            long millis = unit.toMillis(timeout);
            if (millis > Integer.MAX_VALUE) throw new IllegalArgumentException("Timeout too large.");
            if (millis == 0 && timeout > 0) throw new IllegalArgumentException("Timeout too small.");
            readTimeout = (int) millis;
            return this;
        }

        /**
         * Sets the default write timeout for new connections. A value of 0 means no timeout, otherwise
         * values must be between 1 and {@link Integer#MAX_VALUE} when converted to milliseconds.
         */
        public Builder writeTimeout(long timeout, TimeUnit unit) {
            if (timeout < 0) throw new IllegalArgumentException("timeout < 0");
            if (unit == null) throw new NullPointerException("unit == null");
            long millis = unit.toMillis(timeout);
            if (millis > Integer.MAX_VALUE) throw new IllegalArgumentException("Timeout too large.");
            if (millis == 0 && timeout > 0) throw new IllegalArgumentException("Timeout too small.");
            writeTimeout = (int) millis;
            return this;
        }

        /**
         * Sets the HTTP proxy that will be used by connections created by this client. This takes
         * precedence over {@link #proxySelector}, which is only honored when this proxy is null (which
         * it is by default). To disable proxy use completely, call {@code setProxy(Proxy.NO_PROXY)}.
         */
        public Builder proxy(Proxy proxy) {
            this.proxy = proxy;
            return this;
        }

        /**
         * Sets the proxy selection policy to be used if no {@link #proxy proxy} is specified
         * explicitly. The proxy selector may return dynamic proxies; in that case they will be tried
         * in sequence until a successful connection is established.
         *
         * <p>If unset, the {@link ProxySelector#getDefault() system-wide default} proxy selector will
         * be used.
         */
        public Builder proxySelector(ProxySelector proxySelector) {
            this.proxySelector = proxySelector;
            return this;
        }

        /**
         * Sets the handler that can accept cookies from incoming HTTP responses and provides cookies to
         * outgoing HTTP requests.
         *
         * <p>If unset, {@linkplain CookieJar#NO_COOKIES no cookies} will be accepted nor provided.
         */
        public Builder cookieJar(CookieJar cookieJar) {
            if (cookieJar == null) throw new NullPointerException("cookieJar == null");
            this.cookieJar = cookieJar;
            return this;
        }

        /**
         * Sets the response cache to be used to read and write cached responses.
         */
        void setInternalCache(InternalCache internalCache) {
            this.internalCache = internalCache;
            this.cache = null;
        }

        public Builder cache(Cache cache) {
            this.cache = cache;
            this.internalCache = null;
            return this;
        }

        /**
         * Sets the DNS service used to lookup IP addresses for hostnames.
         *
         * <p>If unset, the {@link Dns#SYSTEM system-wide default} DNS will be used.
         */
        public Builder dns(Dns dns) {
            if (dns == null) throw new NullPointerException("dns == null");
            this.dns = dns;
            return this;
        }

        /**
         * Sets the socket factory used to create connections. OkHttp only uses the parameterless {@link
         * SocketFactory#createSocket() createSocket()} method to create unconnected sockets. Overriding
         * this method, e. g., allows the socket to be bound to a specific local address.
         *
         * <p>If unset, the {@link SocketFactory#getDefault() system-wide default} socket factory will
         * be used.
         */
        public Builder socketFactory(SocketFactory socketFactory) {
            if (socketFactory == null) throw new NullPointerException("socketFactory == null");
            this.socketFactory = socketFactory;
            return this;
        }

        /**
         * Sets the socket factory used to secure HTTPS connections. If unset, the system default will
         * be used.
         *
         * @deprecated {@code SSLSocketFactory} does not expose its {@link X509TrustManager}, which is
         * a field that OkHttp needs to build a clean certificate chain. This method instead must
         * use reflection to extract the trust manager. Applications should prefer to call {@link
         * #sslSocketFactory(SSLSocketFactory, X509TrustManager)}, which avoids such reflection.
         */
        public Builder sslSocketFactory(SSLSocketFactory sslSocketFactory) {
            if (sslSocketFactory == null) throw new NullPointerException("sslSocketFactory == null");
            this.sslSocketFactory = sslSocketFactory;
            this.certificateChainCleaner = Platform.get().buildCertificateChainCleaner(sslSocketFactory);
            return this;
        }

        /**
         * Sets the socket factory and trust manager used to secure HTTPS connections. If unset, the
         * system defaults will be used.
         *
         * <p>Most applications should not call this method, and instead use the system defaults. Those
         * classes include special optimizations that can be lost if the implementations are decorated.
         *
         * <p>If necessary, you can create and configure the defaults yourself with the following code:
         *
         * <pre>   {@code
         *
         *   TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
         *       TrustManagerFactory.getDefaultAlgorithm());
         *   trustManagerFactory.init((KeyStore) null);
         *   TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
         *   if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
         *     throw new IllegalStateException("Unexpected default trust managers:"
         *         + Arrays.toString(trustManagers));
         *   }
         *   X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
         *
         *   SSLContext sslContext = SSLContext.getInstance("TLS");
         *   sslContext.init(null, new TrustManager[] { trustManager }, null);
         *   SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
         *
         *   OkHttpClient client = new OkHttpClient.Builder()
         *       .sslSocketFactory(sslSocketFactory, trustManager);
         *       .build();
         * }</pre>
         */
        public Builder sslSocketFactory(
                SSLSocketFactory sslSocketFactory, X509TrustManager trustManager) {
            if (sslSocketFactory == null) throw new NullPointerException("sslSocketFactory == null");
            if (trustManager == null) throw new NullPointerException("trustManager == null");
            this.sslSocketFactory = sslSocketFactory;
            this.certificateChainCleaner = CertificateChainCleaner.get(trustManager);
            return this;
        }

        /**
         * Sets the verifier used to confirm that response certificates apply to requested hostnames for
         * HTTPS connections.
         *
         * <p>If unset, a default hostname verifier will be used.
         */
        public Builder hostnameVerifier(HostnameVerifier hostnameVerifier) {
            if (hostnameVerifier == null) throw new NullPointerException("hostnameVerifier == null");
            this.hostnameVerifier = hostnameVerifier;
            return this;
        }

        /**
         * Sets the authenticator used to respond to challenges from origin servers. Use {@link
         * #proxyAuthenticator} to set the authenticator for proxy servers.
         *
         * <p>If unset, the {@linkplain Authenticator#NONE no authentication will be attempted}.
         */
        public Builder authenticator(Authenticator authenticator) {
            if (authenticator == null) throw new NullPointerException("authenticator == null");
            this.authenticator = authenticator;
            return this;
        }

        /**
         * Sets the authenticator used to respond to challenges from proxy servers. Use {@link
         * #authenticator} to set the authenticator for origin servers.
         *
         * <p>If unset, the {@linkplain Authenticator#NONE no authentication will be attempted}.
         */
        public Builder proxyAuthenticator(Authenticator proxyAuthenticator) {
            if (proxyAuthenticator == null) throw new NullPointerException("proxyAuthenticator == null");
            this.proxyAuthenticator = proxyAuthenticator;
            return this;
        }

        /**
         * Sets the connection pool used to recycle HTTP and HTTPS connections.
         *
         * <p>If unset, a new connection pool will be used.
         */
        public Builder connectionPool(ConnectionPool connectionPool) {
            if (connectionPool == null) throw new NullPointerException("connectionPool == null");
            this.connectionPool = connectionPool;
            return this;
        }

        /**
         * Configure this client to follow redirects from HTTPS to HTTP and from HTTP to HTTPS.
         *
         * <p>If unset, protocol redirects will be followed. This is different than the built-in {@code
         * HttpURLConnection}'s default.
         */
        public Builder followSslRedirects(boolean followProtocolRedirects) {
            this.followSslRedirects = followProtocolRedirects;
            return this;
        }

        /**
         * Configure this client to follow redirects. If unset, redirects be followed.
         */
        public Builder followRedirects(boolean followRedirects) {
            this.followRedirects = followRedirects;
            return this;
        }

        /**
         * Configure this client to retry or not when a connectivity problem is encountered. By default,
         * this client silently recovers from the following problems:
         *
         * <ul>
         * <li><strong>Unreachable IP addresses.</strong> If the URL's host has dynamic IP addresses,
         * failure to reach any individual IP address doesn't fail the overall request. This can
         * increase availability of multi-homed services.
         * <li><strong>Stale pooled connections.</strong> The {@link ConnectionPool} reuses sockets
         * to decrease request latency, but these connections will occasionally time out.
         * <li><strong>Unreachable proxy servers.</strong> A {@link ProxySelector} can be used to
         * attempt dynamic proxy servers in sequence, eventually falling back to a direct
         * connection.
         * </ul>
         * <p>
         * Set this to false to avoid retrying requests when doing so is destructive. In this case the
         * calling application should do its own recovery of connectivity failures.
         */
        public Builder retryOnConnectionFailure(boolean retryOnConnectionFailure) {
            this.retryOnConnectionFailure = retryOnConnectionFailure;
            return this;
        }

        /**
         * Sets the dispatcher used to set policy and execute asynchronous requests. Must not be null.
         */
        public Builder dispatcher(Dispatcher dispatcher) {
            if (dispatcher == null) throw new IllegalArgumentException("dispatcher == null");
            this.dispatcher = dispatcher;
            return this;
        }

        /**
         * Configure the protocols used by this client to communicate with remote servers. By default
         * this client will prefer the most efficient transport available, falling back to more
         * ubiquitous protocols. Applications should only call this method to avoid specific
         * compatibility problems, such as web servers that behave incorrectly when SPDY is enabled.
         *
         * <p>The following protocols are currently supported:
         *
         * <ul>
         * <li><a href="http://www.w3.org/Protocols/rfc2616/rfc2616.html">http/1.1</a>
         * <li><a
         * href="http://www.chromium.org/spdy/spdy-protocol/spdy-protocol-draft3-1">spdy/3.1</a>
         * <li><a href="http://tools.ietf.org/html/draft-ietf-httpbis-http2-17">h2</a>
         * </ul>
         *
         * <p><strong>This is an evolving set.</strong> Future releases include support for transitional
         * protocols. The http/1.1 transport will never be dropped.
         *
         * <p>If dynamic protocols are specified, <a
         * href="http://tools.ietf.org/html/draft-ietf-tls-applayerprotoneg">ALPN</a> will be used to
         * negotiate a transport.
         *
         * <p>{@link Protocol#HTTP_1_0} is not supported in this set. Requests are initiated with {@code
         * HTTP/1.1} only. If the server responds with {@code HTTP/1.0}, that will be exposed by {@link
         * Response#protocol()}.
         *
         * @param protocols the protocols to use, in order of preference. The list must contain {@link
         *                  Protocol#HTTP_1_1}. It must not contain null or {@link Protocol#HTTP_1_0}.
         */
        public Builder protocols(List<Protocol> protocols) {
            protocols = Util.immutableList(protocols);
            if (!protocols.contains(Protocol.HTTP_1_1)) {
                throw new IllegalArgumentException("protocols doesn't contain http/1.1: " + protocols);
            }
            if (protocols.contains(Protocol.HTTP_1_0)) {
                throw new IllegalArgumentException("protocols must not contain http/1.0: " + protocols);
            }
            if (protocols.contains(null)) {
                throw new IllegalArgumentException("protocols must not contain null");
            }
            this.protocols = Util.immutableList(protocols);
            return this;
        }

        public Builder connectionSpecs(List<ConnectionSpec> connectionSpecs) {
            this.connectionSpecs = Util.immutableList(connectionSpecs);
            return this;
        }

        /**
         * Returns a modifiable list of interceptors that observe the full span of each call: from
         * before the connection is established (if any) until after the response source is selected
         * (either the origin server, cache, or both).
         */
        public List<Interceptor> interceptors() {
            return interceptors;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            interceptors.add(interceptor);
            return this;
        }

        /**
         * Returns a modifiable list of interceptors that observe a single network request and response.
         * These interceptors must call {@link Interceptor.Chain#proceed} exactly once: it is an error
         * for a network interceptor to short-circuit or repeat a network request.
         */
        public List<Interceptor> networkInterceptors() {
            return networkInterceptors;
        }

        public Builder addNetworkInterceptor(Interceptor interceptor) {
            networkInterceptors.add(interceptor);
            return this;
        }

        public SoapOkHttpClient build() {
            return new SoapOkHttpClient(this);
        }

    }

}
