/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client;

import org.yournamehere.client.model.modPerfil;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;
/**
 *
 * @author oskar
 */
public class uiPerfil{
    
    private NumberField id_perfil;
    private TextField nombre;
    private TextArea descripcion;
    private ComboBox<States> activo;
    private FramedPanel panel;
    
    interface StatesProperties extends PropertyAccess<States> {
        ModelKeyProvider<States> value();
        LabelProvider<States> LabelName();
  }
    
    public uiPerfil(){
        panel = new FramedPanel();
        VerticalLayoutContainer p = new VerticalLayoutContainer();
        HorizontalLayoutContainer h = new HorizontalLayoutContainer();
        HorizontalLayoutContainer.HorizontalLayoutData layoutData = new HorizontalLayoutContainer.HorizontalLayoutData(30, 25, new Margins(0,25,0,0));
        panel.add(p);
        panel.setHeadingText("Perfiles");
        panel.setCollapsible(true);
        p.setHeight(400);
        p.add(initId_perfil());
        p.add(initNombre());
        p.add(initDescripcion());
        p.add(initActivo());
        
        h.add(initBtn_Guardar(),layoutData);
        h.add(initBtn_Editar(),layoutData);
        h.add(initBtn_Eliminar(),layoutData);
        
        p.add(h);
    }
    
    public Widget initBtn_Guardar(){
        TextButton guardar = new TextButton("Guardar"){
            @Override
            protected void onClick(Event event){
                super.onClick(event);
                modPerfil p = new modPerfil();
                p.setActivo(activo.getValue().getValue());
                p.setDescripcion(descripcion.getValue());
                p.setId_perfil(Integer.parseInt(id_perfil.getValue().toString()));
                p.setNombre(nombre.getValue());
                SERVICES.getPerfilesAsync().guardar(p, new AsyncCallback<Integer>() {

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
        return guardar;
    }
    
    public Widget initBtn_Editar(){
        TextButton editar = new TextButton("Editar"){
           @Override
           protected void onClick(Event event){
               super.onClick(event);
               final PromptMessageBox box = new PromptMessageBox("Id perfil", "Ingrese el Id del perfil:");  
               box.show();
               box.addHideHandler(new HideEvent.HideHandler() {
                   @Override
                   public void onHide(HideEvent event) {
                       if (box.getHideButton() == box.getButtonById(Dialog.PredefinedButton.OK.name()))
                       {
                            modPerfil p = new modPerfil();
                            p.setActivo(activo.getValue().getValue());
                            p.setDescripcion(descripcion.getValue());
                            p.setId_perfil(Integer.parseInt(box.getValue()));
                            p.setNombre(nombre.getValue());
                            SERVICES.getPerfilesAsync().modificar(p, new AsyncCallback<Integer>() {

                                @Override
                                public void onFailure(Throwable caught) {
                                   Info.display("Respuesta",caught.getMessage());
                                }

                                @Override
                                public void onSuccess(Integer result) {
                                    Info.display("Respuesta",result.toString());
                                }
                            });
                       }else if (box.getHideButton() == box.getButtonById(Dialog.PredefinedButton.CANCEL.name())) {
                          box.hide();
                        }
                   }
               });
            }
          };
        return editar;
    }
    
    public Widget initBtn_Eliminar(){
        TextButton eliminar = new TextButton("Eliminar"){
            @Override
            protected void onClick(Event event){
                super.onClick(event);
                final PromptMessageBox box = new PromptMessageBox("Id Perfil", "Ingresar id del perfil");
                box.show();
                box.addHideHandler(new HideEvent.HideHandler() {

                    @Override
                    public void onHide(HideEvent event) {
                        if (box.getHideButton() == box.getButtonById(Dialog.PredefinedButton.OK.name())) {
                            Integer id = Integer.parseInt(box.getValue());
                            SERVICES.getPerfilesAsync().eliminar(id, new AsyncCallback<Integer>() {

                                @Override
                                public void onFailure(Throwable caught) {
                                    Info.display("Error", caught.getMessage());
                                }

                                @Override
                                public void onSuccess(Integer result) {
                                    Info.display("Respuesta", result.toString());
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
        StatesProperties props = GWT.create(StatesProperties.class);
        ListStore<States> states = new ListStore<States>(props.value());
        states.add(new States("Activo", Boolean.TRUE));
        states.add(new States("Inactivo", Boolean.FALSE));
        activo = new ComboBox<States>(states,props.LabelName());
        
        activo.setEmptyText("Seleccione un estado");
        activo.setTypeAhead(true);
        activo.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        
        return new FieldLabel(activo,"Estado");
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
        id_perfil = new NumberField(new NumberPropertyEditor.IntegerPropertyEditor());
        id_perfil.setAllowDecimals(true);
        id_perfil.setAllowNegative(false);
        id_perfil.setAllowBlank(false);
        id_perfil.setEmptyText("Id perfil");
        return new FieldLabel(id_perfil, "ID");
    }
    public FramedPanel getPanel() {
        return panel;
    }

    public void setPanel(FramedPanel panel) {
        this.panel = panel;
    }

}

class States{
    private String LabelName;
    private Boolean value;
    public States(String LabelName, Boolean value) {
        setLabelName(LabelName);
        setValue(value);
    }

    public String getLabelName() {
        return LabelName;
    }

    public void setLabelName(String LabelName) {
        this.LabelName = LabelName;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
    
}