
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
/**
 *
 * @author jerry
 */
public class FindMail {

    public static void main(String[] args) throws NoSuchProviderException, MessagingException, IOException {

        String host = "proxy.packetstream.io";
        String port = "31112";
        String user = "jerry171";
        String pass = "33bkwcR8ZPhcNqSx";

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

        RequestConfig config = reqconfigconbuilder.setConnectionRequestTimeout(10000).setConnectTimeout(10000).setSocketTimeout(10000).build();

        //HttpGet requestGet = new HttpGet("https://meuip.com.br/");
        HttpGet requestGet = new HttpGet("https://ipapi.co/json/");
        requestGet.setHeader("Host", "ipapi.co");
        requestGet.setHeader("Cookie", "csrftoken=tAjwAuenwJyK0NvjMZ9IRPON7tnhGf5jJTgXenbkYGMA5RNtyh5m1nCmqcCgt89X");
        requestGet.setHeader("sec-ch-ua", "\"Not_A Brand\";v=\"99\", \"Google Chrome\";v=\"109\", \"Chromium\";v=\"109\"");
        requestGet.setHeader("sec-ch-ua-mobile", "?0");
        requestGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");
        requestGet.setHeader("sec-ch-ua-platform", "\"Windows\"");
        requestGet.setHeader("accept", "*/*");
        requestGet.setHeader("sec-fetch-site", "same-origin");
        requestGet.setHeader("sec-fetch-mode", "cors");
        requestGet.setHeader("sec-fetch-dest", "empty");
        requestGet.setHeader("referer", "https://ipapi.co/");
        requestGet.setHeader("accept-language", "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7");
        requestGet.setConfig(config);

        HttpContext localContext = new BasicHttpContext();
        localContext.setAttribute(HttpClientContext.COOKIE_STORE, httpCookieStore);

        long startTime = System.currentTimeMillis();
        //  CloseableHttpResponse response = httpClient.execute( requestGet);
        CloseableHttpResponse response = httpClient.execute(proxyHost, requestGet);
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " ms");

        HttpEntity entityGet = response.getEntity();

        Document getHTML = Jsoup.parse(EntityUtils.toString(entityGet));

        String htmlGet = getHTML.body().text();

        System.out.println("htmlGet: " + htmlGet);
    }
}
