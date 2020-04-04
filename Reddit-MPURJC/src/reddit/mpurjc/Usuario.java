
package reddit.mpurjc;

import static java.lang.Boolean.FALSE;
import java.util.ArrayList;
import java.util.List;
import static reddit.mpurjc.Usuario.Rol.*;


public class Usuario {
    
    private String nick;
    private String nombre;
    private String contraseña;
    private String email;
    private Rol rol;
    private List<SubForo> subscripciones;
    private Penalizacion penalizacion;
    private boolean esAdministrador;

    enum Rol
    {
        ALUMNO, PROFESOR, ADMINISTRADOR;
    }
    public Usuario(){}
    
    public Usuario (String nick, String nombre, String contraseña, String email){
        if(evaluadorEmail(email)){
            this.nick = nick;
            this.nombre = nombre;
            this.contraseña = contraseña;
            this.email = email;
            this.subscripciones = new ArrayList<SubForo>();
            this.esAdministrador = FALSE;
        }else
            System.out.println("Debes introducir un correo de la urjc");
    }

    
    public boolean inicioPermitido(){
        return false;
    }
    
    public boolean comprobarCredenciales(Usuario usuario){
        return false;
    }
   
    private boolean evaluadorEmail(String email){
        int longitud = email.length();
        int index = email.indexOf("@");
        String subEmail = email.substring(index+1,longitud);
        switch (subEmail){
            case "alumnos.urjc.es": this.rol = ALUMNO;
                                    return true;
                                   
            case "urjc.es": this.rol = PROFESOR;
                                    return true;
            default: return false;                        
        }
    }
    
    /*------------------------GETTERS------------------------*/
    public String getNick() {
        return nick;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public Rol getRol() {
        return rol;
    }

    public List<SubForo> getSubscripciones() {
        return subscripciones;
    }

    public Penalizacion getPenalizacion() {
        return penalizacion;
    }

    public boolean isEsAdministrador() {
        return esAdministrador;
    }
    
    /*------------------------SETTERS------------------------*/
    
    
    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setSubscripciones(List<SubForo> subscripciones) {
        this.subscripciones = subscripciones;
    }

    public void setPenalizacion(Penalizacion penalizacion) {
        this.penalizacion = penalizacion;
    }

    public void setEsAdministrador(boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }
    
    
}
