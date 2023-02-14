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

/**
 *
 * @author jerry
 */
public class ConectionIMAP {

    ResponseConection response;

    public ConectionIMAP() {

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

            /*
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
                
                if(array.length ==2){
                     proxy.setAuth(false);
                }
                
                if(array.length ==4){
                     proxy.setAuth(true);
                }
                
               
            }
             */
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
            login(dadosLogin, proxy);
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 2
        if (settingsTemplate.getPortEmail2() && settingsTemplate.getServerEmail1()) {
            dadosLogin.setServer(settingsTemplate.getServerText1());
            dadosLogin.setPort(settingsTemplate.getPortText2());
            login(dadosLogin, proxy);
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 3
        if (settingsTemplate.getPortEmail3() && settingsTemplate.getServerEmail1()) {
            dadosLogin.setServer(settingsTemplate.getServerText1());
            dadosLogin.setPort(settingsTemplate.getPortText3());
            login(dadosLogin, proxy);
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 4
        if (settingsTemplate.getPortEmail1() && settingsTemplate.getServerEmail2()) {
            dadosLogin.setServer(settingsTemplate.getServerText2());
            dadosLogin.setPort(settingsTemplate.getPortText1());
            login(dadosLogin, proxy);
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 5
        if (settingsTemplate.getPortEmail2() && settingsTemplate.getServerEmail2()) {
            dadosLogin.setServer(settingsTemplate.getServerText2());
            dadosLogin.setPort(settingsTemplate.getPortText2());
            login(dadosLogin, proxy);
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 6
        if (settingsTemplate.getPortEmail3() && settingsTemplate.getServerEmail2()) {
            dadosLogin.setServer(settingsTemplate.getServerText2());
            dadosLogin.setPort(settingsTemplate.getPortText3());
            login(dadosLogin, proxy);
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 7
        if (settingsTemplate.getPortEmail1() && settingsTemplate.getServerEmail3()) {
            dadosLogin.setServer(settingsTemplate.getServerText3());
            dadosLogin.setPort(settingsTemplate.getPortText1());
            login(dadosLogin, proxy);
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 8
        if (settingsTemplate.getPortEmail2() && settingsTemplate.getServerEmail3()) {
            dadosLogin.setServer(settingsTemplate.getServerText3());
            dadosLogin.setPort(settingsTemplate.getPortText2());
            login(dadosLogin, proxy);
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 9
        if (settingsTemplate.getPortEmail3() && settingsTemplate.getServerEmail3()) {
            dadosLogin.setServer(settingsTemplate.getServerText3());
            dadosLogin.setPort(settingsTemplate.getPortText3());
            login(dadosLogin, proxy);
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 10
        if (settingsTemplate.getPortEmail1() && settingsTemplate.getServerEmail4()) {
            dadosLogin.setServer(settingsTemplate.getServerText4());
            dadosLogin.setPort(settingsTemplate.getPortText1());
            login(dadosLogin, proxy);
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 12
        if (settingsTemplate.getPortEmail2() && settingsTemplate.getServerEmail4()) {
            dadosLogin.setServer(settingsTemplate.getServerText4());
            dadosLogin.setPort(settingsTemplate.getPortText2());
            login(dadosLogin, proxy);
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }

        //TESTE 13
        if (settingsTemplate.getPortEmail3() && settingsTemplate.getServerEmail4()) {
            dadosLogin.setServer(settingsTemplate.getServerText4());
            dadosLogin.setPort(settingsTemplate.getPortText3());
            login(dadosLogin, proxy);
            if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                return response;
            }
        }
        return response;
    }

    private void login(DadosLogin dadosLogin, Proxy proxy) {

        try {
            MailSession mailSession = new MailSession(dadosLogin, proxy);
            Session session = mailSession.getSession();
            Store store = session.getStore(dadosLogin.getProtocoloType());
            store.connect();

            if (store.isConnected()) {
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
        } catch (com.sun.mail.util.MailConnectException ex) {

            if (ex.toString().contains("Proxy Authentication Required")) {
                response.setCodResponse(2);
                response.setStatusResponse("Proxy Authentication Required");
            }else if (ex.toString().contains("502 Proxy Error")) {
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
            } else {
                ex.printStackTrace();
                response.setCodResponse(3);
                response.setStatusResponse("MessagingException");
            }

        }

    }

}
