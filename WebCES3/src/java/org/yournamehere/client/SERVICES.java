package org.yournamehere.client;

import org.yournamehere.client.rpc.servPermisosAsync;
import com.google.gwt.core.client.GWT;
import org.yournamehere.client.rpc.servModulos;
import org.yournamehere.client.rpc.servModulosAsync;

import org.yournamehere.client.rpc.servPerfiles;
import org.yournamehere.client.rpc.servPerfilesAsync;
import org.yournamehere.client.rpc.servPerfilesModulos;
import org.yournamehere.client.rpc.servPerfilesModulosAsync;
import org.yournamehere.client.rpc.servPermisos;

import org.yournamehere.client.rpc.servUsuarios;
import org.yournamehere.client.rpc.servUsuariosAsync;

/**
 *
 * @author oskar
 */
public class SERVICES {
    
        public static servUsuariosAsync getUsuariosAsync() {
            return GWT.create(servUsuarios.class);
        }
        
        public static servPerfilesAsync getPerfilesAsync(){
            return GWT.create(servPerfiles.class);
        }
        
        public static servModulosAsync getModulosAsync(){
            return GWT.create(servModulos.class);
        }
        
        public static servPerfilesModulosAsync getPerfilesModulosAsync(){
            return GWT.create(servPerfilesModulos.class);
        }
        
        public static servPermisosAsync getPermisosAsync(){
            return GWT.create(servPermisos.class);
        }
    
}
