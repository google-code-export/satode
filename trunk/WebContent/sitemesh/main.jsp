<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="requestURI" value="${pageContext.request.requestURI}" />
<c:set var="msg" value='<%= request.getParameter("msg") %>' />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"> 

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

 		<!-- Standard reset and fonts -->
		<link rel="stylesheet" type="text/css" href="${contextPath}/assets/styles.css" />
        <link rel="stylesheet" type="text/css" href="${contextPath}/build/reset/reset.css"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/build/fonts/fonts.css"/>

        <link rel="stylesheet" type="text/css" href="${contextPath}/build/container/assets/skins/sam/container.css"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/build/menu/assets/skins/sam/menu.css"/> 
 
		<link rel="stylesheet" type="text/css" href="${contextPath}/build/fonts/fonts-min.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/build/datatable/assets/skins/sam/datatable.css" />
		<script type="text/javascript" src="${contextPath}/build/yahoo-dom-event/yahoo-dom-event.js"></script>

		<script type="text/javascript" src="${contextPath}/build/element/element-min.js"></script>
		<script type="text/javascript" src="${contextPath}/build/datasource/datasource-min.js"></script>
		<script type="text/javascript" src="${contextPath}/build/datatable/datatable-min.js"></script>
        <script type="text/javascript" src="${contextPath}/build/utilities/utilities.js"></script>
        <script type="text/javascript" src="${contextPath}/build/container/container.js"></script>
        <script type="text/javascript" src="${contextPath}/build/menu/menu.js"></script>
        
		<link rel="stylesheet" type="text/css" href="${contextPath}/build/button/assets/skins/sam/button.css" />
		<script type="text/javascript" src="${contextPath}/build/yahoo-dom-event/yahoo-dom-event.js"></script>
		
		<script type="text/javascript" src="${contextPath}/build/button/button-min.js"></script>


        <!-- Page-specific styles -->

        <style type="text/css">

            html {
            
                background-color: #dfb8df;
            
            }
            
            em#yahoolabel {

                text-indent: -6em;
                display: block;
                background: url(http://l.yimg.com/a/i/us/nt/b/purpley.1.0.gif) center center no-repeat;
                width: 2em;
                overflow: hidden;

            }


            /*
                Setting the "zoom" property to "1" triggers the "hasLayout" 
                property in IE.  This is necessary to fix a bug IE where 
                mousing mousing off a the text node of MenuItem instance's 
                text label, or help text without the mouse actually exiting the
                boundaries of the MenuItem instance will result in the losing  
                the background color applied when it is selected.
            */
            
            #filemenu.visible .yuimenuitemlabel,
            #editmenu.visible .yuimenuitemlabel {

                *zoom: 1;

            }


			/*
				Remove "hasLayout" from the submenu of the file menu.			
			*/

            #filemenu.visible .yuimenu .yuimenuitemlabel {

                *zoom: normal;

            }
        
        </style>

        <!-- Page-specific script -->

        <script type="text/javascript">
	    /*
             Initialize and render the MenuBar when the page's DOM is ready 
             to be scripted.
        */

        
        YAHOO.util.Event.onDOMReady(function () {

			var onMenuItemClick = function () {
				//alert("Callback for MenuItem: " + this.cfg.getProperty("text"));
				
			};

			
            var aItemData = [
                { 
                    text: "FENAPES", 
                    submenu: { 
                        id: "fenapes"
                    }
                    
                },
                {
                    text: "Trabajar Con", 
                    submenu: { 
                        id: "afimenu", 
                        itemdata:
                            [ 
                                { text: "Afiliados Mantenimiento",  url:"../afiliado/AfiliadoList.action" , onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } },
                                { text: "Convenios Movimientos ",  url:"../movimiento/MovimientoList.action" , onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } }
                         ]}

                }

                ,
                {
                    text: "Gestion D3", 
                    submenu: { 
                        id: "genmenu", 
                        itemdata:
                            [ 
								{ text: "Consultas",  url:"../generar/ConsultarD3.action" , onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } },
                                { text: "Archivos",  url:"../generar/GenerarList.action" , onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } },
                                { text: "Generar",  url:"../generar/GenerarD3.action" , onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } }
                         ]}

                },
                {
                    text: "Listados", 
                    submenu: { 
                        id: "lismenu", 
                        itemdata:
                            [ 
                                { text: "Convenios por afiliado ordenados por Cobro",  url:"../lista/ConveniosXAfiliadoCobro.action" , onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } },
                                { text: "Convenios por afiliado ordenados por C.I",  url:"../lista/ConveniosXAfiliadoCI.action" , onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } },
                                { text: "Afiliados por convenios ordenados por Cobro",  url:"../lista/AfiliadosXConvenioCobro.action" , onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } },
                                { text: "Afiliados por convenio",  url:"../lista/AfiliadosXConvenioFiltro.action" , onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } },
                                { text: "Afiliados por region ordenados por numero de Cobro",  url:"../lista/AfiliadosXRegion.action" , onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } },
                                { text: "Carta Nuevas Afiliaciones",  url:"../lista/AltasAfiliados.action" , onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } },
                                { text: "Carta Bajas Afiliados",  url:"../lista/BajasAfiliados.action" , onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } }
                         ]}

                }
                ,
                
                {
                    text: "Administrar", 
                    submenu: { 
                        id: "editmenu", 
                        itemdata: [

                            [ 
                                { text: "Convenios",  url:"../convenio/ConvenioList.action", onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } },
                                { text: "Lugares",  url:"../ubicacion/LugarList.action" , onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } },
                                { text: "Regiones",  url:"../ubicacion/RegionList.action" , onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } },
                                { text: "Liceos",  url:"../ubicacion/LiceoList.action" , onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } },
                                { text: "Rubros",  url:"../rubro/RubroList.action" , onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 90 } } 
                             ]
                         ] }

                }
                ,
                { 
                    text: "Seguridad", 
                    submenu: {  
                        id: "filemenu", 
                        itemdata: [

                            { text: "Mantenimiento Usuarios", helptext: ".", url:"../usuarios/UsuarioList.action" ,onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 78 } },
                            { text: "Cambio Password", helptext: ".", url:"../usuarios/CambioPassword.action" ,onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 79 } },
                            { text: "Salir", helptext: ".", url:"../usuarios/Login.action" ,onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 78 } }
                        ] 
                    }
                
                }
                ,
                { 
                    text: "Ayuda", 
                    submenu: {  
                        id: "ayudamenu", 
                        itemdata: [

                            { text: "Acerca De", helptext: ".", url:"../usuarios/AcercaDe.action" ,onclick: { fn: onMenuItemClick }, keylistener: { ctrl: true, keys: 78 } }
                        ] 
                    }
                
                }
            ];


           
            
            /*
				Instantiate a Menu:  The first argument passed to the constructor
				is the id for the Menu element to be created, the second is an 
				object literal of configuration properties.
            */

            var oMenuBar = new YAHOO.widget.MenuBar("mymenubar", { 
                                                        lazyload: true, 
                                                        itemdata: aItemData 
                                                        });


            /*
                 Since this MenuBar instance is built completely from 
                 script, call the "render" method passing in a node 
                 reference for the DOM element that its should be 
                 appended to.
            */

            oMenuBar.render(document.body);


            // Add a "show" event listener for each submenu.
            
            function onSubmenuShow() {

				var oIFrame,
					oElement,
                    nOffsetWidth;


				// Keep the left-most submenu against the left edge of the browser viewport

				if (this.id == "fenapes") {
					
					YAHOO.util.Dom.setX(this.element, 0);

					oIFrame = this.iframe;            
		

					if (oIFrame) {
			
						YAHOO.util.Dom.setX(oIFrame, 0);
			
					}
					
					this.cfg.setProperty("x", 0, true);
				
				}


				/*
					Need to set the width for submenus of submenus in IE to prevent the mouseout 
					event from firing prematurely when the user mouses off of a MenuItem's 
					text node.
				*/

                if (( this.id =="ayudamenu"|| this.id =="lismenu"||this.id =="afimenu" || this.id == "filemenu" || this.id == "editmenu" || this.id == "genmenu") && YAHOO.env.ua.ie) {

                    oElement = this.element;
                    nOffsetWidth = oElement.offsetWidth;
            
                    /*
                        Measuring the difference of the offsetWidth before and after
                        setting the "width" style attribute allows us to compute the 
                        about of padding and borders applied to the element, which in 
                        turn allows us to set the "width" property correctly.
                    */
                    
                    oElement.style.width = nOffsetWidth + "px";
                    oElement.style.width = (nOffsetWidth - (oElement.offsetWidth - nOffsetWidth)) + "px";
                
                }

            }
            

            // Subscribe to the "show" event for each submenu
            
            oMenuBar.subscribe("show", onSubmenuShow);

            //var oPanel = new YAHOO.widget.Panel("exampleinfo", { constraintoviewport: true, fixedcenter: true, width: "400px", zIndex: 1});
            
            //oPanel.setHeader("Application Menubar Example");
            //oPanel.setBody("This example demonstrates how to create an application-like menu bar using JavaScript.");

            //oPanel.render(document.body);


			// Hide any currently visible submenus of the menubar when the dialog is clicked
			//YAHOO.util.Event.on(oPanel.element, "mousedown", oMenuBar.clearActiveItem, null, oMenuBar);
			<% if(request.getParameter("msg")!=null ){ %>

				YAHOO.namespace("example.container");
				// Instantiate the Dialog
				YAHOO.example.container.simpledialog1 = new YAHOO.widget.SimpleDialog("simpledialog1", 
																						 { width: "300px",
																						   fixedcenter: true,
																						   visible: false,
																						   draggable: false,
																						   close: true,
																						   text: '<%=request.getParameter("msg")%>',
																						   icon: YAHOO.widget.SimpleDialog.ICON_WARN,
																						   constraintoviewport: true
																						 } );
				YAHOO.example.container.simpledialog1.setHeader('<%=request.getParameter("tit")%>');
				
				// Render the Dialog
				YAHOO.example.container.simpledialog1.render("container");
				YAHOO.example.container.simpledialog1.show();
		
			<%}%>


  
        });

		function ConfirmarEliminar(url){
			if(confirm('Está a punto de eliminar información, ¿desea continuar?')){
				document.location.href=url;
			}
		}

		function EjFiltro(numeroPagina,n){
			document.forms[n].p.value = numeroPagina;
			document.forms[n].submit();
		}

		function CheckDig(idField){
			var s= idField.value;
			var d= ""
			for(i=0; i<s.length;i++)
			{
				try
				{
					if(!isNaN(parseInt(s[i])))
					{
						d= d + parseInt(s[i]);
					}
				}catch(e){
					
				}
			}
			
			idField.value = d;
		}
		function CheckLen(idField,n){
			var s= idField.value;
			var d= ""
			for(i=0; i<s.length && i<n ;i++)
			{
				try
				{
					d = d + s[i];
				}catch(e){
					
				}
			}
			
			idField.value = d;
		}


		function FormatoDate(d){
			var dig= d.value.charAt(d.length-1);
			var s=d.value;

			if(isNaN(parseInt(dig)) )
			{
				s=d.value.substring(0,d.length-2);
			}
			if(s.length==2)
			{
				s=s+"/";
			}
			if(s.length==5)
			{
				s=s+"/"
			}
			if(s.length==10)
			{
				try{
					var d1=new Date( parseInt(s.charAt(6)+s.charAt(7)) , parseInt(s.charAt(3)+s.charAt(4)),parseInt(s.charAt(0)+s.charAt(1)));
					
				}catch(e){
					alert('Fecha inválida.')
				}
			}

			if(s.length>10){
				s=s.substring(0,10);
			}
			
			d.value=s;
		}
        </script>
	
	<title>Afiliados FENAPES</title>
	<decorator:head />
</head>


<body class="yui-skin-sam" >


<div class="bodyhead">
  <h2><decorator:title /></h2>
</div>

<div class="body" style="height: 500px">
	<div id="container"/>
	<decorator:body />
</div>

</body>
</html>