/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.poli.ces3.utility;


import co.edu.poli.ces3.crud.SQL;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
/**
 *
 * @author oskar
 */
public class generator {

    private String NameClass;
    private Package pack;
    
    public generator(){
        setPack(generator.class.getPackage());
    }
    public void WhireCode(){
            Connection cnn = SQL.getConexion();
            System.out.println(cnn);
            int ind = 1;
        try{
            StringBuilder atributs,MethodsGet,MethodsSet;
            String field,anotation;
            atributs = new StringBuilder();
            MethodsGet = new StringBuilder();
            MethodsSet = new StringBuilder();
            PreparedStatement cmd = cnn.prepareStatement(getSQLSelect());
            ResultSet table = cmd.executeQuery();
            ResultSetMetaData infotable = table.getMetaData();
            DatabaseMetaData data = cnn.getMetaData();
            ArrayList<String> PrimaryKey = new ArrayList<String>();
            ResultSet foreingKey = data.getCrossReference(null, null, null,null,null,getNameClass());
            Map<String,String> CatalogForeingKey = new HashMap<String, String>();
            
            while(foreingKey.next())
            {
               CatalogForeingKey.put(foreingKey.getString("PKCOLUMN_NAME"), foreingKey.getString("PKTABLE_NAME"));
            
            }
            ResultSet rs = data.getPrimaryKeys(null,null, String.valueOf(getNameClass()).toLowerCase());
            
            
            File path = new File(System.getProperty("user.dir")+"/src/"+getPath()+getNameClass() + ".java");
            path.createNewFile();
           
            while(rs.next())
                PrimaryKey.add(String.valueOf(rs.getString(4)).toLowerCase()); 
            
           BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path.getPath()), "utf-8"));
           out.write(getPack() + ";\n\nimport co.edu.poli.ces3.crud.Columna;\nimport co.edu.poli.ces3.crud.Crud;\n\n"
                   + "public final class "+getNameClass()+" extends Crud {\n\n");
           
           
           int p=0;
           for(int i=1;i<=infotable.getColumnCount();i++){
               field = String.valueOf(infotable.getColumnLabel(i).charAt(0)).toUpperCase() + infotable.getColumnLabel(i).substring(1, infotable.getColumnLabel(i).length());
              // System.out.println(infotable.getColumnLabel(i) + ":" + infotable.isAutoIncrement(i));
               anotation = "@Columna(";
               if(PrimaryKey.indexOf(field.toLowerCase())>=0)
                   anotation +=  "ClavePrimaria=true";
               else
                   anotation += "ClavePrimaria=false";
      
               if(infotable.isAutoIncrement(i))
                   anotation += ",AutoNumerico=true";
               else
                   anotation +=  ",AutoNumerico=false";
               
               if(infotable.isNullable(i) == 0 )
                   anotation += ", Requered = true";
               else
                   anotation += ", Requered = false";                   
               
               if(CatalogForeingKey.get(field.toLowerCase()) != null)
                   anotation  += ",NameForeingKey = \""+ CatalogForeingKey.get(field.toLowerCase()) +"." + field.toLowerCase() +"\")";
               else 
                   anotation  += ",NameForeingKey = \"\")";
             
               atributs.append("     "+anotation+"\n    private " + infotable.getColumnClassName(i) + " " + field.toLowerCase() +";\n\n");
               MethodsGet.append("     public " + infotable.getColumnClassName(i) + " get" + field +"(){\n           return this."+field.toLowerCase()+";\n     }\n\n");
               MethodsSet.append("     public void" + " set" + field +"("+infotable.getColumnClassName(i) +" "+ field.toLowerCase() +"){\n       this."+field.toLowerCase()+" = "+field.toLowerCase() +";\n     }\n\n");
           }
           
           out.append(atributs.toString()+"\n\n");
           out.write(MethodsGet.toString()+"\n\n");
           out.write(MethodsSet.toString());
           out.write("     public " + getNameClass() + "(){\n     }\n\n");
           out.write("     public static void main(String... args){\n     }\n");
           
           out.write("}");
           out.close();
           
                
        } catch (Exception ioe) {
          ioe.printStackTrace();
        }

    }
    public String getPath(){
        StringTokenizer path = new StringTokenizer(getPack().toString());
        path.nextToken();
        return path.nextToken().replace('.', '/')+"/";
    }
    public Package getPack() {
        return pack;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }
    public String getNameClass(){
        return NameClass;
    }
    
    public void setNameClass(String x){
        NameClass = x;
    }
    
    public String getSQLSelect() {

        return "SELECT * FROM " + getNameClass();
    }    
    
    public static void main(String[] args){
        generator class1 = new generator();
        class1.setNameClass("tbl_permisos");
        class1.WhireCode();
   
    }
}
    