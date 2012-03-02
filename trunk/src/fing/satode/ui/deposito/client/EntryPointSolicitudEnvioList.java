package fing.satode.ui.deposito.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
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
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import fing.satode.constantes.EstadoSolicitudEnvio;
import fing.satode.constantes.ItemConstante;
import fing.satode.data.DepositoDTO;
import fing.satode.data.PuntoReferenciaDTO;
import fing.satode.data.SolicitudEnvioDTO;
import fing.satode.data.SolicitudEnvioSuministroDTO;
import fing.satode.ui.puntoReferencias.client.IPuntoReferencia;
import fing.satode.ui.puntoReferencias.client.IPuntoReferenciaAsync;

public class EntryPointSolicitudEnvioList implements EntryPoint {

	final Button buscarB = new Button("Buscar");
	final VerticalPanel vertical = new VerticalPanel();
	private ArrayList<DepositoDTO> depositoGlobal;
	private ArrayList<PuntoReferenciaDTO> puntosEntregaGlobal;
	private ArrayList<SolicitudEnvioDTO> solicitudesEnvio;
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
				solicitudesEnvio=result;
				Grid principal= new Grid(result.size()+1,7);
				
				principal.setWidget(0, 0, new Label("Deposito"));
				principal.setWidget(0, 1, new Label("Fecha"));
				principal.setWidget(0, 2, new Label("Estado"));
				principal.setWidget(0, 3, new Label("Punto Entrega"));
				principal.setWidget(0, 4, new Label("Ver"));
				principal.setWidget(0, 5, new Label("Enviar"));
				principal.setWidget(0, 6, new Label("Recibir"));
				
				for(int i=0;i<7;i++){
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
					
					final Button recibirB= new Button("Recibir");
					
					recibirB.addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							FormDialogBox dialog= new FormDialogBox(id, "recibir");
							dialog.show();
						}
						
					});
					
					principal.setWidget(row, 4, verI);
					if(c.getEstado()==EstadoSolicitudEnvio.NUEVA){
						principal.setWidget(row, 5, enviarB);	
					}
					if(c.getEstado()==EstadoSolicitudEnvio.ENVIADA){
						principal.setWidget(row, 6, recibirB);	
					}
					
					row++;
				}
				
				vertical.add(principal);
		
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
		//final Label label = new Label();
		final CaptionPanel panelPrincipal = new CaptionPanel();
	  
	    final Grid gridForm= new Grid(6,2);
	    private Grid gridSuministros;
	    
	    final TextBox fecha= new TextBox();
	    final TextBox deposito = new TextBox();
	    final TextBox puntoEntrega=new TextBox();
	    final TextArea observacionesEnvio=new TextArea();
	    final TextArea observacionesRecibir = new TextArea();
	    
	    final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
		final Button nuevoB = new Button("Nuevo");
		
		public FormDialogBox(Long idSolicitudEnvio,String accion){
			a=accion;
			id=idSolicitudEnvio;
			
			if(a=="enviar") panelPrincipal.setCaptionText("Enviar Solicitud");
			if(a=="ver") panelPrincipal.setCaptionText("Ver Solicitud");
			if(a=="recibir") panelPrincipal.setCaptionText("Recibir Solicitud de Envio");
			
			if (  a == "recibir" || a == "enviar" || a=="ver"){
				SolicitudEnvioDTO dto=null;
				for(SolicitudEnvioDTO sol:solicitudesEnvio){
					if(sol.getId().equals(id)){
						dto=sol;
					}
				}
				
				DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
				fecha.setText(format.format(dto.getFecha()));
				deposito.setText(dto.getDeposito().toString());
				puntoEntrega.setText(dto.getPuntoEntrega().toString());
				observacionesEnvio.setText(dto.getObservacionesEnvio());
				observacionesRecibir.setText(dto.getObservacionesEntrega());
				
				gridSuministros= new Grid(dto.getSolicitudesEnvioSuministros().size()+1,2);
				
				gridSuministros.setWidget(0, 0, new Label("Tipo Suministro"));
				gridSuministros.setWidget(0, 1, new Label("Cantidad"));
				
				for(int i=0;i<2;i++){
					gridSuministros.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				
				int row=1;
				for(SolicitudEnvioSuministroDTO solsum :dto.getSolicitudesEnvioSuministros()){
					gridSuministros.setWidget(row, 0, new Label(solsum.getTipoSuministro().toString()));
					gridSuministros.setWidget(row, 1, new Label(String.valueOf(solsum.getCantidad())));
					row++;
				}
				
				fecha.setEnabled(false);
				deposito.setEnabled(false);
				puntoEntrega.setEnabled(false);
				
				if(a!="recibir"){
					observacionesRecibir.setEnabled(false);
				}else{
					observacionesEnvio.setEnabled(false);
				}
			 }
			
			if(a=="ver"){
				observacionesEnvio.setEnabled(false);
				aceptar.setEnabled(false);
			}
			observacionesRecibir.setVisibleLines(10);
			observacionesRecibir.setWidth("250px");
			observacionesEnvio.setVisibleLines(10);
			observacionesEnvio.setWidth("250px");
			deposito.setWidth("250px");
			puntoEntrega.setWidth("250px");
			
			gridForm.setBorderWidth(1);
			gridSuministros.setBorderWidth(1);
			
			gridForm.setWidget(0,0, new Label("Fecha"));
			gridForm.setWidget(0,1, fecha);
			
			gridForm.setWidget(1,0, new Label("Deposito"));
			gridForm.setWidget(1,1, deposito);
			
			gridForm.setWidget(2,0, new Label("Punto Entrega"));
			gridForm.setWidget(2,1, puntoEntrega);
			
			gridForm.setWidget(3,0, new Label("Observaciones Envio"));
			gridForm.setWidget(3,1, observacionesEnvio);
			
			gridForm.setWidget(4,0, new Label("Observaciones Entrega"));
			gridForm.setWidget(4,1, observacionesRecibir);
			
			gridForm.setWidget(5,0, gridSuministros);
			
			
			panelPrincipal.add(vertical);
			vertical.add(gridForm);	
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
			SolicitudEnvioDTO dto=null;
			for(SolicitudEnvioDTO sol:solicitudesEnvio){
				if(sol.getId().equals(id)){
					dto=sol;
				}
			}
			
			if(dto!=null){
				dto.setId(id);
				
				IDepositoAsync serverDeposito=GWT.create(IDeposito.class);
				
				if(a=="enviar"){
					
					dto.setObservacionesEnvio(observacionesEnvio.getText());
					
					serverDeposito.enviarSolicitudEnvio(dto,new AsyncCallback<Void>() {
						
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
				if(a=="recibir"){
					
					dto.setObservacionesEntrega(observacionesRecibir.getText());
					
					serverDeposito.recibirSolicitudEnvio(dto,new AsyncCallback<Void>() {
						
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
		
		
	}
}
