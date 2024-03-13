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
  
      <!-- Contenido del componente Home.vue -->
      <div class="container mt-5">
        <h2 class="text-center mb-4">Bienvenido a nuestra Aplicación de Tareas</h2>
        <p class="lead text-center">Aquí puedes organizar tus tareas de forma fácil y eficiente.</p>
        <!-- Puedes agregar contenido descriptivo adicional aquí, como imágenes, carrusel, etc. -->
      </div>

      <EtiquetasComponent :userId="userId" />
    </div>
  </template>
  
  <script>
  export default {
    methods: {
      redirectToLogin() {
        this.$router.push('/login'); // Redirige a la ruta de Login
      },
      redirectToSignIn() {
        // Agrega lógica para redirigir a la ruta de registro (si es necesario)
      }
    },
    data() {
      return {
        usuario: '' // Nombre del usuario que ha iniciado sesión
      };
    },
    created() {
      // Simulación de nombre de usuario
      this.usuario = sessionStorage.getItem('name') || 'Usuario'; // Obtén el nombre del usuario que ha iniciado sesión desde el almacenamiento de sesión
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
  
  
  
  
  