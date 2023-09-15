<h1 align="center">
 Teste tecnico tgid
</h1>

 ## 📃 Sobre
Este projeto foi desenvolvido como parte do um desafio técnico proposto pela empresa TGID. 

## 🚀 Tecnologias utilizadas
Este projeto foi desenvolvido com as seguintes tecnologias:

- [**JDK 17**](https://www.oracle.com/java/technologies/downloads/#java17): Java Development Kit 17 - um kit de desenvolvimento para construção de aplicações e componentes usando a linguagem de programação Java;

- [**Intellij Community edition**](https://www.jetbrains.com/idea/download/?section=windows): um ambiente de desenvolvimento;

- [**Postman**](https://www.postman.com/): uma ferramenta que tem como objetivo testar serviços RESTful (Web APIs) por meio do envio de requisições HTTP e da análise do seu retorno;

- [**Git**](https://git-scm.com/downloads): o sistema de controle de versão distribuído de código aberto mais utilizado;
  
- [**H2**](https://www.h2database.com/): um banco de dados em memória para testes de seed na aplicação.

- 
 ## 🔧 Instalação e execução

Para baixar o código-fonte do projeto em sua máquina, primeiramente terá que ter instalado o [**Git**](https://git-scm.com/).

Com o Git instalado, em seu terminal execute o seguinte comando:

```bash
$ git clone git@github.com:luanraffaell/teste-tecnico-tgid.git
```

### Backend

- Com o projeto baixado, abra-o em sua IDE.
- Aguarde enquanto o maven baixe as dependências do projeto.
- Execute o arquivo **`DesafioTecnicoTgidApplication.java`**.
- A aplicação Spring Boot será executada no endereço: _**`http://localhost:8080/`**_.

---
>📝: **Acesso a dados**:<br>
Durante o projeto foi utilizado o banco de dados em memória `H2` para acessa-lo basta digitar a url `http://localhost:8080/h2-console`
>
>![h2](https://github.com/luanraffaell/teste-tecnico-tgid/assets/87610833/b7c76c80-0c00-4a5b-9d6c-b38b4ca9ceca)

# REST API
## Criar cliente
### Request
`POST /cliente/cadastrar `
```JSON
{
    "nome":"Luan",
    "cpf":"25251454",
    "email":"luan@gmail.com"
}
```
### Response
```JSON
{
    "id": 1,
    "nome": "Luan",
    "email": "luan@gmail.com",
    "cpf": "25251454"
}
```
## Criar empresa
### Request
`POST /empresa/cadastrar `
```JSON
{
    "nome":"Tgid",
    "cnpj":"254554545",
    "saldo":10000,
    "email":"empresa@empresa.com"
}
```
### Response
```JSON
{
    "id": 1,
    "nome": "Saui",
    "cnpj": "254554545",
    "saldo": 10000,
    "email": "empresa@empresa"
}
```
## Criar transação saque
### Request
`POST /transacao/saque `

```JSON
{
    "clienteId":1,
    "empresaId":1,
    "valorTransacao":150
}
```
### Response
```JSON
{
    "id": 2,
    "clienteId": 1,
    "empresaId": 1,
    "valor": 140.0,
    "valorTaxa": 10.0,
    "tipoTransacao": "SAQUE",
    "timesTamp": "2023-09-14T21:11:14.7840861"
}
```
## Criar transação deposito
### Request
`POST /transacao/deposito `

```JSON
{
    "clienteId":1,
    "empresaId":1,
    "valorTransacao":150
}
```
### Response
```JSON
{
    "id": 1,
    "clienteId": 1,
    "empresaId": 1,
    "valor": 145.0,
    "valorTaxa": 5.0,
    "tipoTransacao": "DEPOSITO",
    "timesTamp": "2023-09-14T21:52:44.0072679"
}
```
## Buscar cliente por id
### Request
`GET /cliente/1 `
```JSON
{
    "id": 1,
    "nome": "Luan",
    "email": "luan@gmail.com",
    "cpf": "25251454"
}
```
