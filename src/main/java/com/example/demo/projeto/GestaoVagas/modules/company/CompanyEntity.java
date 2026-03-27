package com.example.demo.projeto.GestaoVagas.modules.company;

import com.example.demo.projeto.GestaoVagas.modules.jobs.JobsEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String cnpj;

    @Pattern(regexp = "^(?!\\s*$).+", message = "O campo [username] não deve conter espaço")
    private String username;

    @Email(message = "O campo deve conter um e-mail válido")
    private String email;

    @Length(min = 5, max = 100, message = "A senha deve conter entre 5 e 100 caracteres")
    private String password;

    private String website;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
