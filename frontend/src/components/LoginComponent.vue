<template>
  <section class="vh-100 d-flex align-items-center justify-content-center">
    <div class="container bg-light p-4 rounded" style="max-width: 400px;">
      <div class="row">
        <div class="col-md-12">
          <h2 class="text-center text-primary mb-4">Iniciar sesión</h2>
          <div class="card">
            <div class="card-body">
              <form class="login-form" @submit.prevent="submitForm">
                <div class="mb-3">
                  <label for="username" class="form-label">Usuario:</label>
                  <input type="text" id="username" name="username" v-model="usuario" required class="form-control">
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">Contraseña:</label>
                  <input type="password" id="password" name="password" v-model="contrasena" required class="form-control">
                </div>
                <div class="d-grid gap-2">
                  <button type="submit" class="btn btn-primary">Ingresar</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      usuario: '',
      contrasena: ''
    };
  },
  methods: {
    async submitForm() {
      try {
        const response = await axios.post('http://localhost:9999/api/v1/login', {
          usuario: this.usuario,
          contrasena: this.contrasena
        });

        const user = response.data.result;

        if (user && user.id) {
          // Las credenciales son correctas
          sessionStorage.setItem('userId', user.id); // Almacena el ID del usuario en la sesión
          sessionStorage.setItem('name', user.nombre); // Almacena el nombre del usuario en la sesión
          this.$router.push('/');
        } else {
          alert('Credenciales incorrectas');
        }
      } catch (error) {
        console.error('Error al iniciar sesión:', error);
        alert('Error al iniciar sesión');
      }
    }
  }
};
</script>

<style scoped>
/* Estilos específicos para este componente */
.error-message {
  color: red;
  margin-top: 10px;
}
</style>
