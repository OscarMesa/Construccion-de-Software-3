package org.yournamehere.client;

import java.io.Serializable;

/**
 *
 * @author sala302
 */
public class modUsuario implements Serializable{
    private Integer Id_usuario;
    private String Nombre;
    private String Apellido;
    private String Clave;
    private String Usuario;
    
    public modUsuario(){
    
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }
    
    

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }
    
    

    public Integer getId_usuario() {
        return Id_usuario;
    }

    public void setId_usuario(Integer Id_usuario) {
        this.Id_usuario = Id_usuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }
    
}
