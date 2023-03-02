/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jerrycoder.model.vo;

import java.io.Serializable;

/**
 *
 * @author jerry
 */
public class User extends Server implements Serializable {

    private String username;
    private String pass;



    public User(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public User(String username, String pass, String server, String port, String protocoloType) {
        super(server, port, protocoloType);
        this.username = username;
        this.pass = pass;
    }
    
        public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return username + ":" + pass + ":" + this.getServer() + ":" + this.getPort();
    }

    


    

    

}
