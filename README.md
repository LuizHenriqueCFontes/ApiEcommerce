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
