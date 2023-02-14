/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jerrycoder.core;

/**
 *
 * @author jerry
 */
import javax.mail.*;
import java.util.Properties;
import br.com.jerrycoder.model.DadosLogin;
import br.com.jerrycoder.model.Proxy;

public class MailSession {

    private Session session;
    private String server;
    private String port;
    private String user;
    private String pass;

    public MailSession(DadosLogin dadosLogin, Proxy proxy) {
        this.server = dadosLogin.getServer();
        this.port = dadosLogin.getPort();
        this.user = dadosLogin.getUsername();
        this.pass = dadosLogin.getPass();

        System.out.println("dadosLogin: " + dadosLogin.toString());
        System.out.println("proxy: " + proxy.toString());

        Properties properties = System.getProperties();
        properties.put("mail." + dadosLogin.getProtocoloType() + ".host", server); //SMTP Host
        properties.put("mail." + dadosLogin.getProtocoloType() + ".port", port); //TLS Port

        // SSL setting
        properties.put("mail." + dadosLogin.getProtocoloType() + ".socketFactory.fallback", "true"); // Should be true
        properties.put("mail." + dadosLogin.getProtocoloType() + ".socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail." + dadosLogin.getProtocoloType() + ".socketFactory.port", port);
        properties.put("mail." + dadosLogin.getProtocoloType() + ".timeout", "30000");
        properties.put("mail." + dadosLogin.getProtocoloType() + ".connectiontimeout", "30000");
        //     properties.put("mail.imap.ssl.enable", true);

        properties.put("mail." + dadosLogin.getProtocoloType() + ".auth", "true"); //enable authentication
        properties.put("mail." + dadosLogin.getProtocoloType() + ".starttls.enable", "true"); //enable STARTTLS
        properties.put("mail." + dadosLogin.getProtocoloType() + ".ssl.protocols", "TLSv1.2");

        properties.put("mail." + dadosLogin.getProtocoloType() + ".ssl.trust", "*");

        if (proxy.getUseProxy()) {
            properties.put("java.net.useSystemProxies", "true");
            properties.put("mail." + dadosLogin.getProtocoloType() + ".proxy.host", proxy.getHost());
            properties.put("mail." + dadosLogin.getProtocoloType() + ".proxy.port", proxy.getPort());

            if (proxy.getAuth()) {
                properties.put("mail." + dadosLogin.getProtocoloType() + ".proxy.user", proxy.getUser());
                properties.put("mail." + dadosLogin.getProtocoloType() + ".proxy.password", proxy.getPass());
            }

        }

        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

    }

    public Session getSession() {
        return session;
    }
}
