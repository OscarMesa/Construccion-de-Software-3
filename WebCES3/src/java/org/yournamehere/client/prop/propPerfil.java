/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.prop;

import org.yournamehere.client.model.modUsuario;
import org.yournamehere.client.model.modPerfil;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 *
 * @author oskar
 */
public interface propPerfil extends PropertyAccess<propPerfil>{
    
    @Path("id_perfil")
    ModelKeyProvider<modUsuario> key();

    ValueProvider<modPerfil, Integer> id_perfil();

    ValueProvider<modPerfil, String> nombre();

    ValueProvider<modPerfil, String> descripcion();

    ValueProvider<modPerfil, String> activo();
    
}
