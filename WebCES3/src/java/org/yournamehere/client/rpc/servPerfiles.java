/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.ArrayList;
import org.yournamehere.client.model.modPerfil;

/**
 *
 * @author oskar
 */
@RemoteServiceRelativePath("rpc/servperfiles")
public interface servPerfiles extends RemoteService {

    public Integer guardar(modPerfil u);
    
    public Integer modificar(modPerfil u);
    
    public Integer eliminar(Integer id);
    
     public ArrayList<modPerfil> obtenerPerfiles();
}
