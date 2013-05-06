/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client;

import co.edu.poli.ces3.crud.bean.tbl_perfiles;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.info.Info;
import java.util.ArrayList;
import org.yournamehere.client.model.modModulo;
import org.yournamehere.client.model.modPerfil;
import org.yournamehere.client.model.modPerilesModulos;
import org.yournamehere.client.prop.propModulo;
import org.yournamehere.client.prop.propPerfil;

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
        
        h.add(initBtn_Guardar(),layoutData);
        h.add(initBtn_Editar(),layoutData);
        h.add(initBtn_Eliminar(),layoutData);
        
        p.add(h);
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
        SERVICES.getModulosAsync().obtenerModulos(new AsyncCallback<ArrayList<modModulo>>() {

            @Override
            public void onFailure(Throwable caught) {
                Info.display("Error", caught.getMessage());
            }

            @Override
            public void onSuccess(ArrayList<modModulo> result) {
                propModulo props = GWT.create(propModulo.class);
                ListStore<modModulo> modulos = new ListStore<modModulo>(props.key());
                modulos.addAll(result);
                id_modulo.setStore(modulos);
                id_modulo.redraw();
            }
        });
        propModulo props = GWT.create(propModulo.class);
        ListStore<modModulo> modulos = new ListStore<modModulo>(props.key());
        id_modulo = new ComboBox<modModulo>(modulos,props.value());
        id_modulo.setEmptyText("Seleccione un perfil");

        return new FieldLabel(id_modulo, "Modulo");
    }
    
    public Widget initId_perfil()
    {
        SERVICES.getPerfilesAsync().obtenerPerfiles(new AsyncCallback<ArrayList<modPerfil>>() {

            @Override
            public void onFailure(Throwable caught) {
                Info.display("Error", caught.getMessage());
            }

            @Override
            public void onSuccess(ArrayList<modPerfil> result) {
                propPerfil props = GWT.create(propPerfil.class);
                ListStore<modPerfil> perfiles = new ListStore<modPerfil>(props.key());
                perfiles.addAll(result);
                id_perfil.setStore(perfiles);
                id_perfil.redraw();
            }

        
        });
        propPerfil props = GWT.create(propPerfil.class);
        ListStore<modPerfil> perfiles = new ListStore<modPerfil>(props.key());
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
