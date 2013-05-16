package co.edu.poli.ces3.crud.bean;

import co.edu.poli.ces3.crud.Columna;
import co.edu.poli.ces3.crud.Crud;
import java.util.ArrayList;

public final class tbl_permisos extends Crud {

     @Columna(ClavePrimaria=true,AutoNumerico=false, Requered = true,NameForeingKey = "tbl_perfiles.id_perfil")
    private java.lang.Integer id_perfil;

     @Columna(ClavePrimaria=true,AutoNumerico=false, Requered = true,NameForeingKey = "tbl_usuarios.id_usuario")
    private java.lang.Integer id_usuario;
     
    private tbl_perfiles perfil;

    public tbl_perfiles getPerfil() {
        return perfil;
    }

    public void setPerfil(tbl_perfiles perfil) {
        this.perfil = perfil;
    }

    public tbl_usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(tbl_usuarios usuario) {
        this.usuario = usuario;
    }
    
    private tbl_usuarios usuario;
    
    
     public java.lang.Integer getId_perfil(){
           return this.id_perfil;
     }

     public java.lang.Integer getId_usuario(){
           return this.id_usuario;
     }



     public void setId_perfil(java.lang.Integer id_perfil){
       this.id_perfil = id_perfil;
     }

     public void setId_usuario(java.lang.Integer id_usuario){
       this.id_usuario = id_usuario;
     }
     
     @Override
     public ArrayList<tbl_permisos> select()
     {
         return (ArrayList<tbl_permisos>)super.select();
     }
     public tbl_permisos(){
     }

     public static void main(String... args){
     }
}