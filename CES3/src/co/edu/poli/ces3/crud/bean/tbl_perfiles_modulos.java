package co.edu.poli.ces3.crud.bean;

import co.edu.poli.ces3.crud.Columna;
import co.edu.poli.ces3.crud.Crud;
import java.util.ArrayList;

public final class tbl_perfiles_modulos extends Crud {

     @Columna(ClavePrimaria=true,AutoNumerico=false, Requered = true,NameForeingKey = "tbl_perfiles.id_perfil")
    private java.lang.Integer id_perfil;

     @Columna(ClavePrimaria=true,AutoNumerico=false, Requered = true,NameForeingKey = "tbl_modulos.id_modulo")
    private java.lang.Integer id_modulo;



     public java.lang.Integer getId_perfil(){
           return this.id_perfil;
     }

     public java.lang.Integer getId_modulo(){
           return this.id_modulo;
     }

     public void setId_perfil(java.lang.Integer id_perfil){
       this.id_perfil = id_perfil;
     }

     public void setId_modulo(java.lang.Integer id_modulo){
       this.id_modulo = id_modulo;
     }

     public tbl_perfiles_modulos(){
         
     }
     
     @Override
     public ArrayList<tbl_perfiles_modulos> select()
     {
         return (ArrayList<tbl_perfiles_modulos>)super.select();
     }
     public static void main(String... args){
         tbl_perfiles_modulos t = new tbl_perfiles_modulos();
         
         for (tbl_perfiles_modulos x : t.select()) {
             System.out.println(x);
         }
     }
}