package com.example.dossier.modelsDossier;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credentials")
public class Credentials {
    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;
    public Credentials() {
    }

    public Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}