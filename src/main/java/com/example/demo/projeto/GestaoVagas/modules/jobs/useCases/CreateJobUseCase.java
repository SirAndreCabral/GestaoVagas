package com.example.demo.projeto.GestaoVagas.modules.jobs.useCases;

import com.example.demo.projeto.GestaoVagas.modules.jobs.JobsEntity;
import com.example.demo.projeto.GestaoVagas.modules.jobs.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    JobsRepository jobsRepository;

    public JobsEntity executeCreateJob(JobsEntity jobsEntity) {
        return this.jobsRepository.save(jobsEntity);
    }
}
