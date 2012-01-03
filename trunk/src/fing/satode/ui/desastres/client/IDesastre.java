package fing.satode.ui.desastres.client;


import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fing.satode.data.DesastreDTO;



@RemoteServiceRelativePath("desastres")
public interface IDesastre extends RemoteService {


	public ArrayList<DesastreDTO> listaDesastres();
	public void nuevoDesastre(DesastreDTO dto);
	public void modificarDesastre(DesastreDTO dto);
	public void eliminarDesastre(DesastreDTO dto);
	
}
