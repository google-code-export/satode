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
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import fing.satode.data.TipoCostoDTO;

public class EntryPointTipoCostoList implements EntryPoint {

	final Button nuevoB = new Button("Nuevo");
	final VerticalPanel vertical = new VerticalPanel();
	private ArrayList<TipoCostoDTO> tipocostoGlobal;
	
	private Grid tipocostoGrid;
	final Label modificarLabel= new Label("Modificar");
	final Label eliminarLabel= new Label("Eliminar");
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		RootPanel.get("tipocostos").clear();
		vertical.clear();
		RootPanel.get("tipocostos").add(vertical);
		
		IDesastreAsync servidorDesastres = GWT.create(IDesastre.class);
		
		servidorDesastres.listaTipoCosto(new AsyncCallback<ArrayList<TipoCostoDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<TipoCostoDTO> result) {
				// TODO Auto-generated method stub
				tipocostoGlobal=result;
				tipocostoGrid = new Grid(tipocostoGlobal.size()+1,5);
				tipocostoGrid.setWidget(0, 0, new Label("ID"));
				tipocostoGrid.setWidget(0, 1, new Label("Nombre"));
				tipocostoGrid.setWidget(0, 2, new Label("Descripcion"));
				tipocostoGrid.setWidget(0, 3, modificarLabel);
				tipocostoGrid.setWidget(0, 4, eliminarLabel);
				
				for(int i=0;i<5;i++){
					tipocostoGrid.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				
				tipocostoGrid.setBorderWidth(1);
				int row=1;
				for(TipoCostoDTO e: result){
					tipocostoGrid.setWidget(row, 0, new Label(e.getId().toString()));
					tipocostoGrid.setWidget(row, 1, new Label(e.getNombre()));
					tipocostoGrid.setWidget(row, 2, new Label(e.getDescripcion()));
					
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
					tipocostoGrid.setWidget(row, 3, modificarI);
					tipocostoGrid.setWidget(row, 4, eliminarI);
					row++;
				}
				vertical.add(tipocostoGrid);
				
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
	    final Grid grid= new Grid(2,2);
	    final TextBox nombre=new TextBox();
	    final TextBox descripcion=new TextBox();
	    final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
	
	    public FormDialogBox(Long idDesastre, String accion) {
			// TODO Auto-generated constructor stub
	    	a=accion;
	    	id=idDesastre;
	    	
	    	
	    	grid.setWidget(0, 0, new Label("Nombre"));
	    	grid.setWidget(0, 1, nombre);
	    	
	    	grid.setWidget(1, 0, new Label("Descripcion"));
	    	grid.setWidget(1, 1, descripcion);
	    	
	    	grid.setBorderWidth(1);
	    	
			if(a=="modificar") label.setText("Modificar Tipo Costo");
			if(a=="eliminar") label.setText("Eliminar Tipo Costo");
			if(a=="nuevo") label.setText("Nuevo Tipo Costo");
			
			if(a=="modificar" ||a=="eliminar"){
				TipoCostoDTO dto=null;
				for(TipoCostoDTO d:tipocostoGlobal){
					if(id.equals(d.getId())){
						dto=d;
						break;
					}
				}
				
				nombre.setText(dto.getNombre());
				descripcion.setText(dto.getDescripcion());
				
			}
			
			if(a=="eliminar"){
				nombre.setEnabled(false);
				descripcion.setEnabled(false);
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
			TipoCostoDTO dto=validar();
			if(dto!=null){
				dto.setId(id);
				if(a=="modificar"){
					
					IDesastreAsync servidorDesastre=GWT.create(IDesastre.class);
					
					servidorDesastre.modificarTipoCosto(dto,new AsyncCallback<Void>() {
						
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
					IDesastreAsync servidorDesastre=GWT.create(IDesastre.class);
					
					servidorDesastre.nuevoTipoCosto(dto,new AsyncCallback<Void>() {
						
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
					
					IDesastreAsync servidorDesastre=GWT.create(IDesastre.class);
					
					servidorDesastre.eliminarTipoCosto(dto,new AsyncCallback<Void>() {
						
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

		private TipoCostoDTO validar() {
			// TODO Auto-generated method stub
			TipoCostoDTO dto= new TipoCostoDTO();
			
			
			if(nombre.getText()==null ||nombre.getText().trim().length()==0){
				Window.alert("Indique nombre");
				return null;
			}
			
			if(descripcion.getText()==null ||descripcion.getText().trim().length()==0){
				Window.alert("Indique descripcion");
				return null;
			}
			
			dto.setDescripcion(descripcion.getText());
			dto.setNombre(nombre.getText());
			
			return dto;
			
		}
	}
}
