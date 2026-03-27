package com.example.demo.projeto.GestaoVagas.exception;

public class CompanyFoundException extends RuntimeException {
    public CompanyFoundException() {
        super("Empresa existente com esse nome ou email");
    }
}
