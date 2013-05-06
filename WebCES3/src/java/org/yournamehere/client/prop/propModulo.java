/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.prop;

import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.sql.Timestamp;
import org.yournamehere.client.model.modModulo;

/**
 *
 * @author oskar
 */
public interface propModulo extends PropertyAccess<propModulo>{
   
    @Editor.Path("id_modulo")
    ModelKeyProvider<modModulo> key();
    ValueProvider<modModulo,Integer> id_modulo();
    ValueProvider<modModulo,String> nombre();
    ValueProvider<modModulo,String> descripcion();
    ValueProvider<modModulo,Timestamp> creado();
    ValueProvider<modModulo,Boolean> activo();
    
}
