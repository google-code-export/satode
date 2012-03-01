package fing.satode.ui.indices.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.hibernate.HibernateException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRRtfExporter;

import com.google.gwt.dev.util.collect.HashMap;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.data.CalculoIndiceDTO;
import fing.satode.pl.base.DAOBase;
import fing.satode.ui.base.ServerImpl;
import fing.satode.ui.indices.client.IIndices;

public class CalculoIndiceImpl extends ServerImpl implements IIndices {

	private static final long serialVersionUID = 1L;

	
	@Override
	public ArrayList<CalculoIndiceDTO> buscarCalculoIndice(int tipo) {
		return 	ServiceFactory.getInstance().getIndicesService().buscarCalculoIndice(tipo);
	}

	@Override
	public void calcularIDL(CalculoIndiceDTO dto) {
		ServiceFactory.getInstance().getIndicesService().calcularIDL(dto);
	}

	public void exportarGraficosIDL() {
		Map  parameters = new HashMap();										
		
		parameters.put("tipo", 1);
		 
	    JasperPrint jasperPrint=null;;
		try {
			jasperPrint = JasperFillManager.fillReport(perThreadRequest.get().getRealPath("/reports/GraficosIDL.jasper"), parameters,new DAOBase().sess().connection());
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //JasperExportManager.exportReportToPdfFile(jasperPrint, request.getRealPath("report/ConveniosXAfiliado.rtf"));
         
	    perThreadResponse.get().setContentType("Aplication/rtf");
	    perThreadResponse.get().setHeader("Content-Disposition", "attachment; filename=GraficosIDL.rtf");
	    perThreadResponse.get().setHeader("Pragma", "public");
	    perThreadResponse.get().setHeader("Cache-Control", "public");
		
		// realizo la exportación
		ServletOutputStream outputStream=null;
		try {
			outputStream = perThreadResponse.get().getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JRRtfExporter exporter = new JRRtfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		try {
			exporter.exportReport();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
