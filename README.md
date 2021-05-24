# Catálogo de produtos

URL base dos serviços: http://localhost:9999

Documentação da API: http://localhost:9999/swagger-ui/

## Projeto productms-h2

CRUD de produtos usando o banco de dados em memória H2

## Projeto productms-mongodb

CRUD de produtos usando MongoDB

Requisitos para execução do projeto:

```bash
# Instalar o MongoDB v4.4.6
https://www.mongodb.com/try/download/community
```

## Projeto productms-rabbitmq

CRUD de produtos usando o banco de dados em memória H2 e o message broker RabbitMQ

As funções de Producer e Consumer são executadas pelo mesmo projeto, para não ser necessária a execução de dois projetos separados.

Sempre que uma requisição da API é processada sem erros, uma mensagem é enviada do Producer ao Consumer, que irá exibi-la no terminal.

Requisitos para execução do projeto:
```bash
# Instalar o Docker v20.10.6
https://www.docker.com/products/docker-desktop

# Executar o container RabbitMQ
docker run -d --hostname localhost --name local-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
```
A interface de gerenciamento RabbitMQ será acessível em:

URL: http://localhost:15672

Usuário: guest

Senha: guest
