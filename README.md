# Propuesta BCI Ejercicio 1
- Resolucion del ejercicio propuesto por Banco BCI. [Ver PDF](https://github.com/LChris94/ejercicio1bci/blob/main/PropuestasEjercicio1-BCI.pdf "Ver PDF")

## Tecnologias utilizadas: :trophy:
- Lenguaje Programacion: JAVA 8
- Framework: Spring Boot 2.4.5
- Herramienta de Construccion: Gradle
- Base de Datos: H2 ( en memoria)
- Persistencia de Datos: Hibernate + JPA
- Pruebas Unitarias: JUnit 5

## Herramientas utilizadas:  :wrench:
- IDE: Eclipse 2021-03
- Diagramas: StarUML
- Repositorio: Github
- Test API: Postman

## Instrucciones :page_with_curl:
La forma correcta para iniciar este proyecto es:

    Importar el actual repositorio con el IDE de su preferencia.
    Para iniciar la aplicación ejecuté 'gradlew bootRun'

## Aclaración Importante! :warning:

Las pruebas unitarias fueron realizadas con JUnit5 por falta de tiempo/experiencia con el framework Spock, a futuro se modificaran y seran resueltas con dicho framework.

------------

## API endpoints

| Metodo HTTP  | URL  | Parametros  | Descripción  | Link  |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| GET  | /user/  |   | Trae todos los usuarios del sistema. | [Ver](http://aaa "Ver")  |
| GET  | /user/  |  id:numerico | Trae un usuario especifico del sistema.  | [Ver](http://aaa "Ver")  |
| POST  | /user/  |   | Crea un usuario en el sistema.  | [Ver](http://aaa "Ver")  |
| PUT  | /user/  | id:numerico  | Modifica un usuario del sistema. | [Ver](http://aaa "Ver")  |
| DELETE  | /user/  | id:numerico  | Elimina un usuario del sistema.  | [Ver](http://aaa "Ver")  |

------------

## Detalle:

### GET /user/
Trae todos los usuarios de sistema.

**Requisitos**
- Header
	    Authorization: Bearer xxxx.yyyy.xxx [TOKEN USUARIO ACCESO API]

**Parametros**

| Nombre  | Requerido  | Tipo  | Descripción  |
| ------------ | ------------ | ------------ | ------------ |
|   |   |   |   | |   

**Response**
```
[
    {
        "id": 1,
        "created": "2021-05-21T17:55:01.514",
        "modified": null,
        "last_login": "2021-05-21T17:55:01.514",
        "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKdWFuIFJvZHJpZ3VleiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MjE2MzA1MDEsImV4cCI6MTYyMTYzMTEwMX0.TCDmVAUbKuyBU5z9SaYvMr-EmLThZUB1Va2B0GQ7IzYpCwiEsO1K8QATX1VHbVz8tVDxSycZLpvZ5ilHHEkgUQ",
        "isActive": true
    },
    {
        "id": 2,
        "created": "2021-05-21T17:55:04.646",
        "modified": null,
        "last_login": "2021-05-21T17:55:04.646",
        "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKdWFuIFJvZHJpZ3VleiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MjE2MzA1MDQsImV4cCI6MTYyMTYzMTEwNH0.Fr647JGe8ifFnnicuFbVZR2p45-toHCWQYHypH2nTAxvgk1cE8W-7n7jF07KqJOYoMKbSDsCFzJhTu4W2WYKLw",
        "isActive": true
    }
]
```


------------


### GET /user/{idUser}
Trae un usuario especifico del sistema

**Requisitos**
- Header
	    Authorization: Bearer xxxx.yyyy.xxx [TOKEN USUARIO ACCESO API]

**Parametros**

| Nombre  | Requerido  | Tipo  | Descripción  |
| ------------ | ------------ | ------------ | ------------ |
| idUser  | requerido  | numerico  |  Representa el campo 'id' del usuario | |   

**Response**
```
 {
        "id": 1,
        "created": "2021-05-21T17:55:01.514",
        "modified": null,
        "last_login": "2021-05-21T17:55:01.514",
        "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKdWFuIFJvZHJpZ3VleiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MjE2MzA1MDEsImV4cCI6MTYyMTYzMTEwMX0.TCDmVAUbKuyBU5z9SaYvMr-EmLThZUB1Va2B0GQ7IzYpCwiEsO1K8QATX1VHbVz8tVDxSycZLpvZ5ilHHEkgUQ",
        "isActive": true
  }
```

------------


### POST /user/
Crea un usuario en el sistema

**Requisitos**
Ninguno

**Parametros**

| Nombre  | Requerido  | Tipo  | Descripción  |
| ------------ | ------------ | ------------ | ------------ |
|   |   |   |   | |   

**Body**
```
{
    "name": "xxxxx",
    "email": "xxxx@xxxxxx.cl",
    "password": "Xxxxxx00",
    "phones": [
        {
        "number": "00000000",
        "citycode": "0",
        "contrycode": "00"
        }
    ]
}
```

**Response**
```
 {
    "id": 1,
    "created": "2021-05-21T19:26:40.123",
    "modified": null,
    "last_login": "2021-05-21T19:26:40.123",
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKdWFuIFJvZHJpZ3VleiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MjE2MzU5OTksImV4cCI6MTYyMTYzNjU5OX0.U2rFiTkjVAqcHiUBO7Ic0lBzPGXhDrXbKUw5WkVIYNtGht2Ny4PAtZfByjNtybYrQgbqAQzk59mfWnM8-mJBzA",
    "isActive": true
}
```


------------


### PUT /user/{idUser}
Modifica un usuario en el sistema

**Requisitos**
- Header
	    Authorization: Bearer xxxx.yyyy.xxx [TOKEN USUARIO ACCESO API]

**Parametros**

| Nombre  | Requerido  | Tipo  | Descripción  |
| ------------ | ------------ | ------------ | ------------ |
| idUser  | requerido  | numerico  |  Representa el campo 'id' del usuario | |   

**Body**
```
{
    "name": "xxxxx",
    "email": "xxxx@xxxxxx.cl",
    "password": "Xxxxxx00",
    "phones": [
        {
        "number": "00000000",
        "citycode": "0",
        "contrycode": "00"
        }
    ]
}
```

**Response**
```
// Email Invalido
{
    "mensaje":"Expresion de email invalida. (aaaaaaa@dominio.cl)"
}

or

// Email en uso
{
    "mensaje":"El correo ya registrado"
}

or

// Password Invalido
{
    "mensaje":"Expresion de password invalida. (Una Mayúscula, letras minúsculas, y dos números)"
}

or

// Exito
{
    "id": 1,
    "created": "2021-05-21T19:26:40.123",
    "modified": "2021-05-21T19:33:22.036",
    "last_login": "2021-05-21T19:26:40.123",
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKdWFuIFJvZHJpZ3VleiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2MjE2MzU5OTksImV4cCI6MTYyMTYzNjU5OX0.U2rFiTkjVAqcHiUBO7Ic0lBzPGXhDrXbKUw5WkVIYNtGht2Ny4PAtZfByjNtybYrQgbqAQzk59mfWnM8-mJBzA",
    "isActive": true
}
```

------------


### DELETE /user/{idUser}
Elimina un usuario en el sistema

**Requisitos**
- Header
	    Authorization: Bearer xxxx.yyyy.xxx [TOKEN USUARIO ACCESO API]

**Parametros**

| Nombre  | Requerido  | Tipo  | Descripción  |
| ------------ | ------------ | ------------ | ------------ |
| idUser  | requerido  | numerico  |  Representa el campo 'id' del usuario | |   



**Response**
```
// Exito
Status Code 200. Ok
```


------------

## Diagramas
### Diagrama de Componentes
[![DiagComp](https://raw.githubusercontent.com/LChris94/ejercicio1bci/main/diagrams/DiagramComponents.png "DiagComp")](https://raw.githubusercontent.com/LChris94/ejercicio1bci/main/diagrams/DiagramComponents.png "DiagComp")

### Diagrama de Secuencia
[![DiagSec](https://raw.githubusercontent.com/LChris94/ejercicio1bci/main/diagrams/DiagramSecuence.png "DiagSec")](https://raw.githubusercontent.com/LChris94/ejercicio1bci/main/diagrams/DiagramSecuence.png "DiagSec")