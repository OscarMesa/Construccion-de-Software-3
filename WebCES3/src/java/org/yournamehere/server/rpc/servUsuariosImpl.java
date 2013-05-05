/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.server.rpc;

import co.edu.poli.ces3.crud.bean.tbl_usuarios;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.yournamehere.client.modUsuario;

import org.yournamehere.client.rpc.servUsuarios;

/**
 *
 * @author oskar
 */
public class servUsuariosImpl extends RemoteServiceServlet implements servUsuarios {

    @Override
    public Integer guardar(modUsuario u) {
        tbl_usuarios us = new tbl_usuarios();
        us.setApellido(u.getApellido());
        us.setId_usuario(u.getId_usuario());
        us.setNombre(u.getNombre());
        us.setClave(u.getClave());
        us.setUsuario(u.getUsuario());
        return us.insert();
    }

    @Override
    public Integer modificar(modUsuario u) {
        tbl_usuarios us = new tbl_usuarios();
        us.setApellido(u.getApellido());
        us.setId_usuario(u.getId_usuario());
        us.setNombre(u.getNombre());
        us.setClave(u.getClave());
        us.setUsuario(u.getUsuario());
        return us.update();
    }

    @Override
    public Integer eliminar(Integer id) {
        tbl_usuarios us = new tbl_usuarios();
        us.setId_usuario(id);
        return us.delete();
    }



 
}
