package fing.satode.ui.puntoReferencias.client;


import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


import fing.satode.data.BomberosDTO;
import fing.satode.data.CamineraDTO;
import fing.satode.data.CiudadDTO;
import fing.satode.data.CuartelDTO;
import fing.satode.data.DepartamentoDTO;
import fing.satode.data.HospitalDTO;
import fing.satode.data.PROtrosDTO;
import fing.satode.data.PoliciaDTO;
import fing.satode.data.RefugioDTO;

import fing.satode.data.PuntoReferenciaDTO;
import fing.satode.data.TipoPuntoReferencia;
import fing.satode.ui.general.client.IBasicos;
import fing.satode.ui.general.client.IBasicosAsync;
import fing.satode.ui.general.data.KeyNumeric;

public class EntryPointPuntoReferencia implements EntryPoint {

	final Button nuevoB = new Button("Nuevo");
	final VerticalPanel vertical = new VerticalPanel();
	private ArrayList<PuntoReferenciaDTO>PuntoReferenciaGlobal;
	private ArrayList<DepartamentoDTO> departamentosGlobal;
	private Grid puntosReferencias;
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
		RootPanel.get("puntosReferencias").clear();
		vertical.clear();
		RootPanel.get("puntosReferencias").add(vertical);
		
		IPuntoReferenciaAsync servidorPuntoreferenica = GWT.create(IPuntoReferencia.class);
		
		servidorPuntoreferenica.listaPuntoReferencia(new AsyncCallback<ArrayList<PuntoReferenciaDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<PuntoReferenciaDTO> result) {
				PuntoReferenciaGlobal = result;
				puntosReferencias = new Grid(result.size()+1,10);
				puntosReferencias.setWidget(0, 0, new Label("ID"));
				puntosReferencias.setWidget(0, 1, new Label("Punto de entrada"));
				puntosReferencias.setWidget(0, 2, new Label("Punto de entrega"));
				puntosReferencias.setWidget(0, 3, new Label("Departamento"));
				puntosReferencias.setWidget(0, 4, new Label("Ciudad"));
				puntosReferencias.setWidget(0, 5, new Label("Direccion"));
				puntosReferencias.setWidget(0, 6, new Label("Telefono"));
				puntosReferencias.setWidget(0, 7, new Label("Tipo"));
				puntosReferencias.setWidget(0, 8, modificarLabel);
				puntosReferencias.setWidget(0, 9, eliminarLabel);
				puntosReferencias.getCellFormatter().setStyleName(0,0, "tbl-cab");
				puntosReferencias.getCellFormatter().setStyleName(0,1, "tbl-cab");
				puntosReferencias.getCellFormatter().setStyleName(0,2, "tbl-cab");
				puntosReferencias.getCellFormatter().setStyleName(0,3, "tbl-cab");
				puntosReferencias.getCellFormatter().setStyleName(0,4, "tbl-cab");
				puntosReferencias.getCellFormatter().setStyleName(0,5, "tbl-cab");
				puntosReferencias.getCellFormatter().setStyleName(0,6, "tbl-cab");
				puntosReferencias.getCellFormatter().setStyleName(0,7, "tbl-cab");
				puntosReferencias.getCellFormatter().setStyleName(0,8, "tbl-cab");
				puntosReferencias.setBorderWidth(1);
				int row=1;
				
				for(PuntoReferenciaDTO p: result){
					puntosReferencias.setWidget(row, 0, new Label(p.getId().toString()));
					if ( p.isPuntoEntrada()){ 
						puntosReferencias.setWidget(row, 1, new Label("SI"));
					}else
					{
						puntosReferencias.setWidget(row, 1, new Label("NO"));
					}
					if ( p.isPuntoEntrega()){ 
						puntosReferencias.setWidget(row, 2, new Label("SI"));
					}else
					{
						puntosReferencias.setWidget(row, 2, new Label("NO"));
					}	
					puntosReferencias.setWidget(row, 3, new Label(p.getDepartamento().getNombre()));
					puntosReferencias.setWidget(row, 4, new Label(p.getCiudad().getNombre()));
					puntosReferencias.setWidget(row, 5, new Label(p.getDireccion()));
					puntosReferencias.setWidget(row, 6, new Label(p.getTelefono()));
					puntosReferencias.setWidget(row, 7, new Label(TipoPuntoReferencia.getTXT(p.getTipo())));
					
					final Long id= p.getId();
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
					puntosReferencias.setWidget(row, 8, modificarI);
					puntosReferencias.setWidget(row, 9, eliminarI);
					row++;
				}
				vertical.add(puntosReferencias);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("ERROR");
			}
		});
		
		IBasicosAsync servidorBasicos = GWT.create(IBasicos.class);
		
		servidorBasicos.listaDepartamentos(new AsyncCallback<ArrayList<DepartamentoDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<DepartamentoDTO> result) {
				departamentosGlobal=result;
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
		final Grid grid= new Grid(7,2);
		final CheckBox puntoEntrega = new CheckBox();
	    final CheckBox puntoEntrada = new CheckBox();
	    final TextBox direccion=new TextBox();
	    final TextBox telefono=new TextBox();
	    final ListBox departamentos= new ListBox();
	    final ListBox ciudades= new ListBox();
	    final ListBox tipo= new ListBox();
	    final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
		
		
		//Hospitales
		final Grid gridHospital= new Grid(2,2);
		final TextBox capacidad = new TextBox();//Usado tambien para refugios
		final TextBox serviciosEspeciales = new TextBox();
		
		//Refugio
		final Grid gridRefugio= new Grid(4,2);
		final TextBox banios = new TextBox();
		final CheckBox techado = new CheckBox();
		final TextBox m2ParaCarpas=new TextBox();
		
		//Bomberos, Policia, Cuartel, Caminera
		final Grid grid2= new Grid(2,2);
		final TextBox cantFuncionarios = new TextBox();
		final TextBox cantVehiculos=new TextBox();
		
		//Otros
		final Grid gridOtros= new Grid(1,2);
		final TextBox descripcion=new TextBox();
		
	
	    @SuppressWarnings("deprecation")
		public FormDialogBox(Long idPuntoReferencia, String accion) {
			a=accion;
	    	id=idPuntoReferencia;
	    	
	    	if(a=="modificar") label.setText("Modificar Punto de Referencia");
			if(a=="eliminar") label.setText("Eliminar Punto de Referencia");
			if(a=="nuevo") label.setText("Nuevo Punto de Referencia");
			
			grid.setWidget(0, 0, new Label("Punto de Entrada"));
			grid.setWidget(1, 0, new Label("Punto de Entrega"));
			grid.setWidget(2, 0, new Label("Departamento"));
			grid.setWidget(3, 0, new Label("Ciudad"));
			grid.setWidget(4, 0, new Label("Direcci\u00F3n"));
			grid.setWidget(5, 0, new Label("Telefono"));
			grid.setWidget(6, 0, new Label("Tipo"));
			
			grid.setWidget(0, 1, puntoEntrada);
			grid.setWidget(1, 1, puntoEntrega);
			grid.setWidget(2, 1, departamentos);
			grid.setWidget(3, 1, ciudades);
			grid.setWidget(4, 1, direccion);
			grid.setWidget(5, 1, telefono);
			grid.setWidget(6, 1, tipo);
			grid.setBorderWidth(1);
	    	
	        departamentos.addItem("Seleccionar","0");
		    for(DepartamentoDTO d:departamentosGlobal){
		    	departamentos.addItem(d.getNombre(), d.getId().toString());
		    }
		    departamentos.addChangeHandler(new ChangeHandler() {
				
				@Override
				public void onChange(ChangeEvent event) {
					ciudades.clear();
					Long id=Long.valueOf(departamentos.getValue(departamentos.getSelectedIndex()));
					for(DepartamentoDTO dto:departamentosGlobal){
						if(dto.getId().equals(id)){
							ciudades.addItem("Seleccionar","0");
							for(CiudadDTO c:dto.getCiudades()){
								ciudades.addItem(c.getNombre(),c.getId().toString());
							}
						}
					}
				}
			});
		    
		    //No cambiar el orden de ingreso.
		    tipo.addItem("Seleccionar","0");
		    tipo.addItem(TipoPuntoReferencia.HOSPITAL_TXT,String.valueOf(TipoPuntoReferencia.HOSPITAL));
		    tipo.addItem(TipoPuntoReferencia.REFUGIO_TXT,String.valueOf(TipoPuntoReferencia.REFUGIO));
		    tipo.addItem(TipoPuntoReferencia.POLICIA_TXT,String.valueOf(TipoPuntoReferencia.POLICIA));
		    tipo.addItem(TipoPuntoReferencia.CAMINERA_TXT,String.valueOf(TipoPuntoReferencia.CAMINERA));
		    tipo.addItem(TipoPuntoReferencia.CUARTEL_TXT,String.valueOf(TipoPuntoReferencia.CUARTEL));
		    tipo.addItem(TipoPuntoReferencia.BOMBEROS_TXT,String.valueOf(TipoPuntoReferencia.BOMBEROS));
		    tipo.addItem(TipoPuntoReferencia.OTROS_TXT,String.valueOf(TipoPuntoReferencia.OTROS));
		    tipo.addChangeHandler(new ChangeHandler() {
				
				@Override
				public void onChange(ChangeEvent event) {
					gridHospital.setVisible(false);
					gridRefugio.setVisible(false);
					grid2.setVisible(false);
					int id=Integer.valueOf(tipo.getValue(tipo.getSelectedIndex()));
					
					switch(id)
					{

					case TipoPuntoReferencia.HOSPITAL: 
						gridHospital.setWidget(0, 0, new Label("Capacidad"));
						gridHospital.setWidget(1, 0, new Label("Servcios Expeciales"));
						
						gridHospital.setWidget(0, 1, capacidad);
						gridHospital.setWidget(1, 1, serviciosEspeciales);
						
						gridHospital.setBorderWidth(1);
						gridHospital.setVisible(true);
						
					break;
					case TipoPuntoReferencia.REFUGIO:
						gridRefugio.setWidget(0, 0, new Label("Capacidad"));
						gridRefugio.setWidget(1, 0, new Label("Ba\u00F1os"));
						gridRefugio.setWidget(2, 0, new Label("Techado"));
						gridRefugio.setWidget(3, 0, new Label("Metros cuadrados para carpas"));
						
						gridRefugio.setWidget(0, 1, capacidad);
						gridRefugio.setWidget(1, 1, banios);
						gridRefugio.setWidget(2, 1, techado);
						gridRefugio.setWidget(3, 1, m2ParaCarpas);
						gridRefugio.setBorderWidth(1);
						gridRefugio.setVisible(true);

					break;
					case TipoPuntoReferencia.BOMBEROS:
					case TipoPuntoReferencia.CAMINERA:
					case TipoPuntoReferencia.CUARTEL:
					case TipoPuntoReferencia.POLICIA:	
						grid2.setWidget(0, 0, new Label("Cantidad de funcionarios"));
						grid2.setWidget(1, 0, new Label("Cantidad de Vehiculos"));
						
						grid2.setWidget(0, 1, cantFuncionarios);
						grid2.setWidget(1, 1, cantVehiculos);

						grid2.setBorderWidth(1);
						grid2.setVisible(true);

					break;
					case TipoPuntoReferencia.OTROS:	
						gridOtros.setWidget(0, 0, new Label("Descripcion"));
						
						gridOtros.setWidget(0, 1, descripcion);
					

						gridOtros.setBorderWidth(1);
						gridOtros.setVisible(true);
					break;
					
					}; 
				}
			});
		    
		    capacidad.addKeyboardListener(new KeyNumeric());
		    banios.addKeyboardListener(new KeyNumeric());
		    m2ParaCarpas.addKeyboardListener(new KeyNumeric());
		    cantFuncionarios.addKeyboardListener(new KeyNumeric());
		    cantVehiculos.addKeyboardListener(new KeyNumeric());
				    
		    if (a == "modificar" || a == "eliminar" ){
		    	
		    	PuntoReferenciaDTO puntoReferenciaDTO= null;
	    		for(PuntoReferenciaDTO p:PuntoReferenciaGlobal){
	    			if(p.getId().equals(id)){
	    				puntoReferenciaDTO=p;
	    			}
	    		}
		    	
		    	
	    		
		    	
	    		puntoEntrada.setChecked(puntoReferenciaDTO.isPuntoEntrada());
	    		puntoEntrega.setChecked(puntoReferenciaDTO.isPuntoEntrega());
				direccion.setText(puntoReferenciaDTO.getDireccion());
			    telefono.setText(puntoReferenciaDTO.getTelefono());
			    
			    int row=0;
			    for(DepartamentoDTO d:departamentosGlobal){
			    	row++;
				    if(d.getId().equals(puntoReferenciaDTO.getDepartamento().getId())){
			    		departamentos.setSelectedIndex(row);
			    		ciudades.clear();
			    		ciudades.addItem("Seleccionar","0");
			    		int row2=1;
						for(CiudadDTO c:d.getCiudades()){
							ciudades.addItem(c.getNombre(),c.getId().toString());
							if(c.getId().equals(puntoReferenciaDTO.getCiudad().getId())){
								ciudades.setSelectedIndex(row2);
							}
							row2++;
						}
				    }
			    }
			    
			    
			    tipo.setSelectedIndex(puntoReferenciaDTO.getTipo());//OJO Esto anda respetando el orden de insersion el en ListBox tipo (lineas 249)
			    tipo.setEnabled(false);
			    
			    gridHospital.setVisible(false);
				gridRefugio.setVisible(false);
				grid2.setVisible(false);
				gridOtros.setVisible(false);
				
				switch(puntoReferenciaDTO.getTipo())
				{

				case TipoPuntoReferencia.HOSPITAL:
					
					capacidad.setText(String.valueOf(((HospitalDTO)puntoReferenciaDTO).getCapacidad()));
					serviciosEspeciales.setText(((HospitalDTO)puntoReferenciaDTO).getServiciosEspeciales());
					
					
					gridHospital.setWidget(0, 0, new Label("Capacidad"));
					gridHospital.setWidget(1, 0, new Label("Servcios Expeciales"));
					
					gridHospital.setWidget(0, 1, capacidad);
					gridHospital.setWidget(1, 1, serviciosEspeciales);
					
					gridHospital.setBorderWidth(1);
					gridHospital.setVisible(true);
					
					
					
				break;
				case TipoPuntoReferencia.BOMBEROS:
					cantFuncionarios.setText(String.valueOf(((BomberosDTO)puntoReferenciaDTO).getCantidadFuncionarios()));
					cantVehiculos.setText(String.valueOf(((BomberosDTO)puntoReferenciaDTO).getCantidadVehiculos()));
					
					grid2.setWidget(0, 0, new Label("Cantidad de funcionarios"));
					grid2.setWidget(1, 0, new Label("Cantidad de Vehiculos"));
					
					grid2.setWidget(0, 1, cantFuncionarios);
					grid2.setWidget(1, 1, cantVehiculos);

					grid2.setBorderWidth(1);
					grid2.setVisible(true);
				break;	
				case TipoPuntoReferencia.CAMINERA:
					cantFuncionarios.setText(String.valueOf(((CamineraDTO)puntoReferenciaDTO).getCantidadFuncionarios()));
					cantVehiculos.setText(String.valueOf(((CamineraDTO)puntoReferenciaDTO).getCantidadVehiculos()));
					
					grid2.setWidget(0, 0, new Label("Cantidad de funcionarios"));
					grid2.setWidget(1, 0, new Label("Cantidad de Vehiculos"));
					
					grid2.setWidget(0, 1, cantFuncionarios);
					grid2.setWidget(1, 1, cantVehiculos);

					grid2.setBorderWidth(1);
					grid2.setVisible(true);
				break;	
				case TipoPuntoReferencia.CUARTEL:
					cantFuncionarios.setText(String.valueOf(((CuartelDTO)puntoReferenciaDTO).getCantidadFuncionarios()));
					cantVehiculos.setText(String.valueOf(((CuartelDTO)puntoReferenciaDTO).getCantidadVehiculos()));
					
					grid2.setWidget(0, 0, new Label("Cantidad de funcionarios"));
					grid2.setWidget(1, 0, new Label("Cantidad de Vehiculos"));
					
					grid2.setWidget(0, 1, cantFuncionarios);
					grid2.setWidget(1, 1, cantVehiculos);

					grid2.setBorderWidth(1);
					grid2.setVisible(true);
				break;	
				case TipoPuntoReferencia.POLICIA:
					
					cantFuncionarios.setText(String.valueOf(((PoliciaDTO)puntoReferenciaDTO).getCantidadFuncionarios()));
					cantVehiculos.setText(String.valueOf(((PoliciaDTO)puntoReferenciaDTO).getCantidadVehiculos()));
					
					grid2.setWidget(0, 0, new Label("Cantidad de funcionarios"));
					grid2.setWidget(1, 0, new Label("Cantidad de Vehiculos"));
					
					grid2.setWidget(0, 1, cantFuncionarios);
					grid2.setWidget(1, 1, cantVehiculos);

					grid2.setBorderWidth(1);
					grid2.setVisible(true);
				break;	

				case TipoPuntoReferencia.OTROS:	
					
					descripcion.setText(String.valueOf(((PROtrosDTO)puntoReferenciaDTO).getDescripcion()));
					gridOtros.setWidget(0, 0, new Label("Descripcion"));
					
					gridOtros.setWidget(0, 1, descripcion);
				

					gridOtros.setBorderWidth(1);
					gridOtros.setVisible(true);
				break;
				case TipoPuntoReferencia.REFUGIO:
					
					capacidad.setText(String.valueOf(((RefugioDTO)puntoReferenciaDTO).getCapacidad()));
					banios.setText(String.valueOf(((RefugioDTO)puntoReferenciaDTO).getBanios()));
					techado.setChecked(((RefugioDTO)puntoReferenciaDTO).isTechado());
					m2ParaCarpas.setText(String.valueOf(((RefugioDTO)puntoReferenciaDTO).getM2ParaCarpas()));
					
					
					gridRefugio.setWidget(0, 0, new Label("Capacidad"));
					gridRefugio.setWidget(1, 0, new Label("Ba\u00F1os"));
					gridRefugio.setWidget(2, 0, new Label("Techado"));
					gridRefugio.setWidget(3, 0, new Label("Metros cuadrados para carpas"));
					
					gridRefugio.setWidget(0, 1, capacidad);
					gridRefugio.setWidget(1, 1, banios);
					gridRefugio.setWidget(2, 1, techado);
					gridRefugio.setWidget(3, 1, m2ParaCarpas);
					gridRefugio.setBorderWidth(1);
					gridRefugio.setVisible(true);

				break;
				
				}; 
			    
		    }
		    
		    if ( a == "eliminar" ){
		    	direccion.setEnabled(false);
			    telefono.setEnabled(false);
			    puntoEntrada.setEnabled(false);
			    puntoEntrega.setEnabled(false);
			    departamentos.setEnabled(false);
			    ciudades.setEnabled(false);
			    capacidad.setEnabled(false);
				serviciosEspeciales.setEnabled(false);
				banios.setEnabled(false);
				techado.setEnabled(false);
				m2ParaCarpas.setEnabled(false);
				cantFuncionarios.setEnabled(false);
				cantVehiculos.setEnabled(false);
				descripcion.setEnabled(false);
			    
		    }
			vertical.add(label);
			vertical.add(grid);
			vertical.add(gridHospital);
			vertical.add(gridRefugio);
			vertical.add(grid2);
			vertical.add(gridOtros);
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
			PuntoReferenciaDTO ptoReferencia=validar();
			if(ptoReferencia!=null){
				ptoReferencia.setId(id);
				
				
				IPuntoReferenciaAsync servidorPuntoReferencia= GWT.create(IPuntoReferencia.class);
				
				int idTipo=Integer.valueOf(tipo.getValue(tipo.getSelectedIndex()));
				
				
				
				if(a=="modificar"){
					
					
					
					switch(idTipo)
					{

						case TipoPuntoReferencia.HOSPITAL: 
							
							servidorPuntoReferencia.modificarPuntoReferencia((HospitalDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
							
						break;
						case TipoPuntoReferencia.REFUGIO:
							
							servidorPuntoReferencia.modificarPuntoReferencia((RefugioDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
						case TipoPuntoReferencia.BOMBEROS:
							
							servidorPuntoReferencia.modificarPuntoReferencia((BomberosDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
						case TipoPuntoReferencia.CAMINERA:
							
							servidorPuntoReferencia.modificarPuntoReferencia((CamineraDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
						case TipoPuntoReferencia.CUARTEL:
							
							servidorPuntoReferencia.modificarPuntoReferencia((CuartelDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
						case TipoPuntoReferencia.OTROS:
							
							servidorPuntoReferencia.modificarPuntoReferencia((PROtrosDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
						case TipoPuntoReferencia.POLICIA:
							
							servidorPuntoReferencia.modificarPuntoReferencia((PoliciaDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
					}
					
				}else if(a== "nuevo"){
					
					switch(idTipo)
					{

						case TipoPuntoReferencia.HOSPITAL: 
							servidorPuntoReferencia.nuevoPuntoReferencia((HospitalDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
							
						break;
						case TipoPuntoReferencia.REFUGIO:
							servidorPuntoReferencia.nuevoPuntoReferencia((RefugioDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
						case TipoPuntoReferencia.BOMBEROS:
							servidorPuntoReferencia.nuevoPuntoReferencia((BomberosDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
						case TipoPuntoReferencia.CAMINERA:
							servidorPuntoReferencia.nuevoPuntoReferencia((CamineraDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
						case TipoPuntoReferencia.CUARTEL:
							servidorPuntoReferencia.nuevoPuntoReferencia((CuartelDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
						case TipoPuntoReferencia.OTROS:
							servidorPuntoReferencia.nuevoPuntoReferencia((PROtrosDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
						case TipoPuntoReferencia.POLICIA:
							servidorPuntoReferencia.nuevoPuntoReferencia((PoliciaDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
					}
					
					
				}else if(a=="eliminar"){
					
					switch(idTipo)
					{

						case TipoPuntoReferencia.HOSPITAL: 
							servidorPuntoReferencia.eliminarPuntoReferencia((HospitalDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
							
						break;
						case TipoPuntoReferencia.REFUGIO:
							servidorPuntoReferencia.eliminarPuntoReferencia((RefugioDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
						case TipoPuntoReferencia.BOMBEROS:
							servidorPuntoReferencia.eliminarPuntoReferencia((BomberosDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
						case TipoPuntoReferencia.CAMINERA:
							servidorPuntoReferencia.eliminarPuntoReferencia((CamineraDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
						case TipoPuntoReferencia.CUARTEL:
							servidorPuntoReferencia.eliminarPuntoReferencia((CuartelDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
						case TipoPuntoReferencia.OTROS:
							servidorPuntoReferencia.eliminarPuntoReferencia((PROtrosDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
						case TipoPuntoReferencia.POLICIA:
							servidorPuntoReferencia.eliminarPuntoReferencia((PoliciaDTO) ptoReferencia,new AsyncCallback<Void>() {
								
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
						break;
					}
					
					
					
				}
			}
		}

		@SuppressWarnings("deprecation")
		private PuntoReferenciaDTO validar() {
			
			if(direccion.getText().trim().length()==0){
				Window.alert("Indique direccion");
				return null;
			}
			
			
			if(telefono.getText().trim().length()==0){
				Window.alert("Indique telefono");
				return null;
			}
			
			
			
			if(departamentos.getSelectedIndex()<1){
				Window.alert("Indique departamento");
				return null;
			}
			
			
			
			if(ciudades.getSelectedIndex()<1){
				Window.alert("Indique ciudad");
				return null;
			}
			
			int id=Integer.valueOf(tipo.getValue(tipo.getSelectedIndex()));
			
			switch(id)
			{
				case( TipoPuntoReferencia.HOSPITAL):
					
					HospitalDTO hospitalDTO= new HospitalDTO();
					if(capacidad.getText().trim().length()==0){
						Window.alert("Indique Capacidad");
						return null;
					}
					hospitalDTO.setCapacidad(Integer.valueOf(capacidad.getText()));
					if(serviciosEspeciales.getText().trim().length()==0){
						Window.alert("Indique los Servcios especiales");
						return null;
					}
					hospitalDTO.setServiciosEspeciales(serviciosEspeciales.getText());
					hospitalDTO.setTipo(id);
					
					
					hospitalDTO.setPuntoEntrada(puntoEntrada.isChecked());
					hospitalDTO.setPuntoEntrega(puntoEntrega.isChecked());
					hospitalDTO.setDireccion(direccion.getText());
					hospitalDTO.setTelefono(telefono.getText());
					Long idDep=Long.valueOf(departamentos.getValue(departamentos.getSelectedIndex()));
					
					for(DepartamentoDTO d:departamentosGlobal){
						if(idDep.equals(d.getId())){
							hospitalDTO.setDepartamento(d);
						}
					}
					
					Long idCiu=Long.valueOf(ciudades.getValue(ciudades.getSelectedIndex()));
					for(CiudadDTO c:hospitalDTO.getDepartamento().getCiudades()){
						if(idCiu.equals(c.getId())){
							hospitalDTO.setCiudad(c);
						}
					}
					return hospitalDTO;
				case( TipoPuntoReferencia.REFUGIO):
					
					RefugioDTO refugioDTO= new RefugioDTO();
				
					if(capacidad.getText().trim().length()==0){
						Window.alert("Indique Capacidad");
						return null;
					}
					refugioDTO.setCapacidad(Integer.valueOf(capacidad.getText()));
					
					if(banios.getText().trim().length()==0){
						Window.alert("Indique la cantidad de banios");
						return null;
					}
					refugioDTO.setBanios(Integer.valueOf(banios.getText()));
					
					if(m2ParaCarpas.getText().trim().length()==0){
						Window.alert("Indique la cantidad de metros cuadrados para carpas");
						return null;
					}
					refugioDTO.setM2ParaCarpas(Integer.valueOf(m2ParaCarpas.getText()));
					
					refugioDTO.setTipo(id);
					
					
					refugioDTO.setPuntoEntrada(puntoEntrada.isChecked());
					refugioDTO.setPuntoEntrega(puntoEntrega.isChecked());
					refugioDTO.setDireccion(direccion.getText());
					refugioDTO.setTelefono(telefono.getText());
					idDep=Long.valueOf(departamentos.getValue(departamentos.getSelectedIndex()));
					
					for(DepartamentoDTO d:departamentosGlobal){
						if(idDep.equals(d.getId())){
							refugioDTO.setDepartamento(d);
						}
					}
					
					idCiu=Long.valueOf(ciudades.getValue(ciudades.getSelectedIndex()));
					for(CiudadDTO c:refugioDTO.getDepartamento().getCiudades()){
						if(idCiu.equals(c.getId())){
							refugioDTO.setCiudad(c);
						}
					}
					return refugioDTO;
				case( TipoPuntoReferencia.BOMBEROS):
					
					BomberosDTO bomberosDTO= new BomberosDTO();
				
					if(cantFuncionarios.getText().trim().length()==0){
						Window.alert("Indique la cantidad de funcionarios");
						return null;
					}
					bomberosDTO.setCantidadFuncionarios(Integer.valueOf(cantFuncionarios.getText()));
					
					if(cantVehiculos.getText().trim().length()==0){
						Window.alert("Indique la cantidad de vehiculos");
						return null;
					}
					bomberosDTO.setCantidadVehiculos(Integer.valueOf(cantVehiculos.getText()));
					
					bomberosDTO.setTipo(id);
					
					
					bomberosDTO.setPuntoEntrada(puntoEntrada.isChecked());
					bomberosDTO.setPuntoEntrega(puntoEntrega.isChecked());
					bomberosDTO.setDireccion(direccion.getText());
					bomberosDTO.setTelefono(telefono.getText());
					idDep=Long.valueOf(departamentos.getValue(departamentos.getSelectedIndex()));
					
					for(DepartamentoDTO d:departamentosGlobal){
						if(idDep.equals(d.getId())){
							bomberosDTO.setDepartamento(d);
						}
					}
					
					idCiu=Long.valueOf(ciudades.getValue(ciudades.getSelectedIndex()));
					for(CiudadDTO c:bomberosDTO.getDepartamento().getCiudades()){
						if(idCiu.equals(c.getId())){
							bomberosDTO.setCiudad(c);
						}
					}
					return bomberosDTO;
				case( TipoPuntoReferencia.CAMINERA):
					
					CamineraDTO camineraDTO= new CamineraDTO();
				
					if(cantFuncionarios.getText().trim().length()==0){
						Window.alert("Indique la cantidad de funcionarios");
						return null;
					}
					camineraDTO.setCantidadFuncionarios(Integer.valueOf(cantFuncionarios.getText()));
					
					if(cantVehiculos.getText().trim().length()==0){
						Window.alert("Indique la cantidad de vehiculos");
						return null;
					}
					camineraDTO.setCantidadVehiculos(Integer.valueOf(cantVehiculos.getText()));
					
					camineraDTO.setTipo(id);
					
					
					camineraDTO.setPuntoEntrada(puntoEntrada.isChecked());
					camineraDTO.setPuntoEntrega(puntoEntrega.isChecked());
					camineraDTO.setDireccion(direccion.getText());
					camineraDTO.setTelefono(telefono.getText());
					idDep=Long.valueOf(departamentos.getValue(departamentos.getSelectedIndex()));
					
					for(DepartamentoDTO d:departamentosGlobal){
						if(idDep.equals(d.getId())){
							camineraDTO.setDepartamento(d);
						}
					}
					
					idCiu=Long.valueOf(ciudades.getValue(ciudades.getSelectedIndex()));
					for(CiudadDTO c:camineraDTO.getDepartamento().getCiudades()){
						if(idCiu.equals(c.getId())){
							camineraDTO.setCiudad(c);
						}
					}
					return camineraDTO;
				case( TipoPuntoReferencia.CUARTEL):
					
					CuartelDTO cuartelDTO = new CuartelDTO();
				
					if(cantFuncionarios.getText().trim().length()==0){
						Window.alert("Indique la cantidad de funcionarios");
						return null;
					}
					cuartelDTO.setCantidadFuncionarios(Integer.valueOf(cantFuncionarios.getText()));
					
					if(cantVehiculos.getText().trim().length()==0){
						Window.alert("Indique la cantidad de vehiculos");
						return null;
					}
					cuartelDTO.setCantidadVehiculos(Integer.valueOf(cantVehiculos.getText()));
					
					cuartelDTO.setTipo(id);
					
					
					cuartelDTO.setPuntoEntrada(puntoEntrada.isChecked());
					cuartelDTO.setPuntoEntrega(puntoEntrega.isChecked());
					cuartelDTO.setDireccion(direccion.getText());
					cuartelDTO.setTelefono(telefono.getText());
					idDep=Long.valueOf(departamentos.getValue(departamentos.getSelectedIndex()));
					
					for(DepartamentoDTO d:departamentosGlobal){
						if(idDep.equals(d.getId())){
							cuartelDTO.setDepartamento(d);
						}
					}
					
					idCiu=Long.valueOf(ciudades.getValue(ciudades.getSelectedIndex()));
					for(CiudadDTO c:cuartelDTO.getDepartamento().getCiudades()){
						if(idCiu.equals(c.getId())){
							cuartelDTO.setCiudad(c);
						}
					}
					return cuartelDTO;
				case( TipoPuntoReferencia.POLICIA):
					
					PoliciaDTO policiaDTO = new PoliciaDTO();
				
					if(cantFuncionarios.getText().trim().length()==0){
						Window.alert("Indique la cantidad de funcionarios");
						return null;
					}
					policiaDTO.setCantidadFuncionarios(Integer.valueOf(cantFuncionarios.getText()));
					
					if(cantVehiculos.getText().trim().length()==0){
						Window.alert("Indique la cantidad de vehiculos");
						return null;
					}
					policiaDTO.setCantidadVehiculos(Integer.valueOf(cantVehiculos.getText()));
					
					policiaDTO.setTipo(id);
					
					
					policiaDTO.setPuntoEntrada(puntoEntrada.isChecked());
					policiaDTO.setPuntoEntrega(puntoEntrega.isChecked());
					policiaDTO.setDireccion(direccion.getText());
					policiaDTO.setTelefono(telefono.getText());
					idDep=Long.valueOf(departamentos.getValue(departamentos.getSelectedIndex()));
					
					for(DepartamentoDTO d:departamentosGlobal){
						if(idDep.equals(d.getId())){
							policiaDTO.setDepartamento(d);
						}
					}
					
					idCiu=Long.valueOf(ciudades.getValue(ciudades.getSelectedIndex()));
					for(CiudadDTO c:policiaDTO.getDepartamento().getCiudades()){
						if(idCiu.equals(c.getId())){
							policiaDTO.setCiudad(c);
						}
					}
					return policiaDTO;
				case( TipoPuntoReferencia.OTROS):
					
					PROtrosDTO otrosDTO = new PROtrosDTO();
				
					if(descripcion.getText().trim().length()==0){
						Window.alert("Indique la descripcion");
						return null;
					}
					otrosDTO.setDescripcion(descripcion.getText());
					
					otrosDTO.setTipo(id);
					
					
					otrosDTO.setPuntoEntrada(puntoEntrada.isChecked());
					otrosDTO.setPuntoEntrega(puntoEntrega.isChecked());
					otrosDTO.setDireccion(direccion.getText());
					otrosDTO.setTelefono(telefono.getText());
					idDep=Long.valueOf(departamentos.getValue(departamentos.getSelectedIndex()));
					
					for(DepartamentoDTO d:departamentosGlobal){
						if(idDep.equals(d.getId())){
							otrosDTO.setDepartamento(d);
						}
					}
					
					idCiu=Long.valueOf(ciudades.getValue(ciudades.getSelectedIndex()));
					for(CiudadDTO c:otrosDTO.getDepartamento().getCiudades()){
						if(idCiu.equals(c.getId())){
							otrosDTO.setCiudad(c);
						}
					}
					return otrosDTO;	
					
				default: 
					Window.alert("Indique Tipo");
					return null;
				
			}
			
			
			
			
			
			
			
			
					
			
			
			
			
			
			
			
		}
		
		private Float getFloat(String text) {
			if(text==null || text.trim().length()==0){
				return 0F;
			}
			return Float.valueOf(text);
		}
	}

}
