# Gestão de Vagas - API de Gerenciamento de Vagas de Emprego

## 📋 Descrição do Projeto

**Gestão de Vagas** é uma aplicação Spring Boot desenvolvida em Java que gerencia vagas de emprego e conecta candidatos com empresas. O sistema permite que:

- **Candidatos** se registrem, autentiquem e visualizem seu perfil
- **Empresas** se registrem, autentiquem e criem vagas de emprego
- **Vagas** sejam gerenciadas e listadas

A aplicação implementa autenticação via **JWT (JSON Web Tokens)** e utiliza **Spring Security** para controle de acesso baseado em roles.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| **Java** | 17 | Linguagem de programação |
| **Spring Boot** | 4.0.2 | Framework web e IoC |
| **Spring Security** | Incluso | Autenticação e autorização |
| **Spring Data JPA** | Incluso | Persistência de dados |
| **PostgreSQL** | Latest | Banco de dados relacional |
| **JWT (java-jwt)** | 4.4.0 | Autenticação via tokens |
| **Lombok** | Incluso | Redução de boilerplate |
| **Validation API** | Incluso | Validação de dados |

---

## 📁 Estrutura do Projeto

```
GestaoVagas/
├── src/main/java/com/example/demo/projeto/
│   ├── DemoApplication.java              # Classe main da aplicação
│   └── GestaoVagas/
│       ├── exception/                    # Manipuladores de exceções personalizadas
│       │   ├── CompanyFoundException.java
│       │   ├── CompanyNotFoundException.java
│       │   ├── UserFoundException.java
│       │   ├── UsernameOrPasswordException.java
│       │   ├── ErrorMessageDTO.java
│       │   └── ExceptionHandlerError.java
│       ├── modules/
│       │   ├── candidate/               # Módulo de Candidato
│       │   │   ├── CandidateController.java
│       │   │   ├── CandidateEntity.java
│       │   │   ├── CandidateRepository.java
│       │   │   ├── auth/               # Autenticação de Candidato
│       │   │   │   ├── AuthCandidateController.java
│       │   │   │   └── dto/
│       │   │   ├── dto/                # DTOs de Candidato
│       │   │   │   └── ProfileCandidateResponseDTO.java
│       │   │   └── useCase/            # Use Cases de Candidato
│       │   │       ├── AuthCandidateUseCase.java
│       │   │       ├── CreateCandidateUseCase.java
│       │   │       └── ProfileCandidateUseCase.java
│       │   ├── company/                # Módulo de Empresa
│       │   │   ├── CompanyController.java
│       │   │   ├── CompanyEntity.java
│       │   │   ├── CompanyRepository.java
│       │   │   ├── auth/              # Autenticação de Empresa
│       │   │   └── useCases/          # Use Cases de Empresa
│       │   └── jobs/                  # Módulo de Vagas
│       │       ├── JobsController.java
│       │       ├── JobsEntity.java
│       │       ├── JobsRepository.java
│       │       ├── dto/               # DTOs de Vagas
│       │       └── useCases/          # Use Cases de Vagas
│       ├── providers/                 # Provedores de serviços
│       │   ├── JWTCandidateProvider.java
│       │   └── JWTProvider.java
│       └── security/                  # Configuração de segurança
│           ├── SecurityConfig.java
│           ├── SecurityFilter.java
│           └── SecurityCandidateFilter.java
└── src/main/resources/
    └── application.properties         # Configurações da aplicação
```

---

## 🚀 Como Executar

### Pré-requisitos
- **Java 17** instalado
- **Maven** instalado
- **PostgreSQL** instalado e em execução
- **Docker** (opcional, para banco de dados)

### Configuração do Banco de Dados

#### Opção 1: PostgreSQL Local
Crie um banco de dados PostgreSQL com os seguintes parâmetros:

```sql
CREATE DATABASE gestao_vagas;
```

Verifique as credenciais no arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gestao_vagas
spring.datasource.username={{USERNAME}}
spring.datasource.password={{PASSWORD}}
```

#### Opção 2: Docker Compose
Use o arquivo `docker-compose.yml` incluído:

```bash
docker-compose up -d
```

### Executar a Aplicação

```bash
./mvnw spring-boot:run
```

A aplicação iniciará em: `http://localhost:8080`

---

## 🔐 Autenticação e Segurança

### JWT (JSON Web Tokens)

A aplicação utiliza **JWT** para autenticação e autorização. Cada requisição protegida deve incluir o token no header:

```
Authorization: Bearer <seu_token_jwt>
```

### Chaves Secretas
Configuradas em `src/main/resources/application.properties`:

```properties
security.token.secret = JAVAGAS_@123#
security.token.secret.candidate = JAVAGAS_@123#CANDIDATE
```

### Roles (Papéis)
- **CANDIDATE** - Candidato
- **COMPANY** - Empresa

---

## 📚 Endpoints da API

### 🔗 Autenticação

#### Autenticar Candidato
```http
POST /auth/candidate
Content-Type: application/json

{
  "username": "candidato_usuario",
  "password": "senha123"
}
```

**Response (200):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

### 👤 Candidato (`/candidate`)

#### Criar Candidato
```http
POST /candidate/create-candidate
Content-Type: application/json

{
  "name": "João Silva",
  "username": "joao_silva",
  "email": "joao@email.com",
  "password": "senha_segura123",
  "description": "Desenvolvedor experiente",
  "curriculum": "https://exemplo.com/curriculum.pdf"
}
```

**Response (200):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "João Silva",
  "username": "joao_silva",
  "email": "joao@email.com",
  "description": "Desenvolvedor experiente",
  "curriculum": "https://exemplo.com/curriculum.pdf",
  "createdAt": "2024-03-27T10:30:00"
}
```

#### Listar Perfil do Candidato
```http
GET /candidate/list-candidate
Authorization: Bearer <token_candidato>
```

**Response (200):** Retorna o perfil completo do candidato autenticado

---

### 🏢 Empresa (`/company`)

#### Criar Empresa
```http
POST /company/create-company
Content-Type: application/json

{
  "name": "Tech Solutions",
  "cnpj": "12.345.678/0001-90",
  "username": "tech_solutions",
  "email": "contato@techsolutions.com",
  "password": "senha_segura123",
  "website": "https://techsolutions.com",
  "description": "Empresa de tecnologia focada em inovação"
}
```

**Response (200):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440001",
  "name": "Tech Solutions",
  "cnpj": "12.345.678/0001-90",
  "username": "tech_solutions",
  "email": "contato@techsolutions.com",
  "website": "https://techsolutions.com",
  "description": "Empresa de tecnologia focada em inovação",
  "createdAt": "2024-03-27T10:30:00"
}
```

#### Listar Todas as Empresas
```http
GET /company/find-all-companies
Authorization: Bearer <token_empresa>
```

**Response (200):** Array de empresas registradas

#### Buscar Empresa por ID
```http
GET /company/find-one-company/{uuid}
```

**Response (200):** Dados da empresa específica

---

### 💼 Vagas de Emprego (`/jobs`)

#### Criar Vaga
```http
POST /jobs/create-job
Authorization: Bearer <token_empresa>
Content-Type: application/json

{
  "description": "Desenvolvedor Java Senior",
  "level": "Senior",
  "benefits": "Vale refeição, Vale transporte, Plano de saúde"
}
```

**Response (200):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440002",
  "description": "Desenvolvedor Java Senior",
  "level": "Senior",
  "benefits": "Vale refeição, Vale transporte, Plano de saúde",
  "companyId": "550e8400-e29b-41d4-a716-446655440001",
  "createdAt": "2024-03-27T10:30:00"
}
```

---

## 📋 Modelos de Dados

### CandidateEntity
```json
{
  "id": "UUID",
  "name": "String",
  "username": "String (único, sem espaços)",
  "email": "String (email válido)",
  "password": "String (5-100 caracteres)",
  "description": "String",
  "curriculum": "String",
  "createdAt": "LocalDateTime"
}
```

### CompanyEntity
```json
{
  "id": "UUID",
  "name": "String",
  "cnpj": "String",
  "username": "String (único, sem espaços)",
  "email": "String (email válido)",
  "password": "String (5-100 caracteres)",
  "website": "String",
  "description": "String",
  "createdAt": "LocalDateTime"
}
```

### JobsEntity
```json
{
  "id": "UUID",
  "description": "String",
  "level": "String (obrigatório)",
  "benefits": "String",
  "companyId": "UUID (obrigatório)",
  "companyEntity": "CompanyEntity (referência)",
  "createdAt": "LocalDateTime"
}
```

---

## ✅ Validações

### Candidato
- **username**: Não pode estar em branco ou conter espaços
- **email**: Deve ser um email válido
- **password**: Entre 5 e 100 caracteres

### Empresa
- **username**: Não pode estar em branco ou conter espaços
- **email**: Deve ser um email válido
- **password**: Entre 5 e 100 caracteres

### Vaga
- **level**: Campo obrigatório
- **companyId**: Obrigatório (referência à empresa)

---

## ⚠️ Tratamento de Erros

A aplicação utiliza tratamento centralizado de exceções. As exceções customizadas incluem:

- **CompanyFoundException** - Empresa já existe
- **CompanyNotFoundException** - Empresa não encontrada
- **UserFoundException** - Usuário já existe
- **UsernameOrPasswordException** - Username ou senha incorretos

**Exemplo de resposta de erro (400):**
```json
[
  {
    "message": "O campo [username] não deve conter espaço",
    "field": "username"
  },
  {
    "message": "O campo deve conter um e-mail válido",
    "field": "email"
  }
]
```

---

## 🔄 Fluxos Principais

### Fluxo de Registro e Autenticação - Candidato
1. `POST /candidate/create-candidate` - Criar nova conta
2. `POST /auth/candidate` - Fazer login e obter JWT token
3. `GET /candidate/list-candidate` - Acessar perfil protegido

### Fluxo de Registro e Autenticação - Empresa
1. `POST /company/create-company` - Criar nova empresa
2. `POST /auth/company` - Fazer login e obter JWT token
3. `GET /company/find-all-companies` - Listar empresas
4. `POST /jobs/create-job` - Criar vaga de emprego

---

## 📦 Dependências Principais

Para mais detalhes, consulte o arquivo `pom.xml`:

```xml
<!-- Spring Boot Security -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<!-- Spring Data JPA -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- PostgreSQL Driver -->
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>

<!-- JWT (Java JWT) -->
<dependency>
  <groupId>com.auth0</groupId>
  <artifactId>java-jwt</artifactId>
  <version>4.4.0</version>
</dependency>

<!-- Lombok -->
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <optional>true</optional>
</dependency>

<!-- Validation -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

---

## 🐛 Troubleshooting

### Erro de Conexão com PostgreSQL
Verifique se:
- PostgreSQL está rodando
- As credenciais em `application.properties` estão corretas
- O banco `gestao_vagas` foi criado

### Token Inválido ou Expirado
- Gere um novo token autenticando-se novamente
- Verifique se o token está sendo enviado corretamente no header `Authorization: Bearer <token>`

### Erro 401 (Não Autorizado)
- Você não possui as permissões necessárias (role)
- Verifique se o endpoint requer `@PreAuthorize`

### Erro 400 (Bad Request) em Validação
- Verifique se todos os campos obrigatórios foram preenchidos
- Valide o formato dos dados sendo enviados (email, username, etc.)

---

## 📚 Referências Adicionais

- [Documentação Spring Boot 4.0.2](https://docs.spring.io/spring-boot/docs/)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/)
- [Spring Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/)
- [JWT.io - JSON Web Tokens](https://jwt.io)
- [PostgreSQL Documentation](https://www.postgresql.org/)
- [Maven Documentation](https://maven.apache.org/guides/)

---

## 👨‍💻 Desenvolvimento

Este projeto foi desenvolvido como parte do programa de aprendizado da **RocketSeat**, seguindo as melhores práticas de desenvolvimento com Spring Boot e Clean Architecture com uso de Use Cases.

