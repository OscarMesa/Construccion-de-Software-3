/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import org.yournamehere.client.model.modPerfil;
import org.yournamehere.client.model.modPermisos;
import org.yournamehere.client.model.modUsuario;

/**
 *
 * @author omesa
 */
public interface servPermisosAsync {

       public void guardar(modPermisos u, AsyncCallback<Integer> asyncCallback);
       public void modificar(modPermisos u, AsyncCallback<Integer> asyncCallback);
       public void eliminar(Integer id_perfil, Integer id_usuario, AsyncCallback<Integer> asyncCallback);
       public void consultar(PagingLoadConfig config, AsyncCallback<PagingLoadResult<modPermisos>> asyncCallback);
}
