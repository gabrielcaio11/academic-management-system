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

## Serviços (docker-compose)
- `db` (PostgreSQL 16)
  - Porta host: `5433` → container `5432`
  - Banco: `db_academic_management_system`
  - Usuário: `postgres`
  - Senha: definida no compose
- `api` (Spring Boot)
  - Porta: `8080`
  - Variáveis de ambiente consumidas pelo Spring:
    - `DB_URL` (padrão do compose: `jdbc:postgresql://db:5432/db_academic_management_system`)
    - `DB_USERNAME` (padrão do compose: `postgres`)
    - `DB_PASSWORD`
- `frontend` (Nginx + build Angular)
  - Porta: `4200`
  - Proxy: `/api/*` → `http://api:8080/`

## Endpoints principais (API)
- `GET /alunos` → lista de alunos (JSON)
- `POST /alunos` → cria aluno
  - Body (JSON):
    ```json
    { "nome": "Ana", "email": "ana@example.com" }
    ```

Exemplos (PowerShell/curl):
```powershell
# Listar
curl.exe -s http://localhost:8080/alunos

# Criar
curl.exe -s -i -H "Content-Type: application/json" --data-raw "{\"nome\":\"Ana\",\"email\":\"ana@example.com\"}" http://localhost:8080/alunos
```

## Frontend
- Página inicial (`/`) exibe a lista de alunos e um formulário simples para criar novos registros.
- O formulário envia para `/api/alunos` (proxy do Nginx) e recarrega a lista após sucesso.

## Desenvolvimento local (sem Docker)
Opcional, caso prefira rodar separado.

- Backend
  1) Configurar PostgreSQL local e variáveis de ambiente:
     - `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`
  2) Rodar com Maven Wrapper:
     ```powershell
     cd backend
     .\mvnw.cmd spring-boot:run
     ```

- Frontend
  1) Requisitos: Node 20+ e npm
  2) Instalar e iniciar:
     ```powershell
     cd frontend
     npm ci
     npm start
     ```
     O `npm start` usa `proxy.conf.json` para encaminhar `/api` → `http://localhost:8080`.

## Dicas e solução de problemas
- Node para build do Angular: a imagem usa Node 20 (Angular 20 requer Node 20.19+). Se mudar a versão do Angular, alinhe o `frontend/Dockerfile`.
- Proxy do frontend (produção): configurado em `frontend/nginx.conf`.
- Se o frontend não listar dados, verifique no DevTools a chamada `GET /api/alunos` e os logs do container:
  ```powershell
  # Logs
  cd backend
  docker compose logs frontend --tail 100
  docker compose logs api --tail 100
  docker compose logs db --tail 100
  ```
- Banco acessível no host em `localhost:5433`. Exemplo para inserir manualmente:
  ```powershell
  cd backend
  docker compose exec -T db psql -U postgres -d db_academic_management_system -c "insert into tb_alunos (nome,email) values ('Ana','ana@example.com');"
  ```

## Licença
Este projeto é apenas para fins educacionais/demonstração.
