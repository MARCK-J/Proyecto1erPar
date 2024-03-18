# SIS213
Repositorio de ejemplo o para practicas

# 1erParcial Ingenieria de Software

# Proyecto de gestión de tareas

Este proyecto es una aplicación de gestión de tareas que permite a los usuarios crear, actualizar y eliminar tareas, y asociarlas con etiquetas. La aplicación está escrita en Java y utiliza Maven como herramienta de gestión de dependencias.

# BACKEND

## Requisitos

Para ejecutar la aplicación, necesitarás tener instalado lo siguiente:

- Java 8 o superior
- Maven 3.6.0 o superior

## Instalación

1. Clona el repositorio de GitHub:

   ```
   git clone https://github.com/tu-usuario/proyecto-gestion-tareas.git
   ```

2. Navega hasta el directorio del proyecto:

   ```
   cd proyecto-gestion-tareas
   ```

3. Compila el proyecto con Maven:

   ```
   mvn clean install
   ```

4. Ejecuta la aplicación:

   ```
   java -jar target/proyecto-gestion-tareas-1.0.0.jar
   ```

## Uso

La aplicación expone una API REST que permite realizar las siguientes operaciones:

- Crear una etiqueta
- Obtener todas las etiquetas
- Crear una tarea
- Obtener todas las tareas
- Actualizar una tarea
- Eliminar una tarea

Para utilizar la API, puedes enviar solicitudes HTTP utilizando una herramienta como `curl` o `Postman`. Aquí te dejo algunos ejemplos de cómo utilizar la API:

### Crear una etiqueta

```
POST http://localhost:9999/api/v1/label/Create
Content-Type: application/json
Accept: application/json

{
    "nombre": "Etiqueta 1"
}
```

### Obtener todas las etiquetas

```
GET http://localhost:9999/api/v1/label/GetAll
Accept: application/json
```

### Crear una tarea

```
POST http://localhost:9999/api/v1/task/Create
Content-Type: application/json
Accept: application/json

{
    "etiquetaId": 1,
    "titulo": "Tarea 1",
    "descripcion": "Descripción de la tarea 1",
    "estado": "Pendiente",
    "fechaLimite": "2022-01-01"
}
```

### Obtener todas las tareas

```
GET http://localhost:9999/api/v1/task/GetAll
Accept: application/json
```

### Actualizar una tarea

```
PUT http://localhost:9999/api/v1/task/Update/1
Content-Type: application/json
Accept: application/json

{
    "etiquetaId": 2,
    "titulo": "Tarea 1 actualizada",
    "descripcion": "Descripción de la tarea 1 actualizada",
    "estado": "En progreso",
    "fechaLimite": "2022-02-01"
}
```

### Eliminar una tarea

```
DELETE http://localhost:9999/api/v1/task/Delete/1
Content-Type: application/json
Accept: application/json
```

## Contribución

Si deseas contribuir a este proyecto, puedes hacer lo siguiente:

1. Haz un fork del repositorio.
2. Crea una rama para tu nueva función o corrección de errores: `git checkout -b nueva-funcion`.
3. Realiza tus cambios y haz commit de los mismos: `git commit -am 'Agrega nueva función'`.
4. Haz push de la rama a tu fork: `git push origin nueva-funcion`.
5. Crea un pull request en el repositorio original.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para obtener más información.

# FRONTEND

This project is a Vue.js application. The main component is `App.vue`.

## Project Structure

The project is structured as follows:

- `main.js`: This is the entry point of the application. It imports the necessary modules, sets up the Vue router, and mounts the Vue application.
- `App.vue`: This is the main Vue component. It serves as the root component where other components are injected depending on the routing.
- `components/`: This directory contains all the Vue components used in the application. Each component is in its own `.vue` file.
  - `LoginComponent.vue`: This component is used for user login.
  - `HomeComponent.vue`: This component is the home page of the application.
  - `LabelComponent.vue`: This component is used for managing labels.
  - `TaskComponent.vue`: This component is used for managing tasks.
  - `TaskComplete.vue`: This component is used for displaying completed tasks.
  - `TaskPending.vue`: This component is used for displaying pending tasks.
  - `TaskProcess.vue`: This component is used for displaying tasks in process.

## Project setup

To set up the project, you will need to have Node.js and npm installed. Then, you can install the project dependencies with `npm install`.

To start the development server, use `npm run serve`.

## Building for production

To create a production build, use `npm run build`. This will create a `dist` directory with the production-ready files.

## Testing

To run the unit tests, use `npm run test:unit`.

## Linting

To lint and fix files, use `npm run lint`.
