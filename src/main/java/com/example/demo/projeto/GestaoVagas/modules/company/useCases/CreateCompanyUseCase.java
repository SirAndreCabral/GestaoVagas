package com.example.demo.projeto.GestaoVagas.modules.company.useCases;

import com.example.demo.projeto.GestaoVagas.exception.CompanyFoundException;
import com.example.demo.projeto.GestaoVagas.modules.company.CompanyEntity;
import com.example.demo.projeto.GestaoVagas.modules.company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity createCompany(CompanyEntity companyEntity) {

        this.companyRepository
                .findCompanyEntityByNameOrEmail(companyEntity.getName(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new CompanyFoundException();
                });

        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }
}
