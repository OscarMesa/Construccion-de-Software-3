/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.prop;

import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import org.yournamehere.client.model.modPerilesModulos;

/**
 *
 * @author oskar
 */
public interface propPerfilesModulos extends PropertyAccess<propModulo>{
 
    ModelKeyProvider<modPerilesModulos> id_perfil();
    
    ModelKeyProvider<modPerilesModulos> id_modulo();
}
