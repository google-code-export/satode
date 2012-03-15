package fing.satode.ui.registros.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LinkElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
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
import fing.satode.data.DepartamentoDTO;
import fing.satode.data.EventoDTO;
import fing.satode.constantes.MedidaTiempo;
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
				Window.alert("ERROR AJAX");
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
		final Anchor ayuda =new Anchor("AYUDA sobre los campos del registro de eventos");
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
	    final TextBox afectados= new TextBox();
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
	    final ListBox transporte = new ListBox();
	    final ListBox comunicaciones = new ListBox();
	    final ListBox instalacionesSocorro = new ListBox();
	    final ListBox agropecuario = new ListBox();
	    final ListBox acueducto = new ListBox();
	    final ListBox alcantarillado = new ListBox();
	    final ListBox energia = new ListBox();
	    final ListBox industria = new ListBox();
	    final ListBox salud = new ListBox();
	    final TextBox otros= new TextBox();
	    final TextBox duracion= new TextBox();
	    final ListBox duracionMedida = new ListBox();
	    final TextBox observaciones= new TextBox();
	    final CaptionPanel captionPanel=new CaptionPanel();
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
			//vertical.add(label);
			vertical.add(ayuda);
			vertical.add(grid);
			vertical.add(horizontal);
			captionPanel.add(vertical);
			
			if(a=="modificar") captionPanel.setCaptionText("Modificar Evento");
			if(a=="eliminar") captionPanel.setCaptionText("Eliminar Evento");
			if(a=="nuevo") captionPanel.setCaptionText("Nuevo Evento");

			
			ayuda.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					Window.open("/docs/Eventos.html", "Documentación de los campos del registro de eventos", null);
				}
			});
			
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
		    gridDer.setWidget(1, 2,new Label("N\u00FAmero de personas que han sufrido grave da\u00F1o directamente asociado al evento en sus bienes y/o servicios individuales o colectivos. Por ejemplo destrucci\u00F3n parcial o total de su vivienda y sus bienes, p\u00E9rdidas en cultivos y/o en bodegas, etc. Se deben incluir tambi\u00E9n personas reubicadas. ",true));
		    daminificados.addKeyboardListener(new KeyNumeric());
		
		    gridDer.setWidget(2, 0, new Label("Viviendas destruidas"));
		    gridDer.setWidget(2, 1,viviendasDestruidas);
		    gridDer.setWidget(2, 2,new Label("N\u00FAmero de viviendas arrasadas, sepultadas, colapsadas o deterioradas de tal manera que no son habitables.",true));
		    viviendasDestruidas.addKeyboardListener(new KeyNumeric());
		
		    gridDer.setWidget(3, 0, new Label("Transporte"));
		    gridDer.setWidget(3, 1,transporte);
		    gridDer.setWidget(3, 2,new Label("Corresponde con efectos del evento sobre el sector del transporte: redes viales (vehiculares, f\u00E9rreas), terminales de transporte, aeropuertos, puentes fluviales y mar\u00EDtimos, muelles, etc. afectados y/o destruidos. ",true));
		    transporte.addItem("SI", "SI");
		    transporte.addItem("NO", "NO");
		    
		    gridDer.setWidget(4, 0, new Label("Comunicaciones"));
		    gridDer.setWidget(4, 1,comunicaciones);
		    gridDer.setWidget(4, 2,new Label("Corresponde a da\u00F1os en el sector de comunicaciones: sobre plantas y redes telef\u00F3nicas, estaciones de radio y televisi\u00F3n, oficinas de correo y de informaci\u00F3n p\u00FAblica, servicios de internet, radiotelefon\u00EDa, comunicaciones celulares. ",true));
		    comunicaciones.addItem("SI", "SI");
		    comunicaciones.addItem("NO", "NO");
		    
		    gridDer.setWidget(5, 0, new Label("Instalaciones socorro"));
		    gridDer.setWidget(5, 1,instalacionesSocorro);
		    gridDer.setWidget(5, 2,new Label("Corresponde a da\u00F1os en el sector de respuesta a emergencia, espec\u00EDficamente instalaciones de los organismos de socorro: Cuerpos de bomberos, instalaciones de organismos de socorro y de entidades de orden p\u00FAblico. ",true));
		    instalacionesSocorro.addItem("SI", "SI");
		    instalacionesSocorro.addItem("NO", "NO");
		  
		    gridDer.setWidget(6, 0, new Label("Agropecuario"));
		    gridDer.setWidget(6, 1,agropecuario);
		    gridDer.setWidget(6, 2,new Label("Corresponde a da\u00F1os en el sector de Agropecuario: Campos de cultivos, granjas, zonas de pastoreo.",true));
		    agropecuario.addItem("SI", "SI");
		    agropecuario.addItem("NO", "NO");
		  
		    gridDer.setWidget(7, 0, new Label("Acueducto"));
		    gridDer.setWidget(7, 1,acueducto);
		    gridDer.setWidget(7, 2,new Label("Corresponde a da\u00F1os en el sector de Acueducto: Tomas de agua, plantas de tratamiento, acueductos y canales de conducci\u00F3n de agua potable, tanques de almacenamiento.",true));
		    acueducto.addItem("SI", "SI");
		    acueducto.addItem("NO", "NO");
		  
		    gridDer.setWidget(8, 0, new Label("Alcantarillado"));
		    gridDer.setWidget(8, 1,alcantarillado);
		    gridDer.setWidget(8, 2,new Label("Corresponde a da\u00F1os en el sector de Alcantarillado: Redes de disposici\u00F3n de aguas servidas y/o pluviales y sus plantas de tratamiento.",true));
		    alcantarillado.addItem("SI", "SI");
		    alcantarillado.addItem("NO", "NO");
		    
		    gridDer.setWidget(9, 0, new Label("Energ\u00EDa"));
		    gridDer.setWidget(9, 1,energia);
		    energia.addItem("SI", "SI");
		    energia.addItem("NO", "NO");

		    gridDer.setWidget(10, 0, new Label("Industria"));
		    gridDer.setWidget(10, 1,industria);
		    gridDer.setWidget(10, 2,new Label("Corresponde a da\u00F1os en el sector de industrial: Industrias de todos los tipos y tama\u00F1os. ",true));
		    industria.addItem("SI", "SI");
		    industria.addItem("NO", "NO");

		    gridDer.setWidget(11, 0, new Label("Salud"));
		    gridDer.setWidget(11, 1,salud);
		    gridDer.setWidget(11, 2,new Label("Corresponde a da\u00F1os en el sector de la salud: Todo lo relacionado con el sector de salud incluyendo las redes de comunicaci\u00F3n, red de atenci\u00F3n de emergencias (ambulancias), centros de atenci\u00F3n, etc. ",true));
		    salud.addItem("SI", "SI");
		    salud.addItem("NO", "NO");

		    gridDer.setWidget(12, 0, new Label("Otros"));
		    gridDer.setWidget(12, 1,otros);
		    gridDer.setWidget(12, 2,new Label("Otros davos no incluidos en la informaci\u00F3n anterior. ",true));

		    gridDer.setWidget(13, 0, new Label("Duraci\u00F3n"));
		    gridDer.setWidget(13, 1,duracion);
		    gridDer.setWidget(13, 2,duracionMedida);
		    
		    duracion.addKeyboardListener(new KeyNumeric());
		    duracionMedida.addItem(MedidaTiempo.getTXT(MedidaTiempo.SEGUNDOS),String.valueOf(MedidaTiempo.SEGUNDOS));	
		    duracionMedida.addItem(MedidaTiempo.getTXT(MedidaTiempo.MINUTOS),String.valueOf(MedidaTiempo.MINUTOS));
		    duracionMedida.addItem(MedidaTiempo.getTXT(MedidaTiempo.HORAS),String.valueOf(MedidaTiempo.HORAS));
		    duracionMedida.addItem(MedidaTiempo.getTXT(MedidaTiempo.DIAS),String.valueOf(MedidaTiempo.DIAS));
		    duracionMedida.addItem(MedidaTiempo.getTXT(MedidaTiempo.SEMANAS),String.valueOf(MedidaTiempo.SEMANAS));
		    
		    gridDer.setWidget(14, 0, new Label("Observaciones"));
		    gridDer.setWidget(14, 1,observaciones);
		    gridDer.setWidget(14, 2,new Label("Corresponde a cualquier observaci\u00F3n que se desea agregar, ya sea de las causas del evento, comentarios pertinentes, etc."));
		
		    gridDer.setWidget(15, 0, new Label("Afectados"));
		    gridDer.setWidget(15, 1,afectados);
		    gridDer.setWidget(15, 2,new Label("Numero de personas que sufren efectos indirectos o secundarios asociados a un evento. Corresponde al numero de personas; diferentes a damnificados, que sufren el impacto de los efectos secundarios de los desastre, por razones como deficiencias en la prestacion de servicios publicos, en el comercio, o en el trabajo, o por aislamiento."));
		    afectados.addKeyboardListener(new KeyNumeric());
		    
		    if(a=="modificar" || a=="eliminar"){
		    	
	    		EventoDTO eventoDTO= null;
	    		for(EventoDTO e:eventosGlobal){
	    			if(e.getId().equals(idEvento)){
	    				eventoDTO=e;
	    			}
	    		}
	    		datePicker.setValue(eventoDTO.getFechaInicio());
	    		int row=0;
	    		for(TipoEventoDTO te:tiposEventosGlobal){
	    		 	row++;
	    		 	if(te.getId().equals(eventoDTO.getTipoEvento().getId())){
			    		tipoEventos.setSelectedIndex(row);
			    	}
			    }
	    		row=0;
	    		DepartamentoDTO dtoDepto=null;
			    for(DepartamentoDTO d:departamentosGlobal){
			    	row++;
				    if(d.getId().equals(eventoDTO.getDepartamento().getId())){
			    		departamentos.setSelectedIndex(row);
			    		dtoDepto=d;
			    	}
			    }
			    
			    row=0;
			    ciudades.clear();
				ciudades.addItem("Seleccionar","0");
				for(CiudadDTO c:dtoDepto.getCiudades()){
					row++;
					ciudades.addItem(c.getNombre(),c.getId().toString());
					if(c.getId().equals(eventoDTO.getCiudad().getId())){
						ciudades.setSelectedIndex(row);
					}
				}
			    
			    latidud.setText(String.valueOf(eventoDTO.getLatitud()));
			    longitud.setText(String.valueOf(eventoDTO.getLongitud()));
			    fuente.setText(eventoDTO.getFuente());
			    muertos.setText(String.valueOf(eventoDTO.getMuertos()));
			    perdidasPesos.setText(String.valueOf(eventoDTO.getPerdidasPesos()));
			    perdidasDolares.setText(String.valueOf(eventoDTO.getPerdidasDolares()));
			    viasAfectadas.setText(String.valueOf(eventoDTO.getViasAfectadas()));
			    desaparecidos.setText(String.valueOf(eventoDTO.getDesaparecidos()));
			    cultivosBosques.setText(String.valueOf(eventoDTO.getCultivosBosques()));
			    heridosEnfermos.setText(String.valueOf(eventoDTO.getHeridosEnfermos()));
			    ganado.setText(String.valueOf(eventoDTO.getGanado()));
			    centrosEducacion.setText(String.valueOf(eventoDTO.getCentrosEducacion()));
			    reubicados.setText(String.valueOf(eventoDTO.getReubicados()));
			    otrasPerdidas.setText(eventoDTO.getOtrasPerdidas());
			    vivAfectadas.setText(String.valueOf(eventoDTO.getVivAfectadas()));
			    evacuados.setText(String.valueOf(eventoDTO.getEvacuados()));
			    daminificados.setText(String.valueOf(eventoDTO.getDamnificados()));
			    viviendasDestruidas.setText(String.valueOf(eventoDTO.getVivDestruida()));
			    transporte.setSelectedIndex(eventoDTO.getTransporte()?0:1);
			    comunicaciones.setSelectedIndex(eventoDTO.getComunicaciones()?0:1);
			    instalacionesSocorro.setSelectedIndex(eventoDTO.getInstalacionesSocorro()?0:1);
			    agropecuario.setSelectedIndex(eventoDTO.getAgropecuario()?0:1);
			    acueducto.setSelectedIndex(eventoDTO.getAcueducto()?0:1);
			    alcantarillado.setSelectedIndex(eventoDTO.getAlcantarillado()?0:1);
			    energia.setSelectedIndex(eventoDTO.getEnergia()?0:1);
			    industria.setSelectedIndex(eventoDTO.getIndustria()?0:1);
			    salud.setSelectedIndex(eventoDTO.getSalud()?0:1);
				otros.setText(eventoDTO.getOtros());
			    duracion.setText(String.valueOf(eventoDTO.getDuracion()));
			    duracionMedida.setSelectedIndex(eventoDTO.getDuracionMedida()-1);	
			    observaciones.setText(eventoDTO.getObservaciones());
			    afectados.setText(String.valueOf(eventoDTO.getAfectados()));
		    }
		    if(a=="eliminar"){
		    	datePicker.setVisible(false);
	    		departamentos.setEnabled(false);
			    latidud.setEnabled(false);
			    longitud.setEnabled(false);
			    fuente.setEnabled(false);
			    muertos.setEnabled(false);
			    perdidasPesos.setEnabled(false);
			    perdidasDolares.setEnabled(false);
			    viasAfectadas.setEnabled(false);
			    desaparecidos.setEnabled(false);
			    cultivosBosques.setEnabled(false);
			    heridosEnfermos.setEnabled(false);
			    ganado.setEnabled(false);
			    centrosEducacion.setEnabled(false);
			    reubicados.setEnabled(false);
			    vivAfectadas.setEnabled(false);
			    evacuados.setEnabled(false);
			    daminificados.setEnabled(false);
			    viviendasDestruidas.setEnabled(false);
			    transporte.setEnabled(false);
			    comunicaciones.setEnabled(false);
			    instalacionesSocorro.setEnabled(false);
			    agropecuario.setEnabled(false);
			    acueducto.setEnabled(false);
			    alcantarillado.setEnabled(false);
			    energia.setEnabled(false);
			    industria.setEnabled(false);
			    salud.setEnabled(false);
				otros.setEnabled(false);
			    duracion.setEnabled(false);
			    duracionMedida.setEnabled(false);
			    observaciones.setEnabled(false);
			    tipoEventos.setEnabled(false);
			    ciudades.setEnabled(false);
			    otrasPerdidas.setEnabled(false);
			    afectados.setEnabled(false);
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
					// TODO Auto-generated method stub
					FormDialogBox.this.procesar();
				}
			});
			
			setAnimationEnabled(true);
			add(captionPanel);
			
		}

		protected void procesar() {
			// TODO Auto-generated method stub
			EventoDTO dto= validar();
			if(dto!=null){
				
				if(a=="nuevo"){
					dto.setId(0L);
					
					IEventoAsync servidorEvento=GWT.create(IEvento.class);
					servidorEvento.nuevoEvento(dto, new AsyncCallback<Void>() {
						
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
				}else if(a=="modificar"){
					dto.setId(id);
					
					IEventoAsync servidorEvento=GWT.create(IEvento.class);
					servidorEvento.modificarEvento(dto, new AsyncCallback<Void>() {
						
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
				}else if(a=="eliminar"){
					dto.setId(id);
					
					IEventoAsync servidorEvento=GWT.create(IEvento.class);
					servidorEvento.eliminarEvento(dto, new AsyncCallback<Void>() {
						
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
				
				this.hide();
			}
		}

		private EventoDTO validar() {
			// TODO Auto-generated method stub
			EventoDTO dto =new EventoDTO();
			
			dto.setLatitud(getFloat(latidud.getText()));
			dto.setLatitud(getFloat(longitud.getText()));
			dto.setLatitudlongitud(true);
			TipoEventoDTO tpEvento=null;
			for(TipoEventoDTO tp:tiposEventosGlobal){
				if(tp.getId().equals(Long.valueOf(tipoEventos.getValue(tipoEventos.getSelectedIndex())))){
					tpEvento=tp;
				}
			}
			if(tpEvento==null){
				Window.alert("Indique tipo de evento");
				return null;
			}
			dto.setTipoEvento(tpEvento);
			
			dto.setFechaInicio(datePicker.getValue());
			DepartamentoDTO depto=null;
			for(DepartamentoDTO d:departamentosGlobal){
				if(d.getId().equals(Long.valueOf(departamentos.getValue(departamentos.getSelectedIndex())))){
					depto=d;
				}
			}
			if(depto==null){
				Window.alert("Indique departamento");
				return null;
			}

			dto.setDepartamento(depto);

			CiudadDTO ciudad=null;
			for(CiudadDTO c:depto.getCiudades()){
				if(c.getId().equals(Long.valueOf(ciudades.getValue(ciudades.getSelectedIndex())))){
					ciudad=c;
				}
			}
			if(ciudad==null){
				Window.alert("Indique ciudad");
				return null;
			}

			dto.setCiudad(ciudad);
			dto.setFuente(fuente.getText());
		    dto.setMuertos(getInt(muertos.getText()));
		    dto.setPerdidasPesos(getFloat(perdidasPesos.getText()));
		    dto.setPerdidasDolares(getFloat(perdidasDolares.getText()));
		    dto.setViasAfectadas(getFloat(viasAfectadas.getText()));
		    dto.setOtrasPerdidas(otrasPerdidas.getText());
		    dto.setDesaparecidos(getInt(desaparecidos.getText()));
		    dto.setCultivosBosques(getFloat(cultivosBosques.getText()));
		    dto.setHeridosEnfermos(getInt(heridosEnfermos.getText()));
		    dto.setGanado(getInt(ganado.getText()));
		    dto.setCentrosEducacion(getInt(centrosEducacion.getText()));
		    dto.setReubicados(getInt(reubicados.getText()));
		    dto.setCentrosHospitalarios(getInt(centrosHospitalarios.getText()));
		    dto.setVivAfectadas(getInt(vivAfectadas.getText()));
		    dto.setEvacuados(getInt(evacuados.getText()));
		    dto.setDamnificados(getInt(daminificados.getText()));
		    dto.setVivDestruida(getInt(viviendasDestruidas.getText()));
		    dto.setTransporte(getBoolean(transporte.getValue(transporte.getSelectedIndex())));
		    dto.setComunicaciones(getBoolean(comunicaciones.getValue(comunicaciones.getSelectedIndex())));
		    dto.setInstalacionesSocorro(getBoolean(instalacionesSocorro.getValue(instalacionesSocorro.getSelectedIndex())));
		    dto.setAgropecuario(getBoolean(agropecuario.getValue(agropecuario.getSelectedIndex())));
		    dto.setAcueducto(getBoolean(acueducto.getValue(acueducto.getSelectedIndex())));
		    dto.setAlcantarillado(getBoolean(alcantarillado.getValue(alcantarillado.getSelectedIndex())));
		    dto.setEnergia(getBoolean(energia.getValue(energia.getSelectedIndex())));
		    dto.setIndustria(getBoolean(industria.getValue(industria.getSelectedIndex())));
		    dto.setSalud(getBoolean(salud.getValue(salud.getSelectedIndex())));
		    dto.setOtros(otros.getText());
		    dto.setDuracion(getFloat(duracion.getText()));
		    dto.setDuracionMedida(getInt(duracionMedida.getValue(duracionMedida.getSelectedIndex())));
		    dto.setObservaciones(observaciones.getText());
		    dto.setAfectados(getInt(afectados.getText()));
		    return dto;
		}

		private Boolean getBoolean(String text) {
			// TODO Auto-generated method stub
			if(text==null || text.trim().length()==0 || text.equals("NO")){
				return false;
			}
			return true;
		}

		private int getInt(String text) {
			// TODO Auto-generated method stub
			if(text==null || text.trim().length()==0){
				return 0;
			}
			return Integer.valueOf(text);
		}

		private Float getFloat(String text) {
			// TODO Auto-generated method stub
			if(text==null || text.trim().length()==0){
				return 0F;
			}
			return Float.valueOf(text);
		}
	
	}

}
