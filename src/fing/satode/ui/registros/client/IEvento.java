package fing.satode.ui.registros.client;


import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fing.satode.dominio.Evento;



@RemoteServiceRelativePath("evento")
public interface IEvento extends RemoteService {

	public ArrayList<Evento> listaEventos();
}
