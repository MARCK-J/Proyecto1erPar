package ucb.edu.bo.SIS213.erPar.erPar.dto;

public class LoginRequestDTO {
    private String usuario;
    private String contrasena;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    //dame getters y setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    //dame getters y setters
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    //toString
    @Override
    public String toString() {
        return "LoginRequestDTO [contrasena=" + contrasena + ", usuario=" + usuario + "]";
    }
}
