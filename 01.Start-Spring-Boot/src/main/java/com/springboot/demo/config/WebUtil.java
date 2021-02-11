package com.springboot.demo.config;

import com.alibaba.fastjson.JSON;
import com.sun.deploy.net.HttpResponse;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

/**
 * Web请求工具类.
 *
 * @author buer
 * @version 2017-08-21 18:24 1.0
 */
public abstract class WebUtil {


    public static final String DEFAULT_CHARSET = Charset.defaultCharset().toString();
    public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_DELETE = "DELETE";
    public static final String METHOD_PATCH = "PATCH";
    public static final String CTYPE_FORM_DATA = "application/x-www-form-urlencoded";
    public static final String CTYPE_FILE_UPLOAD = "multipart/form-data";
    public static final String CTYPE_TEXT_XML = "text/xml";
    public static final String CTYPE_TEXT_PLAIN = "text/plain";
    public static final String CTYPE_APP_JSON = "application/json";
    public static final boolean IGNORE_SSL_CHECK = false;
    public static final boolean IGNORE_HOST_CHECK = true;
    public static final int DEFAULT_CONNECT_TIMEOUT = 60 * 1000;
    public static final int DEFAULT_READ_TIMEOUT = 60 * 1000;


    public static String postJson(String urlNameString, String param, Map<String, String> headers, Map<String, String> cookies) {
        String result = "";
        final BasicCookieStore basicCookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(basicCookieStore)
                .build();
        try {
            StringEntity entity = new StringEntity(param, "utf-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            HttpPost post = new HttpPost(urlNameString);//这里发送get请求
            post.setEntity(entity);
            final ArrayList<Header> headers1 = new ArrayList<>();
            headers.forEach((key, value) -> {
                headers1.add(new BasicHeader(key, value));
            });
            Header[] header = new Header[headers1.size()];
            headers1.toArray(header);
            post.setHeaders(header);
            if(cookies != null && !cookies.isEmpty()) {
                cookies.forEach((key, value) -> {
                    BasicClientCookie cookie = new BasicClientCookie(key, value);
                    cookie.setDomain("sf2021.kuaishou.com");
                    cookie.setPath("/");
                    basicCookieStore.addCookie(cookie);
                });
            }

            // 通过请求对象获取响应对象
            final CloseableHttpResponse response = httpClient.execute(post);
            // 判断网络连接状态码是否正常(0--200都数正常)
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static String doPostJson(String url, String json, String charset, int connectTimeout,
                                    int readTimeout, Map<String, String> headerMap, List<HttpCookie> cookieContainer) throws IOException {
        String ctype = "application/json;charset=" + charset;
        byte[] content = json.getBytes(charset);
        return executePost(url, ctype, content, connectTimeout, readTimeout, headerMap, cookieContainer);
    }

    private static String executePost(String url, String ctype, byte[] content, int connectTimeout, int readTimeout,
                                      Map<String, String> headerMap, List<HttpCookie> cookieContainer) throws IOException {
        return execute(url, ctype, content, connectTimeout, readTimeout, headerMap, METHOD_POST, cookieContainer);
    }

    private static String execute(String url, String ctype, byte[] content, int connectTimeout,
                                  int readTimeout, Map<String, String> headerMap, String methodPost,
                                  List<HttpCookie> cookieContainer) throws IOException {
        String rsp;
        HttpURLConnection conn = null;
        OutputStream out = null;
        try {
            conn = getConnection(new URL(url), methodPost, ctype, headerMap);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            out = conn.getOutputStream();
            out.write(content);
            rsp = getResponseAsString(conn);
            Map<String, List<String>> headerFields = conn.getHeaderFields();
            List<String> cookiesHeaders = headerFields.get("Set-Cookie");
            if (null != cookiesHeaders && cookiesHeaders.size() > 0) {
                for (String cookiesHeader : cookiesHeaders) {
                    cookieContainer.add(HttpCookie.parse(cookiesHeader).get(0));
                }
            }
        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rsp;
    }

    private static HttpURLConnection getConnection(URL url, String method, String ctype, Map<String, String> headerMap)
            throws IOException {

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        if (conn instanceof HttpsURLConnection) {
            HttpsURLConnection connHttps = (HttpsURLConnection) conn;
//            if (IGNORE_SSL_CHECK) {
//                try {
//                    SSLContext ctx = SSLContext.getInstance("TLS");
//                    ctx.init(null, new TrustManager[]{new TrustAllTrustManager()}, new SecureRandom());
//                    connHttps.setSSLSocketFactory(ctx.getSocketFactory());
//                    connHttps.setHostnameVerifier((hostname, session) -> true);
//                } catch (Exception e) {
//                    throw new IOException(e.toString());
//                }
//            } else {
//                if (IGNORE_HOST_CHECK) {
//                    connHttps.setHostnameVerifier((hostname, session) -> true);
//                }
//            }
            conn = connHttps;
        }
        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Host", url.getHost());
        conn.setRequestProperty("User-Agent", "greatonce-base");
        conn.setRequestProperty("Content-Type", ctype);
        if (headerMap != null) {
            for (Entry<String, String> entry : headerMap.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        return conn;
    }

    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        String contentEncoding = conn.getContentEncoding();

        if (conn.getResponseCode() < 400) {
            if ("gzip".equalsIgnoreCase(contentEncoding)) {
                return getStreamAsString(new GZIPInputStream(conn.getInputStream()), charset);
            } else {
                return getStreamAsString(conn.getInputStream(), charset);
            }
        } else {
            String error;
            if ("gzip".equalsIgnoreCase(contentEncoding)) {
                error = getStreamAsString(new GZIPInputStream(conn.getErrorStream()), charset);
            } else {
                error = getStreamAsString(conn.getErrorStream(), charset);
            }

            if (error != null) {
                throw new IOException(conn.getResponseCode() + " " + conn.getResponseMessage());
            } else {
                throw new IOException(error);
            }
        }
    }
    public static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            Reader reader = new InputStreamReader(stream, charset);
            StringBuilder response = new StringBuilder();
            final char[] buff = new char[1024];
            int read = 0;
            while ((read = reader.read(buff)) > 0) {
                response.append(buff, 0, read);
            }
            return response.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    public static String getResponseCharset(String ctype) {
        String charset = DEFAULT_CHARSET;
        if (!StringUtils.isEmpty(ctype)) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (!StringUtils.isEmpty(pair[1])) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }
        return charset;
    }

}
