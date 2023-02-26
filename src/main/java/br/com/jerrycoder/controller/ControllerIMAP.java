/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jerrycoder.controller;

import br.com.jerrycoder.model.bo.ConectionIMAP;
import static br.com.jerrycoder.model.bo.ConectionIMAP.authenticar;
import br.com.jerrycoder.model.vo.Proxy;
import br.com.jerrycoder.model.vo.ResponseConection;
import br.com.jerrycoder.model.vo.Server;
import br.com.jerrycoder.model.vo.SettingsTemplate;
import br.com.jerrycoder.model.vo.User;
import br.com.jerrycoder.view.FrameIMAP;

/**
 *
 * @author jerry
 */
public class ControllerIMAP {

    SettingsTemplate settingsTemplate;
    User user;
    FrameIMAP viewIMAP;

    public ControllerIMAP(SettingsTemplate settingsTemplate, User user, FrameIMAP viewIMAP) {
        this.settingsTemplate = settingsTemplate;
        this.user = user;
        this.viewIMAP = viewIMAP;
    }

    public ResponseConection login() {
        Proxy proxy = null;
        ResponseConection response = new ResponseConection();

        if (settingsTemplate.getUseProxy()) {
            proxy = new Proxy();

            System.out.println("settingsTemplate.getUseProxy(): " + settingsTemplate.getUseProxy());
            String proxyStr = settingsTemplate.getValueProxyList();
            System.out.println("proxyStr: " + proxyStr);

            String[] array = proxyStr.split(":");
            proxy.setUseProxy(true);

            System.out.println("array: " + array.length);

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

            System.out.println("proxy: " + proxy);

        }

        System.out.println(user.getDominio());
        Server server = viewIMAP.listaServersTypeContains(user.getDominio());

        if (server != null) {
            viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText1());
            user.setServer(server.getServer());
            user.setPort(server.getPort());

            response = ConectionIMAP.authenticar(user, proxy);
            viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText1() + ":" + response.getStatusResponse());
            return response;
        } else {

            //TESTE 1
            System.out.println("TESTE 1");
            if (settingsTemplate.getPortEmail1() && settingsTemplate.getServerEmail1()) {
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText1());
                user.setServer(settingsTemplate.getServerText1());
                user.setPort(settingsTemplate.getPortText1());

                System.out.println("user controller:" + user);

                response = ConectionIMAP.authenticar(user, proxy);
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText1() + ":" + response.getStatusResponse());

                System.out.println("response: " + response);
                if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                    return response;
                }
            }

            //TESTE 2
            System.out.println("TESTE 2");
            if (settingsTemplate.getPortEmail2() && settingsTemplate.getServerEmail1()) {
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText2());
                user.setServer(settingsTemplate.getServerText1());
                user.setPort(settingsTemplate.getPortText2());

                response = ConectionIMAP.authenticar(user, proxy);
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText1() + ":" + response.getStatusResponse());
                if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                    return response;
                }
            }

            //TESTE 3
            System.out.println("TESTE 3");
            if (settingsTemplate.getPortEmail3() && settingsTemplate.getServerEmail1()) {
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText3());
                user.setServer(settingsTemplate.getServerText1());
                user.setPort(settingsTemplate.getPortText3());

                response = ConectionIMAP.authenticar(user, proxy);
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText1() + ":" + response.getStatusResponse());
                if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                    return response;
                }
            }

            //TESTE 4
            System.out.println("TESTE 4");
            if (settingsTemplate.getPortEmail1() && settingsTemplate.getServerEmail2()) {
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText2() + ":" + settingsTemplate.getPortText1());
                user.setServer(settingsTemplate.getServerText2());
                user.setPort(settingsTemplate.getPortText1());

                response = ConectionIMAP.authenticar(user, proxy);
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText1() + ":" + response.getStatusResponse());
                if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                    return response;
                }
            }

            //TESTE 5
            System.out.println("TESTE 5");
            if (settingsTemplate.getPortEmail2() && settingsTemplate.getServerEmail2()) {
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText2() + ":" + settingsTemplate.getPortText2());
                user.setServer(settingsTemplate.getServerText2());
                user.setPort(settingsTemplate.getPortText2());

                response = ConectionIMAP.authenticar(user, proxy);
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText1() + ":" + response.getStatusResponse());
                if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                    return response;
                }
            }

            //TESTE 6
            System.out.println("TESTE 6");
            if (settingsTemplate.getPortEmail3() && settingsTemplate.getServerEmail2()) {
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText2() + ":" + settingsTemplate.getPortText3());
                user.setServer(settingsTemplate.getServerText2());
                user.setPort(settingsTemplate.getPortText3());

                response = ConectionIMAP.authenticar(user, proxy);
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText1() + ":" + response.getStatusResponse());
                if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                    return response;
                }
            }

            //TESTE 7
            System.out.println("TESTE 7");
            if (settingsTemplate.getPortEmail1() && settingsTemplate.getServerEmail3()) {
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText3() + ":" + settingsTemplate.getPortText1());
                user.setServer(settingsTemplate.getServerText3());
                user.setPort(settingsTemplate.getPortText1());

                response = ConectionIMAP.authenticar(user, proxy);
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText1() + ":" + response.getStatusResponse());
                if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                    return response;
                }
            }

            //TESTE 8
            System.out.println("TESTE 8");
            if (settingsTemplate.getPortEmail2() && settingsTemplate.getServerEmail3()) {
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText3() + ":" + settingsTemplate.getPortText2());
                user.setServer(settingsTemplate.getServerText3());
                user.setPort(settingsTemplate.getPortText2());

                response = ConectionIMAP.authenticar(user, proxy);
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText1() + ":" + response.getStatusResponse());
                if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                    return response;
                }
            }

            //TESTE 9
            System.out.println("TESTE 9");
            if (settingsTemplate.getPortEmail3() && settingsTemplate.getServerEmail3()) {
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText3() + ":" + settingsTemplate.getPortText3());
                user.setServer(settingsTemplate.getServerText3());
                user.setPort(settingsTemplate.getPortText3());

                response = ConectionIMAP.authenticar(user, proxy);
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText1() + ":" + response.getStatusResponse());
                if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                    return response;
                }
            }

            //TESTE 10
            System.out.println("TESTE 10");
            if (settingsTemplate.getPortEmail1() && settingsTemplate.getServerEmail4()) {
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText4() + ":" + settingsTemplate.getPortText1());
                user.setServer(settingsTemplate.getServerText4());
                user.setPort(settingsTemplate.getPortText1());

                response = ConectionIMAP.authenticar(user, proxy);
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText1() + ":" + response.getStatusResponse());
                if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                    return response;
                }
            }

            //TESTE 11
            System.out.println("TESTE 11");
            if (settingsTemplate.getPortEmail2() && settingsTemplate.getServerEmail4()) {
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText4() + ":" + settingsTemplate.getPortText2());
                user.setServer(settingsTemplate.getServerText4());
                user.setPort(settingsTemplate.getPortText2());

                response = ConectionIMAP.authenticar(user, proxy);
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText1() + ":" + response.getStatusResponse());
                if (response.getCodResponse() == 0 || response.getCodResponse() == 2 || response.getStatusResponse().equals("AuthenticationFailedException")) {
                    return response;
                }
            }

            //TESTE 12
            System.out.println("TESTE 12");
            if (settingsTemplate.getPortEmail3() && settingsTemplate.getServerEmail4()) {
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText4() + ":" + settingsTemplate.getPortText3());
                user.setServer(settingsTemplate.getServerText4());
                user.setPort(settingsTemplate.getPortText3());

                response = ConectionIMAP.authenticar(user, proxy);
                viewIMAP.changeTableStatus(user.getUsername() + ":" + user.getPass(), "Testando: " + settingsTemplate.getServerText1() + ":" + settingsTemplate.getPortText1() + ":" + response.getStatusResponse());
                return response;
            }

            response.setCodResponse(3);
            response.setStatusResponse("Nenhum teste concluído");
            return response;

        }

    }

}
