/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.DatePicker;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.yournamehere.client.model.modModulo;
import org.yournamehere.client.prop.propModulo;

/**
 *
 * @author oskar
 */
public class uiModulo {
    private NumberField id_modulo;
    private TextField nombre;
    private TextArea descripcion;
    private DateField creado;
    private ComboBox<States> activo;
    private FramedPanel panel;
    private PagingLoader<PagingLoadConfig, PagingLoadResult<modModulo>> loader;

    interface StatesProperties extends PropertyAccess<States> {
        ModelKeyProvider<States> value();
        LabelProvider<States> LabelName();
    }
    
    public FramedPanel getPanel() {
        return panel;
    }

    public uiModulo() {
        panel = new FramedPanel();
        VerticalLayoutContainer p = new VerticalLayoutContainer();
        HorizontalLayoutContainer h = new HorizontalLayoutContainer();
        HorizontalLayoutContainer.HorizontalLayoutData layoutData = new HorizontalLayoutContainer.HorizontalLayoutData(30, 25, new Margins(0,25,0,0));
        panel.add(p);
        panel.setHeadingText("Modulos");
        panel.setCollapsible(true);
        p.setHeight(400);
        p.add(initId_perfil());
        p.add(initNombre());
        p.add(initDescripcion());
        p.add(initCreado());
        p.add(initActivo());
  
        panel.addButton(initBtn_Guardar());
        panel.addButton(initBtn_Editar());
        panel.addButton(initBtn_Eliminar());
        
        p.add(initGrid_Modulo());
        
        panel.setButtonAlign(BoxLayoutContainer.BoxLayoutPack.START);
        
    }
    
    public Widget initGrid_Modulo()
    {
        RpcProxy<PagingLoadConfig, PagingLoadResult<modModulo>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<modModulo>>(){

            @Override
            public void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<modModulo>> callback) {
                SERVICES.getModulosAsync().obtenerModulos(loadConfig, callback);
            }
            
        };
        
        final propModulo props = GWT.create(propModulo.class);
         
        ColumnConfig<modModulo,Integer> id_modulo = new ColumnConfig<modModulo, Integer>(props.id_modulo(),500,"Identificacion");
        ColumnConfig<modModulo,String> nombre= new ColumnConfig<modModulo, String>(props.nombre(), 400,"Nombre");
        ColumnConfig<modModulo,String> descripcion = new ColumnConfig<modModulo, String>(props.descripcion(),600, "Descripcion");
        ColumnConfig<modModulo,Timestamp> creado = new ColumnConfig<modModulo, Timestamp>(props.creado(),300,"Creado");
        ColumnConfig<modModulo,Boolean> activo = new ColumnConfig<modModulo, Boolean>(props.activo(), 300, "Estado");
        
        List<ColumnConfig<modModulo,?>> list = new ArrayList<ColumnConfig<modModulo, ?>>();
        list.add(id_modulo);
        list.add(nombre);
        list.add(descripcion);
        list.add(creado);
        list.add(activo);
        
        ColumnModel<modModulo> cm = new ColumnModel<modModulo>(list);
        
        ListStore<modModulo> store = new ListStore<modModulo>(props.key());
        
        loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<modModulo>>(proxy);
        
        loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, modModulo, PagingLoadResult<modModulo>>(store));
    
        Grid<modModulo> grid = new Grid<modModulo>(store, cm);
        
        grid.getView().setAutoExpandColumn(nombre);
        
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
                modModulo m = new modModulo();
                m.setActivo(activo.getValue().getValue());
                m.setCreado(new Timestamp(creado.getValue().getTime()));
                m.setDescripcion(descripcion.getValue());
                m.setId_modulo(Integer.parseInt(id_modulo.getValue().toString()));
                m.setNombre(nombre.getValue());
                SERVICES.getModulosAsync().guardar(m, new AsyncCallback<Integer>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        Info.display("Error", caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Integer result) {
                        Info.display("Respuesta", result.toString());
                        loader.load();
                    }
                });
            }
        };
        return guardar;
    }
    
    public Widget initBtn_Editar()
    {
        TextButton editar = new TextButton("Editar"){
            @Override
            protected void onClick(Event event){
                super.onClick(event);
                final PromptMessageBox box = new PromptMessageBox("Id modulo", "Ingrese el id del modulo a editar:");
                box.show();
                box.addHideHandler(new HideEvent.HideHandler() {

                    @Override
                    public void onHide(HideEvent event) {
                        if(box.getHideButton() == box.getButtonById(Dialog.PredefinedButton.OK.name()))
                        {
                                modModulo m = new modModulo();
                                m.setActivo(activo.getValue().getValue());
                                m.setCreado(new Timestamp(creado.getValue().getTime()));
                                m.setDescripcion(descripcion.getValue());
                                m.setId_modulo(Integer.parseInt(box.getValue()));
                                m.setNombre(nombre.getValue()); 
                                SERVICES.getModulosAsync().modificar(m, new AsyncCallback<Integer>() {

                                    @Override
                                    public void onFailure(Throwable caught) {
                                        Info.display("Error", caught.getMessage());
                                    }

                                    @Override
                                    public void onSuccess(Integer result) {
                                        Info.display("Respuesta",result.toString());
                                        loader.load();
                                    }
                                });
                        }else{
                            box.hide();
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
                final PromptMessageBox box = new PromptMessageBox("Id modulo", "Ingresar id del modulo");
                box.show();
                box.addHideHandler(new HideEvent.HideHandler() {

                    @Override
                    public void onHide(HideEvent event) {
                        if (box.getHideButton() == box.getButtonById(Dialog.PredefinedButton.OK.name())) {
                            Integer id = Integer.parseInt(box.getValue());
                            SERVICES.getModulosAsync().eliminar(id, new AsyncCallback<Integer>() {

                                @Override
                                public void onFailure(Throwable caught) {
                                    Info.display("Error", caught.getMessage());
                                }

                                @Override
                                public void onSuccess(Integer result) {
                                    Info.display("Respuesta", result.toString());
                                    loader.load();
                                }
                            });
                        }else{
                            box.hide();
                        }
                    }
                });
            }
        };
        return eliminar;
    }    
    public Widget initActivo()
    {
        uiModulo.StatesProperties props = GWT.create(uiModulo.StatesProperties.class);
        ListStore<States> states = new ListStore<States>(props.value());
        states.add(new States("Activo", Boolean.TRUE));
        states.add(new States("Inactivo", Boolean.FALSE));
        activo = new ComboBox<States>(states,props.LabelName());
        
        activo.setEmptyText("Seleccione un estado");
        activo.setTypeAhead(true);
        activo.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        
        return new FieldLabel(activo,"Estado");
    }
    public Widget initCreado(){
        creado = new DateField();
        creado.setAllowTextSelection(false);
        creado.setValue(new Date());
        
        return new FieldLabel(creado,"Creado");
    }
            
    public Widget initDescripcion()
    {
        descripcion = new TextArea();
        descripcion.setAllowBlank(false);
        descripcion.setEmptyText("Ingrese descripcion");
        descripcion.setTitle("oscar");
        return new FieldLabel(descripcion, "Descripcion");
    }
    
    
    public Widget initNombre()
    {
        nombre = new TextField();
        nombre.setAllowBlank(false);
        nombre.setEmptyText("Ingrese el nombre");
        return new FieldLabel(nombre, "Nombre");
    }
        
    public Widget initId_perfil()
    {
        id_modulo = new NumberField(new NumberPropertyEditor.IntegerPropertyEditor());
        id_modulo.setAllowDecimals(true);
        id_modulo.setAllowNegative(false);
        id_modulo.setAllowBlank(false);
        id_modulo.setEmptyText("Id modulo");
        return new FieldLabel(id_modulo, "ID");
    }
    public void setPanel(FramedPanel panel) {
        this.panel = panel;
    }
}

