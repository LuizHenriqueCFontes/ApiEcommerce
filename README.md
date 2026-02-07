# 📦 API de Usuários, Autenticação e Produtos

Essa API é construída com **Spring Boot** e **Spring Security**. Suporta **CRUD de usuários**, autenticação via **JWT Tokens** e gerenciamento de produtos (ainda a implementar).  

---

## 🔑 Base URL

http://localhost:8080


> Em produção, substitua `${BASE_URL}` pelo endereço do servidor.  

---

## 🏷 Autenticação

- A API usa **JWT Tokens** para autenticação.  
- Após login ou registro, você recebe um token que deve ser enviado no header de requisições protegidas:  

Authorization: Bearer ${TOKEN}


---

## 👤 Endpoints de Usuário

### 1. Listar usuários

GET /user?username=${username}


- **Descrição:** Lista todos os usuários. Pode filtrar por username.  
- **Parâmetros:**  

| Parâmetro | Tipo   | Obrigatório | Descrição           |
|-----------|-------|-------------|-------------------|
| username  | String | Não         | Filtra pelo nome  |

- **Resposta 200 OK:**

```json
[
  {
    "id": "1",
    "username": "usuario1",
    "email": "user1@email.com",
    "role": "USER"
  },
  {
    "id": "2",
    "username": "admin",
    "email": "admin@email.com",
    "role": "ADMIN"
  }
]
2. Atualizar usuário
PATCH /user/{id}
Descrição: Atualiza os dados do usuário.

Body:

{
  "username": "novoNome",
  "email": "novo@email.com"
}
Resposta 200 OK: Retorna os dados atualizados.

3. Deletar usuário
DELETE /user/{id}
Descrição: Remove um usuário pelo ID.

Resposta 204 No Content → Usuário deletado com sucesso.

4. Atualizar senha
PATCH /user/{id}/password
Descrição: Atualiza a senha de um usuário.

Body:

{
  "oldPassword": "senhaAntiga",
  "newPassword": "senhaNova"
}
Resposta 204 No Content → Senha atualizada com sucesso.

5. Atualizar Role
PATCH /user/{id}/role
Descrição: Atualiza a role (permissão) do usuário.

Body:

{
  "role": "ADMIN"
}
Resposta 204 No Content → Role atualizada com sucesso.

🔐 Endpoints de Autenticação
1. Login
POST /auth/login
Descrição: Faz login de um usuário existente.

Body:

{
  "username": "usuario1",
  "password": "senha123"
}
Resposta 200 OK:

{
  "token": "${JWT_TOKEN}",
  "username": "usuario1",
  "role": "USER"
}
2. Registro
POST /auth/register
Descrição: Registra um novo usuário.

Body:

{
  "username": "novoUsuario",
  "email": "email@teste.com",
  "password": "senha123"
}
Resposta 200 OK:

{
  "token": "${JWT_TOKEN}",
  "username": "novoUsuario",
  "role": "USER"
}
📦 Próximos passos: Produtos
Endpoints para gerenciar produtos (ainda a implementar):

Método	Endpoint	Descrição
GET	/product	Lista produtos
POST	/product	Cria novo produto
PATCH	/product/{id}	Atualiza produto
DELETE	/product/{id}	Deleta produto
Todos os endpoints de produtos serão protegidos por roles, por exemplo:

Apenas ADMIN pode criar ou deletar produtos.

⚡ Observações
Todos os endpoints protegidos precisam enviar JWT Token no header:

Authorization: Bearer ${TOKEN}
Roles possíveis: USER, ADMIN

DTOs usados: LoginDTO, RegisterDTO, UpdateDTO, UpdatePasswordDTO, UpdateRoleDTO, AuthResponseDTO, ListDTO

🔧 Tecnologias
Java 21

Spring Boot 3+

Spring Security

Spring Data JPA / Hibernate

MySQL

## 🚀 Como Rodar a API

Siga esses passos para executar a API localmente:

---

### 1️⃣ Pré-requisitos

- **Java 17+** ou superior instalado  
- **Maven 3+** instalado  
- Banco de dados **MySQL** (ou H2 para testes em memória)  
- Editor ou IDE (Eclipse, IntelliJ, VS Code, etc.)

---

### 2️⃣ Configurar o Banco de Dados

1. Crie um banco de dados no MySQL:

```sql
CREATE DATABASE nome_do_banco;
Configure o application.properties ou application.yml:

spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Se quiser usar H2 apenas para testes, adicione:

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
3️⃣ Clonar o Projeto
git clone https://github.com/SEU_USUARIO/SEU_PROJETO.git
cd SEU_PROJETO
4️⃣ Build e Execução
Com Maven, você pode rodar direto:

mvn clean install
mvn spring-boot:run
Ou gerar o .jar e executar:

mvn clean package
java -jar target/seu-projeto-0.0.1-SNAPSHOT.jar
5️⃣ Testar a API
Acesse os endpoints no Postman ou Insomnia:

Base URL: http://localhost:8080
Primeiro registre um usuário:

POST /auth/register
Depois faça login para obter o JWT Token:

POST /auth/login
Use o token para acessar os endpoints protegidos:

Authorization: Bearer ${TOKEN}
6️⃣ Acesso ao H2 (opcional)
Se estiver usando H2 em memória, acesse o console:

http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb

User: sa

Password: (vazio)
