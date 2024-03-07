package ucb.edu.bo.SIS213.erPar.erPar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ucb.edu.bo.SIS213.erPar.erPar.entity.User;

public interface UserDao extends JpaRepository<User, Long> {
    public User findByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena);
}
