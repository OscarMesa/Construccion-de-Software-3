package co.edu.poli.ces3.crud.bean;

import co.edu.poli.ces3.crud.Columna;
import co.edu.poli.ces3.crud.Crud;
import java.util.ArrayList;
import java.util.StringTokenizer;

public final class tbl_perfiles_modulos extends Crud {

     @Columna(ClavePrimaria=true,AutoNumerico=false, Requered = true,NameForeingKey = "tbl_perfiles.id_perfil")
    private java.lang.Integer id_perfil;

     @Columna(ClavePrimaria=true,AutoNumerico=false, Requered = true,NameForeingKey = "tbl_modulos.id_modulo")
    private java.lang.Integer id_modulo;
     
    //@Columna(ClavePrimaria=false,AutoNumerico=false, Requered = false,NameForeingKey = "") 
    private tbl_modulos tbl_modulos;

   // @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = false,NameForeingKey = "")
    private tbl_perfiles tbl_perfiles;
    
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

    public tbl_modulos getTbl_modulos() {
        return tbl_modulos;
    }

    public void setTbl_modulos(tbl_modulos tbl_modulos) {
        this.tbl_modulos = tbl_modulos;
    }

    public tbl_perfiles getTbl_perfiles() {
        return tbl_perfiles;
    }

    public void setTbl_perfiles(tbl_perfiles tbl_perfiles) {
        this.tbl_perfiles = tbl_perfiles;
    }
    @Override
    public ArrayList<tbl_perfiles_modulos> select()
    {
        return (ArrayList<tbl_perfiles_modulos>)super.select();
    }
     
     
     public static void main(String... args){
         tbl_perfiles_modulos t = new tbl_perfiles_modulos();
         //list.remove(0);
         
         for (tbl_perfiles_modulos x : t.select()) {
             System.out.println(x);
         }
     }
}