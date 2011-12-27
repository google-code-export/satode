package fing.satode.ui.registros.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
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

import fing.satode.dominio.Evento;
import fing.satode.ui.usuarios.client.IUsuario;
import fing.satode.ui.usuarios.client.IUsuarioAsync;
import fing.satode.ui.usuarios.client.EntryPointUsuarioList.FormDialogBox;

public class EntryPointEvento implements EntryPoint {

	final Button nuevoB = new Button("Nuevo");
	final VerticalPanel vertical = new VerticalPanel();
	private ArrayList<Evento> eventosGlobal;
	private Grid eventos;
	final Label modificarLabel= new Label("Modificar");
	final Label eliminarLabel= new Label("Eliminar");
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		RootPanel.get("botones").add(nuevoB);
		nuevoB.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				FormDialogBox dialog= new FormDialogBox(0L, "nuevo");
				dialog.show();
			}
		});
		cargarLista();	
	}

	private void cargarLista() {
		// TODO Auto-generated method stub
		RootPanel.get("eventos").clear();
		vertical.clear();
		RootPanel.get("eventos").add(vertical);
		
		IEventoAsync servidorEvento = GWT.create(IEvento.class);
		
		servidorEvento.listaEventos(new AsyncCallback<ArrayList<Evento>>() {
			
			@Override
			public void onSuccess(ArrayList<Evento> result) {
				// TODO Auto-generated method stub
				eventos = new Grid(result.size()+1,4);
				eventos.setWidget(0, 0, new Label("ID"));
				eventos.setWidget(0, 1, new Label("Fecha Inicio"));
				eventos.setWidget(0, 2, modificarLabel);
				eventos.setWidget(0, 3, eliminarLabel);
				eventos.getCellFormatter().setStyleName(0,0, "tbl-cab");
				eventos.getCellFormatter().setStyleName(0,1, "tbl-cab");
				eventos.getCellFormatter().setStyleName(0,2, "tbl-cab");
				eventos.getCellFormatter().setStyleName(0,3, "tbl-cab");
				eventos.setBorderWidth(1);
				int row=1;
				DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
				for(Evento e: result){
					eventos.setWidget(row, 0, new Label(e.getId().toString()));
					eventos.setWidget(row, 1, new Label(format.format(e.getFechaInicio())));
					final Long id= e.getId();
					final Image modificarI= new Image("/images/modificar.png");
					modificarI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							// TODO Auto-generated method stub
							FormDialogBox dialog= new FormDialogBox(id, "modificar");
							dialog.show();
						}
					});
					
					final Image eliminarI= new Image("/images/eliminar.png");
					
					eliminarI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							// TODO Auto-generated method stub
							FormDialogBox dialog= new FormDialogBox(id, "eliminar");
							dialog.show();
						}
					});
					eventos.setWidget(row, 2, modificarI);
					eventos.setWidget(row, 3, eliminarI);
					row++;
				}
				vertical.add(eventos);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				caught.printStackTrace();
				Window.alert("ERROR");
			}
		});
		
		
	}
	
	public class FormDialogBox extends DialogBox{
		
		private String a;
		private Long id;
		final HorizontalPanel horizontal= new HorizontalPanel();
		final VerticalPanel vertical= new VerticalPanel();
		final Label label = new Label();
		final Grid grid= new Grid(5,2);
		final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
		
		public FormDialogBox(Long idEvento , String accion){
			super();
			a=accion;
			id=idEvento;
			
			horizontal.add(aceptar);
			horizontal.add(cancelar);
			
			vertical.add(label);
			vertical.add(grid);
			vertical.add(horizontal);
			
			
			cancelar.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					FormDialogBox.this.hide();
				}
			});
			
			aceptar.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					FormDialogBox.this.procesar();
				}
			});
			
			setAnimationEnabled(true);
			add(vertical);
			center();
		}

		protected void procesar() {
			// TODO Auto-generated method stub
			this.hide();
		}
	
	}

}
