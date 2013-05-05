package co.edu.poli.ces3.crud.bean;

import co.edu.poli.ces3.crud.Columna;
import co.edu.poli.ces3.crud.Crud;

public final class tbl_perfiles_modulos extends Crud {

     @Columna(ClavePrimaria=true,AutoNumerico=false, Requered = true,NameForeingKey = "tbl_perfiles.id_perfil")
    private java.lang.Integer Id_perfil;

     @Columna(ClavePrimaria=true,AutoNumerico=false, Requered = true,NameForeingKey = "tbl_modulos.id_modulo")
    private java.lang.Integer Id_modulo;



     public java.lang.Integer Id_perfil(){
           return this.Id_perfil;
     }

     public java.lang.Integer Id_modulo(){
           return this.Id_modulo;
     }



     public void setId_perfil(java.lang.Integer Id_perfil){
       this.Id_perfil = Id_perfil;
     }

     public void setId_modulo(java.lang.Integer Id_modulo){
       this.Id_modulo = Id_modulo;
     }

     public tbl_perfiles_modulos(){
     }

     public static void main(String... args){
     }
}