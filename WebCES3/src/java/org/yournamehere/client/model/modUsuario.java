package org.yournamehere.client.model;

import java.io.Serializable;

/**
 *
 * @author sala302
 */
public class modUsuario implements Serializable{
    
    private Integer id_usuario;
    private String nombre;
    private String apellido;
    private String clave;
    private String usuario;
    
    public modUsuario(){
    
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String Usuario) {
        this.usuario = Usuario;
    }
    
    

    public String getClave() {
        return clave;
    }

    public void setClave(String Clave) {
        this.clave = Clave;
    }
    
    

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer Id_usuario) {
        this.id_usuario = Id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String Apellido) {
        this.apellido = Apellido;
    }
    
}
