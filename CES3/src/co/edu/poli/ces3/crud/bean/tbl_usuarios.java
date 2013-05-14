package co.edu.poli.ces3.crud.bean;

import co.edu.poli.ces3.crud.Columna;
import co.edu.poli.ces3.crud.Crud;
import java.lang.reflect.Field;
import java.util.ArrayList;

public final class tbl_usuarios extends Crud {

     @Columna(ClavePrimaria=true,AutoNumerico=true, Requered = true,NameForeingKey = "")
    private java.lang.Integer id_usuario;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = true,NameForeingKey = "")
    private java.lang.String nombre;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = true,NameForeingKey = "")
    private java.lang.String apellido;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = true,NameForeingKey = "")
    private java.lang.String usuario;

     @Columna(ClavePrimaria=false,AutoNumerico=false, Requered = true,NameForeingKey = "")
    private java.lang.String clave;



     public java.lang.Integer getId_usuario(){
           return this.id_usuario;
     }

     public java.lang.String getNombre(){
           return this.nombre;
     }

     public java.lang.String getApellido(){
           return this.apellido;
     }

     public java.lang.String getUsuario(){
           return this.usuario;
     }

     public java.lang.String getClave(){
           return this.clave;
     }



     public void setId_usuario(java.lang.Integer id_usuario){
       this.id_usuario = id_usuario;
     }

     public void setNombre(java.lang.String nombre){
       this.nombre = nombre;
     }

     public void setApellido(java.lang.String apellido){
       this.apellido = apellido;
     }

     public void setUsuario(java.lang.String usuario){
       this.usuario = usuario;
     }

     public void setClave(java.lang.String clave){
       this.clave = clave;
     }

     public tbl_usuarios(){
     }
     
     @Override
     public ArrayList<tbl_usuarios> select()
     {
         return (ArrayList<tbl_usuarios>)super.select();
     }
     
     public void getFields() throws IllegalArgumentException, IllegalAccessException
     {
         for (Field x : this.getClass().getDeclaredFields()) {
             System.out.println(x.get(this));
         }
     }
     
     public static void main(String... args) throws IllegalArgumentException, IllegalAccessException{
         tbl_usuarios t = new tbl_usuarios();
         t.setApellido("mesa");
         for (Field x : t.getClass().getDeclaredFields()) {
             System.out.println(x.get(t));
         }
         // System.out.println(e.getElementAutoincrement("id_usuario"));
    }
}