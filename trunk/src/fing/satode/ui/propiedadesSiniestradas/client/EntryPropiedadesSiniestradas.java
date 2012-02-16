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
		final VerticalPanel vertical= new VerticalPanel();
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
		final TextBox obsBcantHabTrabajoasura= new TextBox();
		final ListBox actividadLaboralEnViv = new ListBox();
		

		
		
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
			
			
	    }//ESte cierre no va, es solo para que compile.	
			
			
		/*	
			
			
			
			
			
			
			gridDerInund.setWidget(0, 0, new Label("¿Se ha inundado esta vivienda anteriormente?"));
			gridDerInund.setWidget(1, 0, new Label("¿En que años?"));
			gridDerInund.setWidget(2, 0, new Label("¿Hace cuántos años viven ustedes en esta vivienda?"));
			gridDerInund.setWidget(3, 0, new Label("Usted ha considerado la posibilidad de irse de este barrio porque se inunda?"));
			gridDerInund.setWidget(4, 0, new Label("En caso de Negativo, ¿Por qué no?"));
			gridDerInund.setWidget(5, 0, new Label("¿Ingresó en esta oportunidad el agua en el interior de su vivienda?"));
			gridDerInund.setWidget(5, 0, new Label("Especificar altura alcanzada (nivel de piso terminado de la vivienda)"));
			gridDerInund.setWidget(6, 0, new Label("Observaciones ingreso de agua en Vivienda"));
			
			
			gridDerInund.setWidget(7, 0, new Label("¿Cuántos días estuvo fuera de su hogar?"));
			gridDerInund.setWidget(9, 0, new Label("¿Abandonaron su vivienda durante la inundación?));
			gridDerInund.setWidget(10, 0, new Label("Manchas, hongos en paredes o techos"));
			gridDerInund.setWidget(11, 0, new Label("Descalce de cimientos"));
			gridDerInund.setWidget(12, 0, new Label("Pozo negro en mal estado"));
			
			
			
			
			gridDerInund.setWidget(0, 1, inundadaAnteriormente);
			gridDerInund.setWidget(1, 1, anios);
			gridDerInund.setWidget(2, 1, antiguedadEnCasa);
			gridDerInund.setWidget(3, 1, irsePorInundacion);
			gridDerInund.setWidget(4, 1, observacionesNoIrse);
			gridDerInund.setWidget(12, 1, aguaEnVivienda);
			gridDerInund.setWidget(8, 1, alturaAgua);
			gridDerInund.setWidget(9, 1, obsAguaVivienda);
			
			gridDerInund.setWidget(10, 1, abandonoViviendaPorInund);
			gridDerInund.setWidget(5, 1, alojamientoInundacion);
			gridDerInund.setWidget(6, 1, obsAlojamientoEnInundacion);
			gridDerInund.setWidget(7, 1, cntDiasFuera);
			
			
			
			
			
			
			gridDerInund.setWidget(11, 1, persepcionVivienda);
			
			gridDerInund.setBorderWidth(1);
			
			
	    	
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
		    
		    area2.addKeyboardListener(new KeyNumeric());
		    area3.addKeyboardListener(new KeyNumeric());
		    
		    if (a == "modificar" || a == "eliminar" ){
		    	
		    	DepositoDTO depositoDTO= null;
	    		for(DepositoDTO d:depositoGlobal){
	    			if(d.getId().equals(id)){
	    				depositoDTO=d;
	    			}
	    		}
	    		
		    	
		    	direccion.setText(depositoDTO.getDireccion());
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
			center();
		}
	    
	    */

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
