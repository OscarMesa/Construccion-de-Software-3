/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.yournamehere.client.model.modModulo;
import org.yournamehere.client.model.modPerfil;
import org.yournamehere.client.model.modPerilesModulos;
import org.yournamehere.client.prop.propModulo;
import org.yournamehere.client.prop.propPerfil;
import org.yournamehere.client.prop.propPerfilesModulos;

/**
 *
 * @author oskar
 */
public class uiPerfilesModulos {
    private ComboBox<modPerfil> id_perfil;
    private ComboBox<modModulo> id_modulo;
    private FramedPanel panel;


    public uiPerfilesModulos() {
        panel = new FramedPanel();
        VerticalLayoutContainer p = new VerticalLayoutContainer();
        HorizontalLayoutContainer h = new HorizontalLayoutContainer();
        HorizontalLayoutContainer.HorizontalLayoutData layoutData = new HorizontalLayoutContainer.HorizontalLayoutData(30, 25, new Margins(0,25,0,0));
        panel.add(p);
        
        
        p.add(initId_perfil());
        p.add(initId_modulo());
        p.add(initGrid_PerfilesModulos());
        panel.addButton(initBtn_Guardar());
        panel.addButton(initBtn_Editar());
        panel.addButton(initBtn_Eliminar());
        
        panel.setButtonAlign(BoxLayoutContainer.BoxLayoutPack.START);
        p.add(h);
        
    }
    
    public Widget initGrid_PerfilesModulos()
    {
        RpcProxy<PagingLoadConfig, PagingLoadResult<modPerilesModulos>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<modPerilesModulos>>(){

            @Override
            public void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<modPerilesModulos>> callback) {
                SERVICES.getPerfilesModulosAsync().consultar(loadConfig,callback);
            }
            
        };
        
        final propPerfilesModulos props = GWT.create(propPerfilesModulos.class);
        
        ColumnConfig<modPerilesModulos,String> nombrePerfil = new ColumnConfig<modPerilesModulos, String>(props.nombrePerfil(),300,"Perfil");
        ColumnConfig<modPerilesModulos,String> nombreModulo = new ColumnConfig<modPerilesModulos, String>(props.nombreModulo(),300,"Modulo");
        
        
        List<ColumnConfig<modPerilesModulos,?>> list = new ArrayList<ColumnConfig<modPerilesModulos, ?>>();
        list.add(nombrePerfil);
        list.add(nombreModulo);
        
        
        ColumnModel<modPerilesModulos> cm = new ColumnModel<modPerilesModulos>(list);
        
        ListStore<modPerilesModulos> store = new ListStore<modPerilesModulos>(props.key());
        
        PagingLoader<PagingLoadConfig, PagingLoadResult<modPerilesModulos>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<modPerilesModulos>>(proxy);
        
        loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, modPerilesModulos, PagingLoadResult<modPerilesModulos>>(store));
    
        Grid<modPerilesModulos> grid = new Grid<modPerilesModulos>(store, cm);
        
//        grid.getView().setAutoExpandColumn(nombre);
        
        grid.setColumnReordering(true);
        grid.getView().setStripeRows(true);
        grid.getView().setColumnLines(true);
        
        PagingToolBar toolBar = new PagingToolBar(3);
        toolBar.bind(loader);
        loader.load();
        
        VerticalLayoutContainer contenedor = new VerticalLayoutContainer();
        contenedor.setBorders(true);
        contenedor.add(grid);
        contenedor.add(toolBar);
        
        return contenedor;
    }
            
    
    public Widget initBtn_Guardar()
    {
        TextButton guardar = new TextButton("Guardar"){
            @Override
            protected void onClick(Event event){
                super.onClick(event);
                modPerilesModulos pm = new modPerilesModulos();
                pm.setId_modulo(id_modulo.getValue().getId_modulo());
                pm.setId_perfil(id_perfil.getValue().getId_perfil());
                
                SERVICES.getPerfilesModulosAsync().guardar(pm, new AsyncCallback<Integer>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        Info.display("Error",caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Integer result) {
                        Info.display("Respuesta",result.toString());
                    }
                });
    
            }
        };
        guardar.redraw();
        return guardar;
    }
    
    public Widget initBtn_Editar()
    {
        TextButton editar = new TextButton("Editar"){
           @Override
           protected void onClick(Event event){
               super.onClick(event);
               final PromptMessageBox box = new PromptMessageBox("Id perfil", "Ingrese el Id del perfil:");  
               final PromptMessageBox box1 = new PromptMessageBox("Id modulo", "Ingrese el Id del modulo:");  
               box.show();
               box.addHideHandler(new HideEvent.HideHandler() {
                   @Override
                   public void onHide(HideEvent event) {
                       if (box.getHideButton() == box.getButtonById(Dialog.PredefinedButton.OK.name()))
                       {
                           box1.show();
                       }else if (box.getHideButton() == box.getButtonById(Dialog.PredefinedButton.CANCEL.name())) {
                          box.hide();
                        }
                   }
               });
               
               box1.addHideHandler(new HideEvent.HideHandler() {

                   @Override
                   public void onHide(HideEvent event) {
                       if (box1.getHideButton() == box1.getButtonById(Dialog.PredefinedButton.OK.name()))
                       { 
                        modPerilesModulos p = new modPerilesModulos();
                        p.setId_modulo(id_modulo.getValue().getId_modulo());
                        p.setId_perfil(id_perfil.getValue().getId_perfil());
                        SERVICES.getPerfilesModulosAsync().modificar(p, new AsyncCallback<Integer>() {

                            @Override
                            public void onFailure(Throwable caught) {
                               Info.display("Respuesta",caught.getMessage());
                            }

                            @Override
                            public void onSuccess(Integer result) {
                                Info.display("Respuesta",result.toString());
                            }
                        });
                       }else if (box1.getHideButton() == box1.getButtonById(Dialog.PredefinedButton.CANCEL.name())) {
                          box1.hide();
                       }
                   }
               });
            }
          };
        return editar;
    }
    
    public Widget initBtn_Eliminar()
    {
        TextButton eliminar = new TextButton("Eliminar"){
           @Override
           protected void onClick(Event event){
               super.onClick(event);
               final PromptMessageBox box = new PromptMessageBox("Id perfil", "Ingrese el Id del perfil:");  
               final PromptMessageBox box1 = new PromptMessageBox("Id modulo", "Ingrese el Id del modulo:");  
               box.show();
               box.addHideHandler(new HideEvent.HideHandler() {
                   @Override
                   public void onHide(HideEvent event) {
                       if (box.getHideButton() == box.getButtonById(Dialog.PredefinedButton.OK.name()))
                       {
                           box1.show();
                       }else if (box.getHideButton() == box.getButtonById(Dialog.PredefinedButton.CANCEL.name())) {
                          box.hide();
                        }
                   }
               });
               
               box1.addHideHandler(new HideEvent.HideHandler() {

                   @Override
                   public void onHide(HideEvent event) {
                       if (box1.getHideButton() == box1.getButtonById(Dialog.PredefinedButton.OK.name()))
                       {
                        modPerilesModulos p = new modPerilesModulos();
                        p.setId_modulo(id_modulo.getValue().getId_modulo());
                        p.setId_perfil(id_perfil.getValue().getId_perfil());
                        SERVICES.getPerfilesModulosAsync().eliminar(Integer.parseInt(box.getValue()),Integer.parseInt(box1.getValue()), new AsyncCallback<Integer>() {

                            @Override
                            public void onFailure(Throwable caught) {
                               Info.display("Respuesta",caught.getMessage());
                            }

                            @Override
                            public void onSuccess(Integer result) {
                                Info.display("Respuesta",result.toString());
                            }
                        });
                       }else if (box1.getHideButton() == box1.getButtonById(Dialog.PredefinedButton.CANCEL.name())) {
                          box1.hide();
                       }
                   }
               });
            }
          };
        return eliminar;
    }
    public Widget initId_modulo()
    {
//        SERVICES.getModulosAsync().obtenerModulos(new AsyncCallback<ArrayList<modModulo>>() {
//
//            @Override
//            public void onFailure(Throwable caught) {
//                Info.display("Error", caught.getMessage());
//            }
//
//            @Override
//            public void onSuccess(ArrayList<modModulo> result) {
//                propModulo props = GWT.create(propModulo.class);
//                ListStore<modModulo> modulos = new ListStore<modModulo>(props.key());
//                modulos.addAll(result);
//                id_modulo.setStore(modulos);
//                id_modulo.redraw();
//            }
//        });
        RpcProxy<PagingLoadConfig, PagingLoadResult<modModulo>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<modModulo>>(){

            @Override
            public void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<modModulo>> callback) {
             SERVICES.getModulosAsync().obtenerModulos(loadConfig,callback);
            }  
        };
        
        
        final propModulo props = GWT.create(propModulo.class);
       
        ListStore<modModulo> modulos = new ListStore<modModulo>(props.key());
       
        PagingLoader<PagingLoadConfig, PagingLoadResult<modModulo>>
                loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<modModulo>>(proxy);
        loader.addLoadHandler(
        new LoadResultListStoreBinding<PagingLoadConfig, modModulo, PagingLoadResult<modModulo>>(modulos));

        loader.load();
        
        
        id_modulo = new ComboBox<modModulo>(modulos,props.value());
        id_modulo.setEmptyText("Seleccione un perfil");
        
        
        
        return new FieldLabel(id_modulo, "Modulo");
    }
    
    public Widget initId_perfil()
    {
        RpcProxy<PagingLoadConfig, PagingLoadResult<modPerfil>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<modPerfil>>(){

            @Override
            public void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<modPerfil>> callback) {
                SERVICES.getPerfilesAsync().obtenerPerfiles(loadConfig,callback);            
            }

        };
        final propPerfil props = GWT.create(propPerfil.class);
        ListStore<modPerfil> perfiles = new ListStore<modPerfil>(props.key());
        
        PagingLoader<PagingLoadConfig, PagingLoadResult<modPerfil>>
                loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<modPerfil>>(proxy);
        loader.addLoadHandler(
        new LoadResultListStoreBinding<PagingLoadConfig, modPerfil, PagingLoadResult<modPerfil>>(perfiles));

        loader.load();
        
        id_perfil = new ComboBox<modPerfil>(perfiles,props.value());

        id_perfil.setEmptyText("Seleccione un perfil");

        return new FieldLabel(id_perfil, "Perfil");
    }

    public FramedPanel getPanel() {
        return panel;
    }

    public void setPanel(FramedPanel panel) {
        this.panel = panel;
    }

}
