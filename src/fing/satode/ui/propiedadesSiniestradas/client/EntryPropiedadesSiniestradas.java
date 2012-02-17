package fing.satode.ui.propiedadesSiniestradas.client;

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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import fing.satode.data.CiudadDTO;
import fing.satode.data.DepartamentoDTO;
import fing.satode.data.DepositoDTO;
import fing.satode.data.DesastreDTO;
import fing.satode.data.EventoDTO;
import fing.satode.data.HacinamientoDTO;
import fing.satode.data.InundacionDTO;
import fing.satode.data.ParcelaDTO;
import fing.satode.data.ProblemasViviendaDTO;
import fing.satode.data.UsuarioDTO;
import fing.satode.ui.desastres.client.IDesastre;
import fing.satode.ui.desastres.client.IDesastreAsync;
import fing.satode.ui.general.client.IBasicos;
import fing.satode.ui.general.client.IBasicosAsync;
import fing.satode.ui.general.data.KeyNumeric;
import fing.satode.ui.registros.client.IEvento;
import fing.satode.ui.registros.client.IEventoAsync;
import fing.satode.ui.usuarios.client.IUsuario;
import fing.satode.ui.usuarios.client.IUsuarioAsync;

public class EntryPropiedadesSiniestradas implements EntryPoint {

	final Button nuevoB = new Button("Nuevo");
	final VerticalPanel vertical = new VerticalPanel();
	private ArrayList<ParcelaDTO> parcelaGlobal;
	private ArrayList<DepartamentoDTO> departamentosGlobal;
	private Grid parcelas;
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
		RootPanel.get("parcelas").clear();
		vertical.clear();
		RootPanel.get("parcelas").add(vertical);
		
		IPropiedadesSiniestradasAsync servidorPropiedadesSiniestradas= GWT.create(IPropiedadesSiniestradas.class);
		
		servidorPropiedadesSiniestradas.listaParcelas(new AsyncCallback<ArrayList<ParcelaDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<ParcelaDTO> result) {
				parcelaGlobal=result;
				parcelas = new Grid(result.size()+1,9);
				parcelas.setWidget(0, 0, new Label("ID"));
				parcelas.setWidget(0, 1, new Label("Direcci\u00F3n"));
				parcelas.setWidget(0, 2, new Label("Ciudad"));
				parcelas.setWidget(0, 3, new Label("Departamento"));
				parcelas.setWidget(0, 4, new Label("Telefono"));
				parcelas.setWidget(0, 5, new Label("¿Vivienda?"));
				parcelas.setWidget(0, 6, new Label("Otros Usos"));
				parcelas.setWidget(0, 8, modificarLabel);
				parcelas.setWidget(0, 9, eliminarLabel);
				
				for(int i=0;i<9;i++){
					parcelas.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				
				parcelas.setBorderWidth(1);
				int row=1;
				for(ParcelaDTO p: result){
					parcelas.setWidget(row, 0, new Label(p.getId().toString()));
					parcelas.setWidget(row, 1, new Label(p.getDireccion()));
					parcelas.setWidget(row, 2, new Label(p.getCiudad().getNombre()));
					parcelas.setWidget(row, 3, new Label(p.getDepartamento().getNombre()));
					parcelas.setWidget(row, 4, new Label(p.getTelefono()));
					parcelas.setWidget(row, 5, new Label(p.getTipoParcela().isVivienda()?"SI":"NO"));
					parcelas.setWidget(row, 6, new Label(p.getTipoParcela().getOtrosUsos()));
					
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
					parcelas.setWidget(row, 7, modificarI);
					parcelas.setWidget(row, 8, eliminarI);
					row++;
				}
				vertical.add(parcelas);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
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
		final VerticalPanel vertical1= new VerticalPanel();
		final VerticalPanel vertical2= new VerticalPanel();
		final VerticalPanel vertical3= new VerticalPanel();
		final Label label = new Label();
		final Grid gridIzqParcelas= new Grid(6,2);
		final Grid gridIzqDatosViv= new Grid(23,2);
		final Grid gridDerProblViv= new Grid(13,2);
		final Grid gridDerHacin= new Grid(5,2);
		final Grid gridDerInund= new Grid(13,2);
		final Grid grid= new Grid(1,2);
	    final TextBox direccion=new TextBox();
	    final TextBox telefono=new TextBox();
	    final ListBox vivienda=new  ListBox();
	    final TextBox otrosUsos=new  TextBox();
	    final ListBox departamentos= new ListBox();
	    final ListBox ciudades= new ListBox();
	    final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
		
		//UnidadesParcelas
		final TextBox descripcion=new TextBox();
		final TextBox nivelAgua=new TextBox();
		final TextBox metros2afectados=new TextBox();
		final ListBox nivelPiso=new ListBox();
		;
		
		//Datos Vivienda
		final TextBox obsMaterialTecho=new TextBox();
		final TextBox obsMaterialPiso=new TextBox();
		final TextBox obsProcedenciaAgua= new TextBox();
		final TextBox obsMaterialParedes= new TextBox();
		final TextBox obsSanitaria= new TextBox();
		final TextBox obsBasura= new TextBox();
	
		
		//Referencias a constantes
		final ListBox ocupacion = new ListBox();
		final ListBox estadoPared = new ListBox();
		final ListBox materialTecho = new ListBox();
		final ListBox materialParedes = new ListBox();
		final ListBox materialPiso = new ListBox();
		final ListBox banios = new ListBox();
		final ListBox propietarioOtras = new ListBox();
		final ListBox tenenciaVivienda = new ListBox();
		final ListBox mudarse = new ListBox();
		final ListBox procedenciaAguaConsumo = new ListBox();
		final ListBox procedenciaAguaVivienda = new ListBox();
		final ListBox basura = new ListBox();
		final ListBox categoriaVivienda = new ListBox();
		final ListBox sanitario = new ListBox();
		final ListBox estadoTerminacion = new ListBox();
		final ListBox energia = new ListBox();
		final ListBox conservacionVivienda = new ListBox();
		
		
	
		//ProblemasVivienda (Atributos Boolean)
		final ListBox humedadesTecho = new ListBox();
		final ListBox goterasTecho = new ListBox();
		final ListBox puertasVentanasMalEstado = new ListBox();
		final ListBox grietasPiso = new ListBox();
		final ListBox caidaRevoques = new ListBox();
		final ListBox cielorasoDesprendido = new ListBox();
		final ListBox pocaLuzSolar = new ListBox();
		final ListBox escazaVentilacion = new ListBox();
		final ListBox peligroDerrumbe = new ListBox();
		final ListBox instalacionesMalas = new ListBox();
		final ListBox manchasParedesTechos = new ListBox();
		final ListBox descalceDeCimientos = new ListBox();
		final ListBox pozoNegroMalEstado = new ListBox();
		
		//Hacinamiento
		final TextBox cantFamilias= new TextBox();
		final TextBox cantHabitaciones= new TextBox();
		final TextBox cantHabDormitorio= new TextBox();
		final ListBox actividadLaboralEnViv = new ListBox();
		final TextBox cantHabTrabajo= new TextBox();
		
		//Inundacion
		final ListBox inundadaAnteriormente = new ListBox();
		final TextBox anios= new TextBox();
		final TextBox antiguedadEnCasa= new TextBox();
		final ListBox irsePorInundacion = new ListBox();
		final TextBox observacionesNoIrse= new TextBox();
		final TextBox obsAlojamientoEnInundacion= new TextBox();
		final TextBox cntDiasFuera= new TextBox();
		final TextBox alturaAgua= new TextBox();
		final TextBox obsAguaVivienda= new TextBox();
		final ListBox abandonoViviendaPorInund = new ListBox();
		
		
		final ListBox alojamientoInundacion = new ListBox();
		final ListBox persepcionVivienda = new ListBox();
		final ListBox aguaEnVivienda = new ListBox();
		
		
	
	    @SuppressWarnings("deprecation")
		public FormDialogBox(Long idParcela, String accion) {
			a=accion;
	    	id=idParcela;
	    	
	    	if(a=="modificar") label.setText("Modificar Parcela");
			if(a=="eliminar") label.setText("Eliminar Parcela");
			if(a=="nuevo") label.setText("Nueva Parcela");
			
			gridIzqParcelas.setWidget(0, 0, new Label("Direccion"));
			gridIzqParcelas.setWidget(1, 0, new Label("Telefono"));
			gridIzqParcelas.setWidget(2, 0, new Label("Departamento"));
			gridIzqParcelas.setWidget(3, 0, new Label("Ciudad"));
			gridIzqParcelas.setWidget(4, 0, new Label("¿Vivienda?"));
			gridIzqParcelas.setWidget(5, 0, new Label("Otros Usos"));
						
			gridIzqParcelas.setWidget(0, 1, direccion);
			gridIzqParcelas.setWidget(1, 1, telefono);
			gridIzqParcelas.setWidget(2, 1, departamentos);
			gridIzqParcelas.setWidget(3, 1, ciudades);
			gridIzqParcelas.setWidget(4, 1, vivienda);
			gridIzqParcelas.setWidget(5, 1, otrosUsos);
			gridIzqParcelas.setBorderWidth(1);
			
			
			gridIzqDatosViv.setWidget(0, 0, new Label(" Indique si esta vivienda está…"));
			gridIzqDatosViv.setWidget(1, 0, new Label("¿Cuál es el material predominante en las paredes?"));
			gridIzqDatosViv.setWidget(2, 0, new Label("Observaciones material de las paredes"));
			gridIzqDatosViv.setWidget(3, 0, new Label("Las paredes de esta vivienda, se encuentran..."));
			gridIzqDatosViv.setWidget(4, 0, new Label("¿Cuál es el material predominante en el techo?"));
			gridIzqDatosViv.setWidget(5, 0, new Label("Observaciones material en el techo"));
			gridIzqDatosViv.setWidget(6, 0, new Label("¿Cuál es el material predominante en los pisos?"));
			gridIzqDatosViv.setWidget(7, 0, new Label("Observaciones material en los pisos"));
			gridIzqDatosViv.setWidget(8, 0, new Label("Estado de terminación de la vivienda"));
			gridIzqDatosViv.setWidget(9, 0, new Label("Categoría de la vivienda"));
			gridIzqDatosViv.setWidget(10, 0, new Label("¿De donde proviene el agua que utilizan para beber y cocinar? "));
			gridIzqDatosViv.setWidget(11, 0, new Label("Observaciones procedencia de agua"));
			gridIzqDatosViv.setWidget(12, 0, new Label("¿Cómo llega el agua a la vivienda?"));
			gridIzqDatosViv.setWidget(13, 0, new Label("Qué tipo de energía tiene su vivienda?"));
			gridIzqDatosViv.setWidget(14, 0, new Label("¿Cómo se realiza la evacuación del servicio sanitario?"));
			gridIzqDatosViv.setWidget(15, 0, new Label("Observaciones evacuación del servicio sanitario"));
			gridIzqDatosViv.setWidget(16, 0, new Label("¿Dónde tira la basura?"));
			gridIzqDatosViv.setWidget(17, 0, new Label("Observaciones de tirar la basura"));
			gridIzqDatosViv.setWidget(18, 0, new Label("¿Esta vivienda tiene servicio higiénico?"));
			gridIzqDatosViv.setWidget(19, 0, new Label("Respecto a la vivienda donde vive  usted,  es… "));
			gridIzqDatosViv.setWidget(20, 0, new Label("Independientemente de  la situación respecto a ésta vivienda, ud es propietario de:"));
			gridIzqDatosViv.setWidget(21, 0, new Label("Estado de conservación de la vivienda"));
			gridIzqDatosViv.setWidget(22, 0, new Label("Si le ofrecieran una casa igual a esta, ubicada en un barrio mejor que no se inunde... "));
			
						
			gridIzqDatosViv.setWidget(0, 1, ocupacion);
			gridIzqDatosViv.setWidget(1, 1, materialParedes);
			gridIzqDatosViv.setWidget(2, 1, obsMaterialParedes);
			gridIzqDatosViv.setWidget(3, 1, estadoPared);
			gridIzqDatosViv.setWidget(4, 1, materialTecho);
			gridIzqDatosViv.setWidget(5, 1, obsMaterialTecho);
			gridIzqDatosViv.setWidget(6, 1, materialPiso);
			gridIzqDatosViv.setWidget(7, 1, obsMaterialPiso);
			gridIzqDatosViv.setWidget(8, 1, estadoTerminacion);
			gridIzqDatosViv.setWidget(9, 1, categoriaVivienda);
			gridIzqDatosViv.setWidget(10, 1, procedenciaAguaConsumo);
			gridIzqDatosViv.setWidget(11, 1, obsProcedenciaAgua);
			gridIzqDatosViv.setWidget(12, 1, procedenciaAguaVivienda);
			gridIzqDatosViv.setWidget(13, 1, energia);
			gridIzqDatosViv.setWidget(14, 1, sanitario);
			gridIzqDatosViv.setWidget(15, 1, obsSanitaria);
			gridIzqDatosViv.setWidget(16, 1, basura);
			gridIzqDatosViv.setWidget(17, 1, obsBasura);
			gridIzqDatosViv.setWidget(18, 1, banios);
			gridIzqDatosViv.setWidget(19, 1, tenenciaVivienda);
			gridIzqDatosViv.setWidget(20, 1, propietarioOtras);
			gridIzqDatosViv.setWidget(21, 1, conservacionVivienda);
			gridIzqDatosViv.setWidget(22, 1, mudarse);
			gridIzqDatosViv.setBorderWidth(1);
			
			
			gridDerProblViv.setWidget(0, 0, new Label("Humedades en techos"));
			gridDerProblViv.setWidget(1, 0, new Label("Goteras en el techo"));
			gridDerProblViv.setWidget(2, 0, new Label("Puertas y ventanas en mal estado"));
			gridDerProblViv.setWidget(3, 0, new Label("Grietas en pisos"));
			gridDerProblViv.setWidget(4, 0, new Label("Caída de revoque en paredes o techos"));
			gridDerProblViv.setWidget(5, 0, new Label("Cielorrasos desprendidos"));
			gridDerProblViv.setWidget(6, 0, new Label("Poca luz solar"));
			gridDerProblViv.setWidget(7, 0, new Label("Escaza ventilación"));
			gridDerProblViv.setWidget(8, 0, new Label("Peligro de derrumbe"));
			gridDerProblViv.setWidget(9, 0, new Label("Instalaciones en mal estado"));
			gridDerProblViv.setWidget(10, 0, new Label("Manchas, hongos en paredes o techos"));
			gridDerProblViv.setWidget(11, 0, new Label("Descalce de cimientos"));
			gridDerProblViv.setWidget(12, 0, new Label("Pozo negro en mal estado"));
			
			gridDerProblViv.setWidget(0, 1, humedadesTecho);
			gridDerProblViv.setWidget(1, 1, goterasTecho);
			gridDerProblViv.setWidget(2, 1, puertasVentanasMalEstado);
			gridDerProblViv.setWidget(3, 1, grietasPiso);
			gridDerProblViv.setWidget(4, 1, caidaRevoques);
			gridDerProblViv.setWidget(5, 1, cielorasoDesprendido);
			gridDerProblViv.setWidget(6, 1, pocaLuzSolar);
			gridDerProblViv.setWidget(7, 1, escazaVentilacion);
			gridDerProblViv.setWidget(8, 1, peligroDerrumbe);
			gridDerProblViv.setWidget(9, 1, instalacionesMalas);
			gridDerProblViv.setWidget(10, 1, manchasParedesTechos);
			gridDerProblViv.setWidget(11, 1, descalceDeCimientos);
			gridDerProblViv.setWidget(12, 1, pozoNegroMalEstado);
			gridDerProblViv.setBorderWidth(1);
			
			gridDerInund.setWidget(0, 0, new Label("¿Se ha inundado esta vivienda anteriormente?"));
			gridDerInund.setWidget(1, 0, new Label("¿En que años?"));
			gridDerInund.setWidget(2, 0, new Label("¿Hace cuántos años viven ustedes en esta vivienda?"));
			gridDerInund.setWidget(3, 0, new Label("Usted ha considerado la posibilidad de irse de este barrio porque se inunda?"));
			gridDerInund.setWidget(4, 0, new Label("En caso de Negativo, ¿Por qué no?"));
			gridDerInund.setWidget(5, 0, new Label("¿Ingresó en esta oportunidad el agua en el interior de su vivienda?"));
			gridDerInund.setWidget(6, 0, new Label("Especificar altura alcanzada (nivel de piso terminado de la vivienda)"));
			gridDerInund.setWidget(7, 0, new Label("Observaciones ingreso de agua en Vivienda"));
			gridDerInund.setWidget(8, 0, new Label("¿Abandonaron su vivienda durante la inundación?"));
			gridDerInund.setWidget(9, 0, new Label("¿Dónde estaban alojados durante la evacuación?"));
			gridDerInund.setWidget(10, 0, new Label("Observaciones alojamineto durante la evacuación"));
			gridDerInund.setWidget(11, 0, new Label("¿Cuántos días estuvo fuera de su hogar?"));
			gridDerInund.setWidget(12, 0, new Label("¿Usted cree que este es"));
			
			gridDerInund.setWidget(0, 1, inundadaAnteriormente);
			gridDerInund.setWidget(1, 1, anios);
			gridDerInund.setWidget(2, 1, antiguedadEnCasa);
			gridDerInund.setWidget(3, 1, irsePorInundacion);
			gridDerInund.setWidget(4, 1, observacionesNoIrse);
			gridDerInund.setWidget(5, 1, aguaEnVivienda);
			gridDerInund.setWidget(6, 1, alturaAgua);
			gridDerInund.setWidget(7, 1, obsAguaVivienda);
			gridDerInund.setWidget(8, 1, abandonoViviendaPorInund);
			gridDerInund.setWidget(9, 1, alojamientoInundacion);
			gridDerInund.setWidget(10, 1, obsAlojamientoEnInundacion);
			gridDerInund.setWidget(11, 1, cntDiasFuera);
			gridDerInund.setWidget(12, 1, persepcionVivienda);
			gridDerInund.setBorderWidth(1);
			
			gridDerHacin.setWidget(0, 0, new Label("¿Cuántas familias comparten esta vivienda?"));
			gridDerHacin.setWidget(1, 0, new Label("¿Cuántas habitaciones tiene esta vivienda sin considerar baño y cocina?"));
			gridDerHacin.setWidget(2, 0, new Label("¿Cuántas habitaciones usa para dormir?"));
			gridDerHacin.setWidget(3, 0, new Label("¿En estas vivienda se realiza alguna actividad que le reporta ingresos a las personas que viven aquí?"));
			gridDerHacin.setWidget(4, 0, new Label("¿Cuántas habitaciones se usan para trabajar?"));
			
			gridDerHacin.setWidget(0, 1, cantFamilias);
			gridDerHacin.setWidget(1, 1, cantHabitaciones);
			gridDerHacin.setWidget(2, 1, cantHabDormitorio);
			gridDerHacin.setWidget(3, 1, actividadLaboralEnViv);
			gridDerHacin.setWidget(4, 1, cantHabTrabajo);
			gridDerHacin.setBorderWidth(1);
			
			
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
		    
		    cantFamilias.addKeyboardListener(new KeyNumeric());
		    cantHabitaciones.addKeyboardListener(new KeyNumeric());
		    cantHabDormitorio.addKeyboardListener(new KeyNumeric());
		    cantHabTrabajo.addKeyboardListener(new KeyNumeric());
		    antiguedadEnCasa.addKeyboardListener(new KeyNumeric());
		    cntDiasFuera.addKeyboardListener(new KeyNumeric());
		 
		    /*  
		    if (a == "modificar" || a == "eliminar" ){
		    	
		    	ParcelaDTO parcelaDTO= null;
	    		for(ParcelaDTO p:parcelaGlobal){
	    			if(p.getId().equals(id)){
	    				parcelaDTO=p;
	    			}
	    		}
	    		
		    	
	    		direccion.setText(parcelaDTO.getDireccion());
	    		telefono.setText(parcelaDTO.getTelefono());
	    		vivienda.addItem("SI", "SI");
	    		vivienda.addItem("NO", "NO");
	    		vivienda.setSelectedIndex(parcelaDTO.getTipoParcela().isVivienda()?0:1);   
	    		otrosUsos.setText(parcelaDTO.getTipoParcela().getOtrosUsos());
	    		
	    		int row=0;
	    		DepartamentoDTO dtoDepto=null;
			    for(DepartamentoDTO d:departamentosGlobal){
			    	row++;
				    if(d.getId().equals(parcelaDTO.getDepartamento().getId())){
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
					if(c.getId().equals(parcelaDTO.getCiudad().getId())){
						ciudades.setSelectedIndex(row);
					}
				}
	    		
	    		
	    			
	    			//UnidadesParcelas
	    			final TextBox descripcion=new TextBox();
	    			final TextBox nivelAgua=new TextBox();
	    			final TextBox metros2afectados=new TextBox();
	    			final ListBox nivelPiso=new ListBox();
	    			;
	    			
	    			//Datos Vivienda
	    			final TextBox obsMaterialTecho=new TextBox();
	    			final TextBox obsMaterialPiso=new TextBox();
	    			final TextBox obsProcedenciaAgua= new TextBox();
	    			final TextBox obsMaterialParedes= new TextBox();
	    			final TextBox obsSanitaria= new TextBox();
	    			final TextBox obsBasura= new TextBox();
	    		
	    			
	    			//Referencias a constantes
	    			final ListBox ocupacion = new ListBox();
	    			final ListBox estadoPared = new ListBox();
	    			final ListBox materialTecho = new ListBox();
	    			final ListBox materialParedes = new ListBox();
	    			final ListBox materialPiso = new ListBox();
	    			final ListBox banios = new ListBox();
	    			final ListBox propietarioOtras = new ListBox();
	    			final ListBox tenenciaVivienda = new ListBox();
	    			final ListBox mudarse = new ListBox();
	    			final ListBox procedenciaAguaConsumo = new ListBox();
	    			final ListBox procedenciaAguaVivienda = new ListBox();
	    			final ListBox basura = new ListBox();
	    			final ListBox categoriaVivienda = new ListBox();
	    			final ListBox sanitario = new ListBox();
	    			final ListBox estadoTerminacion = new ListBox();
	    			final ListBox energia = new ListBox();
	    			final ListBox conservacionVivienda = new ListBox();
	    			
	    			
	    		
	    			//ProblemasVivienda (Atributos Boolean)
	    			final ListBox humedadesTecho = new ListBox();
	    			final ListBox goterasTecho = new ListBox();
	    			final ListBox puertasVentanasMalEstado = new ListBox();
	    			final ListBox grietasPiso = new ListBox();
	    			final ListBox caidaRevoques = new ListBox();
	    			final ListBox cielorasoDesprendido = new ListBox();
	    			final ListBox pocaLuzSolar = new ListBox();
	    			final ListBox escazaVentilacion = new ListBox();
	    			final ListBox peligroDerrumbe = new ListBox();
	    			final ListBox instalacionesMalas = new ListBox();
	    			final ListBox manchasParedesTechos = new ListBox();
	    			final ListBox descalceDeCimientos = new ListBox();
	    			final ListBox pozoNegroMalEstado = new ListBox();
	    			
	    			//Hacinamiento
	    			final TextBox cantFamilias= new TextBox();
	    			final TextBox cantHabitaciones= new TextBox();
	    			final TextBox cantHabDormitorio= new TextBox();
	    			final ListBox actividadLaboralEnViv = new ListBox();
	    			final TextBox cantHabTrabajo= new TextBox();
	    			
	    			//Inundacion
	    			final ListBox inundadaAnteriormente = new ListBox();
	    			final TextBox anios= new TextBox();
	    			final TextBox antiguedadEnCasa= new TextBox();
	    			final ListBox irsePorInundacion = new ListBox();
	    			final TextBox observacionesNoIrse= new TextBox();
	    			final TextBox obsAlojamientoEnInundacion= new TextBox();
	    			final TextBox cntDiasFuera= new TextBox();
	    			final TextBox alturaAgua= new TextBox();
	    			final TextBox obsAguaVivienda= new TextBox();
	    			final ListBox abandonoViviendaPorInund = new ListBox();
	    			
	    			
	    			final ListBox alojamientoInundacion = new ListBox();
	    			final ListBox persepcionVivienda = new ListBox();
	    			final ListBox aguaEnVivienda = new ListBox();
	    		
	    		
	    		
		    	
			    telefono.setText(depositoDTO.getTelefono());
			    mail.setText(depositoDTO.getMail());
			    area2.setText(String.valueOf(depositoDTO.getArea2()));
			    area3.setText(String.valueOf(depositoDTO.getArea3()));
			    responsable.setText(depositoDTO.getResponsable());
			    
			    int row=0;
			    for(DepartamentoDTO d:departamentosGlobal){
			    	row++;
				    if(d.getId().equals(depositoDTO.getDepartamento().getId())){
			    		departamentos.setSelectedIndex(row);
			    		ciudades.clear();
			    		ciudades.addItem("Seleccionar","0");
			    		int row2=1;
						for(CiudadDTO c:d.getCiudades()){
							ciudades.addItem(c.getNombre(),c.getId().toString());
							if(c.getId().equals(depositoDTO.getCiudad().getId())){
								ciudades.setSelectedIndex(row2);
							}
							row2++;
						}
				    }
			    }
			   
		    }
		    
		    if ( a == "eliminar" ){
		    	direccion.setEnabled(false);
			    telefono.setEnabled(false);
			    mail.setEnabled(false);
			    area2.setEnabled(false);
			    area3.setEnabled(false);
			    responsable.setEnabled(false);
			    departamentos.setEnabled(false);
			    ciudades.setEnabled(false);
			    
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
					// TODO Auto-generated method stub
					FormDialogBox.this.procesar();
				}
			});
				
			setAnimationEnabled(true);
			add(vertical);
			center();*/
		}
	    
	

		protected void procesar() {
			// TODO Auto-generated method stub
		/*	DepositoDTO dto=validar();
			if(dto!=null){
				dto.setId(id);
				if(a=="modificar"){
					
					IPropiedadesSiniestradasAsync servidorDeposito=GWT.create(IPropiedadesSiniestradas.class);
					
					servidorDeposito.modificarDeposito(dto,new AsyncCallback<Void>() {
						
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
					IPropiedadesSiniestradasAsync servidorDeposito=GWT.create(IPropiedadesSiniestradas.class);
					
					servidorDeposito.nuevoDeposito(dto,new AsyncCallback<Void>() {
						
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
					IPropiedadesSiniestradasAsync servidorDeposito=GWT.create(IPropiedadesSiniestradas.class);
					
					servidorDeposito.eliminarDeposito(dto,new AsyncCallback<Void>() {
						
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
			*/
		}

		private DepositoDTO validar() {
			// TODO Auto-generated method stub
			DepositoDTO dto= new DepositoDTO();
			
			/*if(responsable.getText().trim().length()==0){
				Window.alert("Indique responsable");
				return null;
			}
			dto.setResponsable(responsable.getText());
			
			if(departamentos.getSelectedIndex()<1){
				Window.alert("Indique departamento");
				return null;
			}
			
			Long idDep=Long.valueOf(departamentos.getValue(departamentos.getSelectedIndex()));
			for(DepartamentoDTO d:departamentosGlobal){
				if(idDep.equals(d.getId())){
					dto.setDepartamento(d);
				}
			}
			
			if(ciudades.getSelectedIndex()<1){
				Window.alert("Indique ciudad");
				return null;
			}
			
			Long idCiu=Long.valueOf(ciudades.getValue(ciudades.getSelectedIndex()));
			for(CiudadDTO c:dto.getDepartamento().getCiudades()){
				if(idCiu.equals(c.getId())){
					dto.setCiudad(c);
				}
			}
			
			dto.setDireccion(direccion.getText());
			dto.setTelefono(telefono.getText());
			dto.setMail(mail.getText());
			dto.setArea2(getFloat(area2.getText()));
			dto.setArea3(getFloat(area3.getText()));
			*/
			
			return dto;
			
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
