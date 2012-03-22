package fing.satode.ui.usuarios.client;

import java.util.ArrayList;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


import fing.satode.data.PerfilDTO;
import fing.satode.data.PermisoDTO;
import fing.satode.data.UsuarioDTO;
import fing.satode.ui.usuarios.client.EntryPointPerfilList.FormDialogBox;

public class EntryPointUsuarioList implements EntryPoint {

	private ArrayList<UsuarioDTO> usuariosGlobal;
	private Grid usuarios;
	final Label usuarioLabel= new Label("Usuario");
	final Label usuarioNomLabel= new Label("Nombre");
	final Label modificarLabel= new Label("Modificar");
	final Label eliminarLabel= new Label("Eliminar");
	final VerticalPanel vertical = new VerticalPanel();
	final Button nuevoB = new Button("Nuevo");
	private ArrayList<PerfilDTO> perfilesGlobal;
	
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
		RootPanel.get("usuarios").clear();
		vertical.clear();
		RootPanel.get("usuarios").add(vertical);
		IUsuarioAsync servidorUsuario = GWT.create(IUsuario.class);
		
		servidorUsuario.listaUsuarios(new AsyncCallback<ArrayList<UsuarioDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<UsuarioDTO> result) {
				usuariosGlobal= result;
				usuarios = new Grid(result.size()+1,4);
				usuarios.getElement().setId("myTable");
				usuarios.setWidget(0, 0, usuarioLabel);
				usuarios.setWidget(0, 1, usuarioNomLabel);
				usuarios.setWidget(0, 2, modificarLabel);
				usuarios.setWidget(0, 3, eliminarLabel);
				usuarios.getCellFormatter().setStyleName(0,0, "tbl-cab");
				usuarios.getCellFormatter().setStyleName(0,1, "tbl-cab");
				usuarios.getCellFormatter().setStyleName(0,2, "tbl-cab");
				usuarios.getCellFormatter().setStyleName(0,3, "tbl-cab");
				usuarios.setBorderWidth(1);
				int row=1;
				for(UsuarioDTO u : result){
					usuarios.setWidget(row, 0, new Label(u.getUsuario()));
					usuarios.setWidget(row, 1, new Label(u.getNombreCompleto()));
					final Long id= u.getId();
					final Image modificarI= new Image("images/modificar.png");
					modificarI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							FormDialogBox dialog= new FormDialogBox(id, "modificar");
							dialog.show();
						}
					});
					
					final Image eliminarI= new Image("images/eliminar.png");
					
					eliminarI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							FormDialogBox dialog= new FormDialogBox(id, "eliminar");
							dialog.show();
						}
					});
					usuarios.setWidget(row, 0, new Label(u.getUsuario()));
					usuarios.setWidget(row, 2, modificarI);
					usuarios.setWidget(row, 3, eliminarI);
					row++;
				}
				vertical.add(usuarios);
		
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("ERROR");
			}
		});
	
		servidorUsuario.listaPerfiles(new AsyncCallback<ArrayList<PerfilDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<PerfilDTO> result) {
				perfilesGlobal=result;
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
	}

	public class FormDialogBox extends DialogBox{
		private String a;
		private Long id;
		final HorizontalPanel horizontal= new HorizontalPanel();
		final VerticalPanel vertical= new VerticalPanel();
		final CaptionPanel panelPrincipal = new CaptionPanel();
		final Grid grid= new Grid(5,2);
		final TextBox nombreCompleto= new TextBox();
		final TextBox usuario= new TextBox();
		final PasswordTextBox password= new PasswordTextBox();
		final PasswordTextBox passwordConfirm= new PasswordTextBox();
		final ListBox perfiles = new ListBox();
		final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
		
		public FormDialogBox(Long idUsuario, String accion){
			id=idUsuario;
			a=accion;
			
			grid.setWidget(0,0, new Label("Usuario"));
			grid.setWidget(0,1, usuario);
			grid.setWidget(1,0, new Label("Nombre Completo"));
			grid.setWidget(1,1, nombreCompleto);
			grid.setWidget(2,0, new Label("Password"));
			grid.setWidget(2,1, password);
			grid.setWidget(3,0, new Label("Password Confirmar"));
			grid.setWidget(3,1, passwordConfirm);
			grid.setWidget(4,0, new Label("Perfil"));
			grid.setWidget(4,1, perfiles);
			horizontal.add(aceptar);
			horizontal.add(cancelar);
			
			if(accion=="modificar"){
				panelPrincipal.setCaptionText("Modificar Usuario");
				UsuarioDTO usuarioAModificar=null;
				for(UsuarioDTO p:usuariosGlobal){
					if(id.equals(p.getId())){
						usuarioAModificar=p;
					}
				}
				
				usuario.setText(usuarioAModificar.getUsuario());
				nombreCompleto.setText(usuarioAModificar.getNombreCompleto());
				password.setText(usuarioAModificar.getPassword());
				passwordConfirm.setText(usuarioAModificar.getPassword());
				int index=1;
				perfiles.addItem("Seleccionar", "0");
				for(PerfilDTO pdto:perfilesGlobal){
					perfiles.addItem(pdto.getNombre(), pdto.getId().toString());
					if(usuarioAModificar.getPerfil()!=null && usuarioAModificar.getPerfil().getId().equals(pdto.getId())){
						perfiles.setItemSelected(index, true);
					}
					
					index++;
				}
				
				panelPrincipal.add(vertical);
				vertical.add(grid);
				vertical.add(horizontal);
				
				
			}else if(accion=="eliminar"){
				panelPrincipal.setCaptionText("Eliminar Usuario");
				UsuarioDTO usuarioAModificar=null;
				for(UsuarioDTO p:usuariosGlobal){
					if(id.equals(p.getId())){
						usuarioAModificar=p;
					}
				}
				
				usuario.setText(usuarioAModificar.getUsuario());
				nombreCompleto.setText(usuarioAModificar.getNombreCompleto());
				password.setText(usuarioAModificar.getPassword());
				passwordConfirm.setText(usuarioAModificar.getPassword());
				usuario.setEnabled(false);
				nombreCompleto.setEnabled(false);
				password.setEnabled(false);
				passwordConfirm.setEnabled(false);
				
				int index=1;
				perfiles.addItem("Seleccionar", "0");
				for(PerfilDTO pdto:perfilesGlobal){
					perfiles.addItem(pdto.getNombre(), pdto.getId().toString());
					if(usuarioAModificar.getPerfil().getId().equals(pdto.getId())){
						perfiles.setItemSelected(index, true);
					}
					
					index++;
				}
				perfiles.setEnabled(false);
				
				panelPrincipal.add(vertical);
				vertical.add(grid);
				vertical.add(horizontal);
				
			}else if(accion=="nuevo"){
				panelPrincipal.setCaptionText("Nuevo Usuario");
				
				perfiles.addItem("Seleccionar", "0");
				for(PerfilDTO pdto:perfilesGlobal){
					perfiles.addItem(pdto.getNombre(), pdto.getId().toString());
				}

				panelPrincipal.add(vertical);
				vertical.add(grid);
				vertical.add(horizontal);
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
				}
			});
				
			setAnimationEnabled(true);
			add(panelPrincipal);
			center();
		}

		protected void procesar() {
			UsuarioDTO usuarioDTO= validar();
			if(usuarioDTO!=null){
			
				if(a=="nuevo"){
					IUsuarioAsync servidorUsuario = GWT.create(IUsuario.class);
					
					servidorUsuario.nuevoUsuario(usuarioDTO, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							cargarLista();
							FormDialogBox.this.hide();
						}
						
						@Override
						public void onFailure(Throwable caught) {
								caught.printStackTrace();
								Window.alert("ERROR AJAX");
						}
					});
				}else if(a=="modificar"){
					usuarioDTO.setId(id);
					IUsuarioAsync servidorUsuario = GWT.create(IUsuario.class);
					
					servidorUsuario.modificarUsuario(usuarioDTO, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							cargarLista();
							FormDialogBox.this.hide();
						}
						
						@Override
						public void onFailure(Throwable caught) {
								caught.printStackTrace();
								Window.alert("ERROR AJAX");
						}
					});
				}else if(a=="eliminar"){
					usuarioDTO.setId(id);
					IUsuarioAsync servidorUsuario = GWT.create(IUsuario.class);
					
					servidorUsuario.eliminarUsuario(usuarioDTO, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							cargarLista();
							FormDialogBox.this.hide();
						}
						
						@Override
						public void onFailure(Throwable caught) {
								caught.printStackTrace();
								Window.alert("ERROR AJAX");
						}
					});
				}
			
			}
		}

		private UsuarioDTO validar() {
			boolean ok=true;
			if(usuario.getText().trim().length()==0){
				Window.alert("Indique usuario");
				ok=false;
			}else{
				if(a=="nuevo")
				for(UsuarioDTO u:usuariosGlobal){
					if(u.getUsuario().toLowerCase().equals(usuario.getText().toLowerCase())){
						ok=false;
						Window.alert("Ya existe el usuario, indique otro");
						break;
					}
				}
				
				if(ok){
					if(password.getText().trim().length()==0 ){
						Window.alert("Indique password");
						ok=false;
					}else{
						if(!password.getText().trim().equals(passwordConfirm.getText().trim())){
							ok=false;
							Window.alert("El password y la confimrmaci\u00F3n no coinciden");
						}else if(nombreCompleto.getText().trim().length()==0){
							Window.alert("Indique nombre completo");
							ok=false;
						}else if(perfiles.getSelectedIndex()<1){
							Window.alert("Indique perfil");
							ok=false;
						}
					}
				}
			}
			
			if(ok){
				UsuarioDTO usuarioDTO = new UsuarioDTO();
				usuarioDTO.setNombreCompleto(nombreCompleto.getText());
				usuarioDTO.setPassword(password.getText());
				usuarioDTO.setUsuario(usuario.getText());
				for(PerfilDTO p:perfilesGlobal){
					if(p.getId().equals(Long.valueOf(perfiles.getValue(perfiles.getSelectedIndex())))){
						usuarioDTO.setPerfil(p);
						break;
					}
				}
				
				return usuarioDTO;
				
			}else{
				return null;
			}
		}
	}
}
