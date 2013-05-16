/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.prop;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import org.yournamehere.client.model.modPerfil;
import org.yournamehere.client.model.modPermisos;
import org.yournamehere.client.model.modUsuario;

/**
 *
 * @author omesa
 */
public interface propPermiso extends PropertyAccess<modPermisos> {
    @Path("id_perfil")
    ModelKeyProvider<modPermisos> key();
    
    ValueProvider<modPermisos, Integer> id_perfil();
    
    ValueProvider<modPermisos, Integer> id_usuario();

    ValueProvider<modPermisos, String> nombrePerfil();
    
    ValueProvider<modPermisos, String> nombreUsuario();
    
    ValueProvider<modPermisos, modPerfil> perfil();
    
    ValueProvider<modPermisos, modUsuario> usuario();
    
    ValueProvider<modPermisos, String> eliminar();
}
