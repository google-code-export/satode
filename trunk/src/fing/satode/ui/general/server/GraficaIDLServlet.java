package fing.satode.ui.general.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.apache.commons.fileupload.FileItem;
import org.hibernate.HibernateException;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.data.FotoDTO;
import fing.satode.data.UsuarioDTO;
import fing.satode.pl.base.DAOBase;
import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;
import gwtupload.shared.UConsts;

public class GraficaIDLServlet  extends HttpServlet {

	 public void doGet(HttpServletRequest request, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException{
		 
		    Map  parameters = new HashMap();										
			
			parameters.put("tipo", 1);
			 
		    JasperPrint jasperPrint=null;;
			try {
				jasperPrint = JasperFillManager.fillReport(request.getRealPath("reports/GraficosIDL.jasper"), parameters,new DAOBase().sess().connection());
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
