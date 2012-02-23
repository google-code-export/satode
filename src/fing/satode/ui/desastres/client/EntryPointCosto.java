package fing.satode.ui.desastres.client;

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
import com.sun.org.apache.bcel.internal.generic.NEW;

import fing.satode.data.CostoDTO;
import fing.satode.data.DesastreDTO;
import fing.satode.data.TipoCostoDTO;

public class EntryPointCosto implements EntryPoint {

	final Button nuevoB = new Button("Nuevo");
	final Button buscarB = new Button("Buscar");
	final VerticalPanel vertical = new VerticalPanel();
	final HorizontalPanel horizontalBotonera = new HorizontalPanel();
	private ArrayList<CostoDTO> costoGlobal;
	private ArrayList<DesastreDTO> desastresGlobal;
	private ArrayList<TipoCostoDTO> tipocostoGlobal;
	
	private Grid costoGrid;
	final Label modificarLabel= new Label("Modificar");
	final Label eliminarLabel= new Label("Eliminar");
	final ListBox desastresFiltro= new ListBox();
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub

		horizontalBotonera.add(new Label("Desastre"));
		horizontalBotonera.add(desastresFiltro);
		horizontalBotonera.add(buscarB);
		horizontalBotonera.add(nuevoB);
		
		RootPanel.get("botones").add(horizontalBotonera);
		
		nuevoB.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				FormDialogBox dialog= new FormDialogBox(0L, "nuevo");
				dialog.show();
			}
		});
		
		buscarB.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				cargarLista();
			}
		});

		IDesastreAsync servidorDesastres = GWT.create(IDesastre.class);
		
		servidorDesastres.listaDesastres(new AsyncCallback<ArrayList<DesastreDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<DesastreDTO> result) {
				// TODO Auto-generated method stub
				desastresGlobal=result;
				desastresFiltro.addItem("Seleccionar","0");
		    	for(DesastreDTO d:desastresGlobal){
		    		desastresFiltro.addItem(d.toString(),d.getId().toString());
		    	}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
		
		servidorDesastres.listaTipoCosto(new AsyncCallback<ArrayList<TipoCostoDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<TipoCostoDTO> result) {
				tipocostoGlobal=result;
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
	}
	
	private void cargarLista() {
		// TODO Auto-generated method stub
		RootPanel.get("tipocostos").clear();
		vertical.clear();
		RootPanel.get("tipocostos").add(vertical);
		
		IDesastreAsync servidorDesastres = GWT.create(IDesastre.class);
		Long idDesastre=Long.valueOf(desastresFiltro.getValue(desastresFiltro.getSelectedIndex()));
		
		servidorDesastres.listaCosto(idDesastre, new AsyncCallback<ArrayList<CostoDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<CostoDTO> result) {
				// TODO Auto-generated method stub
				costoGlobal=result;
				costoGrid = new Grid(costoGlobal.size()+2,8);
				costoGrid.setWidget(0, 0, new Label("ID"));
				costoGrid.setWidget(0, 1, new Label("Desastre"));
				costoGrid.setWidget(0, 2, new Label("Fecha"));
				costoGrid.setWidget(0, 3, new Label("Tipo Costo"));
				costoGrid.setWidget(0, 4, new Label("Pesos"));
				costoGrid.setWidget(0, 5, new Label("Dolares"));
				costoGrid.setWidget(0, 6, modificarLabel);
				costoGrid.setWidget(0, 7, eliminarLabel);
				
				for(int i=0;i<8;i++){
					costoGrid.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				
				costoGrid.setBorderWidth(1);
				int row=1;
				DateTimeFormat format = DateTimeFormat.getFormat("dd/MM/yyyy");
				float pesos=0;
				float dolares=0;
				for(CostoDTO e: result){
					costoGrid.setWidget(row, 0, new Label(e.getId().toString()));
					costoGrid.setWidget(row, 1, new Label(e.getDesastre().toString()));
					costoGrid.setWidget(row, 2, new Label(format.format(e.getFecha())));
					costoGrid.setWidget(row, 3, new Label(e.getTipocosto().getNombre()));
					costoGrid.setWidget(row, 4, new Label(String.valueOf(e.getCantidadPesos())));
					costoGrid.setWidget(row, 5, new Label(String.valueOf(e.getCantidadDolares())));
					pesos+=e.getCantidadPesos();
					dolares+=e.getCantidadDolares();
					
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
					costoGrid.setWidget(row, 6, modificarI);
					costoGrid.setWidget(row, 7, eliminarI);
					row++;
				}
				costoGrid.setWidget(row, 3, new Label("TOTALES"));
				costoGrid.setWidget(row, 4, new Label(String.valueOf(pesos)));
				costoGrid.setWidget(row, 5, new Label(String.valueOf(dolares)));
				vertical.add(costoGrid);
				
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
	    final Grid grid= new Grid(6,3);
	    final Label fecha = new Label();
	    final DatePicker datePicker = new DatePicker();
	    final TextBox pesos=new TextBox();
	    final TextBox observacion=new TextBox();
	    final TextBox dolares=new TextBox();
	    final ListBox desastre=new ListBox();
	    final ListBox tipocosto=new ListBox();
	    final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
	
	    public FormDialogBox(Long idDesastre, String accion) {
			// TODO Auto-generated constructor stub
	    	a=accion;
	    	id=idDesastre;
	    	
	 	   // Set the value in the text box when the user selects a date
		    datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
		      public void onValueChange(ValueChangeEvent<Date> event) {
		        Date date = (Date)event.getValue();
		        DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
		        String dateString = format.format(date);
		        fecha.setText(dateString);
		      }
		    });
		    datePicker.setValue(new Date(), true);
	        
	    	
	    	grid.setWidget(0, 0, new Label("Fecha"));
	    	grid.setWidget(0, 1, fecha);
	    	grid.setWidget(0, 2, datePicker);
	    	
	    	grid.setWidget(1, 0, new Label("Desastre"));
	    	grid.setWidget(1, 1, desastre);
	    	
	    	grid.setWidget(2, 0, new Label("Tipo Costo"));
	    	grid.setWidget(2, 1, tipocosto);

	    	grid.setWidget(3, 0, new Label("Observaciones"));
	    	grid.setWidget(3, 1, observacion);

	    	grid.setWidget(4, 0, new Label("Pesos"));
	    	grid.setWidget(4, 1, pesos);

	    	grid.setWidget(5, 0, new Label("Dolares"));
	    	grid.setWidget(5, 1, dolares);

	    	desastre.addItem("Seleccionar","0");
	    	for(DesastreDTO d:desastresGlobal){
	    		desastre.addItem(d.toString(),d.getId().toString());
	    	}
	    	
	    	tipocosto.addItem("Seleccionar","0");
	    	for(TipoCostoDTO d:tipocostoGlobal){
	    		tipocosto.addItem(d.toString(),d.getId().toString());
	    	}
	    	
	    	grid.setBorderWidth(1);
	    	
			if(a=="modificar") label.setText("Modificar Tipo Costo");
			if(a=="eliminar") label.setText("Eliminar Tipo Costo");
			if(a=="nuevo") label.setText("Nuevo Tipo Costo");
			
			if(a=="modificar" ||a=="eliminar"){
				CostoDTO dto=null;
				for(CostoDTO d:costoGlobal){
					if(id.equals(d.getId())){
						dto=d;
						break;
					}
				}
				
				datePicker.setValue(dto.getFecha());
				observacion.setText(dto.getObservaciones());
				pesos.setText(String.valueOf(dto.getCantidadPesos()));
				dolares.setText(String.valueOf(dto.getCantidadDolares()));
				
				int row=1;
				for(DesastreDTO d:desastresGlobal){
		    		if(d.getId().equals(dto.getDesastre().getId())){
		    			desastre.setSelectedIndex(row);
		    			break;
		    		}
		    		row++;
		    	}
		    	row=1;
				for(TipoCostoDTO d:tipocostoGlobal){
					if(d.getId().equals(dto.getTipocosto().getId())){
		    			tipocosto.setSelectedIndex(row);
		    			break;
		    		}
		    		row++;
		    	}
			}
			
			if(a=="eliminar"){
				datePicker.setVisible(false);
				observacion.setEnabled(false);
				pesos.setEnabled(false);
				dolares.setEnabled(false);
				desastre.setEnabled(false);
				tipocosto.setEnabled(false);
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
			CostoDTO dto=validar();
			if(dto!=null){
				dto.setId(id);
				if(a=="modificar"){
					
					IDesastreAsync servidorDesastre=GWT.create(IDesastre.class);
					
					servidorDesastre.modificarCosto(dto,new AsyncCallback<Void>() {
						
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
					
					servidorDesastre.nuevoCosto(dto,new AsyncCallback<Void>() {
						
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
					
					servidorDesastre.eliminarCosto(dto,new AsyncCallback<Void>() {
						
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

		private CostoDTO validar() {
			// TODO Auto-generated method stub
			CostoDTO dto= new CostoDTO();
			
			pesos.setEnabled(false);
			desastre.setEnabled(false);
			tipocosto.setEnabled(false);
			
			if(desastre.getSelectedIndex()<1){
				Window.alert("Indique desastre");
				return null;
			}
			
			if(tipocosto.getSelectedIndex()<1){
				Window.alert("Indique tipo costo");
				return null;
			}

			if(pesos.getText()==null ||pesos.getText().trim().length()==0){
				Window.alert("Indique pesos");
				return null;
			}
			
			if(dolares.getText()==null ||dolares.getText().trim().length()==0){
				Window.alert("Indique pesos");
				return null;
			}
			
			dto.setFecha(datePicker.getValue());
			dto.setCantidadDolares(Float.valueOf(dolares.getText()));
			dto.setCantidadPesos(Float.valueOf(pesos.getText()));
			dto.setObservaciones(observacion.getText());
			
			for(DesastreDTO d:desastresGlobal){
				if(d.getId().equals(Long.valueOf(desastre.getValue(desastre.getSelectedIndex())))){
					dto.setDesastre(d);
					break;
				}
			}
			
			for(TipoCostoDTO d:tipocostoGlobal){
				if(d.getId().equals(Long.valueOf(tipocosto.getValue(tipocosto.getSelectedIndex())))){
					dto.setTipocosto(d);
					break;
				}
			}
			
			return dto;
			
		}
	}

}
