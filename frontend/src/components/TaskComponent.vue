<template>
    <div>
      <!-- Barra de navegación -->
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
          <router-link to="/" class="navbar-brand">Inicio</router-link>
          <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <router-link to="/tareas" class="nav-link">Tareas</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/etiquetas" class="nav-link">Etiquetas</router-link>
              </li>
            </ul>
            <div class="navbar-text">
              <!-- Imagen del usuario -->
              <img src="@/assets/UserIcon.png" alt="Usuario" class="user-icon" />
              <!-- Botones de Login y Sign In -->
              <button class="btn btn-primary mr-2" @click="redirectToLogin">Login</button>
              <button class="btn btn-secondary" @click="redirectToSignIn">Sign In</button>
            </div>
          </div>
        </div>
      </nav>
  
      <!-- Contenido de la página de tareas -->
      <div class="container mt-5">
        <h2 class="text-center mb-4">Tareas</h2>
        <table class="table">
          <thead>
            <tr>
              <th>Etiqueta ID</th>
              <th>Título</th>
              <th>Descripción</th>
              <th>Estado</th>
              <th>Fecha Creación</th>
              <th>Fecha Modificación</th>
              <th>Fecha Límite</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="task in tasks" :key="task.id">
              <td>{{ task.etiquetaId }}</td>
              <td>{{ task.titulo }}</td>
              <td>{{ task.descripcion }}</td>
              <td>{{ task.estado }}</td>
              <td>{{ task.fechaCreacion }}</td>
              <td>{{ task.fechaModificacion }}</td>
              <td>{{ task.fechaLimite }}</td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- Botón para mostrar tareas completadas -->
      <div class="text-center mt-4">
        <button @click="showCompletedTasks" class="btn btn-info">Mostrar Tareas Completadas</button>
        </div>

      <div class="container mt-5">
        <h2 class="text-center mb-4">Tareas</h2>
        <table class="table" v-if="showingCompletedTasks">
            <!-- Cabecera de la tabla -->
            <thead>
            <tr>
                <th>Etiqueta ID</th>
                <th>Título</th>
                <th>Descripción</th>
                <th>Estado</th>
                <th>Fecha Creación</th>
                <th>Fecha Modificación</th>
                <th>Fecha Límite</th>
            </tr>
            </thead>
            <!-- Cuerpo de la tabla -->
            <tbody>
            <!-- Iteración sobre las tareas Completadas -->
            <tr v-for="task in completedTasks" :key="task.id">
                <td>{{ task.etiquetaId }}</td>
                <td>{{ task.titulo }}</td>
                <td>{{ task.descripcion }}</td>
                <td>{{ task.estado }}</td>
                <td>{{ task.fechaCreacion }}</td>
                <td>{{ task.fechaModificacion }}</td>
                <td>{{ task.fechaLimite }}</td>
            </tr>
            </tbody>
        </table>
        </div>
        <div class="container mt-5">
            <h2 class="text-center mb-4" style="color: #007bff;">Crear Nueva Tarea</h2>

            <div class="input-group mb-3">
                <!-- Input para el título de la tarea -->
                <input v-model="nuevaTarea.titulo" type="text" class="form-control" placeholder="Título de la tarea">
                <!-- Input para la descripción de la tarea -->
                <input v-model="nuevaTarea.descripcion" type="text" class="form-control" placeholder="Descripción">
                <!-- Input para el estado de la tarea -->
                <input v-model="nuevaTarea.estado" type="text" class="form-control" placeholder="Estado">
                <!-- Input para la fecha límite de la tarea -->
                <input v-model="nuevaTarea.fechaLimite" type="datetime-local" class="form-control">
            <!-- Select para la etiqueta de la tarea -->
                <select v-model="selectedEtiqueta" class="form-select">
                    <option value="" disabled>Seleccionar etiqueta</option>
                    <option v-for="etiqueta in etiquetas" :key="etiqueta.id" :value="etiqueta.id">{{ etiqueta.name }}</option>
                </select>
                <button @click="crearTarea" class="btn btn-primary">Crear</button>
            </div>
        </div> 
    </div>
</template>
  
<script>
  import axios from 'axios';
  
  export default {
    data() {
        return {
        tasks: [], // Aquí se almacenarán las tareas obtenidas de la API
        nuevaTarea: {
            titulo: '',
            descripcion: '',
            estado: '',
            fechaLimite: '',
        },
        completedTasks: [], // Aquí se almacenarán las tareas completadas
        showingCompletedTasks: false,
        etiquetas: [], // Aquí se almacenarán las etiquetas obtenidas de la API
        selectedEtiqueta: null, // ID de la etiqueta seleccionada
        };
    },
    created() {
        const userId = sessionStorage.getItem('userId');
        if (userId) {
        this.getLabelsByUserId(userId);
        this.getTasksByEtiquetaId();
        } else {
        console.error('ID de usuario no válido.');
        }
    },
    methods: {
      redirectToLogin() {
        this.$router.push('/login');
      },
      redirectToSignIn() {
        // Agrega lógica para redirigir a la ruta de registro (si es necesario)
      },
      async getTasksByEtiquetaId() {
        try {
          const userId = sessionStorage.getItem('userId');
          if (userId) {
            const response = await axios.get(`http://localhost:9999/api/v1/label/User/${userId}`);
            const etiquetas = response.data.result;
            for (const etiqueta of etiquetas) {
              const tasksResponse = await axios.get(`http://localhost:9999/api/v1/task/etiqueta/${etiqueta.id}`);
              // Filtrar solo las tareas con estado "Pending"
              const pendingTasks = tasksResponse.data.result.filter(task => task.estado === 'Pending');
              this.tasks.push(...pendingTasks);
            }
            console.log('Tareas cargadas exitosamente:', this.tasks);
          } else {
            console.error('ID de usuario no válido.');
          }
        } catch (error) {
          console.error('Error al cargar las tareas:', error);
        }
      },

      async getLabelsByUserId(userId) {
        try {
            const response = await axios.get(`http://localhost:9999/api/v1/label/User/${userId}`);
            this.etiquetas = response.data.result; // Acceder a la propiedad `result` en la respuesta
            console.log('Etiquetas cargadas exitosamente:', this.etiquetas);
        } catch (error) {
            console.error('Error al cargar las etiquetas:', error);
        }
    },

      async showCompletedTasks() {
        if (this.showingCompletedTasks) {
            // Si se está mostrando las tareas completadas y se presiona el botón nuevamente,
            // limpiamos el array y restablecemos el indicador a false
            this.completedTasks = [];
            this.showingCompletedTasks = false;
        } else {
            // Si no se están mostrando las tareas completadas, las cargamos
            try {
            const userId = sessionStorage.getItem('userId');
            if (userId) {
                const response = await axios.get(`http://localhost:9999/api/v1/label/User/${userId}`);
                const etiquetas = response.data.result;
                for (const etiqueta of etiquetas) {
                const tasksResponse = await axios.get(`http://localhost:9999/api/v1/task/etiqueta/${etiqueta.id}`);
                // Filtrar solo las tareas con estado "Completed"
                const completedTasks = tasksResponse.data.result.filter(task => task.estado === 'Completed');
                this.completedTasks.push(...completedTasks);
                }
                console.log('Tareas completadas cargadas exitosamente:', this.completedTasks);
                this.showingCompletedTasks = true; // Establecemos el indicador a true para mostrar las tareas completadas
            } else {
                console.error('ID de usuario no válido.');
            }
            } catch (error) {
            console.error('Error al cargar las tareas completadas:', error);
            }
        }
    },

    async crearTarea() {
      // Verificamos si se ha seleccionado una etiqueta
      if (!this.selectedEtiqueta) {
        console.error('Debe seleccionar una etiqueta para la tarea.');
        return;
      }

      // Creamos la tarea con la etiqueta seleccionada
      const nuevaTareaConEtiqueta = { ...this.nuevaTarea, etiquetaId: this.selectedEtiqueta };

      // Enviamos la solicitud para crear la tarea
      try {
        const response = await axios.post(`http://localhost:9999/api/v1/task/Label/${this.selectedEtiqueta}/Create`, nuevaTareaConEtiqueta);
        const createdTask = response.data.result;
        console.log('Tarea creada exitosamente:', createdTask);
        // Limpiamos el formulario después de crear la tarea
        this.getTasksByEtiquetaId();

        this.nuevaTarea = {
          titulo: '',
          descripcion: '',
          estado: '',
          fechaLimite: '',
        };
        // Podemos agregar lógica adicional aquí, como mostrar un mensaje de éxito
      } catch (error) {
        console.error('Error al crear la tarea:', error);
        // Podemos agregar lógica adicional aquí, como mostrar un mensaje de error al usuario
      }
    },

    }
  };
</script>
  
  
<style scoped>
  /* Estilos específicos para este componente */
  .container {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    background-color: #ffffff;
  }
  
  .navbar {
    margin-bottom: 20px;
    border-bottom: 1px solid #ccc;
    background-color: #f8f9fa;
  }
  
  .navbar-brand {
    font-weight: bold;
    font-size: 1.5rem;
  }
  
  .user-icon {
    width: 30px; /* Ajusta el tamaño de la imagen del usuario según sea necesario */
    height: 30px;
    border-radius: 50%; /* Para que la imagen sea redonda */
    margin-right: 10px;
  }
  </style>
  