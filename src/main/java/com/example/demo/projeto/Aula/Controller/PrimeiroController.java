package com.example.demo.projeto.Aula.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/primeiraController")
public class PrimeiroController {

    @GetMapping("/primeiroMetodo/{id}")
    public String primeiroMetodo(@PathVariable String id) {
        return "o parametro é " + id;
    }

    @GetMapping("/metodoComQueryParams")
    public String metodoComQueryParams(@RequestParam String id) {
        return "O parametro com metodoComQueryParams é " + id;
    }

    @GetMapping("/metodoComQueryParams2")
    public String metodoComQueryParams2(@RequestParam Map<String, String> allParams) {
        return "O parametro com metodoComQueryParams é " + allParams.entrySet();
    }

    @PostMapping("/metodoBodyParams")
    public String metodoComBodyParams(@RequestBody String name) {
        return "Metodo com body params " + name;
    }

    @PostMapping("/metodoComHeaders")
    public String metodoComHeader(@RequestHeader Map<String, String> headers) {
        return "metodo com Header " + headers.entrySet();
    }

    @GetMapping("/metodoRespondeEntity")
    public ResponseEntity<String> metodoResponseEntity() {
        return ResponseEntity.status(400).body("Mensagem de erro");
    }
}