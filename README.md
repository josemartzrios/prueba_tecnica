API de Gestión de Productos y Usuarios - Prueba Técnica Afore Coppel
📋 Descripción
API REST para la gestión de productos y usuarios con sistema de autenticación JWT y auditoría de cambios. Soporta dos roles principales:

Administrador: Permisos completos de escritura y consulta de auditoría
Usuarios Anónimos: Acceso de solo lectura a productos

🗂️ Estructura del Proyecto
/
├── diseño/
│   ├── entidad-relacion.txt 
│   └── swagger.yaml
└── [otros archivos del proyecto]
En la carpeta diseño/ encontrarás:

Diagrama Entidad-Relación: Modelo de datos de la base de datos
Swagger: Especificación completa de los endpoints de la API (OpenAPI 3.0.3)

🐳 Base de Datos
La base de datos PostgreSQL se levanta mediante Docker y Docker Compose, facilitando el despliegue y la portabilidad del entorno de desarrollo.
🚀 Ejecución Local
La API se ejecuta en:
http://localhost:8080

🔑 Autenticación
Login
POST /api/auth/login
Obtén un token JWT para acceder a endpoints protegidos.
Request:
json{
  "email": "admin@market.com",
  "password": "secret123"
}
Response:
json{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "role": "ADMIN"
}
📦 Endpoints - Productos
Listar todos los productos
GET /api/products

✅ Acceso público (sin autenticación)

Obtener producto por SKU
GET /api/products/{sku}

✅ Acceso público

Crear producto
POST /api/products

🔒 Requiere rol ADMIN
Header: Authorization: Bearer {token}

Request:
json{
  "sku": "ELEC-001",
  "name": "Laptop Gamer X1",
  "price": 1299.99,
  "brand": "TechBrand",
  "description": "Laptop de alto rendimiento con 16GB RAM"
}
Actualizar producto
PUT /api/products/{sku}

🔒 Requiere rol ADMIN
Genera registro automático en auditoría

Eliminar producto
DELETE /api/products/{sku}

🔒 Requiere rol ADMIN
Response: 204 No Content

👥 Endpoints - Usuarios
Listar usuarios
GET /api/users

🔒 Requiere rol ADMIN

Obtener usuario por ID
GET /api/users/{id}

🔒 Requiere rol ADMIN

Eliminar usuario
DELETE /api/users/{id}

🔒 Requiere rol ADMIN
Response: 204 No Content

📊 Endpoints - Auditoría
Ver logs de productos
GET /api/audit/product-logs

🔒 Requiere rol ADMIN
Muestra historial de cambios en productos (CREATE, UPDATE, DELETE)

Response:
json[
  {
    "logId": 1,
    "productSku": "ELEC-001",
    "adminUser": "admin@market.com",
    "action": "UPDATE",
    "changes": "precio de 100 a 150",
    "timestamp": "2024-12-02T10:30:00Z"
  }
]
```

## 🔐 Seguridad

La API utiliza **JWT (JSON Web Tokens)** con el esquema Bearer para la autenticación. Para endpoints protegidos, incluye el header:
```
Authorization: Bearer {tu_token_jwt}
📝 Roles y Permisos
RolPermisosADMINCrear, actualizar, eliminar productos; Gestionar usuarios; Ver auditoríaUSER/AnónimoSolo lectura de productos
🛠️ Tecnologías

PostgreSQL (Base de datos)
Docker & Docker Compose (Contenedores)
JWT (Autenticación)
OpenAPI 3.0.3 (Documentación)

📄 Códigos de Respuesta

200 - OK
201 - Created
204 - No Content
401 - Unauthorized (Credenciales inválidas)
403 - Forbidden (Sin permisos)
404 - Not Found


Versión: 1.0.0
Desarrollado para: Prueba Técnica Afore Coppel
