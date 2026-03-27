package com.example.demo.projeto.GestaoVagas.modules.candidate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandidateEntity> findCandidateEntityByUsernameOrEmail(String username, String email);

    Optional<CandidateEntity> findByUsername(String username);

    UUID id(UUID id);
}
