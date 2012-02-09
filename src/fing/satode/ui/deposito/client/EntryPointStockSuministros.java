package fing.satode.ui.deposito.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

import fing.satode.constantes.EstadoNecesidad;
import fing.satode.constantes.ItemConstante;
import fing.satode.data.CiudadDTO;
import fing.satode.data.CuentaCorrienteSuministroDTO;
import fing.satode.data.DepartamentoDTO;
import fing.satode.data.DepositoDTO;
import fing.satode.data.TipoSuministroDTO;
import fing.satode.ui.deposito.client.EntryPointDeposito.FormDialogBox;
import fing.satode.ui.general.client.IBasicos;
import fing.satode.ui.general.client.IBasicosAsync;
import fing.satode.ui.general.data.KeyNumeric;

public class EntryPointStockSuministros implements EntryPoint {

	final Button buscarB = new Button("Buscar");
	final VerticalPanel vertical = new VerticalPanel();
	private ArrayList<DepositoDTO> depositoGlobal;
	private ArrayList<TipoSuministroDTO> tipoSuministrosGlobal;
	private Grid gridStock;
	final Label modificarLabel= new Label("Modificar");
	final Label eliminarLabel= new Label("Eliminar");
	private static ListBox depositos;
	private static ListBox tipoSuministro;
	
	@Override
	public void onModuleLoad() {
		HorizontalPanel botonera= new HorizontalPanel();
		Grid depositoGrid=new Grid(1,2);
		Grid tipoSuministroGrid=new Grid(1,2);
		botonera.add(depositoGrid);
		botonera.add(tipoSuministroGrid);
		botonera.add(buscarB);
		RootPanel.get("botones").add(botonera);
		RootPanel.get("stock").add(vertical);
		
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
		
		tipoSuministro=new ListBox();
		tipoSuministro.addItem("Todos","0");
		tipoSuministroGrid.setWidget(0, 1, tipoSuministro);
		tipoSuministroGrid.setWidget(0, 0,new Label("Tipo Suministro"));
		
		servidorDepositos.listaTipoSuministros(new AsyncCallback<ArrayList<TipoSuministroDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<TipoSuministroDTO> result) {
				// TODO Auto-generated method stub
				tipoSuministrosGlobal=result;
				for(TipoSuministroDTO d:result){
					tipoSuministro.addItem(d.getId().toString()+"-"+d.getNombre()+"-"+d.getDescripcion(),d.getId().toString());
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
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
	}
	
	private void cargarLista() {
		vertical.clear();
		
		Long idDeposito=Long.valueOf(depositos.getValue(depositos.getSelectedIndex()));
		Long idTipoSuministro=Long.valueOf(tipoSuministro.getValue(tipoSuministro.getSelectedIndex()));

		IDepositoAsync serverDeposito =GWT.create(IDeposito.class);
		
		serverDeposito.buscarCuentaCorrienteSuministro(idDeposito, idTipoSuministro, new AsyncCallback<ArrayList<CuentaCorrienteSuministroDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<CuentaCorrienteSuministroDTO> result) {
				// TODO Auto-generated method stub
				Grid principal= new Grid(result.size()+1,4);
				
				principal.setWidget(0, 0, new Label("Deposito"));
				principal.setWidget(0, 1, new Label("Suministro"));
				principal.setWidget(0, 2, new Label("Descripcion"));
				principal.setWidget(0, 3, new Label("Cantidad"));
				for(int i=0;i<4;i++){
					principal.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				principal.setBorderWidth(1);
				
				int row=1;
				for(CuentaCorrienteSuministroDTO c:result){
					principal.setWidget(row, 0, new Label( c.getDeposito().getId().toString()+"-"+c.getDeposito().getCiudad().getNombre()+"-"+c.getDeposito().getDireccion()));
					principal.setWidget(row, 1, new Label(c.getTipoSuministro().getNombre()));
					principal.setWidget(row, 2, new Label(c.getTipoSuministro().getDescripcion()));
					principal.setWidget(row, 3, new Label(String.valueOf(c.getCantidad())));
					row++;	
				}
				
				vertical.add(principal);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	
}
