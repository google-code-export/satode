package fing.satode.ui.usuarios.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class EntryPointLogin implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		final TextBox usuario = new TextBox();
		final PasswordTextBox password= new PasswordTextBox();
		final Button send  =new Button("Enviar");
		
		RootPanel.get("usuario").add(usuario);
		RootPanel.get("password").add(password);
		RootPanel.get("boton").add(send);
		
		send.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				IUsuarioAsync serverUsuario = GWT.create(IUsuario.class);
				
				serverUsuario.login(usuario.getText(), password.getText(), new AsyncCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean result) {
						// TODO Auto-generated method stub
						if(result){
							Window.alert("Login OK!");
						}else{
							Window.alert("Login ERROR!");
						}
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("ERROR EN LA SOLICITUD");
					}
				});
			}
		});
		
		
	}

}
