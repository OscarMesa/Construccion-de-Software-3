/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.model;

import java.io.Serializable;

/**
 *
 * @author omesa
 */
public class modPermisos implements Serializable{
   private Integer id_perfil;
   private Integer id_usuario;
   private String nombrePerfil;
   private String nombreUsuario;
   private modPerfil perfil;
   private modUsuario usuario;
   private String eliminar;

    public String getEliminar() {
        return eliminar;
    }

    public void setEliminar(String eliminar) {
        this.eliminar = eliminar;
    }
   
   

    public Integer getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(Integer id_perfil) {
        this.id_perfil = id_perfil;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public modPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(modPerfil perfil) {
        this.perfil = perfil;
    }

    public modUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(modUsuario usuario) {
        this.usuario = usuario;
    }
   
}
