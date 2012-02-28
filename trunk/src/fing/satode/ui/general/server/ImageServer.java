package fing.satode.ui.general.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.bl.usuarios.UsuarioService;
import fing.satode.data.FotoDTO;
import fing.satode.pl.base.DAOBase;

public class ImageServer extends HttpServlet {

	 public void doGet(HttpServletRequest request, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException{
		 
		 Long id = Long.valueOf(request.getParameter("id"));
		 
		 FotoDTO foto = ServiceFactory.getInstance().getPropiedadesSiniestradasService().getFoto(id);
		 
		 // Get the absolute path of the image
		    ServletContext sc = getServletContext();
		    String filename =foto.getNombre();

		    // Get the MIME type of the image
		    String mimeType = sc.getMimeType(filename);
		    if (mimeType == null) {
		        sc.log("No se encuantra el MIME type de "+filename);
		        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		        return;
		    }

		    resp.setContentType(mimeType);

		    resp.setContentLength(foto.getDatos().length);

		    OutputStream out = resp.getOutputStream();

		    out.write(foto.getDatos(), 0, foto.getDatos().length);
		    out.close();
	 }
}
