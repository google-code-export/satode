package fing.satode.ui.necesidades.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import org.mortbay.jetty.servlet.HashSessionIdManager;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LinkElement;
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
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import fing.satode.constantes.EstadoNecesidad;
import fing.satode.constantes.EstadoSolicitudEnvio;
import fing.satode.constantes.ItemConstante;
import fing.satode.data.CuentaCorrienteSuministroDTO;
import fing.satode.data.DesastreDTO;
import fing.satode.data.GestionNecesidadDTO;
import fing.satode.data.NecesidadDTO;
import fing.satode.data.PlanSuministroDTO;
import fing.satode.data.PuntoReferenciaDTO;
import fing.satode.data.SolicitudEnvioDTO;
import fing.satode.data.SolicitudEnvioSuministroDTO;
import fing.satode.data.SolicitudSuministroDTO;
import fing.satode.data.TipoSuministroDTO;
import fing.satode.data.UsuarioDTO;
import fing.satode.ui.deposito.client.IDeposito;
import fing.satode.ui.deposito.client.IDepositoAsync;
import fing.satode.ui.desastres.client.IDesastre;
import fing.satode.ui.desastres.client.IDesastreAsync;
import fing.satode.ui.general.data.DepositoCantidad;
import fing.satode.ui.general.data.KeyNumeric;
import fing.satode.ui.necesidades.client.EntryPointNecesidad.FormDialogBox;
import fing.satode.ui.necesidades.client.EntryPointNecesidad.FormDialogSuministroBox;
import fing.satode.ui.puntoReferencias.client.IPuntoReferencia;
import fing.satode.ui.puntoReferencias.client.IPuntoReferenciaAsync;
import fing.satode.ui.usuarios.client.IUsuario;
import fing.satode.ui.usuarios.client.IUsuarioAsync;

public class EntryPointGestionNecesidad implements EntryPoint {


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
	private static HashMap<Long, ArrayList<CuentaCorrienteSuministroDTO>> stocks;
	
	@Override
	public void onModuleLoad() {
		HorizontalPanel botonera= new HorizontalPanel();
		Grid desastreGrid=new Grid(1,2);
		Grid estadoGrid=new Grid(1,2);
		botonera.add(desastreGrid);
		botonera.add(estadoGrid);
		botonera.add(buscarB);
		
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
		
		serverDeposito.buscarCuentaCorrienteSuministro(0L, 0L, new AsyncCallback<ArrayList<CuentaCorrienteSuministroDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<CuentaCorrienteSuministroDTO> result) {
				stocks= new HashMap<Long, ArrayList<CuentaCorrienteSuministroDTO>>();
				for(CuentaCorrienteSuministroDTO dto:result){
					if(!stocks.containsKey(dto.getTipoSuministro().getId())){
						stocks.put(dto.getTipoSuministro().getId(),new ArrayList<CuentaCorrienteSuministroDTO>());
					}
					ArrayList<CuentaCorrienteSuministroDTO> lista=stocks.get(dto.getTipoSuministro().getId());
					lista.add(dto);
				}
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
		
		final INecesidadAsync serverNecesidad=GWT.create(INecesidad.class);
		
		serverNecesidad.buscarNecesidades(idDesastre, idEstado, false, new AsyncCallback<ArrayList<NecesidadDTO>>() {
			
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
				gridPrincipal.setWidget(0, 7, new Label("Procesar"));
				gridPrincipal.setWidget(0, 8, new Label("Confirmar"));
				gridPrincipal.setWidget(0, 9, new Label("Rechazar"));
		
				
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
					
					
					final Button  procesar= new Button();
					procesar.setText("Procesar");
					
					procesar.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							FormDialogBox dialog= new FormDialogBox(id, "procesar");
							dialog.show();
						}
					});
					
					final Button  modificarI= new Button();
					modificarI.setText("Confirmar");
					final NecesidadDTO ns=s;
					modificarI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							ns.setEstado(EstadoNecesidad.ACEPTADA);
							serverNecesidad.modificarNecesidad(ns, new AsyncCallback<Void>() {
								
								@Override
								public void onSuccess(Void result) {
									
									serverNecesidad.buscarGestionNecesidadPorNecesidad(ns.getId(), new AsyncCallback<GestionNecesidadDTO>() {
										
										@Override
										public void onSuccess(GestionNecesidadDTO result) {
											for(PlanSuministroDTO ps:result.getPlanesSuministros()){
												for(SolicitudEnvioDTO se:ps.getSolicitudesEnvios()){
													se.setEstado(EstadoSolicitudEnvio.NUEVA);
												}
											}
											
											serverNecesidad.modificarGestionNecesidad(result, new AsyncCallback<Void>() {
												
												@Override
												public void onSuccess(Void result) {
													cargarLista();
												}
												
												@Override
												public void onFailure(Throwable caught) {
													caught.printStackTrace();
													Window.alert("ERROR AJAX");
												}
											});
										}
										
										@Override
										public void onFailure(Throwable caught) {
											caught.printStackTrace();
											Window.alert("ERROR AJAX");
											
										}
									});
								}
								
								@Override
								public void onFailure(Throwable caught) {
									caught.printStackTrace();
									Window.alert("ERROR AJAX");
								}
							});
						}
					});
					
					final Button  rechazarI= new Button();
					rechazarI.setText("Rechazar");
					rechazarI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							ns.setEstado(EstadoNecesidad.RECHAZADA);
							serverNecesidad.modificarNecesidad(ns, new AsyncCallback<Void>() {
								
								@Override
								public void onSuccess(Void result) {
									cargarLista();
								}
								
								@Override
								public void onFailure(Throwable caught) {
									caught.printStackTrace();
									Window.alert("ERROR AJAX");
								}
							});
						}
					});
					
					if(s.getEstado()==EstadoNecesidad.EN_PROCESO || s.getEstado()==EstadoNecesidad.INGRESADA){
						gridPrincipal.setWidget(row, 7, procesar);
						gridPrincipal.setWidget(row, 9, rechazarI);
					}
					
					if(s.getEstado()==EstadoNecesidad.EN_PROCESO){
						gridPrincipal.setWidget(row, 8, modificarI);
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
		final VerticalPanel panelSuministros= new VerticalPanel();
		final VerticalPanel vertical= new VerticalPanel();
		final CaptionPanel panelPrincipal = new CaptionPanel();
	  
	    final Grid gridFecha= new Grid(1,2);
	    
	    final DatePicker datePicker = new DatePicker();
	    final TextBox descripcion =new TextBox();
	    final TextBox fecha= new TextBox();
	    final ListBox desastres=new ListBox();
	    final HashMap<Long,ArrayList<DepositoCantidad>> cantidades= new HashMap<Long,ArrayList<DepositoCantidad>>();
	    final ListBox puntoEntrega=new ListBox();
	   
	    final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
		private GestionNecesidadDTO dto;
		
		public FormDialogBox(Long idNecesidad,String accion){
			a=accion;
			id=idNecesidad;
			cantidades.clear();
			
			datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
		      public void onValueChange(ValueChangeEvent<Date> event) {
		        Date date = (Date)event.getValue();
		        DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
		        String dateString = format.format(date);
		        fecha.setText(dateString);
		      }
		    });
		    datePicker.setValue(new Date(), true);
	    	
	    	if(a=="modificar") panelPrincipal.setCaptionText("Modificar Gestion Necesidad");
			if(a=="procesar") panelPrincipal.setCaptionText("Procesar Necesidad");
			if(a=="nuevo") panelPrincipal.setCaptionText("Nuevo Gestion Necesidad");
			if(a=="ver") panelPrincipal.setCaptionText("Ver Gestion Necesidad");
			
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
			
		    gridFormulario.setWidget(5, 0, suministros);
			    
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
			
		    gridFormulario.setWidget(5, 0, panelSuministros);
		    
		    INecesidadAsync serverNecesidad=GWT.create(INecesidad.class);
		    
		    serverNecesidad.buscarGestionNecesidadPorNecesidad(id, new AsyncCallback<GestionNecesidadDTO>() {
				
				@Override
				public void onSuccess(GestionNecesidadDTO result) {
					dto=result;
					boolean yaExiste=true;
					if(dto==null){
						yaExiste=false;
						dto=new GestionNecesidadDTO();
						dto.setNecesidad(necesidadDTO);
						dto.setUsuario(usuarioGlobal);
						INecesidadAsync serverNecesidad=GWT.create(INecesidad.class);
						
						serverNecesidad.nuevoGestionNecesidad(dto, new AsyncCallback<Void>() {
							
							@Override
							public void onSuccess(Void result) {}
							
							@Override
							public void onFailure(Throwable caught) {
								caught.printStackTrace();
								Window.alert("ERROR AJAX");
							}
						});
					}
					
					for(SolicitudSuministroDTO s: necesidadDTO.getSolicitudesSuministros()){
						suministros= new Grid(2, 2);
						suministros.setBorderWidth(1);
						suministros.setWidget(0, 0, new Label("Tipo Suministro"));
					    suministros.setWidget(0, 1, new Label("Cantidad"));
					    suministros.setWidget(1, 0, new Label(s.getTipoSuministro().getNombre()));
						suministros.setWidget(1, 1, new Label(String.valueOf(s.getCantidad())));
						for(int i=0;i<2;i++){
							suministros.getCellFormatter().setStyleName(0,i, "tbl-cab");
						}
						panelSuministros.add(suministros);
						
						
						PlanSuministroDTO planSum=null;
						for(PlanSuministroDTO pdto:dto.getPlanesSuministros()){
							if(pdto.getSolicitudsuministro().getTipoSuministro().getId().equals(s.getTipoSuministro().getId())){
								planSum=pdto;
								break;
							}
						}
						
						if(stocks.containsKey(s.getTipoSuministro().getId())){
							Grid depoCantidades= new Grid(stocks.get(s.getTipoSuministro().getId()).size()+1,3);
							depoCantidades.setBorderWidth(1);
							depoCantidades.setWidget(0, 0, new Label("Deposito"));
							depoCantidades.setWidget(0, 1, new Label("Cantidad en Stock"));
							depoCantidades.setWidget(0, 2, new Label("Cantidad a enviar"));
							panelSuministros.add(depoCantidades);
							
							for(int i=0;i<3;i++){
								depoCantidades.getCellFormatter().setStyleName(0,i, "tbl-cab");
							}
							int row=1;
							for(CuentaCorrienteSuministroDTO cuenta:stocks.get(s.getTipoSuministro().getId())){
								depoCantidades.setWidget(row, 0, new Label(cuenta.getDeposito().getId().toString()+"-"+cuenta.getDeposito().getCiudad().getNombre()+"-"+cuenta.getDeposito().getDireccion()));
								depoCantidades.setWidget(row, 1, new Label(String.valueOf(cuenta.getCantidad())));
								TextBox cantidad = new TextBox();
								cantidad.setText("0");
								cantidad.addKeyboardListener(new KeyNumeric());
								DepositoCantidad depoCan=new DepositoCantidad();
								depoCan.setDeposito(cuenta.getDeposito());
								depoCan.setCantidad(cantidad);
								if(!cantidades.containsKey(s.getId())){
									cantidades.put(s.getTipoSuministro().getId(),new ArrayList<DepositoCantidad>());
								}
								ArrayList<DepositoCantidad> listaDepoCan= cantidades.get(s.getTipoSuministro().getId());
								listaDepoCan.add(depoCan);
								depoCantidades.setWidget(row, 2, cantidad);
								row++;
								if(planSum!=null){
									for(SolicitudEnvioDTO sdto: planSum.getSolicitudesEnvios()){
										if(sdto.getDeposito().getId().equals(cuenta.getDeposito().getId())){
											for(SolicitudEnvioSuministroDTO sesdto:sdto.getSolicitudesEnvioSuministros()){
												if(sesdto.getTipoSuministro().getId().equals(s.getTipoSuministro().getId())){
													cantidad.setText(String.valueOf(sesdto.getCantidad()));
												}
											}
										}
									}
								}
							}
						}else{
							if(planSum==null){
								Grid depoCantidades= new Grid(2,3);
								depoCantidades.setBorderWidth(1);
								depoCantidades.setWidget(0, 0, new Label("Deposito"));
								depoCantidades.setWidget(0, 1, new Label("Cantidad en Stock"));
								depoCantidades.setWidget(0, 2, new Label("Cantidad a enviar"));
								panelSuministros.add(depoCantidades);
								
								for(int i=0;i<3;i++){
									depoCantidades.getCellFormatter().setStyleName(0,i, "tbl-cab");
								}
				
								depoCantidades.setWidget(1, 0, new Label("SIN STOCK"));
								depoCantidades.setWidget(1, 1, new Label("SIN STOCK"));
								depoCantidades.setWidget(1, 2, new Label("SIN STOCK"));
							}else{
								
								Grid depoCantidades= new Grid(stocks.get(s.getTipoSuministro().getId()).size()+1,3);
								depoCantidades.setBorderWidth(1);
								depoCantidades.setWidget(0, 0, new Label("Deposito"));
								depoCantidades.setWidget(0, 1, new Label("Cantidad en Stock"));
								depoCantidades.setWidget(0, 2, new Label("Cantidad a enviar"));
								panelSuministros.add(depoCantidades);
								
								for(int i=0;i<3;i++){
									depoCantidades.getCellFormatter().setStyleName(0,i, "tbl-cab");
								}
								int row=1;
								for(SolicitudEnvioDTO solenv:planSum.getSolicitudesEnvios() ){
									for(SolicitudEnvioSuministroDTO solenvsum:solenv.getSolicitudesEnvioSuministros()){
										if(solenvsum.getTipoSuministro().getId().equals(s.getTipoSuministro().getId()))
										{
											depoCantidades.setWidget(row, 0, new Label(solenv.getDeposito().getId().toString()+"-"+solenv.getDeposito().getCiudad().getNombre()+"-"+solenv.getDeposito().getDireccion()));
											depoCantidades.setWidget(row, 1, new Label(String.valueOf(solenvsum.getCantidad())));
											TextBox cantidad = new TextBox();
											cantidad.setText(String.valueOf(solenvsum.getCantidad()));
											cantidad.addKeyboardListener(new KeyNumeric());
											DepositoCantidad depoCan=new DepositoCantidad();
											depoCan.setDeposito(solenv.getDeposito());
											depoCan.setCantidad(cantidad);
											if(!cantidades.containsKey(s.getId())){
												cantidades.put(s.getTipoSuministro().getId(),new ArrayList<DepositoCantidad>());
											}
											ArrayList<DepositoCantidad> listaDepoCan= cantidades.get(s.getTipoSuministro().getId());
											listaDepoCan.add(depoCan);
											depoCantidades.setWidget(row, 2, cantidad);
											row++;
											
										}
									}
								}
							}
						}
					}
				}
				
				@Override
				public void onFailure(Throwable caught) {
					caught.printStackTrace();
					Window.alert("ERROR AJAX");
				}
			});
		    
			
	    	
	    	descripcion.setEnabled(false);
	    	fecha.setEnabled(false);
	    	desastres.setEnabled(false);
	    	puntoEntrega.setEnabled(false);
	    	recursosLocales.setEnabled(false);
	    	datePicker.setVisible(false);
			  
			
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
			GestionNecesidadDTO dto=validar();
			
			if(dto!=null){
				
				dto.setUsuario(usuarioGlobal);
				
				if(a=="procesar"){
					
					INecesidadAsync servidorNecesidad=GWT.create(INecesidad.class);
					
					necesidadDTO.setEstado(EstadoNecesidad.EN_PROCESO);
					servidorNecesidad.modificarNecesidad(necesidadDTO, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
						}
						
						@Override
						public void onFailure(Throwable caught) {
							caught.printStackTrace();
							Window.alert("ERROR AJAX");
						}
					});
					
					servidorNecesidad.modificarGestionNecesidad(dto, new AsyncCallback<Void>() {
						
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
		}
		
		private GestionNecesidadDTO validar(){
			dto.getPlanesSuministros().clear();
			for(Long idTipoSuministo :cantidades.keySet()){
				ArrayList<DepositoCantidad> depoCanList= cantidades.get(idTipoSuministo);
				TipoSuministroDTO tipoSuministro=null;
				for(TipoSuministroDTO t:tipoSuministrosGlobal){
					if(idTipoSuministo.equals(t.getId())){
						tipoSuministro=t;
						break;
					}
					
				}
				PlanSuministroDTO plan= new PlanSuministroDTO();
				dto.getPlanesSuministros().add(plan);				
				for(SolicitudSuministroDTO soldto:necesidadDTO.getSolicitudesSuministros()){
					if(soldto.getTipoSuministro().getId().equals(idTipoSuministo)){
						plan.setSolicitudsuministro(soldto);
						break;
					}
				}
				HashSet<SolicitudEnvioDTO> solicitudesEnvios=new HashSet<SolicitudEnvioDTO>();
				plan.setSolicitudesEnvios(solicitudesEnvios);
				for(DepositoCantidad depoCan:depoCanList){
					if(Integer.valueOf(depoCan.getCantidad().getText())>0){
						SolicitudEnvioDTO solenv= new SolicitudEnvioDTO();
						solenv.setDeposito(depoCan.getDeposito());
						solenv.setEstado(EstadoSolicitudEnvio.CREADA);
						solenv.setFecha(new Date());
						solenv.setPuntoEntrega(necesidadDTO.getPuntoEntrega());
						solenv.setSolicitudesEnvioSuministros(new HashSet<SolicitudEnvioSuministroDTO>());
						SolicitudEnvioSuministroDTO solensum=new SolicitudEnvioSuministroDTO();
						solensum.setCantidad(Integer.valueOf(depoCan.getCantidad().getText()));
						solensum.setTipoSuministro(tipoSuministro);
						solenv.getSolicitudesEnvioSuministros().add(solensum);
						solicitudesEnvios.add(solenv);
					}
				}
				
			}
			
			return dto;
		}
	}

}
