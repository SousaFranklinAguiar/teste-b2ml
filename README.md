No postman, realize uma requisição POST através da seguinte URL: http://localhost:8090/api/parcelas

Insira um body no formato JSON (raw) assim como e exemplo a seguir:

{
    "product": {
        "id": 123,
        "name": "Nome do Produto",
        "price": 2000
    },
    "payment": {
        "entry": 1000,
        "installments": 10
    }
}
