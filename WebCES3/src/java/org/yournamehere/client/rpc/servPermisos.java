/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import org.yournamehere.client.model.modPermisos;

/**
 *
 * @author omesa
 */
@RemoteServiceRelativePath("rpc/servpermisos")
public interface servPermisos extends RemoteService {

    public Integer guardar(modPermisos u);
    
    public Integer modificar(modPermisos u);
    
    public Integer eliminar(Integer id_perfil, Integer id_usuario);
    
    public PagingLoadResult<modPermisos> consultar(PagingLoadConfig config);
}
