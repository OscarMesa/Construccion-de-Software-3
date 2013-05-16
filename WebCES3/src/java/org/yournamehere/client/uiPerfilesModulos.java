/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.ButtonCell;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.ModalPanel;
import com.sencha.gxt.widget.core.client.Popup;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.HeaderClickEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridRowEditing;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.yournamehere.client.model.modModulo;
import org.yournamehere.client.model.modPerfil;
import org.yournamehere.client.model.modPerilesModulos;
import org.yournamehere.client.prop.propModulo;
import org.yournamehere.client.prop.propPerfil;
import org.yournamehere.client.prop.propPerfilesModulos;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.impl.ImageResourcePrototype;
import com.google.gwt.uibinder.attributeparsers.SafeUriAttributeParser;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.CellSelectionModel;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
/**
 *
 * @author oskar
 */
interface MyResources extends ClientBundle {
            public static final MyResources INSTANCE = GWT.create(org.yournamehere.client.MyResources.class);

            @ClientBundle.Source("delete.png")
            ImageResource logo();
}   

public abstract class uiPerfilesModulos {
    private ComboBox<modPerfil> id_perfil;
    private ComboBox<modModulo> id_modulo;
    private FramedPanel panel;
    
    private PagingLoader<PagingLoadConfig, PagingLoadResult<modPerilesModulos>> loader;
    private ListStore<modPerilesModulos> store;
    
    private final propModulo propModulo;
    private ListStore<modModulo> ListModulos;
    private PagingLoader<PagingLoadConfig, PagingLoadResult<modModulo>> loaderModulos;
  
    protected Grid<modPerilesModulos> grid;
    private final propPerfil propPerfiles;
    private ListStore<modPerfil> Listperfiles;
    private PagingLoader<PagingLoadConfig, PagingLoadResult<modPerfil>> loaderPerfiles;
    {
        propModulo = GWT.create(propModulo.class);
        propPerfiles = GWT.create(propPerfil.class);
    }
    
    /**
     *
     */
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
        
        //ColumnConfig<modPerilesModulos,String> nombrePerfil = new ColumnConfig<modPerilesModulos, String>(props.nombrePerfil(),300,"Perfil");
        ColumnConfig<modPerilesModulos,String> nombrePerfil = new ColumnConfig<modPerilesModulos, String>(props.nombrePerfil(),300,"Perfil");
        ColumnConfig<modPerilesModulos,String> nombreModulo = new ColumnConfig<modPerilesModulos, String>(props.nombreModulo(),300,"Modulo");
        ColumnConfig<modPerilesModulos,String> eliminar = new ColumnConfig<modPerilesModulos, String>(props.eliminar(),50,"Eliminar");
        
       ButtonCell button = new ButtonCell();
       button.setIcon(MyResources.INSTANCE.logo());
       button.addSelectHandler(new SelectHandler() {
 
            @Override
            public void onSelect(SelectEvent event) {
              Context c = event.getContext();
              int row = c.getIndex();
              modPerilesModulos p = store.get(row);
              
              SERVICES.getPerfilesModulosAsync().eliminar(p.getId_modulo(), p.getId_perfil(), new AsyncCallback<Integer>() {

                  @Override
                  public void onFailure(Throwable caught) {
                   Info.display("Error",caught.getMessage());
                  }

                  @Override
                  public void onSuccess(Integer result) {
                     Info.display("Error","Eliminado " + result.toString());
                     loader.load();
                  }
              });
            }
        });
        eliminar.setCell(button);
        List<ColumnConfig<modPerilesModulos,?>> list = new ArrayList<ColumnConfig<modPerilesModulos, ?>>();
        list.add(nombrePerfil);
        list.add(nombreModulo);
        list.add(eliminar);
        
        
        
        ColumnModel<modPerilesModulos> cm = new ColumnModel<modPerilesModulos>(list);
        
        store = new ListStore<modPerilesModulos>(props.key());
        
        loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<modPerilesModulos>>(proxy);
        
        loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, modPerilesModulos, PagingLoadResult<modPerilesModulos>>(store));
    
        grid = new Grid<modPerilesModulos>(store, cm);
        
//        grid.getView().setAutoExpandColumn(nombre);
        
        grid.setColumnReordering(true);
        grid.getView().setStripeRows(true);
        grid.getView().setColumnLines(true);

        loader.load();
        
        RpcProxy<PagingLoadConfig, PagingLoadResult<modModulo>> proxy1 = new RpcProxy<PagingLoadConfig, PagingLoadResult<modModulo>>(){

            @Override
            public void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<modModulo>> callback) {
             SERVICES.getModulosAsync().obtenerModulos(loadConfig,callback);
            }  
        };
        
        ComboBox<modModulo> Editid_modulo = new ComboBox<modModulo>(ListModulos,propModulo.value());
        Editid_modulo.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        
        ComboBox<modPerfil> Editid_perfiles = new ComboBox<modPerfil>(Listperfiles,propPerfiles.value());
        Editid_perfiles.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        
        Editid_perfiles.setPropertyEditor(new PropertyEditor<modPerfil>() {

            @Override
            public String render(modPerfil object) {
                     return object == null ? object.toString():object.getNombre();
            }

            @Override
            public modPerfil parse(CharSequence text) throws ParseException {
                modPerfil x = new modPerfil();
                x.setNombre(text.toString());
                return x;
            }
        });
        
        Editid_modulo.setPropertyEditor(new PropertyEditor<modModulo>() {

            @Override
            public String render(modModulo object) {
                   return object == null ? object.toString():object.getNombre();
            }

            @Override
            public modModulo parse(CharSequence text) throws ParseException {
                modModulo x = new modModulo();
                x.setNombre(text.toString());
                return x;
            }
        });
      //  grid.getView().setAutoExpandColumn(eliminar);
        // EDITING//
        final GridEditing<modPerilesModulos> editing = createGridEditing(grid);
        
        editing.addEditor(nombreModulo, new Converter<String,modModulo>() {

            @Override
            public String convertFieldValue(modModulo object) {
               return object.getNombre();
            }

            @Override
            public modModulo convertModelValue(String object) {
                modModulo g = new modModulo();
                g.setNombre(object);
                return g;
            }
        },Editid_modulo);
        
        editing.addEditor(nombrePerfil,new Converter<String, modPerfil>() {

            @Override
            public String convertFieldValue(modPerfil object) {
                return object.getNombre();
            }

            @Override
            public modPerfil convertModelValue(String object) {
                modPerfil c = new modPerfil();
                c.setNombre(object);
                return c;
            }
        },Editid_perfiles);
        
        PagingToolBar toolBar;
        toolBar = new PagingToolBar(3);
        toolBar.bind(loader);
        loader.load();
        
        VerticalLayoutContainer contenedor = new VerticalLayoutContainer();
        contenedor.setBorders(true);
        contenedor.add(grid, new VerticalLayoutData(1, -1));
        contenedor.add(toolBar,new VerticalLayoutData(1, -1));
        
         FramedPanel cp = new FramedPanel();
         cp.setHeadingText("Perfiles de los modulos");
       //  cp.setPixelSize(600,200);
        // cp.addStyleName("margin-10");
        
         cp.setWidget(contenedor);
        
         cp.setButtonAlign(BoxLayoutPack.CENTER);

         cp.addButton(new TextButton("Guardar cambios", new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                store.commitChanges();
            }
         }));
         
         
         
         
         return cp;
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
                        loader.load();
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
       
        ListModulos = new ListStore<modModulo>(propModulo.key());
        loaderModulos = new PagingLoader<PagingLoadConfig, PagingLoadResult<modModulo>>(proxy);
        loaderModulos.addLoadHandler(
        new LoadResultListStoreBinding<PagingLoadConfig, modModulo, PagingLoadResult<modModulo>>(ListModulos));

        loaderModulos.load();
        
        id_modulo = new ComboBox<modModulo>(ListModulos,propModulo.value());
        id_modulo.setEmptyText("Seleccione un perfil");
        id_modulo.setAllowBlank(false);
        id_modulo.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        
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
        
        Listperfiles = new ListStore<modPerfil>(propPerfiles.key());
        loaderPerfiles = new PagingLoader<PagingLoadConfig, PagingLoadResult<modPerfil>>(proxy);
        loaderPerfiles.addLoadHandler(
        new LoadResultListStoreBinding<PagingLoadConfig, modPerfil, PagingLoadResult<modPerfil>>(Listperfiles));

        loaderPerfiles.load();
        
        id_perfil = new ComboBox<modPerfil>(Listperfiles,propPerfiles.value());
        id_perfil.setEmptyText("Seleccione un perfil");
        id_perfil.setAllowBlank(false);
        id_perfil.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        
        return new FieldLabel(id_perfil, "Perfil");
    }

    public FramedPanel getPanel() {
        return panel;
    }

    public void setPanel(FramedPanel panel) {
        this.panel = panel;
    }
    
    protected abstract GridEditing<modPerilesModulos> createGridEditing(Grid<modPerilesModulos> grid);

}


class ImplementUIPerfilesModulos extends uiPerfilesModulos {
 
//  @Override
//  protected GridEditing<modPerilesModulos> createGridEditing(Grid<modPerilesModulos> editableGrid) {
//      GridRowEditing<modPerilesModulos> gre = new GridRowEditing<modPerilesModulos>(editableGrid);
//      return new GridInlineEditing<modPerilesModulos>(editableGrid);
//  }
  @Override
  protected GridEditing<modPerilesModulos> createGridEditing(Grid<modPerilesModulos> editableGrid) {
    return new GridInlineEditing<modPerilesModulos>(editableGrid);
  }
    
  @Override
  public Widget initGrid_PerfilesModulos() {
    Widget w = super.initGrid_PerfilesModulos();
    grid.setSelectionModel(new CellSelectionModel<modPerilesModulos>());
     
    grid.getColumnModel().getColumn(0).setHideable(false);
     
    return w;
  }
 
}
