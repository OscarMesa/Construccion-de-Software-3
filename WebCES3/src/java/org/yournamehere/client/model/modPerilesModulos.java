/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.model;

import java.io.Serializable;

/**
 *
 * @author oskar
 */
public class modPerilesModulos implements Serializable{
    private Integer id_perfil;
    private Integer id_modulo;
    private modModulo modulo;
    private modPerfil perfil;
    private String nombrePerfil;
    private String nombreModulo;

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }
    
    
    
    public modModulo getModulo() {
        return modulo;
    }

    public void setModulo(modModulo modulo) {
        this.modulo = modulo;
    }

    public modPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(modPerfil perfil) {
        this.perfil = perfil;
    }
    
    public Integer getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(Integer id_perfil) {
        this.id_perfil = id_perfil;
    }

    public Integer getId_modulo() {
        return id_modulo;
    }

    public void setId_modulo(Integer id_modulo) {
        this.id_modulo = id_modulo;
    }
}
