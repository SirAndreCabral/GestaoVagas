# Gestão de Vagas

Este é um projeto Java Spring Boot para gerenciar vagas de emprego, empresas e candidatos.

## Estrutura do projeto

- `src/main/java/com/example/demo/projeto/GestaoVagas`
  - `modules/company` - CRUD e autenticação para empresa
  - `modules/candidate` - CRUD e autenticação para candidato
  - `modules/jobs` - CRUD de vagas
  - `exception` - tratamentos de exceção e respostas padronizadas
  - `security` - configuração de segurança e filtros JWT
  - `providers` - provedor JWT para autenticação

## Requisitos

- Java 17 (ou versão compatível)
- Maven
- Banco de dados configurado em `application.properties` (H2, PostgreSQL, MySQL etc.)

## Como executar

1. `./mvnw clean install`
2. `./mvnw spring-boot:run`

## Endpoints principais

- `POST /company/auth/login` - login empresa
- `POST /candidate/auth/login` - login candidato
- `GET /jobs` - listar vagas
- `POST /jobs` - criar vaga (autenticado)

## Notas

- A aplicação utiliza JWT para autenticação.
- Exceções personalizadas estão em `GestaoVagas/exception`.

## Desenvolvimento

- Para testes: `./mvnw test`
- Para debug no IDE, execute a classe `DemoApplication` em `src/main/java/com/example/demo/projeto/DemoApplication.java`.
