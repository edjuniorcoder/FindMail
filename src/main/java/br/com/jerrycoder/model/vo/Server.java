/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jerrycoder.model.vo;

/**
 *
 * @author jerry
 */
public class Server {
    
    private String dominio;
    private String server;
    private String port;
    private boolean isSSL;
    private String protocoloType;
    private boolean isNull;

    public Server() {
    }

    public Server(String dominio, String server, String port) {
        this.dominio = dominio;
        this.server = server;
        this.port = port;
    }

 

    public Server(String dominio, String server, String port, boolean isSSL, String protocoloType) {
        this.dominio = dominio;
        this.server = server;
        this.port = port;
        this.isSSL = isSSL;
        this.protocoloType = protocoloType;
    }

    public boolean isIsNull() {
        return isNull;
    }

    public void setIsNull(boolean isNull) {
        this.isNull = isNull;
    }
    
    
    
    

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }
    
    

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean isIsSSL() {
        return isSSL;
    }

    public void setIsSSL(boolean isSSL) {
        this.isSSL = isSSL;
    }
    
    

    public String getProtocoloType() {
        return protocoloType;
    }

    public void setProtocoloType(String protocoloType) {
        this.protocoloType = protocoloType;
    }

    @Override
    public String toString() {
        return "Server{" + "server=" + server + ", port=" + port + ", protocoloType=" + protocoloType + '}';
    }

    
    
    
    
}
