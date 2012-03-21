package fing.satode.ui.propiedadesSiniestradas.client;

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
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import fing.satode.com.reveregroup.carousel.client.Carousel;
import fing.satode.com.reveregroup.carousel.client.Photo;
import fing.satode.constantes.AguaEnVivienda;
import fing.satode.constantes.AlojamientoInundacion;
import fing.satode.constantes.Banios;
import fing.satode.constantes.Basura;
import fing.satode.constantes.CategoriaVivienda;
import fing.satode.constantes.ConservacionVivienda;
import fing.satode.constantes.Energia;
import fing.satode.constantes.EstadoPared;
import fing.satode.constantes.EstadoTerminacion;
import fing.satode.constantes.ItemConstante;
import fing.satode.constantes.MaterialParedes;
import fing.satode.constantes.MaterialPiso;
import fing.satode.constantes.MaterialTecho;
import fing.satode.constantes.Mudarse;
import fing.satode.constantes.NivelPiso;
import fing.satode.constantes.Ocupacion;
import fing.satode.constantes.PersepcionVivienda;
import fing.satode.constantes.ProcedenciaAguaConsumo;
import fing.satode.constantes.ProcedenciaAguaVivienda;
import fing.satode.constantes.PropietarioOtras;
import fing.satode.constantes.Sanitaria;
import fing.satode.constantes.TenenciaVivienda;
import fing.satode.data.CiudadDTO;
import fing.satode.data.DatosViviendaDTO;
import fing.satode.data.DepartamentoDTO;
import fing.satode.data.FotoDTO;
import fing.satode.data.HacinamientoDTO;
import fing.satode.data.InundacionDTO;
import fing.satode.data.ParcelaDTO;
import fing.satode.data.ProblemasViviendaDTO;
import fing.satode.data.TipoParcelaDTO;
import fing.satode.data.UnidadParcelaDTO;
import fing.satode.data.UsuarioDTO;
import fing.satode.ui.general.client.IBasicos;
import fing.satode.ui.general.client.IBasicosAsync;
import fing.satode.ui.general.data.KeyNumeric;
import fing.satode.ui.usuarios.client.IUsuario;
import fing.satode.ui.usuarios.client.IUsuarioAsync;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.MultiUploader;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;

public class EntryPropiedadesSiniestradas implements EntryPoint {

	final Button nuevoB = new Button("Nuevo");
	final VerticalPanel vertical = new VerticalPanel();
	private UsuarioDTO usuarioGlobal;
	private ArrayList<ParcelaDTO> parcelaGlobal;
	private ArrayList<FotoDTO> fotosBorradas;
	private ArrayList<Photo> photosAntes;
	private ArrayList<Photo> photosDespues;
	private ArrayList<DepartamentoDTO> departamentosGlobal;
	private Grid parcelas;
	public static ParcelaDTO parcelaDTO;
	public static Grid unidadesParcelas;
	public static Grid gridUnidadesParcelas= new Grid(1,2);
	private static Long numerador = 10000000000L;
	private static Long baseNumerador = 10000000000L;
	final Label modificarLabel = new Label("Modificar");
	final Label eliminarLabel = new Label("Eliminar");
	
	public static native void setWindowHref(String url)/*-{
	$wnd.location.href = url;
}-*/; 


	@Override
	public void onModuleLoad() {

		RootPanel.get("botones").add(nuevoB);
		nuevoB.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				FormDialogBox dialog = new FormDialogBox(0L, "nuevo");
				dialog.show();
			}
		});

		cargarLista();
	}

	private void cargarLista() {
		RootPanel.get("parcelas").clear();
		vertical.clear();
		RootPanel.get("parcelas").add(vertical);
		
		IUsuarioAsync servidorUsuario= GWT.create(IUsuario.class);
		
		servidorUsuario.getUsuarioLogin(new AsyncCallback<UsuarioDTO>() {
			
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

		IPropiedadesSiniestradasAsync servidorPropiedadesSiniestradas = GWT
				.create(IPropiedadesSiniestradas.class);

		servidorPropiedadesSiniestradas
				.listaParcelas(new AsyncCallback<ArrayList<ParcelaDTO>>() {

					@Override
					public void onSuccess(ArrayList<ParcelaDTO> result) {
						parcelaGlobal = result;
						parcelas = new Grid(result.size() + 1, 9);
						parcelas.setWidget(0, 0, new Label("ID"));
						parcelas.setWidget(0, 1, new Label("Direcci\u00F3n"));
						parcelas.setWidget(0, 2, new Label("Ciudad"));
						parcelas.setWidget(0, 3, new Label("Departamento"));
						parcelas.setWidget(0, 4, new Label("Telefono"));
						parcelas.setWidget(0, 5, new Label("\u00BFVivienda?"));
						parcelas.setWidget(0, 6, new Label("Otros Usos"));
						parcelas.setWidget(0, 7, modificarLabel);
						parcelas.setWidget(0, 8, eliminarLabel);

						for (int i = 0; i < 9; i++) {
							parcelas.getCellFormatter().setStyleName(0, i,
									"tbl-cab");
						}

						parcelas.setBorderWidth(1);
						int row = 1;
						for (ParcelaDTO p : result) {
							parcelas.setWidget(row, 0, new Label(p.getId()
									.toString()));
							parcelas.setWidget(row, 1,
									new Label(p.getDireccion()));
							parcelas.setWidget(row, 2, new Label(p.getCiudad()
									.getNombre()));
							parcelas.setWidget(row, 3, new Label(p
									.getDepartamento().getNombre()));
							parcelas.setWidget(row, 4,
									new Label(p.getTelefono()));
							parcelas.setWidget(row, 5, new Label(p
									.getTipoParcela().isVivienda() ? "SI"
									: "NO"));
							parcelas.setWidget(row, 6, new Label(p
									.getTipoParcela().getOtrosUsos()));

							final Long id = p.getId();
							final Image modificarI = new Image(
									"/images/modificar.png");
							modificarI.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									FormDialogBox dialog = new FormDialogBox(
											id, "modificar");
									dialog.show();
								}
							});

							final Image eliminarI = new Image(
									"/images/eliminar.png");

							eliminarI.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									FormDialogBox dialog = new FormDialogBox(
											id, "eliminar");
									dialog.show();
								}
							});
							parcelas.setWidget(row, 7, modificarI);
							parcelas.setWidget(row, 8, eliminarI);
							row++;
						}
						vertical.add(parcelas);

					}

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						Window.alert("ERROR AJAX");
					}
				});

		IBasicosAsync servidorBasicos = GWT.create(IBasicos.class);

		servidorBasicos
				.listaDepartamentos(new AsyncCallback<ArrayList<DepartamentoDTO>>() {

					@Override
					public void onSuccess(ArrayList<DepartamentoDTO> result) {
						departamentosGlobal = result;
					}

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						Window.alert("ERROR AJAX");
					}
				});
	}

	public class FormDialogBox extends DialogBox {
		private String a;
		private Long id;
		private int indexFotoAntes= 0;
		private int indexFotoDespues= 0;
		private int cantFotosAntes = 0;
		private int cantFotosDespues = 0;
		
		final HorizontalPanel horizontal1 = new HorizontalPanel();
		final HorizontalPanel horizontal3 = new HorizontalPanel();
		final HorizontalPanel horizFotos = new HorizontalPanel();
		final CaptionPanel panelParcelas = new CaptionPanel("Datos de identificaci\u00F3n");
		final CaptionPanel panelDatosVivienda = new CaptionPanel("Datos de la vivienda");
		final CaptionPanel panelProblemasVivienda = new CaptionPanel("Problemas en la vivienda");
		final CaptionPanel panelInundacion = new CaptionPanel("Inundacion");
		final CaptionPanel panelHacinamineto = new CaptionPanel("Hacinamineto");
		final CaptionPanel panelUnidadesParcela = new CaptionPanel("Unidades de la parcela");
		final CaptionPanel panelFotosAntes = new CaptionPanel("Fotos antes de los arreglos");
		final CaptionPanel panelFotosDespues = new CaptionPanel("Fotos despues de los arreglos");
		final CaptionPanel panelFotos = new CaptionPanel("Fotos");
		final VerticalPanel vertical1 = new VerticalPanel();
		final VerticalPanel vertical2 = new VerticalPanel();
		final VerticalPanel vertical3 = new VerticalPanel();
		final VerticalPanel vertical = new VerticalPanel();
		final VerticalPanel verticalFoto = new VerticalPanel();
		final CaptionPanel panelTitulo = new CaptionPanel();
		final Grid gridIzqParcelas = new Grid(6, 2);
		final Grid gridIzqDatosViv = new Grid(23, 2);
		final Grid gridDerProblViv = new Grid(13, 2);
		final Grid gridDerHacin = new Grid(5, 2);
		final Grid gridDerInund = new Grid(13, 2);
		final TextBox direccion = new TextBox();
		final TextBox telefono = new TextBox();
		final ListBox vivienda = new ListBox();
		final TextBox otrosUsos = new TextBox();
		final ListBox departamentos = new ListBox();
		final ListBox ciudades = new ListBox();
		final Button cancelar = new Button("Cancelar");
		final Button aceptar = new Button("Aceptar");
		final Button nuevoB = new Button("Nuevo");
		
		
		
		
	
		

		// UnidadesParcelas
		final TextBox descripcion = new TextBox();
		final TextBox nivelAgua = new TextBox();
		final TextBox metros2afectados = new TextBox();
		final ListBox nivelPiso = new ListBox();;

		// Datos Vivienda
		final TextBox obsMaterialTecho = new TextBox();
		final TextBox obsMaterialPiso = new TextBox();
		final TextBox obsProcedenciaAgua = new TextBox();
		final TextBox obsMaterialParedes = new TextBox();
		final TextBox obsSanitaria = new TextBox();
		final TextBox obsBasura = new TextBox();

		// Referencias a constantes
		final ListBox ocupacion = new ListBox();
		final ListBox estadoPared = new ListBox();
		final ListBox materialTecho = new ListBox();
		final ListBox materialParedes = new ListBox();
		final ListBox materialPiso = new ListBox();
		final ListBox banios = new ListBox();
		final ListBox propietarioOtras = new ListBox();
		final ListBox tenenciaVivienda = new ListBox();
		final ListBox mudarse = new ListBox();
		final ListBox procedenciaAguaConsumo = new ListBox();
		final ListBox procedenciaAguaVivienda = new ListBox();
		final ListBox basura = new ListBox();
		final ListBox categoriaVivienda = new ListBox();
		final ListBox sanitario = new ListBox();
		final ListBox estadoTerminacion = new ListBox();
		final ListBox energia = new ListBox();
		final ListBox conservacionVivienda = new ListBox();

		// ProblemasVivienda (Atributos Boolean)
		final ListBox humedadesTecho = new ListBox();
		final ListBox goterasTecho = new ListBox();
		final ListBox puertasVentanasMalEstado = new ListBox();
		final ListBox grietasPiso = new ListBox();
		final ListBox caidaRevoques = new ListBox();
		final ListBox cielorasoDesprendido = new ListBox();
		final ListBox pocaLuzSolar = new ListBox();
		final ListBox escazaVentilacion = new ListBox();
		final ListBox peligroDerrumbe = new ListBox();
		final ListBox instalacionesMalas = new ListBox();
		final ListBox manchasParedesTechos = new ListBox();
		final ListBox descalceDeCimientos = new ListBox();
		final ListBox pozoNegroMalEstado = new ListBox();

		// Hacinamiento
		final TextBox cantFamilias = new TextBox();
		final TextBox cantHabitaciones = new TextBox();
		final TextBox cantHabDormitorio = new TextBox();
		final ListBox actividadLaboralEnViv = new ListBox();
		final TextBox cantHabTrabajo = new TextBox();

		// Inundacion
		final ListBox inundadaAnteriormente = new ListBox();
		final TextBox anios = new TextBox();
		final TextBox antiguedadEnCasa = new TextBox();
		final ListBox irsePorInundacion = new ListBox();
		final TextBox observacionesNoIrse = new TextBox();
		final TextBox obsAlojamientoEnInundacion = new TextBox();
		final TextBox cntDiasFuera = new TextBox();
		final TextBox alturaAgua = new TextBox();
		final TextBox obsAguaVivienda = new TextBox();
		final ListBox abandonoViviendaPorInund = new ListBox();

		final ListBox alojamientoInundacion = new ListBox();
		final ListBox persepcionVivienda = new ListBox();
		final ListBox aguaEnVivienda = new ListBox();
		
		// A panel where the thumbnails of uploaded images will be shown
		final FlowPanel panelImages = new FlowPanel();
		final FlowPanel panelImagesAntes = new FlowPanel();
		final FlowPanel panelImagesDespues = new FlowPanel();
		
		
		final CheckBox fotoAntesDespues = new CheckBox();
		final Label labelFoto = new Label();
		
		final Button sigCarruselAntes = new Button("Siguiente");
		final Button antCarruselAntes = new Button("Anterior");
		final Button sigCarruselDespues = new Button("Siguiente");
		final Button antCarruselDespues = new Button("Anterior");
		final Button elimFotoDespues = new Button("Eliminar");
		final Button elimFotoAntes = new Button("Eliminar");
		final VerticalPanel verticalFotosAntes = new VerticalPanel();
		final VerticalPanel verticalFotosDespues = new VerticalPanel();
		final HorizontalPanel horizFotosAntes = new HorizontalPanel();
		final HorizontalPanel horizFotosDespues = new HorizontalPanel();
				
		
		 
		
		
		// Attach an image to the pictures viewer
		private OnLoadPreloadedImageHandler showImageFotosAntes = new OnLoadPreloadedImageHandler() {
		    public void onLoad(PreloadedImage image) {
		      image.setWidth("500px");
		      image.setHeight("400px");
		      panelImagesAntes.clear();
		      panelImagesAntes.add(image);
		    }
		  };
		  
		// Attach an image to the pictures viewer
			private OnLoadPreloadedImageHandler showImageFotosDespues = new OnLoadPreloadedImageHandler() {
			    public void onLoad(PreloadedImage image) {
			      image.setWidth("500px");
			      image.setHeight("400px");
			      panelImagesDespues.clear();
			      panelImagesDespues.add(image);
			    }
		 };

		  

		      
		
		 

		@SuppressWarnings("deprecation")
		public FormDialogBox(Long idParcela, String accion) {
			a = accion;
			id = idParcela;

			if (a == "modificar"){
				panelTitulo.setCaptionText("Modificar Propiedad Siniestrada");
			}
			if (a == "eliminar"){
				panelTitulo.setCaptionText("Eliminar Propiedad Siniestrada");
			}
			if (a == "nuevo"){
				panelTitulo.setCaptionText("Nueva Propiedad Siniestrada");
			}
			
			fotosBorradas = new ArrayList<FotoDTO>();
			
			
			IPropiedadesSiniestradasAsync servidorPropiedadesSiniestradas = GWT
					.create(IPropiedadesSiniestradas.class);

			servidorPropiedadesSiniestradas.setFoto(true, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
						}
						
						@Override
						public void onFailure(Throwable caught) {
							caught.printStackTrace();
							Window.alert("ERROR AJAX");
						}
					});
			
			fotoAntesDespues.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
				       boolean checked = ((CheckBox) event.getSource()).isChecked();
				       IPropiedadesSiniestradasAsync servidorPropiedadesSiniestradas = GWT
								.create(IPropiedadesSiniestradas.class);
				       servidorPropiedadesSiniestradas.setFoto(!checked, new AsyncCallback<Void>() {
							
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
			
			
			gridIzqParcelas.setWidget(0, 0, new Label("Direccion"));
			gridIzqParcelas.setWidget(1, 0, new Label("Telefono"));
			gridIzqParcelas.setWidget(2, 0, new Label("Departamento"));
			gridIzqParcelas.setWidget(3, 0, new Label("Ciudad"));
			gridIzqParcelas.setWidget(4, 0, new Label("\u00BFVivienda?"));
			gridIzqParcelas.setWidget(5, 0, new Label("Otros Usos"));

			gridIzqParcelas.setWidget(0, 1, direccion);
			gridIzqParcelas.setWidget(1, 1, telefono);
			gridIzqParcelas.setWidget(2, 1, departamentos);
			gridIzqParcelas.setWidget(3, 1, ciudades);
			gridIzqParcelas.setWidget(4, 1, vivienda);
			gridIzqParcelas.setWidget(5, 1, otrosUsos);
			gridIzqParcelas.setBorderWidth(1);

			gridIzqDatosViv.setWidget(0, 0, new Label(
					" Indique si esta vivienda est\u00E1..."));
			gridIzqDatosViv.setWidget(1, 0, new Label(
					"\u00BFCu\u00E1l es el material predominante en las paredes?"));
			gridIzqDatosViv.setWidget(2, 0, new Label(
					"Observaciones material de las paredes"));
			gridIzqDatosViv.setWidget(3, 0, new Label(
					"Las paredes de esta vivienda, se encuentran..."));
			gridIzqDatosViv.setWidget(4, 0, new Label(
					"\u00BFCu\u00E1l es el material predominante en el techo?"));
			gridIzqDatosViv.setWidget(5, 0, new Label(
					"Observaciones material en el techo"));
			gridIzqDatosViv.setWidget(6, 0, new Label(
					"\u00BFCu\u00E1l es el material predominante en los pisos?"));
			gridIzqDatosViv.setWidget(7, 0, new Label(
					"Observaciones material en los pisos"));
			gridIzqDatosViv.setWidget(8, 0, new Label(
					"Estado de terminaci\u00F3n de la vivienda"));
			gridIzqDatosViv.setWidget(9, 0, new Label(
					"Categor\u00EDa de la vivienda"));
			gridIzqDatosViv
					.setWidget(
							10,
							0,
							new Label(
									"\u00BFDe donde proviene el agua que utilizan para beber y cocinar? "));
			gridIzqDatosViv.setWidget(11, 0, new Label(
					"Observaciones procedencia de agua"));
			gridIzqDatosViv.setWidget(12, 0, new Label(
					"\u00BFC\u00F3mo llega el agua a la vivienda?"));
			gridIzqDatosViv.setWidget(13, 0, new Label(
					"Qu\u00E9 tipo de energ\u00EDa tiene su vivienda?"));
			gridIzqDatosViv.setWidget(14, 0, new Label(
					"\u00BFC\u00F3mo se realiza la evacuaci\u00F3n del servicio sanitario?"));
			gridIzqDatosViv.setWidget(15, 0, new Label(
					"Observaciones evacuaci\u00F3n del servicio sanitario"));
			gridIzqDatosViv.setWidget(16, 0,
					new Label("\u00BFD\u00F3nde tira la basura?"));
			gridIzqDatosViv.setWidget(17, 0, new Label(
					"Observaciones de tirar la basura"));
			gridIzqDatosViv.setWidget(18, 0, new Label(
					"\u00BFEsta vivienda tiene servicio higi\u00E9nico?"));
			gridIzqDatosViv.setWidget(19, 0, new Label(
					"Respecto a la vivienda donde vive  usted,  es..."));
			gridIzqDatosViv
					.setWidget(
							20,
							0,
							new Label(
									"Independientemente de  la situaci\u00F3n respecto a \u00E9sta vivienda, ud es propietario de:"));
			gridIzqDatosViv.setWidget(21, 0, new Label(
					"Estado de conservaci\u00F3n de la vivienda"));
			gridIzqDatosViv
					.setWidget(
							22,
							0,
							new Label(
									"Si le ofrecieran una casa igual a esta, ubicada en un barrio mejor que no se inunde... "));

			gridIzqDatosViv.setWidget(0, 1, ocupacion);
			gridIzqDatosViv.setWidget(1, 1, materialParedes);
			gridIzqDatosViv.setWidget(2, 1, obsMaterialParedes);
			gridIzqDatosViv.setWidget(3, 1, estadoPared);
			gridIzqDatosViv.setWidget(4, 1, materialTecho);
			gridIzqDatosViv.setWidget(5, 1, obsMaterialTecho);
			gridIzqDatosViv.setWidget(6, 1, materialPiso);
			gridIzqDatosViv.setWidget(7, 1, obsMaterialPiso);
			gridIzqDatosViv.setWidget(8, 1, estadoTerminacion);
			gridIzqDatosViv.setWidget(9, 1, categoriaVivienda);
			gridIzqDatosViv.setWidget(10, 1, procedenciaAguaConsumo);
			gridIzqDatosViv.setWidget(11, 1, obsProcedenciaAgua);
			gridIzqDatosViv.setWidget(12, 1, procedenciaAguaVivienda);
			gridIzqDatosViv.setWidget(13, 1, energia);
			gridIzqDatosViv.setWidget(14, 1, sanitario);
			gridIzqDatosViv.setWidget(15, 1, obsSanitaria);
			gridIzqDatosViv.setWidget(16, 1, basura);
			gridIzqDatosViv.setWidget(17, 1, obsBasura);
			gridIzqDatosViv.setWidget(18, 1, banios);
			gridIzqDatosViv.setWidget(19, 1, tenenciaVivienda);
			gridIzqDatosViv.setWidget(20, 1, propietarioOtras);
			gridIzqDatosViv.setWidget(21, 1, conservacionVivienda);
			gridIzqDatosViv.setWidget(22, 1, mudarse);
			gridIzqDatosViv.setBorderWidth(1);

			gridDerProblViv.setWidget(0, 0, new Label("Humedades en techos"));
			gridDerProblViv.setWidget(1, 0, new Label("Goteras en el techo"));
			gridDerProblViv.setWidget(2, 0, new Label(
					"Puertas y ventanas en mal estado"));
			gridDerProblViv.setWidget(3, 0, new Label("Grietas en pisos"));
			gridDerProblViv.setWidget(4, 0, new Label(
					"Ca\u00EDda de revoque en paredes o techos"));
			gridDerProblViv.setWidget(5, 0, new Label(
					"Cielorrasos desprendidos"));
			gridDerProblViv.setWidget(6, 0, new Label("Poca luz solar"));
			gridDerProblViv.setWidget(7, 0, new Label("Escaza ventilaci\u00F3n"));
			gridDerProblViv.setWidget(8, 0, new Label("Peligro de derrumbe"));
			gridDerProblViv.setWidget(9, 0, new Label(
					"Instalaciones en mal estado"));
			gridDerProblViv.setWidget(10, 0, new Label(
					"Manchas, hongos en paredes o techos"));
			gridDerProblViv
					.setWidget(11, 0, new Label("Descalce de cimientos"));
			gridDerProblViv.setWidget(12, 0, new Label(
					"Pozo negro en mal estado"));

			gridDerProblViv.setWidget(0, 1, humedadesTecho);
			gridDerProblViv.setWidget(1, 1, goterasTecho);
			gridDerProblViv.setWidget(2, 1, puertasVentanasMalEstado);
			gridDerProblViv.setWidget(3, 1, grietasPiso);
			gridDerProblViv.setWidget(4, 1, caidaRevoques);
			gridDerProblViv.setWidget(5, 1, cielorasoDesprendido);
			gridDerProblViv.setWidget(6, 1, pocaLuzSolar);
			gridDerProblViv.setWidget(7, 1, escazaVentilacion);
			gridDerProblViv.setWidget(8, 1, peligroDerrumbe);
			gridDerProblViv.setWidget(9, 1, instalacionesMalas);
			gridDerProblViv.setWidget(10, 1, manchasParedesTechos);
			gridDerProblViv.setWidget(11, 1, descalceDeCimientos);
			gridDerProblViv.setWidget(12, 1, pozoNegroMalEstado);
			gridDerProblViv.setBorderWidth(1);

			gridDerInund.setWidget(0, 0, new Label(
					"\u00BFSe ha inundado esta vivienda anteriormente?"));
			gridDerInund.setWidget(1, 0, new Label("\u00BFEn que a\u00F1os?"));
			gridDerInund.setWidget(2, 0, new Label(
					"\u00BFHace cu\u00E1ntos a\u00F1os viven ustedes en esta vivienda?"));
			gridDerInund
					.setWidget(
							3,
							0,
							new Label(
									"Usted ha considerado la posibilidad de irse de este barrio porque se inunda?"));
			gridDerInund.setWidget(4, 0, new Label(
					"En caso de Negativo, \u00BFPor qu\u00E9 no?"));
			gridDerInund
					.setWidget(
							5,
							0,
							new Label(
									"\u00BFIngres\u00F3 en esta oportunidad el agua en el interior de su vivienda?"));
			gridDerInund
					.setWidget(
							6,
							0,
							new Label(
									"Especificar altura alcanzada (nivel de piso terminado de la vivienda)"));
			gridDerInund.setWidget(7, 0, new Label(
					"Observaciones ingreso de agua en Vivienda"));
			gridDerInund.setWidget(8, 0, new Label(
					"\u00BFAbandonaron su vivienda durante la inundaci\u00F3n?"));
			gridDerInund.setWidget(9, 0, new Label(
					"\u00BFD\u00F3nde estaban alojados durante la evacuaci\u00F3n?"));
			gridDerInund.setWidget(10, 0, new Label(
					"Observaciones alojamineto durante la evacuaci\u00F3n"));
			gridDerInund.setWidget(11, 0, new Label(
					"\u00BFCu\u00E1ntos d\u00EDas estuvo fuera de su hogar?"));
			gridDerInund.setWidget(12, 0, new Label("\u00BFUsted cree que este es"));

			gridDerInund.setWidget(0, 1, inundadaAnteriormente);
			gridDerInund.setWidget(1, 1, anios);
			gridDerInund.setWidget(2, 1, antiguedadEnCasa);
			gridDerInund.setWidget(3, 1, irsePorInundacion);
			gridDerInund.setWidget(4, 1, observacionesNoIrse);
			gridDerInund.setWidget(5, 1, aguaEnVivienda);
			gridDerInund.setWidget(6, 1, alturaAgua);
			gridDerInund.setWidget(7, 1, obsAguaVivienda);
			gridDerInund.setWidget(8, 1, abandonoViviendaPorInund);
			gridDerInund.setWidget(9, 1, alojamientoInundacion);
			gridDerInund.setWidget(10, 1, obsAlojamientoEnInundacion);
			gridDerInund.setWidget(11, 1, cntDiasFuera);
			gridDerInund.setWidget(12, 1, persepcionVivienda);
			gridDerInund.setBorderWidth(1);

			gridDerHacin.setWidget(0, 0, new Label(
					"\u00BFCu\u00E1ntas familias comparten esta vivienda?"));
			gridDerHacin
					.setWidget(
							1,
							0,
							new Label(
									"\u00BFCu\u00E1ntas habitaciones tiene esta vivienda sin considerar ba\u00F1o y cocina?"));
			gridDerHacin.setWidget(2, 0, new Label(
					"\u00BFCu\u00E1ntas habitaciones usa para dormir?"));
			gridDerHacin
					.setWidget(
							3,
							0,
							new Label(
									"\u00BFEn estas vivienda se realiza alguna actividad que le reporta ingresos a las personas que viven aqu\u00ED?"));
			gridDerHacin.setWidget(4, 0, new Label(
					"\u00BFCu\u00E1ntas habitaciones se usan para trabajar?"));

			gridDerHacin.setWidget(0, 1, cantFamilias);
			gridDerHacin.setWidget(1, 1, cantHabitaciones);
			gridDerHacin.setWidget(2, 1, cantHabDormitorio);
			gridDerHacin.setWidget(3, 1, actividadLaboralEnViv);
			gridDerHacin.setWidget(4, 1, cantHabTrabajo);
			gridDerHacin.setBorderWidth(1);

			departamentos.addItem("Seleccionar", "0");
			for (DepartamentoDTO d : departamentosGlobal) {
				departamentos.addItem(d.getNombre(), d.getId().toString());
			}
			departamentos.addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(ChangeEvent event) {
					ciudades.clear();
					Long id = Long.valueOf(departamentos.getValue(departamentos
							.getSelectedIndex()));
					for (DepartamentoDTO dto : departamentosGlobal) {
						if (dto.getId().equals(id)) {
							ciudades.addItem("Seleccionar", "0");
							for (CiudadDTO c : dto.getCiudades()) {
								ciudades.addItem(c.getNombre(), c.getId()
										.toString());
							}
						}
					}
				}
			});

			vivienda.addItem("SI", "SI");
			vivienda.addItem("NO", "NO");

			unidadesParcelas = new Grid(1, 6);
			unidadesParcelas.setWidget(0, 0, new Label("Descripcion"));
			unidadesParcelas.setWidget(0, 1, new Label(
					"Nivel del Agua en la vivienda (m)"));
			unidadesParcelas.setWidget(0, 2, new Label(
					"m\u00B2 afectados construidos"));
			unidadesParcelas.setWidget(0, 3, new Label(
					"Nivel de Piso Terminado"));
			unidadesParcelas.setWidget(0, 4, new Label("Modificar"));
			unidadesParcelas.setWidget(0, 5, new Label("Eliminar"));

			for (int i = 0; i < 6; i++) {
				unidadesParcelas.getCellFormatter().setStyleName(0, i,
						"tbl-cab");
			}

			unidadesParcelas.setBorderWidth(1);
			
			nuevoB.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					FormDialogBoxUnidadParcela dialog= new FormDialogBoxUnidadParcela(null, "nuevo",FormDialogBox.this);
					dialog.show();
				}
			});
			
			sigCarruselAntes.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					indexFotoAntes++;
					if(indexFotoAntes>=cantFotosAntes)
					{
						indexFotoAntes=0;
						
					}
					int count = 0;
					for(FotoDTO foto:parcelaDTO.getFotos()){
						if(foto.isAntes()){
							if(count == indexFotoAntes)
							{
								new PreloadedImage("/ImageServer.image?id="+foto.getId(), showImageFotosAntes);
								
							}
							count++;
						}
						
					}
				}
			});
			
			
			
			antCarruselAntes.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					indexFotoAntes--;
					if(indexFotoAntes<0)
					{
						indexFotoAntes=cantFotosAntes - 1;
						if(indexFotoAntes<0)
						{
							indexFotoAntes= 0;
						}
						
					}
					int count = 0;
					for(FotoDTO foto:parcelaDTO.getFotos()){
						if(foto.isAntes()){
							if(count == indexFotoAntes)
							{
								new PreloadedImage("/ImageServer.image?id="+foto.getId(), showImageFotosAntes);
								
							}
							count++;
						}
						
					}
				}
			});
			
			sigCarruselDespues.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					indexFotoDespues++;
					if(indexFotoDespues>=cantFotosDespues)
					{
						indexFotoDespues=0;
						
					}
					int count = 0;
					for(FotoDTO foto:parcelaDTO.getFotos()){
						if(!foto.isAntes()){
							if(count == indexFotoDespues)
							{
								new PreloadedImage("/ImageServer.image?id="+foto.getId(), showImageFotosDespues);
								
							}
							count++;
						}
						
					}
				}
			});
			
			antCarruselDespues.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					indexFotoDespues--;
					if(indexFotoDespues<0)
					{
						indexFotoDespues=cantFotosDespues - 1;
						if(indexFotoDespues<0)
						{
							indexFotoDespues= 0;
						}
						
					}
					int count = 0;
					for(FotoDTO foto:parcelaDTO.getFotos()){
						if(!foto.isAntes()){
							if(count == indexFotoDespues)
							{
								new PreloadedImage("/ImageServer.image?id="+foto.getId(), showImageFotosDespues);
								
							}
							count++;
						}
						
					}
					
					
				}
			});
			
			elimFotoAntes.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					int indiceFotoActual = indexFotoAntes;
					int count = 0;
					for(FotoDTO foto:parcelaDTO.getFotos()){
						if(foto.isAntes()){
							if(count == indiceFotoActual)
							{
								fotosBorradas.add(foto);
								/*Photo f = null;
								for(Photo photo : photosAntes){
									if(photo.getCaption().equals(foto.getNombre())){
										f= photo;
										break;
									}
									
								}
								//if(photosAntes.size()==1){
								if(cantFotosAntes==1){
									verticalFotosAntes.setVisible(false);
								}else{
									//photosAntes.remove(f);
									//carruselAntes.setPhotos(photosAntes);
									indexFotoAntes--;
									if(indexFotoAntes<0)
									{
										indexFotoAntes=cantFotosAntes - 1;
										if(indexFotoAntes<0)
										{
											indexFotoAntes= 0;
										}
										
									}
									int count2 = 0;
									for(FotoDTO foto2:parcelaDTO.getFotos()){
										if(foto2.isAntes()){
											if(count2 == indexFotoAntes)
											{
												new PreloadedImage("/ImageServer.image?id="+foto2.getId(), showImageFotosAntes);
												
											}
											count2++;
										}
										
									}
									// TODO Auto-generated method stub 
									
								}
								cantFotosAntes--;*/
							}
							count++;
						}
						
					}
				}
			});
			
			elimFotoDespues.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					int indiceFotoActual = 0;//= carruselDespues.getCurrentPhotoIndex();
					// TODO Auto-generated method stub
					int count = 0;
					for(FotoDTO foto:parcelaDTO.getFotos()){
						if(!foto.isAntes()){
							if(count == indiceFotoActual)
							{
								fotosBorradas.add(foto);
								/*Photo f = null;
								for(Photo photo : photosDespues){
									if(photo.getCaption().equals(foto.getNombre())){
										f= photo;
										break;
									}
									
								}
								//if(photosDespues.size()==1){
								if(cantFotosDespues ==1){
									verticalFotosDespues.setVisible(false);
								}else{
									//photosDespues.remove(f);
									//carruselDespues.setPhotos(photosDespues);
									indexFotoDespues--;
									if(indexFotoDespues<0)
									{
										indexFotoDespues=cantFotosDespues - 1;
										if(indexFotoDespues<0)
										{
											indexFotoDespues= 0;
										}
										
									}
									int count2 = 0;
									for(FotoDTO foto2:parcelaDTO.getFotos()){
										if(!foto2.isAntes()){
											if(count2 == indexFotoDespues)
											{
												new PreloadedImage("/ImageServer.image?id="+foto2.getId(), showImageFotosDespues);
												
											}
											count2++;
										}
										
									}
									// TODO Auto-generated method stub
								}
								cantFotosDespues--;*/
								
							}
							count++;
						}
						
					}
				}
			});
			
			
			ocupacion.addItem("Seleccionar", "0");
			for (ItemConstante i : Ocupacion.getItems()) {
				ocupacion.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}

			estadoPared.addItem("Seleccionar", "0");
			for (ItemConstante i : EstadoPared.getItems()) {
				estadoPared
						.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}

			materialTecho.addItem("Seleccionar", "0");
			for (ItemConstante i : MaterialTecho.getItems()) {
				materialTecho.addItem(i.getTexto(),
						String.valueOf(i.getCodigo()));
			}

			materialParedes.addItem("Seleccionar", "0");
			for (ItemConstante i : MaterialParedes.getItems()) {
				materialParedes.addItem(i.getTexto(),
						String.valueOf(i.getCodigo()));
			}

			materialPiso.addItem("Seleccionar", "0");
			for (ItemConstante i : MaterialPiso.getItems()) {
				materialPiso.addItem(i.getTexto(),
						String.valueOf(i.getCodigo()));
			}

			banios.addItem("Seleccionar", "0");
			for (ItemConstante i : Banios.getItems()) {
				banios.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}

			propietarioOtras.addItem("Seleccionar", "0");
			for (ItemConstante i : PropietarioOtras.getItems()) {
				propietarioOtras.addItem(i.getTexto(),
						String.valueOf(i.getCodigo()));
			}

			tenenciaVivienda.addItem("Seleccionar", "0");
			for (ItemConstante i : TenenciaVivienda.getItems()) {
				tenenciaVivienda.addItem(i.getTexto(),
						String.valueOf(i.getCodigo()));
			}

			mudarse.addItem("Seleccionar", "0");
			for (ItemConstante i : Mudarse.getItems()) {
				mudarse.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}

			procedenciaAguaConsumo.addItem("Seleccionar", "0");
			for (ItemConstante i : ProcedenciaAguaConsumo.getItems()) {
				procedenciaAguaConsumo.addItem(i.getTexto(),
						String.valueOf(i.getCodigo()));
			}

			procedenciaAguaVivienda.addItem("Seleccionar", "0");
			for (ItemConstante i : ProcedenciaAguaVivienda.getItems()) {
				procedenciaAguaVivienda.addItem(i.getTexto(),
						String.valueOf(i.getCodigo()));
			}

			basura.addItem("Seleccionar", "0");
			for (ItemConstante i : Basura.getItems()) {
				basura.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}

			categoriaVivienda.addItem("Seleccionar", "0");
			for (ItemConstante i : CategoriaVivienda.getItems()) {
				categoriaVivienda.addItem(i.getTexto(),
						String.valueOf(i.getCodigo()));
			}

			sanitario.addItem("Seleccionar", "0");
			for (ItemConstante i : Sanitaria.getItems()) {
				sanitario.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}

			estadoTerminacion.addItem("Seleccionar", "0");
			for (ItemConstante i : EstadoTerminacion.getItems()) {
				estadoTerminacion.addItem(i.getTexto(),
						String.valueOf(i.getCodigo()));
			}

			energia.addItem("Seleccionar", "0");
			for (ItemConstante i : Energia.getItems()) {
				energia.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
			}

			conservacionVivienda.addItem("Seleccionar", "0");
			for (ItemConstante i : ConservacionVivienda.getItems()) {
				conservacionVivienda.addItem(i.getTexto(),
						String.valueOf(i.getCodigo()));
			}

			humedadesTecho.addItem("SI", "SI");
			humedadesTecho.addItem("NO", "NO");

			goterasTecho.clear();
			goterasTecho.addItem("SI", "SI");
			goterasTecho.addItem("NO", "NO");

			puertasVentanasMalEstado.clear();
			puertasVentanasMalEstado.addItem("SI", "SI");
			puertasVentanasMalEstado.addItem("NO", "NO");

			grietasPiso.clear();
			grietasPiso.addItem("SI", "SI");
			grietasPiso.addItem("NO", "NO");

			caidaRevoques.clear();
			caidaRevoques.addItem("SI", "SI");
			caidaRevoques.addItem("NO", "NO");

			cielorasoDesprendido.clear();
			cielorasoDesprendido.addItem("SI", "SI");
			cielorasoDesprendido.addItem("NO", "NO");

			pocaLuzSolar.clear();
			pocaLuzSolar.addItem("SI", "SI");
			pocaLuzSolar.addItem("NO", "NO");

			escazaVentilacion.clear();
			escazaVentilacion.addItem("SI", "SI");
			escazaVentilacion.addItem("NO", "NO");

			peligroDerrumbe.clear();
			peligroDerrumbe.addItem("SI", "SI");
			peligroDerrumbe.addItem("NO", "NO");

			instalacionesMalas.clear();
			instalacionesMalas.addItem("SI", "SI");
			instalacionesMalas.addItem("NO", "NO");

			manchasParedesTechos.clear();
			manchasParedesTechos.addItem("SI", "SI");
			manchasParedesTechos.addItem("NO", "NO");

			descalceDeCimientos.clear();
			descalceDeCimientos.addItem("SI", "SI");
			descalceDeCimientos.addItem("NO", "NO");

			pozoNegroMalEstado.clear();
			pozoNegroMalEstado.addItem("SI", "SI");
			pozoNegroMalEstado.addItem("NO", "NO");

			// Hacinamiento
			actividadLaboralEnViv.clear();
			actividadLaboralEnViv.addItem("SI", "SI");
			actividadLaboralEnViv.addItem("NO", "NO");

			// Inundacion
			inundadaAnteriormente.clear();
			inundadaAnteriormente.addItem("SI", "SI");
			inundadaAnteriormente.addItem("NO", "NO");

			irsePorInundacion.clear();
			irsePorInundacion.addItem("SI", "SI");
			irsePorInundacion.addItem("NO", "NO");

			abandonoViviendaPorInund.clear();
			abandonoViviendaPorInund.addItem("SI", "SI");
			abandonoViviendaPorInund.addItem("NO", "NO");

			alojamientoInundacion.clear();
			alojamientoInundacion.addItem("Seleccionar", "0");
			for (ItemConstante i : AlojamientoInundacion.getItems()) {
				alojamientoInundacion.addItem(i.getTexto(),
						String.valueOf(i.getCodigo()));
			}

			persepcionVivienda.clear();
			persepcionVivienda.addItem("Seleccionar", "0");
			for (ItemConstante i : PersepcionVivienda.getItems()) {
				persepcionVivienda.addItem(i.getTexto(),
						String.valueOf(i.getCodigo()));
			}

			aguaEnVivienda.clear();
			aguaEnVivienda.addItem("Seleccionar", "0");
			for (ItemConstante i : AguaEnVivienda.getItems()) {
				aguaEnVivienda.addItem(i.getTexto(),
						String.valueOf(i.getCodigo()));
			}

			cantFamilias.addKeyboardListener(new KeyNumeric());
			cantHabitaciones.addKeyboardListener(new KeyNumeric());
			cantHabDormitorio.addKeyboardListener(new KeyNumeric());
			cantHabTrabajo.addKeyboardListener(new KeyNumeric());
			antiguedadEnCasa.addKeyboardListener(new KeyNumeric());
			cntDiasFuera.addKeyboardListener(new KeyNumeric());
			anios.addKeyboardListener(new KeyNumeric());
			alturaAgua.addKeyboardListener(new KeyNumeric());
			
			
			// Attach the image viewer to the document
		    // Create a new uploader panel and attach it to the document
		    MultiUploader defaultUploader = new MultiUploader();
		 
		    // Add a finish handler which will load the image once the upload finishes
		    defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
		    
		    labelFoto.setText("Despues de los arreglos");
		    
			
			parcelaDTO = new ParcelaDTO();

			if (a == "modificar" || a == "eliminar") {

				for (ParcelaDTO p : parcelaGlobal) {
					if (p.getId().equals(id)) {
						parcelaDTO = p;
					}
				}
				
				
				photosAntes=new ArrayList<Photo>();
				photosDespues=new ArrayList<Photo>();
				 
				for(FotoDTO foto:parcelaDTO.getFotos()){
					if(foto.isAntes()){
						new PreloadedImage("/ImageServer.image?id="+foto.getId(), showImageFotosAntes);
						Photo photo = new Photo("/ImageServer.image?id="+foto.getId());
						photo.setHeight(50);
						photo.setWidth(50);
						photo.setCaption(foto.getNombre());
						photosAntes.add(photo);
						cantFotosAntes++;
					}else{
						new PreloadedImage("/ImageServer.image?id="+foto.getId(), showImageFotosDespues);
						Photo photo = new Photo("/ImageServer.image?id="+foto.getId());
						photo.setHeight(50);
						photo.setWidth(50);
						photo.setCaption(foto.getNombre());
						photosDespues.add(photo);
						cantFotosDespues++;
					}
				}
				/*if(photosAntes.size()>0){
					carruselAntes.setPhotos(photosAntes);
					carruselAntes.setVisible(true);
					carruselAntes.setPixelSize(800,500);
					carruselAntes.setTitle("Imagenes Antes");
					
					
				}else{
					carruselAntes.setVisible(false);
					verticalFotosAntes.setVisible(false);
				}
				
				

				if(photosDespues.size()>0){
					carruselDespues.setPhotos(photosDespues);
					carruselDespues.setVisible(true);
					carruselDespues.setPixelSize(800,500);
					carruselDespues.setTitle("Imagenes Despues");
				}else{
					carruselDespues.setVisible(false);
					verticalFotosDespues.setVisible(false);
				}
				*/
				// TODO Auto-generated method stub
				
				direccion.setText(parcelaDTO.getDireccion());
				telefono.setText(parcelaDTO.getTelefono());
				vivienda.addItem("SI", "SI");
				vivienda.addItem("NO", "NO");
				vivienda.setSelectedIndex(parcelaDTO.getTipoParcela()
						.isVivienda() ? 0 : 1);
				otrosUsos.setText(parcelaDTO.getTipoParcela().getOtrosUsos());

				int row = 0;
				DepartamentoDTO dtoDepto = null;
				for (DepartamentoDTO d : departamentosGlobal) {
					row++;
					if (d.getId().equals(parcelaDTO.getDepartamento().getId())) {
						departamentos.setSelectedIndex(row);
						dtoDepto = d;
					}
				}

				row = 0;
				ciudades.clear();
				ciudades.addItem("Seleccionar", "0");
				for (CiudadDTO c : dtoDepto.getCiudades()) {
					row++;
					ciudades.addItem(c.getNombre(), c.getId().toString());
					if (c.getId().equals(parcelaDTO.getCiudad().getId())) {
						ciudades.setSelectedIndex(row);
					}
				}

				unidadesParcelas = new Grid(parcelaDTO.getUnidadesParcelas()
						.size() + 1, 6);
				unidadesParcelas.setWidget(0, 0, new Label("Descripcion"));
				unidadesParcelas.setWidget(0, 1, new Label(
						"Nivel del Agua en la vivienda (m)"));
				unidadesParcelas.setWidget(0, 2, new Label(
						"m\u00B2 afectados construidos"));
				unidadesParcelas.setWidget(0, 3, new Label(
						"Nivel de Piso Terminado"));
				unidadesParcelas.setWidget(0, 4, new Label("Modificar"));
				unidadesParcelas.setWidget(0, 5, new Label("Eliminar"));
				for (int i = 0; i < 6; i++) {
					unidadesParcelas.getCellFormatter().setStyleName(0, i,
							"tbl-cab");
				}

				unidadesParcelas.setBorderWidth(1);

				row = 1;
				for (UnidadParcelaDTO u : parcelaDTO.getUnidadesParcelas()) {
					unidadesParcelas.setWidget(row, 0,
							new Label(u.getDescripcion()));
					unidadesParcelas.setWidget(row, 1,
							new Label(String.valueOf(u.getNivelAgua())));
					unidadesParcelas.setWidget(row, 2,
							new Label(String.valueOf(u.getMetros2afectados())));
					unidadesParcelas.setWidget(row, 3,
							new Label(NivelPiso.getTXT(u.getNivelPiso())));
					final UnidadParcelaDTO uni = u;
					final Image modificarI = new Image("/images/modificar.png");
					modificarI.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							FormDialogBoxUnidadParcela dialog = new FormDialogBoxUnidadParcela(
									uni, "modificar",FormDialogBox.this);
							dialog.show();
						}
					});

					final Image eliminarI = new Image("/images/eliminar.png");

					eliminarI.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							FormDialogBoxUnidadParcela dialog = new FormDialogBoxUnidadParcela(
									uni, "eliminar",FormDialogBox.this);
							dialog.show();
						}
					});
					if (a == "modificar") {
						unidadesParcelas.setWidget(row, 4, modificarI);
						unidadesParcelas.setWidget(row, 5, eliminarI);
					}
					row++;
				}
				gridUnidadesParcelas.setWidget(0, 0, unidadesParcelas);
				

				obsMaterialTecho.setText(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getObsMaterialTecho());
				obsMaterialPiso.setText(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getObsMaterialPiso());
				obsProcedenciaAgua.setText(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getObsProcedenciaAgua());
				obsMaterialParedes.setText(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getObsMaterialParedes());
				obsSanitaria.setText(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getObsSanitaria());
				obsBasura.setText(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getObsBasura());

				row = 0;
				ocupacion.clear();
				ocupacion.addItem("Seleccionar", "0");
				for (ItemConstante i : Ocupacion.getItems()) {
					row++;
					ocupacion.addItem(i.getTexto(),
							String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getOcupacion()) {
						ocupacion.setSelectedIndex(row);
					}
				}

				row = 0;
				estadoPared.clear();
				estadoPared.addItem("Seleccionar", "0");
				for (ItemConstante i : EstadoPared.getItems()) {
					row++;
					estadoPared.addItem(i.getTexto(),
							String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getEstadoPared()) {
						estadoPared.setSelectedIndex(row);
					}
				}

				row = 0;
				materialTecho.clear();
				materialTecho.addItem("Seleccionar", "0");
				for (ItemConstante i : MaterialTecho.getItems()) {
					row++;
					materialTecho.addItem(i.getTexto(),
							String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getMaterialTecho()) {
						materialTecho.setSelectedIndex(row);
					}
				}

				row = 0;
				materialParedes.clear();
				materialParedes.addItem("Seleccionar", "0");
				for (ItemConstante i : MaterialParedes.getItems()) {
					row++;
					materialParedes.addItem(i.getTexto(),
							String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getMaterialParedes()) {
						materialParedes.setSelectedIndex(row);
					}
				}

				row = 0;
				materialPiso.clear();
				materialPiso.addItem("Seleccionar", "0");
				for (ItemConstante i : MaterialPiso.getItems()) {
					row++;
					materialPiso.addItem(i.getTexto(),
							String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getMaterialPiso()) {
						materialPiso.setSelectedIndex(row);
					}
				}

				row = 0;
				banios.clear();
				banios.addItem("Seleccionar", "0");
				for (ItemConstante i : Banios.getItems()) {
					row++;
					banios.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getBanios()) {
						banios.setSelectedIndex(row);
					}
				}

				row = 0;
				propietarioOtras.clear();
				propietarioOtras.addItem("Seleccionar", "0");
				for (ItemConstante i : PropietarioOtras.getItems()) {
					row++;
					propietarioOtras.addItem(i.getTexto(),
							String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getPropietarioOtras()) {
						propietarioOtras.setSelectedIndex(row);
					}
				}

				row = 0;
				tenenciaVivienda.clear();
				tenenciaVivienda.addItem("Seleccionar", "0");
				for (ItemConstante i : TenenciaVivienda.getItems()) {
					row++;
					tenenciaVivienda.addItem(i.getTexto(),
							String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getTenenciaVivienda()) {
						tenenciaVivienda.setSelectedIndex(row);
					}
				}

				row = 0;
				mudarse.clear();
				mudarse.addItem("Seleccionar", "0");
				for (ItemConstante i : Mudarse.getItems()) {
					row++;
					mudarse.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getMudarse()) {
						mudarse.setSelectedIndex(row);
					}
				}

				row = 0;
				procedenciaAguaConsumo.clear();
				procedenciaAguaConsumo.addItem("Seleccionar", "0");
				for (ItemConstante i : ProcedenciaAguaConsumo.getItems()) {
					row++;
					procedenciaAguaConsumo.addItem(i.getTexto(),
							String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getProcedenciaAguaConsumo()) {
						procedenciaAguaConsumo.setSelectedIndex(row);
					}
				}

				row = 0;
				procedenciaAguaVivienda.clear();
				procedenciaAguaVivienda.addItem("Seleccionar", "0");
				for (ItemConstante i : ProcedenciaAguaVivienda.getItems()) {
					row++;
					procedenciaAguaVivienda.addItem(i.getTexto(),
							String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getProcedenciaAguaVivienda()) {
						procedenciaAguaVivienda.setSelectedIndex(row);
					}
				}

				row = 0;
				basura.clear();
				basura.addItem("Seleccionar", "0");
				for (ItemConstante i : Basura.getItems()) {
					row++;
					basura.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getBasura()) {
						basura.setSelectedIndex(row);
					}
				}

				row = 0;
				categoriaVivienda.clear();
				categoriaVivienda.addItem("Seleccionar", "0");
				for (ItemConstante i : CategoriaVivienda.getItems()) {
					row++;
					categoriaVivienda.addItem(i.getTexto(),
							String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getCategoriaVivienda()) {
						categoriaVivienda.setSelectedIndex(row);
					}
				}

				row = 0;
				sanitario.clear();
				sanitario.addItem("Seleccionar", "0");
				for (ItemConstante i : Sanitaria.getItems()) {
					row++;
					sanitario.addItem(i.getTexto(),
							String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getSanitario()) {
						sanitario.setSelectedIndex(row);
					}
				}

				row = 0;
				estadoTerminacion.clear();
				estadoTerminacion.addItem("Seleccionar", "0");
				for (ItemConstante i : EstadoTerminacion.getItems()) {
					row++;
					estadoTerminacion.addItem(i.getTexto(),
							String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getEstadoTerminacion()) {
						estadoTerminacion.setSelectedIndex(row);
					}
				}

				row = 0;
				energia.clear();
				energia.addItem("Seleccionar", "0");
				for (ItemConstante i : Energia.getItems()) {
					row++;
					energia.addItem(i.getTexto(), String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getEnergia()) {
						energia.setSelectedIndex(row);
					}
				}

				row = 0;
				conservacionVivienda.clear();
				conservacionVivienda.addItem("Seleccionar", "0");
				for (ItemConstante i : ConservacionVivienda.getItems()) {
					row++;
					conservacionVivienda.addItem(i.getTexto(),
							String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getConservacionVivienda()) {
						conservacionVivienda.setSelectedIndex(row);
					}
				}

				humedadesTecho.clear();
				humedadesTecho.addItem("SI", "SI");
				humedadesTecho.addItem("NO", "NO");
				humedadesTecho.setSelectedIndex(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getProblemasVivienda()
						.isHumedadesTecho() ? 0 : 1);

				goterasTecho.clear();
				goterasTecho.addItem("SI", "SI");
				goterasTecho.addItem("NO", "NO");
				goterasTecho.setSelectedIndex(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getProblemasVivienda()
						.isGoterasTecho() ? 0 : 1);

				puertasVentanasMalEstado.clear();
				puertasVentanasMalEstado.addItem("SI", "SI");
				puertasVentanasMalEstado.addItem("NO", "NO");
				puertasVentanasMalEstado
						.setSelectedIndex(parcelaDTO.getTipoParcela()
								.getDatosVivienda().getProblemasVivienda()
								.isPuertasVentanasMalEstado() ? 0 : 1);

				grietasPiso.clear();
				grietasPiso.addItem("SI", "SI");
				grietasPiso.addItem("NO", "NO");
				grietasPiso.setSelectedIndex(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getProblemasVivienda()
						.isGrietasPiso() ? 0 : 1);

				caidaRevoques.clear();
				caidaRevoques.addItem("SI", "SI");
				caidaRevoques.addItem("NO", "NO");
				caidaRevoques.setSelectedIndex(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getProblemasVivienda()
						.isCaidaRevoques() ? 0 : 1);

				cielorasoDesprendido.clear();
				cielorasoDesprendido.addItem("SI", "SI");
				cielorasoDesprendido.addItem("NO", "NO");
				cielorasoDesprendido.setSelectedIndex(parcelaDTO
						.getTipoParcela().getDatosVivienda()
						.getProblemasVivienda().isCielorasoDesprendido() ? 0
						: 1);

				pocaLuzSolar.clear();
				pocaLuzSolar.addItem("SI", "SI");
				pocaLuzSolar.addItem("NO", "NO");
				pocaLuzSolar.setSelectedIndex(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getProblemasVivienda()
						.isPocaLuzSolar() ? 0 : 1);

				escazaVentilacion.clear();
				escazaVentilacion.addItem("SI", "SI");
				escazaVentilacion.addItem("NO", "NO");
				escazaVentilacion.setSelectedIndex(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getProblemasVivienda()
						.isEscazaVentilacion() ? 0 : 1);

				peligroDerrumbe.clear();
				peligroDerrumbe.addItem("SI", "SI");
				peligroDerrumbe.addItem("NO", "NO");
				peligroDerrumbe.setSelectedIndex(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getProblemasVivienda()
						.isPeligroDerrumbe() ? 0 : 1);

				instalacionesMalas.clear();
				instalacionesMalas.addItem("SI", "SI");
				instalacionesMalas.addItem("NO", "NO");
				instalacionesMalas.setSelectedIndex(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getProblemasVivienda()
						.isInstalacionesMalas() ? 0 : 1);

				manchasParedesTechos.clear();
				manchasParedesTechos.addItem("SI", "SI");
				manchasParedesTechos.addItem("NO", "NO");
				manchasParedesTechos.setSelectedIndex(parcelaDTO
						.getTipoParcela().getDatosVivienda()
						.getProblemasVivienda().isManchasParedesTechos() ? 0
						: 1);

				descalceDeCimientos.clear();
				descalceDeCimientos.addItem("SI", "SI");
				descalceDeCimientos.addItem("NO", "NO");
				descalceDeCimientos
						.setSelectedIndex(parcelaDTO.getTipoParcela()
								.getDatosVivienda().getProblemasVivienda()
								.isDescalceDeCimientos() ? 0 : 1);

				pozoNegroMalEstado.clear();
				pozoNegroMalEstado.addItem("SI", "SI");
				pozoNegroMalEstado.addItem("NO", "NO");
				pozoNegroMalEstado.setSelectedIndex(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getProblemasVivienda()
						.isPozoNegroMalEstado() ? 0 : 1);

				// Hacinamiento

				cantFamilias.setText(String
						.valueOf(parcelaDTO.getTipoParcela().getDatosVivienda()
								.getHacinamiento().getCantFamilias()));
				cantHabitaciones.setText(String.valueOf(parcelaDTO
						.getTipoParcela().getDatosVivienda().getHacinamiento()
						.getCantHabitaciones()));
				cantHabDormitorio.setText(String.valueOf(parcelaDTO
						.getTipoParcela().getDatosVivienda().getHacinamiento()
						.getCantHabDormitorio()));
				actividadLaboralEnViv.clear();
				actividadLaboralEnViv.addItem("SI", "SI");
				actividadLaboralEnViv.addItem("NO", "NO");
				actividadLaboralEnViv.setSelectedIndex(parcelaDTO
						.getTipoParcela().getDatosVivienda().getHacinamiento()
						.isActividadLaboralEnViv() ? 0 : 1);
				cantHabTrabajo.setText(String.valueOf(parcelaDTO
						.getTipoParcela().getDatosVivienda().getHacinamiento()
						.getCantHabTrabajo()));

				// Inundacion
				inundadaAnteriormente.clear();
				inundadaAnteriormente.addItem("SI", "SI");
				inundadaAnteriormente.addItem("NO", "NO");
				inundadaAnteriormente.setSelectedIndex(parcelaDTO
						.getTipoParcela().getDatosVivienda().getInundacion()
						.isInundadaAnteriormente() ? 0 : 1);
				anios.setText(String.valueOf(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getInundacion().getAnios()));
				antiguedadEnCasa.setText(String.valueOf(parcelaDTO
						.getTipoParcela().getDatosVivienda().getInundacion()
						.getAntiguedadEnCasa()));
				irsePorInundacion.clear();
				irsePorInundacion.addItem("SI", "SI");
				irsePorInundacion.addItem("NO", "NO");
				irsePorInundacion.setSelectedIndex(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getInundacion()
						.isInundadaAnteriormente() ? 0 : 1);
				observacionesNoIrse.setText(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getInundacion()
						.getObservacionesNoIrse());
				obsAlojamientoEnInundacion.setText(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getInundacion()
						.getObsAlojamientoEnInundacion());
				cntDiasFuera.setText(String.valueOf(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getInundacion().getCntDiasFuera()));
				alturaAgua.setText(String.valueOf(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getInundacion().getAlturaAgua()));
				obsAguaVivienda.setText(parcelaDTO.getTipoParcela()
						.getDatosVivienda().getInundacion()
						.getObsAguaVivienda());
				abandonoViviendaPorInund.clear();
				abandonoViviendaPorInund.addItem("SI", "SI");
				abandonoViviendaPorInund.addItem("NO", "NO");
				abandonoViviendaPorInund.setSelectedIndex(parcelaDTO
						.getTipoParcela().getDatosVivienda().getInundacion()
						.isAbandonoViviendaPorInund() ? 0 : 1);

				row = 0;
				alojamientoInundacion.clear();
				alojamientoInundacion.addItem("Seleccionar", "0");
				for (ItemConstante i : AlojamientoInundacion.getItems()) {
					row++;
					alojamientoInundacion.addItem(i.getTexto(),
							String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getInundacion()
							.getAlojamientoInundacion()) {
						alojamientoInundacion.setSelectedIndex(row);
					}
				}

				row = 0;
				persepcionVivienda.clear();
				persepcionVivienda.addItem("Seleccionar", "0");
				for (ItemConstante i : PersepcionVivienda.getItems()) {
					row++;
					persepcionVivienda.addItem(i.getTexto(),
							String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getInundacion()
							.getPersepcionVivienda()) {
						persepcionVivienda.setSelectedIndex(row);
					}
				}

				row = 0;
				aguaEnVivienda.clear();
				aguaEnVivienda.addItem("Seleccionar", "0");
				for (ItemConstante i : AguaEnVivienda.getItems()) {
					row++;
					aguaEnVivienda.addItem(i.getTexto(),
							String.valueOf(i.getCodigo()));
					if (i.getCodigo() == parcelaDTO.getTipoParcela()
							.getDatosVivienda().getInundacion()
							.getAguaEnVivienda()) {
						aguaEnVivienda.setSelectedIndex(row);
					}
				}

				row = 0;
				for (DepartamentoDTO d : departamentosGlobal) {
					row++;
					if (d.getId().equals(parcelaDTO.getDepartamento().getId())) {
						departamentos.setSelectedIndex(row);
						ciudades.clear();
						ciudades.addItem("Seleccionar", "0");
						int row2 = 1;
						for (CiudadDTO c : d.getCiudades()) {
							ciudades.addItem(c.getNombre(), c.getId()
									.toString());
							if (c.getId()
									.equals(parcelaDTO.getCiudad().getId())) {
								ciudades.setSelectedIndex(row2);
							}
							row2++;
						}
					}
				}

			}

			if (a == "eliminar") {

				direccion.setEnabled(false);
				telefono.setEnabled(false);
				vivienda.setEnabled(false);
				otrosUsos.setEnabled(false);
				departamentos.setEnabled(false);
				ciudades.setEnabled(false);

				
				// Datos Vivienda
				obsMaterialTecho.setEnabled(false);
				obsMaterialPiso.setEnabled(false);
				obsProcedenciaAgua.setEnabled(false);
				obsMaterialParedes.setEnabled(false);
				obsSanitaria.setEnabled(false);
				obsBasura.setEnabled(false);

				// Referencias a constantes
				ocupacion.setEnabled(false);
				estadoPared.setEnabled(false);
				materialTecho.setEnabled(false);
				materialParedes.setEnabled(false);
				materialPiso.setEnabled(false);
				banios.setEnabled(false);
				propietarioOtras.setEnabled(false);
				tenenciaVivienda.setEnabled(false);
				mudarse.setEnabled(false);
				procedenciaAguaConsumo.setEnabled(false);
				procedenciaAguaVivienda.setEnabled(false);
				basura.setEnabled(false);
				categoriaVivienda.setEnabled(false);
				sanitario.setEnabled(false);
				estadoTerminacion.setEnabled(false);
				energia.setEnabled(false);
				conservacionVivienda.setEnabled(false);

				// ProblemasVivienda
				humedadesTecho.setEnabled(false);
				goterasTecho.setEnabled(false);
				puertasVentanasMalEstado.setEnabled(false);
				grietasPiso.setEnabled(false);
				caidaRevoques.setEnabled(false);
				cielorasoDesprendido.setEnabled(false);
				pocaLuzSolar.setEnabled(false);
				escazaVentilacion.setEnabled(false);
				peligroDerrumbe.setEnabled(false);
				instalacionesMalas.setEnabled(false);
				manchasParedesTechos.setEnabled(false);
				descalceDeCimientos.setEnabled(false);
				pozoNegroMalEstado.setEnabled(false);

				// Hacinamiento
				cantFamilias.setEnabled(false);
				cantHabitaciones.setEnabled(false);
				cantHabDormitorio.setEnabled(false);
				actividadLaboralEnViv.setEnabled(false);
				cantHabTrabajo.setEnabled(false);

				// Inundacion
				inundadaAnteriormente.setEnabled(false);
				anios.setEnabled(false);
				antiguedadEnCasa.setEnabled(false);
				irsePorInundacion.setEnabled(false);
				observacionesNoIrse.setEnabled(false);
				obsAlojamientoEnInundacion.setEnabled(false);
				cntDiasFuera.setEnabled(false);
				alturaAgua.setEnabled(false);
				obsAguaVivienda.setEnabled(false);
				abandonoViviendaPorInund.setEnabled(false);

				alojamientoInundacion.setEnabled(false);
				persepcionVivienda.setEnabled(false);
				aguaEnVivienda.setEnabled(false);
				nuevoB.setEnabled(false);

			};
			
			
			
			
			
			vertical1.add(panelDatosVivienda);
			panelParcelas.add(gridIzqParcelas);
			panelDatosVivienda.add(gridIzqDatosViv);
			panelProblemasVivienda.add(gridDerProblViv);
			panelInundacion.add(gridDerInund);
			panelHacinamineto.add(gridDerHacin);
			vertical1.add(panelParcelas);
			vertical1.add(panelDatosVivienda);
			vertical2.add(panelProblemasVivienda);
			vertical2.add(panelHacinamineto);
			vertical2.add(panelInundacion);
			horizontal1.add(vertical1);
			horizontal1.add(vertical2);
			panelTitulo.add(vertical);
			vertical.add(horizontal1);
			vertical.add(panelUnidadesParcela);
			vertical.add(panelFotos);
			panelFotos.add(verticalFoto);
			verticalFoto.add(panelFotosAntes);
			verticalFoto.add(panelFotosDespues);
			panelFotosAntes.add(verticalFotosAntes);
			//verticalFotosAntes.add(carruselAntes);
			verticalFotosAntes.add(panelImagesAntes);
			verticalFotosAntes.add(horizFotosAntes);
			horizFotosAntes.add(antCarruselAntes);
			horizFotosAntes.add(elimFotoAntes);
			horizFotosAntes.add(sigCarruselAntes);

			panelFotosDespues.add(verticalFotosDespues);
			//verticalFotosDespues.add(carruselDespues);
			verticalFotosDespues.add(panelImagesDespues);
			verticalFotosDespues.add(horizFotosDespues);
			horizFotosDespues.add(antCarruselDespues);
			horizFotosDespues.add(elimFotoDespues);
			horizFotosDespues.add(sigCarruselDespues);
			
			
			verticalFoto.add(horizFotos);
			verticalFoto.add(panelImages);
			
			
			horizFotos.add(defaultUploader);
			horizFotos.add(fotoAntesDespues);
			horizFotos.add(labelFoto);
			panelUnidadesParcela.add(gridUnidadesParcelas);
			gridUnidadesParcelas.setWidget(0, 0, unidadesParcelas);
			gridUnidadesParcelas.setWidget(0, 1, nuevoB);
			
			
			// Agregar Grilal Fotos

			horizontal3.add(aceptar);
			horizontal3.add(cancelar);
			vertical.add(horizontal3);

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
			add(panelTitulo);
			center();
		}

		protected void procesar() {
			
			
			 ParcelaDTO dto=validar();
			 
			if(dto!=null){
				for(UnidadParcelaDTO u: parcelaDTO.getUnidadesParcelas()){
					if(u.getId()>=baseNumerador){
						u.setId(null);
					}
				}
				dto.setId(id);
				if(a=="modificar"){
					dto.setUsuario(usuarioGlobal);
					
					IPropiedadesSiniestradasAsync servidorPropiedadesSiniestradas=GWT.create(IPropiedadesSiniestradas.class);
					
					servidorPropiedadesSiniestradas.borrarFotos(dto.getId(), fotosBorradas, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
						}
						
						@Override
						public void onFailure(Throwable caught) {
							caught.printStackTrace();
							Window.alert("ERROR AJAX");
						}
					});
					
					
					servidorPropiedadesSiniestradas.modificarParcela(dto,new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							setWindowHref("/PropiedadesSiniestradasList.html");
						}
						
						@Override
						public void onFailure(Throwable caught) {
							caught.printStackTrace();
							Window.alert("ERROR AJAX");
						}
					});
					
					
				}else if(a== "nuevo"){
					dto.setUsuario(usuarioGlobal);
					IPropiedadesSiniestradasAsync servidorPropiedadesSiniestradas=GWT.create(IPropiedadesSiniestradas.class);
					
					servidorPropiedadesSiniestradas.nuevaParcela(dto, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							setWindowHref("/PropiedadesSiniestradasList.html");
						}
						
						@Override
						public void onFailure(Throwable caught) {
							caught.printStackTrace();
							Window.alert("ERROR AJAX");
						}
					});
					
				}else if(a=="eliminar"){
IPropiedadesSiniestradasAsync servidorPropiedadesSiniestradas=GWT.create(IPropiedadesSiniestradas.class);
					
					servidorPropiedadesSiniestradas.eliminarParcela(dto, new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							setWindowHref("/PropiedadesSiniestradasList.html");
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

		private ParcelaDTO validar() {
			
			ParcelaDTO dto = parcelaDTO;

			
			 if(direccion.getText().trim().length()==0){
				 Window.alert("Indique Direccion");
				 return null;
			 }
			 dto.setDireccion(direccion.getText());
			  
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
			  
			  if(telefono.getText().trim().length()==0){
					 Window.alert("Indique Telefono");
					 return null;
				 }
				 dto.setTelefono(telefono.getText());
				 
			TipoParcelaDTO tipoParcelaDTO = new TipoParcelaDTO();
			
			tipoParcelaDTO.setVivienda(getBoolean(vivienda.getValue(vivienda.getSelectedIndex())));
			
			 if(otrosUsos.getText().trim().length()==0){
				 Window.alert("Indique Otros Usos");
				 return null;
			 }
			 tipoParcelaDTO.setOtrosUsos(otrosUsos.getText());
			 
			DatosViviendaDTO datosViviendaDTO = new DatosViviendaDTO();
			
			if(ocupacion.getSelectedIndex()<1){
				  Window.alert(" Indique si esta vivienda est\u00E1..."); 
				  return null;
			  }
			
			datosViviendaDTO.setOcupacion(Integer.valueOf(ocupacion.getValue(ocupacion.getSelectedIndex())));
			 
			if(materialParedes.getSelectedIndex()<1){
				  Window.alert("Indique Cu\u00E1l es el material predominante en las paredes"); 
				  return null;
			  }
			
			datosViviendaDTO.setMaterialParedes(Integer.valueOf(materialParedes.getValue(materialParedes.getSelectedIndex())));
			
			datosViviendaDTO.setObsMaterialParedes(obsMaterialParedes.getText());
			
			
			
			if(estadoPared.getSelectedIndex()<1){
				  Window.alert("Indique Las paredes de esta vivienda, se encuentran..."); 
				  return null;
			  }
			
			datosViviendaDTO.setEstadoPared(Integer.valueOf(estadoPared.getValue(estadoPared.getSelectedIndex())));
			
			if(materialTecho.getSelectedIndex()<1){
				  Window.alert("Indique Cu\u00E1l es el material predominante en el techo"); 
				  return null;
			  }
			
			datosViviendaDTO.setMaterialTecho(Integer.valueOf(materialTecho.getValue(materialTecho.getSelectedIndex())));
			
			datosViviendaDTO.setObsMaterialTecho(obsMaterialTecho.getText());
			
			if(materialPiso.getSelectedIndex()<1){
				  Window.alert("Indique Cu\u00E1l es el material predominante en los pisos"); 
				  return null;
			  }
			
			datosViviendaDTO.setMaterialPiso(Integer.valueOf(materialPiso.getValue(materialPiso.getSelectedIndex())));
			
			datosViviendaDTO.setObsMaterialPiso(obsMaterialPiso.getText());

			if(estadoTerminacion.getSelectedIndex()<1){
				  Window.alert("Indique Estado de terminaci\u00F3n de la vivienda"); 
				  return null;
			  }
			
			datosViviendaDTO.setEstadoTerminacion(Integer.valueOf(estadoTerminacion.getValue(estadoTerminacion.getSelectedIndex())));
			
			if(categoriaVivienda.getSelectedIndex()<1){
				  Window.alert("Indique Categor\u00EDa de la vivienda"); 
				  return null;
			  }
			
			datosViviendaDTO.setCategoriaVivienda(Integer.valueOf(categoriaVivienda.getValue(categoriaVivienda.getSelectedIndex())));
			
			if(procedenciaAguaConsumo.getSelectedIndex()<1){
				  Window.alert("Indique De donde proviene el agua que utilizan para beber y cocinar"); 
				  return null;
			  }
			
			datosViviendaDTO.setProcedenciaAguaConsumo(Integer.valueOf(procedenciaAguaConsumo.getValue(procedenciaAguaConsumo.getSelectedIndex())));
			
			datosViviendaDTO.setObsProcedenciaAgua(obsProcedenciaAgua.getText());
			
			if(procedenciaAguaVivienda.getSelectedIndex()<1){
				  Window.alert("Indique C\u00F3mo llega el agua a la vivienda"); 
				  return null;
			  }
			
			datosViviendaDTO.setProcedenciaAguaVivienda(Integer.valueOf(procedenciaAguaVivienda.getValue(procedenciaAguaVivienda.getSelectedIndex())));
			
			if(energia.getSelectedIndex()<1){
				  Window.alert("Indique Qu\u00E9 tipo de energ\u00EDa tiene su vivienda"); 
				  return null;
			  }
			
			datosViviendaDTO.setEnergia(Integer.valueOf(energia.getValue(energia.getSelectedIndex())));
			
			if(sanitario.getSelectedIndex()<1){
				  Window.alert("Indique C\u00F3mo se realiza la evacuaci\u00F3n del servicio sanitario"); 
				  return null;
			  }
			
			datosViviendaDTO.setSanitario(Integer.valueOf(sanitario.getValue(sanitario.getSelectedIndex())));
			
			datosViviendaDTO.setObsSanitaria(obsSanitaria.getText());
			
			
			if(basura.getSelectedIndex()<1){
				  Window.alert("Indique D\u00F3nde tira la basura"); 
				  return null;
			  }
			
			datosViviendaDTO.setBasura(Integer.valueOf(basura.getValue(basura.getSelectedIndex())));
			
			datosViviendaDTO.setObsBasura(obsBasura.getText());
			
			if(banios.getSelectedIndex()<1){
				  Window.alert("Indique si esta vivienda tiene servicio higi\u00E9nico"); 
				  return null;
			  }
			
			datosViviendaDTO.setBanios(Integer.valueOf(banios.getValue(banios.getSelectedIndex())));
			
			if(tenenciaVivienda.getSelectedIndex()<1){
				  Window.alert("Indique Respecto a la vivienda donde vive  usted,  es..."); 
				  return null;
			  }
			
			datosViviendaDTO.setTenenciaVivienda(Integer.valueOf(tenenciaVivienda.getValue(tenenciaVivienda.getSelectedIndex())));
			 
			if(propietarioOtras.getSelectedIndex()<1){
				  Window.alert("Indique Independientemente de  la situaci\u00F3n respecto a \u00E9sta vivienda, ud es propietario de:"); 
				  return null;
			  }
			
			datosViviendaDTO.setPropietarioOtras(Integer.valueOf(propietarioOtras.getValue(propietarioOtras.getSelectedIndex())));
			 
			if(conservacionVivienda.getSelectedIndex()<1){
				  Window.alert("Indique Estado de conservaci\u00F3n de la vivienda"); 
				  return null;
			  }
			
			datosViviendaDTO.setConservacionVivienda(Integer.valueOf(conservacionVivienda.getValue(conservacionVivienda.getSelectedIndex())));
			 
			if(mudarse.getSelectedIndex()<1){
				  Window.alert("Indique Si le ofrecieran una casa igual a esta, ubicada en un barrio mejor que no se inunde..."); 
				  return null;
			  }
			
			datosViviendaDTO.setMudarse(Integer.valueOf(mudarse.getValue(mudarse.getSelectedIndex())));
			 
			ProblemasViviendaDTO problemasViviendaDTO = new ProblemasViviendaDTO();
			
			
			problemasViviendaDTO.setHumedadesTecho(getBoolean(humedadesTecho.getValue(humedadesTecho.getSelectedIndex())));
			problemasViviendaDTO.setGoterasTecho(getBoolean(goterasTecho.getValue(goterasTecho.getSelectedIndex())));
			problemasViviendaDTO.setPuertasVentanasMalEstado(getBoolean(puertasVentanasMalEstado.getValue(puertasVentanasMalEstado.getSelectedIndex())));
			problemasViviendaDTO.setGrietasPiso(getBoolean(grietasPiso.getValue(grietasPiso.getSelectedIndex())));
			problemasViviendaDTO.setCaidaRevoques(getBoolean(caidaRevoques.getValue(caidaRevoques.getSelectedIndex())));
			problemasViviendaDTO.setCielorasoDesprendido(getBoolean(cielorasoDesprendido.getValue(cielorasoDesprendido.getSelectedIndex())));
			problemasViviendaDTO.setPocaLuzSolar(getBoolean(pocaLuzSolar.getValue(pocaLuzSolar.getSelectedIndex())));
			problemasViviendaDTO.setEscazaVentilacion(getBoolean(escazaVentilacion.getValue(escazaVentilacion.getSelectedIndex())));
			problemasViviendaDTO.setPeligroDerrumbe(getBoolean(peligroDerrumbe.getValue(peligroDerrumbe.getSelectedIndex())));
			problemasViviendaDTO.setInstalacionesMalas(getBoolean(instalacionesMalas.getValue(instalacionesMalas.getSelectedIndex())));
			problemasViviendaDTO.setManchasParedesTechos(getBoolean(manchasParedesTechos.getValue(manchasParedesTechos.getSelectedIndex())));
			problemasViviendaDTO.setDescalceDeCimientos(getBoolean(descalceDeCimientos.getValue(descalceDeCimientos.getSelectedIndex())));
			problemasViviendaDTO.setPozoNegroMalEstado(getBoolean(pozoNegroMalEstado.getValue(pozoNegroMalEstado.getSelectedIndex())));
			
			InundacionDTO inundacionDTO = new InundacionDTO();
			
			inundacionDTO.setInundadaAnteriormente(getBoolean(inundadaAnteriormente.getValue(inundadaAnteriormente.getSelectedIndex())));
			
			if(inundadaAnteriormente.getValue(inundadaAnteriormente.getSelectedIndex() ).equals("SI")){
				if(anios.getText().trim().length()==0){
					  Window.alert("Indique En que a\u00F1os fue inundada anteriormente"); 
					  return null;
				  }
				
				inundacionDTO.setAnios(anios.getText());
				
			}
			
			if(antiguedadEnCasa.getText().trim().length()==0){
				  Window.alert("Indique Hace cu\u00E1ntos a\u00F1os viven ustedes en esta vivienda"); 
				  return null;
			  }
			
			inundacionDTO.setAntiguedadEnCasa(Integer.valueOf(antiguedadEnCasa.getText()));
			
			inundacionDTO.setIrsePorInundacion(getBoolean(irsePorInundacion.getValue(irsePorInundacion.getSelectedIndex())));
			
			if(irsePorInundacion.getValue(irsePorInundacion.getSelectedIndex() ).equals("NO")){
				if(observacionesNoIrse.getText().trim().length()==0){
					  Window.alert("Indique Por qu\u00E9 no a considerado irse por inundacion"); 
					  return null;
				  }
				
				inundacionDTO.setObservacionesNoIrse(observacionesNoIrse.getText());
				
			}
			
			if(aguaEnVivienda.getSelectedIndex()<1){
				  Window.alert("Indique Si ingres\u00F3 en esta oportunidad el agua en el interior de su  vivienda"); 
				  return null;
			  }
			
			inundacionDTO.setAguaEnVivienda(Integer.valueOf(aguaEnVivienda.getValue(aguaEnVivienda.getSelectedIndex())));
			
			if(alturaAgua.getText().trim().length()==0){
				  Window.alert("Indique altura alcanzada (nivel de piso terminado de la vivienda"); 
				  return null;
			  }
			
			inundacionDTO.setAlturaAgua(Long.valueOf((alturaAgua.getText())));
			inundacionDTO.setObsAguaVivienda(obsAguaVivienda.getText());
			
			inundacionDTO.setAbandonoViviendaPorInund(getBoolean(abandonoViviendaPorInund.getValue(abandonoViviendaPorInund.getSelectedIndex())));
				
			if(alojamientoInundacion.getSelectedIndex()<1){
				  Window.alert("Indique D\u00F3nde estaban alojados durante la evacuaci\u00F3n"); 
				  return null;
			  }
			
			inundacionDTO.setAlojamientoInundacion(Integer.valueOf(alojamientoInundacion.getValue(alojamientoInundacion.getSelectedIndex())));
			inundacionDTO.setObsAlojamientoEnInundacion(obsAlojamientoEnInundacion.getText());
			
			if(cntDiasFuera.getText().trim().length()==0){
				  Window.alert("Indique Cu\u00E1ntos d\u00EDas estuvo fuera de su hogar"); 
				  return null;
			  }
			
			inundacionDTO.setCntDiasFuera(Integer.valueOf((cntDiasFuera.getText())));
			
			if(persepcionVivienda.getSelectedIndex()<1){
				  Window.alert("Indique persepcion de la vivienda, Usted cree que este es"); 
				  return null;
			  }
			
			inundacionDTO.setPersepcionVivienda(Integer.valueOf(persepcionVivienda.getValue(persepcionVivienda.getSelectedIndex())));
			
			HacinamientoDTO hacinamientoDTO = new HacinamientoDTO();
			
			if(cantFamilias.getText().trim().length()==0){
				  Window.alert("Indique Cu\u00E1ntas familias comparten esta vivienda"); 
				  return null;
			  }
			
			hacinamientoDTO.setCantFamilias(Integer.valueOf((cantFamilias.getText())));
			
			if(cantHabitaciones.getText().trim().length()==0){
				  Window.alert("Indique Cu\u00E1ntas habitaciones tiene esta vivienda sin considerar ba\u00F1o y cocina"); 
				  return null;
			  }
			
			hacinamientoDTO.setCantHabitaciones(Integer.valueOf((cantHabitaciones.getText())));
			
			if(cantHabDormitorio.getText().trim().length()==0){
				  Window.alert("Indique Cu\u00E1ntas habitaciones usa para dormir"); 
				  return null;
			  }
			
			hacinamientoDTO.setCantHabDormitorio(Integer.valueOf((cantHabDormitorio.getText())));
			
					
			hacinamientoDTO.setActividadLaboralEnViv(getBoolean((actividadLaboralEnViv.getValue(actividadLaboralEnViv.getSelectedIndex()))));
			
			if(cantHabTrabajo.getText().trim().length()==0){
				  Window.alert("Indique Cu\u00E1ntas habitaciones se usan para trabajar"); 
				  return null;
			  }
			
			hacinamientoDTO.setCantHabTrabajo(Integer.valueOf((cantHabTrabajo.getText())));
			
			ArrayList<FotoDTO> imag=new ArrayList<FotoDTO>();
			for(String i: imagenes){
				FotoDTO f= new FotoDTO();
				f.setAntes(true);
				f.setUrl(i);
				imag.add(f);
			}
			parcelaDTO.setFotos(imag);
			
			
			datosViviendaDTO.setHacinamiento(hacinamientoDTO);
			datosViviendaDTO.setInundacion(inundacionDTO);
			datosViviendaDTO.setProblemasVivienda(problemasViviendaDTO);
			tipoParcelaDTO.setDatosVivienda(datosViviendaDTO);	 
			dto.setTipoParcela(tipoParcelaDTO);
		
			  

			return dto;

		}

		private Boolean getBoolean(String text) {
			if(text==null || text.trim().length()==0 || text.equals("NO")){
				return false;
			}
			return true;
		}

		  // Attach an image to the pictures viewer
		  private OnLoadPreloadedImageHandler showImage = new OnLoadPreloadedImageHandler() {
		    public void onLoad(PreloadedImage image) {
		      image.setWidth("250px");
		      panelImages.add(image);
		    }
		  };

		  private ArrayList<String> imagenes=new ArrayList<String>();
		  // Load the image in the document and in the case of success attach it to the viewer
		  private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
		    public void onFinish(IUploader uploader) {
		      if (uploader.getStatus() == Status.SUCCESS) {

		        new PreloadedImage(uploader.fileUrl(), showImage);
		        
		       imagenes.add(uploader.fileUrl()) ;		        
		        
		        // The server sends useful information to the client by default
		        UploadedInfo info = uploader.getServerInfo();
		        
		        System.out.println("File name " + info.name);
		        System.out.println("File content-type " + info.ctype);
		        System.out.println("File size " + info.size);

		        // You can send any customized message and parse it 
		        System.out.println("Server message " + info.message);
		      }
		    }
		  };


	
	}

	public class FormDialogBoxUnidadParcela extends DialogBox {
		private String a;
		private UnidadParcelaDTO unidad;
		final HorizontalPanel horizontal = new HorizontalPanel();
		final VerticalPanel vertical = new VerticalPanel();
		final CaptionPanel panelPrincipal = new CaptionPanel();
		final Grid grid = new Grid(4, 2);

		final TextBox descripcion = new TextBox();
		final TextBox nivelAgua = new TextBox();
		final TextBox metros2afectados = new TextBox();
		final ListBox nivelPiso = new ListBox();

		final Button cancelar = new Button("Cancelar");
		final Button aceptar = new Button("Aceptar");

		private DialogBox llamador;
		
		@SuppressWarnings("deprecation")
		public FormDialogBoxUnidadParcela(UnidadParcelaDTO uni, String accion, DialogBox dialog) {
			a = accion;
			unidad = uni;
			llamador=dialog;
			llamador.setStyleName("gwt-DialogBoxOpaco");
			
			if (a == "modificar")
				panelPrincipal.setCaptionText("Modificar Unidad Parcela");
			if (a == "eliminar")
				panelPrincipal.setCaptionText("Eliminar Unidad Parcela");
			if (a == "nuevo")
				panelPrincipal.setCaptionText("Nueva Unidad Parcela");

			grid.setWidget(0, 0, new Label("Descripcion"));
			grid.setWidget(1, 0, new Label("Nivel del Agua en la vivienda (m)"));
			grid.setWidget(2, 0, new Label("m\u00B2 afectados construidos"));
			grid.setWidget(3, 0, new Label("Nivel de Piso Terminado"));

			grid.setWidget(0, 1, descripcion);
			grid.setWidget(1, 1, nivelAgua);
			grid.setWidget(2, 1, metros2afectados);
			grid.setWidget(3, 1, nivelPiso);

			grid.setBorderWidth(1);

			nivelPiso.addItem("Seleccionar", "0");
			for (ItemConstante i : NivelPiso.getItems()) {
				nivelPiso.addItem(i.getTexto(), String.valueOf(i.getCodigo()));

			}

			metros2afectados.addKeyboardListener(new KeyNumeric());
			nivelAgua.addKeyboardListener(new KeyNumeric());

			if (a == "modificar" || a == "eliminar") {

				int row = 0;
				boolean salir = false;
				while (!salir) {
					row++;
					if (nivelPiso.getValue(row).equals(
							String.valueOf(uni.getNivelPiso()))) {
						nivelPiso.setSelectedIndex(row);
						salir = true;
					}
				}

				descripcion.setText(String.valueOf(uni.getDescripcion()));
				nivelAgua.setText(String.valueOf(uni.getNivelAgua()));
				metros2afectados.setText(String.valueOf(uni
						.getMetros2afectados()));

			}

			if (a == "eliminar") {
				descripcion.setEnabled(false);
				nivelAgua.setEnabled(false);
				metros2afectados.setEnabled(false);
				nivelPiso.setEnabled(false);
			}

			panelPrincipal.add(vertical);
			vertical.add(grid);
			horizontal.add(aceptar);
			horizontal.add(cancelar);
			vertical.add(horizontal);

			cancelar.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					FormDialogBoxUnidadParcela.this.hide();
					llamador.setStyleName("gwt-DialogBox");
				}
			});

			aceptar.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					FormDialogBoxUnidadParcela.this.procesar();
				}
			});

			setAnimationEnabled(true);
			add(panelPrincipal);
			center();
		}

		protected void procesar() {
			UnidadParcelaDTO dto = validar();
			if (dto != null) {

				if (a == "modificar") {
					dto.setId(unidad.getId());
					for (UnidadParcelaDTO u : parcelaDTO.getUnidadesParcelas()) {
						if (u.getId().equals(dto.getId())) {
							u.setDescripcion(dto.getDescripcion());
							u.setMetros2afectados(dto.getMetros2afectados());
							u.setNivelAgua(dto.getNivelAgua());
							u.setNivelPiso(dto.getNivelPiso());
						}
					}
				} else if (a == "nuevo") {
					dto.setId(numerador++);
					parcelaDTO.getUnidadesParcelas().add(dto);
				} else if (a == "eliminar") {
					dto.setId(unidad.getId());
					UnidadParcelaDTO uni = null;
					for (UnidadParcelaDTO u : parcelaDTO.getUnidadesParcelas()) {
						if (u.getId().equals(dto.getId())) {
							uni = u;
						}
					}
					parcelaDTO.getUnidadesParcelas().remove(uni);
				}

				unidadesParcelas = new Grid(parcelaDTO.getUnidadesParcelas()
						.size() + 1, 6);
				unidadesParcelas.setWidget(0, 0, new Label("Descripcion"));
				unidadesParcelas.setWidget(0, 1, new Label(
						"Nivel del Agua en la vivienda (m)"));
				unidadesParcelas.setWidget(0, 2, new Label(
						"m\u00B2 afectados construidos"));
				unidadesParcelas.setWidget(0, 3, new Label(
						"Nivel de Piso Terminado"));
				unidadesParcelas.setWidget(0, 4, new Label("Modificar"));
				unidadesParcelas.setWidget(0, 5, new Label("Eliminar"));

				for (int i = 0; i < 6; i++) {
					unidadesParcelas.getCellFormatter().setStyleName(0, i,
							"tbl-cab");
				}

				unidadesParcelas.setBorderWidth(1);

				int row = 1;
				for (UnidadParcelaDTO u : parcelaDTO.getUnidadesParcelas()) {
					unidadesParcelas.setWidget(row, 0,
							new Label(u.getDescripcion()));
					unidadesParcelas.setWidget(row, 1,
							new Label(String.valueOf(u.getNivelAgua())));
					unidadesParcelas.setWidget(row, 2,
							new Label(String.valueOf(u.getMetros2afectados())));
					unidadesParcelas.setWidget(row, 3,
							new Label(NivelPiso.getTXT(u.getNivelPiso())));
					final UnidadParcelaDTO uni = u;
					final Image modificarI = new Image("/images/modificar.png");
					modificarI.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							FormDialogBoxUnidadParcela dialog = new FormDialogBoxUnidadParcela(
									uni, "modificar",llamador);
							dialog.show();
						}
					});

					final Image eliminarI = new Image("/images/eliminar.png");

					eliminarI.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							FormDialogBoxUnidadParcela dialog = new FormDialogBoxUnidadParcela(
									uni, "eliminar",llamador);
							dialog.show();
						}
					});
					unidadesParcelas.setWidget(row, 4, modificarI);
					unidadesParcelas.setWidget(row, 5, eliminarI);
					row++;
				}
				gridUnidadesParcelas.setWidget(0, 0, unidadesParcelas);
				
			
				hide();
				llamador.setStyleName("gwt-DialogBox");
			}
		}

		private UnidadParcelaDTO validar() {
			UnidadParcelaDTO dto = new UnidadParcelaDTO();

			if (nivelPiso.getSelectedIndex() == 0) {
				Window.alert("Indique Nivel de Piso Terminado");
				return null;
			}
			dto.setNivelPiso(Integer.valueOf(nivelPiso.getValue(nivelPiso
					.getSelectedIndex())));

			if (descripcion.getText().trim().length() == 0) {
				Window.alert("Indique Descripcion");
				return null;
			}
			dto.setDescripcion(descripcion.getText());

			grid.setWidget(2, 0, new Label(""));

			if (nivelAgua.getText().trim().length() == 0) {
				Window.alert("Indique Nivel del Agua en la vivienda (m)");
				return null;
			}
			dto.setNivelAgua(Float.valueOf((nivelAgua.getText())));

			if (metros2afectados.getText().trim().length() == 0) {
				Window.alert("Indique m\u00B2 afectados construidos");
				return null;
			}
			dto.setMetros2afectados(Integer.valueOf((metros2afectados.getText())));

			return dto;

		}

	}

}
