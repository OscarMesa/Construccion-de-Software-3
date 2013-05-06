/*
 * To change this template, choose Tools | Templates
 *
 * 
 * JPA
 * EJB
 * JSF
 * 
 * and open the template in the editor.
 */
package co.edu.poli.ces3.crud;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oskar
 */
public abstract class Crud implements ICrud {

    public int posicion;
    private int cantidad;
    private int num_row;

    public int getNum_row() {
        return num_row;
    }

    public void setNum_row(int num_row) {
        this.num_row = num_row;
    }
    private Connection cnn;
    private boolean conexionExterna;
    private String tabla;
    private TreeMap<String,Object> autoincrement;
    private ResultSet filas;
    {
        this.posicion = 0;
        this.cantidad = 10;
        this.conexionExterna = false;
        this.cnn = null;
        this.filas = null;
        this.tabla = this.getClass().getSimpleName();
        autoincrement = new TreeMap<String, Object>();
    }

    @Override
    public void set_Cantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int get_Cantidad() {
        return this.cantidad;
    }

    @Override
    public void set_Posicion(int posicion) {
        this.posicion = posicion;
    }

    @Override
    public int get_Posicion() {
        return this.posicion;
    }

    @Override
    public void set_Conexion(Connection Conexion) {
        this.cnn = Conexion;
        this.conexionExterna = true;
    }

    @Override
    public Connection get_Conexion() {
        if (!isConexionExterna() && cnn != null) {
            return this.cnn;
        } else {
            return SQL.getConexion();
        }
    }

    @Override
    public boolean isConexionExterna() {
        return this.conexionExterna;
    }

    @Override
    public String get_Tabla() {
        return this.tabla;
    }

//    @Override
//    public void set_Tabla(String tabla) {
//        this.tabla = tabla;
//    }

    @Override
    public int get_Filas() {
        try{
        if(get_Cantidad() > 0 && get_Posicion() > 0 && this.filas != null)
            filas.last();
            return filas.getRow();
        }catch(SQLException e){e.printStackTrace();}
        return 0;
    }

    @Override
    public int execute(String sql, Object... parametros) {
        PreparedStatement cmd = null;
        Connection cnn = null;
        int res = 0;
        int index = 1 ;
        cnn = get_Conexion();
        ResultSet generatedKeys = null;
        try {
            cnn.setAutoCommit(false);
            cmd = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            for (Object object : parametros) {
                cmd.setObject(index++, object);
            }
            res = cmd.executeUpdate();
            ResultSetMetaData rsMetaData = cmd.getGeneratedKeys().getMetaData();
            index = 1;
            while(cmd.getGeneratedKeys().next())
            autoincrement.put(rsMetaData.getColumnLabel(index++),cmd.getGeneratedKeys().getString(1));
            cnn.commit();
            cmd.close();
        } catch (SQLException e) {
            cnn.rollback();
            e.printStackTrace();
        } finally {
            try {
                cnn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return res;
        }
    }

    @Override
    public int save() {
        try {
            Columna col = null;
            Field cm = null;
             for (Field campo : this.getClass().getDeclaredFields()) {
                 col = campo.getAnnotation(Columna.class);
                if (col == null) {
                    continue;
                }
                if(col.ClavePrimaria()){
                    cm = campo;
                    break;
                }
             }
             cm.setAccessible(true);
             String sql = "SELECT * FROM " + get_Tabla() + " WHERE " +cm.getName()+ " = ?";
             ArrayList<Object> parametros = new ArrayList<Object>();
             parametros.add(cm.get(this));
             this.select(sql, parametros.toArray());
             
             if(this.getNum_row()>0)
                 return update();
             else
                 return insert();
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int insert() {
        int res = 1, i;
        Object auto_id; 
        ResultSet r = null;
        cnn = get_Conexion();
        ArrayList<Object> parametros = new ArrayList<Object>();
        Columna col;
        int ind = 0;

        for (Field campo : this.getClass().getDeclaredFields()) {
            campo.setAccessible(true);
            col = campo.getAnnotation(Columna.class);
            if (col == null || col.AutoNumerico()) {
                continue;
            }
            try {
               // System.out.println(campo.get(this));
                parametros.add(campo.get(this));
            } catch (IllegalAccessException e) {
            }
        }
        System.out.println(getSQLInsert());
        res = execute(getSQLInsert(), parametros.toArray());
        System.out.println("desde respuesta " + res);        
        return res;
    }

    @Override
    public int delete() {
        Columna col;
        int ind = 1;
        ArrayList<Object> parametros = new ArrayList<Object>();
        for (Field campo : this.getClass().getDeclaredFields()) {
            col = campo.getAnnotation(Columna.class);
            if (col == null) {
                continue;
            }
            campo.setAccessible(true);
            if (col.ClavePrimaria()) {
                try {
                    parametros.add(campo.get(this));
                } catch (IllegalAccessException e) {
                };
            }
        }
        return execute(getSQLDelete(), parametros.toArray());
    }

    @Override
    public int update() {
        int res = 0;
        Object[] parametros = new Object[this.getClass().getDeclaredFields().length];
        System.out.println(parametros.length);
        try {
            Columna col;
            int ind = 0, num_campos = this.getClass().getDeclaredFields().length-1;
            for (Field campo : this.getClass().getDeclaredFields()) {
                col = campo.getAnnotation(Columna.class);
                if (col == null) {
                    continue;
                }
                campo.setAccessible(true);
                if (col.ClavePrimaria()) {
                    parametros[num_campos--] = campo.get(this);
                } else {
                    parametros[ind++] = campo.get(this);
                }
            }
            res = execute(getSQLUpdate(), parametros);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return res;
        }
    }

    public List<? extends ICrud> select() {
        int res = 0;
        ArrayList<Object> parametros = new ArrayList<Object>();
        try {
           // Columna col;
            int ind = 1;
            for (Field campo : this.getClass().getDeclaredFields()) {
               // col = campo.getAnnotation(Columna.class);
                campo.setAccessible(true);
                if (campo.get(this) != null) {
                    parametros.add(campo.get(this));
                }
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            return select(getSQLSelect(), parametros.toArray());
       }
    }
    
    public List<? extends ICrud> select(String sql,  Object... parametros) {
        Connection con;
        PreparedStatement cmd;
        ArrayList<Crud> registros = new ArrayList<Crud>();
        Crud reg;
        int ind = 1;
        con = get_Conexion();

        try {
          //  con.setAutoCommit(false);
            if (get_Cantidad() > 0) {
                cmd = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            } else {
                cmd = con.prepareStatement(sql);
            }
            for (Object object : parametros) {
                cmd.setObject(ind++, object);
            }
            System.out.println(cmd);
            filas = cmd.executeQuery();
        
            
            filas.last();
            
            setNum_row(filas.getRow());
            filas.beforeFirst();
            if (get_Cantidad() > 0) {
                filas.setFetchSize(get_Cantidad());
                filas.absolute(get_Posicion());
            }
            Columna col;
            
            while (filas.next()) {
                System.out.println("Encontro");
                reg = this.getClass().newInstance();
                for (Field campo : reg.getClass().getDeclaredFields()) {
                    col = campo.getAnnotation(Columna.class);
                    if (col == null) {
                        continue;
                    }
                    campo.setAccessible(true);
                    campo.set(reg, filas.getObject(this.getClass().getSimpleName().toLowerCase() + "$" + campo.getName()));
               }
               registros.add(reg);
            }
            //con.commit();
            filas.close();
            cmd.close();
            con.close();
        } catch (SQLException ex) {
            //con.rollback();
            ex.printStackTrace();
        } finally {
            return registros;
        }
    }

    public String getSQLDelete() {
        Columna col;
        StringBuilder SqlSelect = new StringBuilder();
        try {
            for (Field campo : this.getClass().getDeclaredFields()) {
                col = campo.getAnnotation(Columna.class);
                if (col == null || !col.ClavePrimaria()) {
                    continue;
                }

                campo.setAccessible(true);
                if (campo.get(this) != null) {
                    SqlSelect.append("" + campo.getName() + " = ? AND ");
                }

                if (col.ClavePrimaria()) {
                    continue;
                }
            }
        } catch (IllegalAccessException e) {
        }
        SqlSelect.delete(SqlSelect.length() - 4, SqlSelect.length());
        return "DELETE FROM " + get_Tabla()+ " WHERE " + SqlSelect.toString();
    }

    public String getSQLUpdate() {

        boolean Sw = false;
        StringBuilder sql = new StringBuilder();
        Columna col;
        StringBuilder SqlSelect = new StringBuilder();
        try {
            for (Field campo : this.getClass().getDeclaredFields()) {
                col = campo.getAnnotation(Columna.class);
                if (col == null) {
                    continue;
                }
                campo.setAccessible(true);
                if (campo.get(this) == null) {continue;}
                if (col.ClavePrimaria()) {
                    SqlSelect.append("" + campo.getName() + " = ? AND ");
                    continue;
                }
                sql.append(String.valueOf(campo.getName()) + "=?,");

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        sql.delete(sql.length() - 1, sql.length());
        SqlSelect.delete(SqlSelect.length() - 4, SqlSelect.length());

        return "UPDATE " + get_Tabla() + " SET " + sql.toString() + " WHERE " + SqlSelect.toString();
    }

    public String getSQLInsert() {
        StringBuilder sql = new StringBuilder();
        StringBuilder questions = new StringBuilder();
        Columna col;
        for (Field campo : this.getClass().getDeclaredFields()) {

            col = campo.getAnnotation(Columna.class);
            if (col == null || col.AutoNumerico()) {
                continue;
            }
            sql.append(String.valueOf(campo.getName()) + ",");
            questions.append("?,");


        }
        sql.delete(sql.length() - 1, sql.length());
        questions.delete(questions.length() - 1, questions.length());

        return "INSERT INTO " + get_Tabla() + " (" + sql.toString() + ") VALUES(" + questions.toString() + ")";
    }

    public String getSQLSelect() 
    {
        StringBuilder SqlSelect = new StringBuilder();
        StringBuilder SqlForeingKey = new StringBuilder();
        StringBuilder FieldsSqlForeingKey = new StringBuilder();
        
        String typeJoin = new String();
        HashMap<String,ArrayList> TablesForeingKey = new HashMap<String, ArrayList>();
        Columna col;
        try{
            for (Field campo : this.getClass().getDeclaredFields()) {
                col = campo.getAnnotation(Columna.class);
                campo.setAccessible(true);
                if (campo.get(this) != null) {
                    SqlSelect.append(this.getClass().getSimpleName().toLowerCase() + "." + campo.getName().toLowerCase() + " = ? AND ");
                }
                FieldsSqlForeingKey.append(this.getClass().getSimpleName().toLowerCase() + "." + campo.getName().toLowerCase() +
                        "\"" + this.getClass().getSimpleName().toLowerCase() + "$"  + campo.getName().toLowerCase() + "\",");
                if(!col.NameForeingKey().equals(""))
                {
                    String[] x = col.NameForeingKey().toString().split("\\.");   
                    Class BeanClass = Class.forName("co.edu.poli.ces3.crud.bean." + x[0]);
                    
                    if(TablesForeingKey.get(x[0]) == null)
                      TablesForeingKey.put(x[0], new ArrayList());
                                        
                    for(Field fielbean : BeanClass.getDeclaredFields())
                    {
                        FieldsSqlForeingKey.append(x[0] +"." + fielbean.getName().toLowerCase() + " AS "+
                                "\"" + x[0].toLowerCase()+ "$"  +fielbean.getName().toLowerCase() + "\",");
                    }
                    if(col.Requered())
                        typeJoin = "INNER JOIN";
                    else
                        typeJoin = "LEFT JOIN";
                   
                    if(TablesForeingKey.get(x[0]).size() > 0 )
                    {
                        if(!TablesForeingKey.get(x[0]).get(0).equals("INNER JOIN") && typeJoin.equals("INNER JOIN"))
                        {
                            TablesForeingKey.get(x[0]).set(0, typeJoin);
                        }
                        TablesForeingKey.get(x[0]).add(1, TablesForeingKey.get(x[0]).get(1) + " AND " + col.NameForeingKey().toLowerCase() +" = " + this.getClass().getSimpleName().toLowerCase() + "." + campo.getName().toLowerCase());
                    }else{
                        TablesForeingKey.get(x[0]).add(typeJoin);
                        TablesForeingKey.get(x[0]).add(col.NameForeingKey().toLowerCase() +" = " + this.getClass().getSimpleName().toLowerCase() + "." + campo.getName().toLowerCase());
                    }
                }
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        
        for (Map.Entry<String, ArrayList> entry : TablesForeingKey.entrySet()) {
            SqlForeingKey.append(" " + entry.getValue().get(0) + " " + entry.getKey() + " ON " + entry.getValue().get(1) );
            
        }
        if(SqlSelect.length()>0)
            SqlSelect.delete(SqlSelect.length() - 4, SqlSelect.length());
        System.out.println("-------");
        System.out.println(FieldsSqlForeingKey.toString());
        System.out.println("--------");        
        FieldsSqlForeingKey.delete(FieldsSqlForeingKey.length() - 1, FieldsSqlForeingKey.length());
        return "SELECT " + FieldsSqlForeingKey.toString() + " FROM " + get_Tabla() + SqlForeingKey.toString()  +(SqlSelect.length()>0?" WHERE ":"") + SqlSelect.toString();
    }
    
    public Object getElementAutoincrement(String position) {
        return autoincrement.get(position);
    }
}