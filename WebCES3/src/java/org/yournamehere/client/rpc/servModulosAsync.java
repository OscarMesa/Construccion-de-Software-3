/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import org.yournamehere.client.model.modModulo;

/**
 *
 * @author oskar
 */
public interface servModulosAsync {

    public void guardar(modModulo u, AsyncCallback<Integer> asyncCallback);
    public void modificar(modModulo u, AsyncCallback<Integer> asyncCallback);
    public void eliminar(Integer id, AsyncCallback<Integer> asyncCallback);
    public void obtenerModulos(AsyncCallback<ArrayList<modModulo>> asyncCallback);
}
