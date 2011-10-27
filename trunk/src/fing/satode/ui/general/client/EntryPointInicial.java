package fing.satode.ui.general.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;


public class EntryPointInicial implements EntryPoint  {

	public static native void setWindowHref(String url)/*-{
		$wnd.location.href = url;
	}-*/; 
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub

		 // Create a command that will execute on menu item selection
	    Command menuCommand = new Command() {
	      
	      public void execute() {
	        Window.alert("COMMAND");
	      }
	    };

	    // Create a menu bar
	    MenuBar menu = new MenuBar();
	    menu.setAutoOpen(true);
	    menu.setWidth("500px");
	    menu.setAnimationEnabled(true);

	    // Create a sub menu of recent documents
	    MenuBar recentDocsMenu = new MenuBar(true);
	    recentDocsMenu.addItem("recentDocsMenu", menuCommand);
	   
	    // Create the file menu
	    MenuBar fileMenu = new MenuBar(true);
	    fileMenu.setAnimationEnabled(true);
	    menu.addItem(new MenuItem("fileMenu", fileMenu));
        fileMenu.addSeparator();
        fileMenu.addItem("recentDocsMenu", recentDocsMenu);
        fileMenu.addSeparator();
        fileMenu.addItem("menuCommand", menuCommand);

	    // Return the menu
	    menu.ensureDebugId("cwMenuBar");
	    
	    RootPanel.get("menu").add(menu);
	   
	}

}
