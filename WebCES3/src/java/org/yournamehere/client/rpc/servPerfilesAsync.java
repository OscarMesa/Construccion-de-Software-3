/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import java.util.ArrayList;
import org.yournamehere.client.model.modPerfil;
import org.yournamehere.client.model.modUsuario;

/**
 *
 * @author oskar
 */
public interface servPerfilesAsync {

       public void guardar(modPerfil u, AsyncCallback<Integer> asyncCallback);
       public void modificar(modPerfil u, AsyncCallback<Integer> asyncCallback);
       public void eliminar(Integer id, AsyncCallback<Integer> asyncCallback);
       public void obtenerPerfiles(PagingLoadConfig cfg, AsyncCallback<PagingLoadResult<modPerfil>> asyncCallback);
}
