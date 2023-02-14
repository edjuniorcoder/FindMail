/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jerrycoder.core;

import br.com.jerrycoder.util.email.MXRecordChecker;
import br.com.jerrycoder.model.SettingsTemplate;
import br.com.jerrycoder.model.DadosLogin;
import br.com.jerrycoder.model.Proxy;
import br.com.jerrycoder.model.ResponseConection;
import br.com.jerrycoder.util.email.EmailValidator;

/**
 *
 * @author jerry
 */
public class ConectionSpliMail {
    
    ResponseConection response;
    
    public ConectionSpliMail() {
        
        response = new ResponseConection();
    }
    
    public ResponseConection conectar(DadosLogin dadosLogin, SettingsTemplate settingsTemplate) {
        
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
        
        String[] arrayUser = dadosLogin.getUsername().split("@");
        String server = arrayUser[1];
        
        response.setCodResponse(0);
        response.setStatusResponse(server);
        
        return response;
    }
    
}
