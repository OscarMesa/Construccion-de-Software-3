/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import org.yournamehere.client.model.modPerilesModulos;

/**
 *
 * @author oskar
 */
public interface servPerfilesModulosAsync {
    public void guardar(modPerilesModulos u, AsyncCallback<Integer> asyncCallback);
    public void modificar(modPerilesModulos u, AsyncCallback<Integer> asyncCallback);
    public void eliminar(Integer id_modulo, Integer id_perfil, AsyncCallback<Integer> asyncCallback);      
    public void consultar(PagingLoadConfig config, AsyncCallback<PagingLoadResult<modPerilesModulos>> asyncCallback);
}
