package com.example.demo.projeto.GestaoVagas.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super("Empresa não encontrada com esse nome ou email");
    }
}
