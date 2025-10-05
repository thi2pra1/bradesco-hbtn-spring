# Product Management API

This is a Spring MVC REST API for managing a product catalog with CRUD operations.

## How to Run

1. Navigate to the project directory:
```bash
cd F:\CURSO_JAVA_HOLBERTON\bradesco-hbtn-spring\mvc\1x01
```

2. Run the application:
```bash
mvn spring-boot:run
```

The application will start on http://localhost:8080

## API Endpoints

### 1. List All Products
**GET** `http://localhost:8080/api/produtos`

**Response:**
```json
[
  {
    "id": 1,
    "nome": "Celular",
    "preco": 1999.99
  }
]
```

### 2. Add New Product
**POST** `http://localhost:8080/api/produtos`

**Request Body:**
```json
{
  "nome": "Celular",
  "preco": 1999.99
}
```

**Response:** 201 Created
```json
{
  "id": 1,
  "nome": "Celular", 
  "preco": 1999.99
}
```

### 3. Update Product
**PUT** `http://localhost:8080/api/produtos/1`

**Request Body:**
```json
{
  "nome": "Celular Samsung",
  "preco": 2199.99
}
```

**Response:** 200 OK
```json
{
  "id": 1,
  "nome": "Celular Samsung",
  "preco": 2199.99
}
```

### 4. Delete Product
**DELETE** `http://localhost:8080/api/produtos/1`

**Response:** 200 OK
```
"Produto deletado com sucesso"
```

## Testing with cURL

```bash
# List products
curl -X GET http://localhost:8080/api/produtos

# Add product
curl -X POST http://localhost:8080/api/produtos \
  -H "Content-Type: application/json" \
  -d '{"nome": "Celular", "preco": 1999.99}'

# Update product
curl -X PUT http://localhost:8080/api/produtos/1 \
  -H "Content-Type: application/json" \
  -d '{"nome": "Celular Samsung", "preco": 2199.99}'

# Delete product
curl -X DELETE http://localhost:8080/api/produtos/1
```

## Project Structure
```
mvc/1x01/
├── pom.xml
├── src/main/java/com/example/demo/
│   ├── DemoApplication.java
│   ├── model/Produto.java
│   ├── service/ProdutoService.java
│   └── controller/ProdutoController.java
└── src/main/resources/
    └── application.properties
```
