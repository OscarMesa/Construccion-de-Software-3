/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.ButtonCell;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.ListStoreBinding;
import com.sencha.gxt.data.shared.loader.LoadHandler;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.yournamehere.client.model.modModulo;
import org.yournamehere.client.model.modPerfil;
import org.yournamehere.client.model.modPerilesModulos;
import org.yournamehere.client.model.modPermisos;
import org.yournamehere.client.model.modUsuario;
import org.yournamehere.client.prop.propModulo;
import org.yournamehere.client.prop.propPerfil;
import org.yournamehere.client.prop.propPermiso;
import org.yournamehere.client.prop.propUsuario;

/**
 *
 * @author omesa
 */
public abstract class uiPermiso {

    private ComboBox<modPerfil> id_perfil;
    private ComboBox<modUsuario> id_usuario;
    private FramedPanel panel;
    private PagingLoader<PagingLoadConfig, PagingLoadResult<modPermisos>> loader;
    private ListStore<modPermisos> store;
    protected Grid<modPermisos> grid;
    private final propUsuario propUsuario;
    private ListStore<modUsuario> ListUsuarios;
    private PagingLoader<PagingLoadConfig, PagingLoadResult<modUsuario>> loaderUsuarios;
    private final propPerfil propPerfiles;
    private ListStore<modPerfil> Listperfiles;
    private PagingLoader<PagingLoadConfig, PagingLoadResult<modPerfil>> loaderPerfiles;

    {
        propPerfiles = GWT.create(propPerfil.class);
        propUsuario = GWT.create(propUsuario.class);
    }

    public uiPermiso() {
        panel = new FramedPanel();
        VerticalLayoutContainer p = new VerticalLayoutContainer();
        HorizontalLayoutContainer h = new HorizontalLayoutContainer();
        HorizontalLayoutContainer.HorizontalLayoutData layoutData = new HorizontalLayoutContainer.HorizontalLayoutData(30, 25, new Margins(0, 25, 0, 0));
        panel.add(p);

        p.add(initId_perfil());
        p.add(initId_usuario());
        p.add(initGrid_Permisos());
        panel.addButton(initBtn_Guardar());
    
        panel.setButtonAlign(BoxLayoutContainer.BoxLayoutPack.START);
        p.add(h);
    }
    
    public Widget initBtn_Guardar()
    {
        TextButton guardar = new TextButton("Guardar"){

            @Override
            protected void onClick(Event event) {
                modPermisos x = new modPermisos();
                x.setId_perfil(id_perfil.getValue().getId_perfil());
                x.setId_usuario(id_usuario.getValue().getId_usuario());
                
                SERVICES.getPermisosAsync().guardar(x, new AsyncCallback<Integer>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        Info.display("Error",caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Integer result) {
                        Info.display("Exito", result.toString());
                        loader.load();
                    }
                });
            }
        };
        
        return new FieldLabel(guardar);
    }
    
    public Widget initGrid_Permisos() {
        RpcProxy<PagingLoadConfig, PagingLoadResult<modPermisos>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<modPermisos>>() {
            @Override
            public void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<modPermisos>> callback) {
                SERVICES.getPermisosAsync().consultar(loadConfig, callback);
            }
        };
        final propPermiso propPermisos = GWT.create(propPermiso.class);
        ColumnConfig<modPermisos, String> nombrePerfil = new ColumnConfig<modPermisos, String>(propPermisos.nombrePerfil(), 200, "Perfil");
        ColumnConfig<modPermisos, String> nombreUsuario = new ColumnConfig<modPermisos, String>(propPermisos.nombreUsuario(), 200, "Usuario");
        ColumnConfig<modPermisos, String> eliminarPermiso = new ColumnConfig<modPermisos, String>(propPermisos.eliminar(), 50, "Eliminar");

        ButtonCell button = new ButtonCell();
        button.setIcon(MyResourcesPermisos.INSTANCE.logo());
        button.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                Cell.Context c = event.getContext();
                modPermisos x = store.get(c.getIndex());
                SERVICES.getPermisosAsync().eliminar(x.getId_perfil(), x.getId_usuario(), new AsyncCallback<Integer>() {

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
        eliminarPermiso.setCell(button);

        List<ColumnConfig<modPermisos, ?>> ListaColumnas = new ArrayList<ColumnConfig<modPermisos, ?>>();
        ListaColumnas.add(nombrePerfil);
        ListaColumnas.add(nombreUsuario);
        ListaColumnas.add(eliminarPermiso);

        ColumnModel<modPermisos> cm = new ColumnModel<modPermisos>(ListaColumnas);

        store = new ListStore<modPermisos>(propPermisos.key());

        loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<modPermisos>>(proxy);
        
        loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, modPermisos, PagingLoadResult<modPermisos>>(store));        
    
        grid = new Grid<modPermisos>(store, cm);
        
        grid.setColumnReordering(true);
        grid.getView().setStripeRows(true);
        grid.getView().setColumnLines(true);

        loader.load();
        
        ComboBox<modPerfil> CEditPerfil = new ComboBox<modPerfil>(Listperfiles, propPerfiles.value());
        CEditPerfil.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        
        ComboBox<modUsuario> CEditUsuario = new ComboBox<modUsuario>(ListUsuarios,propUsuario.value());
        CEditUsuario.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        
        CEditPerfil.setPropertyEditor(new PropertyEditor<modPerfil>() {

            @Override
            public String render(modPerfil object) {
                return object.getNombre();
            }

            @Override
            public modPerfil parse(CharSequence text) throws ParseException {
                modPerfil x = new modPerfil();
                x.setNombre(text.toString());
                return x;
            }
        });
        
        CEditUsuario.setPropertyEditor(new PropertyEditor<modUsuario>() {

            @Override
            public String render(modUsuario object) {
                return object.getNombre();
            }

            @Override
            public modUsuario parse(CharSequence text) throws ParseException {
                modUsuario x = new modUsuario();
                x.setNombre(text.toString());
                return x;
            }
        });
        
        final GridEditing<modPermisos> editing = createGridEditing(grid);
        
        editing.addEditor(nombrePerfil, new Converter<String, modPerfil>() {

            @Override
            public String convertFieldValue(modPerfil object) {
                return object.getNombre();
            }

            @Override
            public modPerfil convertModelValue(String object) {
                modPerfil x = new modPerfil();
                x.setNombre(object);
                return x;
            }

        },CEditPerfil);
        
        editing.addEditor(nombreUsuario, new Converter<String, modUsuario>() {

            @Override
            public String convertFieldValue(modUsuario object) {
                return object.getNombre();
            }

            @Override
            public modUsuario convertModelValue(String object) {
                modUsuario x = new modUsuario();
                x.setNombre(object);
                return x;
            }
        }, CEditUsuario);
         
        PagingToolBar toolBar;
        toolBar = new PagingToolBar(3);
        toolBar.bind(loader);
        loader.load();
        
        VerticalLayoutContainer contenedor = new VerticalLayoutContainer();
        contenedor.setBorders(true);
        contenedor.add(grid, new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        contenedor.add(toolBar,new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        
         FramedPanel cp = new FramedPanel();
         cp.setHeadingText("Permisos");
        
         cp.setWidget(contenedor);
        
         cp.setButtonAlign(BoxLayoutContainer.BoxLayoutPack.CENTER);

         cp.addButton(new TextButton("Guardar cambios", new SelectEvent.SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                store.commitChanges();
            }
         }));
        
         return cp;
    }

    public Widget initId_usuario() {
        RpcProxy<PagingLoadConfig, PagingLoadResult<modUsuario>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<modUsuario>>() {
            @Override
            public void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<modUsuario>> callback) {
                SERVICES.getUsuariosAsync().consultar(loadConfig, callback);
            }
        };

        ListUsuarios = new ListStore<modUsuario>(propUsuario.key());
        loaderUsuarios = new PagingLoader<PagingLoadConfig, PagingLoadResult<modUsuario>>(proxy);
        loaderUsuarios.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, modUsuario, PagingLoadResult<modUsuario>>(ListUsuarios));
        loaderUsuarios.load();

        id_usuario = new ComboBox<modUsuario>(ListUsuarios, propUsuario.value());
        id_usuario.setEmptyText("Seleccione un perfil");
        id_usuario.setAllowBlank(false);
        id_usuario.setTriggerAction(ComboBoxCell.TriggerAction.ALL);

        return new FieldLabel(id_usuario, "Usuario");

    }

    public Widget initId_perfil() {
        RpcProxy<PagingLoadConfig, PagingLoadResult<modPerfil>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<modPerfil>>() {
            @Override
            public void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<modPerfil>> callback) {
                SERVICES.getPerfilesAsync().obtenerPerfiles(loadConfig, callback);
            }
        };

        Listperfiles = new ListStore<modPerfil>(propPerfiles.key());
        loaderPerfiles = new PagingLoader<PagingLoadConfig, PagingLoadResult<modPerfil>>(proxy);
        loaderPerfiles.addLoadHandler(
                new LoadResultListStoreBinding<PagingLoadConfig, modPerfil, PagingLoadResult<modPerfil>>(Listperfiles));

        loaderPerfiles.load();

        id_perfil = new ComboBox<modPerfil>(Listperfiles, propPerfiles.value());
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

    protected abstract GridEditing<modPermisos> createGridEditing(Grid<modPermisos> grid);
}

interface MyResourcesPermisos extends ClientBundle {

    public static final MyResources INSTANCE = GWT.create(org.yournamehere.client.MyResources.class);

    @ClientBundle.Source("delete.png")
    ImageResource logo();
}

class ImplementUIPermisos extends uiPermiso {

    @Override
    protected GridEditing<modPermisos> createGridEditing(Grid<modPermisos> grid) {
        return new GridInlineEditing<modPermisos>(grid);
    }
}