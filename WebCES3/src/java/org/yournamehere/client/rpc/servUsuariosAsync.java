/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import org.yournamehere.client.model.modUsuario;

/**
 *
 * @author oskar
 */
public interface servUsuariosAsync {

       public void guardar(modUsuario u, AsyncCallback<Integer> asyncCallback);
       public void modificar(modUsuario u, AsyncCallback<Integer> asyncCallback);
       public void eliminar(Integer id, AsyncCallback<Integer> asyncCallback);
       public void consultar(PagingLoadConfig cfg, AsyncCallback<PagingLoadResult<modUsuario>> asyncCallback);
}//, 
