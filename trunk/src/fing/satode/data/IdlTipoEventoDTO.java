package fing.satode.data;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class IdlTipoEventoDTO implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private float pocentaje;
	
	private ArrayList<EventoDTO> eventos;

	private TipoEventoDTO tipoEvento;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoEventoDTO getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEventoDTO tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public float getPocentaje() {
		return pocentaje;
	}

	public void setPocentaje(float pocentaje) {
		this.pocentaje = pocentaje;
	}

	public ArrayList<EventoDTO> getEventos() {
		return eventos;
	}

	public void setEventos(ArrayList<EventoDTO> eventos) {
		this.eventos = eventos;
	}
	
	
}
