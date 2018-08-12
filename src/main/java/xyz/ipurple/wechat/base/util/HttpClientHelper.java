package xyz.ipurple.wechat.base.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @ClassName: HttpClientHelper
 * @Description: //TODO
 * @Author: zcy
 * @Date: 2018/8/6 10:34
 * @Version: 1.0
 */
public class HttpClientHelper {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientHelper.class);

    private String url;
    private String cookie;
    private CookieStore cookieStore;
    private CloseableHttpClient httpClient;
    private HttpPost httpPost;
    private HttpGet httpGet;
    private String payLoad;
    private String charSet = "utf-8";
    private String contentType = "application/json";
    private List<NameValuePair> params = null;

    public HttpClientHelper(String url) {
        this.url = url;
    }
    public HttpClientHelper(String url, String cookie) {
        this.url = url;
        this.cookie = cookie;
    }

    public static HttpClientHelper build(String url) {
        return build(url, null);
    }

    public static  HttpClientHelper build(String url, String cookie) {
        return new HttpClientHelper(url, cookie);
    }

    public HttpResponse doPost() {
        try {
            createHttpClient();
            httpPost = new HttpPost(url);

            if (params != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(params, charSet));
            }
            if (StringUtils.isNotBlank(payLoad)) {
                StringEntity se = new StringEntity(payLoad, contentType, charSet);
                httpPost.setEntity(se);
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String responseContent = getResponseContent(response);
            return getHttpResponse(responseContent);
        } catch (UnsupportedEncodingException e) {
            logger.error("编码失败", e);
        } catch (ClientProtocolException e) {
            logger.error("httpclient 请求失败", e);
        } catch (IOException e) {
            logger.error("httpclient 请求失败", e);
        }
        return new HttpResponse();
    }

    public HttpResponse doGet() {
        try {
            createHttpClient();
            httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String responseContent = getResponseContent(response);
            return getHttpResponse(responseContent);
        } catch (ClientProtocolException e) {
            logger.error("httpclient 请求失败", e);
        } catch (IOException e) {
            logger.error("httpclient 请求失败", e);
        }
        return new HttpResponse();
    }

    public void doPostFile(String path, String fileName) {
        InputStream in = null;
        try {
            httpPost = new HttpPost(url);
            if (params != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(params, charSet));
            }
            HttpResponse httpResponse = doPost();
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            in = httpEntity.getContent();
            FileHelper.createFile(in, path, fileName);
            //释放资源
            EntityUtils.consume(httpEntity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createHttpClient() {
        try {
            URL uri = new URL(url);
            cookieStore = new BasicCookieStore();
            if (StringUtils.isNotBlank(cookie)) {
                BasicClientCookie basicClientCookie = new BasicClientCookie("JSESSIONID", cookie);
                basicClientCookie.setDomain(uri.getHost());
                basicClientCookie.setPath(uri.getPath());
                cookieStore.addCookie(basicClientCookie);
            }
            httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        } catch (MalformedURLException e) {
            logger.error("java.net.URL 创建失败", e);
        }
    }

    private String getResponseContent(CloseableHttpResponse response) {
        String responseContent = "";
        try {
            //获取响应内容
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                responseContent = EntityUtils.toString(httpEntity, "utf-8");
            }
        } catch (IOException e) {
            logger.error("获取相应内容失败", e);
        }
        return responseContent;
    }

    public HttpResponse getHttpResponse(String responseContent) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setContent(responseContent);
        getCookie(cookieStore, httpResponse);
        return httpResponse;
    }

    private static void getCookie(CookieStore cookieStore, HttpResponse httpResponse) {
        StringBuffer cookie = new StringBuffer();
        String jsessionId = null;
        String cookieUser = null;
        List<Cookie> cookies = cookieStore.getCookies();
        for (int i = 0; i < cookies.size(); i++) {
            cookie.append(cookies.get(i).getName())
                    .append("=")
                    .append(cookies.get(i).getValue())
                    .append(";");
            if (cookies.get(i).getName().equals("JSESSIONID")) {
                jsessionId = cookies.get(i).getValue();
            }
            if (cookies.get(i).getName().equals("cookie_user")) {
                cookieUser = cookies.get(i).getValue();
            }
        }
        httpResponse.setCookie(cookie.toString());
        httpResponse.setjSessionId(jsessionId);
        httpResponse.setCookieUser(cookieUser);
    }

    public String getCookie() {
        return cookie;
    }

    public HttpClientHelper setCookie(String cookie) {
        this.cookie = cookie;
        return this;
    }

    public String getPayLoad() {
        return payLoad;
    }

    public HttpClientHelper setPayLoad(String payLoad) {
        this.payLoad = payLoad;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public HttpClientHelper setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getCharSet() {
        return charSet;
    }

    public HttpClientHelper setCharSet(String charSet) {
        this.charSet = charSet;
        return this;
    }

    public List<NameValuePair> getParams() {
        return params;
    }

    public HttpClientHelper setParams(List<NameValuePair> params) {
        this.params = params;
        return this;
    }
}
