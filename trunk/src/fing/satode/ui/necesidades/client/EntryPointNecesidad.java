package fing.satode.ui.necesidades.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.logging.client.DefaultLevel.Severe;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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

import fing.satode.constantes.EstadoNecesidad;
import fing.satode.constantes.EstadoSuministro;
import fing.satode.constantes.ItemConstante;
import fing.satode.data.DepositoDTO;
import fing.satode.data.DesastreDTO;
import fing.satode.data.DonacionDTO;
import fing.satode.data.NecesidadDTO;
import fing.satode.data.PuntoReferenciaDTO;
import fing.satode.data.SolicitudSuministroDTO;
import fing.satode.data.SuministroDTO;
import fing.satode.data.TipoSuministroDTO;
import fing.satode.data.UsuarioDTO;
import fing.satode.ui.deposito.client.IDeposito;
import fing.satode.ui.deposito.client.IDepositoAsync;
import fing.satode.ui.deposito.client.EntryPointSuministro.FormDialogBox;
import fing.satode.ui.deposito.client.EntryPointSuministro.FormDialogSuministroBox;
import fing.satode.ui.desastres.client.IDesastre;
import fing.satode.ui.desastres.client.IDesastreAsync;
import fing.satode.ui.general.data.KeyNumeric;
import fing.satode.ui.puntoReferencias.client.IPuntoReferencia;
import fing.satode.ui.puntoReferencias.client.IPuntoReferenciaAsync;
import fing.satode.ui.usuarios.client.IUsuario;
import fing.satode.ui.usuarios.client.IUsuarioAsync;

public class EntryPointNecesidad implements EntryPoint {

	final Button nuevoB = new Button("Nuevo");
	final Button buscarB = new Button("Buscar");
	final VerticalPanel vertical = new VerticalPanel();
	private ArrayList<NecesidadDTO> necesidadesGlobal;
	private ArrayList<DesastreDTO> desastreGlobal;
	private ArrayList<TipoSuministroDTO> tipoSuministrosGlobal;
	private ArrayList<PuntoReferenciaDTO> puntosReferenciaGlobal;
	final Label modificarLabel= new Label("Modificar");
	final Label eliminarLabel= new Label("Eliminar");
	final Label ver= new Label("Ver");
	public static Grid gridFormulario = new Grid(6,2);
	public static  Grid suministros;
	private static ListBox desastres;
	private static ListBox estados;
	public static NecesidadDTO necesidadDTO;
	private static Long numerador=10000000000L;
	private static Long baseNumerador=10000000000L;
	private UsuarioDTO usuarioGlobal;
	private static ListBox recursosLocales=new ListBox();
	
	@Override
	public void onModuleLoad() {
		HorizontalPanel botonera= new HorizontalPanel();
		Grid desastreGrid=new Grid(1,2);
		Grid estadoGrid=new Grid(1,2);
		botonera.add(desastreGrid);
		botonera.add(estadoGrid);
		botonera.add(buscarB);
		botonera.add(nuevoB);
		RootPanel.get("botones").add(botonera);
		RootPanel.get("necesidades").add(vertical);
		
		desastres=new ListBox();
		desastres.addItem("Todos","0");
		desastreGrid.setWidget(0, 1, desastres);
		desastreGrid.setWidget(0, 0,new Label("Desastre"));
		
		estados=new ListBox();
		estados.addItem("Todos","0");
		estadoGrid.setWidget(0, 1, estados);
		estadoGrid.setWidget(0,0,new Label("Estado"));
		for(ItemConstante i:EstadoNecesidad.getItems()){
			estados.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
		}
		
		IDesastreAsync serverDesastre=GWT.create(IDesastre.class);
		serverDesastre.listaDesastres(new AsyncCallback<ArrayList<DesastreDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<DesastreDTO> result) {
				desastreGlobal=result;
				DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
				for(DesastreDTO d: result){
					desastres.addItem(d.getId().toString()+"-"+d.getEvento().getCiudad().getNombre()+"-"+format.format(d.getFechaDeclaracion()),d.getId().toString());
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
		
		IPuntoReferenciaAsync serverPuntoRef=GWT.create(IPuntoReferencia.class);
		
		serverPuntoRef.listPuntoEntrega(new AsyncCallback<ArrayList<PuntoReferenciaDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<PuntoReferenciaDTO> result) {
				puntosReferenciaGlobal=result;
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
		
		IDepositoAsync serverDeposito=GWT.create(IDeposito.class);
		
		serverDeposito.listaTipoSuministros(new AsyncCallback<ArrayList<TipoSuministroDTO>>() {
			
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
		
		buscarB.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
					cargarLista();
			}
					
		});
		
		nuevoB.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				FormDialogBox dialog= new FormDialogBox(0L, "nuevo");
				dialog.show();
			}
		});
		
		IUsuarioAsync servidorUsuario= GWT.create(IUsuario.class);
		
		servidorUsuario.getUsuarioLogin(new AsyncCallback<UsuarioDTO>() {
			
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
	
	public void cargarLista(){
		vertical.clear();
	
		Long idDesastre=Long.valueOf(desastres.getValue(desastres.getSelectedIndex()));
		Long idEstado=Long.valueOf(estados.getValue(estados.getSelectedIndex()));
		
		INecesidadAsync serverNecesidad=GWT.create(INecesidad.class);
		
		serverNecesidad.buscarNecesidades(idDesastre, idEstado, new AsyncCallback<ArrayList<NecesidadDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<NecesidadDTO> result) {
				necesidadesGlobal=result;
				
				Grid gridPrincipal= new Grid(result.size()+1,10);
				for(int i=0;i<10;i++){
					gridPrincipal.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				gridPrincipal.setBorderWidth(1);
				gridPrincipal.setWidget(0, 0, new Label("ID"));
				gridPrincipal.setWidget(0, 1, new Label("Fecha"));
				gridPrincipal.setWidget(0, 2, new Label("Desastre"));
				gridPrincipal.setWidget(0, 3, new Label("Punto Entrega"));
				gridPrincipal.setWidget(0, 4, new Label("Descripcion"));
				gridPrincipal.setWidget(0, 5, new Label("Estado"));
				gridPrincipal.setWidget(0, 6, new Label("Usuario"));
				gridPrincipal.setWidget(0, 7, ver);
				gridPrincipal.setWidget(0, 8, modificarLabel);
				gridPrincipal.setWidget(0, 9, eliminarLabel);
				
				vertical.add(gridPrincipal);
				
				int row=1;
				DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
				for(NecesidadDTO s: result){
					gridPrincipal.setWidget(row, 0, new Label(s.getId().toString()));
					gridPrincipal.setWidget(row, 1, new Label(format.format(s.getFecha())));
					gridPrincipal.setWidget(row, 2, new Label(s.getDesastre().getId().toString()+"-"+s.getDesastre().getEvento().getCiudad().getNombre()+"-"+format.format(s.getDesastre().getFechaDeclaracion())));
					gridPrincipal.setWidget(row, 3, new Label(s.getId().toString()+"-"+s.getPuntoEntrega().getCiudad().getNombre()+"-"+s.getPuntoEntrega().getDireccion()));
					gridPrincipal.setWidget(row, 4, new Label(s.getDescripcion()));
					gridPrincipal.setWidget(row, 5, new Label(EstadoNecesidad.getTXT(s.getEstado())));
					gridPrincipal.setWidget(row, 6, new Label(s.getUsuarioCreador().getNombreCompleto()));
					
					final Long id= s.getId();
					
					final Image verI= new Image("/images/LOCATE.bmp");
						verI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							FormDialogBox dialog= new FormDialogBox(id, "ver");
							dialog.show();
						}
					});
					
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
					gridPrincipal.setWidget(row, 7, verI);
					if(s.getEstado()==EstadoNecesidad.INGRESADA){
						gridPrincipal.setWidget(row, 8, modificarI);
						gridPrincipal.setWidget(row, 9, eliminarI);
					}
					
					row++;
					
				}
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
		final CaptionPanel panelPrincipal = new CaptionPanel();
	  
	    final Grid gridFecha= new Grid(1,2);
	    
	    final DatePicker datePicker = new DatePicker();
	    final TextBox descripcion =new TextBox();
	    final TextBox fecha= new TextBox();
	    final ListBox desastres=new ListBox();
	    
	    final ListBox puntoEntrega=new ListBox();
	   
	    final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
		final Button nuevoB = new Button("Nuevo");
		
		public FormDialogBox(Long idNecesidad,String accion){
			a=accion;
			id=idNecesidad;
			
			
			datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
		      public void onValueChange(ValueChangeEvent<Date> event) {
		        Date date = (Date)event.getValue();
		        DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
		        String dateString = format.format(date);
		        fecha.setText(dateString);
		      }
		    });
		    datePicker.setValue(new Date(), true);
	    	
	    	if(a=="modificar") panelPrincipal.setCaptionText("Modificar Necesidad");
			if(a=="eliminar") panelPrincipal.setCaptionText("Eliminar Necesidad");
			if(a=="nuevo") panelPrincipal.setCaptionText("Nuevo Necesidad");
			if(a=="ver") panelPrincipal.setCaptionText("Ver Necesidad");
			
			gridFormulario.setWidget(0, 0, new Label("Fecha"));
			gridFormulario.setWidget(1, 0, new Label("Desastre"));
			gridFormulario.setWidget(2, 0, new Label("Punto de Entrega"));
			gridFormulario.setWidget(3, 0, new Label("Descripcion"));
			gridFormulario.setWidget(4, 0, new Label("Recursos Locales"));
			gridFormulario.setBorderWidth(1);
			
			gridFecha.setWidget(0, 0, fecha);
			gridFecha.setWidget(0, 1, datePicker);
			gridFormulario.setWidget(0, 1, gridFecha);
			
			desastres.addItem("Seleccionar", "0");
			DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
			for(DesastreDTO d: desastreGlobal){
				desastres.addItem(d.getId().toString()+"-"+d.getEvento().getCiudad().getNombre()+"-"+format.format(d.getFechaDeclaracion()),d.getId().toString());
			}
			gridFormulario.setWidget(1, 1, desastres);
			
			puntoEntrega.addItem("Seleccionar", "0");
			for(PuntoReferenciaDTO d: puntosReferenciaGlobal){
				puntoEntrega.addItem(d.getId().toString()+"-"+d.getCiudad().getNombre()+"-"+d.getDireccion(),d.getId().toString());
			}
			gridFormulario.setWidget(2, 1, puntoEntrega);
			
			gridFormulario.setWidget(3, 1, descripcion);
			
			recursosLocales.clear();
			recursosLocales.setEnabled(true);
			recursosLocales.addItem("NO", "NO");
			recursosLocales.addItem("SI", "SI");
			gridFormulario.setWidget(4, 1, recursosLocales);
			
			necesidadDTO= new NecesidadDTO();
			
			suministros= new Grid(1, 4);
		    suministros.setWidget(0, 0, new Label("Tipo Suministro"));
		    suministros.setWidget(0, 1, new Label("Cantidad"));
		    suministros.setWidget(0, 2, new Label("Modificar"));
		    suministros.setWidget(0, 3, new Label("Eliminar"));
		    
		    for(int i=0;i<4;i++){
		    	suministros.getCellFormatter().setStyleName(0,i, "tbl-cab");
			}
			
		    
		    gridFormulario.setWidget(5, 0, suministros);
			    
			gridFormulario.setWidget(5, 1, nuevoB);
			 
			nuevoB.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					FormDialogSuministroBox dialog= new FormDialogSuministroBox(null, "nuevo",FormDialogBox.this);
					dialog.show();
				}
			});
			
			if (a == "modificar" || a == "eliminar" || a=="ver"){
				for(NecesidadDTO e:necesidadesGlobal){
	    			if(e.getId().equals(id)){
	    				necesidadDTO=e;
	    			}
	    		}
	    		
		    	descripcion.setText(necesidadDTO.getDescripcion());
			    datePicker.setValue(necesidadDTO.getFecha(),true);
			    
			    desastres.clear();
			    desastres.addItem("Seleccionar", "0");
			    int index=1;
				for(DesastreDTO d: desastreGlobal){
					desastres.addItem(d.getId().toString()+"-"+d.getEvento().getCiudad().getNombre()+"-"+format.format(d.getFechaDeclaracion()),d.getId().toString());
					if(d.getId().equals(necesidadDTO.getDesastre().getId())){
						desastres.setSelectedIndex(index);
					}
					index++;
				}
				
				puntoEntrega.clear();
				puntoEntrega.addItem("Seleccionar", "0");
			    index=1;
				for(PuntoReferenciaDTO d: puntosReferenciaGlobal){
					puntoEntrega.addItem(d.getId()+"-"+d.getCiudad().getNombre()+"-"+d.getDireccion(),String.valueOf(d.getId()));
					if(d.getId().equals(necesidadDTO.getPuntoEntrega().getId())){
						puntoEntrega.setSelectedIndex(index);
					}
					index++;
				}
				
				recursosLocales.setSelectedIndex(necesidadDTO.isRecursosLocales()?1:0);
				
			    suministros= new Grid(necesidadDTO.getSolicitudesSuministros().size()+1, 4);
			    suministros.setWidget(0, 0, new Label("Tipo Suministro"));
			    suministros.setWidget(0, 1, new Label("Cantidad"));
			    suministros.setWidget(0, 2, new Label("Modificar"));
			    suministros.setWidget(0, 3, new Label("Eliminar"));
			    
			    gridFormulario.setWidget(5, 0, suministros);
			    
				for(int i=0;i<4;i++){
					suministros.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				
				suministros.setBorderWidth(1);
				
				int row=1;
				for(SolicitudSuministroDTO s: necesidadDTO.getSolicitudesSuministros()){
					suministros.setWidget(row, 0, new Label(s.getTipoSuministro().getNombre()));
					suministros.setWidget(row, 1, new Label(String.valueOf(s.getCantidad())));
					final SolicitudSuministroDTO sum= s;
					final Image modificarI= new Image("/images/modificar.png");
					modificarI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							FormDialogSuministroBox dialog= new FormDialogSuministroBox(sum, "modificar",FormDialogBox.this);
							dialog.show();
						}
					});
					
					final Image eliminarI= new Image("/images/eliminar.png");
					
					eliminarI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							FormDialogSuministroBox dialog= new FormDialogSuministroBox(sum, "eliminar",FormDialogBox.this);
							dialog.show();
						}
					});
					if(a == "modificar" ){
						suministros.setWidget(row,2, modificarI);
						suministros.setWidget(row, 3, eliminarI);
					}
					row++;
				}
		    }
			
			  if ( a == "eliminar" || a=="ver"){
			    	descripcion.setEnabled(false);
			    	fecha.setEnabled(false);
			    	desastres.setEnabled(false);
			    	nuevoB.setEnabled(false);
			    	puntoEntrega.setEnabled(false);
			    	aceptar.setEnabled(false);
			    	recursosLocales.setEnabled(false);
			    	datePicker.setVisible(false);
			    }
			
			
			panelPrincipal.add(vertical);
			vertical.add(gridFormulario);
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
			add(panelPrincipal);
			center();
		}

		protected void procesar() {
			NecesidadDTO dto=validar();
			
			if(dto!=null){
				for(SolicitudSuministroDTO s: dto.getSolicitudesSuministros()){
					if(s.getId()>=baseNumerador){
						s.setId(null);
					}
					if(!dto.isRecursosLocales()){
						s.setCosto(0);
					}
				}
				dto.setId(id);
				dto.setUsuarioCreador(usuarioGlobal);
				
				if(a=="modificar"){
					
					INecesidadAsync servidorNecesidad=GWT.create(INecesidad.class);
					
					servidorNecesidad.modificarNecesidad(dto,new AsyncCallback<Void>() {
						
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
					INecesidadAsync servidorNecesidad=GWT.create(INecesidad.class);
					
					servidorNecesidad.nuevoNecesidad(dto, new AsyncCallback<Void>() {
						
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
					INecesidadAsync servidorNecesidad=GWT.create(INecesidad.class);
					
					servidorNecesidad.eliminarNecesidad(dto, new AsyncCallback<Void>() {
						
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
		
		private NecesidadDTO validar(){
			NecesidadDTO dto= necesidadDTO;
			
			if(descripcion.getText().trim().length()== 0){
				Window.alert("Indique Descripcion");
				return null;
			}
			
			if(desastres.getSelectedIndex()<1){
				Window.alert("Indique Desastre");
				return null;
			}
			
			if(puntoEntrega.getSelectedIndex()<1){
				Window.alert("Indique Punto Entrega");
				return null;
			}
			
			dto.setFecha(datePicker.getValue());
			dto.setDescripcion(descripcion.getText());
			dto.setRecursosLocales(recursosLocales.getSelectedIndex()==1);
			
			for(DesastreDTO d: desastreGlobal){
				if( d.getId().equals( Long.valueOf(desastres.getValue(desastres.getSelectedIndex()) ))){
					dto.setDesastre(d);
				}
			}
			
			for(PuntoReferenciaDTO d: puntosReferenciaGlobal){
				if( d.getId().equals( Long.valueOf(puntoEntrega.getValue(puntoEntrega.getSelectedIndex()) ))){
					dto.setPuntoEntrega(d);
				}
			}
			
			return dto;
		}
	}

	public class FormDialogSuministroBox extends DialogBox{
		private String a;
		private SolicitudSuministroDTO suministro;
		final HorizontalPanel horizontal= new HorizontalPanel();
		final VerticalPanel vertical= new VerticalPanel();
		final Label label = new Label();
	    final Grid grid= new Grid(3,2);
	    
	    final ListBox tipoSuministro= new ListBox();
	    final TextBox cantidad =new TextBox();
	    final TextBox costo =new TextBox();
	    final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
		private DialogBox llamador;
		
	
	    @SuppressWarnings("deprecation")
		public FormDialogSuministroBox(SolicitudSuministroDTO sum, String accion,DialogBox dialog) {
			a=accion;
	    	suministro=sum;
	    	llamador=dialog;
	    	llamador.setStyleName("gwt-DialogBoxOpaco");
	    	if(a=="modificar") label.setText("Modificar Suministro");
			if(a=="eliminar") label.setText("Eliminar Suministro");
			if(a=="nuevo") label.setText("Nuevo Suministro");
			
			grid.setWidget(0, 0, new Label("Tipo de Suministro"));
			grid.setWidget(1, 0, new Label("Cantidad"));
			
			grid.setWidget(0, 1, tipoSuministro);
			grid.setWidget(1, 1, cantidad);
			
			grid.setBorderWidth(1);
	    	
			cantidad.addKeyboardListener(new KeyNumeric());
			costo.addKeyboardListener(new KeyNumeric());
			if(recursosLocales.getSelectedIndex()==1){
				grid.setWidget(2, 0, new Label("Costo en pesos"));
				grid.setWidget(2, 1, costo);
			}
			tipoSuministro.addItem("Seleccionar","0");
			for (TipoSuministroDTO dto : tipoSuministrosGlobal) {
				tipoSuministro.addItem(dto.getNombre(), dto.getId().toString());
			}
			
			if (a == "modificar" || a == "eliminar" ){
		    	
		    	int row=0;
	    		boolean salir = false;
	    		while (!salir) {
					row++;
					if(tipoSuministro.getValue(row).equals(String.valueOf(sum.getTipoSuministro().getId()))){
						tipoSuministro.setSelectedIndex(row);
						salir = true;
					}
				}
	    		
	    		cantidad.setText(String.valueOf(sum.getCantidad()));
			    costo.setText(String.valueOf(sum.getCosto()));
		    }
		    
		    if ( a == "eliminar" ){
		    	tipoSuministro.setEnabled(false);
		    	cantidad.setEnabled(false);
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
					llamador.setStyleName("gwt-DialogBox");
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
			SolicitudSuministroDTO dto=validar();
			if(dto!=null){
			
				if(a=="modificar"){
					dto.setId(suministro.getId());
					for(SolicitudSuministroDTO s:necesidadDTO.getSolicitudesSuministros()){
						if(s.getId().equals(dto.getId())){
							s.setCantidad(dto.getCantidad());
							s.setTipoSuministro(dto.getTipoSuministro());
							s.setCosto(dto.getCosto());
						}
					}
				}else if(a== "nuevo"){
					dto.setId(numerador++);
					necesidadDTO.getSolicitudesSuministros().add(dto);
				}else if(a=="eliminar"){
					dto.setId(suministro.getId());
					SolicitudSuministroDTO del=null;
					for(SolicitudSuministroDTO s:necesidadDTO.getSolicitudesSuministros()){
						if(s.getId().equals(dto.getId())){
							del=s;
						}
					}
					necesidadDTO.getSolicitudesSuministros().remove(del);
				}
				
				    suministros= new Grid(necesidadDTO.getSolicitudesSuministros().size()+1, 4);
				    suministros.setWidget(0, 0, new Label("Tipo Suministro"));
				    suministros.setWidget(0, 1, new Label("Cantidad"));
				    suministros.setWidget(0, 2, new Label("Modificar"));
				    suministros.setWidget(0, 3, new Label("Eliminar"));
				    
				    gridFormulario.setWidget(5, 0, suministros);
				    
					for(int i=0;i<4;i++){
						suministros.getCellFormatter().setStyleName(0,i, "tbl-cab");
					}
					
					suministros.setBorderWidth(1);
					
					int row=1;
					for(SolicitudSuministroDTO s: necesidadDTO.getSolicitudesSuministros()){
						suministros.setWidget(row, 0, new Label(s.getTipoSuministro().getNombre()));
						suministros.setWidget(row, 1, new Label(String.valueOf(s.getCantidad())));
						final SolicitudSuministroDTO sum= s;
						final Image modificarI= new Image("/images/modificar.png");
						modificarI.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								FormDialogSuministroBox dialog= new FormDialogSuministroBox(sum, "modificar",llamador);
								dialog.show();
							}
						});
						
						final Image eliminarI= new Image("/images/eliminar.png");
						
						eliminarI.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								FormDialogSuministroBox dialog= new FormDialogSuministroBox(sum, "eliminar",llamador);
								dialog.show();
							}
						});
						suministros.setWidget(row, 2, modificarI);
						suministros.setWidget(row, 3, eliminarI);
						row++;
					}
					hide();
					llamador.setStyleName("gwt-DialogBox");
			}
			
			
		}

		private SolicitudSuministroDTO validar() {
			SolicitudSuministroDTO dto= new SolicitudSuministroDTO();
			
			if(tipoSuministro.getSelectedIndex()== 0){
				Window.alert("Indique Tipo de Suministro");
				return null;
			}
			for (TipoSuministroDTO t : tipoSuministrosGlobal) {
				if(t.getId().equals(Long.valueOf(tipoSuministro.getValue(tipoSuministro.getSelectedIndex())))){
					dto.setTipoSuministro(t);
				}
			}
			
			if(cantidad.getText().trim().length()==0){
				Window.alert("Indique Cantidad");
				return null;
			}
			
			if(recursosLocales.getSelectedIndex()==1){
				if(costo.getText().trim().length()==0){
					Window.alert("Indique Costo");
					return null;
				}
				dto.setCosto(Float.valueOf(costo.getText()));
			}
			
			dto.setCantidad(Integer.valueOf(cantidad.getText()));
			
			
			
			return dto;
			
		}
		
	}
}
