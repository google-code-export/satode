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

	    Command menuCommandSalir = new Command() {
	    	 public void execute() {
	 	        setWindowHref("/Login.html");
	 	      }   };
 	    Command menuCommandUsuarios = new Command() {
	    	 public void execute() {
	 	        setWindowHref("/UsuarioList.html");
	 	      }   };
	 	Command menuCommandPerfil = new Command() {
	    	 public void execute() {
	 	        setWindowHref("/PerfilList.html");
	 	      }   };
 	 	Command menuCommandEvento = new Command() {
	    	 public void execute() {
	 	        setWindowHref("/EventosList.html");
	 	      }   };   
 	 	Command menuCommandDesastre = new Command() {
	    	 public void execute() {
	 	        setWindowHref("/DesastreList.html");
	 	      }   };  
	    Command menuCommandDeposito = new Command() {
	    	 public void execute() {
	 	        setWindowHref("/DepositoList.html");}};// Create a menu bar
	 	        
	    Command menuCommandTipoSuministro = new Command() {
	    	 public void execute() {
	 	        setWindowHref("/TipoSuministroList.html");}};// Create a menu bar
	 	        
	    MenuBar menu = new MenuBar();
	    menu.setAutoOpen(true);
	    menu.setWidth("100%");
	    menu.setAnimationEnabled(true);

	    // Create the file menu
	    MenuBar fileMenu = new MenuBar(true);
	    fileMenu.setAnimationEnabled(true);
	    menu.addItem(new MenuItem("Archivo", fileMenu));
        fileMenu.addItem("Salir", menuCommandSalir);

        MenuBar eventosMenu = new MenuBar(true);
        eventosMenu.setAnimationEnabled(true);
	    menu.addItem(new MenuItem("Registros", eventosMenu));
	    eventosMenu.addItem("Eventos", menuCommandEvento);
	    
	    MenuBar depositoMenu = new MenuBar(true);
	    depositoMenu.setAnimationEnabled(true);
	    menu.addItem(new MenuItem("Deposito", depositoMenu));
	    depositoMenu.addItem("Deposito Mantenimiento", menuCommandDeposito);
	    depositoMenu.addItem("Tipo Suministro Mantenimiento", menuCommandTipoSuministro);
	    
	    MenuBar desastresMenu = new MenuBar(true);
	    desastresMenu.setAnimationEnabled(true);
	    menu.addItem(new MenuItem("Desastres", desastresMenu));
	    desastresMenu.addItem("Declaracion", menuCommandDesastre);

        MenuBar seguridadMenu = new MenuBar(true);
        seguridadMenu.setAnimationEnabled(true);
	    menu.addItem(new MenuItem("Seguridad", seguridadMenu));
        seguridadMenu.addSeparator();
	    seguridadMenu.addItem("Usuarios", menuCommandUsuarios);
	    seguridadMenu.addSeparator();
	    seguridadMenu.addItem("Perfiles", menuCommandPerfil);
	   
	    
	    // Return the menu
	    menu.ensureDebugId("cwMenuBar");
	    
	    RootPanel.get("menu").add(menu);
	   
	}

}
