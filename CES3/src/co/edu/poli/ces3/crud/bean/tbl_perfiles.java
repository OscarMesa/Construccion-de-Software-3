package co.edu.poli.ces3.crud.bean;

import co.edu.poli.ces3.crud.Columna;
import co.edu.poli.ces3.crud.Crud;

public final class tbl_perfiles extends Crud {

     @Columna(ClavePrimaria=true,AutoNumerico=true, Requered = true,NameForeingKey = "")
    private java.lang.Integer id_perfil;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = true,NameForeingKey = "")
    private java.lang.String nombre;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = false,NameForeingKey = "")
    private java.lang.String descripcion;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = false,NameForeingKey = "")
    private java.lang.Boolean activo;



     public java.lang.Integer getId_perfil(){
           return this.id_perfil;
     }

     public java.lang.String getNombre(){
           return this.nombre;
     }

     public java.lang.String getDescripcion(){
           return this.descripcion;
     }

     public java.lang.Boolean getActivo(){
           return this.activo;
     }



     public void setId_perfil(java.lang.Integer id_perfil){
       this.id_perfil = id_perfil;
     }

     public void setNombre(java.lang.String nombre){
       this.nombre = nombre;
     }

     public void setDescripcion(java.lang.String descripcion){
       this.descripcion = descripcion;
     }

     public void setActivo(java.lang.Boolean activo){
       this.activo = activo;
     }

     public tbl_perfiles(){
     }

     public static void main(String... args){
     }
}