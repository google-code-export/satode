package fing.satode.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;


public class DonacionDTO  implements Serializable, IsSerializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date fecha;
	private String donante;
	private boolean impactarCuentas;
	private ArrayList<SuministroDTO>  suministros;
	private DepositoDTO deposito;
	private PuntoReferenciaDTO puntoEntrada;
	
	
	public DepositoDTO getDeposito() {
		return deposito;
	}

	public void setDeposito(DepositoDTO deposito) {
		this.deposito = deposito;
	}

	public DonacionDTO(){
		suministros= new ArrayList<SuministroDTO>();
	}
	
	public boolean isImpactarCuentas() {
		return impactarCuentas;
	}

	public void setImpactarCuentas(boolean impactarCuentas) {
		this.impactarCuentas = impactarCuentas;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDonante() {
		return donante;
	}
	public void setDonante(String donante) {
		this.donante = donante;
	}
	public ArrayList<SuministroDTO> getSuministros() {
		return suministros;
	}
	public void setSuministros(ArrayList<SuministroDTO> suministros) {
		this.suministros = suministros;
	}

	public PuntoReferenciaDTO getPuntoEntrada() {
		return puntoEntrada;
	}

	public void setPuntoEntrada(PuntoReferenciaDTO puntoEntrada) {
		this.puntoEntrada = puntoEntrada;
	}
	
	
	
}
