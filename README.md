# Seller Department API

API REST desenvolvida em **Spring Boot** para gerenciamento de departamentos e vendedores, com autenticação e autorização baseadas em **JWT** e controle de acesso por perfil de usuário (**RBAC**).

**API em produção:** [https://seller-department-api.onrender.com](https://seller-department-api.onrender.com)

>  A aplicação está hospedada no plano gratuito do Render. Após períodos de inatividade, a primeira requisição pode levar de 30 a 50 segundos para responder enquanto o serviço "acorda".

---

## Tecnologias utilizadas

- **Java 17**
- **Spring Boot 3.2.5**
  - Spring Web
  - Spring Data JPA
  - Spring Security
- **PostgreSQL**
- **JWT (JJWT)** — geração e validação de tokens
- **BCrypt** — hash de senhas
- **Docker** e **Docker Compose**
- **Maven**
- Deploy: **Render** (Web Service + PostgreSQL gerenciado)

---

##  Funcionalidades

- Cadastro e autenticação de usuários com JWT
- Controle de acesso baseado em papéis (`USER`, `ADMIN`)
- CRUD de **Departamentos**
- CRUD de **Vendedores** (associados a um departamento)
- Tratamento centralizado de exceções
- Validação de dados de entrada (Bean Validation)
- Containerização completa com Docker

---

##  Arquitetura

O projeto segue uma arquitetura em camadas, separando claramente as responsabilidades:
