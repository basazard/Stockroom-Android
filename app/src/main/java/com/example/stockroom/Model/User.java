package com.example.stockroom.Model;

public class User {
    public String email, password, image;

    public User(){

    }
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.image = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.image = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
