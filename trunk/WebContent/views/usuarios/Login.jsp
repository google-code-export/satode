<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style type="text/css">
.frm {
    border: 1px solid #666666;
    text-align: left;
     top: 40px;
    left: 15px;
}

</style>
</head>




<body>
<table width="100%" height="100%" >
<tr>
<td valign="center" align="left">
	
	<s:form cssClass="frm">
		<s:textfield name="usuario" label="Usuario" /> 
		<s:password name="password" label="Password" />
		<s:submit value="Confirmar" ></s:submit>
		<input type="hidden" value="true" name="isPostBack"/>
	</s:form>

</td>
</tr>
</table>

</body>
</html>