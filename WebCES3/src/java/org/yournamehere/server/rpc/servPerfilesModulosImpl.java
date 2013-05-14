/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.server.rpc;

import co.edu.poli.ces3.crud.bean.tbl_perfiles_modulos;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.sun.corba.se.impl.oa.poa.Policies;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.yournamehere.client.model.modModulo;
import org.yournamehere.client.model.modPerfil;
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

    @Override
    public PagingLoadResult<modPerilesModulos> consultar(PagingLoadConfig config) {
        tbl_perfiles_modulos pm = new tbl_perfiles_modulos();
        pm.set_Cantidad(config.getLimit());
        pm.set_Posicion(config.getOffset());
        ArrayList<modPerilesModulos> listArray = new ArrayList<modPerilesModulos>();
        PagingLoadResultBean<modPerilesModulos> elements = new PagingLoadResultBean<modPerilesModulos>();
        for (tbl_perfiles_modulos x: pm.select()) {
            modPerilesModulos p = new modPerilesModulos();
            p.setId_modulo(x.getId_modulo());
            p.setId_perfil(x.getId_perfil());
            p.setNombreModulo(x.getTbl_modulos().getNombre());
            p.setNombrePerfil(x.getTbl_perfiles().getNombre());
            p.setPerfil((modPerfil)getModObject(x.getTbl_perfiles(), "org.yournamehere.client.model.modPerfil"));
            p.setModulo((modModulo)getModObject(x.getTbl_modulos(),"org.yournamehere.client.model.modModulo"));
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
