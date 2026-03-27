package com.example.demo.projeto.GestaoVagas.modules.company;

import com.example.demo.projeto.GestaoVagas.exception.CompanyFoundException;
import com.example.demo.projeto.GestaoVagas.modules.company.useCases.CreateCompanyUseCase;
import com.example.demo.projeto.GestaoVagas.modules.company.useCases.FindAllCompaniesUseCase;
import com.example.demo.projeto.GestaoVagas.modules.company.useCases.FindOneCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @Autowired
    private FindAllCompaniesUseCase findAllCompaniesUseCase;

    @Autowired
    private FindOneCompanyUseCase findOneCompanyUseCase;

    @PostMapping("/create-company")
    public @Valid ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
        try {

            var companyCreated = this.createCompanyUseCase.createCompany(companyEntity);

            return ResponseEntity.ok().body(companyCreated);

        }catch (CompanyFoundException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/find-all-companies")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<List<CompanyEntity>> findAll() {
        try {

            var companies = this.findAllCompaniesUseCase.listAllCompanies();

            return ResponseEntity.ok(companies);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("find-one-company/{uuid}")
    public ResponseEntity<Object> findOne(@PathVariable UUID uuid) {
        try {

            var company = this.findOneCompanyUseCase.findOneCompany(uuid);

            return ResponseEntity.ok().body(company);

        }catch (CompanyFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
