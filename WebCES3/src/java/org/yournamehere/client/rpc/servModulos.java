/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.yournamehere.client.model.modModulo;

/**
 *
 * @author oskar
 */
@RemoteServiceRelativePath("rpc/servmodulos")
public interface servModulos extends RemoteService {

    public Integer guardar(modModulo u);
    
    public Integer modificar(modModulo u);
    
    public Integer eliminar(Integer id);
}
