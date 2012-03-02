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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import fing.satode.constantes.EstadoNecesidad;
import fing.satode.data.DesastreDTO;
import fing.satode.data.EstadoDesastreDTO;
import fing.satode.data.NecesidadDTO;
import fing.satode.data.SolicitudEnvioSuministroDTO;
import fing.satode.data.SolicitudSuministroDTO;


public class EntryPointEstadoDesastre implements EntryPoint {

	final Button consultarB = new Button("Consultar");
	final VerticalPanel vertical = new VerticalPanel();
	
	
	private ArrayList<DesastreDTO> desastresGlobal;
	private ListBox desastre=new ListBox(); 
	
	@Override
	public void onModuleLoad() {
		RootPanel.get("botones").add(new Label("Desastre"));
		RootPanel.get("botones").add(desastre);
		RootPanel.get("botones").add(consultarB);
		
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
				 CaptionPanel necesidadesCaption=new CaptionPanel("Necesidades");
				 CaptionPanel necesidadesLocalesCaption=new CaptionPanel("Locales");
				 CaptionPanel necesidadesPendientesCaption=new CaptionPanel("Pendientes");
				 CaptionPanel necesidadesAprobadasCaption=new CaptionPanel("Aprobadas");
				 CaptionPanel necesidadesRechazadasCaption=new CaptionPanel("Rechazadas");
				 Grid necesidadesLocales= new Grid(dto.getNecesidadesLocales().size()+1, 7);
				 Grid necesidadesPendientes= new Grid(dto.getNecesidadesPendientes().size()+1, 7);
				 Grid necesidadesAprobadas= new Grid(dto.getNecesidadesAprobadas().size()+1, 7);
				 Grid necesidadesRechazadas= new Grid(dto.getNecesidadesRechazadas().size()+1, 7);
				 
				 vertical.add(necesidadesCaption);
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
				 RootPanel.get("reporte").add(vertical);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
		
		
	}

}
