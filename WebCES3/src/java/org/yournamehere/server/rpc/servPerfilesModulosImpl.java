/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.server.rpc;

import co.edu.poli.ces3.crud.bean.tbl_perfiles_modulos;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.yournamehere.client.model.modPerilesModulos;
import org.yournamehere.client.rpc.servPerfilesModulos;

/**
 *
 * @author oskar
 */
public class servPerfilesModulosImpl extends RemoteServiceServlet implements servPerfilesModulos {

    @Override
    public Integer guardar(modPerilesModulos u) {
        tbl_perfiles_modulos p = new tbl_perfiles_modulos();
        p.setId_modulo(u.getId_modulo());
        p.setId_perfil(u.getId_perfil());
        return p.insert();
    }

    @Override
    public Integer modificar(modPerilesModulos u) {
        tbl_perfiles_modulos p = new tbl_perfiles_modulos();
        p.setId_modulo(u.getId_modulo());
        p.setId_perfil(u.getId_perfil());
        return p.update();
    }

    @Override
    public Integer eliminar(Integer id_modulo, Integer id_perfil) {
        tbl_perfiles_modulos p = new tbl_perfiles_modulos();
        p.setId_modulo(id_modulo);
        p.setId_perfil(id_perfil);
        return p.delete();
    }

}
