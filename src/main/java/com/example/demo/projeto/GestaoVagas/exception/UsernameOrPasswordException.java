package com.example.demo.projeto.GestaoVagas.exception;

public class UsernameOrPasswordException extends RuntimeException {
    public UsernameOrPasswordException() {
        super("Username or Password incorrect");
    }
}
