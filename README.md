# Academic Management System

Aplicação full stack para gestão acadêmica (Java + Spring Boot no backend e Angular no frontend), com PostgreSQL e orquestração via Docker.

## Visão geral
- Backend: Spring Boot 3, Java 21, JPA/Hibernate, PostgreSQL
- Frontend: Angular 20 (standalone), Nginx para produção
- Infra: Docker e Docker Compose (um arquivo único em `backend/docker-compose.yml`)

## Estrutura do projeto
```
academic-management-system/
  backend/               # API Spring Boot e compose principal
  frontend/              # Aplicação Angular (build para Nginx)
```

## Pré-requisitos
- Docker Desktop atualizado (com suporte a `docker compose`)

## Como executar com Docker
1) Abra um terminal na pasta `backend`:
```powershell
cd backend
```
2) Suba a stack (construção de imagens inclusa):
```powershell
docker compose up -d --build
```
3) Acesse:
- API: `http://localhost:8080/alunos`
- Frontend: `http://localhost:4200`

O frontend faz proxy de `/api` para o backend. Ex.: `http://localhost:4200/api/alunos`.

### Variáveis de ambiente (.env)
- Crie um arquivo `.env` dentro de `backend/` com as chaves abaixo (exemplos):
```
# Ports
API_PORT=8080
FRONTEND_PORT=4200
DB_HOST_PORT=5433

# Database
DB_HOST=db
DB_PORT=5432
DB_NAME=db_academic_management_system
DB_USERNAME=postgres
DB_PASSWORD=troque-esta-senha
POSTGRES_DB=db_academic_management_system
POSTGRES_USER=postgres
POSTGRES_PASSWORD=troque-esta-senha

# Spring
SPRING_SHOW_SQL=false
SPRING_DDL_AUTO=update
SPRING_FORMAT_SQL=true
SERVER_PORT=8080
```
- O arquivo `.env` já está ignorado pelo Git (não comitar senhas).

## Licença
Este projeto é apenas para fins educacionais/demonstração.
