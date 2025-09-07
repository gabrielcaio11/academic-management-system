# Projeto Backend - Spring Boot

Este é um projeto backend desenvolvido em Java utilizando o framework Spring Boot e gerenciado pelo Maven.

## Tecnologias Utilizadas

- Java 21  
- Spring Boot
- Spring Data JPA  
- PostgreSQL
- Spring Security

## Como executar o projeto

1. **Clone o repositório:**

```
git clone https://github.com/gabrielcaio11/academic-management-system.git
```

2. **Acesse a pasta do projeto:**

```
cd academic-management-system
```

3. **Configure o arquivo `application.yml` com as informações do seu banco de dados.**

4. **Execute o projeto:** (É necessário ter o Docker instalado e rodando)

```
docker compose up --build
 ```

## Endpoints

Acesse a documentação da API em:  
`http://localhost:8080/swagger-ui.html`

## Estrutura do Projeto

- `src/main/java` - Código fonte da aplicação
- `src/main/resources` - Arquivos de configuração
- `pom.xml` - Gerenciamento de dependências Maven

## Contribuição

Sinta-se à vontade para abrir issues e pull requests.

## Licença

Este projeto está sob a licença MIT.

