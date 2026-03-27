package com.example.demo.projeto.GestaoVagas.modules.company.useCases;

import com.example.demo.projeto.GestaoVagas.modules.company.CompanyEntity;
import com.example.demo.projeto.GestaoVagas.modules.company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllCompaniesUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public List<CompanyEntity> listAllCompanies() {
        return this.companyRepository.findAll();
    }
}
