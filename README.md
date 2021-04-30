
Projeto criado para testar as novas funcionalidades do Java (Text Blocks, Records etc).

# Dependências:
- Spring Boot 2.4.2: Spring Boot Web, Spring Boot Data JPA, Spring Boot Dev Tools, Spring Boot Test, Spring Boot Validation
- H2 Database: Banco em memória para testes
- MapStruct: Para converter classes DTO (Json) em classes de domínio (Entity)
- MySQL Connector 8.0
- Docker

# Deploy
Para iniciar a aplicação, basta acessar o diretório do projeto e rodar o comando "docker-compose up" para baixar a imagem do banco MySQL e iniciar o container.
