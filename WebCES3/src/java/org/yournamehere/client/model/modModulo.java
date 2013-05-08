/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author oskar
 */
public class modModulo implements Serializable {
    
    private Integer id_modulo;
    private String nombre;
    private String descripcion;
    private Timestamp creado;
    private Boolean activo;
    
    

    public Integer getId_modulo() {
        return id_modulo;
    }

    public void setId_modulo(Integer id_modulo) {
        this.id_modulo = id_modulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getCreado() {
        return creado;
    }

    public void setCreado(Timestamp creado) {
        this.creado = creado;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
