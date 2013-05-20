/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client;

import org.yournamehere.client.prop.propUsuario;
import org.yournamehere.client.model.modUsuario;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.validation.client.impl.GwtBeanDescriptor;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.state.client.GridStateHandler;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sala302
 */
public class uiUsuario {
    private static final propUsuario props = GWT.create(propUsuario.class);
    private FramedPanel panel;
    private  Grid<modUsuario> grid;
    private ContentPanel root;
    private NumberField id_usuario;
    private TextField nombre;
    private TextField usuario;
    private TextField apellido;
    private PasswordField clave;
    private PagingLoader<PagingLoadConfig, PagingLoadResult<modUsuario>> loader ;
    
    public uiUsuario()
    {
        panel = new FramedPanel();
        VerticalLayoutContainer p = new VerticalLayoutContainer();
        HorizontalLayoutContainer h = new HorizontalLayoutContainer();
        HorizontalLayoutData layoutData = new HorizontalLayoutData(30, 25, new Margins(0,25,0,0));
        panel.add(p);
        panel.setHeadingText("Usuario");
        panel.setCollapsible(true);
        p.setHeight(400);
        p.add(initId_usuario());
        p.add(initNombre());
        p.add(initUsuario());
        p.add(initApellido());
        p.add(initCLave());
        p.add(initGridUsuario());
        panel.addButton(initBtn_Guardar());
        panel.addButton(initBtn_Editar());
        panel.addButton(initBtn_Eliminar());
        panel.setButtonAlign(BoxLayoutContainer.BoxLayoutPack.START);
        p.add(h);
        
        //
    }
    
    public Widget initBtn_Eliminar()
    {
       TextButton btnEditar;
       btnEditar = new TextButton("Eliminar"){
           @Override
           protected void onClick(Event event){
               super.onClick(event);
               
               Integer idUsuario=(Integer)id_usuario.getValue();
               
               SERVICES.getUsuariosAsync().eliminar(idUsuario, new AsyncCallback<Integer>() {

                   @Override
                   public void onFailure(Throwable caught) {
                       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   }

                   @Override
                   public void onSuccess(Integer result) {
                       Info.display("Respuesta", result.toString());
                       loader.load();
                   }
               });
           }
       };
       return btnEditar;
    }
    
    public Widget initBtn_Editar()
    {
       
       TextButton btnEditar;
       btnEditar = new TextButton("Editar"){
           @Override
           protected void onClick(Event event){
               super.onClick(event);
               final PromptMessageBox box = new PromptMessageBox("Id usuario", "Ingrese el Id del estudiante:");  
               box.show();
               box.addHideHandler(new HideHandler() {

                   @Override
                   public void onHide(HideEvent event) {
                        if (box.getHideButton() == box.getButtonById(PredefinedButton.OK.name())) {
                            modUsuario mod = new modUsuario();
                            mod.setApellido(apellido.getValue());
                            mod.setId_usuario((Integer.parseInt(box.getValue())));
                            mod.setNombre(nombre.getValue());
                            mod.setClave(clave.getValue());
                            mod.setUsuario(usuario.getValue());
                            SERVICES.getUsuariosAsync().modificar(mod, new AsyncCallback<Integer>() {

                                @Override
                                public void onFailure(Throwable caught) {
                                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                }

                                @Override
                                public void onSuccess(Integer result) {
                                    Info.display("Respuesta", result.toString());
                                    loader.load();
                                }
                            });
                        } else if (box.getHideButton() == box.getButtonById(PredefinedButton.CANCEL.name())) {
                          box.hide();
                        }
                   }
               });
           }
       };
       return btnEditar;
    }
    public Widget initBtn_Guardar()
    {
       TextButton btnGuardar;
       btnGuardar = new TextButton("Guardar"){
           @Override
           protected void onClick(Event event){
               super.onClick(event);
               modUsuario mod = new modUsuario();
               mod.setApellido(apellido.getValue());
               mod.setId_usuario((Integer)id_usuario.getValue());
               mod.setNombre(nombre.getValue());
               mod.setClave(clave.getValue());
               mod.setUsuario(usuario.getValue());
               
               SERVICES.getUsuariosAsync().guardar(mod, new AsyncCallback<Integer>() {

                   @Override
                   public void onFailure(Throwable caught) {
                      Info.display("resultjjjjado", caught.getMessage()); //To change body of generated methods, choose Tools | Templates.
                   }

                   @Override
                   public void onSuccess(Integer result) {
                          Info.display("resultado", String.valueOf(result));
                          loader.load();
                   }
               });
           }
       };
       return btnGuardar;
    }
    
    public Widget initUsuario(){
        usuario = new TextField();
        usuario.setText("Usuario");
        usuario.setEmptyText("Usuario");
        usuario.setAllowBlank(false);
        return new FieldLabel(usuario,"Usuario");
    }
    public Widget initId_usuario()
    {
        id_usuario = new NumberField(new NumberPropertyEditor.IntegerPropertyEditor());
        id_usuario.setAllowDecimals(true);
        id_usuario.setAllowNegative(false);
        id_usuario.setAllowBlank(false);
        id_usuario.setEmptyText("Identificacion");
        
        return new FieldLabel(id_usuario, "ID");
    }
    
    private Widget initNombre()
    {
        nombre = new TextField();
        nombre.setAllowBlank(false);
        nombre.setEmptyText("Ingrese el nombre");
        return new FieldLabel(nombre, "Nombre");
    }
    private Widget initApellido()
    {
        apellido = new TextField();
        apellido.setEmptyText("Ingrese el apellido");
        return new FieldLabel(apellido, "Apellido");
    }
    
    private Widget initCLave()
    {
        clave = new PasswordField();
        clave.setEmptyText("Ingrese contraseña");
        clave.setAllowBlank(false);
        return new FieldLabel(clave, "CLave");
    }
    
    public Widget initGridUsuario() {
       
      RpcProxy<PagingLoadConfig, PagingLoadResult<modUsuario>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<modUsuario>>(){

        @Override
        public void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<modUsuario>> callback) {
           SERVICES.getUsuariosAsync().consultar(loadConfig,callback);
        }

      };
      
      final propUsuario props = GWT.create(propUsuario.class);
      ColumnConfig<modUsuario, Integer> id_usuario = new ColumnConfig<modUsuario, Integer>(props.id_usuario(), 50, "Identificacion");
      ColumnConfig<modUsuario, String> nombre = new ColumnConfig<modUsuario, String>(props.nombre(), 100, "Nombre");
      ColumnConfig<modUsuario, String> apellido = new ColumnConfig<modUsuario, String>(props.apellido(), 75, "Apellido");
      ColumnConfig<modUsuario, String> usuario = new ColumnConfig<modUsuario, String>(props.usuario(), 75, "Usuario");
      ColumnConfig<modUsuario, String> password = new ColumnConfig<modUsuario, String>(props.clave(), 75, "Clave");
       
      List<ColumnConfig<modUsuario, ?>> l = new ArrayList<ColumnConfig<modUsuario, ?>>();
      l.add(id_usuario);
      l.add(nombre);
      l.add(apellido);
      l.add(usuario);
      l.add(password);
      
      ColumnModel<modUsuario> cm = new ColumnModel<modUsuario>(l);
      ListStore<modUsuario> store = new ListStore<modUsuario>(props.key());
      
      loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<modUsuario>>(proxy);
      loader.addLoadHandler(
      new LoadResultListStoreBinding<PagingLoadConfig, modUsuario, PagingLoadResult<modUsuario>>(store));
      
      
      
      grid = new Grid<modUsuario>(store, cm);
      grid.getView().setAutoExpandColumn(nombre);

 
      grid.setColumnReordering(true);
      grid.getView().setStripeRows(true);
      grid.getView().setColumnLines(true);
      
//      GridStateHandler<modUsuario> state = new GridStateHandler<modUsuario>(grid);
//      state.loadState();
      
      PagingToolBar toolBar = new PagingToolBar(3);
      toolBar.bind(loader);
      loader.load();
      
      VerticalLayoutContainer contenedor = new VerticalLayoutContainer();
      contenedor.setBorders(true);
      contenedor.add(grid);
      contenedor.add(toolBar);
      
      return contenedor;
    }

        public FramedPanel getPanel() {
        return panel;
    }

    public void setPanel(FramedPanel panel) {
        this.panel = panel;
    }

    public NumberField getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(NumberField id_usuario) {
        this.id_usuario = id_usuario;
    }

    public TextField getNombre() {
        return nombre;
    }

    public void setNombre(TextField nombre) {
        this.nombre = nombre;
    }

    public TextField getApellido() {
        return apellido;
    }

    public void setApellido(TextField apellido) {
        this.apellido = apellido;
    }

    public PasswordField getClave() {
        return clave;
    }

    public void setClave(PasswordField password) {
        this.clave = password;
    }

    public TextField getUsuario() {
        return usuario;
    }

    public void setUsuario(TextField usuario) {
        this.usuario = usuario;
    }
    
}
