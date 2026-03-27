# Sucursales API

API REST reactiva para gestión de franquicias, sucursales y productos.

## Tecnologías

- Java 21 + Spring Boot 3.5
- Spring WebFlux (programación reactiva)
- MongoDB (base de datos)
- Docker + Docker Compose
- SpringDoc OpenAPI (Swagger UI)

## Requisitos previos

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado y corriendo

## Despliegue local

### 1. Clonar el repositorio

git clone https://github.com/CardaniH/sucursales-api.git
cd sucursales-api

### 2. Levantar con Docker Compose

docker-compose up --build

### 3. Acceder a la API

- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs:   http://localhost:8080/api-docs

## Endpoints disponibles

| Método | URL | Descripción |
|--------|-----|-------------|
| POST | /api/franchises | Crear franquicia |
| PATCH | /api/franchises/{id}/name | Actualizar nombre de franquicia |
| GET | /api/franchises/{id}/top-stock-products | Producto con más stock por sucursal |
| POST | /api/franchises/{franchiseId}/branches | Agregar sucursal |
| PATCH | /api/branches/{id}/name | Actualizar nombre de sucursal |
| POST | /api/branches/{branchId}/products | Agregar producto |
| DELETE | /api/products/{productId} | Eliminar producto |
| PATCH | /api/products/{productId}/stock | Modificar stock |
| PATCH | /api/products/{productId}/name | Actualizar nombre del producto |

## Ejemplo de uso

### Crear una franquicia
curl -X POST http://localhost:8080/api/franchises \
  -H "Content-Type: application/json" \
  -d '{"name": "Mi Franquicia"}'

### Agregar sucursal
curl -X POST http://localhost:8080/api/franchises/{franchiseId}/branches \
  -H "Content-Type: application/json" \
  -d '{"name": "Sucursal Norte"}'

### Agregar producto
curl -X POST http://localhost:8080/api/branches/{branchId}/products \
  -H "Content-Type: application/json" \
  -d '{"name": "Producto A", "stock": 100}'

### Ver producto con más stock por sucursal
curl http://localhost:8080/api/franchises/{franchiseId}/top-stock-products
