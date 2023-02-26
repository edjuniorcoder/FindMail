/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jerrycoder.model.bo;

import java.io.IOException;
import java.net.CookieStore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
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
public class Requests {

    org.apache.http.client.CookieStore httpCookieStore;
    CloseableHttpClient httpClient;
    RequestConfig config;
    RequestConfig.Builder reqconfigconbuilder;
    HttpHost proxyHost;
    HttpContext localContex;
    HttpClientBuilder clientbuilder;

    public Requests() {

        CredentialsProvider credsProvider = null;

        //Creating the HttpClientBuilder
        clientbuilder = HttpClients.custom();

        httpCookieStore = new BasicCookieStore();
        httpClient = clientbuilder.setDefaultCookieStore(httpCookieStore).setRedirectStrategy(new LaxRedirectStrategy()).build();

        //Setting the proxy
        reqconfigconbuilder = RequestConfig.custom();
        reqconfigconbuilder.setCircularRedirectsAllowed(false);
        reqconfigconbuilder.setRedirectsEnabled(false);
        reqconfigconbuilder.setRelativeRedirectsAllowed(false);
        reqconfigconbuilder.setMaxRedirects(0);

        clientbuilder = clientbuilder.setDefaultCredentialsProvider(credsProvider);

        config = reqconfigconbuilder.setConnectionRequestTimeout(10000).setConnectTimeout(10000).setSocketTimeout(10000).build();
    }

    public String get(String url) {

        try {
            HttpGet requestGet = new HttpGet(url);
            requestGet.setHeader("sec-ch-ua", "\"Not_A Brand\";v=\"99\", \"Google Chrome\";v=\"109\", \"Chromium\";v=\"109\"");
            requestGet.setHeader("sec-ch-ua-mobile", "?0");
            requestGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");
            requestGet.setHeader("sec-ch-ua-platform", "\"Windows\"");
            requestGet.setHeader("accept", "*/*");
            requestGet.setHeader("sec-fetch-site", "same-origin");
            requestGet.setHeader("sec-fetch-mode", "cors");
            requestGet.setHeader("sec-fetch-dest", "empty");
            requestGet.setHeader("accept-language", "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7");

            HttpContext localContext = new BasicHttpContext();
            localContext.setAttribute(HttpClientContext.COOKIE_STORE, httpCookieStore);

            HttpResponse response = httpClient.execute(requestGet, localContext);
            HttpEntity entityGet = response.getEntity();

            Document getHTML = Jsoup.parse(EntityUtils.toString(entityGet));

            String htmlGet = getHTML.body().text();

            System.out.println("htmlGet: " + htmlGet);

            return htmlGet;
        } catch (IOException ex) {
            return ex.toString();
        }
    }
}
