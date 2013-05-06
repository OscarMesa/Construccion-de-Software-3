/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.sencha.gxt.widget.core.client.TabPanel;

/**
 * Main entry point.
 *
 * @author sala302
 */
public class MainEntryPoint implements  EntryPoint {
     
    /**
     * Creates a new instance of MainEntryPoint
     */
    public MainEntryPoint() {
    }

    /**
     * The entry point method, called automatically by loading a module that
     * declares an implementing class as an entry-point
     */
    public void onModuleLoad() {
        final Label label = new Label("Hello, GWT!!!");
        final Button button = new Button("Click me!");
        final TabPanel p;
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                label.setVisible(!label.isVisible());
            }
        });
        p = new TabPanel();
        uiUsuario usuario = new uiUsuario();
        uiPerfil perfil = new uiPerfil();
        uiModulo modulo = new uiModulo();
        p.add(usuario.getPanel(),"Usuario");        
        p.add(perfil.getPanel(),"Perfil");
        p.add(modulo.getPanel(),"Modulo");

       // RootPanel.get().add(button);
      //  RootPanel.get().add(label);
        RootPanel.get().add(p);
    }

}
