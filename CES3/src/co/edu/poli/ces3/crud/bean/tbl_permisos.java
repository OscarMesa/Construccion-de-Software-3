package co.edu.poli.ces3.crud.bean;

import co.edu.poli.ces3.crud.Columna;
import co.edu.poli.ces3.crud.Crud;

public final class tbl_permisos extends Crud {

     @Columna(ClavePrimaria=true,AutoNumerico=false, Requered = true,NameForeingKey = "tbl_perfiles.id_perfil")
    private java.lang.Integer Id_perfil;

     @Columna(ClavePrimaria=true,AutoNumerico=false, Requered = true,NameForeingKey = "tbl_usuarios.id_usuario")
    private java.lang.Integer Id_usuario;



     public java.lang.Integer Id_perfil(){
           return this.Id_perfil;
     }

     public java.lang.Integer Id_usuario(){
           return this.Id_usuario;
     }



     public void setId_perfil(java.lang.Integer Id_perfil){
       this.Id_perfil = Id_perfil;
     }

     public void setId_usuario(java.lang.Integer Id_usuario){
       this.Id_usuario = Id_usuario;
     }

     public tbl_permisos(){
     }

     public static void main(String... args){
     }
}