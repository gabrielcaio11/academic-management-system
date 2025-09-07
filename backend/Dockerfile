# Etapa 1: Build da aplicação
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /build

# Copia apenas o pom.xml e baixa dependências (melhora o cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o restante do código
COPY src ./src
# pode usar COPY . . e usar o .dockerignore para ignorar arquivos

# Faz o build da aplicação
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final para rodar a aplicação
FROM eclipse-temurin:21-jre AS runtime

# Define diretório de trabalho
WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=build /build/target/*.jar app.jar

# Reduz privilégios do usuário
RUN useradd -m myuser
USER myuser

# Expõe a porta 8080
EXPOSE 8080

# Usa ENTRYPOINT para melhor flexibilidade
ENTRYPOINT ["java", "-jar", "app.jar"]
