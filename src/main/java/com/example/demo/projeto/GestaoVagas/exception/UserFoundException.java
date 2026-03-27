package com.example.demo.projeto.GestaoVagas.exception;

public class UserFoundException extends RuntimeException{

    public UserFoundException() {
        super("Usuário existente com esse Username ou Email");
    }
}
