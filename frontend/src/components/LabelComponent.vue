<template>
    <div>
      <!-- Barra de navegación -->
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
          <router-link to="/" class="navbar-brand">Inicio</router-link>
          <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <router-link to="/tareas" class="navbar-brand">Tareas</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/etiquetas" class="navbar-brand">Etiquetas</router-link>
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
  
      <!-- Contenido de la página de etiquetas -->
    <div class="container mt-5">
      <h2 class="text-center mb-4">Etiquetas</h2>
      <table class="table">
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Acciones</th> <!-- Nueva columna para acciones -->
          </tr>
        </thead>
        <tbody>
          <tr v-for="etiqueta in etiquetas" :key="etiqueta.id">
            <td>
              <input v-model="etiqueta.name" type="text" class="form-control" />
            </td>
            <td>
              <button @click="actualizarEtiqueta(etiqueta.id, etiqueta.name)">Actualizar</button>
              <button @click="eliminarEtiqueta(etiqueta.id)">Eliminar</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Crear Nueva Etiqueta -->
    <div class="mt-4">
      <h4 class="mb-3">Crear Nueva Etiqueta</h4>
      <div class="input-group mb-3">
        <input v-model="nuevaEtiqueta" type="text" class="form-control" placeholder="Nombre de la etiqueta">
        <button @click="crearEtiqueta" class="btn btn-primary">Crear</button>
      </div>
    </div>
  </div>
</template>
  
  
  <script>
import axios from 'axios';

export default {
  data() {
    return {
      etiquetas: [],
      nuevaEtiqueta: '',
    };
  },
  created() {
    const userId = sessionStorage.getItem('userId');
    if (userId) {
      // Cuando se carga el componente, hacer una solicitud para obtener las etiquetas
      this.getLabelsByUserId(userId);
    }
  },
  methods: {

    redirectToLogin() {
        this.$router.push('/login'); // Redirige a la ruta de Login
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
    // Agrega aquí otros métodos para crear, actualizar y eliminar etiquetas según sea necesario
    async crearEtiqueta() {
      const userId = sessionStorage.getItem('userId');
      if (userId && this.nuevaEtiqueta) {
        try {
          // Enviar solicitud para crear la etiqueta
          const response = await axios.post(`http://localhost:9999/api/v1/label/User/${userId}/Create`, {
            name: this.nuevaEtiqueta
          });

          const nuevaEtiquetaCreada = response.data.data; // Suponiendo que el servidor devuelve la etiqueta creada
          console.log('Etiqueta creada exitosamente:', nuevaEtiquetaCreada);

          // Actualizar la lista de etiquetas después de la creación
          this.getLabelsByUserId(userId);
          
          // Limpiar el campo del nombre de la etiqueta después de la creación
          this.nuevaEtiqueta = '';
        } catch (error) {
          console.error('Error al crear la etiqueta:', error);
        }
      } else {
        console.error('Usuario no válido o nombre de etiqueta vacío.');
      }
    },

    async eliminarEtiqueta(etiquetaId) {
      try {
        await axios.delete(`http://localhost:9999/api/v1/label/Delete/${etiquetaId}`);
        console.log('Etiqueta eliminada exitosamente.');

        // Actualizar la lista de etiquetas después de la eliminación
        const userId = sessionStorage.getItem('userId');
        if (userId) {
          this.getLabelsByUserId(userId);
        }
      } catch (error) {
        console.error('Error al eliminar la etiqueta:', error);
      }
    },

    async actualizarEtiqueta(etiquetaId, nuevoNombre) {
      try {
        const etiquetaActualizada = await axios.put(`http://localhost:9999/api/v1/label/Update/${etiquetaId}`, {
          name: nuevoNombre,
        });

        console.log('Etiqueta actualizada exitosamente:', etiquetaActualizada.data);
        // Actualizar la lista de etiquetas después de la actualización
        this.getLabelsByUserId(sessionStorage.getItem('userId'));
      } catch (error) {
        console.error('Error al actualizar la etiqueta:', error);
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