package fing.satode.ui.deposito.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import fing.satode.data.CiudadDTO;
import fing.satode.data.DepartamentoDTO;
import fing.satode.data.DepositoDTO;
import fing.satode.data.DesastreDTO;
import fing.satode.data.EventoDTO;
import fing.satode.data.UsuarioDTO;
import fing.satode.ui.desastres.client.IDesastre;
import fing.satode.ui.desastres.client.IDesastreAsync;
import fing.satode.ui.general.client.IBasicos;
import fing.satode.ui.general.client.IBasicosAsync;
import fing.satode.ui.general.data.KeyNumeric;
import fing.satode.ui.registros.client.IEvento;
import fing.satode.ui.registros.client.IEventoAsync;
import fing.satode.ui.usuarios.client.IUsuario;
import fing.satode.ui.usuarios.client.IUsuarioAsync;

public class EntryPointDeposito implements EntryPoint {

	final Button nuevoB = new Button("Nuevo");
	final VerticalPanel vertical = new VerticalPanel();
	private ArrayList<DepositoDTO> depositoGlobal;
	private ArrayList<DepartamentoDTO> departamentosGlobal;
	private Grid depositos;
	final Label modificarLabel= new Label("Modificar");
	final Label eliminarLabel= new Label("Eliminar");
	
	@Override
	public void onModuleLoad() {
		
		RootPanel.get("botones").add(nuevoB);
		nuevoB.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				FormDialogBox dialog= new FormDialogBox(0L, "nuevo");
				dialog.show();
			}
		});
		
		cargarLista();
	}
	
	private void cargarLista() {
		RootPanel.get("depositos").clear();
		vertical.clear();
		RootPanel.get("depositos").add(vertical);
		
		IDepositoAsync servidorDepositos= GWT.create(IDeposito.class);
		
		servidorDepositos.listaDepositos(new AsyncCallback<ArrayList<DepositoDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<DepositoDTO> result) {
				depositoGlobal=result;
				depositos = new Grid(result.size()+1,10);
				depositos.setWidget(0, 0, new Label("ID"));
				depositos.setWidget(0, 1, new Label("Responsable"));
				depositos.setWidget(0, 2, new Label("Ciudad"));
				depositos.setWidget(0, 3, new Label("Direcci\u00F3n"));
				depositos.setWidget(0, 4, new Label("Telefono"));
				depositos.setWidget(0, 5, new Label("Mail"));
				depositos.setWidget(0, 6, new Label("Area en m2"));
				depositos.setWidget(0, 7, new Label("Area en m3"));
				depositos.setWidget(0, 8, modificarLabel);
				depositos.setWidget(0, 9, eliminarLabel);
				
				for(int i=0;i<10;i++){
					depositos.getCellFormatter().setStyleName(0,i, "tbl-cab");
				}
				
				depositos.setBorderWidth(1);
				int row=1;
				for(DepositoDTO e: result){
					depositos.setWidget(row, 0, new Label(e.getId().toString()));
					depositos.setWidget(row, 1, new Label(e.getResponsable()));
					depositos.setWidget(row, 2, new Label(e.getCiudad().getNombre()));
					depositos.setWidget(row, 3, new Label(e.getDireccion()));
					depositos.setWidget(row, 4, new Label(e.getTelefono()));
					depositos.setWidget(row, 5, new Label(e.getMail()));
					depositos.setWidget(row, 6, new Label(String.valueOf(e.getArea2())));
					depositos.setWidget(row, 7, new Label(String.valueOf(e.getArea3())));
					final Long id= e.getId();
					final Image modificarI= new Image("/images/modificar.png");
					modificarI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							FormDialogBox dialog= new FormDialogBox(id, "modificar");
							dialog.show();
						}
					});
					
					final Image eliminarI= new Image("/images/eliminar.png");
					
					eliminarI.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							FormDialogBox dialog= new FormDialogBox(id, "eliminar");
							dialog.show();
						}
					});
					depositos.setWidget(row, 8, modificarI);
					depositos.setWidget(row, 9, eliminarI);
					row++;
				}
				vertical.add(depositos);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("ERROR AJAX");
			}
		});
		

		IBasicosAsync servidorBasicos = GWT.create(IBasicos.class);
		
		servidorBasicos.listaDepartamentos(new AsyncCallback<ArrayList<DepartamentoDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<DepartamentoDTO> result) {
				departamentosGlobal=result;
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
		final CaptionPanel panelPrincipal = new CaptionPanel();
		final Grid grid= new Grid(8,2);
	    final TextBox direccion=new TextBox();
	    final TextBox telefono=new TextBox();
	    final TextBox mail=new TextBox();
	    final TextBox area2=new TextBox();
	    final TextBox area3=new TextBox();
	    final TextBox responsable=new TextBox();
	    final ListBox departamentos= new ListBox();
	    final ListBox ciudades= new ListBox();
	    final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Aceptar");
	
	    @SuppressWarnings("deprecation")
		public FormDialogBox(Long idDeposito, String accion) {
			a=accion;
	    	id=idDeposito;
	    	
	    	if(a=="modificar") panelPrincipal.setCaptionText("Modificar Deposito");
			if(a=="eliminar") panelPrincipal.setCaptionText("Eliminar Deposito");
			if(a=="nuevo") panelPrincipal.setCaptionText("Nuevo Deposito");
			
			grid.setWidget(0, 0, new Label("Responsable"));
			grid.setWidget(1, 0, new Label("Departamento"));
			grid.setWidget(2, 0, new Label("Ciudad"));
			grid.setWidget(3, 0, new Label("Direcci\u00F3n"));
			grid.setWidget(4, 0, new Label("Telefono"));
			grid.setWidget(5, 0, new Label("Mail"));
			grid.setWidget(6, 0, new Label("Area en m2"));
			grid.setWidget(7, 0, new Label("Area en m3"));
			
			grid.setWidget(0, 1, responsable);
			grid.setWidget(1, 1, departamentos);
			grid.setWidget(2, 1, ciudades);
			grid.setWidget(3, 1, direccion);
			grid.setWidget(4, 1, telefono);
			grid.setWidget(5, 1, mail);
			grid.setWidget(6, 1, area2);
			grid.setWidget(7, 1, area3);
	    	grid.setBorderWidth(1);
	    	
	        departamentos.addItem("Seleccionar","0");
		    for(DepartamentoDTO d:departamentosGlobal){
		    	departamentos.addItem(d.getNombre(), d.getId().toString());
		    }
		    departamentos.addChangeHandler(new ChangeHandler() {
				
				@Override
				public void onChange(ChangeEvent event) {
					ciudades.clear();
					Long id=Long.valueOf(departamentos.getValue(departamentos.getSelectedIndex()));
					for(DepartamentoDTO dto:departamentosGlobal){
						if(dto.getId().equals(id)){
							ciudades.addItem("Seleccionar","0");
							for(CiudadDTO c:dto.getCiudades()){
								ciudades.addItem(c.getNombre(),c.getId().toString());
							}
						}
					}
				}
			});
		    
		    area2.addKeyboardListener(new KeyNumeric());
		    area3.addKeyboardListener(new KeyNumeric());
		    
		    if (a == "modificar" || a == "eliminar" ){
		    	
		    	DepositoDTO depositoDTO= null;
	    		for(DepositoDTO d:depositoGlobal){
	    			if(d.getId().equals(id)){
	    				depositoDTO=d;
	    			}
	    		}
	    		
		    	
		    	direccion.setText(depositoDTO.getDireccion());
			    telefono.setText(depositoDTO.getTelefono());
			    mail.setText(depositoDTO.getMail());
			    area2.setText(String.valueOf(depositoDTO.getArea2()));
			    area3.setText(String.valueOf(depositoDTO.getArea3()));
			    responsable.setText(depositoDTO.getResponsable());
			    
			    int row=0;
			    for(DepartamentoDTO d:departamentosGlobal){
			    	row++;
				    if(d.getId().equals(depositoDTO.getDepartamento().getId())){
			    		departamentos.setSelectedIndex(row);
			    		ciudades.clear();
			    		ciudades.addItem("Seleccionar","0");
			    		int row2=1;
						for(CiudadDTO c:d.getCiudades()){
							ciudades.addItem(c.getNombre(),c.getId().toString());
							if(c.getId().equals(depositoDTO.getCiudad().getId())){
								ciudades.setSelectedIndex(row2);
							}
							row2++;
						}
				    }
			    }
			   
		    }
		    
		    if ( a == "eliminar" ){
		    	direccion.setEnabled(false);
			    telefono.setEnabled(false);
			    mail.setEnabled(false);
			    area2.setEnabled(false);
			    area3.setEnabled(false);
			    responsable.setEnabled(false);
			    departamentos.setEnabled(false);
			    ciudades.setEnabled(false);
			    
		    }
			//vertical.add(label);
		    panelPrincipal.add(vertical);
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
					FormDialogBox.this.procesar();
				}
			});
				
			setAnimationEnabled(true);
			add(panelPrincipal);
			center();
		}

		protected void procesar() {
			DepositoDTO dto=validar();
			if(dto!=null){
				dto.setId(id);
				if(a=="modificar"){
					
					IDepositoAsync servidorDeposito=GWT.create(IDeposito.class);
					
					servidorDeposito.modificarDeposito(dto,new AsyncCallback<Void>() {
						
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
				}else if(a== "nuevo"){
					IDepositoAsync servidorDeposito=GWT.create(IDeposito.class);
					
					servidorDeposito.nuevoDeposito(dto,new AsyncCallback<Void>() {
						
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
				}else if(a=="eliminar"){
					IDepositoAsync servidorDeposito=GWT.create(IDeposito.class);
					
					servidorDeposito.eliminarDeposito(dto,new AsyncCallback<Void>() {
						
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

		private DepositoDTO validar() {
			DepositoDTO dto= new DepositoDTO();
			
			if(responsable.getText().trim().length()==0){
				Window.alert("Indique responsable");
				return null;
			}
			dto.setResponsable(responsable.getText());
			
			if(departamentos.getSelectedIndex()<1){
				Window.alert("Indique departamento");
				return null;
			}
			
			Long idDep=Long.valueOf(departamentos.getValue(departamentos.getSelectedIndex()));
			for(DepartamentoDTO d:departamentosGlobal){
				if(idDep.equals(d.getId())){
					dto.setDepartamento(d);
				}
			}
			
			if(ciudades.getSelectedIndex()<1){
				Window.alert("Indique ciudad");
				return null;
			}
			
			Long idCiu=Long.valueOf(ciudades.getValue(ciudades.getSelectedIndex()));
			for(CiudadDTO c:dto.getDepartamento().getCiudades()){
				if(idCiu.equals(c.getId())){
					dto.setCiudad(c);
				}
			}
			
			dto.setDireccion(direccion.getText());
			dto.setTelefono(telefono.getText());
			dto.setMail(mail.getText());
			dto.setArea2(getFloat(area2.getText()));
			dto.setArea3(getFloat(area3.getText()));
			
			return dto;
			
		}
		
		private Float getFloat(String text) {
			if(text==null || text.trim().length()==0){
				return 0F;
			}
			return Float.valueOf(text);
		}
	}

}
