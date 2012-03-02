package fing.satode.ui.usuarios.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import fing.satode.data.UsuarioDTO;


public class EntryPointLogin   implements EntryPoint {

	public static native void setWindowHref(String url)/*-{
		$wnd.location.href = url;
	}-*/; 

	@Override
	public void onModuleLoad() {
		
		final TextBox usuario = new TextBox();
		final PasswordTextBox password= new PasswordTextBox();
		final Button send  =new Button("Enviar");
		
		RootPanel.get("usuario").add(usuario);
		RootPanel.get("password").add(password);
		RootPanel.get("boton").add(send);
		IUsuarioAsync serverUsuario = GWT.create(IUsuario.class);
		serverUsuario.limpiarSession(new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
		send.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final IUsuarioAsync serverUsuario = GWT.create(IUsuario.class);
				
				serverUsuario.login(usuario.getText(), password.getText(), new AsyncCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean result) {
						if(result){
							setWindowHref("/Inicial.html");							 
						}else{
							DialogBox dialogBox = new DialogBox();
							VerticalPanel panel = new VerticalPanel();
							panel.add(new HTML("<b>Usuario o contraseña inv&aacutelida</b>"));
							dialogBox.add(panel);
							dialogBox.center();
							dialogBox.show();
						}
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ERROR EN LA SOLICITUD");
					}
				});
			}
		});
		
		
	}

}
