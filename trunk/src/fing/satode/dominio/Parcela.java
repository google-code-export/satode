package fing.satode.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fing.satode.data.CiudadDTO;
import fing.satode.data.DepartamentoDTO;
import fing.satode.data.EventoDTO;
import fing.satode.data.FotoDTO;
import fing.satode.data.ParcelaDTO;
import fing.satode.data.SuministroDTO;
import fing.satode.data.TipoParcelaDTO;
import fing.satode.data.UnidadParcelaDTO;
import fing.satode.data.UsuarioDTO;

@Entity
@Table(name="parcelas")
public class Parcela implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
   private Set<Foto> fotos;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<UnidadParcela> unidadesParcelas;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="tipoparcela_id")
	private TipoParcela tipoParcela;
	
	
	@ManyToOne
    @JoinColumn(name="ciudad_id")
	private Ciudad ciudad;
	
	@ManyToOne
    @JoinColumn(name="departamento_id")
	private Departamento departamento;
	
	@ManyToOne
	 @JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	
	private String  direccion;
	
	private String telefono;
	
	
	public Parcela(){}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Set<Foto> getFotos() {
		return fotos;
	}


	public void setFotos(Set<Foto> fotos) {
		this.fotos = fotos;
	}


	public Set<UnidadParcela> getUnidadesParcelas() {
		return unidadesParcelas;
	}


	public void setUnidadesParcelas(Set<UnidadParcela> unidadesParcelas) {
		this.unidadesParcelas = unidadesParcelas;
	}


	public TipoParcela getTipoParcela() {
		return tipoParcela;
	}


	public void setTipoParcela(TipoParcela tipoParcela) {
		this.tipoParcela = tipoParcela;
	}


	public Ciudad getCiudad() {
		return ciudad;
	}


	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}


	public Departamento getDepartamento() {
		return departamento;
	}


	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Parcela(ParcelaDTO dto){
		id=dto.getId();
		fotos= new HashSet<Foto>();
		for (FotoDTO fotoDto : dto.getFotos()) {
			fotos.add(new Foto(fotoDto));
		}
		unidadesParcelas = new HashSet<UnidadParcela>();
		for( UnidadParcelaDTO parcelaDto: dto.getUnidadesParcelas()){
			unidadesParcelas.add(new UnidadParcela(parcelaDto));
		}
		tipoParcela= new TipoParcela(dto.getTipoParcela());
		ciudad = new Ciudad(dto.getCiudad());
		departamento = new Departamento(dto.getDepartamento());
		usuario= new Usuario(dto.getUsuario());
		direccion= dto.getDireccion();
		telefono= dto.getTelefono();
	}

	
	
	public ParcelaDTO getDTO(){
		ParcelaDTO dto= new ParcelaDTO();
		dto.setId(id);
		ArrayList<FotoDTO> lista= new ArrayList<FotoDTO>();
		for(Foto f: fotos){
			lista.add(f.getDTO());
		}
		dto.setFotos(lista);
		ArrayList<UnidadParcelaDTO> listaUnidades= new ArrayList<UnidadParcelaDTO>();
		for(UnidadParcela u: unidadesParcelas){
			listaUnidades.add(u.getDTO());
		}
		dto.setUnidadesParcelas(listaUnidades);
		dto.setTipoParcela(tipoParcela.getDTO());
		dto.setCiudad(ciudad.getDTO());
		dto.setDepartamento(departamento.getDTO());
		dto.setUsuario(usuario.getDTO());
		dto.setDireccion(direccion);
		dto.setTelefono(telefono);
		
		return dto;
	}

	
}
