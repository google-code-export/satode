package fing.satode.ui.usuarios.client;

import java.util.ArrayList;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;

import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import fing.satode.data.PerfilDTO;
import fing.satode.data.PermisoDTO;
import fing.satode.ui.desastres.client.EntryPointDesastre.FormDialogBox;


public class EntryPointPerfilList implements EntryPoint {
	private ArrayList<PerfilDTO> perfilList;
	private Grid perfiles;
	final Label perfilLabel= new Label("Perfil");
	final Label modificarLabel= new Label("Modificar");
	final Label eliminarLabel= new Label("Eliminar");
	final VerticalPanel vertical = new VerticalPanel();
	final Button nuevoB = new Button("Nuevo");
	public ArrayList<PermisoDTO> permisosGlobal;
	
	public static native void setWindowHref(String url)/*-{
		$wnd.location.href = url;
	}-*/; 
	
	@Override
	public void onModuleLoad() {
		RootPanel.get("botones").add(nuevoB);
		nuevoB.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				FormDialogBox dialog= new FormDialogBox(0L, "nuevo");
				dialog.show();
			}
		});
		cargarLista();	
		
    }
			
	
	private void cargarLista() {

		vertical.clear();
		RootPanel.get("perfiles").clear();
		RootPanel.get("perfiles").add(vertical);

		IUsuarioAsync servidorUsuario = GWT.create(IUsuario.class);
		
		servidorUsuario.listaPerfiles(new AsyncCallback<ArrayList<PerfilDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<PerfilDTO> result) {
				perfiles = new Grid(result.size()+1,3);
				perfiles.setWidget(0, 0, perfilLabel);
				perfiles.setWidget(0, 1, modificarLabel);
				perfiles.setWidget(0, 2, eliminarLabel);
				perfilList= result;
				
				for(int i=0;i<3;i++){
					perfiles.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				int row=1;
				for(PerfilDTO p : result){
					final Long id= p.getId();
					final Image modificarB = new Image("/images/modificar.png");
					modificarB.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							FormDialogBox dialog= new FormDialogBox(id, "modificar");
							dialog.show();
						}
					});
					
				
					final Image eliminarB = new Image("/images/eliminar.png");
					eliminarB.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							
							FormDialogBox dialog= new FormDialogBox(id, "eliminar");
							dialog.show();
						}
					});
					perfiles.setWidget(row, 0, new Label(p.getNombre()));
					perfiles.setWidget(row, 1, modificarB);
					perfiles.setWidget(row, 2, eliminarB);
					perfiles.setBorderWidth(1);
					row++;
				}
				vertical.add(perfiles);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
		
		
		servidorUsuario.listaPermisos(new AsyncCallback<ArrayList<PermisoDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<PermisoDTO> result) {
				
				permisosGlobal=result;
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
		
	}


	public class FormDialogBox extends DialogBox{
		final VerticalPanel vertical= new VerticalPanel();
		final HorizontalPanel horizontal= new HorizontalPanel();
		final CaptionPanel panelPrincipal = new CaptionPanel();
		final Grid grid= new Grid(1,2);
		final TextBox nombre= new TextBox();
		final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
		Grid gridPermisos;
		private String a;
		private Long id;
		
		
		public FormDialogBox(Long idPerfil,String accion){
			super();
			id=idPerfil;
			a=accion;
			grid.setWidget(0,0, new Label("Nombre"));
			grid.setWidget(0,1, nombre);
			horizontal.add(aceptar);
			horizontal.add(cancelar);
			nombre.setFocus(true);
			
			setAnimationEnabled(true);
			add(panelPrincipal);
			center();  
			
			if(accion=="modificar"){
				panelPrincipal.setCaptionText("Modificar Perfil");
				PerfilDTO perfilAModificar=null;
				for(PerfilDTO p:perfilList){
					if(id.equals(p.getId())){
						perfilAModificar=p;
					}
				}
				
				gridPermisos= new Grid(permisosGlobal.size(),1);
				int row=0;
				for(PermisoDTO p: permisosGlobal){
					CheckBox c= new CheckBox();
					c.setText(p.getPermiso());
					for(PermisoDTO pdto:perfilAModificar.getPermisos()){
						if(pdto.getPermiso().equals(p.getPermiso())){
							c.setChecked(true);
						}
					}
					gridPermisos.setWidget(row, 0, c);
					row++;
				}
				nombre.setText(perfilAModificar.getNombre());
				panelPrincipal.add(vertical);
				vertical.add(grid);
				vertical.add(gridPermisos);
				vertical.add(horizontal);
				
				
			}else if(accion=="eliminar"){
				panelPrincipal.setCaptionText("Eliminar Perfil");
				nombre.setEnabled(false);
				PerfilDTO perfilAModificar=null;
				for(PerfilDTO p:perfilList){
					if(id.equals(p.getId())){
						perfilAModificar=p;
					}
				}
				
				gridPermisos= new Grid(permisosGlobal.size(),1);
				int row=0;
				for(PermisoDTO p: permisosGlobal){
					CheckBox c= new CheckBox();
					c.setEnabled(false);
					c.setText(p.getPermiso());
					for(PermisoDTO pdto:perfilAModificar.getPermisos()){
						if(pdto.getPermiso().equals(p.getPermiso())){
							c.setChecked(true);
						}
					}
					gridPermisos.setWidget(row, 0, c);
					row++;
				}
				nombre.setText(perfilAModificar.getNombre());
				panelPrincipal.add(vertical);
				vertical.add(grid);
				vertical.add(gridPermisos);
				vertical.add(horizontal);
				
			}else if(accion=="nuevo"){
				panelPrincipal.setCaptionText("Nuevo Perfil");
				
				IUsuarioAsync servidorUsuario = GWT.create(IUsuario.class);
				
				servidorUsuario.listaPermisos(new AsyncCallback<ArrayList<PermisoDTO>>() {
					
					@Override
					public void onSuccess(ArrayList<PermisoDTO> result) {
						
						gridPermisos= new Grid(result.size(),1);
						permisosGlobal=result;
						int row=0;
						for(PermisoDTO p: result){
							CheckBox c= new CheckBox();
							c.setText(p.getPermiso());
							gridPermisos.setWidget(row, 0, c);
							row++;
						}
						
						panelPrincipal.add(vertical);
						vertical.add(grid);
						vertical.add(gridPermisos);
						vertical.add(horizontal);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						
						caught.printStackTrace();
						Window.alert("ERROR AJAX");
					}
				});
				

			}
			
			cancelar.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					FormDialogBox.this.hide();
				}
			});
			
			aceptar.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					FormDialogBox.this.procesar();
					FormDialogBox.this.hide();
				}
			});
				
			
		}

		@SuppressWarnings("deprecation")
		protected void procesar() {
		
				PerfilDTO perfil = new PerfilDTO();
				
				perfil.setNombre(nombre.getText());
				ArrayList<PermisoDTO> permisosSet= new ArrayList<PermisoDTO>();
				for(int row=0; row<gridPermisos.getRowCount();row++){
					CheckBox c =(CheckBox)gridPermisos.getWidget(row, 0);
					if(c.isChecked()){
						permisosSet.add(buscarPermiso(c.getText()));
					}
				}
				perfil.setPermisos(permisosSet);
		
				if(a=="nuevo"){
					perfil.setId(0L);
					IUsuarioAsync servidorUsuario = GWT.create(IUsuario.class);
				
					servidorUsuario.nuevoPerfil(perfil, new AsyncCallback<Void>() {
					
						@Override
						public void onSuccess(Void result) {
							
							EntryPointPerfilList.this.cargarLista();
						}
						
						@Override
						public void onFailure(Throwable caught) {
							
							Window.alert("ERROR AJAX");
						}
				    });
			    } else if(a=="modificar"){
			    	
					perfil.setId(id);
				
					IUsuarioAsync servidorUsuario = GWT.create(IUsuario.class);
					
					servidorUsuario.modificarPerfil(perfil, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							
							EntryPointPerfilList.this.cargarLista();
						}
						
						@Override
						public void onFailure(Throwable caught) {
							
							caught.printStackTrace();
							Window.alert("ERROR AJAX");
						}
					});
				
			    }else if(a=="eliminar"){
			    	    	
							perfil.setId(id);
						
							IUsuarioAsync servidorUsuario = GWT.create(IUsuario.class);
							
							servidorUsuario.eliminarPerfil(perfil, new AsyncCallback<Void>() {
								
								@Override
								public void onSuccess(Void result) {
									EntryPointPerfilList.this.cargarLista();
								}
								
								@Override
								public void onFailure(Throwable caught) {
									caught.printStackTrace();
									Window.alert("ERROR AJAX");
								}
							});
						
			    }
			
		}
		
		protected PermisoDTO buscarPermiso(String p){
			for(PermisoDTO per:permisosGlobal){
				if(per.getPermiso().equals(p)){
					return per;
				}
			}
			
			return null;
		}
	}

}
