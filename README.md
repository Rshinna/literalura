# 📚 Literalura

Um projeto Java com Spring Boot que consome dados da [API Gutendex](https://gutendex.com) para buscar livros, registrar autores e livros no banco de dados, e permitir diversas consultas por meio de um menu interativo no terminal.

## 🚀 Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Postgres (banco de dados)
- Jackson (para desserialização de JSON)
- API Gutendex

## 🧠 Funcionalidades principais

- 🔎 Buscar livros pelo título e registrar os dados.
- 📖 Listar livros e autores registrados.
- 👴 Filtrar autores que estavam vivos em determinado ano.
- 🌐 Listar livros por idioma.


## ▶️ Como executar o projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Rshinna/literalura.git
   cd literalura
   ```
2. **Abra no IntelliJ, VSCode ou IDE da sua preferência.**

3. **Execute o projeto:**

**Com Maven:**
```
bash
./mvnw spring-boot:run
```
Ou rode a classe `Principal` diretamente na IDE.

4. **Interaja pelo terminal:**

O programa exibe um menu como este:

```
1- Buscar livros pelo título  
2- Listar livros registrados  
3- Listar autores registrados  
4- Listar autores vivos em um determinado ano  
5- Listar livros em um determinado idioma 
0- Sair
```

**Escolha a opção desejada e siga as instruções exibidas.**

## 💡 Observações
- Os dados são salvos no banco de dados Postgres.

- A API Gutendex fornece dados públicos sobre livros de domínio público.

- Para buscas, digite palavras-chave do título em português ou inglês (ex: Pride and Prejudice).

## ✍️ Autor
### Desenvolvido por Rodrigo Franco como parte dos estudos do desafio Alura turma G8ONE.
