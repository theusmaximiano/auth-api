# 🔐 API de Autenticação com JWT - Spring Boot

Este projeto é uma API de autenticação utilizando Spring Boot, Spring Security e JWT (JSON Web Token). Ele permite o registro de usuários e login seguro com geração de token JWT, utilizado para acessar os demais endpoints protegidos.

---

## 🚀 Como Rodar o Projeto

1. **Pré-requisitos**:
    - Java 17+
    - Maven
    - MySQL (configurar com usuario e senha)

2. **Configuração do Banco** (caso ainda não exista):

```sql
CREATE DATABASE autenticacao_db;
```

3. **Rodar a aplicação**:

```bash
./mvnw spring-boot:run
```
Ou via IntelliJ (Run > Run Application)

---

## 🔑 Endpoints Disponíveis

### 🔓 Abertos (não exigem token)

| Método | Endpoint             | Descrição              |
|--------|----------------------|------------------------|
| POST   | `/api/auth/register` | Registro de novo usuário |
| POST   | `/api/auth/login`    | Login e geração do token JWT |

### 🔒 Protegidos (exigem token JWT)

| Método | Endpoint             | Descrição                        |
|--------|----------------------|----------------------------------|
| GET    | `/api/usuarios`      | Lista todos os usuários (exemplo) |
| Qualquer outro `/api/**`      | Necessita autenticação via JWT  |

---

## 🛡️ Como Gerar e Usar o Token JWT

### 1. Registrar um novo usuário

**POST** `/api/auth/register`

```json
{
  "email": "matheus@email.com",
  "senha": "123456"
}
```

### 2. Fazer login

**POST** `/api/auth/login`

```json
{
  "email": "matheus@email.com",
  "senha": "123456"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

## ✅ Como usar o token JWT

### Usando no Postman

1. Copie o token retornado no login.
2. Em qualquer requisição protegida:
    - Vá até a aba **Authorization**
    - Tipo: `Bearer Token`
    - Cole o token no campo

### Ou via Header manual:

```http
Authorization: Bearer SEU_TOKEN_AQUI
```

---

## 🧪 Exemplo com Postman

**GET** `/api/usuarios`

- Headers:
```http
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---
## 📚 Tecnologias Usadas

- Java 17
- Spring Boot
- Spring Security
- JWT (jjwt)
- MySQL
- Maven

---

## 👨‍💻 Autor

- Matheus – `.com`