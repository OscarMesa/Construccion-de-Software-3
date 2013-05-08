/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.prop;

import org.yournamehere.client.model.modUsuario;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 *
 * @author sala302
 */
public interface propUsuario extends PropertyAccess<modUsuario> {

    @Path("Id_usuario")
    ModelKeyProvider<modUsuario> key();

    ValueProvider<modUsuario, Integer> Id_usuario();

    ValueProvider<modUsuario, String> Nombre();

    ValueProvider<modUsuario, String> Apellido();

    ValueProvider<modUsuario, String> Usuario();

    ValueProvider<modUsuario, String> Clave();
}
