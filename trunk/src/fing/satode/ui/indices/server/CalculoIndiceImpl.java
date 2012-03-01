package fing.satode.ui.indices.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.hibernate.HibernateException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

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
		
	}
}
