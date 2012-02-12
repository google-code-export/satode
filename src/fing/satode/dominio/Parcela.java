package fing.satode.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
	
	@OneToMany(
	        targetEntity=fing.satode.dominio.Foto.class,
	        cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
        name="fotos",
        joinColumns=@JoinColumn(name="id"),
        inverseJoinColumns=@JoinColumn(name="parcela_id")
    )
	private Collection<Foto> fotos;
	
	@OneToMany(
	        targetEntity=fing.satode.dominio.Foto.class,
	        cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
        name="unidadesparcela",
        joinColumns=@JoinColumn(name="id"),
        inverseJoinColumns=@JoinColumn(name="parcela_id")
    )
	private Collection<UnidadParcela> unidadesParcelas;
	
	@ManyToOne
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
	
	public Parcela(ParcelaDTO dto){
		id=dto.getId();
		fotos= new ArrayList<Foto>();
		for (FotoDTO fotoDto : dto.getFotos()) {
			fotos.add(new Foto(fotoDto));
		}
		unidadesParcelas = new ArrayList<UnidadParcela>();
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
