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

import fing.satode.constantes.IR1;
import fing.satode.constantes.IR2;
import fing.satode.constantes.IR3;
import fing.satode.constantes.IR4;
import fing.satode.constantes.IR5;
import fing.satode.constantes.IR6;
import fing.satode.constantes.ItemConstante;
import fing.satode.constantes.MD1;
import fing.satode.constantes.MD2;
import fing.satode.constantes.MD3;
import fing.satode.constantes.MD4;
import fing.satode.constantes.MD5;
import fing.satode.constantes.MD6;
import fing.satode.constantes.PF1;
import fing.satode.constantes.PF2;
import fing.satode.constantes.PF3;
import fing.satode.constantes.PF4;
import fing.satode.constantes.PF5;
import fing.satode.constantes.PF6;
import fing.satode.constantes.RR1;
import fing.satode.constantes.RR2;
import fing.satode.constantes.RR3;
import fing.satode.constantes.RR4;
import fing.satode.constantes.RR5;
import fing.satode.constantes.RR6;
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

public class EntryPointCalculoIndiceList implements EntryPoint {

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
	final Button graficarIGR= new Button("Graficar IGR");
	
	@Override
	public void onModuleLoad() {
		
		horizontalBotonera.add(new Label("Tipo"));
		horizontalBotonera.add(tipoFiltro);
		horizontalBotonera.add(buscarB);
		horizontalBotonera.add(nuevoIDL);
		horizontalBotonera.add(graficarIDL);
		horizontalBotonera.add(nuevoIGR);
		horizontalBotonera.add(graficarIGR);
		
		RootPanel.get("botones").add(horizontalBotonera);
		
		graficarIDL.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Window.open("/gaficaidl.gidl.pdf", "_blank", null);
			}
		});
		
		graficarIGR.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Window.open("/gaficaigr.gigr.pdf", "_blank", null);
			}
		});
		

		nuevoIDL.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				FormDialogIDLBox dialog= new FormDialogIDLBox(0L, "nuevo");
				dialog.show();
			}
		});
		
		nuevoIGR.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				FormDialogIGRBox dialog= new FormDialogIGRBox();
				dialog.show();
			}
		});
		
		buscarB.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
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
		RootPanel.get("indices").clear();
		vertical.clear();
		RootPanel.get("indices").add(vertical);
		
		IIndicesAsync serverIndice=GWT.create(IIndices.class);
		
		int tipo=Integer.valueOf(tipoFiltro.getValue(tipoFiltro.getSelectedIndex()));
		
		serverIndice.buscarCalculoIndice(tipo, new AsyncCallback<ArrayList<CalculoIndiceDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<CalculoIndiceDTO> result) {
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
					FormDialogIDLBox.this.procesar();
				}
			});
				
			
			
			setAnimationEnabled(true);
			add(captionPrincipal);
			center();
		}

		protected void procesar() {
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
			if(text==null || text.trim().length()==0){
				return 0F;
			}
			return Float.valueOf(text);
		}
	}
	
	
	public class FormDialogIGRBox extends DialogBox{
		
		final HorizontalPanel horizontal= new HorizontalPanel();
		final VerticalPanel vertical= new VerticalPanel();
		final CaptionPanel captionPrincipal=new CaptionPanel("Nuevo calculo de IGR");
		final Grid grid= new Grid(3,2);
	    final Label fecha = new Label();
	    final TextBox observacion =new TextBox();
	    final ListBox tipo=new ListBox();
	    final Button cancelar= new Button("Cancelar");
		final Button aceptar= new Button("Calcular");
		final CaptionPanel captionIR=new CaptionPanel("Indicadores de identificaci\u00F3n del Riesgo");
		final CaptionPanel captionRR=new CaptionPanel("Indicadores de reducci\u00F3n del riesgo");
		final CaptionPanel captionMD=new CaptionPanel("Indicadores de manejo de desastres");
		final CaptionPanel captionPF=new CaptionPanel("Indicadores de gobernabilidad y protecci\u00F3n financiera");
		final VerticalPanel verticalIR= new VerticalPanel();
		final VerticalPanel verticalRR= new VerticalPanel();
		final VerticalPanel verticalMD= new VerticalPanel();
		final VerticalPanel verticalPF= new VerticalPanel();
		
		
		final ListBox ir1 = new ListBox();
		final ListBox ir2 = new ListBox();
		final ListBox ir3 = new ListBox();
		final ListBox ir4 = new ListBox();
		final ListBox ir5 = new ListBox();
		final ListBox ir6 = new ListBox();
		final ListBox md1 = new ListBox();
		final ListBox md2 = new ListBox();
		final ListBox md3 = new ListBox();
		final ListBox md4 = new ListBox();
		final ListBox md5 = new ListBox();
		final ListBox md6 = new ListBox();
		final ListBox pf1 = new ListBox();
		final ListBox pf2 = new ListBox();
		final ListBox pf3 = new ListBox();
		final ListBox pf4 = new ListBox();
		final ListBox pf5 = new ListBox();
		final ListBox pf6 = new ListBox();
		final ListBox rr1 = new ListBox();
		final ListBox rr2 = new ListBox();
		final ListBox rr3 = new ListBox();
		final ListBox rr4 = new ListBox();
		final ListBox rr5 = new ListBox();
		final ListBox rr6 = new ListBox();
		
	
	    public FormDialogIGRBox() {
			
	    	
	 	    
	 	    DateTimeFormat format=DateTimeFormat.getFormat("dd/MM/yyyy");
	        String dateString = format.format(new Date());
	        fecha.setText(dateString);
	        
		    
	    	grid.setWidget(0, 0, new Label("Fecha"));
	    	grid.setWidget(0, 1, fecha);
	    	
	    	grid.setWidget(1, 0, new Label("Tipo"));
	    	grid.setWidget(1, 1, tipo);
	    	
	    	grid.setWidget(2, 0, new Label("Observaciones"));
	    	grid.setWidget(2, 1, observacion);

	    	
	    	tipo.addItem(TipoIndice.getTXT(TipoIndice.IGR),String.valueOf(TipoIndice.IGR));
	    	tipo.setEnabled(false);
	    	
	    	ir1.addItem("Seleccionar", "0");
			for (ItemConstante i : IR1.getItems()) {
				ir1.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
	    	
	    	ir2.addItem("Seleccionar", "0");
			for (ItemConstante i : IR2.getItems()) {
				ir2.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
	    	ir3.addItem("Seleccionar", "0");
			for (ItemConstante i : IR3.getItems()) {
				ir3.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
	    	ir4.addItem("Seleccionar", "0");
			for (ItemConstante i : IR4.getItems()) {
				ir4.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
	    	ir5.addItem("Seleccionar", "0");
			for (ItemConstante i : IR5.getItems()) {
				ir5.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
	    	ir6.addItem("Seleccionar", "0");
			for (ItemConstante i : IR6.getItems()) {
				ir6.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
	    	md1.addItem("Seleccionar", "0");
			for (ItemConstante i : MD1.getItems()) {
				md1.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			md2.addItem("Seleccionar", "0");
			for (ItemConstante i : MD2.getItems()) {
				md2.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			md3.addItem("Seleccionar", "0");
			for (ItemConstante i : MD3.getItems()) {
				md3.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			md4.addItem("Seleccionar", "0");
			for (ItemConstante i : MD4.getItems()) {
				md4.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			md5.addItem("Seleccionar", "0");
			for (ItemConstante i : MD5.getItems()) {
				md5.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			md6.addItem("Seleccionar", "0");
			for (ItemConstante i : MD6.getItems()) {
				md6.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			pf1.addItem("Seleccionar", "0");
			for (ItemConstante i : PF1.getItems()) {
				pf1.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			pf2.addItem("Seleccionar", "0");
			for (ItemConstante i : PF2.getItems()) {
				pf2.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			pf3.addItem("Seleccionar", "0");
			for (ItemConstante i : PF3.getItems()) {
				pf3.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			pf4.addItem("Seleccionar", "0");
			for (ItemConstante i : PF4.getItems()) {
				pf4.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			pf5.addItem("Seleccionar", "0");
			for (ItemConstante i : PF5.getItems()) {
				pf5.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			pf6.addItem("Seleccionar", "0");
			for (ItemConstante i : PF6.getItems()) {
				pf6.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			rr1.addItem("Seleccionar", "0");
			for (ItemConstante i : RR1.getItems()) {
				rr1.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			rr2.addItem("Seleccionar", "0");
			for (ItemConstante i : RR2.getItems()) {
				rr2.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			rr3.addItem("Seleccionar", "0");
			for (ItemConstante i : RR3.getItems()) {
				rr3.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			rr4.addItem("Seleccionar", "0");
			for (ItemConstante i : RR4.getItems()) {
				rr4.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			rr5.addItem("Seleccionar", "0");
			for (ItemConstante i : RR5.getItems()) {
				rr5.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
			
			rr6.addItem("Seleccionar", "0");
			for (ItemConstante i : RR6.getItems()) {
				rr6.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}
	    	grid.setBorderWidth(1);
	    	
			captionPrincipal.add(vertical);
			vertical.add(grid);
			vertical.add(captionIR);
			verticalIR.add(ir1);
			verticalIR.add(ir2);
			verticalIR.add(ir3);
			verticalIR.add(ir4);
			verticalIR.add(ir5);
			verticalIR.add(ir6);
			captionIR.add(verticalIR);
			vertical.add(captionRR);
			verticalRR.add(rr1);
			verticalRR.add(rr2);
			verticalRR.add(rr3);
			verticalRR.add(rr4);
			verticalRR.add(rr5);
			verticalRR.add(rr6);
			captionRR.add(verticalRR);
			vertical.add(captionMD);
			verticalMD.add(md1);
			verticalMD.add(md2);
			verticalMD.add(md3);
			verticalMD.add(md4);
			verticalMD.add(md5);
			verticalMD.add(md6);
			captionMD.add(verticalMD);
			vertical.add(captionPF);
			verticalPF.add(pf1);
			verticalPF.add(pf2);
			verticalPF.add(pf3);
			verticalPF.add(pf4);
			verticalPF.add(pf5);
			verticalPF.add(pf6);
			captionPF.add(verticalPF);
			
	    	horizontal.add(aceptar);
			horizontal.add(cancelar);
			vertical.add(horizontal);
			
			cancelar.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					FormDialogIGRBox.this.hide();
				}
			});
			
			aceptar.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					FormDialogIGRBox.this.procesar();
				}
			});
				
			
			
			setAnimationEnabled(true);
			add(captionPrincipal);
			center();
		}

		protected void procesar() {
			CalculoIndiceDTO dto=validar();
			if(dto!=null){
				dto.setUsuario(usuarioGlobal);
				IIndicesAsync serverIndice=GWT.create(IIndices.class);
				serverIndice.calcularIGR(dto,new AsyncCallback<Void>() {
					
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
			CalculoIndiceDTO dto= new CalculoIndiceDTO();
			
			
			
			if(ir1.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 1 de identificaci\u00F3n del Riesgo"); 
				  return null;
			}
			dto.setIr1(Integer.valueOf(ir1.getValue(ir1.getSelectedIndex())));
			
			if(ir2.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 2 de identificaci\u00F3n del Riesgo"); 
				  return null;
			}
			dto.setIr2(Integer.valueOf(ir2.getValue(ir2.getSelectedIndex())));
			
			if(ir3.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 3 de identificaci\u00F3n del Riesgo"); 
				  return null;
			}
			dto.setIr3(Integer.valueOf(ir3.getValue(ir3.getSelectedIndex())));
			
			if(ir4.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 4 de identificaci\u00F3n del Riesgo"); 
				  return null;
			}
			dto.setIr4(Integer.valueOf(ir4.getValue(ir4.getSelectedIndex())));
			
			if(ir5.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 5 de identificaci\u00F3n del Riesgo"); 
				  return null;
			}
			dto.setIr5(Integer.valueOf(ir5.getValue(ir5.getSelectedIndex())));
			
			if(ir6.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 6 de identificaci\u00F3n del Riesgo"); 
				  return null;
			}
			dto.setIr6(Integer.valueOf(ir6.getValue(ir6.getSelectedIndex())));
			
			if(rr1.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 1 de reducci\u00F3n del riesgo"); 
				  return null;
			}
			dto.setRr1(Integer.valueOf(rr1.getValue(rr1.getSelectedIndex())));
			
			if(rr2.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 2 de reducci\u00F3n del riesgo"); 
				  return null;
			}
			dto.setRr2(Integer.valueOf(rr2.getValue(rr2.getSelectedIndex())));
			
			if(rr3.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 3 de reducci\u00F3n del riesgo"); 
				  return null;
			}
			dto.setRr3(Integer.valueOf(rr3.getValue(rr3.getSelectedIndex())));
			
			if(rr4.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 4 de reducci\u00F3n del riesgo"); 
				  return null;
			}
			dto.setRr4(Integer.valueOf(rr4.getValue(rr4.getSelectedIndex())));
			
			if(rr5.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 5 de reducci\u00F3n del riesgo"); 
				  return null;
			}
			dto.setRr5(Integer.valueOf(rr5.getValue(rr5.getSelectedIndex())));
			
			if(rr6.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 6 de reducci\u00F3n del riesgo"); 
				  return null;
			}
			dto.setRr6(Integer.valueOf(rr6.getValue(rr6.getSelectedIndex())));
			
			if(md1.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 1 de manejo de desastres"); 
				  return null;
			}
			dto.setMd1(Integer.valueOf(md1.getValue(md1.getSelectedIndex())));
			
			if(md2.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 2 de manejo de desastres"); 
				  return null;
			}
			dto.setMd2(Integer.valueOf(md2.getValue(md2.getSelectedIndex())));
			
			if(md3.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 3 de manejo de desastres"); 
				  return null;
			}
			dto.setMd3(Integer.valueOf(md3.getValue(md3.getSelectedIndex())));
			
			if(md4.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 4 de manejo de desastres"); 
				  return null;
			}
			dto.setMd4(Integer.valueOf(md4.getValue(md4.getSelectedIndex())));
			
			if(md5.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 5 de manejo de desastres"); 
				  return null;
			}
			dto.setMd5(Integer.valueOf(md5.getValue(md5.getSelectedIndex())));
			
			if(md6.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 6 de manejo de desastres"); 
				  return null;
			}
			dto.setMd6(Integer.valueOf(md6.getValue(md6.getSelectedIndex())));
			
			if(pf1.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 1 de gobernabilidad y protecci\u00F3n financiera"); 
				  return null;
			}
			dto.setPf1(Integer.valueOf(pf1.getValue(pf1.getSelectedIndex())));
			
			if(pf2.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 2 de gobernabilidad y protecci\u00F3n financiera"); 
				  return null;
			}
			dto.setPf2(Integer.valueOf(pf2.getValue(pf2.getSelectedIndex())));
			
			if(pf3.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 3 de gobernabilidad y protecci\u00F3n financiera"); 
				  return null;
			}
			dto.setPf3(Integer.valueOf(pf3.getValue(pf3.getSelectedIndex())));
			
			if(pf4.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 4 de gobernabilidad y protecci\u00F3n financiera"); 
				  return null;
			}
			dto.setPf4(Integer.valueOf(pf4.getValue(pf4.getSelectedIndex())));
			
			if(pf5.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 5 de gobernabilidad y protecci\u00F3n financiera"); 
				  return null;
			}
			dto.setPf5(Integer.valueOf(pf5.getValue(pf5.getSelectedIndex())));
			
			if(pf6.getSelectedIndex()<1){
				  Window.alert("Indique Indicador 6 de gobernabilidad y protecci\u00F3n financiera"); 
				  return null;
			}
			dto.setPf6(Integer.valueOf(pf6.getValue(pf6.getSelectedIndex())));
			 
			
			dto.setFecha(new Date());
			dto.setObservaciones(observacion.getText());
			dto.setTipo(Integer.valueOf(tipo.getValue(tipo.getSelectedIndex())));
			
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
