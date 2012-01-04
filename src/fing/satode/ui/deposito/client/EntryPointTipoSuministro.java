package fing.satode.ui.deposito.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
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

import fing.satode.data.TipoSuministroDTO;

public class EntryPointTipoSuministro implements EntryPoint {

	final Button nuevoB = new Button("Nuevo");
	final VerticalPanel vertical = new VerticalPanel();
	private ArrayList<TipoSuministroDTO> tiposuministrosGlobal;
	private Grid tiposumninistros;
	final Label modificarLabel= new Label("Modificar");
	final Label eliminarLabel= new Label("Eliminar");
	
	@Override
	public void onModuleLoad() {
		
		RootPanel.get("botones").add(nuevoB);
		nuevoB.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				FormDialogBox dialog= new FormDialogBox(0L, "nuevo");
				dialog.show();
			}
		});
		
		cargarLista();
	}
	
	private void cargarLista() {
		RootPanel.get("tiposuministros").clear();
		vertical.clear();
		RootPanel.get("tiposuministros").add(vertical);
		
		IDepositoAsync servidorDepositos= GWT.create(IDeposito.class);
		
		servidorDepositos.listaTipoSuministros(new AsyncCallback<ArrayList<TipoSuministroDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<TipoSuministroDTO> result) {
				tiposuministrosGlobal=result;
				tiposumninistros = new Grid(result.size()+1,7);
				tiposumninistros.setWidget(0, 0, new Label("ID"));
				tiposumninistros.setWidget(0, 1, new Label("Nombre"));
				tiposumninistros.setWidget(0, 2, new Label("Descripci\u00F3n"));
				tiposumninistros.setWidget(0, 3, new Label("Refrigeraci\u00F3n"));
				tiposumninistros.setWidget(0, 4, new Label("Vence"));
				tiposumninistros.setWidget(0, 5, modificarLabel);
				tiposumninistros.setWidget(0, 6, eliminarLabel);
				
				for(int i=0;i<7;i++){
					tiposumninistros.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				
				tiposumninistros.setBorderWidth(1);
				int row=1;
				for(TipoSuministroDTO e: result){
					tiposumninistros.setWidget(row, 0, new Label(e.getId().toString()));
					tiposumninistros.setWidget(row, 1, new Label(e.getNombre()));
					tiposumninistros.setWidget(row, 2, new Label(e.getDescripcion()));
					tiposumninistros.setWidget(row, 3, new Label(e.getRefrigeracion()?"SI":"NO"));
					tiposumninistros.setWidget(row, 4, new Label(e.getFechaVencimiento()?"SI":"NO"));
					final Long id= e.getId();
					final Image modificarI= new Image("/images/modificar.png");
					modificarI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							// TODO Auto-generated method stub
							FormDialogBox dialog= new FormDialogBox(id, "modificar");
							dialog.show();
						}
					});
					
					final Image eliminarI= new Image("/images/eliminar.png");
					
					eliminarI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							// TODO Auto-generated method stub
							FormDialogBox dialog= new FormDialogBox(id, "eliminar");
							dialog.show();
						}
					});
					tiposumninistros.setWidget(row, 5, modificarI);
					tiposumninistros.setWidget(row, 6, eliminarI);
					row++;
				}
				vertical.add(tiposumninistros);
				
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
	    final Grid grid= new Grid(8,2);
	    final TextBox nombre=new TextBox();
	    final TextBox descripcion=new TextBox();
	    final ListBox refrigeracion= new ListBox();
	    final ListBox fechavencimiento= new ListBox();
	    final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
	
	    public FormDialogBox(Long idDeposito, String accion) {
			// TODO Auto-generated constructor stub
	    	a=accion;
	    	id=idDeposito;
	    	
	    	if(a=="modificar") label.setText("Modificar Tipo Suministro");
			if(a=="eliminar") label.setText("Eliminar  Tipo Suministro");
			if(a=="nuevo") label.setText("Nuevo  Tipo Suministro");
			
			grid.setWidget(0, 0, new Label("Nombre"));
			grid.setWidget(1, 0, new Label("Descripci\u00F3n"));
			grid.setWidget(2, 0, new Label("Refrigeraci\u00F3n"));
			grid.setWidget(3, 0, new Label("Vencie"));
			
			grid.setWidget(0, 1, nombre);
			grid.setWidget(1, 1, descripcion);
			grid.setWidget(2, 1, refrigeracion);
			grid.setWidget(3, 1, fechavencimiento);
			grid.setBorderWidth(1);
	    	
			refrigeracion.addItem("SI", "SI");
			refrigeracion.addItem("NO", "NO");
			fechavencimiento.addItem("SI", "SI");
			fechavencimiento.addItem("NO", "NO");
			
	        if (a == "modificar" || a == "eliminar" ){
		    	
		    	TipoSuministroDTO tiposuministroDTO= null;
	    		for(TipoSuministroDTO d:tiposuministrosGlobal){
	    			if(d.getId().equals(id)){
	    				tiposuministroDTO=d;
	    			}
	    		}
	    	
	    		nombre.setText(tiposuministroDTO.getNombre());
			    descripcion.setText(tiposuministroDTO.getDescripcion());
			    refrigeracion.setSelectedIndex(tiposuministroDTO.getRefrigeracion()?0:1);
			    fechavencimiento.setSelectedIndex(tiposuministroDTO.getFechaVencimiento()?0:1);
			    
		    }
		    
		    if ( a == "eliminar" ){
		    	nombre.setEnabled(false);
		    	descripcion.setEnabled(false);
		    	refrigeracion.setEnabled(false);
		    	fechavencimiento.setEnabled(false);
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

		protected void procesar() {
			// TODO Auto-generated method stub
			TipoSuministroDTO dto=validar();
			if(dto!=null){
				dto.setId(id);
				if(a=="modificar"){
					
					IDepositoAsync servidorDeposito=GWT.create(IDeposito.class);
					
					servidorDeposito.modificarTipoSuministro(dto,new AsyncCallback<Void>() {
						
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
					IDepositoAsync servidorDeposito=GWT.create(IDeposito.class);
					
					servidorDeposito.nuevoTipoSuministro(dto,new AsyncCallback<Void>() {
						
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
					IDepositoAsync servidorDeposito=GWT.create(IDeposito.class);
					
					servidorDeposito.eliminarTipoSuministro(dto,new AsyncCallback<Void>() {
						
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
		}

		private TipoSuministroDTO validar() {
			TipoSuministroDTO dto= new TipoSuministroDTO();
			
			if(nombre.getText().trim().length()==0){
				Window.alert("Indique nombre");
				return null;
			}
			dto.setNombre(nombre.getText());
			dto.setDescripcion(descripcion.getText());
			dto.setRefrigeracion(refrigeracion.getSelectedIndex()==0);
			dto.setFechaVencimiento(fechavencimiento.getSelectedIndex()==0);
			
			return dto;
			
		}
		
		
	}


}
