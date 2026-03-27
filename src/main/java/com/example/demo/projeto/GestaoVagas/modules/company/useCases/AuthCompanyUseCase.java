package com.example.demo.projeto.GestaoVagas.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.projeto.GestaoVagas.exception.UsernameOrPasswordException;
import com.example.demo.projeto.GestaoVagas.modules.company.CompanyRepository;
import com.example.demo.projeto.GestaoVagas.modules.company.auth.dto.AuthCompanyDTO;
import com.example.demo.projeto.GestaoVagas.modules.company.auth.dto.AuthCompanyResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretyKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO executeAuthCompany(AuthCompanyDTO authCompanyDTO) throws UsernameOrPasswordException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
                .orElseThrow(() -> new UsernameOrPasswordException());

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if(!passwordMatches) {
            throw new UsernameOrPasswordException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretyKey);

        var experiesIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("javagas")
                .withExpiresAt(experiesIn)
                .withSubject(company.getId().toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
                .access_token_company(token)
                .expires_in(experiesIn.toEpochMilli())
                .build();

        return authCompanyResponseDTO;
    }
}
