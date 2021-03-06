/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.theme.gray.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.base.client.menu.MenuBarItemBaseAppearance;

public class GrayMenuBarItemAppearance extends MenuBarItemBaseAppearance {

  public interface GrayMenuBarItemResources extends MenuBarItemResources, ClientBundle {
    @Source({"com/sencha/gxt/theme/base/client/menu/MenuBarItem.css", "GrayMenuBarItem.css"})
    GrayMenuBarItemStyle css();
  }
  
  public interface GrayMenuBarItemStyle extends MenuBarItemStyle {
  }
  
  public GrayMenuBarItemAppearance() {
    this(GWT.<GrayMenuBarItemResources>create(GrayMenuBarItemResources.class));
  }
  
  public GrayMenuBarItemAppearance(GrayMenuBarItemResources resources) {
    super(resources);
  }

}
