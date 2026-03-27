package com.example.demo.projeto.GestaoVagas.modules.company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findCompanyEntityByNameOrEmail(String name, String email);

    Optional<CompanyEntity> findByUsername(String name);
}
