/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.server.rpc;

import co.edu.poli.ces3.crud.bean.tbl_perfiles;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import java.util.ArrayList;
import org.yournamehere.client.model.modPerfil;

import org.yournamehere.client.rpc.servPerfiles;

/**
 *
 * @author oskar
 */
public class servPerfilesImpl extends RemoteServiceServlet implements servPerfiles {

    @Override
    public Integer guardar(modPerfil u) {
        tbl_perfiles perfil = new tbl_perfiles();
        perfil.setActivo(u.getActivo());
        perfil.setDescripcion(u.getDescripcion());
        perfil.setId_perfil(u.getId_perfil());
        perfil.setNombre(u.getNombre());
        if(perfil.insert()>0)
            return Integer.parseInt(perfil.getElementAutoincrement("id_perfil").toString());
        else
            return 0;
    }

        @Override
    public Integer modificar(modPerfil u) {
        tbl_perfiles perfil = new tbl_perfiles();
        perfil.setActivo(u.getActivo());
        perfil.setDescripcion(u.getDescripcion());
        perfil.setId_perfil(u.getId_perfil());
        perfil.setNombre(u.getNombre());
        return perfil.update();
    }

    @Override
    public Integer eliminar(Integer id) {
        tbl_perfiles perfil = new tbl_perfiles();
        perfil.setId_perfil(id);
        return perfil.delete();
    }
    
    @Override
    public PagingLoadResult<modPerfil> obtenerPerfiles(PagingLoadConfig config) {
        tbl_perfiles t = new tbl_perfiles();
        t.set_Cantidad(config.getLimit());
        t.set_Posicion(config.getOffset());
        ArrayList<tbl_perfiles> list = (t.select()).get("tbl_perfiles");
        list.remove(0);
        ArrayList<modPerfil> elements = new ArrayList<modPerfil>();
        for (tbl_perfiles x : list) {
            modPerfil a = new modPerfil();
            a.setId_perfil(x.getId_perfil());
            a.setNombre(x.getNombre());
            elements.add(a);
        }
        
         PagingLoadResultBean<modPerfil> x = new PagingLoadResultBean<modPerfil>(elements, config.getLimit(), config.getOffset());
        
         return x;  
    }
}
