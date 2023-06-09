package com.example.demo.dto;

// Formato que retorna um usuário e suas informações com relação ao token
public class UserAndToken<T> {
    T user;
    String token;

    public UserAndToken(T user, String token) {
        this.user = user;
        this.token = token;
    }

    public T getUser() {
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
