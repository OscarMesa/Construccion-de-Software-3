/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.state.client.GridStateHandler;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
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

    public TextField getUsuario() {
        return usuario;
    }

    public void setUsuario(TextField usuario) {
        this.usuario = usuario;
    }
    private TextField usuario;
    private TextField apellido;
    private PasswordField clave;

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
    
    public uiUsuario()
    {
        panel = new FramedPanel();
        VerticalLayoutContainer p = new VerticalLayoutContainer();
        panel.add(p);
        panel.setHeadingText("Usuario");
        panel.setCollapsible(true);
        p.setHeight(400);
        p.add(initId_usuario());
        p.add(initNombre());
        p.add(initApellido());
        p.add(initCLave());
        p.add(initBtn_Guardar());
        p.add(initBtn_Editar());
        p.add(initBtn_Eliminar());
        p.add(initUsuario());
        //p.add(initGridUsuario());
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
                   }
               });
           }
       };
       return new FieldLabel(btnEditar);
    }
    
    public Widget initBtn_Editar()
    {
       TextButton btnEditar;
       btnEditar = new TextButton("Editar"){
           @Override
           protected void onClick(Event event){
               super.onClick(event);
               modUsuario mod = new modUsuario();
               mod.setApellido(apellido.getValue());
               mod.setId_usuario((Integer)id_usuario.getValue());
               mod.setNombre(nombre.getValue());
               mod.setClave(clave.getValue());
               SERVICES.getUsuariosAsync().modificar(mod, new AsyncCallback<Integer>() {

                   @Override
                   public void onFailure(Throwable caught) {
                       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   }

                   @Override
                   public void onSuccess(Integer result) {
                       Info.display("Respuesta", result.toString());
                   }
               });
           }
       };
       return new FieldLabel(btnEditar);
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
                   }
               });
           }
       };
       return new FieldLabel(btnGuardar);
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
        ColumnConfig<modUsuario, Integer> id_usuario = new ColumnConfig<modUsuario, Integer>(props.Id_usuario(), 50, "Identificacion");
        ColumnConfig<modUsuario, String> nombre = new ColumnConfig<modUsuario, String>(props.Nombre(), 100, "Nombre");
        ColumnConfig<modUsuario, String> apellido = new ColumnConfig<modUsuario, String>(props.Apellido(), 75, "Apellido");
        ColumnConfig<modUsuario, String> usuario = new ColumnConfig<modUsuario, String>(props.Usuario(), 75, "Usuario");
        ColumnConfig<modUsuario, String> password = new ColumnConfig<modUsuario, String>(props.Clave(), 75, "Clave");
        
      List<ColumnConfig<modUsuario, ?>> l = new ArrayList<ColumnConfig<modUsuario, ?>>();
      l.add(id_usuario);
      l.add(nombre);
      l.add(apellido);
      l.add(usuario);
      l.add(password);
      ColumnModel<modUsuario> cm = new ColumnModel<modUsuario>(l);
      
      ListStore<modUsuario> store = new ListStore<modUsuario>(props.key());
      modUsuario u;
        for (int i = 0; i < 10; i++) {
            u = new modUsuario();
            u.setId_usuario(1);
            u.setNombre("Esteban");
            u.setApellido("Restrepo Ramirez");
            u.setClave("rebeldia");
            store.add(u);
        }
      root = new ContentPanel();
      root.setHeadingText("Basic Grid");
      root.setPixelSize(600, 300);
      root.addStyleName("margin-10");
      
      
      grid = new Grid<modUsuario>(store, cm);
      grid.getView().setAutoExpandColumn(nombre);
      grid.getView().setStripeRows(true);
      grid.getView().setColumnLines(true);
      grid.setBorders(false);
 
      grid.setColumnReordering(true);
      grid.setStateful(true);
      grid.setStateId("gridExample");
 
      GridStateHandler<modUsuario> state = new GridStateHandler<modUsuario>(grid);
      state.loadState();
      
      root.add(grid);
      return root;
    }
}
