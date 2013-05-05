package org.yournamehere.client;

import com.google.gwt.core.client.GWT;
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
        
    
    
}
