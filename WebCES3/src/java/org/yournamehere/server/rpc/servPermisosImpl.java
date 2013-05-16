/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.server.rpc;

import co.edu.poli.ces3.crud.bean.tbl_permisos;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.yournamehere.client.model.modPerfil;
import org.yournamehere.client.model.modPermisos;
import org.yournamehere.client.model.modUsuario;

import org.yournamehere.client.rpc.servPermisos;

/**
 *
 * @author omesa
 */
public class servPermisosImpl extends RemoteServiceServlet implements servPermisos {

    @Override
    public Integer guardar(modPermisos u) {
        tbl_permisos p = new tbl_permisos();
        p.setId_perfil(u.getId_perfil());
        p.setId_usuario(u.getId_usuario());
        return p.insert();
    }

    @Override
    public Integer modificar(modPermisos u) {
        tbl_permisos p = new tbl_permisos();
        p.setId_perfil(u.getId_perfil());
        p.setId_usuario(u.getId_usuario());
        return p.update();
    }

    @Override
    public Integer eliminar(Integer id_perfil, Integer id_usuario) {
        tbl_permisos p = new tbl_permisos();
        p.setId_perfil(id_perfil);
        p.setId_usuario(id_usuario);
        return p.delete();
    }

    @Override
    public PagingLoadResult<modPermisos> consultar(PagingLoadConfig config) {
        tbl_permisos pm = new tbl_permisos();
        pm.set_Cantidad(config.getLimit());
        pm.set_Posicion(config.getOffset());
        ArrayList<modPermisos> listArray = new ArrayList<modPermisos>();
        PagingLoadResultBean<modPermisos> elements = new PagingLoadResultBean<modPermisos>();
        for (tbl_permisos x: pm.select()) {
            modPermisos p = new modPermisos();
            p.setId_perfil(x.getId_perfil());
            p.setId_usuario(x.getId_usuario());
            p.setNombrePerfil(x.getPerfil().getNombre());
            p.setNombreUsuario(x.getUsuario().getNombre());
            p.setPerfil((modPerfil)getModObject(x.getPerfil(), "org.yournamehere.client.model.modPerfil"));
            p.setUsuario((modUsuario)getModObject(x.getUsuario(),"org.yournamehere.client.model.modUsuario"));
            listArray.add(p);
        }
        elements.setData(listArray);
        return elements;
    }

    public Object getModObject(Object ClassIcrud, String ClassMod)
    {
        Object ObjtM = null;
        try{
            ObjtM = Class.forName(ClassMod).newInstance();
            for (Field x : ClassIcrud.getClass().getDeclaredFields())
            {
                x.setAccessible(true);
                Field c = ObjtM.getClass().getDeclaredField(x.getName());
                c.setAccessible(true);
                c.set(ObjtM, x.get(ClassIcrud));
            }
        }catch(ClassNotFoundException ex){ex.printStackTrace();}
        catch(InstantiationException er){er.printStackTrace();} 
        catch (IllegalAccessException et) {et.printStackTrace();} 
        catch (NoSuchFieldException ex) {ex.printStackTrace();} 
        catch (SecurityException ex) {ex.printStackTrace(); }
       return ObjtM;
    }
    
}
