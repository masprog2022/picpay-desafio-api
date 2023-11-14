<h1 align="left">
  PicPay
</h1>

API que simula uma transação entre usuários que faz parte [desse desafio](https://github.com/PicPay/picpay-desafio-backend) para pessoas desenvolvedoras backend.

## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [Base de dados H2](https://www.h2database.com/html/main.html)


## Práticas adotadas

- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3

### Swagger Documentation

![Swagger3](https://github.com/masprog2022/api-desafio-todo-list/assets/89852935/83d8ef6b-51c8-4e4c-be74-3a4dd342ace3)

### Como Executar

- Clonar repositório git
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/desafio-picpay-api-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [httpie](https://httpie.io):

- Criar Usuário
```
$ http POST :8080/users fullName="Vanilda Manuel" cpf="896.371.240-04" email=vanilda.manuel@unitel.co.ao userType="LOJISTA" balance=500

[
 {
    "id": 1,
    "fullName": "Vanilda Manuel",
    "cpf": "896.371.240-04",
    "email": "vanilda.manuel@unitel.co.ao",
    "userType": "LOJISTA",
    "balance": 500
  }
]
```

- Transação
```
$ http GET :8080/transactions value=100 payer=3 payee=1

{
  "id": 1,
  "payer": {
    "id": 3,
    "fullName": "Mauro Manuel",
    "cpf": "896.371.240-05",
    "email": "mauro.manuel@unitel.co.ao",
    "userType": "COMUN",
    "balance": 900
  },
  "payee": {
    "id": 1,
    "fullName": "Vanilda Manuel",
    "cpf": "896.371.240-04",
    "email": "vanilda.manuel@unitel.co.ao",
    "userType": "LOJISTA",
    "balance": 600
  },
  "amount": 100,
  "timestemp": "2023-11-14T22:38:29.9272596"
}
```


