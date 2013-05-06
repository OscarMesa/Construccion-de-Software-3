package co.edu.poli.ces3.crud.bean;

import co.edu.poli.ces3.crud.Columna;
import co.edu.poli.ces3.crud.Crud;

public final class tbl_modulos extends Crud {

     @Columna(ClavePrimaria=true,AutoNumerico=true, Requered = true,NameForeingKey = "")
    private java.lang.Integer id_modulo;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = true,NameForeingKey = "")
    private java.lang.String nombre;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = false,NameForeingKey = "")
    private java.lang.String descripcion;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = true,NameForeingKey = "")
    private java.sql.Timestamp creado;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = false,NameForeingKey = "")
    private java.lang.Boolean activo;



     public java.lang.Integer getId_modulo(){
           return this.id_modulo;
     }

     public java.lang.String getNombre(){
           return this.nombre;
     }

     public java.lang.String getDescripcion(){
           return this.descripcion;
     }

     public java.sql.Timestamp getCreado(){
           return this.creado;
     }

     public java.lang.Boolean getActivo(){
           return this.activo;
     }

     public void setId_modulo(java.lang.Integer id_modulo){
       this.id_modulo = id_modulo;
     }

     public void setNombre(java.lang.String nombre){
       this.nombre = nombre;
     }

     public void setDescripcion(java.lang.String descripcion){
       this.descripcion = descripcion;
     }

     public void setCreado(java.sql.Timestamp creado){
       this.creado = creado;
     }

     public void setActivo(java.lang.Boolean activo){
       this.activo = activo;
     }

     public tbl_modulos(){
     }

     public static void main(String... args){
     }
}