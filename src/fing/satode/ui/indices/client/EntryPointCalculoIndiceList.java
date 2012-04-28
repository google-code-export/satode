package fing.satode.ui.indices.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.linker.IFrameLinker;
import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.dom.client.IFrameElement;
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
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.sun.corba.se.spi.activation.Server;

import fing.satode.constantes.IR1;
import fing.satode.constantes.IR2;
import fing.satode.constantes.IR3;
import fing.satode.constantes.IR4;
import fing.satode.constantes.IR5;
import fing.satode.constantes.IR6;
import fing.satode.constantes.ItemConstante;
import fing.satode.constantes.MD1;
import fing.satode.constantes.MD2;
import fing.satode.constantes.MD3;
import fing.satode.constantes.MD4;
import fing.satode.constantes.MD5;
import fing.satode.constantes.MD6;
import fing.satode.constantes.PF1;
import fing.satode.constantes.PF2;
import fing.satode.constantes.PF3;
import fing.satode.constantes.PF4;
import fing.satode.constantes.PF5;
import fing.satode.constantes.PF6;
import fing.satode.constantes.RR1;
import fing.satode.constantes.RR2;
import fing.satode.constantes.RR3;
import fing.satode.constantes.RR4;
import fing.satode.constantes.RR5;
import fing.satode.constantes.RR6;
import fing.satode.constantes.TipoIndice;
import fing.satode.data.CalculoIndiceDTO;
import fing.satode.data.CostoDTO;
import fing.satode.data.DesastreDTO;
import fing.satode.data.IdlDTO;
import fing.satode.data.IdlDepartamentoDTO;
import fing.satode.data.IdlTipoEventoDTO;
import fing.satode.data.IgrDTO;
import fing.satode.data.TipoCostoDTO;
import fing.satode.data.UsuarioDTO;
import fing.satode.ui.desastres.client.IDesastre;
import fing.satode.ui.desastres.client.IDesastreAsync;
import fing.satode.ui.general.data.KeyNumeric;
import fing.satode.ui.usuarios.client.IUsuario;
import fing.satode.ui.usuarios.client.IUsuarioAsync;

public class EntryPointCalculoIndiceList implements EntryPoint {

	final Button nuevoIDL = new Button("Nuevo IDL");
	final Button nuevoIGR = new Button("Nuevo IGR");
	final Button buscarB = new Button("Buscar");
	final VerticalPanel vertical = new VerticalPanel();
	final HorizontalPanel horizontalBotonera = new HorizontalPanel();
	private ArrayList<CalculoIndiceDTO> calculoIndicesGlobal;
	
	private Grid calculoIndiceGrid;
	private UsuarioDTO usuarioGlobal;
	final Label modificarLabel= new Label("Modificar");
	final Label eliminarLabel= new Label("Eliminar");
	final ListBox tipoFiltro= new ListBox();
	final Button graficarIDL= new Button("Graficar IDL");
	final Button graficarIGR= new Button("Graficar IGR");
	
	@Override
	public void onModuleLoad() {
		
		horizontalBotonera.add(new Label("Tipo"));
		horizontalBotonera.add(tipoFiltro);
		horizontalBotonera.add(buscarB);
		horizontalBotonera.add(nuevoIDL);
		horizontalBotonera.add(graficarIDL);
		horizontalBotonera.add(nuevoIGR);
		horizontalBotonera.add(graficarIGR);
		
		RootPanel.get("botones").add(horizontalBotonera);
		
		graficarIDL.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				//Window.open("/gaficaidl.gidl.pdf", "_blank", null);
				RootPanel.get("indices").clear();
				vertical.clear();
				RootPanel.get("indices").add(vertical);
				Frame frame =new Frame("./graficaidl.idlpdf");
				frame.setSize("700px", "500px");
				
				vertical.add(frame);
				
			}
		});
		
		graficarIGR.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				//Window.open("/gaficaigr.gigr.pdf", "_blank", null);
				RootPanel.get("indices").clear();
				vertical.clear();
				RootPanel.get("indices").add(vertical);
				Frame frame =new Frame("./graficaigr.igrpdf");
				frame.setSize("700px", "500px");
				
				vertical.add(frame);
			}
		});
		

		nuevoIDL.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				FormDialogIDLBox dialog= new FormDialogIDLBox(0L, "nuevo");
				dialog.show();
			}
		});
		
		nuevoIGR.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				FormDialogIGRBox dialog= new FormDialogIGRBox();
				dialog.show();
			}
		});
		
		buscarB.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				cargarLista();
			}
		});

		tipoFiltro.addItem("Seleccionar","0");
		for(ItemConstante item:TipoIndice.getItems()){
			tipoFiltro.addItem(item.getTexto(),String.valueOf(item.getCodigo()));	
		}
		
		IUsuarioAsync serverUsuario=GWT.create(IUsuario.class);
		
		serverUsuario.getUsuarioLogin(new AsyncCallback<UsuarioDTO>() {
			
			@Override
			public void onSuccess(UsuarioDTO result) {
				usuarioGlobal=result;
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
	}
	
	private void cargarLista() {
		RootPanel.get("indices").clear();
		vertical.clear();
		RootPanel.get("indices").add(vertical);
		
		IIndicesAsync serverIndice=GWT.create(IIndices.class);
		
		int tipo=Integer.valueOf(tipoFiltro.getValue(tipoFiltro.getSelectedIndex()));
		
		serverIndice.buscarCalculoIndice(tipo, new AsyncCallback<ArrayList<CalculoIndiceDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<CalculoIndiceDTO> result) {
				calculoIndicesGlobal=result;
				calculoIndiceGrid = new Grid(calculoIndicesGlobal.size()+1,6);
				calculoIndiceGrid.setWidget(0, 0, new Label("ID"));
				calculoIndiceGrid.setWidget(0, 1, new Label("Tipo"));
				calculoIndiceGrid.setWidget(0, 2, new Label("Fecha"));
				calculoIndiceGrid.setWidget(0, 3, new Label("Observacion"));
				calculoIndiceGrid.setWidget(0, 4, new Label("Valor"));
				calculoIndiceGrid.setWidget(0, 5, new Label("Analizar"));
				
				for(int i=0;i<6;i++){
					calculoIndiceGrid.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				
				calculoIndiceGrid.setBorderWidth(1);
				int row=1;
				DateTimeFormat format = DateTimeFormat.getFormat("dd/MM/yyyy");
				for(CalculoIndiceDTO e: result){
					calculoIndiceGrid.setWidget(row, 0, new Label(e.getId().toString()));
					calculoIndiceGrid.setWidget(row, 1, new Label(TipoIndice.getTXT(e.getTipo())));
					calculoIndiceGrid.setWidget(row, 2, new Label(format.format(e.getFecha())));
					calculoIndiceGrid.setWidget(row, 3, new Label(e.getObservaciones()));
					calculoIndiceGrid.setWidget(row, 4, new Label(String.valueOf(e.getValor())));
				
					if(e.getTipo()== TipoIndice.IDL){
						final IdlDTO idlDto= (IdlDTO)e;
						
						Button analizar=new Button("Analizar");
						analizar.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								// TODO Auto-generated method stub
								IIndicesAsync serverIndice=GWT.create(IIndices.class);
								
								serverIndice.getCalculoIndice(idlDto.getId(), new AsyncCallback<CalculoIndiceDTO>() {
									
									@Override
									public void onSuccess(CalculoIndiceDTO result) {
										FormIDLAnalizar1 form=new FormIDLAnalizar1((IdlDTO)result);
										form.show();	
									}
									
									@Override
									public void onFailure(Throwable caught) {
										caught.printStackTrace();
										Window.alert("ERROR AJAX");
									}
								});
							}
						});
						calculoIndiceGrid.setWidget(row, 5, analizar);
												
					}else{
						final IgrDTO igrDto= (IgrDTO)e;
						
						Button analizar=new Button("Analizar");
						analizar.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								// TODO Auto-generated method stub
								FormDialogIGRAnalizarBox form=new FormDialogIGRAnalizarBox(igrDto);
								form.show();	
							}
						});
						
						calculoIndiceGrid.setWidget(row, 5, analizar);
					}
					
					row++;
				}
				
				vertical.add(calculoIndiceGrid);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
		

	
	}

	public class FormIDLAnalizar1 extends DialogBox
	{
		private IdlDTO idto;
		private CaptionPanel captionPrincipal=new CaptionPanel("IDL-Pesos por departamento");
		private VerticalPanel vertical=new VerticalPanel();
		private Button cerrar=new Button("Cerrar");
		private Grid gridAux=new Grid(1,2);
		
		final Grid grid= new Grid(7,2);
	    final Label fecha = new Label();
	    final TextBox observacion=new TextBox();
	    final TextBox valVivSocial=new TextBox();
	    final TextBox valHectaria=new TextBox();
	    final ListBox tipo=new ListBox();
	    final Label fechaInicio= new Label();
	    final Label fechaFin= new Label();
		
		public FormIDLAnalizar1(IdlDTO dto )
		{
			idto=dto;
			
			
			DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
	        String dateString = format.format(dto.getFecha());
	       
	        fecha.setText(dateString);
	        fechaInicio.setText(format.format(dto.getFechaInicio()));
	        fechaFin.setText(format.format(dto.getFechaFino()));
	        
	    	grid.setWidget(0, 0, new Label("Fecha"));
	    	grid.setWidget(0, 1, fecha);
	    	
	    	grid.setWidget(1, 0, new Label("Tipo"));
	    	grid.setWidget(1, 1, tipo);
	    	
	    	grid.setWidget(2, 0, new Label("Observaciones"));
	    	grid.setWidget(2, 1, observacion);
	    	observacion.setText(dto.getObservaciones());
	    	observacion.setEnabled(false);
	    	
	    	grid.setWidget(3, 0, new Label("Valor Vivienda Social"));
	    	grid.setWidget(3, 1, valVivSocial);
	    	valVivSocial.setText(String.valueOf(dto.getValorVivindaSocial()));
	    	valVivSocial.setEnabled(false);
	    
	    	grid.setWidget(4, 0, new Label("Valor Hectaria de cultivos"));
	    	grid.setWidget(4, 1, valHectaria);
	    	valHectaria.setText(String.valueOf(dto.getHectariaDeCultivo()));
	    	valHectaria.setEnabled(false);
	    
	    	grid.setWidget(5, 0, new Label("Fecha Inicio"));
	    	grid.setWidget(5, 1, fechaInicio);

	    	grid.setWidget(6, 0, new Label("Fecha Fin"));
	    	grid.setWidget(6, 1, fechaFin);

	    	for(int i=0; i<7;i++){
	    		grid.getCellFormatter().setStyleName(i,0, "tbl-cab");
	    	}
	    	tipo.addItem(TipoIndice.getTXT(TipoIndice.IDL),String.valueOf(TipoIndice.IDL));
	    	tipo.setEnabled(false);
	    	
	    	grid.setBorderWidth(1);
			
			Grid deptos=new Grid(dto.getDepartamentos().size()+1, 3);
			for(int i=0;i<3;i++){
				deptos.getCellFormatter().setStyleName(0,i, "tbl-cab");
			}
			deptos.setBorderWidth(1);
			deptos.setWidget(0, 0, new Label("Departamento"));
			deptos.setWidget(0, 1, new Label("%"));
			deptos.setWidget(0, 2, new Label("Analizar"));
			int row=1;
			float tot=0;
			for(IdlDepartamentoDTO d:dto.getDepartamentos())
			{
				deptos.setWidget(row, 0, new Label(d.getDepartamento().getNombre()));
				deptos.setWidget(row, 1, new Label(String.valueOf(d.getPorcentaje())));
				//deptos.setWidget(row, 0, new Label(String.valueOf(d.getPorcentaje())));
				tot+=d.getPorcentaje();
				final IdlDepartamentoDTO df=d;
				Button analizar=new Button("Analizar");
				analizar.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						FormIDLAnalizar2 form=new FormIDLAnalizar2(df,FormIDLAnalizar1.this);
						form.show();
					}
				});
				
				deptos.setWidget(row, 2,analizar);
				
				row++;
			}
		//	Label totalLb=new Label(String.valueOf(tot));
		
			cerrar.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					FormIDLAnalizar1.this.hide();
				}
			});
			
			Frame frame = new Frame("./idlgraficos.idldepartamentos?id="+dto.getId());
			frame.setSize("600px", "380px");
			Grid aux=new Grid(2,1);
			aux.setWidget(0, 0, grid);
			aux.setWidget(1, 0, frame);
			gridAux.setWidget(0, 0, aux);
			gridAux.setWidget(0, 1, deptos);
			vertical.add(gridAux);
			//vertical.add(totalLb);
			vertical.add(cerrar);
			captionPrincipal.add(vertical);
			add(captionPrincipal);
			center();
		}
	}
	
	public class FormIDLAnalizar2 extends DialogBox
	{
		private IdlDepartamentoDTO idto;
		private CaptionPanel captionPrincipal=new CaptionPanel("IDL-Pesos por tipo de evento");
		private VerticalPanel vertical=new VerticalPanel();
		private Button cerrar=new Button("Cerrar");
		private Grid gridAux=new Grid(1,2);
		private DialogBox llamador;
		
		public FormIDLAnalizar2(IdlDepartamentoDTO dto,DialogBox dialog )
		{
			dialog.setStyleName("gwt-DialogBoxOpaco");
			llamador=dialog;
			idto=dto;
			int size=(dto.getTiposEventos().size()/2)+2;
			Grid tipoevent=new Grid(size, 4);
			
			for(int i=0;i<4;i++){
				tipoevent.getCellFormatter().setStyleName(0,i, "tbl-cab");
			}
			tipoevent.setBorderWidth(1);
			tipoevent.setWidget(0, 0, new Label("Tipo Evento"));
			tipoevent.setWidget(0, 1, new Label("%"));
			tipoevent.setWidget(0, 2, new Label("Tipo Evento"));
			tipoevent.setWidget(0, 3, new Label("%"));
			int row=1;
			float tot=0;
			for(IdlTipoEventoDTO d:dto.getTiposEventos())
			{
				if(row %2 ==0){
					tipoevent.setWidget(row/2, 2, new Label(d.getTipoEvento().getNombre()));
					tipoevent.setWidget(row/2, 3, new Label(String.valueOf(d.getPocentaje())));
					tot+=d.getPocentaje();
				}else{
					tipoevent.setWidget((row+1)/2, 0, new Label(d.getTipoEvento().getNombre()));
					tipoevent.setWidget((row+1)/2, 1, new Label(String.valueOf(d.getPocentaje())));
					tot+=d.getPocentaje();
				}
				row++;
			}
			
		
			cerrar.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					FormIDLAnalizar2.this.hide();
					llamador.setStyleName("gwt-DialogBox");
				}
			});
			
			//Frame frame = new Frame("/idlgraficos.gidl.idldepartamentos?id="+dto.getId());
			//frame.setSize("500px", "300px");
			
			//vertical.add(frame);
			
			
			
			vertical.add(tipoevent);
			
			vertical.add(cerrar);
			captionPrincipal.add(vertical);
			add(captionPrincipal);
			center();
		}
	}
	public class FormDialogIDLBox extends DialogBox{
		private String a;
		private Long id;
		final HorizontalPanel horizontal= new HorizontalPanel();
		final VerticalPanel vertical= new VerticalPanel();
		final CaptionPanel captionPrincipal=new CaptionPanel("Nuevo calculo de indice");
		final Label label = new Label();
	    final Grid grid= new Grid(7,3);
	    final Label fecha = new Label();
	    final TextBox observacion=new TextBox();
	    final TextBox valVivSocial=new TextBox();
	    final TextBox valHectaria=new TextBox();
	    final ListBox tipo=new ListBox();
	    final DatePicker datePickerInicio = new DatePicker();
	    final Label fechaInicio= new Label();
	    final DatePicker datePickerFin = new DatePicker();
	    final Label fechaFin= new Label();
	    
	    final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Calcular");
		
	
	    public FormDialogIDLBox(Long idDesastre, String accion) {
			a=accion;
	    	id=idDesastre;
	    	
	 	    
	 	    DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
	        String dateString = format.format(new Date());
	        fecha.setText(dateString);
	        
	        datePickerInicio.addValueChangeHandler(new ValueChangeHandler<Date>() {
			      public void onValueChange(ValueChangeEvent<Date> event) {
			        Date date = (Date)event.getValue();
			        DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
			        String dateString = format.format(date);
			        fechaInicio.setText(dateString);
			      }
			    });
			datePickerInicio.setValue(new Date(), true);
		    	
	        datePickerFin.addValueChangeHandler(new ValueChangeHandler<Date>() {
			      public void onValueChange(ValueChangeEvent<Date> event) {
			        Date date = (Date)event.getValue();
			        DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
			        String dateString = format.format(date);
			        fechaFin.setText(dateString);
			      }
			    });
			datePickerFin.setValue(new Date(), true);
		    
	    	grid.setWidget(0, 0, new Label("Fecha"));
	    	grid.setWidget(0, 1, fecha);
	    	
	    	grid.setWidget(1, 0, new Label("Tipo"));
	    	grid.setWidget(1, 1, tipo);
	    	
	    	grid.setWidget(2, 0, new Label("Observaciones"));
	    	grid.setWidget(2, 1, observacion);

	    	grid.setWidget(3, 0, new Label("Valor Vivienda Social"));
	    	grid.setWidget(3, 1, valVivSocial);

	    	grid.setWidget(4, 0, new Label("Valor Hectaria de cultivos"));
	    	grid.setWidget(4, 1, valHectaria);

	    	grid.setWidget(5, 0, new Label("Fecha Inicio"));
	    	grid.setWidget(5, 1, fechaInicio);
	    	grid.setWidget(5, 2, datePickerInicio);

	    	grid.setWidget(6, 0, new Label("Fecha Fin"));
	    	grid.setWidget(6, 1, fechaFin);
	    	grid.setWidget(6, 2, datePickerFin);

	    	tipo.addItem(TipoIndice.getTXT(TipoIndice.IDL),String.valueOf(TipoIndice.IDL));
	    	tipo.setEnabled(false);
	    	
	    	grid.setBorderWidth(1);
	    	
	    	valVivSocial.addKeyboardListener(new KeyNumeric());
	    	valHectaria.addKeyboardListener(new KeyNumeric());
	    	
			captionPrincipal.add(vertical);
			vertical.add(grid);
	    	horizontal.add(aceptar);
			horizontal.add(cancelar);
			vertical.add(horizontal);
			
			cancelar.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					FormDialogIDLBox.this.hide();
				}
			});
			
			aceptar.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					FormDialogIDLBox.this.procesar();
				}
			});
				
			
			
			setAnimationEnabled(true);
			add(captionPrincipal);
			center();
		}

		protected void procesar() {
			IdlDTO dto=validar();
			if(dto!=null){
				dto.setId(id);
				dto.setUsuario(usuarioGlobal);
				IIndicesAsync serverIndice=GWT.create(IIndices.class);
				serverIndice.calcularIDL(dto,new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						hide();
						cargarLista();
					}
					
					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						Window.alert("ERROR AJAX");
					}
				});
			}
		}

		private IdlDTO validar() {
			IdlDTO dto= new IdlDTO();
			
			if(! (getFloat(valVivSocial.getText())>0)){
				Window.alert("Indique el valor de vivienda social");
				return null;
			}
			
			if(! (getFloat(valHectaria.getText())>0)){
				Window.alert("Indique el valor de una hectaria de cultivo");
				return null;
			}
			
			if(datePickerFin.getValue().before(datePickerInicio.getValue()))
			{
				Window.alert("La fecha de fin del periodo tiene que ser mayor a la de inicio.");
				return null;				
			}
			
			dto.setFechaInicio(datePickerInicio.getValue());
			dto.setFechaFino(datePickerFin.getValue());
			dto.setFecha(new Date());
			dto.setObservaciones(observacion.getText());
			dto.setTipo(Integer.valueOf(tipo.getValue(tipo.getSelectedIndex())));
			dto.setHectariaDeCultivo(getFloat(valHectaria.getText()));
			dto.setValorVivindaSocial(getFloat(valVivSocial.getText()));
			
			return dto;
			
		}
		
		private Float getFloat(String text) {
			if(text==null || text.trim().length()==0){
				return 0F;
			}
			return Float.valueOf(text);
		}
	}
	
public class FormDialogIGRAnalizarBox extends DialogBox{
		
		final HorizontalPanel horizontal= new HorizontalPanel();
		final VerticalPanel vertical= new VerticalPanel();
		final CaptionPanel captionPrincipal=new CaptionPanel("Nuevo calculo de IGR");
		final Grid grid= new Grid(3,2);
	    final Label fecha = new Label();
	    final TextBox observacion =new TextBox();
	    final ListBox tipo=new ListBox();
	    final Button cancelar= new Button("Cancelar");
		final CaptionPanel captionIR=new CaptionPanel("Indicadores de identificaci\u00F3n del Riesgo");
		final CaptionPanel captionRR=new CaptionPanel("Indicadores de reducci\u00F3n del riesgo");
		final CaptionPanel captionMD=new CaptionPanel("Indicadores de manejo de desastres");
		final CaptionPanel captionPF=new CaptionPanel("Indicadores de gobernabilidad y protecci\u00F3n financiera");
		final VerticalPanel verticalIR= new VerticalPanel();
		final VerticalPanel verticalRR= new VerticalPanel();
		final VerticalPanel verticalMD= new VerticalPanel();
		final VerticalPanel verticalPF= new VerticalPanel();
		
		
		final TextArea ir1 = new TextArea();
		final TextArea ir2 = new TextArea();
		final TextArea ir3 = new TextArea();
		final TextArea ir4 = new TextArea();
		final TextArea ir5 = new TextArea();
		final TextArea ir6 = new TextArea();
		final TextArea md1 = new TextArea();
		final TextArea md2 = new TextArea();
		final TextArea md3 = new TextArea();
		final TextArea md4 = new TextArea();
		final TextArea md5 = new TextArea();
		final TextArea md6 = new TextArea();
		final TextArea pf1 = new TextArea();
		final TextArea pf2 = new TextArea();
		final TextArea pf3 = new TextArea();
		final TextArea pf4 = new TextArea();
		final TextArea pf5 = new TextArea();
		final TextArea pf6 = new TextArea();
		final TextArea rr1 = new TextArea();
		final TextArea rr2 = new TextArea();
		final TextArea rr3 = new TextArea();
		final TextArea rr4 = new TextArea();
		final TextArea rr5 = new TextArea();
		final TextArea rr6 = new TextArea();
		
	
	    public FormDialogIGRAnalizarBox(IgrDTO dto) {
			
	    	
	 	    
	 	    DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
	        String dateString = format.format(dto.getFecha());
	        fecha.setText(dateString);
	        
		    
	    	grid.setWidget(0, 0, new Label("Fecha"));
	    	grid.setWidget(0, 1, fecha);
	    	
	    	grid.setWidget(1, 0, new Label("Tipo"));
	    	grid.setWidget(1, 1, tipo);
	    	
	    	grid.setWidget(2, 0, new Label("Observaciones"));
	    	grid.setWidget(2, 1, observacion);
	    	observacion.setText(dto.getObservaciones());
	    	observacion.setEnabled(false);
	    	
	    	tipo.addItem(TipoIndice.getTXT(TipoIndice.IGR),String.valueOf(TipoIndice.IGR));
	    	tipo.setEnabled(false);
	    	
	    	
	    	 ir1.setText( IR1.getTXT(dto.getIr1())); 
			 ir2.setText( IR2.getTXT(dto.getIr2())); 
			 ir3.setText( IR3.getTXT(dto.getIr3())); 
			 ir4.setText( IR4.getTXT(dto.getIr4())); 
			 ir5.setText( IR5.getTXT(dto.getIr5())); 
			 ir6.setText( IR6.getTXT(dto.getIr6())); 
			 md1.setText( MD1.getTXT(dto.getMd1())); 
			 md2.setText( MD2.getTXT(dto.getMd2())); 
			 md3.setText( MD3.getTXT(dto.getMd3())); 
			 md4.setText( MD4.getTXT(dto.getMd4())); 
			 md5.setText( MD5.getTXT(dto.getMd5())); 
			 md6.setText( MD6.getTXT(dto.getMd6())); 
			 pf1.setText( PF1.getTXT(dto.getPf1())); 
			 pf2.setText( PF2.getTXT(dto.getPf2())); 
			 pf3.setText( PF3.getTXT(dto.getPf3())); 
			 pf4.setText( PF4.getTXT(dto.getPf4())); 
			 pf5.setText( PF5.getTXT(dto.getPf5())); 
			 pf6.setText( PF6.getTXT(dto.getPf6())); 
			 rr1.setText( RR1.getTXT(dto.getRr1())); 
			 rr2.setText( RR2.getTXT(dto.getRr2())); 
			 rr3.setText( RR3.getTXT(dto.getRr3())); 
			 rr4.setText( RR4.getTXT(dto.getRr4())); 
			 rr5.setText( RR5.getTXT(dto.getRr5())); 
			 rr6.setText( RR6.getTXT(dto.getRr6())); 
			 	
			 ir1.setEnabled(false);
			 ir2.setEnabled(false); 
			 ir3.setEnabled(false); 
			 ir4.setEnabled(false);
			 ir5.setEnabled(false); 
			 ir6.setEnabled(false);
			 md1.setEnabled(false);
			 md2.setEnabled(false);
			 md3.setEnabled(false); 
			 md4.setEnabled(false); 
			 md5.setEnabled(false); 
			 md6.setEnabled(false); 
			 pf1.setEnabled(false); 
			 pf2.setEnabled(false); 
			 pf3.setEnabled(false); 
			 pf4.setEnabled(false); 
			 pf5.setEnabled(false); 
			 pf6.setEnabled(false); 
			 rr1.setEnabled(false); 
			 rr2.setEnabled(false); 
			 rr3.setEnabled(false); 
			 rr4.setEnabled(false); 
			 rr5.setEnabled(false); 
			 rr6.setEnabled(false); 
			 
			 ir1.setVisibleLines(3);
			 ir1.setWidth("400px");
			 ir2.setVisibleLines(3);
			 ir2.setWidth("400px");
			 ir3.setVisibleLines(3);
			 ir3.setWidth("400px");
			 ir4.setVisibleLines(3);
			 ir4.setWidth("400px");
			 ir5.setVisibleLines(3);
			 ir5.setWidth("400px");
			 ir6.setVisibleLines(3);
			 ir6.setWidth("400px");
			 md1.setVisibleLines(3);
			 md1.setWidth("400px");
			 md2.setVisibleLines(3);
			 md2.setWidth("400px");
			 md3.setVisibleLines(3);
			 md3.setWidth("400px");
			 md4.setVisibleLines(3);
			 md4.setWidth("400px");
			 md5.setVisibleLines(3);
			 md5.setWidth("400px");
			 md6.setVisibleLines(3);
			 md6.setWidth("400px");
			 pf1.setVisibleLines(3);
			 pf1.setWidth("400px");
			 pf2.setVisibleLines(3);
			 pf2.setWidth("400px");
			 pf3.setVisibleLines(3);
			 pf3.setWidth("400px");
			 pf4.setVisibleLines(3);
			 pf4.setWidth("400px");
			 pf5.setVisibleLines(3);
			 pf5.setWidth("400px");
			 pf6.setVisibleLines(3);
			 pf6.setWidth("400px");
			 rr1.setVisibleLines(3);
			 rr1.setWidth("400px");
			 rr2.setVisibleLines(3);
			 rr2.setWidth("400px");
			 rr3.setVisibleLines(3);
			 rr3.setWidth("400px");
			 rr4.setVisibleLines(3);
			 rr4.setWidth("400px");
			 rr5.setVisibleLines(3);
			 rr5.setWidth("400px");
			 rr6.setVisibleLines(3);
			 rr6.setWidth("400px");
			 
			 
	    	grid.setBorderWidth(1);
	    	
			captionPrincipal.add(vertical);
			vertical.add(grid);
			vertical.add(captionIR);
			HorizontalPanel h1=new HorizontalPanel();
			h1.add(ir1);
			h1.add(ir2);
			verticalIR.add(h1);
			HorizontalPanel h2=new HorizontalPanel();
			h2.add(ir3);
			h2.add(ir4);
			verticalIR.add(h2);
			HorizontalPanel h3=new HorizontalPanel();
			h3.add(ir5);
			h3.add(ir6);
			verticalIR.add(h3);
			captionIR.add(verticalIR);
		
			vertical.add(captionRR);
			HorizontalPanel h4=new HorizontalPanel();
			h4.add(rr1);
			h4.add(rr2);
			verticalRR.add(h4);
			HorizontalPanel h5=new HorizontalPanel();
			h5.add(rr3);
			h5.add(rr4);
			verticalRR.add(h5);
			HorizontalPanel h6=new HorizontalPanel();
			h6.add(rr5);
			h6.add(rr6);
			verticalRR.add(h6);
			captionRR.add(verticalRR);
			vertical.add(captionMD);
			HorizontalPanel h7=new HorizontalPanel();
			h7.add(md1);
			h7.add(md2);
			verticalMD.add(h7);
			HorizontalPanel h8=new HorizontalPanel();
			h8.add(md3);
			h8.add(md4);
			verticalMD.add(h8);
			HorizontalPanel h9=new HorizontalPanel();
			h9.add(md5);
			h9.add(md6);
			verticalMD.add(h9);
			captionMD.add(verticalMD);
			vertical.add(captionPF);
			HorizontalPanel h10=new HorizontalPanel();
			h10.add(pf1);
			h10.add(pf2);
			verticalPF.add(h10);
			HorizontalPanel h11=new HorizontalPanel();
			h11.add(pf3);
			h11.add(pf4);
			verticalPF.add(h11);
			HorizontalPanel h12=new HorizontalPanel();
			h12.add(pf5);
			h12.add(pf6);
			verticalPF.add(h12);
			captionPF.add(verticalPF);
			
	    	horizontal.add(cancelar);
			vertical.add(horizontal);
			
			cancelar.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					FormDialogIGRAnalizarBox.this.hide();
				}
			});
			
			
			setAnimationEnabled(true);
			add(captionPrincipal);
			center();
		}

		
	}
	
	public class FormDialogIGRBox extends DialogBox{
		
		final HorizontalPanel horizontal= new HorizontalPanel();
		final VerticalPanel vertical= new VerticalPanel();
		final CaptionPanel captionPrincipal=new CaptionPanel("Nuevo calculo de IGR");
		final Grid grid= new Grid(3,2);
	    final Label fecha = new Label();
	    final TextBox observacion =new TextBox();
	    final ListBox tipo=new ListBox();
	    final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Calcular");
		final CaptionPanel captionIR=new CaptionPanel("Indicadores de identificaci\u00F3n del Riesgo");
		final CaptionPanel captionRR=new CaptionPanel("Indicadores de reducci\u00F3n del riesgo");
		final CaptionPanel captionMD=new CaptionPanel("Indicadores de manejo de desastres");
		final CaptionPanel captionPF=new CaptionPanel("Indicadores de gobernabilidad y protecci\u00F3n financiera");
		final VerticalPanel verticalIR= new VerticalPanel();
		final VerticalPanel verticalRR= new VerticalPanel();
		final VerticalPanel verticalMD= new VerticalPanel();
		final VerticalPanel verticalPF= new VerticalPanel();
		
		
		final ListBox ir1 = new ListBox();
		final ListBox ir2 = new ListBox();
		final ListBox ir3 = new ListBox();
		final ListBox ir4 = new ListBox();
		final ListBox ir5 = new ListBox();
		final ListBox ir6 = new ListBox();
		final ListBox md1 = new ListBox();
		final ListBox md2 = new ListBox();
		final ListBox md3 = new ListBox();
		final ListBox md4 = new ListBox();
		final ListBox md5 = new ListBox();
		final ListBox md6 = new ListBox();
		final ListBox pf1 = new ListBox();
		final ListBox pf2 = new ListBox();
		final ListBox pf3 = new ListBox();
		final ListBox pf4 = new ListBox();
		final ListBox pf5 = new ListBox();
		final ListBox pf6 = new ListBox();
		final ListBox rr1 = new ListBox();
		final ListBox rr2 = new ListBox();
		final ListBox rr3 = new ListBox();
		final ListBox rr4 = new ListBox();
		final ListBox rr5 = new ListBox();
		final ListBox rr6 = new ListBox();
		
	
	    public FormDialogIGRBox() {
			
	    	
	 	    
	 	    DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
	        String dateString = format.format(new Date());
	        fecha.setText(dateString);
	        
		    
	    	grid.setWidget(0, 0, new Label("Fecha"));
	    	grid.setWidget(0, 1, fecha);
	    	
	    	grid.setWidget(1, 0, new Label("Tipo"));
	    	grid.setWidget(1, 1, tipo);
	    	
	    	grid.setWidget(2, 0, new Label("Observaciones"));
	    	grid.setWidget(2, 1, observacion);

	    	
	    	tipo.addItem(TipoIndice.getTXT(TipoIndice.IGR),String.valueOf(TipoIndice.IGR));
	    	tipo.setEnabled(false);
	    	
	    	ir1.addItem("Seleccionar", "0");
			for (ItemConstante i : IR1.getItems()) {
				ir1.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
	    	
	    	ir2.addItem("Seleccionar", "0");
			for (ItemConstante i : IR2.getItems()) {
				ir2.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
	    	ir3.addItem("Seleccionar", "0");
			for (ItemConstante i : IR3.getItems()) {
				ir3.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
	    	ir4.addItem("Seleccionar", "0");
			for (ItemConstante i : IR4.getItems()) {
				ir4.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
	    	ir5.addItem("Seleccionar", "0");
			for (ItemConstante i : IR5.getItems()) {
				ir5.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
	    	ir6.addItem("Seleccionar", "0");
			for (ItemConstante i : IR6.getItems()) {
				ir6.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
	    	md1.addItem("Seleccionar", "0");
			for (ItemConstante i : MD1.getItems()) {
				md1.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			md2.addItem("Seleccionar", "0");
			for (ItemConstante i : MD2.getItems()) {
				md2.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			md3.addItem("Seleccionar", "0");
			for (ItemConstante i : MD3.getItems()) {
				md3.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			md4.addItem("Seleccionar", "0");
			for (ItemConstante i : MD4.getItems()) {
				md4.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			md5.addItem("Seleccionar", "0");
			for (ItemConstante i : MD5.getItems()) {
				md5.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			md6.addItem("Seleccionar", "0");
			for (ItemConstante i : MD6.getItems()) {
				md6.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			pf1.addItem("Seleccionar", "0");
			for (ItemConstante i : PF1.getItems()) {
				pf1.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			pf2.addItem("Seleccionar", "0");
			for (ItemConstante i : PF2.getItems()) {
				pf2.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			pf3.addItem("Seleccionar", "0");
			for (ItemConstante i : PF3.getItems()) {
				pf3.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			pf4.addItem("Seleccionar", "0");
			for (ItemConstante i : PF4.getItems()) {
				pf4.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			pf5.addItem("Seleccionar", "0");
			for (ItemConstante i : PF5.getItems()) {
				pf5.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			pf6.addItem("Seleccionar", "0");
			for (ItemConstante i : PF6.getItems()) {
				pf6.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			rr1.addItem("Seleccionar", "0");
			for (ItemConstante i : RR1.getItems()) {
				rr1.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			rr2.addItem("Seleccionar", "0");
			for (ItemConstante i : RR2.getItems()) {
				rr2.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			rr3.addItem("Seleccionar", "0");
			for (ItemConstante i : RR3.getItems()) {
				rr3.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			rr4.addItem("Seleccionar", "0");
			for (ItemConstante i : RR4.getItems()) {
				rr4.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			rr5.addItem("Seleccionar", "0");
			for (ItemConstante i : RR5.getItems()) {
				rr5.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			rr6.addItem("Seleccionar", "0");
			for (ItemConstante i : RR6.getItems()) {
				rr6.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
	    	grid.setBorderWidth(1);
	    	
			captionPrincipal.add(vertical);
			vertical.add(grid);
			vertical.add(captionIR);
			verticalIR.add(ir1);
			verticalIR.add(ir2);
			verticalIR.add(ir3);
			verticalIR.add(ir4);
			verticalIR.add(ir5);
			verticalIR.add(ir6);
			captionIR.add(verticalIR);
			vertical.add(captionRR);
			verticalRR.add(rr1);
			verticalRR.add(rr2);
			verticalRR.add(rr3);
			verticalRR.add(rr4);
			verticalRR.add(rr5);
			verticalRR.add(rr6);
			captionRR.add(verticalRR);
			vertical.add(captionMD);
			verticalMD.add(md1);
			verticalMD.add(md2);
			verticalMD.add(md3);
			verticalMD.add(md4);
			verticalMD.add(md5);
			verticalMD.add(md6);
			captionMD.add(verticalMD);
			vertical.add(captionPF);
			verticalPF.add(pf1);
			verticalPF.add(pf2);
			verticalPF.add(pf3);
			verticalPF.add(pf4);
			verticalPF.add(pf5);
			verticalPF.add(pf6);
			captionPF.add(verticalPF);
			
	    	horizontal.add(aceptar);
			horizontal.add(cancelar);
			vertical.add(horizontal);
			
			cancelar.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					FormDialogIGRBox.this.hide();
				}
			});
			
			aceptar.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					FormDialogIGRBox.this.procesar();
				}
			});
				
			
			
			setAnimationEnabled(true);
			add(captionPrincipal);
			center();
		}

		protected void procesar() {
			IgrDTO dto=validar();
			if(dto!=null){
				dto.setUsuario(usuarioGlobal);
				IIndicesAsync serverIndice=GWT.create(IIndices.class);
				serverIndice.calcularIGR(dto,new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						hide();
						cargarLista();
					}
					
					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						Window.alert("ERROR AJAX");
					}
				});
			}
		}

		private IgrDTO validar() {
			IgrDTO dto= new IgrDTO();
			
			
			if(ir1.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 1 de identificaci\u00F3n del Riesgo"); 
				  return null;
			}
			dto.setIr1(Integer.valueOf(ir1.getValue(ir1.getSelectedIndex())));
			
			if(ir2.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 2 de identificaci\u00F3n del Riesgo"); 
				  return null;
			}
			dto.setIr2(Integer.valueOf(ir2.getValue(ir2.getSelectedIndex())));
			
			if(ir3.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 3 de identificaci\u00F3n del Riesgo"); 
				  return null;
			}
			dto.setIr3(Integer.valueOf(ir3.getValue(ir3.getSelectedIndex())));
			
			if(ir4.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 4 de identificaci\u00F3n del Riesgo"); 
				  return null;
			}
			dto.setIr4(Integer.valueOf(ir4.getValue(ir4.getSelectedIndex())));
			
			if(ir5.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 5 de identificaci\u00F3n del Riesgo"); 
				  return null;
			}
			dto.setIr5(Integer.valueOf(ir5.getValue(ir5.getSelectedIndex())));
			
			if(ir6.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 6 de identificaci\u00F3n del Riesgo"); 
				  return null;
			}
			dto.setIr6(Integer.valueOf(ir6.getValue(ir6.getSelectedIndex())));
			
			if(rr1.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 1 de reducci\u00F3n del riesgo"); 
				  return null;
			}
			dto.setRr1(Integer.valueOf(rr1.getValue(rr1.getSelectedIndex())));
			
			if(rr2.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 2 de reducci\u00F3n del riesgo"); 
				  return null;
			}
			dto.setRr2(Integer.valueOf(rr2.getValue(rr2.getSelectedIndex())));
			
			if(rr3.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 3 de reducci\u00F3n del riesgo"); 
				  return null;
			}
			dto.setRr3(Integer.valueOf(rr3.getValue(rr3.getSelectedIndex())));
			
			if(rr4.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 4 de reducci\u00F3n del riesgo"); 
				  return null;
			}
			dto.setRr4(Integer.valueOf(rr4.getValue(rr4.getSelectedIndex())));
			
			if(rr5.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 5 de reducci\u00F3n del riesgo"); 
				  return null;
			}
			dto.setRr5(Integer.valueOf(rr5.getValue(rr5.getSelectedIndex())));
			
			if(rr6.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 6 de reducci\u00F3n del riesgo"); 
				  return null;
			}
			dto.setRr6(Integer.valueOf(rr6.getValue(rr6.getSelectedIndex())));
			
			if(md1.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 1 de manejo de desastres"); 
				  return null;
			}
			dto.setMd1(Integer.valueOf(md1.getValue(md1.getSelectedIndex())));
			
			if(md2.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 2 de manejo de desastres"); 
				  return null;
			}
			dto.setMd2(Integer.valueOf(md2.getValue(md2.getSelectedIndex())));
			
			if(md3.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 3 de manejo de desastres"); 
				  return null;
			}
			dto.setMd3(Integer.valueOf(md3.getValue(md3.getSelectedIndex())));
			
			if(md4.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 4 de manejo de desastres"); 
				  return null;
			}
			dto.setMd4(Integer.valueOf(md4.getValue(md4.getSelectedIndex())));
			
			if(md5.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 5 de manejo de desastres"); 
				  return null;
			}
			dto.setMd5(Integer.valueOf(md5.getValue(md5.getSelectedIndex())));
			
			if(md6.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 6 de manejo de desastres"); 
				  return null;
			}
			dto.setMd6(Integer.valueOf(md6.getValue(md6.getSelectedIndex())));
			
			if(pf1.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 1 de gobernabilidad y protecci\u00F3n financiera"); 
				  return null;
			}
			dto.setPf1(Integer.valueOf(pf1.getValue(pf1.getSelectedIndex())));
			
			if(pf2.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 2 de gobernabilidad y protecci\u00F3n financiera"); 
				  return null;
			}
			dto.setPf2(Integer.valueOf(pf2.getValue(pf2.getSelectedIndex())));
			
			if(pf3.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 3 de gobernabilidad y protecci\u00F3n financiera"); 
				  return null;
			}
			dto.setPf3(Integer.valueOf(pf3.getValue(pf3.getSelectedIndex())));
			
			if(pf4.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 4 de gobernabilidad y protecci\u00F3n financiera"); 
				  return null;
			}
			dto.setPf4(Integer.valueOf(pf4.getValue(pf4.getSelectedIndex())));
			
			if(pf5.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 5 de gobernabilidad y protecci\u00F3n financiera"); 
				  return null;
			}
			dto.setPf5(Integer.valueOf(pf5.getValue(pf5.getSelectedIndex())));
			
			if(pf6.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 6 de gobernabilidad y protecci\u00F3n financiera"); 
				  return null;
			}
			dto.setPf6(Integer.valueOf(pf6.getValue(pf6.getSelectedIndex())));
			 
			
			dto.setFecha(new Date());
			dto.setObservaciones(observacion.getText());
			dto.setTipo(Integer.valueOf(tipo.getValue(tipo.getSelectedIndex())));
			
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
