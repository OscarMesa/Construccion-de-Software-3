/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.poli.ces3.crud;

/**
 *
 * @author Oskar
 */
import java.sql.Connection;
import java.sql.DriverManager;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

public class SQL {
    
    private Connection cnn;
    public SQL(){
    
    }
    
    public static Connection getConexion(){
        Connection con = null;
        
        try{
            String url = "jdbc:postgresql://localhost:5432/ces3";
            String user = "postgres";
            String password = "postgres";
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url,user,password);
        }catch(Exception e){e.printStackTrace();}
         finally{return con;}
    }
    
    public static Connection getConexion1(){
        Connection conexion = null;
        Context Ctx;
        DataSource ds;
        try{
            Ctx = new InitialContext();
            
            ds = (DataSource) Ctx.lookup("jdbc/ces3");
            conexion = ds.getConnection();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            return conexion;
        }
    }
    
    public static void main(String... args){
        Connection x = SQL.getConexion();
        System.out.println(x);
    }
    
    
    
    
    
}
