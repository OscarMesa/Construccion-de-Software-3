/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.server.rpc;

import co.edu.poli.ces3.crud.bean.tbl_modulos;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import java.util.ArrayList;
import org.yournamehere.client.model.modModulo;

import org.yournamehere.client.rpc.servModulos;

/**
 *
 * @author oskar
 */
public class servModulosImpl extends RemoteServiceServlet implements servModulos {

    @Override
    public Integer guardar(modModulo u) {
        tbl_modulos modulo = new tbl_modulos();
        modulo.setActivo(u.getActivo());
        modulo.setCreado(u.getCreado());
        modulo.setDescripcion(u.getDescripcion());
        modulo.setId_modulo(u.getId_modulo());
        modulo.setNombre(u.getNombre());
        
        if(modulo.insert()>0)
            return Integer.parseInt(modulo.getElementAutoincrement("id_modulo").toString());
        else
            return 0;
    }

    @Override
    public Integer modificar(modModulo u) {
        tbl_modulos modulo = new tbl_modulos();
        modulo.setActivo(u.getActivo());
        modulo.setCreado(u.getCreado());
        modulo.setDescripcion(u.getDescripcion());
        modulo.setId_modulo(u.getId_modulo());
        modulo.setNombre(u.getNombre());
        return modulo.update();
    }

    @Override
    public Integer eliminar(Integer id) {
        tbl_modulos modulo = new tbl_modulos();
        modulo.setId_modulo(id);
        return modulo.delete();
    }

    @Override
    public PagingLoadResult<modModulo> obtenerModulos(PagingLoadConfig config) {
        tbl_modulos t = new tbl_modulos();
        t.set_Cantidad(config.getLimit());
        t.set_Posicion(config.getOffset());
        ArrayList<tbl_modulos> list = (t.select()).get("tbl_modulos");
        list.remove(0);
        ArrayList<modModulo> modulos = new ArrayList<modModulo>();
        PagingLoadResultBean<modModulo> elements = new PagingLoadResultBean<modModulo>();
        for (tbl_modulos x : list) {
            modModulo a = new modModulo();
            a.setId_modulo(x.getId_modulo());
            a.setNombre(x.getNombre());
            a.setActivo(x.getActivo());
            a.setCreado(x.getCreado());
            a.setDescripcion(x.getDescripcion());
            modulos.add(a);
        }
        elements.setData(modulos);
        return elements; 
    }

   
}
