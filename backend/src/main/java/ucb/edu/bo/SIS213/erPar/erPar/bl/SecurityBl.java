package ucb.edu.bo.SIS213.erPar.erPar.bl;

import org.springframework.stereotype.Service;

import ucb.edu.bo.SIS213.erPar.erPar.dao.UserDao;
import ucb.edu.bo.SIS213.erPar.erPar.entity.User;
import ucb.edu.bo.SIS213.erPar.erPar.exep.UserException;

@Service
public class SecurityBl {

    private UserDao usuarioDao;

    public SecurityBl(UserDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public User login(String nombreUsuario, String contrasena) throws UserException {
            User user = usuarioDao.findByNombreUsuarioAndContrasena(nombreUsuario, contrasena);
        if (user == null) {
            throw new UserException("Usuario o contraseña incorrectos");
        }
        user.setContrasena(null);
        return user;
    }
}
