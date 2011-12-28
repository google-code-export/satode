package fing.satode.ui.registros.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

import fing.satode.data.CiudadDTO;
import fing.satode.data.DepartamentoDTO;
import fing.satode.data.EventoDTO;
import fing.satode.data.TipoEventoDTO;
import fing.satode.ui.general.client.IBasicos;
import fing.satode.ui.general.client.IBasicosAsync;
import fing.satode.ui.general.data.KeyNumeric;

public class EntryPointEvento implements EntryPoint {

	final Button nuevoB = new Button("Nuevo");
	final VerticalPanel vertical = new VerticalPanel();
	private ArrayList<EventoDTO> eventosGlobal;
	private ArrayList<TipoEventoDTO> tiposEventosGlobal;
	private ArrayList<DepartamentoDTO> departamentosGlobal;
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
		
		servidorEvento.listaEventos(new AsyncCallback<ArrayList<EventoDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<EventoDTO> result) {
				// TODO Auto-generated method stub
				eventosGlobal=result;
				eventos = new Grid(result.size()+1,9);
				eventos.setWidget(0, 0, new Label("ID"));
				eventos.setWidget(0, 1, new Label("Fecha Inicio"));
				eventos.setWidget(0, 2, new Label("Tipo Evento"));
				eventos.setWidget(0, 3, new Label("Ciudad"));
				eventos.setWidget(0, 4, new Label("Fuente"));
				eventos.setWidget(0, 5, new Label("Muertos"));
				eventos.setWidget(0, 6, new Label("Perdidas Dolares"));
				eventos.setWidget(0, 7, modificarLabel);
				eventos.setWidget(0, 8, eliminarLabel);
				
				for(int i=0;i<9;i++){
					eventos.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				
				eventos.setBorderWidth(1);
				int row=1;
				DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
				for(EventoDTO e: result){
					eventos.setWidget(row, 0, new Label(e.getId().toString()));
					eventos.setWidget(row, 1, new Label(format.format(e.getFechaInicio())));
					eventos.setWidget(row, 2, new Label(e.getTipoEvento().getNombre()));
					eventos.setWidget(row, 3, new Label(e.getCiudad().getNombre()));
					eventos.setWidget(row, 4, new Label(e.getFuente()));
					eventos.setWidget(row, 5, new Label(String.valueOf(e.getMuertos())));
					eventos.setWidget(row, 6, new Label(String.valueOf(e.getPerdidasDolares())));
					
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
					eventos.setWidget(row, 7, modificarI);
					eventos.setWidget(row, 8, eliminarI);
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
		
		servidorEvento.listaTipoEventos(new AsyncCallback<ArrayList<TipoEventoDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<TipoEventoDTO> result) {
				// TODO Auto-generated method stub
				tiposEventosGlobal=result;
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
		
		IBasicosAsync servidorBasicos = GWT.create(IBasicos.class);
		
		servidorBasicos.listaDepartamentos(new AsyncCallback<ArrayList<DepartamentoDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<DepartamentoDTO> result) {
				// TODO Auto-generated method stub
				departamentosGlobal=result;
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
		final Grid gridIzq= new Grid(18,3);
		final Grid gridDer= new Grid(18,3);
		final Grid grid= new Grid(1,2);
		final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
		final TextBox latidud= new TextBox();
	    final TextBox longitud= new TextBox();
	    final ListBox tipoEventos= new ListBox();
	    final Label fecha = new Label();
	    final DatePicker datePicker = new DatePicker();
	    final ListBox departamentos= new ListBox();
	    final ListBox ciudades= new ListBox();
	    final TextBox fuente= new TextBox();
	    final TextBox muertos= new TextBox();
	    final TextBox perdidasPesos= new TextBox();
	    final TextBox perdidasDolares= new TextBox();
	    final TextBox viasAfectadas= new TextBox();
	    final TextBox otrasPerdidas= new TextBox();
	    final TextBox desaparecidos= new TextBox();
	    final TextBox cultivosBosques= new TextBox();
	    final TextBox heridosEnfermos= new TextBox();
	    final TextBox ganado= new TextBox();
	    final TextBox centrosEducacion= new TextBox();
	    final TextBox reubicados= new TextBox();
	    final TextBox centrosHospitalarios= new TextBox();
	    final TextBox vivAfectadas= new TextBox();
	    final TextBox evacuados= new TextBox();
	    final TextBox daminificados= new TextBox();
	    final TextBox viviendasDestruidas= new TextBox();
	    
		public FormDialogBox(Long idEvento , String accion){
			super();
			a=accion;
			id=idEvento;
			
			horizontal.add(aceptar);
			horizontal.add(cancelar);
			grid.setWidget(0, 0, gridIzq);
			grid.setWidget(0, 1, gridDer);
			grid.setBorderWidth(1);
			gridIzq.setBorderWidth(1);
			gridDer.setBorderWidth(1);
			vertical.add(label);
			vertical.add(grid);
			vertical.add(horizontal);
			

		    // Set the value in the text box when the user selects a date
		    datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
		      public void onValueChange(ValueChangeEvent event) {
		        Date date = (Date)event.getValue();
		        DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
		        String dateString = format.format(date);
		        fecha.setText(dateString);
		      }
		    });

		    // Set the default value
		    datePicker.setValue(new Date(), true);
		    gridIzq.setWidget(0, 0, new Label("Fecha"));
		    gridIzq.setWidget(0, 1, fecha);
		    gridIzq.setWidget(0,2 , datePicker);
		    
		    
		    tipoEventos.addItem("Seleccione ","0");
		    for(TipoEventoDTO te:tiposEventosGlobal){
		    	tipoEventos.addItem(te.getNombre(),te.getId().toString());
		    }
		    gridIzq.setWidget(1, 0, new Label("Tipo Evento"));
		    gridIzq.setWidget(1, 1, tipoEventos);
		    
		    tipoEventos.addChangeHandler(new ChangeHandler() {
				
				@Override
				public void onChange(ChangeEvent event) {
					// TODO Auto-generated method stub
					Long idSel=Long.valueOf(tipoEventos.getValue(tipoEventos.getSelectedIndex()));
					gridIzq.setWidget(1, 2,new Label(""));
					for(TipoEventoDTO te:tiposEventosGlobal){
						if(idSel.equals(te.getId())){
							Label txtLbl= new Label(te.getDescripcion(), true);
							gridIzq.setWidget(1, 2,txtLbl);
							break;
						}
					}
					
				}
			});
		   
		    gridIzq.setWidget(2, 0, new Label("Departamento"));
		    gridIzq.setWidget(2, 1, departamentos);
		    departamentos.addItem("Seleccionar","0");
		    for(DepartamentoDTO d:departamentosGlobal){
		    	departamentos.addItem(d.getNombre(), d.getId().toString());
		    }
		    departamentos.addChangeHandler(new ChangeHandler() {
				
				@Override
				public void onChange(ChangeEvent event) {
					// TODO Auto-generated method stub
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
		    
		    gridIzq.setWidget(3, 0, new Label("Ciudad"));
		    gridIzq.setWidget(3, 1,ciudades);
		    
		    latidud.addKeyboardListener(new KeyNumeric());
		    longitud.addKeyboardListener(new KeyNumeric());
		    
		    gridIzq.setWidget(4, 0, new Label("Latitud y Longitud"));
		    gridIzq.setWidget(4, 1,latidud);
		    gridIzq.setWidget(4, 2, longitud);
		    
		    gridIzq.setWidget(5, 0, new Label("Fuente"));
		    gridIzq.setWidget(5, 1,fuente);
		    gridIzq.setWidget(5, 2,new Label("Fuente de informaci\u00F3n de los datos del evento.",true));
		    
		    gridIzq.setWidget(6, 0, new Label("Muertos"));
		    gridIzq.setWidget(6, 1,muertos);
		    gridIzq.setWidget(6, 2,new Label("N\u00FAmero de personas fallecidas por causas directas del evento. ",true));
		    muertos.addKeyboardListener(new KeyNumeric());
		    
		    gridIzq.setWidget(7, 0, new Label("Perdidas Pesos"));
		    gridIzq.setWidget(7, 1,perdidasPesos);
		    gridIzq.setWidget(7, 2,new Label("Monto en pesos de las p\u00E9rdidas directas causadas por el desastre.",true));
		    perdidasPesos.addKeyboardListener(new KeyNumeric());
		    
		    gridIzq.setWidget(8, 0, new Label("Perdidas Dolares"));
		    gridIzq.setWidget(8, 1,perdidasDolares);
		    gridIzq.setWidget(8, 2, new Label("Equivalente en d\u00F3lares (US$) de las p\u00E9rdidas en moneda local, seg\u00FAn la tasa de cambio de moneda nacional en la fecha de ocurrencia del evento.",true));
		    perdidasDolares.addKeyboardListener(new KeyNumeric());
		    
		    gridIzq.setWidget(9, 0, new Label("Vias Afectadas"));
		    gridIzq.setWidget(9, 1,viasAfectadas);
		    gridIzq.setWidget(9, 2,new Label("Longitud en metros de redes viales destruidas y/o inhabilitadas.",true));
			    viasAfectadas.addKeyboardListener(new KeyNumeric());
		    
		    gridIzq.setWidget(10, 0, new Label("Otras Perdidas"));
		    gridIzq.setWidget(10, 1,otrasPerdidas);
		    gridIzq.setWidget(10, 2,new Label("Descripci\u00F3n de otras p\u00E9rdidas, por ejemplo: edificaciones religiosas y monumentos; patrimonio arquitect\u00F3nico y cultural; escenarios e instalaciones masivas; edificios de administraci\u00F3n p\u00FAblica de la banca; del comercio y del turismo; veh\u00EDculos o buses perdidos; puentes, entre otros.",true));
		    
		    gridIzq.setWidget(11, 0, new Label("Desaparecidos"));
		    gridIzq.setWidget(11, 1,desaparecidos);
		    gridIzq.setWidget(11, 2,new Label("N\u00FAmero de personas cuyo paradero a partir del desastre es desconocido. Incluye personas presumiblemente muertas sin evidencias f\u00EDsicas. Los datos de muertos y de desaparecidos son mutuamente excluyentes.",true));
			desaparecidos.addKeyboardListener(new KeyNumeric());
		    
			gridIzq.setWidget(12, 0, new Label("Cultivos y Bosques"));
		    gridIzq.setWidget(12, 1,cultivosBosques);
		    gridIzq.setWidget(12, 2,new Label("Cantidad de hect\u00E1reas de cultivo, pastizales o bosques destruidas y afectadas.",true));
		    cultivosBosques.addKeyboardListener(new KeyNumeric());
		    
			gridIzq.setWidget(13, 0, new Label("Her\u00EDdos y Enfermos"));
		    gridIzq.setWidget(13, 1,heridosEnfermos);
		    gridIzq.setWidget(13, 2,new Label("N\u00FAmero de personas que resultaron afectadas en su salud o integridad f\u00EDsica, sin ser v\u00EDctimas mortales, por causa directa del evento. Se deben incluir las personas que sufrieron lesiones y las que quedaron enfermas, si se trata de una plaga o epidemia.",true));
		    heridosEnfermos.addKeyboardListener(new KeyNumeric());
		
			gridIzq.setWidget(14, 0, new Label("Ganado"));
		    gridIzq.setWidget(14, 1,ganado);
		    gridIzq.setWidget(14, 2,new Label("N\u00FAmero de unidades perdidas (bovinos, porcinos, av\u00EDcolas).",true));
		    ganado.addKeyboardListener(new KeyNumeric());
		
			gridIzq.setWidget(15, 0, new Label("Centros de educaci\u00F3n"));
		    gridIzq.setWidget(15, 1,centrosEducacion);
		    gridIzq.setWidget(15, 2,new Label("N\u00FAmero de guarder\u00EDas, escuelas, colegios, universidades, centros de capacitaci\u00F3n, etc. destruidas y afectadas directa o indirectamente por el evento. Incluye aquellos que han sido utilizados como albergues temporales",true));
		    centrosEducacion.addKeyboardListener(new KeyNumeric());
		
			gridIzq.setWidget(16, 0, new Label("Reubicados"));
		    gridIzq.setWidget(16, 1,reubicados);
		    gridIzq.setWidget(16, 2,new Label("N\u00FAmero de personas que han sido trasladadas desde sus sitios de vivienda a nuevos emplazamientos permanentes. ",true));
		    reubicados.addKeyboardListener(new KeyNumeric());
		
		    gridIzq.setWidget(17, 0, new Label("Viviendas Afectadas"));
		    gridIzq.setWidget(17, 1,vivAfectadas);
		    gridIzq.setWidget(17, 2,new Label("N\u00FAmero de viviendas con da\u00F1os menores, no estructurales o arquitect\u00F3nicos, que pueden seguir siendo habitadas, a\u00FAn cuando requieran de acciones de reparaci\u00F3n o de limpieza.",true));
		    vivAfectadas.addKeyboardListener(new KeyNumeric());
		
		    gridDer.setWidget(0, 0, new Label("Evacuados"));
		    gridDer.setWidget(0, 1,evacuados);
		    gridDer.setWidget(0, 2,new Label("N\u00FAmero de personas evacuadas temporalmente de sus viviendas, lugares de trabajo, colegios, hospitales, etc. ",true));
		    evacuados.addKeyboardListener(new KeyNumeric());
		
		    gridDer.setWidget(1, 0, new Label("Damnificados"));
		    gridDer.setWidget(1, 1,daminificados);
		    gridDer.setWidget(1, 2,new Label("N\u00FAmero de personas que han sufrido grave da\u00F1o directamente asociado al evento en sus bienes y/o servicios individuales o colectivos. Por ejemplo destrucción parcial o total de su vivienda y sus bienes, p\u00E9rdidas en cultivos y/o en bodegas, etc. Se deben incluir tambi\u00E9n personas reubicadas. ",true));
		    daminificados.addKeyboardListener(new KeyNumeric());
		
		    gridDer.setWidget(2, 0, new Label("Viviendas destruidas"));
		    gridDer.setWidget(2, 1,viviendasDestruidas);
		    gridDer.setWidget(2, 2,new Label("N\u00FAmero de viviendas arrasadas, sepultadas, colapsadas o deterioradas de tal manera que no son habitables.",true));
		    viviendasDestruidas.addKeyboardListener(new KeyNumeric());
		
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
			
		}

		protected void procesar() {
			// TODO Auto-generated method stub
			this.hide();
		}
	
	}

}
