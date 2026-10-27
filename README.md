# 🌱 Crisalis-Back

Backend de **Crisalis** desarrollado con Java + Spring Boot.
Este repositorio contiene **solo el backend** y funciona de forma independiente, para ser consumido por uno o varios repositorios **Crisalis-Front** externos.

---

## ✨ Objetivo del proyecto

Proveer una API REST con lógica de negocio, persistencia y autenticación para el ecosistema Crisalis, manteniendo separado el backend del frontend.

---

## 🧰 Stack real del repositorio

- **Java 11**
- **Spring Boot 2.7.6**
- Spring Web
- Spring Data JPA / JDBC
- Spring Security
- JWT (`jjwt`)
- Microsoft SQL Server
- Maven Wrapper (`mvnw`, `mvnw.cmd`)

> No se han añadido tecnologías nuevas ni migraciones de versión mayor en esta actualización.

---

## 🗂️ Estructura principal

```text
src/main/java/com/example/crisalisbackend/
├── controller/   # Controladores REST
├── service/      # Lógica de negocio
├── repository/   # Acceso a datos
├── model/        # Entidades
├── Security/     # Seguridad y JWT
└── Dto/          # DTOs

src/main/resources/
├── application.properties
└── application-example.properties
```

---

## ✅ Requisitos

- JDK 11
- SQL Server accesible (por defecto, `localhost:1433`)
- Permisos para crear/actualizar esquema en la base de datos usada

---

## 🔐 Configuración segura (sin secretos hardcodeados)

`application.properties` usa variables de entorno con valores por defecto de desarrollo local.

Variables soportadas:

- `DB_HOST` (default: `localhost`)
- `DB_PORT` (default: `1433`)
- `DB_NAME` (default: `crisalis`)
- `DB_USERNAME` (default: `sa`)
- `DB_PWD` (default: `change_me`)
- `DB_ENCRYPT` (default: `true`)
- `DB_TRUST_SERVER_CERTIFICATE` (default: `true`)
- `JWT_SECRET` (default: `change_me`)
- `JWT_EXPIRATION` (default: `10000`)

Referencia: `src/main/resources/application-example.properties`.

> Recomendación: definir `DB_PWD` y `JWT_SECRET` en tu entorno local antes de ejecutar.

---

## ▶️ Ejecución

### Linux / macOS

```bash
./mvnw spring-boot:run
```

### Windows (PowerShell / CMD)

```bat
mvnw.cmd spring-boot:run
```

---

## 🧪 Comandos útiles (Maven Wrapper)

### Linux / macOS

```bash
./mvnw -DskipTests compile
./mvnw -DskipTests verify
./mvnw test
./mvnw verify
```

### Windows (PowerShell / CMD)

```bat
mvnw.cmd -DskipTests compile
mvnw.cmd -DskipTests verify
mvnw.cmd test
mvnw.cmd verify
```

---

## ⚠️ Limitaciones actuales

- Las pruebas de contexto (`contextLoads`) requieren conexión real a SQL Server.
- En entornos sin SQL Server disponible, `./mvnw test` y `./mvnw verify` pueden fallar por conexión a base de datos.

---

## 🔗 Relación con Crisalis-Front

Este repositorio se mantiene **separado** del frontend.

Para levantar el sistema completo:

1. Clona este backend.
2. Clona el/los repositorios `Crisalis-Front` por separado.
3. Levanta SQL Server y configura variables de entorno del backend.
4. Inicia este backend.
5. Inicia el frontend desde su propio repositorio.

Así cada proyecto mantiene su ciclo de vida y despliegue de forma independiente. 🚀
