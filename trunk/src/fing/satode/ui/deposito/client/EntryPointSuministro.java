package fing.satode.ui.deposito.client;

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
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import fing.satode.data.CiudadDTO;
import fing.satode.data.EstadoSuministro;
import fing.satode.data.SuministroDTO;
import fing.satode.data.TipoSuministroDTO;
import fing.satode.dominio.TipoSuministro;
import fing.satode.ui.general.data.KeyNumeric;

public class EntryPointSuministro implements EntryPoint {

	final Button nuevoB = new Button("Nuevo");
	final VerticalPanel vertical = new VerticalPanel();
	private ArrayList<SuministroDTO> suministrosGlobal;
	private ArrayList<TipoSuministroDTO> tipoSuministrosGlobal;
	private Grid sumninistros;
	final Label modificarLabel= new Label("Modificar");
	final Label eliminarLabel= new Label("Eliminar");
	
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
		RootPanel.get("suministros").clear();
		vertical.clear();
		RootPanel.get("suministros").add(vertical);
		
		IDepositoAsync servidorDepositos= GWT.create(IDeposito.class);
		
		servidorDepositos.listaSuministros(new AsyncCallback<ArrayList<SuministroDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<SuministroDTO> result) {
				suministrosGlobal=result;
				sumninistros = new Grid(result.size()+1,7);
				
				sumninistros.setWidget(0, 0, new Label("ID"));
				sumninistros.setWidget(0, 1, new Label("Tipo de suministro"));
				sumninistros.setWidget(0, 2, new Label("Cantidad"));
				sumninistros.setWidget(0, 3, new Label("Estado"));
				sumninistros.setWidget(0, 4, new Label("Fecha de Vencimiento"));
				sumninistros.setWidget(0, 5, modificarLabel);
				sumninistros.setWidget(0, 6, eliminarLabel);
				
				for(int i=0;i<7;i++){
					sumninistros.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				
				sumninistros.setBorderWidth(1);
				int row=1;
				for(SuministroDTO s: result){
					sumninistros.setWidget(row, 0, new Label(s.getId().toString()));
					sumninistros.setWidget(row, 1, new Label(s.getTipo().getNombre()));
					sumninistros.setWidget(row, 2, new Label(String.valueOf(s.getCantidad())));
					sumninistros.setWidget(row, 3, new Label(EstadoSuministro.getTXT(s.getEstado())));
					DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
				    String dateString = format.format(s.getFechaVencimiento());
					
					sumninistros.setWidget(row, 4, new Label(dateString));
					final Long id= s.getId();
					final Image modificarI= new Image("/images/modificar.png");
					modificarI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							FormDialogBox dialog= new FormDialogBox(id, "modificar");
							dialog.show();
						}
					});
					
					final Image eliminarI= new Image("/images/eliminar.png");
					
					eliminarI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							FormDialogBox dialog= new FormDialogBox(id, "eliminar");
							dialog.show();
						}
					});
					sumninistros.setWidget(row, 5, modificarI);
					sumninistros.setWidget(row, 6, eliminarI);
					row++;
				}
				vertical.add(sumninistros);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
		
		
		servidorDepositos.listaTipoSuministros(new AsyncCallback<ArrayList<TipoSuministroDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<TipoSuministroDTO> result) {
				tipoSuministrosGlobal=result;
					
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
		final Label label = new Label();
	    final Grid grid= new Grid(4,2);
	    final Grid gridFecha= new Grid(1,2);
	    
	
		
	    final ListBox tipoSuministro= new ListBox();
	    final TextBox cantidad =new TextBox();
	    final ListBox estado = new ListBox();
	    final TextBox fechavencimiento= new TextBox();
	    final DatePicker datePicker = new DatePicker();
	    final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
		
		
	
	    @SuppressWarnings("deprecation")
		public FormDialogBox(Long idSuministro, String accion) {
			a=accion;
	    	id=idSuministro;
	    	
	    	 // Set the value in the text box when the user selects a date
		    datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
		      public void onValueChange(ValueChangeEvent<Date> event) {
		        Date date = (Date)event.getValue();
		        DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
		        String dateString = format.format(date);
		        fechavencimiento.setText(dateString);
		      }
		    });
		    datePicker.setValue(new Date(), true);
	    	
	    	if(a=="modificar") label.setText("Modificar Suministro");
			if(a=="eliminar") label.setText("Eliminar Suministro");
			if(a=="nuevo") label.setText("Nuevo Suministro");
			
			grid.setWidget(0, 0, new Label("Tipo de Suministro"));
			grid.setWidget(1, 0, new Label("Cantidad"));
			grid.setWidget(2, 0, new Label("Estado"));
			grid.setWidget(3, 0, new Label("Vencimiento"));
			
			grid.setWidget(0, 1, tipoSuministro);
			grid.setWidget(1, 1, cantidad);
			grid.setWidget(2, 1, estado);
			
			gridFecha.setWidget(0, 0, fechavencimiento);
			gridFecha.setWidget(0, 1, datePicker);
			grid.setWidget(3, 1, gridFecha);
			grid.setBorderWidth(1);
	    	
			estado.addItem(EstadoSuministro.BUENO_TXT);
			estado.addItem(EstadoSuministro.INTERMEDIO_TXT);
			estado.addItem(EstadoSuministro.MALO_TXT);
			
			cantidad.addKeyboardListener(new KeyNumeric());
			
			tipoSuministro.addItem("Seleccionar","0");
			for (TipoSuministroDTO dto : tipoSuministrosGlobal) {
				tipoSuministro.addItem(dto.getNombre(), dto.getId().toString());
			}
			
			if (a == "modificar" || a == "eliminar" ){
		    	
		    	SuministroDTO suministroDTO= null;
	    		for(SuministroDTO s:suministrosGlobal){
	    			if(s.getId().equals(id)){
	    				suministroDTO=s;
	    			}
	    		}
	    		
	    		int row=0;
	    		boolean salir = false;
	    		while (!salir) {
					row++;
					if(tipoSuministro.getValue(row).equals(String.valueOf(suministroDTO.getTipo().getId()))){
						tipoSuministro.setSelectedIndex(row);
						salir = true;
					}
				}
	    		
	    		cantidad.setText(String.valueOf(suministroDTO.getCantidad()));
			    estado.setSelectedIndex(suministroDTO.getEstado());
			    DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
			    fechavencimiento.setText(format.format(suministroDTO.getFechaVencimiento()));
			    
		    }
		    
		    if ( a == "eliminar" ){
		    	tipoSuministro.setEnabled(false);
		    	cantidad.setEnabled(false);
		    	estado.setEnabled(false);
		    	fechavencimiento.setEnabled(false);
		    }
		    
			vertical.add(label);
			vertical.add(grid);
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
					FormDialogBox.this.procesar();
				}
			});
				
			setAnimationEnabled(true);
			add(vertical);
			center();
		}

		protected void procesar() {
			SuministroDTO dto=validar();
			if(dto!=null){
				dto.setId(id);
				if(a=="modificar"){
					
					IDepositoAsync servidorDeposito=GWT.create(IDeposito.class);
					
					servidorDeposito.modificarSuministro(dto,new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							cargarLista();
							hide();
						}
						
						@Override
						public void onFailure(Throwable caught) {
							caught.printStackTrace();
							Window.alert("ERROR AJAX");
						}
					});
				}else if(a== "nuevo"){
					IDepositoAsync servidorDeposito=GWT.create(IDeposito.class);
					
					servidorDeposito.nuevoSuministro(dto,new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							cargarLista();
							hide();
						}
						
						@Override
						public void onFailure(Throwable caught) {
							caught.printStackTrace();
							Window.alert("ERROR AJAX");
						}
					});
				}else if(a=="eliminar"){
					IDepositoAsync servidorDeposito=GWT.create(IDeposito.class);
					
					servidorDeposito.eliminarSuministro(dto,new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							cargarLista();
							hide();
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

		private SuministroDTO validar() {
			SuministroDTO dto= new SuministroDTO();
			
			if(tipoSuministro.getSelectedIndex()== 0){
				Window.alert("Indique Tipo de Suministro");
				return null;
			}
			for (TipoSuministroDTO t : tipoSuministrosGlobal) {
				if(t.getId().equals(Long.valueOf(tipoSuministro.getValue(tipoSuministro.getSelectedIndex())))){
					dto.setTipo(t);
				}
			}
			
			if(cantidad.getText().trim().length()==0){
				Window.alert("Indique Cantidad");
				return null;
			}
			dto.setCantidad(Integer.valueOf(cantidad.getText()));
			
			
			if(fechavencimiento.getText().trim().length()==0){
				Window.alert("Indique Fecha de vencimiento");
				return null;
			}
			DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
		    
			dto.setFechaVencimiento(format.parse(fechavencimiento.getText()));
			
			dto.setEstado(estado.getSelectedIndex()+1);
			
			return dto;
			
		}
		
		
	}


}
