package com.example.demo.projeto.GestaoVagas.modules.jobs;

import com.example.demo.projeto.GestaoVagas.modules.jobs.dto.CreateJobsDTO;
import com.example.demo.projeto.GestaoVagas.modules.jobs.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobsController {

    @Autowired
    CreateJobUseCase createJobUseCase;

    @PostMapping("create-job")
    @PreAuthorize("hasRole('COMPANY')")
    public @Valid ResponseEntity<Object> createJob(@Valid @RequestBody CreateJobsDTO createJobsDTO, HttpServletRequest request) {
        try {
            var companyId = request.getAttribute("company_id");

//            jobsEntity.setCompanyId(UUID.fromString(companyId.toString()));

            var jobsEntity = JobsEntity.builder()
                    .benefits(createJobsDTO.getBenefits())
                    .companyId(UUID.fromString(companyId.toString()))
                    .description(createJobsDTO.getDescription())
                    .level(createJobsDTO.getLevel())
                    .build();

            var jobCreated = this.createJobUseCase.executeCreateJob(jobsEntity);

            return ResponseEntity.ok().body(jobCreated);

        }catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
