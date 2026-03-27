package com.example.demo.projeto.GestaoVagas.modules.jobs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobsRepository extends JpaRepository<JobsEntity, UUID> {

}
