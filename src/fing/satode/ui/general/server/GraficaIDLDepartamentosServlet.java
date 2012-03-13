package fing.satode.ui.general.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.hibernate.HibernateException;

import fing.satode.pl.base.DAOBase;


public class GraficaIDLDepartamentosServlet  extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException{
		 
		    Map  parameters = new HashMap();										
			
			parameters.put("id",Long.valueOf(request.getParameter("id")));
			 
		    JasperPrint jasperPrint=null;;
			try {
				jasperPrint = JasperFillManager.fillReport(request.getRealPath("/reports/GrafIDLDeptos.jasper"), parameters,new DAOBase().sess().connection());
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
			//resp.setContentType("Aplication/pdf");
			//resp.setHeader("Content-Disposition", "attachment; filename=GraficosIDL.pdf");
			//resp.setHeader("Pragma", "public");
			//resp.setHeader("Cache-Control", "public");
			
			// realizo la exportación
			ServletOutputStream outputStream=null;
			try {
				outputStream = resp.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//JRPdfExporter exporter = new JRPdfExporter();
		    
			//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			try {
				JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);
				//exporter.exportReport();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	 }
}
