# Java SpringBoot API REST - Notas
Prueba técnica para una vacante como Desarrollador Java

## Enunciado de la prueba técnica
En la raíz de este proyecto se encuentra un documento PDF llamado `Prueba tecnica Backend.pdf` el cual fue enviado por la empresa. En él se encuentra el enunciado de la prueba.

## Descripción

Se optó por usar Spring Boot con el objetivo de disminuir las tareas de configuración. Además se está usando Maven como herramienta para gestionar las dependencias y faicilitar la construcción del jar.

## Base de datos
El servicio está configurado para trabajar con una base de datos MySql v8. El servicio está hecho con JPA con implementación en Hibernate. El servicio esta configurado para trabajar con una bd en la nube de google, si se desea probar de manera local, es necesario que se cree una base de datos vacia con el nombre universidad en un servidor local y modificar en el archivo `pom.xml` la ruta de la bd. 

## Configuración de ambiente

Se debe clonar este repositorio, luego abrirlo en un IDE(Intellij idea, Eclipse, sts, etc). Luego, crear una base de datos vacia con el nombre `universidad` (solo en casos que se desea probar de manera local)

## Iniciar servicio rest

Buscar en el proyecto, el archivo:

`com.qds.backend.evaluacion.springrestqdsnotas.SpringRestQdsNotasApplication.java`

Dar click derecho encima de este y buscar la opción en el menú desplegable "Run". Despues, en la consola del IDE usted podra apreciar como un servidor embebido, propio de la tecnología Spring-boot, inicia. Hay que tener en cuenta, que el servidor utiliza por default el puerto 8080, sin embargo, para esta prueba se ha configurado el puerto 7890, si actualmente lo esta utilizando opte por dejarlo libre o considere cambiar el puerto en el siguiente archivo de propiedades:

`/src/main/resources/application.yml`

*Antes de usar los servicios, es neceario ejecutar el archivo `datos.sql` para que la base de datos tenga registros. Considerar que este archivo pobla todas las tablas de registros excepto la tabla de notas*

## Usar servicios
El servicio está implementado con spring security y jwt, por lo que para acceder a cualquier servicio es necesario autenticarse con un usuario y contraseña.

### Autenticación
#### Para autenticarse, usar la siguiente url:

`POST http://localhost:7890/autenticar`

Luego, enviar las credenciales como Basic Authentication.
Se pueden usar los siguientes usuarios:
* (admin) usuario: admin  contraseña: 12345
* (profesor) usuario: ggomez  contraseña: 12345
* (profesor) usuario: flopez  contraseña: 12345
* (alumno) usuario: rrodriguez  contraseña: 12345
* (alumno) usuario: jarevalo  contraseña: 12345
* (alumno) usuario: pvaldiviezo  contraseña: 12345
* (alumno) usuario: cramirez  contraseña: 12345

El valor obtenido será como el siguiente:

```json
{
  "jwtToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}
```
#### *Considerar que el token mostrado en el ejemplo no es válido. Para probar el servicio es necesario generar el token como se explicó anteriormente.*

### Servicio Notas

 #### Para utilizar el servicio de registrar notas, usar la siguiente url:

`POST http://localhost:7890/notas`

***Request***

```json
{
  "calificacion": 16,
  "idSeccion": 2,
  "idAlumno": 5,
  "idTipoEvaluacion" : 1
}
```

***Response***

```json
{
  "respuesta": {
    "curso": "Programacion Java",
    "alumno": "Carlos Ramirez",
    "ciclo": "2022-I",
    "tipoEvaluacion": "Práctica Calificada I",
    "calificacion": 16,
    "fechaRegistro": "2022-10-09T15:13:33.1439293"
  },
  "fecha": "2022-10-09 15:13:33",
  "procesado": true,
  "errores": []
}
```
#### *Considerar que solo los usuarios que son profesores pueden registrar notas. Es decir, solo se podrá registrar notas si previamente me he autenticado con usuario que es profesor. Usar un usuario de tipo profesor para probar este servicio. Además, es necesario que el profesor que va a registrar la nota sea el responsable de sección*


#### Para utilizar lista las notas, usar la siguiente url:

`GET http://localhost:7890/notas`

***Response***

```json
{
  "respuesta": [
    {
      "curso": "Programacion Java",
      "alumno": "Carlos Ramirez",
      "ciclo": "2022-I",
      "tipoEvaluacion": "Práctica Calificada I",
      "calificacion": 16,
      "fechaRegistro": "2022-10-09T15:13:33.143929"
    },
    {
      "curso": "Programacion Java",
      "alumno": "Carlos Ramirez",
      "ciclo": "2022-I",
      "tipoEvaluacion": "Práctica Calificada II",
      "calificacion": 12,
      "fechaRegistro": "2022-10-09T16:46:04.238747"
    }
  ],
  "fecha": "2022-10-09 16:54:50",
  "procesado": true,
  "errores": []
}
```
#### *Considerar que solo se mostrarán las notas del alumno autenticado. Para probar este servicio es necesario autenticarse con un usuario de tipo alumno*

### Otros Servicios
La prueba consistia solo en construir la autenticación y los servicios de notas(listar y registrar). Sin embargo, adicionalmente se han realizado otros servicios para poder accceder la información y asi poder registrar las notas.

Para conocer la documentación de los servicios, se ha implementado la herramienta swagger. Para ver la documentación ingresar a la siguiente url:

`http://localhost:7890/swagger-ui.html`

Además se está compartiendo también la colección de postman para las pruebas. Se encuentra en el archivo raiz con el nombre `Universidad.postman_collection.json`

### Host
Adicionalmente, el servicio está alojado en heroku, con base datos mysql en google cloud.

El servicio se encuentra en la siguiente url:

`https://spring-java-universidad-v1.herokuapp.com/`
