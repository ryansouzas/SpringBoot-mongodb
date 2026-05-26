# 🍃 Workshop Spring Boot + MongoDB

API RESTful desenvolvida com **Spring Boot 3** e **MongoDB**, explorando os conceitos de banco de dados orientado a documentos integrado a uma aplicação Java moderna.

---

## ✨ Funcionalidades

- 👤 CRUD completo de **Usuários** (`/users`)
- 📝 Consulta de **Posts** por usuário (`/users/{id}/posts`)
- 🔍 Busca de posts por **título** (`/posts/titlesearch`)
- 🗓️ Busca de posts por **data e texto** (`/posts/fullsearch`)
- 📦 Uso de **DTOs** para transferência de dados
- 🔗 Referências e documentos **embutidos** (embedded documents)
- ⚠️ Tratamento de exceções com respostas HTTP adequadas

---

## 🛠️ Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 21 |
| Spring Boot | 3.5.x |
| Spring Data MongoDB | — |
| Spring Web | — |
| Maven | Wrapper incluso |
| MongoDB | 6+ |

---

## 📐 Modelo de Domínio

```
User                          Post
─────────────────             ──────────────────────
id: String                    id: String
name: String                  date: Date
email: String                 title: String
posts: List<Post>  ──────►   body: String
                              author: AuthorDTO
                              comments: List<CommentDTO>
```

**Relacionamentos:**
- Um `User` possui uma lista de `Post` (referência)
- Cada `Post` contém o autor como **documento embutido** (`AuthorDTO`)
- Cada `Post` contém uma lista de comentários embutidos (`CommentDTO`)

---

## 🚀 Como Executar

### Pré-requisitos

- [Java JDK 21+](https://www.oracle.com/java/technologies/downloads/)
- [MongoDB](https://www.mongodb.com/try/download/community) instalado e rodando na porta padrão `27017`
- [Maven](https://maven.apache.org/) (ou use o wrapper `./mvnw` incluso no projeto)

### Clonando o repositório

```bash
git clone https://github.com/ryansouzas/SpringBoot-mongodb.git
cd SpringBoot-mongodb
```

### Configurando o MongoDB

Certifique-se de que o MongoDB está rodando localmente. O projeto se conecta por padrão em:

```
mongodb://localhost:27017/workshopMongo
```

Caso queira alterar, edite o arquivo `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/workshopMongo
```

### Executando a aplicação

```bash
# Com o Maven Wrapper (Linux/macOS)
./mvnw spring-boot:run

# Com o Maven Wrapper (Windows)
mvnw.cmd spring-boot:run

# Ou gerando o JAR e executando
./mvnw package
java -jar target/workshopMongo-0.0.1-SNAPSHOT.jar
```

A aplicação estará disponível em: `http://localhost:8080`

---

## 📡 Endpoints da API

### Usuários — `/users`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/users` | Lista todos os usuários |
| `GET` | `/users/{id}` | Busca usuário por ID |
| `POST` | `/users` | Cadastra novo usuário |
| `PUT` | `/users/{id}` | Atualiza um usuário |
| `DELETE` | `/users/{id}` | Remove um usuário |
| `GET` | `/users/{id}/posts` | Lista posts de um usuário |

### Posts — `/posts`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/posts/{id}` | Busca post por ID |
| `GET` | `/posts/titlesearch?text=` | Busca posts por título |
| `GET` | `/posts/fullsearch?text=&minDate=&maxDate=` | Busca por texto e intervalo de datas |

### Exemplos de requisição

**POST** `/users`
```json
{
  "name": "Maria Brown",
  "email": "maria@email.com"
}
```

**GET** `/posts/fullsearch?text=enjoy&minDate=2018-03-01&maxDate=2018-03-31`

---

## 🗂️ Estrutura do Projeto

```
src/main/java/com/project/workshopMongo/
├── config/
│   └── Instantiation.java          # Seeding do banco com dados iniciais
├── domain/
│   ├── Post.java                   # Documento Post
│   └── User.java                   # Documento User
├── dto/
│   ├── AuthorDTO.java              # DTO do autor embutido no Post
│   ├── CommentDTO.java             # DTO de comentário embutido
│   └── UserDTO.java                # DTO de transferência do User
├── repository/
│   ├── PostRepository.java         # Repositório MongoDB de Post
│   └── UserRepository.java         # Repositório MongoDB de User
├── resources/
│   ├── PostResource.java           # Controller REST de Post
│   ├── UserResource.java           # Controller REST de User
│   └── exception/
│       ├── ResourceExceptionHandler.java
│       └── StandardError.java
├── services/
│   ├── PostService.java            # Regras de negócio de Post
│   ├── UserService.java            # Regras de negócio de User
│   └── exception/
│       └── ObjectNotFoundException.java
└── WorkshopMongoApplication.java   # Classe principal
```

---

## 🏗️ Arquitetura

O projeto segue o padrão em camadas:

```
Controller (Resource)  →  Service  →  Repository  →  MongoDB
         ↕
        DTO
```

- **Resource** — recebe as requisições HTTP e retorna respostas
- **Service** — contém as regras de negócio e validações
- **Repository** — abstração de acesso ao MongoDB via Spring Data
- **DTO** — objetos de transferência para desacoplar o domínio da API
- **Domain** — documentos MongoDB mapeados com `@Document`

---

## 📄 Licença

Este projeto está sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👤 Autor

Feito por **Ryan Souza**

[![GitHub](https://img.shields.io/badge/GitHub-ryansouzas-181717?style=flat&logo=github)](https://github.com/ryansouzas)
