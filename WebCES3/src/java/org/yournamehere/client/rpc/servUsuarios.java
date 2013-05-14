/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import org.yournamehere.client.model.modUsuario;

/**
 *
 * @author oskar
 */
@RemoteServiceRelativePath("rpc/servusuarios")
public interface servUsuarios extends RemoteService {

    public Integer guardar(modUsuario u);
    
    public Integer modificar(modUsuario u);
    
    public Integer eliminar(Integer id);
    
    public PagingLoadResult<modUsuario> consultar(PagingLoadConfig config);
}
