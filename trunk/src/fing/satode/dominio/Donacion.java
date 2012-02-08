package fing.satode.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.jasper.tagplugins.jstl.Otherwise;

import fing.satode.constantes.TipoPuntoReferencia;
import fing.satode.data.DonacionDTO;
import fing.satode.data.PuntoReferenciaDTO;
import fing.satode.data.SuministroDTO;

@Entity
@Table(name="donaciones")
public class Donacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;

	private Date fecha;
	
	private String donante;
	
	private boolean impactarCuentas;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="donacion_id")
	private Collection<Suministro> suministros;

	@ManyToOne
    @JoinColumn(name="deposito_id")
	private Deposito deposito;
	
	@ManyToOne
    @JoinColumn(name="puntoReferencia_id")
	private PuntoReferencia puntoEntrada;
	
	
	public Donacion(){}
	 
	 public Donacion(DonacionDTO dto){
		 id=dto.getId();
		 donante=dto.getDonante();
		 fecha=dto.getFecha();
		 suministros = new ArrayList<Suministro>();
		 impactarCuentas =dto.isImpactarCuentas();
		 for(SuministroDTO dtoSum:dto.getSuministros()){
			 suministros.add(new Suministro(dtoSum));
		 }
		 deposito= new Deposito(dto.getDeposito());
		 puntoEntrada= Util.crearPuntoReferencia(dto.getPuntoEntrada());
			
	 }
	 
	 
	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
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

	public Collection<Suministro> getSuministros() {
		return suministros;
	}

	public void setSuministros(Collection<Suministro> suministros) {
		this.suministros = suministros;
	}
	 
	public PuntoReferencia getPuntoEntrada() {
		return puntoEntrada;
	}

	public void setPuntoEntrada(PuntoReferencia puntoEntrada) {
		this.puntoEntrada = puntoEntrada;
	}

	public DonacionDTO getDTO(){
		DonacionDTO dto= new DonacionDTO();
		dto.setFecha(fecha);
		dto.setId(id);
		dto.setDonante(donante);
		ArrayList<SuministroDTO> lista= new ArrayList<SuministroDTO>();
		for(Suministro s: suministros){
			lista.add(s.getDTO());
		}
		dto.setSuministros(lista);
		dto.setImpactarCuentas(impactarCuentas);
		dto.setDeposito(deposito.getDTO());
		dto.setPuntoEntrada(Util.crearPuntoReferenciaDTO(puntoEntrada));
		return dto;
	}
	 
}
