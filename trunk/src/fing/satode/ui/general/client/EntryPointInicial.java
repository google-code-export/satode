package fing.satode.ui.general.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;

import fing.satode.data.UsuarioDTO;
import fing.satode.ui.usuarios.client.IUsuario;
import fing.satode.ui.usuarios.client.IUsuarioAsync;


public class EntryPointInicial implements EntryPoint  {

	public static native void setWindowHref(String url)/*-{
		$wnd.location.href = url;
	}-*/; 
	private UsuarioDTO usuarioGlobal;
	
	@Override
	public void onModuleLoad() {

 	    IUsuarioAsync servidorUsuario= GWT.create(IUsuario.class);
 		
 		servidorUsuario.getUsuarioLogin(new AsyncCallback<UsuarioDTO>() {
 			
 			@Override
 			public void onSuccess(UsuarioDTO result) {
 				usuarioGlobal=result;
 				
 				if(usuarioGlobal!=null){

	 			    Command menuCommandSalir = new Command() {
	 			    	 public void execute() {
	 			 	        setWindowHref("../Login.html");
	 			 	      }   };
	 		 	    Command menuCommandUsuarios = new Command() {
	 			    	 public void execute() {
	 			 	        setWindowHref("../UsuarioList.html");
	 			 	      }   };
	 			 	Command menuCommandPerfil = new Command() {
	 			    	 public void execute() {
	 			 	        setWindowHref("../PerfilList.html");
	 			 	      }   };
	 		 	 	Command menuCommandEvento = new Command() {
	 			    	 public void execute() {
	 			 	        setWindowHref("../EventosList.html");
	 			 	      }   };   
	 		 	 	Command menuCommandDesastre = new Command() {
	 			    	 public void execute() {
	 			 	        setWindowHref("../DesastreList.html");
	 			 	      }   };  
	 			    Command menuCommandDeposito = new Command() {
	 			    	 public void execute() {
	 			 	        setWindowHref("../DepositoList.html");}};// Create a menu bar
	 			 	        
	 			    Command menuCommandTipoSuministro = new Command() {
	 			    	 public void execute() {
	 			 	        setWindowHref("../TipoSuministroList.html");}};// Create a menu bar
	 		        
	 			 	Command menuCommandPuntoReferencia = new Command() {
	 			    	 public void execute() {
	 			 	        setWindowHref("../PuntoReferenciaList.html");}};// Create a menu bar
	
	 			 	Command menuCommandDoaciones = new Command() {
	 			    	 public void execute() {
	 			 	        setWindowHref("../SuministroList.html");}};// Create a menu bar
	 			 	        
	 			 	Command menuCommandNecesidades = new Command() {
	 			    	 public void execute() {
	 			 	        setWindowHref("../NecesidadList.html");}};// Create a menu bar
	 		       
	 			 	Command menuCommandStockSuministros = new Command() {
	 			    	 public void execute() {
	 			 	        setWindowHref("../StockSuministroList.html");}};// Create a menu bar	 	        
	
	 			 	Command menuCommandGestionNecesidad = new Command() {
	 			    	 public void execute() {
	 			 	        setWindowHref("../GestionNecesidad.html");}};// Create a menu bar	 
	
	 			 	Command menuCommandSolicitudEnvio = new Command() {
	 			    	 public void execute() {
	 			 	        setWindowHref("../SolicitudEnvioList.html");}};// Create a menu bar	 
	
	 			 	Command menuCommandTipoCosto = new Command() {
	 			    	 public void execute() {
	 			 	        setWindowHref("../TipoCostoList.html");}};// Create a menu bar	 
	
	 			 	Command menuCommandCosto = new Command() {
	 			    	 public void execute() {
	 			 	        setWindowHref("../CostoList.html");}};// Create a menu bar	 
	 			 	Command  menuCommandPropiedadesSinistradas = new Command() {
	 			 		    	 public void execute() {
	 			 		 	        setWindowHref("../PropiedadesSiniestradasList.html");}};// Create a menu bar	         
	 			 	        
	 			 	Command  menuCommandIndices= new Command() {
		 		    	 public void execute() {
		 		 	        setWindowHref("../CalculoIndiceList.html");}};// Create a menu bar	         
	 				
	 			 	Command  menuCommandEstadoDesastre= new Command() {
		 		    	 public void execute() {
		 		 	        setWindowHref("../EstadoDesastre.html");}};// Create a menu bar	         

		 		 	MenuBar menu = new MenuBar();
	 			    menu.setAutoOpen(true);
	 			    menu.setWidth("100%");
	 			    menu.setAnimationEnabled(true);
	
	 			    // Create the file menu
	 			    MenuBar fileMenu = new MenuBar(true);
	 			    fileMenu.setAnimationEnabled(true);
	 			    menu.addItem(new MenuItem("Inicio", fileMenu));
	 		        fileMenu.addItem("Salir", menuCommandSalir);
	
	 		        MenuBar eventosMenu = new MenuBar(true);
	 		        eventosMenu.setAnimationEnabled(true);
	 		        if(usuarioGlobal.getPerfil().tienePermiso("MNU_REGISTROS")){
		 			    menu.addItem(new MenuItem("Registros", eventosMenu));
		 			   if(usuarioGlobal.getPerfil().tienePermiso("MNU_DONACIONES")){
		 				  eventosMenu.addSeparator();
		 				  eventosMenu.addItem("Donaciones", menuCommandDoaciones);
		 			   } 
		 			   if(usuarioGlobal.getPerfil().tienePermiso("MNU_EVENTOS")){
		 				   eventosMenu.addSeparator();
		 				   eventosMenu.addItem("Eventos", menuCommandEvento);
		 			   }
		 			  if(usuarioGlobal.getPerfil().tienePermiso("MNU_PROPIEDADES_SINIESTRADAS")){
		 				 eventosMenu.addSeparator();
		 				 eventosMenu.addItem("Propiedades Siniestradas", menuCommandPropiedadesSinistradas);
		 			  }
	 		        }
	 			   if(usuarioGlobal.getPerfil().tienePermiso("MNU_DEPOSITOS")){
	 				    MenuBar depositoMenu = new MenuBar(true);
		 			    depositoMenu.setAnimationEnabled(true);
		 			    menu.addItem(new MenuItem("Deposito", depositoMenu));
		 			    
		 			   if(usuarioGlobal.getPerfil().tienePermiso("MNU_SUMINISTROS")){
		 				   depositoMenu.addSeparator();
		 				   depositoMenu.addItem("Stock Suministros", menuCommandStockSuministros);
		 			   }
		 			  if(usuarioGlobal.getPerfil().tienePermiso("MNU_SOLICITUDES_ENVIO")){
		 				 depositoMenu.addSeparator();
		 				 depositoMenu.addItem("Solicitudes de envio", menuCommandSolicitudEnvio);
		 			  }
	 			    }
	 			  if(usuarioGlobal.getPerfil().tienePermiso("MNU_DESASTRES")){
	 			    MenuBar desastresMenu = new MenuBar(true);
	 			    desastresMenu.setAnimationEnabled(true);
	 			    menu.addItem(new MenuItem("Desastres", desastresMenu));
   	 			    if(usuarioGlobal.getPerfil().tienePermiso("MNU_DESASTRES_DECLARACION")){
   	 			    	desastresMenu.addSeparator();
   	 			    	desastresMenu.addItem("Declaracion Desastres", menuCommandDesastre);
	 			    }
   	 			    if(usuarioGlobal.getPerfil().tienePermiso("MNU_ESTADO_DESASTRE")){
   	 			    	desastresMenu.addItem("Estado Desastre", menuCommandEstadoDesastre);
   	 			    }
   	 			    if(usuarioGlobal.getPerfil().tienePermiso("MNU_INGRESO_NECESIDADES")){
   	 			    	desastresMenu.addSeparator();
   	 			    	desastresMenu.addItem("Ingreso Necesidades", menuCommandNecesidades);
   	 			    }
   	 			    if(usuarioGlobal.getPerfil().tienePermiso("MNU_GESTION_NECESIDADES")){
   	 			    	desastresMenu.addItem("Gestion Necesidades", menuCommandGestionNecesidad);
   	 			    }
   	 			    if(usuarioGlobal.getPerfil().tienePermiso("MNU_GESTION_COSTOS")){
   	 			    	desastresMenu.addSeparator();
   	 			    	desastresMenu.addItem("Gestion Costos", menuCommandCosto);
   	 			    }
	   	 			if(usuarioGlobal.getPerfil().tienePermiso("MNU_CALCULO_INDICES")){
	 			    	desastresMenu.addSeparator();
	 			    	desastresMenu.addItem("Calculo de indicadores", menuCommandIndices);
	 			    }
   	 			   }
	 			  if(usuarioGlobal.getPerfil().tienePermiso("MNU_MANTENIMISNTOS")){
		 			    MenuBar basicosMenu = new MenuBar(true);
		 			    basicosMenu.setAnimationEnabled(true);
		 			    menu.addItem(new MenuItem("Mantenimientos", basicosMenu));
		 			   if(usuarioGlobal.getPerfil().tienePermiso("MNU_PUNTO_REFERENCIA")){
		 				   basicosMenu.addSeparator();
		 				   basicosMenu.addItem("Punto Referencia ", menuCommandPuntoReferencia);
		 			   }
		 			   if(usuarioGlobal.getPerfil().tienePermiso("MNU_MANTENIMIENTOS_DEPOSITOS")){
		 				   basicosMenu.addSeparator();
		 				   basicosMenu.addItem("Deposito ", menuCommandDeposito);
		 			   }
		 			   if(usuarioGlobal.getPerfil().tienePermiso("MNU_TIPOS_SUMINISTROS")){
		 				   basicosMenu.addSeparator();
		 				   basicosMenu.addItem("Tipo Suministro ", menuCommandTipoSuministro);
		 			   }
		 			   if(usuarioGlobal.getPerfil().tienePermiso("MNU_TIPOS_COSTOS")){
		 				   basicosMenu.addSeparator();
		 				   basicosMenu.addItem("Tipo Costo ", menuCommandTipoCosto);
		 			   }	
		 				    
		 		  }
	 			 
	 			  if(usuarioGlobal.getPerfil().tienePermiso("MNU_SEGURIDAD")){
	 			    MenuBar seguridadMenu = new MenuBar(true);
	 		        seguridadMenu.setAnimationEnabled(true);
	 			    menu.addItem(new MenuItem("Seguridad", seguridadMenu));
	 			    seguridadMenu.addSeparator();
	 			   if(usuarioGlobal.getPerfil().tienePermiso("MNU_USUARIOS")){
	 				   seguridadMenu.addItem("Usuarios", menuCommandUsuarios);
	 				   seguridadMenu.addSeparator();
	 			   }
	 			   if(usuarioGlobal.getPerfil().tienePermiso("MNU_PERFILES")){
	 				   seguridadMenu.addItem("Perfiles", menuCommandPerfil);
	 			   }
	 			  }
	 			 
	 			    // Return the menu
	 			    menu.ensureDebugId("cwMenuBar");
	 			    
	 			    RootPanel.get("menu").add(menu);
 				}
 				else{
 					setWindowHref("../Login.html");
 				}
 			}
 			
 			@Override
 			public void onFailure(Throwable caught) {
 				setWindowHref("../Login.html");
 			}
 		});
	 		 		
	 	
	   
	}

}
