package com.example.demo.projeto.GestaoVagas.modules.candidate.useCase;

import com.example.demo.projeto.GestaoVagas.modules.candidate.CandidateEntity;
import com.example.demo.projeto.GestaoVagas.modules.candidate.CandidateRepository;
import com.example.demo.projeto.GestaoVagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidato = this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });

        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .description(candidato.getDescription())
                .username(candidato.getUsername())
                .email(candidato.getEmail())
                .name(candidato.getName())
                .id(candidato.getId())
                .build();

        return candidateDTO;
    }
}
