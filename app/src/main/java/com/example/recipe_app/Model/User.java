package com.example.recipe_app.Model;

/**
 * Created by sickbay on 12/13/2017.
 */

public class User {

    private String password;
    private String email;
    private String name;



    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;


    }

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
