<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
	<table width="100%">
	<tr>
		<td valign="top"  align="left">
			Perfiles de usuarios
		</td>
		<td valign="top"  align="right">
			<s:a action="PerfilForm.action?a=nuevo" ><img title="Nuevo" class="Nuevo" src="../assets/Iconos/mas.png"/> </s:a>
		</td>
	</tr>
	</table>
</title>
</head>
<body >

	<table height="100%" width="100%">
	<tr>
	<td  valign="top" align="left" width="20%" >
			<s:form>
					<s:textfield label="Nombre" name="nombre" size="7"  />
					<input type="hidden" value="true" name="isPostBack"  />
					<s:submit value="Buscar" id="filtro" onclick="a.value='buscar';" />
				    <s:hidden name="a" id="a" />
			</s:form>
	</td>
	<td valign="middle" align="left" width="80%">
	<div id="myMarkedUpContainer" >
	    <table id="myTable" >
	     <thead>
	            <tr>
	                <th>Id</th>
	                <th>Nombre</th>
	                <th>Descripcion</th>
	            </tr>
	        </thead>
	        <tbody>
	    	<s:iterator value="Perfiles">
	    		<tr>
	                <td><s:property value="id" /></td>
	                <td><s:property value="nombre" /></td>
	                <td><s:property value="descripcion" /></td>
	                <td>
	               	    <a href="PerfilForm.action?id=<s:property value="id" />&a=modificar" >
					    	<img title="Modificar" class="Modificar" src="../assets/Iconos/modificar.png"/>
					    </a>	
					     <a href="PerfilBaja.action?id=<s:property value="id" />&a=baja"  >
					    	<img title="Baja" class="Baja" src="../assets/Iconos/eliminar.png"/>
					    </a>	
			        </td>
	            </tr>
			</s:iterator>
	       </tbody>
	    </table>
	</div>
	
	</td>
	</tr>
	</table>
	
		
	<script type="text/javascript">
YAHOO.util.Event.addListener(window, "load", function() {
    YAHOO.example.EnhanceFromMarkup = function() {
        var myColumnDefs = [
            {key:"ID",label:"ID", sortable:true},
            {key:"Nombre",label:"Nombre", sortable:true},             
            {key:"Descripcion",label:"Descripcion", sortable:true},            
            {key:"Acciones",label:"Acciones", sortable:false}
        ];

        var parseNumberFromCurrency = function(sString) {
            // Remove dollar sign and make it a float
            return parseFloat(sString.substring(1));
        };

        var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom.get("myTable"));
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
        myDataSource.responseSchema = {
            fields: [{key:"ID"},{key:"Nombre"},{key:"Descripcion"},{key:"Acciones"}
            ]
        };

        var myDataTable = new YAHOO.widget.DataTable("myMarkedUpContainer", myColumnDefs, myDataSource,
                {caption:" ",scrollable:true}
        );
        
        return {
            oDS: myDataSource,
            oDT: myDataTable
        };
    }();
});
</script>
	
</body>

</html>