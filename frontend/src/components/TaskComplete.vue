<template>
  <div>
    <!-- Barra de navegación -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
        <router-link to="/" class="navbar-brand">
          <img src="@/assets/LogoApp.jpg" alt="Inicio" class="logo-icon" />
          Inicio
        </router-link>
        <div class="collapse navbar-collapse">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <!-- Las opciones de Tareas y Etiquetas se han eliminado de aquí -->
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
      <h2 class="text-center mb-4">Tareas Completadas</h2>

      <!-- Panel group para los paneles de tareas -->
      <div class="d-flex flex-wrap">
        <!-- Iteración sobre las tareas -->
        <div v-for="task in completedTasks" :key="task.id" class="card mb-3" style="width: 18rem; margin-right: 20px;">
          <!-- Cabecera del panel -->
          <div class="card-header" style="color: white; background-color: rgb(114, 213, 147);">

            <input v-model="task.titulo" type="text" class="form-control" id="fechaCreacion{{ task.id }}"
              style="border: none; background: none; color: inherit; width: 100%; outline: none; cursor: pointer; font-size: inherit;">
          </div>

          <!-- Contenido del panel -->
          <div class="card-body">
            <div class="form-group mb-3">
              <label for="descripcion{{ task.id }}">Descripción:</label>
              <input v-model="task.descripcion" type="text" class="form-control" id="descripcion{{ task.id }}">
            </div>
            <div class="form-group mb-3">
              <label for="estado{{ task.id }}">Estado:</label>
              <select v-model="task.estado" class="form-control" id="estado{{ task.id }}">
                <option value="Pendiente" :selected="task.estado === 'Pendiente'">Pendiente</option>
                <option value="En proceso" :selected="task.estado === 'En proceso'">En Proceso</option>
                <option value="Completada" :selected="task.estado === 'Completada'">Completada</option>
                <!-- Agrega más opciones según sea necesario -->
              </select>
            </div>
            <div class="form-group mb-3">
              <label for="fechaCreacion{{ task.id }}">Fecha Creación:</label>
              <input v-model="task.fechaCreacion" type="text" class="form-control" id="fechaCreacion{{ task.id }}"
                disabled>
            </div>
            <div class="form-group mb-3">
              <label for="fechaModificacion{{ task.id }}">Fecha Modificación:</label>
              <input v-model="task.fechaModificacion" type="text" class="form-control"
                id="fechaModificacion{{ task.id }}" disabled>
            </div>
            <div class="form-group mb-3">
              <label for="fechaLimit{{ task.id }}">Fecha Límite:</label>
              <input v-model="task.fechaLimite" type="text" class="form-control" id="fechaLimite{{ task.id }}">
            </div>
            <div class="form-group mb-3">
              <label for="tiempoTranscurrido{{ task.id }}">Tiempo Transcurrido:</label>
              <input
                :value="formatoTiempoTranscurrido(calcularTiempoTranscurrido(parseFecha(task.fechaCreacion), parseFecha(task.fechaLimite)))"
                type="text" class="form-control" id="tiempoTranscurrido{{ task.id }}" disabled>
            </div>
            <!-- Acciones -->
            <button @click="actualizarTarea(task)" class="btn btn-primary mr-1"
              style="margin-right: 75px;">Actualizar</button>
            <button @click="eliminarTarea(task.id)" class="btn btn-danger">Eliminar</button>
          </div>
        </div>
      </div>
    </div>


    <!-- Barra de navegación inferior -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-bottom bg-light">
      <div class="container-fluid justify-content-center">
        <router-link to="/tareas" class="nav-link mx-2">Tareas</router-link>
        <router-link to="/etiquetas" class="nav-link mx-2">Etiquetas</router-link>
        <router-link to="/tareasCompletadas" class="nav-link mx-2">Completadas</router-link>
        <router-link to="/tareasProceso" class="nav-link mx-2">En Proceso</router-link>
        <router-link to="/tareasPendientes" class="nav-link mx-2">Pendientes</router-link>
      </div>
    </nav>

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
      console.error('ID de usuario no válido.', userId);
    } else {
      console.error('ID de usuario no válido.');
    }
  },

  methods: {
    parseFecha(fechaString) {
      return new Date(fechaString);
    },
    // Función para calcular los días transcurridos entre dos fechas
    calcularTiempoTranscurrido(fechaInicio, fechaFin) {
      const unMinuto = 1000 * 60; // milisegundos en un minuto
      const unHora = unMinuto * 60; // milisegundos en una hora
      const unDia = unHora * 24; // milisegundos en un día

      const diferenciaTiempo = Math.abs(fechaFin.getTime() - fechaInicio.getTime());
      const diasTranscurridos = Math.floor(diferenciaTiempo / unDia);
      const horasTranscurridas = Math.floor((diferenciaTiempo % unDia) / unHora);
      const minutosTranscurridos = Math.floor((diferenciaTiempo % unHora) / unMinuto);

      return {
        dias: diasTranscurridos,
        horas: horasTranscurridas,
        minutos: minutosTranscurridos
      };
    },
    // Función para formatear el objeto de tiempo transcurrido en una cadena legible
    formatoTiempoTranscurrido(tiempoTranscurrido) {
      return `${tiempoTranscurrido.dias} días, ${tiempoTranscurrido.horas} horas y ${tiempoTranscurrido.minutos} minutos`;
    },
    redirectToLogin() {
      this.$router.push('/login');
    },
    redirectToSignIn() {
      // Agrega lógica para redirigir a la ruta de registro (si es necesario)
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

    async getTasksByEtiquetaId() {
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
              const completedTasks = tasksResponse.data.result.filter(task => task.estado === 'Completada');
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

        // Agregamos la nueva tarea a la lista existente
        this.tasks.push(createdTask);

        // Limpiamos el formulario después de crear la tarea
        this.nuevaTarea = {
          titulo: '',
          descripcion: '',
          estado: '',
          fechaLimite: '',
        };
      } catch (error) {
        console.error('Error al crear la tarea:', error);
        // Podemos agregar lógica adicional aquí, como mostrar un mensaje de error al usuario
      }
    },

    async actualizarTarea(task) {
      try {
        // Realizar la solicitud para actualizar la tarea
        const response = await axios.put(`http://localhost:9999/api/v1/task/Update/${task.id}`, task);
        const updatedTask = response.data.result;
        console.log('Tarea actualizada exitosamente:', updatedTask);

        // Actualizar la tarea en la lista local
        const index = this.tasks.findIndex(t => t.id === updatedTask.id);
        if (index !== -1) {
          // Actualizar solo los campos que se han modificado
          this.tasks[index] = { ...this.tasks[index], ...updatedTask };
        }
      } catch (error) {
        console.error('Error al actualizar la tarea:', error);
      }
    },


    async eliminarTarea(taskId) {
      try {
        // Realizar la solicitud para eliminar la tarea
        await axios.delete(`http://localhost:9999/api/v1/task/Delete/${taskId}`);
        console.log('Tarea eliminada exitosamente:', taskId);

        // Actualizar la lista de tareas después de la eliminación
        this.tasks = this.tasks.filter(task => task.id !== taskId);
      } catch (error) {
        console.error('Error al eliminar la tarea:', error);
      }
    },

  }
};
</script>


<style scoped>
/* Estilos específicos para este componente */
.container {
  max-width: max-content;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #f8f8f8;
}

.navbar {
  margin-bottom: 0px;
  border-bottom: 1px solid #ccc;
  background-color: #f8f9fa;
}

.navbar-brand {
  font-weight: bold;
  font-size: 1.5rem;
}

.user-icon {
  width: 30px;
  /* Ajusta el tamaño de la imagen del usuario según sea necesario */
  height: 30px;
  border-radius: 50%;
  /* Para que la imagen sea redonda */
  margin-right: 10px;
}

.logo-icon {
  width: 50px;
  /* Ajusta el tamaño de la imagen del usuario según sea necesario */
  height: 50px;
  border-radius: 50%;
  /* Para que la imagen sea redonda */
  margin-right: 10px;
}
</style>