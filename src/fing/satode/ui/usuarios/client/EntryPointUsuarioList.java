package fing.satode.ui.usuarios.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.SetRootRule;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


import fing.satode.dominio.Usuario;

public class EntryPointUsuarioList implements EntryPoint {

	private ArrayList<Usuario> usuariosList;
	private Grid usuarios;
	
	public static native void setWindowHref(String url)/*-{
		$wnd.location.href = url;
	}-*/; 
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		final Label usuarioLabel= new Label("Usuario");
		final Label modificarLabel= new Label("Modificar");
		final Label eliminarLabel= new Label("Eliminar");
		final VerticalPanel vertical = new VerticalPanel();
		RootPanel.get("usuarios").add(vertical);

		IUsuarioAsync servidorUsuario = GWT.create(IUsuario.class);
		
		servidorUsuario.listaUsuarios(new AsyncCallback<ArrayList<Usuario>>() {
			
			@Override
			public void onSuccess(ArrayList<Usuario> result) {
				// TODO Auto-generated method stub
				
				usuarios = new Grid(result.size()+1,3);
				usuarios.setWidget(0, 0, usuarioLabel);
				usuarios.setWidget(0, 1, modificarLabel);
				usuarios.setWidget(0, 2, eliminarLabel);
				int row=1;
				for(Usuario u : result){
					usuarios.setWidget(row, 0, new Label(u.getUsuario()));
					final Long id= u.getId();
					final Button modificarB = new Button("M");
					modificarB.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							// TODO Auto-generated method stub
							setWindowHref("/UsuarioForm.html?a='modificar'&id='"+id+"'");
						}
					});
					final Button eliminarB = new Button("E");
					eliminarB.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							// TODO Auto-generated method stub
							setWindowHref("/UsuarioForm.html?a='eliminar'&id='"+id+"'");
						}
					});
					usuarios.setWidget(row, 0, new Label(u.getUsuario()));
					usuarios.setWidget(row, 1, modificarB);
					usuarios.setWidget(row, 2, eliminarB);
					row++;
				}
				vertical.add(usuarios);
		
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				caught.printStackTrace();
				Window.alert("ERROR");
			}
		});
	
		
		}
				
		
}
