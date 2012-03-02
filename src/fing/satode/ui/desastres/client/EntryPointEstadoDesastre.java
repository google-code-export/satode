package fing.satode.ui.desastres.client;

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
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import fing.satode.constantes.EstadoNecesidad;
import fing.satode.constantes.EstadoSolicitudEnvio;
import fing.satode.data.CostoDTO;
import fing.satode.data.DesastreDTO;
import fing.satode.data.EstadoDesastreDTO;
import fing.satode.data.NecesidadDTO;
import fing.satode.data.SolicitudEnvioDTO;
import fing.satode.data.SolicitudEnvioSuministroDTO;
import fing.satode.data.SolicitudSuministroDTO;


public class EntryPointEstadoDesastre implements EntryPoint {

	final Button consultarB = new Button("Consultar");
	final VerticalPanel vertical = new VerticalPanel();
	final HorizontalPanel botonera = new HorizontalPanel();
	
	
	private ArrayList<DesastreDTO> desastresGlobal;
	private ListBox desastre=new ListBox(); 
	
	@Override
	public void onModuleLoad() {
		RootPanel.get("botones").add(botonera);
		botonera.add(new Label("Desastre"));
		botonera.add(desastre);
		botonera.add(consultarB);
		
		IDesastreAsync servidorDesastre=GWT.create(IDesastre.class);
		
		servidorDesastre.listaDesastres(new AsyncCallback<ArrayList<DesastreDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<DesastreDTO> result) {
				desastresGlobal=result;
				
				desastre.addItem("Seleccionar","0");
				for(DesastreDTO d: desastresGlobal){
					desastre.addItem(d+"",d.getId().toString());
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
		
		
		consultarB.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				mostrarInforme();
			}
		});
	}
	
	private void mostrarInforme() {
		Long idDesastre= Long.valueOf(desastre.getValue(desastre.getSelectedIndex()));
		
		if(idDesastre==0){
			Window.alert("Seleccione desastre");
			return;
		}
		
		IDesastreAsync servidorDesastres=GWT.create(IDesastre.class);
		
		servidorDesastres.reporteEstadoDesastre(idDesastre, new AsyncCallback<EstadoDesastreDTO>() {
			
			@Override
			public void onSuccess(EstadoDesastreDTO dto) {
				 RootPanel.get("reporte").clear();
				 VerticalPanel vertical=new VerticalPanel();
				 VerticalPanel verticalNecesidades=new VerticalPanel();
				 VerticalPanel verticalLocalNecesidades=new VerticalPanel();
				 
				 CaptionPanel necesidadesCaption=new CaptionPanel();
				 necesidadesCaption.setCaptionHTML("<titulo1>Necesidades</titulo1>");
				 CaptionPanel necesidadesLocalesCaption=new CaptionPanel();
				 necesidadesLocalesCaption.setCaptionHTML("<titulo2>Locales</titulo2>");
				 CaptionPanel necesidadesPendientesCaption=new CaptionPanel();
				 necesidadesPendientesCaption.setCaptionHTML("<titulo2>Pendientes</titulo2>");
				 CaptionPanel necesidadesAprobadasCaption=new CaptionPanel();
				 necesidadesAprobadasCaption.setCaptionHTML("<titulo2>Aprobadas</titulo2>");
				 CaptionPanel necesidadesRechazadasCaption=new CaptionPanel();
				 necesidadesRechazadasCaption.setCaptionHTML("<titulo2>Rechazadas</titulo2>");
				 
				 Grid necesidadesLocales= new Grid(dto.getNecesidadesLocales().size()+1, 7);
				 Grid necesidadesPendientes= new Grid(dto.getNecesidadesPendientes().size()+1, 7);
				 Grid necesidadesAprobadas= new Grid(dto.getNecesidadesAprobadas().size()+1, 7);
				 Grid necesidadesRechazadas= new Grid(dto.getNecesidadesRechazadas().size()+1, 7);
				 float totalGastosDesastre=0;
				 
				 vertical.add(necesidadesCaption);
				 necesidadesCaption.setStyleName("fondocomobarra");
				 necesidadesCaption.add(verticalNecesidades);
			
				 
				 verticalNecesidades.add(necesidadesLocalesCaption);
				 verticalNecesidades.add(necesidadesPendientesCaption);
				 verticalNecesidades.add(necesidadesAprobadasCaption);
				 verticalNecesidades.add(necesidadesRechazadasCaption);
				 
				 necesidadesLocalesCaption.add(verticalLocalNecesidades);
				 necesidadesPendientesCaption.add(necesidadesPendientes);
				 verticalLocalNecesidades.add(necesidadesLocales);
				 necesidadesAprobadasCaption.add(necesidadesAprobadas);
				 necesidadesRechazadasCaption.add(necesidadesRechazadas);
				 
				 necesidadesLocalesCaption.setStyleName("fondoblanco");
				 necesidadesPendientesCaption.setStyleName("fondoblanco");
				 necesidadesAprobadasCaption.setStyleName("fondoblanco");
				 necesidadesRechazadasCaption.setStyleName("fondoblanco");
				 
			
				 
				 for(int i=0;i<7;i++){
					 necesidadesLocales.getCellFormatter().setStyleName(0,i, "tbl-cab");
				 }
				 necesidadesLocales.setBorderWidth(1);
				 necesidadesLocales.setWidget(0, 0, new Label("ID"));
				 necesidadesLocales.setWidget(0, 1, new Label("Fecha"));
				 necesidadesLocales.setWidget(0, 2, new Label("Punto Entrega"));
				 necesidadesLocales.setWidget(0, 3, new Label("Descripcion"));
				 necesidadesLocales.setWidget(0, 4, new Label("Estado"));
				 necesidadesLocales.setWidget(0, 5, new Label("Usuario"));
				 necesidadesLocales.setWidget(0, 6, new Label("Suministros"));
				 
				
				 DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
				 int row=1;
				 for(NecesidadDTO s:dto.getNecesidadesLocales()){
					
					 necesidadesLocales.setWidget(row, 0, new Label(s.getId().toString()));
					 necesidadesLocales.setWidget(row, 1, new Label(format.format(s.getFecha())));
					 necesidadesLocales.setWidget(row, 2, new Label(s.getId().toString()+"-"+s.getPuntoEntrega().getCiudad().getNombre()+"-"+s.getPuntoEntrega().getDireccion()));
					 necesidadesLocales.setWidget(row, 3, new Label(s.getDescripcion()));
					 necesidadesLocales.setWidget(row, 4, new Label(EstadoNecesidad.getTXT(s.getEstado())));
					 necesidadesLocales.setWidget(row, 5, new Label(s.getUsuarioCreador().getNombreCompleto()));
					 
					 Grid solsGrid=new Grid( s.getSolicitudesSuministros().size()+2,3);
					 for(int i=0;i<3;i++){
						 solsGrid.getCellFormatter().setStyleName(0,i, "tbl-cab");
					 }
					 solsGrid.setBorderWidth(1);
					 solsGrid.setWidget(0, 0, new Label("Tipo Suministro"));
					 solsGrid.setWidget(0, 1, new Label("Cantidad"));
					 solsGrid.setWidget(0, 2, new Label("Costo"));
					 float total=0;
					 int rows=1;
					 for(SolicitudSuministroDTO sols: s.getSolicitudesSuministros()){
						 solsGrid.setWidget(rows, 0, new Label(sols.getTipoSuministro().getNombre()));
						 solsGrid.setWidget(rows, 1, new Label(String.valueOf(sols.getCantidad())));
						 solsGrid.setWidget(rows, 2, new Label(String.valueOf(sols.getCosto())));
						 total+=sols.getCosto();
						 rows++;
					 }
					 solsGrid.setWidget(rows, 1, new Label("Total"));
					 solsGrid.getCellFormatter().setStyleName(rows,1, "tbl-cab");
					 solsGrid.setWidget(rows, 2, new Label(String.valueOf(total)));
					 solsGrid.getCellFormatter().setStyleName(rows,2, "total");
					 totalGastosDesastre+=total;
					 necesidadesLocales.setWidget(row,6,solsGrid);
					 
					 row++;
				 }
				 
				 for(int i=0;i<7;i++){
					 necesidadesPendientes.getCellFormatter().setStyleName(0,i, "tbl-cab");
				 }
				 necesidadesPendientes.setBorderWidth(1);
				 necesidadesPendientes.setWidget(0, 0, new Label("ID"));
				 necesidadesPendientes.setWidget(0, 1, new Label("Fecha"));
				 necesidadesPendientes.setWidget(0, 2, new Label("Punto Entrega"));
				 necesidadesPendientes.setWidget(0, 3, new Label("Descripcion"));
				 necesidadesPendientes.setWidget(0, 4, new Label("Estado"));
				 necesidadesPendientes.setWidget(0, 5, new Label("Usuario"));
				 necesidadesPendientes.setWidget(0, 6, new Label("Suministros"));
				 
				
				 row=1;
				 for(NecesidadDTO s:dto.getNecesidadesPendientes()){
					
					 necesidadesPendientes.setWidget(row, 0, new Label(s.getId().toString()));
					 necesidadesPendientes.setWidget(row, 1, new Label(format.format(s.getFecha())));
					 necesidadesPendientes.setWidget(row, 2, new Label(s.getId().toString()+"-"+s.getPuntoEntrega().getCiudad().getNombre()+"-"+s.getPuntoEntrega().getDireccion()));
					 necesidadesPendientes.setWidget(row, 3, new Label(s.getDescripcion()));
					 necesidadesPendientes.setWidget(row, 4, new Label(EstadoNecesidad.getTXT(s.getEstado())));
					 necesidadesPendientes.setWidget(row, 5, new Label(s.getUsuarioCreador().getNombreCompleto()));
					 
					 Grid solsGrid=new Grid( s.getSolicitudesSuministros().size()+1,2);
					 for(int i=0;i<2;i++){
						 solsGrid.getCellFormatter().setStyleName(0,i, "tbl-cab");
					 }
					 solsGrid.setBorderWidth(1);
					 solsGrid.setWidget(0, 0, new Label("Tipo Suministro"));
					 solsGrid.setWidget(0, 1, new Label("Cantidad"));
					
					 int rows=1;
					 for(SolicitudSuministroDTO sols: s.getSolicitudesSuministros()){
						 solsGrid.setWidget(rows, 0, new Label(sols.getTipoSuministro().getNombre()));
						 solsGrid.setWidget(rows, 1, new Label(String.valueOf(sols.getCantidad())));
						 rows++;
					 }
					 
					 necesidadesPendientes.setWidget(row,6,solsGrid);
					 
					 row++;
				 }
				 
				 for(int i=0;i<7;i++){
					 necesidadesAprobadas.getCellFormatter().setStyleName(0,i, "tbl-cab");
				 }
				 necesidadesAprobadas.setBorderWidth(1);
				 necesidadesAprobadas.setWidget(0, 0, new Label("ID"));
				 necesidadesAprobadas.setWidget(0, 1, new Label("Fecha"));
				 necesidadesAprobadas.setWidget(0, 2, new Label("Punto Entrega"));
				 necesidadesAprobadas.setWidget(0, 3, new Label("Descripcion"));
				 necesidadesAprobadas.setWidget(0, 4, new Label("Estado"));
				 necesidadesAprobadas.setWidget(0, 5, new Label("Usuario"));
				 necesidadesAprobadas.setWidget(0, 6, new Label("Suministros"));
				 
				
				 row=1;
				 for(NecesidadDTO s:dto.getNecesidadesAprobadas()){
					
					 necesidadesAprobadas.setWidget(row, 0, new Label(s.getId().toString()));
					 necesidadesAprobadas.setWidget(row, 1, new Label(format.format(s.getFecha())));
					 necesidadesAprobadas.setWidget(row, 2, new Label(s.getId().toString()+"-"+s.getPuntoEntrega().getCiudad().getNombre()+"-"+s.getPuntoEntrega().getDireccion()));
					 necesidadesAprobadas.setWidget(row, 3, new Label(s.getDescripcion()));
					 necesidadesAprobadas.setWidget(row, 4, new Label(EstadoNecesidad.getTXT(s.getEstado())));
					 necesidadesAprobadas.setWidget(row, 5, new Label(s.getUsuarioCreador().getNombreCompleto()));
					 
					 Grid solsGrid=new Grid( s.getSolicitudesSuministros().size()+1,2);
					 for(int i=0;i<2;i++){
						 solsGrid.getCellFormatter().setStyleName(0,i, "tbl-cab");
					 }
					 solsGrid.setBorderWidth(1);
					 solsGrid.setWidget(0, 0, new Label("Tipo Suministro"));
					 solsGrid.setWidget(0, 1, new Label("Cantidad"));
					
					 int rows=1;
					 for(SolicitudSuministroDTO sols: s.getSolicitudesSuministros()){
						 solsGrid.setWidget(rows, 0, new Label(sols.getTipoSuministro().getNombre()));
						 solsGrid.setWidget(rows, 1, new Label(String.valueOf(sols.getCantidad())));
						 rows++;
					 }
					 
					 necesidadesAprobadas.setWidget(row,6,solsGrid);
					 
					 row++;
				 }
				 
				 for(int i=0;i<7;i++){
					 necesidadesRechazadas.getCellFormatter().setStyleName(0,i, "tbl-cab");
				 }
				 necesidadesRechazadas.setBorderWidth(1);
				 necesidadesRechazadas.setWidget(0, 0, new Label("ID"));
				 necesidadesRechazadas.setWidget(0, 1, new Label("Fecha"));
				 necesidadesRechazadas.setWidget(0, 2, new Label("Punto Entrega"));
				 necesidadesRechazadas.setWidget(0, 3, new Label("Descripcion"));
				 necesidadesRechazadas.setWidget(0, 4, new Label("Estado"));
				 necesidadesRechazadas.setWidget(0, 5, new Label("Usuario"));
				 necesidadesRechazadas.setWidget(0, 6, new Label("Suministros"));
				 
				
				 row=1;
				 for(NecesidadDTO s:dto.getNecesidadesRechazadas()){
					
					 necesidadesRechazadas.setWidget(row, 0, new Label(s.getId().toString()));
					 necesidadesRechazadas.setWidget(row, 1, new Label(format.format(s.getFecha())));
					 necesidadesRechazadas.setWidget(row, 2, new Label(s.getId().toString()+"-"+s.getPuntoEntrega().getCiudad().getNombre()+"-"+s.getPuntoEntrega().getDireccion()));
					 necesidadesRechazadas.setWidget(row, 3, new Label(s.getDescripcion()));
					 necesidadesRechazadas.setWidget(row, 4, new Label(EstadoNecesidad.getTXT(s.getEstado())));
					 necesidadesRechazadas.setWidget(row, 5, new Label(s.getUsuarioCreador().getNombreCompleto()));
					 
					 Grid solsGrid=new Grid( s.getSolicitudesSuministros().size()+1,2);
					 for(int i=0;i<2;i++){
						 solsGrid.getCellFormatter().setStyleName(0,i, "tbl-cab");
					 }
					 solsGrid.setBorderWidth(1);
					 solsGrid.setWidget(0, 0, new Label("Tipo Suministro"));
					 solsGrid.setWidget(0, 1, new Label("Cantidad"));
					
					 int rows=1;
					 for(SolicitudSuministroDTO sols: s.getSolicitudesSuministros()){
						 solsGrid.setWidget(rows, 0, new Label(sols.getTipoSuministro().getNombre()));
						 solsGrid.setWidget(rows, 1, new Label(String.valueOf(sols.getCantidad())));
						 rows++;
					 }
					 
					 necesidadesRechazadas.setWidget(row,6,solsGrid);
					 
					 row++;
				 }
				 
				 
				 VerticalPanel verticalSolicitudes=new VerticalPanel();
				 
				 CaptionPanel solicitudesCaption=new CaptionPanel();
				 solicitudesCaption.setCaptionHTML("<titulo1>Solicitudes de suministros</titulo1>");
				 CaptionPanel solicitudesPendientesCaption=new CaptionPanel("Pendientes");
				 solicitudesPendientesCaption.setCaptionHTML("<titulo2>Pendientes</titulo2>");
				 CaptionPanel solicitudesEnviadasNoRecibidasCaption=new CaptionPanel();
				 solicitudesEnviadasNoRecibidasCaption.setCaptionHTML("<titulo2>Solicitudes enviadas no recibidas</titulo2>");
				 CaptionPanel solicitudesRecibidasCaption=new CaptionPanel();
				 solicitudesRecibidasCaption.setCaptionHTML("<titulo2>Solicitudes recibidas</titulo2>");
				 
				 Grid solicitudesPendientes= new Grid(dto.getSolicitudPendiente().size()+1, 5);
				 Grid solicitudesEnviNoRec= new Grid(dto.getSolicitudEnviadasNoRecibidas().size()+1, 5);
				 Grid solicitudesRecibidas= new Grid(dto.getSolicitudEnvioRecibidas().size()+1, 5);
				 
				 vertical.add(solicitudesCaption);
				 solicitudesCaption.add(verticalSolicitudes);
				 solicitudesCaption.setStyleName("fondocomobarra");
				 
				 verticalSolicitudes.add(solicitudesPendientesCaption);
				 verticalSolicitudes.add(solicitudesEnviadasNoRecibidasCaption);
				 verticalSolicitudes.add(solicitudesRecibidasCaption);
				 
				 
				 solicitudesPendientesCaption.add(solicitudesPendientes);
				 solicitudesEnviadasNoRecibidasCaption.add(solicitudesEnviNoRec);
				 solicitudesRecibidasCaption.add(solicitudesRecibidas);
				
				 solicitudesPendientesCaption.setStyleName("fondoblanco");
				 solicitudesEnviadasNoRecibidasCaption.setStyleName("fondoblanco");
				 solicitudesRecibidasCaption.setStyleName("fondoblanco");
				 
				 solicitudesPendientes.setWidget(0, 0, new Label("Deposito"));
				 solicitudesPendientes.setWidget(0, 1, new Label("Fecha"));
				 solicitudesPendientes.setWidget(0, 2, new Label("Estado"));
				 solicitudesPendientes.setWidget(0, 3, new Label("Punto Entrega"));
				 solicitudesPendientes.setWidget(0, 4, new Label("Suministros"));

				 for(int i=0;i<5;i++){
					 solicitudesPendientes.getCellFormatter().setStyleName(0,i, "tbl-cab");
				 }
				
				 solicitudesPendientes.setBorderWidth(1);
				
				 row=1;
				 for(SolicitudEnvioDTO c:dto.getSolicitudPendiente()){
					 solicitudesPendientes.setWidget(row, 0, new Label( c.getDeposito().getId().toString()+"-"+c.getDeposito().getCiudad().getNombre()+"-"+c.getDeposito().getDireccion()));
					 solicitudesPendientes.setWidget(row, 1, new Label(c.getFecha()!=null?format.format(c.getFecha()):"Sin Fecha"));
					 solicitudesPendientes.setWidget(row, 2, new Label(EstadoSolicitudEnvio.getTXT(c.getEstado())));
					 solicitudesPendientes.setWidget(row, 3, new Label(c.getPuntoEntrega().getId().toString()+"-"+c.getPuntoEntrega().getDepartamento().getNombre()+"-"+c.getPuntoEntrega().getCiudad().getNombre()+"-"+c.getPuntoEntrega().getDireccion()));
					 
					 Grid solsGrid=new Grid( c.getSolicitudesEnvioSuministros().size()+1,2);
					 for(int i=0;i<2;i++){
						 solsGrid.getCellFormatter().setStyleName(0,i, "tbl-cab");
					 }
					 solsGrid.setBorderWidth(1);
					 solsGrid.setWidget(0, 0, new Label("Tipo Suministro"));
					 solsGrid.setWidget(0, 1, new Label("Cantidad"));
					
					 int rows=1;
					 for(SolicitudEnvioSuministroDTO sols: c.getSolicitudesEnvioSuministros()){
						 solsGrid.setWidget(rows, 0, new Label(sols.getTipoSuministro().getNombre()));
						 solsGrid.setWidget(rows, 1, new Label(String.valueOf(sols.getCantidad())));
						 rows++;
					 }
					 solicitudesPendientes.setWidget(row, 4, solsGrid);
					 row++;
				 }
				 
				 solicitudesEnviNoRec.setWidget(0, 0, new Label("Deposito"));
				 solicitudesEnviNoRec.setWidget(0, 1, new Label("Fecha"));
				 solicitudesEnviNoRec.setWidget(0, 2, new Label("Estado"));
				 solicitudesEnviNoRec.setWidget(0, 3, new Label("Punto Entrega"));
				 solicitudesEnviNoRec.setWidget(0, 4, new Label("Suministros"));
				
				 for(int i=0;i<5;i++){
					 solicitudesEnviNoRec.getCellFormatter().setStyleName(0,i, "tbl-cab");
				 }
				
				 solicitudesEnviNoRec.setBorderWidth(1);
				
				 row=1;
				 for(SolicitudEnvioDTO c:dto.getSolicitudEnviadasNoRecibidas()){
					 solicitudesEnviNoRec.setWidget(row, 0, new Label( c.getDeposito().getId().toString()+"-"+c.getDeposito().getCiudad().getNombre()+"-"+c.getDeposito().getDireccion()));
					 solicitudesEnviNoRec.setWidget(row, 1, new Label(c.getFecha()!=null?format.format(c.getFecha()):"Sin Fecha"));
					 solicitudesEnviNoRec.setWidget(row, 2, new Label(EstadoSolicitudEnvio.getTXT(c.getEstado())));
					 solicitudesEnviNoRec.setWidget(row, 3, new Label(c.getPuntoEntrega().getId().toString()+"-"+c.getPuntoEntrega().getDepartamento().getNombre()+"-"+c.getPuntoEntrega().getCiudad().getNombre()+"-"+c.getPuntoEntrega().getDireccion()));
					 
					 Grid solsGrid=new Grid( c.getSolicitudesEnvioSuministros().size()+1,2);
					 for(int i=0;i<2;i++){
						 solsGrid.getCellFormatter().setStyleName(0,i, "tbl-cab");
					 }
					 solsGrid.setBorderWidth(1);
					 solsGrid.setWidget(0, 0, new Label("Tipo Suministro"));
					 solsGrid.setWidget(0, 1, new Label("Cantidad"));
					
					 int rows=1;
					 for(SolicitudEnvioSuministroDTO sols: c.getSolicitudesEnvioSuministros()){
						 solsGrid.setWidget(rows, 0, new Label(sols.getTipoSuministro().getNombre()));
						 solsGrid.setWidget(rows, 1, new Label(String.valueOf(sols.getCantidad())));
						 rows++;
					 }
					 solicitudesEnviNoRec.setWidget(row, 4, solsGrid);
					 row++;
				 }
				 
				 solicitudesRecibidas.setWidget(0, 0, new Label("Deposito"));
				 solicitudesRecibidas.setWidget(0, 1, new Label("Fecha"));
				 solicitudesRecibidas.setWidget(0, 2, new Label("Estado"));
				 solicitudesRecibidas.setWidget(0, 3, new Label("Punto Entrega"));
				 solicitudesRecibidas.setWidget(0, 4, new Label("Suministros"));
				
				 for(int i=0;i<5;i++){
					 solicitudesRecibidas.getCellFormatter().setStyleName(0,i, "tbl-cab");
				 }
				
				 solicitudesRecibidas.setBorderWidth(1);
				
				 row=1;
				 for(SolicitudEnvioDTO c:dto.getSolicitudEnvioRecibidas()){
					 solicitudesRecibidas.setWidget(row, 0, new Label( c.getDeposito().getId().toString()+"-"+c.getDeposito().getCiudad().getNombre()+"-"+c.getDeposito().getDireccion()));
					 solicitudesRecibidas.setWidget(row, 1, new Label(c.getFecha()!=null?format.format(c.getFecha()):"Sin Fecha"));
					 solicitudesRecibidas.setWidget(row, 2, new Label(EstadoSolicitudEnvio.getTXT(c.getEstado())));
					 solicitudesRecibidas.setWidget(row, 3, new Label(c.getPuntoEntrega().getId().toString()+"-"+c.getPuntoEntrega().getDepartamento().getNombre()+"-"+c.getPuntoEntrega().getCiudad().getNombre()+"-"+c.getPuntoEntrega().getDireccion()));
					 
					 Grid solsGrid=new Grid( c.getSolicitudesEnvioSuministros().size()+1,2);
					 for(int i=0;i<2;i++){
						 solsGrid.getCellFormatter().setStyleName(0,i, "tbl-cab");
					 }
					 solsGrid.setBorderWidth(1);
					 solsGrid.setWidget(0, 0, new Label("Tipo Suministro"));
					 solsGrid.setWidget(0, 1, new Label("Cantidad"));
					
					 int rows=1;
					 for(SolicitudEnvioSuministroDTO sols: c.getSolicitudesEnvioSuministros()){
						 solsGrid.setWidget(rows, 0, new Label(sols.getTipoSuministro().getNombre()));
						 solsGrid.setWidget(rows, 1, new Label(String.valueOf(sols.getCantidad())));
						 rows++;
					 }
					 solicitudesRecibidas.setWidget(row, 4, solsGrid);
					 row++;
				 }
				 
				 VerticalPanel verticalCostos=new VerticalPanel();
				 
				 CaptionPanel costosCabCaption=new CaptionPanel();
				 costosCabCaption.setCaptionHTML("<titulo1>Gestion de gastos</titulo1>");
				 
				 CaptionPanel costoCaption=new CaptionPanel("Costo");
				 costoCaption.setCaptionHTML("<titulo2>Costo</titulo2>");
				 CaptionPanel totalCostosCaption=new CaptionPanel("Total Gastos");
				 totalCostosCaption.setCaptionHTML("<titulo2>Total Gastos</titulo2>");
				 
				 Grid costos= new Grid(dto.getCostos().size()+2, 5);
				 Grid total= new Grid(4, 2);
				 
				 vertical.add(costosCabCaption);
				 costosCabCaption.add(verticalCostos);
				 costosCabCaption.setStyleName("fondocomobarra");
				 
				 verticalCostos.add(costoCaption);
				 verticalCostos.add(totalCostosCaption);
				 
				 
				 costoCaption.add(costos);
				 totalCostosCaption.add(total);
				 
				 costoCaption.setStyleName("fondoblanco");
				 totalCostosCaption.setStyleName("fondoblanco");
				 
				 
				 costos.setWidget(0, 0, new Label("ID"));
				 costos.setWidget(0, 1, new Label("Fecha"));
				 costos.setWidget(0, 2, new Label("Tipo Costo"));
				 costos.setWidget(0, 3, new Label("Pesos"));
				 costos.setWidget(0, 4, new Label("Dolares"));
				 
				 for(int i=0;i<5;i++){
					 costos.getCellFormatter().setStyleName(0,i, "tbl-cab");
				 }
				
				 costos.setBorderWidth(1);
				 row=1;
				 float pesos=0;
				 float dolares=0;
				 for(CostoDTO e: dto.getCostos()){
					 costos.setWidget(row, 0, new Label(e.getId().toString()));
					 costos.setWidget(row, 1, new Label(format.format(e.getFecha())));
					 costos.setWidget(row, 2, new Label(e.getTipocosto().getNombre()));
					 costos.setWidget(row, 3, new Label(String.valueOf(e.getCantidadPesos())));
					 costos.setWidget(row, 4, new Label(String.valueOf(e.getCantidadDolares())));
					 pesos+=e.getCantidadPesos();
					 dolares+=e.getCantidadDolares();
					 row++;
				 }
				 costos.setWidget(row, 2, new Label("Totales"));
				 costos.getCellFormatter().setStyleName(row, 2, "tbl-cab");
				 costos.setWidget(row, 3, new Label(String.valueOf(pesos)));
				 costos.getCellFormatter().setStyleName(row, 3, "total");
				 costos.setWidget(row, 4, new Label(String.valueOf(dolares)));
				 costos.getCellFormatter().setStyleName(row, 4, "total");
			 
				 total.setWidget(0, 0, new Label("Gasto"));
				 total.setWidget(0, 1, new Label("Cantidad pesos"));
				 total.setBorderWidth(1);
				 
				 for(int i=0;i<2;i++){
					 total.getCellFormatter().setStyleName(0,i, "tbl-cab");
				 }
				 total.setWidget(1, 0, new Label("Necesidades cubiertas con recursos locales"));
				 total.setWidget(1, 1, new Label(String.valueOf(totalGastosDesastre)));
				 total.setWidget(2, 0, new Label("Costos registrados"));
				 total.setWidget(2, 1, new Label(String.valueOf(pesos)));
				 total.setWidget(3, 0, new Label("Total"));
				 total.getCellFormatter().setStyleName(3, 0, "tbl-cab");
				 total.setWidget(3, 1, new Label(String.valueOf(pesos+totalGastosDesastre)));
				 total.getCellFormatter().setStyleName(3, 1, "total");
				 
				 
				 RootPanel.get("reporte").add(vertical);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
		
		
	}

}
