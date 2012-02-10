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
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

import fing.satode.constantes.EstadoSuministro;
import fing.satode.data.DepositoDTO;
import fing.satode.data.DonacionDTO;
import fing.satode.data.PuntoReferenciaDTO;
import fing.satode.data.SuministroDTO;
import fing.satode.data.TipoSuministroDTO;
import fing.satode.ui.general.data.KeyNumeric;
import fing.satode.ui.puntoReferencias.client.IPuntoReferencia;
import fing.satode.ui.puntoReferencias.client.IPuntoReferenciaAsync;

public class EntryPointSuministro implements EntryPoint {

	final Button nuevoB = new Button("Nuevo");
	final Button confirmar = new Button("Confirmar");
	final VerticalPanel vertical = new VerticalPanel();
	private ArrayList<DonacionDTO> donacionesGlobal;
	private ArrayList<TipoSuministroDTO> tipoSuministrosGlobal;
	private ArrayList<PuntoReferenciaDTO> puntosReferenciaGlobal;
	private ArrayList<DepositoDTO> depositosGlobal;
	private Grid donaciones;
	final Label modificarLabel= new Label("Modificar");
	final Label eliminarLabel= new Label("Eliminar");
	public static Grid gridSuministros= new Grid(5,2);
	public static DonacionDTO donacionDTO;
	public static  Grid suministros;
	private static Long numerador=10000000000L;
	private static Long baseNumerador=10000000000L;
	  
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
		RootPanel.get("donaciones").clear();
		vertical.clear();
		RootPanel.get("donaciones").add(vertical);
		
		IDepositoAsync servidorDepositos= GWT.create(IDeposito.class);
		
		servidorDepositos.listaDonaciones(new AsyncCallback<ArrayList<DonacionDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<DonacionDTO> result) {
				donacionesGlobal=result;
				donaciones = new Grid(result.size()+1,8);
				
				donaciones.setWidget(0, 0, new Label("ID"));
				donaciones.setWidget(0, 1, new Label("Fecha"));
				donaciones.setWidget(0, 2, new Label("Donante"));
				donaciones.setWidget(0, 3, new Label("Deposito"));
				donaciones.setWidget(0, 4, new Label("Punto Entrada"));
				donaciones.setWidget(0, 5, modificarLabel);
				donaciones.setWidget(0, 6, eliminarLabel);
				donaciones.setWidget(0, 7, new Label("Confirmar"));
				
				for(int i=0;i<8;i++){
					donaciones.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				
				donaciones.setBorderWidth(1);
				int row=1;
				DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
				for(DonacionDTO s: result){
					donaciones.setWidget(row, 0, new Label(s.getId().toString()));
					donaciones.setWidget(row, 1, new Label(format.format(s.getFecha())));
					donaciones.setWidget(row, 2, new Label(s.getDonante()));
					donaciones.setWidget(row, 3, new Label(s.getDeposito().getCiudad().getNombre()+"-"+ s.getDeposito().getDireccion()));
					donaciones.setWidget(row, 4, new Label(s.getPuntoEntrada().getCiudad().getNombre()+"-"+ s.getPuntoEntrada().getDireccion()));
					final Long id= s.getId();
					if(!s.isImpactarCuentas()){
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
						
						confirmar.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								// TODO Auto-generated method stub
								DonacionDTO dto=null;
								for(DonacionDTO d:donacionesGlobal){
									if(d.getId().equals(id)){
										dto=d;
									}
								}
								
								IDepositoAsync servidorDepositos= GWT.create(IDeposito.class);
								
								servidorDepositos.confirmar(dto, new AsyncCallback<Void>() {
									
									@Override
									public void onSuccess(Void result) {
										// TODO Auto-generated method stub
										cargarLista();
									}
									
									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub
										caught.printStackTrace();
										Window.alert("ERROR AJAX");
									}
								});
							}
						});
						
						donaciones.setWidget(row, 5, modificarI);
						donaciones.setWidget(row, 6, eliminarI);
						donaciones.setWidget(row, 7, confirmar);
					}
					row++;
				}
				vertical.add(donaciones);
				
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
		
		servidorDepositos.listaDepositos(new AsyncCallback<ArrayList<DepositoDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<DepositoDTO> result) {
				// TODO Auto-generated method stub
				depositosGlobal=result;
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
				
			}
		});
		
		IPuntoReferenciaAsync serverPuntoRef=GWT.create(IPuntoReferencia.class);
		
		serverPuntoRef.listPuntoEntrada(new AsyncCallback<ArrayList<PuntoReferenciaDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<PuntoReferenciaDTO> result) {
				// TODO Auto-generated method stub
				puntosReferenciaGlobal=result;
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
		final Label label = new Label();
	  
	    final Grid gridFecha= new Grid(1,2);
	    final DatePicker datePicker = new DatePicker();
	    final TextBox donante =new TextBox();
	    final TextBox fecha= new TextBox();
	    final ListBox depositos=new ListBox();
	    final ListBox puntoEntrada=new ListBox();
	   
	    final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
		final Button nuevoB = new Button("Nuevo");
		
		public FormDialogBox(Long idSuministro, String accion) {
			a=accion;
			id=idSuministro;
			
			datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			      public void onValueChange(ValueChangeEvent<Date> event) {
			        Date date = (Date)event.getValue();
			        DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
			        String dateString = format.format(date);
			        fecha.setText(dateString);
			      }
			    });
			    datePicker.setValue(new Date(), true);
		    	
		    	if(a=="modificar") label.setText("Modificar Donacion");
				if(a=="eliminar") label.setText("Eliminar Donacion");
				if(a=="nuevo") label.setText("Nuevo Donacion");
				
				gridSuministros.setWidget(0, 0, new Label("Fecha"));
				gridSuministros.setWidget(1, 0, new Label("Donante"));
				gridSuministros.setWidget(2, 0, new Label("Deposito"));
				gridSuministros.setWidget(3, 0, new Label("Punto de Entrada"));
				
				gridFecha.setWidget(0, 0, fecha);
				gridFecha.setWidget(0, 1, datePicker);
				gridSuministros.setWidget(0, 1, gridFecha);
				gridSuministros.setWidget(1, 1, donante);
				
				depositos.addItem("Seleccionar", "0");
				for(DepositoDTO d: depositosGlobal){
					depositos.addItem(d.getId()+"-"+d.getCiudad().getNombre()+"-"+d.getDireccion(),String.valueOf(d.getId()));
				}
				gridSuministros.setWidget(2, 1,depositos);
				
				puntoEntrada.addItem("Seleccionar", "0");
				for(PuntoReferenciaDTO d: puntosReferenciaGlobal){
					puntoEntrada.addItem(d.getId()+"-"+d.getCiudad().getNombre()+"-"+d.getDireccion(),String.valueOf(d.getId()));
				}
				gridSuministros.setWidget(3, 1,puntoEntrada);
				
				gridSuministros.setBorderWidth(1);
		    	
				nuevoB.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						FormDialogSuministroBox dialog= new FormDialogSuministroBox(null, "nuevo");
						dialog.show();
					}
				});
				
				gridSuministros.setWidget(4, 1,nuevoB);
				
				suministros= new Grid(1, 5);
			    suministros.setWidget(0, 0, new Label("Tipo Suministro"));
			    suministros.setWidget(0, 1, new Label("Estado"));
			    suministros.setWidget(0, 2, new Label("Cantidad"));
			    suministros.setWidget(0, 3, new Label("Modificar"));
			    suministros.setWidget(0, 4, new Label("Eliminar"));
			    gridSuministros.setWidget(4, 0, suministros);
			    for(int i=0;i<5;i++){
					suministros.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				donacionDTO=new DonacionDTO();
				
				if (a == "modificar" || a == "eliminar" ){
					for(DonacionDTO e:donacionesGlobal){
		    			if(e.getId().equals(id)){
		    				donacionDTO=e;
		    			}
		    		}
		    		
			    	donante.setText(donacionDTO.getDonante());
				    datePicker.setValue(donacionDTO.getFecha());
				    
				    depositos.clear();
				    depositos.addItem("Seleccionar", "0");
				    int index=1;
					for(DepositoDTO d: depositosGlobal){
						depositos.addItem(d.getId()+"-"+d.getCiudad().getNombre()+"-"+d.getDireccion(),String.valueOf(d.getId()));
						if(d.getId().equals(donacionDTO.getDeposito().getId())){
							depositos.setSelectedIndex(index);
						}
						index++;
					}
					
				    puntoEntrada.clear();
				    puntoEntrada.addItem("Seleccionar", "0");
				    index=1;
					for(PuntoReferenciaDTO d: puntosReferenciaGlobal){
						puntoEntrada.addItem(d.getId()+"-"+d.getCiudad().getNombre()+"-"+d.getDireccion(),String.valueOf(d.getId()));
						if(d.getId().equals(donacionDTO.getPuntoEntrada().getId())){
							puntoEntrada.setSelectedIndex(index);
						}
						index++;
					}
					
				    suministros= new Grid(donacionDTO.getSuministros().size()+1, 5);
				    suministros.setWidget(0, 0, new Label("Tipo Suministro"));
				    suministros.setWidget(0, 1, new Label("Estado"));
				    suministros.setWidget(0, 2, new Label("Cantidad"));
				    suministros.setWidget(0, 3, new Label("Modificar"));
				    suministros.setWidget(0, 4, new Label("Eliminar"));
				    gridSuministros.setWidget(4, 0, suministros);
				    
					for(int i=0;i<5;i++){
						suministros.getCellFormatter().setStyleName(0,i, "tbl-cab");
					}
					
					suministros.setBorderWidth(1);
					
					int row=1;
					for(SuministroDTO s: donacionDTO.getSuministros()){
						suministros.setWidget(row, 0, new Label(s.getTipo().getNombre()));
						suministros.setWidget(row, 1, new Label(EstadoSuministro.getTXT(s.getEstado())));
						suministros.setWidget(row, 2, new Label(String.valueOf(s.getCantidad())));
						final SuministroDTO sum= s;
						final Image modificarI= new Image("/images/modificar.png");
						modificarI.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								FormDialogSuministroBox dialog= new FormDialogSuministroBox(sum, "modificar");
								dialog.show();
							}
						});
						
						final Image eliminarI= new Image("/images/eliminar.png");
						
						eliminarI.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								FormDialogSuministroBox dialog= new FormDialogSuministroBox(sum, "eliminar");
								dialog.show();
							}
						});
						if(a == "modificar" ){
							suministros.setWidget(row, 3, modificarI);
							suministros.setWidget(row, 4, eliminarI);
						}
						row++;
					}
			    }
			    
			    if ( a == "eliminar" ){
			    	donante.setEnabled(false);
			    	fecha.setEnabled(false);
			    	depositos.setEnabled(false);
			    	nuevoB.setEnabled(false);
			    }
			    
				vertical.add(label);
				vertical.add(gridSuministros);
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
			DonacionDTO dto=validar();
			
			if(dto!=null){
				for(SuministroDTO s: dto.getSuministros()){
					if(s.getId()>=baseNumerador){
						s.setId(null);
					}
				}
				dto.setId(id);
				if(a=="modificar"){
					
					IDepositoAsync servidorDeposito=GWT.create(IDeposito.class);
					
					servidorDeposito.modificarDonacion(dto,new AsyncCallback<Void>() {
						
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
					
					servidorDeposito.nuevoDonacion(dto, new AsyncCallback<Void>() {
						
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
					IDepositoAsync servidorDeposito=GWT.create(IDeposito.class);
					
					servidorDeposito.eliminarDonacion(dto, new AsyncCallback<Void>() {
						
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
		
		private DonacionDTO validar() {
			DonacionDTO dto= donacionDTO;
			
			if(donante.getText().trim().length()== 0){
				Window.alert("Indique Donante");
				return null;
			}
			
			if(depositos.getSelectedIndex()<1){
				Window.alert("Indique Deposito destino");
				return null;
			}
			
			if(puntoEntrada.getSelectedIndex()<1){
				Window.alert("Indique Punto Entrada");
				return null;
			}
			
			dto.setFecha(datePicker.getValue());
			dto.setDonante(donante.getText());
			
			for(DepositoDTO d: depositosGlobal){
				if( d.getId().equals( Long.valueOf(depositos.getValue(depositos.getSelectedIndex()) ))){
					dto.setDeposito(d);
				}
			}
			
			for(PuntoReferenciaDTO d: puntosReferenciaGlobal){
				if( d.getId().equals( Long.valueOf(puntoEntrada.getValue(puntoEntrada.getSelectedIndex()) ))){
					dto.setPuntoEntrada(d);
				}
			}
			
			return dto;
		}
		
			
	}
	
	public class FormDialogSuministroBox extends DialogBox{
		private String a;
		private SuministroDTO suministro;
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
		public FormDialogSuministroBox(SuministroDTO sum, String accion) {
			a=accion;
	    	suministro=sum;
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
			
			tipoSuministro.addChangeListener(new ChangeListener() {
				
				@Override
				public void onChange(Widget sender) {
					// TODO Auto-generated method stub
					TipoSuministroDTO tipo=null;
					for(TipoSuministroDTO t:tipoSuministrosGlobal){
						if(t.getId().equals(Long.valueOf(tipoSuministro.getValue(tipoSuministro.getSelectedIndex())))){
							if(t.getFechaVencimiento()){
								gridFecha.setWidget(0, 0, fechavencimiento);
								gridFecha.setWidget(0, 1, datePicker);
								grid.setWidget(3, 0, new Label("Vencimiento"));
							}else{
								gridFecha.setWidget(0, 0, new Label(""));
								gridFecha.setWidget(0, 1, new Label(""));
								grid.setWidget(3, 0, new Label(""));
							}
						}
					}
				}
			});
			
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
		    	
		    	int row=0;
	    		boolean salir = false;
	    		while (!salir) {
					row++;
					if(tipoSuministro.getValue(row).equals(String.valueOf(sum.getTipo().getId()))){
						tipoSuministro.setSelectedIndex(row);
						salir = true;
					}
				}
	    		
	    		cantidad.setText(String.valueOf(sum.getCantidad()));
			    estado.setSelectedIndex(sum.getEstado()-1);
			    DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
			    fechavencimiento.setText(format.format(sum.getFechaVencimiento()));
			    
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
					FormDialogSuministroBox.this.hide();
				}
			});
			
			aceptar.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					FormDialogSuministroBox.this.procesar();
				}
			});
				
			setAnimationEnabled(true);
			add(vertical);
			center();
		}

		protected void procesar() {
			SuministroDTO dto=validar();
			if(dto!=null){
			
				if(a=="modificar"){
					dto.setId(suministro.getId());
					for(SuministroDTO s:donacionDTO.getSuministros()){
						if(s.getId().equals(dto.getId())){
							s.setCantidad(dto.getCantidad());
							s.setEstado(dto.getEstado());
							s.setFechaVencimiento(dto.getFechaVencimiento());
							s.setTipo(dto.getTipo());
						}
					}
				}else if(a== "nuevo"){
					dto.setId(numerador++);
					donacionDTO.getSuministros().add(dto);
				}else if(a=="eliminar"){
					dto.setId(suministro.getId());
					SuministroDTO del=null;
					for(SuministroDTO s:donacionDTO.getSuministros()){
						if(s.getId().equals(dto.getId())){
							del=s;
						}
					}
					donacionDTO.getSuministros().remove(del);
				}
				
				   suministros= new Grid(donacionDTO.getSuministros().size()+1, 5);
				    suministros.setWidget(0, 0, new Label("Tipo Suministro"));
				    suministros.setWidget(0, 1, new Label("Estado"));
				    suministros.setWidget(0, 2, new Label("Cantidad"));
				    suministros.setWidget(0, 3, new Label("Modificar"));
				    suministros.setWidget(0, 4, new Label("Eliminar"));
				    gridSuministros.setWidget(4, 0, suministros);
				    
					for(int i=0;i<5;i++){
						suministros.getCellFormatter().setStyleName(0,i, "tbl-cab");
					}
					
					suministros.setBorderWidth(1);
					
					int row=1;
					for(SuministroDTO s: donacionDTO.getSuministros()){
						suministros.setWidget(row, 0, new Label(s.getTipo().getNombre()));
						suministros.setWidget(row, 1, new Label(EstadoSuministro.getTXT(s.getEstado())));
						suministros.setWidget(row, 2, new Label(String.valueOf(s.getCantidad())));
						final SuministroDTO sum= s;
						final Image modificarI= new Image("/images/modificar.png");
						modificarI.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								FormDialogSuministroBox dialog= new FormDialogSuministroBox(sum, "modificar");
								dialog.show();
							}
						});
						
						final Image eliminarI= new Image("/images/eliminar.png");
						
						eliminarI.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								FormDialogSuministroBox dialog= new FormDialogSuministroBox(sum, "eliminar");
								dialog.show();
							}
						});
						suministros.setWidget(row, 3, modificarI);
						suministros.setWidget(row, 4, eliminarI);
						row++;
					}
					hide();
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
					
					if(t.getFechaVencimiento()){
						if(fechavencimiento.getText().trim().length()==0){
							Window.alert("Indique Fecha de vencimiento");
							return null;
						}
						DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
					    
						dto.setFechaVencimiento(format.parse(fechavencimiento.getText()));
						
					}
				}
			}
			
			if(cantidad.getText().trim().length()==0){
				Window.alert("Indique Cantidad");
				return null;
			}
			dto.setCantidad(Integer.valueOf(cantidad.getText()));
			
			
			
			dto.setEstado(estado.getSelectedIndex()+1);
			
			return dto;
			
		}
		
	}



}
