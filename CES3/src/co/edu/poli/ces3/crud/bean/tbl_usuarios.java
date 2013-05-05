package co.edu.poli.ces3.crud.bean;

import co.edu.poli.ces3.crud.Columna;
import co.edu.poli.ces3.crud.Crud;

public final class tbl_usuarios extends Crud {

     @Columna(ClavePrimaria=true,AutoNumerico=true, Requered = true,NameForeingKey = "")
    private java.lang.Integer Id_usuario;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = true,NameForeingKey = "")
    private java.lang.String Nombre;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = true,NameForeingKey = "")
    private java.lang.String Apellido;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = true,NameForeingKey = "")
    private java.lang.String Usuario;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = true,NameForeingKey = "")
    private java.lang.String Clave;



     public java.lang.Integer getId_usuario(){
           return this.Id_usuario;
     }

     public java.lang.String getNombre(){
           return this.Nombre;
     }

     public java.lang.String getApellido(){
           return this.Apellido;
     }

     public java.lang.String getUsuario(){
           return this.Usuario;
     }

     public java.lang.String getClave(){
           return this.Clave;
     }



     public void setId_usuario(java.lang.Integer Id_usuario){
       this.Id_usuario = Id_usuario;
     }

     public void setNombre(java.lang.String Nombre){
       this.Nombre = Nombre;
     }

     public void setApellido(java.lang.String Apellido){
       this.Apellido = Apellido;
     }

     public void setUsuario(java.lang.String Usuario){
       this.Usuario = Usuario;
     }

     public void setClave(java.lang.String Clave){
       this.Clave = Clave;
     }

     public tbl_usuarios(){
     }

     public static void main(String... args){
         tbl_usuarios e = new tbl_usuarios();
         e.setApellido("kdsfsdaf");
         e.setClave("tales");
         e.setNombre("jfaskdf");
         e.setUsuario("ajajjaja");
         
         e.insert();
     }
}