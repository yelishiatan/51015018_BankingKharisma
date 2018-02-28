package com.example.vivibook.bankingkharisma.model;

/**
 * Created by VIVIBOOK on 1/26/2018.
 */

public class User {

    private int id;
    private String name;
    private String email;
    private String Password;
    private String Money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        this.Money = money;
    }
}
