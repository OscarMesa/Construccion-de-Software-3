/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.server.rpc;

import co.edu.poli.ces3.crud.bean.tbl_usuarios;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import java.util.ArrayList;
import org.yournamehere.client.model.modUsuario;

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
        if(us.insert()>0)
            return Integer.parseInt(us.getElementAutoincrement("id_usuario").toString());
        else
            return 0;
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

    @Override
    public PagingLoadResult<modUsuario> consultar(PagingLoadConfig config) {
        tbl_usuarios usu = new tbl_usuarios();
        usu.set_Cantidad(config.getLimit());
        usu.set_Posicion(config.getOffset());
        
        ArrayList<modUsuario> list = new ArrayList<modUsuario>();
        ArrayList<tbl_usuarios> v = usu.select().get("tbl_usuarios");
        v.remove(0);
        for (tbl_usuarios xx : v) {
            modUsuario h = new modUsuario();
            h.setApellido(xx.getApellido());
            h.setClave(xx.getClave());
            h.setId_usuario(xx.getId_usuario());
            h.setNombre(xx.getNombre());
            h.setUsuario(xx.getUsuario());
            list.add(h);
        }
        
        PagingLoadResultBean<modUsuario> x = new PagingLoadResultBean<modUsuario>(list, config.getLimit(), config.getOffset());
        
        return x;
    }


 
}
