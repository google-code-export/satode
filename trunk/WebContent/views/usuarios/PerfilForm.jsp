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
			Perfil 
		</td>
		<td valign="top"  align="right">
			<s:a action="PerfilForm.action?a=nuevo"><img title="Nuevo" class="Nuevo" src="../assets/Iconos/mas.png"/> </s:a>
		</td>
	</tr>
	</table>
</title>
</head>
<body>
	<s:form>
			<s:textfield label="Nombre" name="nombre" size="15"  />
			<s:textfield label="Descripcion" name="descripcion" size="25"  />
			
			<input type="hidden" value="true" name="isPostBack"  />
			<s:submit value="Guardar" id="filtro" />
		    <s:hidden name="a" id="a" />
		    <s:hidden name="idsPermisos" id="idsPermisos" />
		    <s:checkboxlist list="permisos" key="clave" value="nombre" > </s:checkboxlist>
	</s:form>
	<table>
	<td>
		
	</td>
	<td>
	</td>
	</table>
	
	<s:a action="PerfilList.action">Cancelar</s:a>
</body>
</html>