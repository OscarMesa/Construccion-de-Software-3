/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.yournamehere.client.model.modPerilesModulos;

/**
 *
 * @author oskar
 */
@RemoteServiceRelativePath("rpc/servperfilesmodulos")
public interface servPerfilesModulos extends RemoteService {
    
    public Integer guardar(modPerilesModulos u);
    
    public Integer modificar(modPerilesModulos u);
    
    public Integer eliminar(Integer id_modulo, Integer id_perfil);
}
