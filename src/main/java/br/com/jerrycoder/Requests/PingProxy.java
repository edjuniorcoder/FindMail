/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jerrycoder.Requests;

import br.com.jerrycoder.model.Proxy;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author jerry
 */
public class PingProxy {

    org.apache.http.client.CookieStore httpCookieStore;
    CloseableHttpClient httpClient;
    RequestConfig config;
    RequestConfig.Builder reqconfigconbuilder;
    HttpHost proxyHost;
    HttpContext localContex;
    HttpClientBuilder clientbuilder;
    private String host;
    private String port;
    private String user;
    private String pass;

    public PingProxy(String host, String port, String user, String pass) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass;
    }

    public PingProxy(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public PingProxy() {

    }

    public void checkPing() {

        HttpHost proxyHost = new HttpHost(host, Integer.parseInt(port));

        // Cria as credencias da API
        CredentialsProvider credentialsPovider = new BasicCredentialsProvider();
        credentialsPovider.setCredentials(new org.apache.http.auth.AuthScope(host, Integer.parseInt(port)), new org.apache.http.auth.UsernamePasswordCredentials(user, pass));

        //Creating the HttpClientBuilder
        HttpClientBuilder clientbuilder = HttpClients.custom();
        clientbuilder = clientbuilder.setDefaultCredentialsProvider(credentialsPovider);

        CookieStore httpCookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = clientbuilder.setDefaultCookieStore(httpCookieStore).setRedirectStrategy(new LaxRedirectStrategy()).build();

        //Setting the proxy
        RequestConfig.Builder reqconfigconbuilder = RequestConfig.custom();
        reqconfigconbuilder = reqconfigconbuilder.setProxy(proxyHost);
        reqconfigconbuilder.setCircularRedirectsAllowed(false);
        reqconfigconbuilder.setRedirectsEnabled(false);
        reqconfigconbuilder.setRelativeRedirectsAllowed(false);
        reqconfigconbuilder.setMaxRedirects(0);

        RequestConfig config = reqconfigconbuilder.setConnectionRequestTimeout(10000).setConnectTimeout(10000).setSocketTimeout(10000).build();

        try {
            HttpGet requestGet = new HttpGet("https://ipv4.icanhazip.com/");
           // HttpGet requestGet = new HttpGet("https://ipapi.co/json/");
           // requestGet.setHeader("Host", "ipapi.co");
        //    requestGet.setHeader("Cookie", "csrftoken=tAjwAuenwJyK0NvjMZ9IRPON7tnhGf5jJTgXenbkYGMA5RNtyh5m1nCmqcCgt89X");
            requestGet.setHeader("sec-ch-ua", "\"Not_A Brand\";v=\"99\", \"Google Chrome\";v=\"109\", \"Chromium\";v=\"109\"");
            requestGet.setHeader("sec-ch-ua-mobile", "?0");
            requestGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");
            requestGet.setHeader("sec-ch-ua-platform", "\"Windows\"");
            requestGet.setHeader("accept", "*/*");
            requestGet.setHeader("sec-fetch-site", "same-origin");
            requestGet.setHeader("sec-fetch-mode", "cors");
            requestGet.setHeader("sec-fetch-dest", "empty");
      //      requestGet.setHeader("referer", "https://ipapi.co/");
            requestGet.setHeader("accept-language", "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7");
            requestGet.setConfig(config);

            HttpContext localContext = new BasicHttpContext();
            localContext.setAttribute(HttpClientContext.COOKIE_STORE, httpCookieStore);

            long startTime = System.currentTimeMillis();
          //  CloseableHttpResponse response = httpClient.execute( requestGet);
            CloseableHttpResponse response = httpClient.execute( proxyHost,requestGet);
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Elapsed time: " + elapsedTime + " ms");

            HttpEntity entityGet = response.getEntity();

            Document getHTML = Jsoup.parse(EntityUtils.toString(entityGet));

            String htmlGet = getHTML.body().text();

            System.out.println("htmlGet: " + htmlGet);

            JOptionPane.showMessageDialog(null, "Ping: " + elapsedTime + "\n"
                    + "Response: " + htmlGet);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }

    }

}
