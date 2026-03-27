package com.example.demo.projeto.GestaoVagas.modules.candidate.useCase;

import com.example.demo.projeto.GestaoVagas.exception.UserFoundException;
import com.example.demo.projeto.GestaoVagas.modules.candidate.CandidateEntity;
import com.example.demo.projeto.GestaoVagas.modules.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity createUser(CandidateEntity candidateEntity) {
        this.candidateRepository
                .findCandidateEntityByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);

        return this.candidateRepository.save(candidateEntity);
    }
}
