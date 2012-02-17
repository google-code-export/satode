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

import fing.satode.constantes.EstadoSolicitudEnvio;
import fing.satode.constantes.ItemConstante;
import fing.satode.data.CuentaCorrienteSuministroDTO;
import fing.satode.data.DepositoDTO;
import fing.satode.data.DesastreDTO;
import fing.satode.data.NecesidadDTO;
import fing.satode.data.PuntoReferenciaDTO;
import fing.satode.data.SolicitudEnvioDTO;
import fing.satode.data.SolicitudSuministroDTO;
import fing.satode.data.TipoSuministroDTO;
import fing.satode.ui.necesidades.client.INecesidad;
import fing.satode.ui.necesidades.client.INecesidadAsync;
import fing.satode.ui.necesidades.client.EntryPointNecesidad.FormDialogBox;
import fing.satode.ui.necesidades.client.EntryPointNecesidad.FormDialogSuministroBox;
import fing.satode.ui.puntoReferencias.client.IPuntoReferencia;
import fing.satode.ui.puntoReferencias.client.IPuntoReferenciaAsync;

public class EntryPointSolicitudEnvioList implements EntryPoint {

	final Button buscarB = new Button("Buscar");
	final VerticalPanel vertical = new VerticalPanel();
	private ArrayList<DepositoDTO> depositoGlobal;
	private ArrayList<TipoSuministroDTO> tipoSuministrosGlobal;
	private ArrayList<PuntoReferenciaDTO> puntosEntregaGlobal;
	private Grid gridStock;
	final Label modificarLabel= new Label("Modificar");
	final Label eliminarLabel= new Label("Eliminar");
	private static ListBox depositos;
	private static ListBox estados;
	private static ListBox puntosEntrega;
	
	@Override
	public void onModuleLoad() {
		HorizontalPanel botonera= new HorizontalPanel();
		Grid depositoGrid=new Grid(1,2);
		Grid puntoEntregaGrid=new Grid(1,2);
		Grid estadosGrid=new Grid(1,2);
		botonera.add(depositoGrid);
		botonera.add(puntoEntregaGrid);
		botonera.add(estadosGrid);
		botonera.add(buscarB);
		RootPanel.get("botones").add(botonera);
		RootPanel.get("solicitudes").add(vertical);
		
		depositos=new ListBox();
		depositos.addItem("Todos","0");
		depositoGrid.setWidget(0, 1, depositos);
		depositoGrid.setWidget(0, 0,new Label("Deposito"));
		
		IDepositoAsync servidorDepositos= GWT.create(IDeposito.class);
		
		servidorDepositos.listaDepositos(new AsyncCallback<ArrayList<DepositoDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<DepositoDTO> result) {
				depositoGlobal=result;
				for(DepositoDTO d:result){
					depositos.addItem(d.getId().toString()+"-"+d.getCiudad().getNombre()+"-"+d.getDireccion(), d.getId().toString());
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
		
		puntosEntrega=new ListBox();
		puntosEntrega.addItem("Todos","0");
		puntoEntregaGrid.setWidget(0, 1, puntosEntrega);
		puntoEntregaGrid.setWidget(0, 0,new Label("Punto Entrega"));
		IPuntoReferenciaAsync serverPuntoReferencia=GWT.create(IPuntoReferencia.class);
		
		serverPuntoReferencia.listPuntoEntrega(new AsyncCallback<ArrayList<PuntoReferenciaDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<PuntoReferenciaDTO> result) {
				// TODO Auto-generated method stub
				puntosEntregaGlobal=result;
				for(PuntoReferenciaDTO d:result){
					puntosEntrega.addItem(d.getId().toString()+"-"+d.getDepartamento().getNombre()+"-"+d.getCiudad().getNombre()+"-"+d.getDireccion(),d.getId().toString());
				}
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
				// TODO Auto-generated method stub
			
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
				
			}
		});
		
		estados=new ListBox();
		estados.addItem("Todos","0");
		estadosGrid.setWidget(0, 1, estados);
		estadosGrid.setWidget(0, 0,new Label("Estado"));
		
		for(ItemConstante item:EstadoSolicitudEnvio.getItems()){
			estados.addItem(item.getTexto(),String.valueOf(item.getCodigo()));
		}
		
		buscarB.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
					cargarLista();
			}
					
		});
	}
	
	private void cargarLista() {
		vertical.clear();
		
		Long idDeposito=Long.valueOf(depositos.getValue(depositos.getSelectedIndex()));
		Long idPuntoEntrega=Long.valueOf(puntosEntrega.getValue(puntosEntrega.getSelectedIndex()));
		int estado=Integer.valueOf(estados.getValue(estados.getSelectedIndex()));

		IDepositoAsync serverDeposito =GWT.create(IDeposito.class);
		
		serverDeposito.buscarSolicitudesEnvio(idPuntoEntrega, idDeposito, estado,new AsyncCallback<ArrayList<SolicitudEnvioDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<SolicitudEnvioDTO> result) {
				// TODO Auto-generated method stub
				Grid principal= new Grid(result.size()+1,6);
				
				principal.setWidget(0, 0, new Label("Deposito"));
				principal.setWidget(0, 1, new Label("Fecha"));
				principal.setWidget(0, 2, new Label("Estado"));
				principal.setWidget(0, 3, new Label("Punto Entrega"));
				principal.setWidget(0, 4, new Label("Ver"));
				principal.setWidget(0, 5, new Label("Enviar"));
				
				for(int i=0;i<6;i++){
					principal.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				
				principal.setBorderWidth(1);
				
				int row=1;
				DateTimeFormat format = DateTimeFormat.getFormat("dd/MM/yyyy");
				for(SolicitudEnvioDTO c:result){
					principal.setWidget(row, 0, new Label( c.getDeposito().getId().toString()+"-"+c.getDeposito().getCiudad().getNombre()+"-"+c.getDeposito().getDireccion()));
					principal.setWidget(row, 1, new Label(c.getFecha()!=null?format.format(c.getFecha()):"Sin Fecha"));
					principal.setWidget(row, 2, new Label(EstadoSolicitudEnvio.getTXT(c.getEstado())));
					principal.setWidget(row, 3, new Label(c.getPuntoEntrega().getId().toString()+"-"+c.getPuntoEntrega().getDepartamento().getNombre()+"-"+c.getPuntoEntrega().getCiudad().getNombre()+"-"+c.getPuntoEntrega().getDireccion()));
					
					final Image verI= new Image("/images/LOCATE.bmp");
					final Long id=c.getId();
					
					verI.addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							FormDialogBox dialog= new FormDialogBox(id, "ver");
							dialog.show();
						}
						
					});

					final Button enviarB= new Button("Enviar");
					
					enviarB.addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							FormDialogBox dialog= new FormDialogBox(id, "enviar");
							dialog.show();
						}
						
					});
					
					principal.setWidget(row, 4, verI);
					if(c.getEstado()==EstadoSolicitudEnvio.NUEVA){
						principal.setWidget(row, 5, enviarB);	
					}
					
					row++;
				}
				
				vertical.add(principal);
		
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
	    final TextBox fecha= new TextBox();
	
   
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
	    	
	    	if(a=="enviar") label.setText("Enviar Solicitud");
			if(a=="ver") label.setText("Ver Solicitud");
			
			if ( a == "enviar" || a=="ver"){
			 }
			
			
			vertical.add(label);

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
			SolicitudEnvioDTO dto=validar();
			
			if(dto!=null){
				dto.setId(id);
				
				if(a=="enviar"){
					
				}
			}
		}
		
		private SolicitudEnvioDTO validar(){
			
			return null;
		}
	}
}
