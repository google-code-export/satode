package fing.satode.ui.indices.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.util.collect.HashMap;
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
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.sun.corba.se.spi.activation.Server;

import fing.satode.constantes.ItemConstante;
import fing.satode.constantes.TipoIndice;
import fing.satode.data.CalculoIndiceDTO;
import fing.satode.data.CostoDTO;
import fing.satode.data.DesastreDTO;
import fing.satode.data.TipoCostoDTO;
import fing.satode.data.UsuarioDTO;
import fing.satode.ui.desastres.client.IDesastre;
import fing.satode.ui.desastres.client.IDesastreAsync;
import fing.satode.ui.usuarios.client.IUsuario;
import fing.satode.ui.usuarios.client.IUsuarioAsync;

public class CalculoIndiceList implements EntryPoint {

	final Button nuevoIDL = new Button("Nuevo IDL");
	final Button nuevoIGR = new Button("Nuevo IGR");
	final Button buscarB = new Button("Buscar");
	final VerticalPanel vertical = new VerticalPanel();
	final HorizontalPanel horizontalBotonera = new HorizontalPanel();
	private ArrayList<CalculoIndiceDTO> calculoIndicesGlobal;
	
	private Grid calculoIndiceGrid;
	private UsuarioDTO usuarioGlobal;
	final Label modificarLabel= new Label("Modificar");
	final Label eliminarLabel= new Label("Eliminar");
	final ListBox tipoFiltro= new ListBox();
	final Button graficarIDL= new Button("Graficar IDL");
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub

		horizontalBotonera.add(new Label("Tipo"));
		horizontalBotonera.add(tipoFiltro);
		horizontalBotonera.add(buscarB);
		horizontalBotonera.add(graficarIDL);
		horizontalBotonera.add(nuevoIDL);
		horizontalBotonera.add(nuevoIGR);
		
		RootPanel.get("botones").add(horizontalBotonera);
		
		graficarIDL.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				IIndicesAsync serverIndice=GWT.create(IIndices.class);
				
				serverIndice.exportarGraficosIDL(new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						Window.alert("ERROR AJAX");
					}
				});
				
			}
		});
		
		nuevoIDL.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				FormDialogIDLBox dialog= new FormDialogIDLBox(0L, "nuevo");
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

		tipoFiltro.addItem("Seleccionar","0");
		for(ItemConstante item:TipoIndice.getItems()){
			tipoFiltro.addItem(item.getTexto(),String.valueOf(item.getCodigo()));	
		}
		
		IUsuarioAsync serverUsuario=GWT.create(IUsuario.class);
		
		serverUsuario.getUsuarioLogin(new AsyncCallback<UsuarioDTO>() {
			
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
	
	private void cargarLista() {
		// TODO Auto-generated method stub
		RootPanel.get("indices").clear();
		vertical.clear();
		RootPanel.get("indices").add(vertical);
		
		IIndicesAsync serverIndice=GWT.create(IIndices.class);
		
		int tipo=Integer.valueOf(tipoFiltro.getValue(tipoFiltro.getSelectedIndex()));
		
		serverIndice.buscarCalculoIndice(tipo, new AsyncCallback<ArrayList<CalculoIndiceDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<CalculoIndiceDTO> result) {
				// TODO Auto-generated method stub
				calculoIndicesGlobal=result;
				calculoIndiceGrid = new Grid(calculoIndicesGlobal.size()+1,5);
				calculoIndiceGrid.setWidget(0, 0, new Label("ID"));
				calculoIndiceGrid.setWidget(0, 1, new Label("Tipo"));
				calculoIndiceGrid.setWidget(0, 2, new Label("Fecha"));
				calculoIndiceGrid.setWidget(0, 3, new Label("Observacion"));
				calculoIndiceGrid.setWidget(0, 4, new Label("Valor"));
				
				for(int i=0;i<5;i++){
					calculoIndiceGrid.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				
				calculoIndiceGrid.setBorderWidth(1);
				int row=1;
				DateTimeFormat format = DateTimeFormat.getFormat("dd/MM/yyyy");
				for(CalculoIndiceDTO e: result){
					calculoIndiceGrid.setWidget(row, 0, new Label(e.getId().toString()));
					calculoIndiceGrid.setWidget(row, 1, new Label(TipoIndice.getTXT(e.getTipo())));
					calculoIndiceGrid.setWidget(row, 2, new Label(format.format(e.getFecha())));
					calculoIndiceGrid.setWidget(row, 3, new Label(e.getObservaciones()));
					calculoIndiceGrid.setWidget(row, 4, new Label(String.valueOf(e.getValor())));
				
					final Long id= e.getId();
					row++;
				}
				
				vertical.add(calculoIndiceGrid);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
		

	
	}

	public class FormDialogIDLBox extends DialogBox{
		private String a;
		private Long id;
		final HorizontalPanel horizontal= new HorizontalPanel();
		final VerticalPanel vertical= new VerticalPanel();
		final CaptionPanel captionPrincipal=new CaptionPanel("Nuevo calculo de indice");
		final Label label = new Label();
	    final Grid grid= new Grid(5,2);
	    final Label fecha = new Label();
	    final TextBox observacion=new TextBox();
	    final TextBox valVivSocial=new TextBox();
	    final TextBox valHectaria=new TextBox();
	    final ListBox tipo=new ListBox();
	    final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Calcular");
		
	
	    public FormDialogIDLBox(Long idDesastre, String accion) {
			// TODO Auto-generated constructor stub
	    	a=accion;
	    	id=idDesastre;
	    	
	 	    
	 	    DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
	        String dateString = format.format(new Date());
	        fecha.setText(dateString);
	        
		    
	    	grid.setWidget(0, 0, new Label("Fecha"));
	    	grid.setWidget(0, 1, fecha);
	    	
	    	grid.setWidget(1, 0, new Label("Tipo"));
	    	grid.setWidget(1, 1, tipo);
	    	
	    	grid.setWidget(2, 0, new Label("Observaciones"));
	    	grid.setWidget(2, 1, observacion);

	    	grid.setWidget(3, 0, new Label("Valor Vivienda Social"));
	    	grid.setWidget(3, 1, valVivSocial);

	    	grid.setWidget(4, 0, new Label("Valor Hectaria de cultivos"));
	    	grid.setWidget(4, 1, valHectaria);

	    	tipo.addItem(TipoIndice.getTXT(TipoIndice.IDL),String.valueOf(TipoIndice.IDL));
	    	tipo.setEnabled(false);
	    	
	    	grid.setBorderWidth(1);
	    	
			captionPrincipal.add(vertical);
			vertical.add(grid);
	    	horizontal.add(aceptar);
			horizontal.add(cancelar);
			vertical.add(horizontal);
			
			cancelar.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					FormDialogIDLBox.this.hide();
				}
			});
			
			aceptar.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					FormDialogIDLBox.this.procesar();
				}
			});
				
			
			
			setAnimationEnabled(true);
			add(captionPrincipal);
			center();
		}

		protected void procesar() {
			// TODO Auto-generated method stub
			CalculoIndiceDTO dto=validar();
			if(dto!=null){
				dto.setId(id);
				dto.setUsuario(usuarioGlobal);
				IIndicesAsync serverIndice=GWT.create(IIndices.class);
				serverIndice.calcularIDL(dto,new AsyncCallback<Void>() {
					
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

		private CalculoIndiceDTO validar() {
			// TODO Auto-generated method stub
			CalculoIndiceDTO dto= new CalculoIndiceDTO();
			
			if(! (getFloat(valVivSocial.getText())>0)){
				Window.alert("Indique el valor de vivienda social");
				return null;
			}
			
			if(! (getFloat(valHectaria.getText())>0)){
				Window.alert("Indique el valor de una hectaria de cultivo");
				return null;
			}
			
			dto.setFecha(new Date());
			dto.setObservaciones(observacion.getText());
			dto.setTipo(Integer.valueOf(tipo.getValue(tipo.getSelectedIndex())));
			dto.setHectariaDeCultivo(getFloat(valHectaria.getText()));
			dto.setValorVivindaSocial(getFloat(valVivSocial.getText()));
			
			return dto;
			
		}
		
		private Float getFloat(String text) {
			// TODO Auto-generated method stub
			if(text==null || text.trim().length()==0){
				return 0F;
			}
			return Float.valueOf(text);
		}
	}
}
