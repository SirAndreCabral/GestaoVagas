package com.example.demo.projeto.GestaoVagas.modules.company.useCases;

import com.example.demo.projeto.GestaoVagas.exception.CompanyNotFoundException;
import com.example.demo.projeto.GestaoVagas.modules.company.CompanyEntity;
import com.example.demo.projeto.GestaoVagas.modules.company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindOneCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity findOneCompany(UUID uuid) {
        return this.companyRepository.findById(uuid).orElseThrow(CompanyNotFoundException::new);
    }
}
