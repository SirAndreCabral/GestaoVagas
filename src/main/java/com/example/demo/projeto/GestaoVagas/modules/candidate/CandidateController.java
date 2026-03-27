package com.example.demo.projeto.GestaoVagas.modules.candidate;

import com.example.demo.projeto.GestaoVagas.modules.candidate.useCase.CreateCandidateUseCase;
import com.example.demo.projeto.GestaoVagas.modules.candidate.useCase.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @PostMapping("/create-candidate")
    public @Valid ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.createCandidateUseCase.createUser(candidateEntity);

            return ResponseEntity.ok().body(result);

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/list-candidate")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> listCandidate(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");
        try {

            var profile = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));

            return ResponseEntity.ok().body(profile);

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
