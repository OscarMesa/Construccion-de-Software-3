/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.poli.ces3.crud;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 *
 * @author thomas
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Columna {

    boolean ClavePrimaria() default false;
    boolean AutoNumerico() default false;
    boolean Requered() default false;
    String NameForeingKey(); 
}
