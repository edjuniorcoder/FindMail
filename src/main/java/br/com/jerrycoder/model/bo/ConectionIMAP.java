/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jerrycoder.model.bo;

import br.com.jerrycoder.model.vo.MailSession;
import br.com.jerrycoder.util.email.MXRecordChecker;
import br.com.jerrycoder.model.vo.SettingsTemplate;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import br.com.jerrycoder.model.vo.User;
import br.com.jerrycoder.model.vo.Proxy;
import br.com.jerrycoder.model.vo.ResponseConection;
import br.com.jerrycoder.util.email.EmailValidator;

/**
 *
 * @author jerry
 */
public class ConectionIMAP {

    public static ResponseConection authenticar(User user, Proxy proxy) {

        ResponseConection response = new ResponseConection();

        try {

            Session session = MailSession.getSessionImap(user, proxy);
            System.out.println("session: " + session);
            Store store = session.getStore(user.getProtocoloType());
          //  System.out.println("store: " + store);
            store.connect();

            if (store.isConnected()) {
                System.out.println("conectado");
                response.setCodResponse(0);
                response.setStatusResponse("Conectado");
            } else {
                System.out.println("não conectado");
                response.setCodResponse(3);
                response.setStatusResponse("Não Conectado");
            }
        } catch (javax.mail.AuthenticationFailedException ex) {
            //  ex.printStackTrace();
            response.setCodResponse(1);
            response.setStatusResponse("AuthenticationFailedException");
        } catch (NoSuchProviderException ex) {
           // ex.printStackTrace();
            response.setCodResponse(3);
            response.setStatusResponse("NoSuchProviderException");
        } catch (com.sun.mail.util.MailConnectException ex) {

            if (ex.toString().contains("Proxy Authentication Required")) {
                response.setCodResponse(2);
                response.setStatusResponse("Proxy Authentication Required");
            } else if (ex.toString().contains("java.net.SocketTimeoutException")) {
                response.setCodResponse(2);
                response.setStatusResponse("java.net.SocketTimeoutException");
            } else if (ex.toString().contains("502 Proxy Error")) {
                response.setCodResponse(3);
                response.setStatusResponse("502 Proxy Error");
            }else if (ex.toString().contains("Connection refused")) {
                response.setCodResponse(3);
                response.setStatusResponse("Connection refused");
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
            } else if (ex.toString().contains("STARTTLS failure")) {
                response.setCodResponse(3);
                response.setStatusResponse("STARTTLS failure");
            } else {
                ex.printStackTrace();
                response.setCodResponse(3);
                response.setStatusResponse("MessagingException");
            }

        }

        return response;

    }

}
