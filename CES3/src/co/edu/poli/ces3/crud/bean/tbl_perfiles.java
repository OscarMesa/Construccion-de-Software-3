package co.edu.poli.ces3.crud.bean;

import co.edu.poli.ces3.crud.Columna;
import co.edu.poli.ces3.crud.Crud;

public final class tbl_perfiles extends Crud {

     @Columna(ClavePrimaria=true,AutoNumerico=true, Requered = true,NameForeingKey = "")
    private java.lang.Integer Id_perfil;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = true,NameForeingKey = "")
    private java.lang.String Nombre;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = false,NameForeingKey = "")
    private java.lang.String Descripcion;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = false,NameForeingKey = "")
    private java.lang.Boolean Activo;



     public java.lang.Integer Id_perfil(){
           return this.Id_perfil;
     }

     public java.lang.String Nombre(){
           return this.Nombre;
     }

     public java.lang.String Descripcion(){
           return this.Descripcion;
     }

     public java.lang.Boolean Activo(){
           return this.Activo;
     }



     public void setId_perfil(java.lang.Integer Id_perfil){
       this.Id_perfil = Id_perfil;
     }

     public void setNombre(java.lang.String Nombre){
       this.Nombre = Nombre;
     }

     public void setDescripcion(java.lang.String Descripcion){
       this.Descripcion = Descripcion;
     }

     public void setActivo(java.lang.Boolean Activo){
       this.Activo = Activo;
     }

     public tbl_perfiles(){
     }

     public static void main(String... args){
     }
}