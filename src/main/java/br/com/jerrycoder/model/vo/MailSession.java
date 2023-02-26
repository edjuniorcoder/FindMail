/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jerrycoder.model.vo;

/**
 *
 * @author jerry
 */
import javax.mail.*;
import java.util.Properties;

public class MailSession {

    public static Session getSessionImap(User user, Proxy proxy) {

        Properties properties = System.getProperties();
        properties.put("mail.imap.host", user.getServer()); //SMTP Host
        properties.put("mail.imap.port", user.getPort()); //TLS Port
        properties.put("mail.imap.auth", "true"); //enable authentication
        properties.put("mail.imap.starttls.enable", "true"); //enable STARTTLS
        properties.put("mail.imap.ssl.protocols", "TLSv1.2");
        properties.put("mail.imap.timeout", "10000");
        properties.put("mail.imap.connectiontimeout", "10000");

      
        // SSL setting
        properties.put("mail.imap.socketFactory.fallback", "true"); // Should be true
        properties.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.imap.socketFactory.port", user.getPort());

        //     properties.put("mail.imap.ssl.enable", true);
        properties.put("mail.imap.ssl.trust", "*");
        

        try {

            if (proxy != null && proxy.getUseProxy()) {
                properties.put("java.net.useSystemProxies", "true");
                properties.put("mail.imap.proxy.host", proxy.getHost());
                properties.put("mail.imap.proxy.port", proxy.getPort());

                if (proxy != null && proxy.getAuth()) {
                    properties.put("mail.imap.proxy.user", proxy.getUser());
                    properties.put("mail.imap.proxy.password", proxy.getPass());
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user.getUsername(), user.getPass());
            }
        });

        return session;

    }

    public static Session getSessionSmtp(User user, Proxy proxy) {

        Session session = null;
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", user.getServer()); //SMTP Host
        properties.put("mail.smtp.port", user.getPort()); //TLS Port

        // SSL setting
        properties.put("mail.smtp.socketFactory.fallback", "true"); // Should be true
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.port", user.getPort());
        properties.put("mail.smtp.timeout", "30000");
        properties.put("mail.smtp.connectiontimeout", "30000");
        //     properties.put("mail.imap.ssl.enable", true);

        properties.put("mail.smtp.auth", "true"); //enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        properties.put("mail.smtp.ssl.trust", "*");

        if (proxy != null && proxy.getUseProxy()) {
            properties.put("java.net.useSystemProxies", "true");
            properties.put("mail.smtp.proxy.host", proxy.getHost());
            properties.put("mail.smtp.proxy.port", proxy.getPort());

            if (proxy.getAuth()) {
                properties.put("mail.smtp.proxy.user", proxy.getUser());
                properties.put("mail.smtp.proxy.password", proxy.getPass());
            }

        }

        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user.getUsername(), user.getPass());
            }
        });

        return session;

    }
}
