package fing.satode.ui.desastres.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import fing.satode.data.DesastreDTO;
import fing.satode.data.EventoDTO;
import fing.satode.data.UsuarioDTO;
import fing.satode.ui.registros.client.IEvento;
import fing.satode.ui.registros.client.IEventoAsync;
import fing.satode.ui.usuarios.client.IUsuario;
import fing.satode.ui.usuarios.client.IUsuarioAsync;

public class EntryPointDesastre implements EntryPoint {

	final Button nuevoB = new Button("Nuevo");
	final VerticalPanel vertical = new VerticalPanel();
	private ArrayList<DesastreDTO> desastreGlobal;
	private UsuarioDTO usuarioGlobal;
	
	private ArrayList<EventoDTO> eventosGlobal;
	private Grid desastres;
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
		RootPanel.get("desastres").clear();
		vertical.clear();
		RootPanel.get("desastres").add(vertical);
		
		IDesastreAsync servidorDesastres = GWT.create(IDesastre.class);
		
		servidorDesastres.listaDesastres(new AsyncCallback<ArrayList<DesastreDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<DesastreDTO> result) {
				// TODO Auto-generated method stub
				desastreGlobal=result;
				desastres = new Grid(result.size()+1,9);
				desastres.setWidget(0, 0, new Label("ID"));
				desastres.setWidget(0, 1, new Label("Fecha Declaracion"));
				desastres.setWidget(0, 2, new Label("Tipo Evento"));
				desastres.setWidget(0, 3, new Label("Ciudad"));
				desastres.setWidget(0, 4, new Label("Usuario"));
				desastres.setWidget(0, 5, new Label("Muertos"));
				desastres.setWidget(0, 6, new Label("Perdidas Dolares"));
				desastres.setWidget(0, 7, modificarLabel);
				desastres.setWidget(0, 8, eliminarLabel);
				
				for(int i=0;i<9;i++){
					desastres.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				
				desastres.setBorderWidth(1);
				int row=1;
				DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
				for(DesastreDTO e: result){
					desastres.setWidget(row, 0, new Label(e.getId().toString()));
					desastres.setWidget(row, 1, new Label(format.format(e.getFechaDeclaracion())));
					desastres.setWidget(row, 2, new Label(e.getEvento().getTipoEvento().getNombre()));
					desastres.setWidget(row, 3, new Label(e.getEvento().getCiudad().getNombre()));
					desastres.setWidget(row, 4, new Label(e.getUsuario().getNombreCompleto()));
					desastres.setWidget(row, 5, new Label(String.valueOf(e.getEvento().getMuertos())));
					desastres.setWidget(row, 6, new Label(String.valueOf(e.getEvento().getPerdidasDolares())));
					
					final Long id= e.getId();
					final Image modificarI= new Image("images/modificar.png");
					modificarI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							// TODO Auto-generated method stub
							FormDialogBox dialog= new FormDialogBox(id, "modificar");
							dialog.show();
						}
					});
					
					final Image eliminarI= new Image("images/eliminar.png");
					
					eliminarI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							// TODO Auto-generated method stub
							FormDialogBox dialog= new FormDialogBox(id, "eliminar");
							dialog.show();
						}
					});
					desastres.setWidget(row, 7, modificarI);
					desastres.setWidget(row, 8, eliminarI);
					row++;
				}
				vertical.add(desastres);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
		

		IEventoAsync servidorEvento = GWT.create(IEvento.class);
		
		servidorEvento.listaEventos(new AsyncCallback<ArrayList<EventoDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<EventoDTO> result) {
				// TODO Auto-generated method stub
				eventosGlobal=result;
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
			
		IUsuarioAsync servidorUsuario= GWT.create(IUsuario.class);
		
		servidorUsuario.getUsuarioLogin(new AsyncCallback<UsuarioDTO>() {
			
			@Override
			public void onSuccess(UsuarioDTO result) {
				// TODO Auto-generated method stub
				usuarioGlobal=result;
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
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
		//final Label label = new Label();
		final CaptionPanel panelPrincipal = new CaptionPanel();
	    final Grid grid= new Grid(1,3);
	    private Grid gridEventos;
	    final Label fecha = new Label();
	    final DatePicker datePicker = new DatePicker();
		final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
	
	    public FormDialogBox(Long idDesastre, String accion) {
			// TODO Auto-generated constructor stub
	    	a=accion;
	    	id=idDesastre;
	    	
	    	
	    	   // Set the value in the text box when the user selects a date
		    datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
		      public void onValueChange(ValueChangeEvent<Date> event) {
		        Date date = (Date)event.getValue();
		        DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
		        String dateString = format.format(date);
		        fecha.setText(dateString);
		      }
		    });
		    datePicker.setValue(new Date(), true);
	        
		        
	    	grid.setWidget(0, 0, new Label("Fecha Declaracion"));
	    	grid.setWidget(0, 1, fecha);
	    	grid.setWidget(0, 2, datePicker);
	    	grid.setBorderWidth(1);
	    	
	    	gridEventos= new Grid(eventosGlobal.size()+1,8);
	    	gridEventos.setWidget(0, 0, new Label("#"));
	    	gridEventos.setWidget(0, 1, new Label("ID"));
	    	gridEventos.setWidget(0, 2, new Label("Fecha Inicio"));
	    	gridEventos.setWidget(0, 3, new Label("Tipo Evento"));
	    	gridEventos.setWidget(0, 4, new Label("Ciudad"));
	    	gridEventos.setWidget(0, 5, new Label("Fuente"));
	    	gridEventos.setWidget(0, 6, new Label("Muertos"));
	    	gridEventos.setWidget(0, 7, new Label("Perdidas Dolares"));
	    	
			for(int i=0;i<8;i++){
				gridEventos.getCellFormatter().setStyleName(0,i, "tbl-cab");
			}
			
			gridEventos.setBorderWidth(1);
			int row=1;
			DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
			for(EventoDTO e: eventosGlobal){
				RadioButton radio = new RadioButton("evento");
				gridEventos.setWidget(row, 0, radio);
				gridEventos.setWidget(row, 1, new Label(e.getId().toString()));
				gridEventos.setWidget(row, 2, new Label(format.format(e.getFechaInicio())));
				gridEventos.setWidget(row, 3, new Label(e.getTipoEvento().getNombre()));
				gridEventos.setWidget(row, 4, new Label(e.getCiudad().getNombre()));
				gridEventos.setWidget(row, 5, new Label(e.getFuente()));
				gridEventos.setWidget(row, 6, new Label(String.valueOf(e.getMuertos())));
				gridEventos.setWidget(row, 7, new Label(String.valueOf(e.getPerdidasDolares())));
				
				
				row++;
			}
			if(a=="modificar") panelPrincipal.setCaptionText("Modificar Desastre");
			if(a=="eliminar") panelPrincipal.setCaptionText("Eliminar Desastre");
			if(a=="nuevo") panelPrincipal.setCaptionText("Nuevo Desastre");
			
			if(a=="modificar" ||a=="eliminar"){
				DesastreDTO dto=null;
				for(DesastreDTO d:desastreGlobal){
					if(id.equals(d.getId())){
						dto=d;
						break;
					}
				}
				
				datePicker.setValue(dto.getFechaDeclaracion(), true);
				row=1;
				for(EventoDTO e:eventosGlobal){
					if(e.getId().equals(dto.getEvento().getId())){
						RadioButton radio=(RadioButton) gridEventos.getWidget(row, 0);
						radio.setValue(true);
						break;
					}
					row++;
				}
			}
			
			if(a=="eliminar"){
				datePicker.setVisible(false);
				for(int i=1;i<eventosGlobal.size()+1;i++){
					RadioButton radio=(RadioButton) gridEventos.getWidget(i, 0);
					radio.setEnabled(false);
				}
			}
			
			panelPrincipal.add(vertical);
			vertical.add(grid);
	    	vertical.add(gridEventos);
	    	horizontal.add(aceptar);
			horizontal.add(cancelar);
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
			add(panelPrincipal);
			center();
		}

		protected void procesar() {
			// TODO Auto-generated method stub
			DesastreDTO dto=validar();
			if(dto!=null){
				dto.setId(id);
				if(a=="modificar"){
					
					IDesastreAsync servidorDesastre=GWT.create(IDesastre.class);
					
					servidorDesastre.modificarDesastre(dto,new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							// TODO Auto-generated method stub
							cargarLista();
							hide();
						}
						
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							caught.printStackTrace();
							Window.alert("ERROR AJAX");
						}
					});
				}else if(a== "nuevo"){
					IDesastreAsync servidorDesastre=GWT.create(IDesastre.class);
					
					servidorDesastre.nuevoDesastre(dto,new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							// TODO Auto-generated method stub
							cargarLista();
							hide();
						}
						
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							caught.printStackTrace();
							Window.alert("ERROR AJAX");
						}
					});
				}else if(a=="eliminar"){
					for(DesastreDTO d:desastreGlobal){
						if(id.equals(d.getId())){
							dto=d;
							break;
						}
					}
					IDesastreAsync servidorDesastre=GWT.create(IDesastre.class);
					
					servidorDesastre.eliminarDesastre(dto,new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							// TODO Auto-generated method stub
							cargarLista();
							hide();
						}
						
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							caught.printStackTrace();
							Window.alert("ERROR AJAX");
						}
					});
				}
			}
		}

		private DesastreDTO validar() {
			// TODO Auto-generated method stub
			DesastreDTO dto= new DesastreDTO();
			
			dto.setFechaDeclaracion(datePicker.getValue()==null?new Date():datePicker.getValue());
			boolean salir=false;
			for(int i=1; i<eventosGlobal.size()+1 && !salir;i++){
				Long idEvento=Long.valueOf(((Label)gridEventos.getWidget(i, 1)).getText());
				RadioButton radio= (RadioButton) gridEventos.getWidget(i, 0);
				if(radio.getValue()){
					for(EventoDTO e: eventosGlobal){
						if(e.getId().equals(idEvento)){
							dto.setEvento(e);
							salir=true;
						}
					}
				}
			}
			if(!salir){
				Window.alert("Indique evento");
				return null;
			}
			dto.setUsuario(usuarioGlobal);
			
			return dto;
			
		}
	}
}
