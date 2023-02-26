/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jerrycoder.model.bo;

import br.com.jerrycoder.util.email.MXRecordChecker;
import br.com.jerrycoder.model.vo.SettingsTemplate;
import br.com.jerrycoder.model.vo.User;
import br.com.jerrycoder.model.vo.Proxy;
import br.com.jerrycoder.model.vo.ResponseConection;
import br.com.jerrycoder.util.email.EmailValidator;

/**
 *
 * @author jerry
 */
public class ConectionValidMail {

    ResponseConection response;

    public ConectionValidMail() {

        response = new ResponseConection();
    }

    public ResponseConection conectar(User dadosLogin, SettingsTemplate settingsTemplate) {

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

        if (settingsTemplate.getVerifyFormattEmail()) {
            if (!EmailValidator.validateEmail(dadosLogin.getUsername())) {
                response.setCodResponse(1);
                response.setStatusResponse("Formato email inválido");
                return response;
            }

        }

        if (settingsTemplate.getVerifyMx()) {
            if (MXRecordChecker.isValidMX(dadosLogin.getUsername())) {
                response.setCodResponse(0);
                response.setStatusResponse("MX Valid");
            } else {
                response.setCodResponse(0);
                response.setStatusResponse("MX Error");
            }
        }

        return response;
    }

}
