package ucb.edu.bo.SIS213.erPar.erPar.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ucb.edu.bo.SIS213.erPar.erPar.bl.SecurityBl;
import ucb.edu.bo.SIS213.erPar.erPar.dto.LoginRequestDTO;
import ucb.edu.bo.SIS213.erPar.erPar.dto.ResponseDTO;
import ucb.edu.bo.SIS213.erPar.erPar.entity.User;
import ucb.edu.bo.SIS213.erPar.erPar.exep.UserException;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/api/v1/login")
public class LoginAPI { 

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LabelAPI.class);

    SecurityBl securityBL;

    public LoginAPI(SecurityBl securityBL) {
        this.securityBL = securityBL;
    }

    @PostMapping
    public ResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        User user;
        try {
            user = securityBL.login(loginRequestDTO.getUsuario(), loginRequestDTO.getContrasena());
            LOGGER.info("Se obtuvieron las etiquetas exitosamente");
        } catch (UserException e) {
            LOGGER.error("Error al obtener las etiquetas", e);
            return new ResponseDTO("TASK-1000", e.getMessage());
        }
        return new ResponseDTO(user);
    }
    
}
