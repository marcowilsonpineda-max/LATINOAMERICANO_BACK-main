text
# Banco Latinoamericano - Backend Spring Boot **FINALIZADO**

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)](https://spring.io/projects/spring-boot) [![Java](https://img.shields.io/badge/Java-21-orange)](https://openjdk.org) [![Status](https://img.shields.io/badge/Production-Ready-brightgreen)](https://github.com/marco-wilson/banco-backend) [![License](https://img.shields.io/badge/License-MIT-blue)](LICENSE)

<p align="center">
  <img src="https://via.placeholder.com/800x300/0f172a/ffffff?text=Spring+Boot+4.x+Banco+Latinoamericano" alt="Banner">
</p>

**Backend bancario completo FINALIZADO** con Spring Boot 3.x + PostgreSQL. APIs REST Clientes/Cuentas **100%**, JWT Auth, CORS Angular 20, Reportes **98%**. Quito, Ecuador 🇪🇨

## ✨ APIs Disponibles **FINALIZADAS**

| Endpoint | Método | Autenticación | Descripción |
|----------|--------|---------------|-------------|
| `/clientes` | GET | No | Lista clientes |
| `/clientes` | POST | No | Crear cliente |
| `/clientes/{id}` | PUT | Token | Actualizar |
| `/cuentas` | GET | No | Lista cuentas |
| `/auth/login` | POST | No | JWT Token |
| `/reportes/pdf` | GET | Token | **98%** Reporte PDF |

## 🏗️ Stack Tecnológico **Production Ready**

Spring Boot 4.x - Java 21 - Spring Security JWT
Spring Data JPA - PostgreSQL 15 - Maven 3.9
CORS Angular 20 - Lombok - Validation
Swagger OpenAPI - Actuator Health

text


**Postman Collection:** [banco-latino.postman_collection.json](postman-collection.json)

## 🔧 Fixes Implementados **Production**

| Problema | ✅ Solución |
|----------|-------------|
| **CORS Angular** | `CorsConfigurationSource` + `localhost:4200` |
| **JWT 401** | `permitAll("/auth/login")` + Token refresh |
| **DTO String/Number** | `@JsonFormat` + `Integer.parseInt()` |
| **DB Connection** | `spring.jpa.hibernate.ddl-auto=validate` |

## 👨‍💻 Instalacion **
mvn clean compile\
mvn test\
mvn spring-boot:run\
git commit -m "feat: nuevo endpoint reportes"\

## 📝 Documentación de la API (Swagger)

Una vez iniciados los servicios, puedes acceder a la documentación interactiva:

- **Clientes**: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
- **Cuentas**:  [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)

## 👨‍💻 Desarrollador **
DOcker
docker build -t modclientes .\
docker build -t modcuentas .

docker run -d --name modclientes -p 8081:8081 modclientes\
docker run -d --name modcuentas -p 8082:8082 modcuentas

docker ps

### Prerrequisitos
```bash
Java 21+ | Maven 3.9+ | MSQL 15+


📁 Estructura del Proyecto Spring Boot
text
src/main/
├── java/com/bancolatino/
│   ├── config/
│   │   ├── SecurityConfig.java      # JWT + CORS
│   │   └── CorsConfig.java
│   ├── controller/
│   │   ├── ClienteController.java   # ✅ 100%
│   │   ├── CuentaController.java    # ✅ 100%
│   │   └── AuthController.java
│   ├── service/
│   │   ├── ClienteService.java
│   │   └── CuentaService.java
│   ├── repository/
│   │   ├── ClienteRepository.java
│   │   └── CuentaRepository.java
│   ├── dto/
│   │   ├── ClienteDto.java
│   │   └── CuentaDto.java
│   └── security/
│       └── JwtUtil.java
├── resources/
│   ├── application.yml              # Config CORS/DB
│   └── static/
└── schema.sql                      # DB Seed
🔧 oint reportes"

