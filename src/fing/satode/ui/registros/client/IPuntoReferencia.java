package fing.satode.ui.puntoReferencias.client;


import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


import fing.satode.data.PuntoReferenciaDTO;
import fing.satode.dominio.PuntoReferencia;


@RemoteServiceRelativePath("puntoReferencia")
public interface IPuntoReferencia extends RemoteService {

	public ArrayList<PuntoReferencia> listaPuntoReferencia();
	public void nuevoPuntoReferencia(PuntoReferenciaDTO dto);
	public void modificarPuntoReferencia(PuntoReferenciaDTO dto);
	public void eliminarPuntoReferencia(PuntoReferenciaDTO dto);
}
