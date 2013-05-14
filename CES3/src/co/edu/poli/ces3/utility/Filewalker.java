package co.edu.poli.ces3.utility;

import java.io.File;

public class Filewalker {
    public static boolean sw;
    
    public Filewalker()
    {
        sw = true;
    }
    
    public static String toGoDIr( String path, String nameClass ) {
        
        File root = new File( path );
        File[] list = root.listFiles();
        String x = "";
        for ( File f : list ) {
            if(sw)
            {
                if ( f.isDirectory()) {
                   x += f.getName()+ "."+toGoDIr( f.getAbsolutePath(),nameClass );
                }
                else {
                    //System.out.println(f.getName());
                    if(f.getName().equals(nameClass))
                    {
                        sw = false;
                        x = "";
                    }else{
                        x = "";
                    }
                }
            }
        }
        return x;
    }
    
    public static String getPackages(String path, String cl)
    {
        
        StringBuilder z = new StringBuilder(toGoDIr(path + "/build/classes", cl + ".class"));
        return z.delete(z.length()-1, z.length()).toString();
    }
    
    public static void main(String... args)
    {
        
        File file = new File("");   //Dummy file
        String  path=file.getAbsolutePath();
        Filewalker x = new Filewalker();
        System.out.println(x.getPackages(path, "tbl_paginas"));
        
    }
}