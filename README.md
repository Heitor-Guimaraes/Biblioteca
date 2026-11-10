# Biblioteca API

API REST para controle de usuários, carteiras de biblioteca e empréstimos, desenvolvida em Java e Spring Boot.

## Tecnologias

Java 25 · Spring Boot 4 (Web MVC, Validation) · Spring Data JPA / Hibernate · MySQL · Lombok · Maven

## Arquitetura

Arquitetura em camadas: Controller → Service → Repository → MySQL, com DTOs para entrada e saída de dados.

## Endpoints

| Recurso | Método | Rota |
|---|---|---|
| Usuário | GET · POST | /usuarios |
| Usuário | GET · PUT · DELETE | /usuarios/{id} |
| Carteira | GET | /carteiras |
| Carteira | GET · PUT · DELETE | /carteiras/{id} |
| Carteira | POST | /carteiras/usuario/{usuarioId} |
| Empréstimo | GET | /emprestimos |
| Empréstimo | GET · PUT · DELETE | /emprestimos/{id} |
| Empréstimo | GET · POST | /emprestimos/usuario/{usuarioId} |

## Como executar

Pré-requisitos: Java 25, Maven e MySQL.

Crie o banco emprestimos no MySQL, ajuste as credenciais em src/main/resources/application.properties e execute mvn spring-boot:run. A API sobe em http://localhost:8080.

## Autor

Heitor Guimarães Perciliano · [LinkedIn](https://www.linkedin.com/in/heitor-perciliano/)
