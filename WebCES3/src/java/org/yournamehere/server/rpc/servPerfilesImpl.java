/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.server.rpc;

import co.edu.poli.ces3.crud.bean.tbl_perfiles;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
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
}
