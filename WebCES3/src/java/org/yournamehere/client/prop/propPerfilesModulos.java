/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.prop;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import org.yournamehere.client.model.modModulo;
import org.yournamehere.client.model.modPerfil;
import org.yournamehere.client.model.modPerilesModulos;

/**
 *
 * @author oskar
 */
public interface propPerfilesModulos extends PropertyAccess<modPerilesModulos>{
 
    @Path("id_perfil")
    ModelKeyProvider<modPerilesModulos> key();
    
    ValueProvider<modPerilesModulos, Integer> id_perfil();
    
    ValueProvider<modPerilesModulos, Integer> id_modulo();

    ValueProvider<modPerilesModulos, String> nombrePerfil();
    
    ValueProvider<modPerilesModulos, String> nombreModulo();
    
    ValueProvider<modPerilesModulos, modModulo> modulo();
    
    ValueProvider<modPerilesModulos, modPerfil> perfil();
}
