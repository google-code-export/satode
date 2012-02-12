package fing.satode.data;

import java.io.Serializable;
import java.util.Collection;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ParcelaDTO implements Serializable, IsSerializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Collection<FotoDTO> fotos;
	private Collection<UnidadParcelaDTO> unidadesParcelas;
	private TipoParcelaDTO tipoParcela;
	private CiudadDTO ciudad;
	private DepartamentoDTO departamento;
	private UsuarioDTO usuario;
	private String  direccion;
	private String telefono;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Collection<FotoDTO> getFotos() {
		return fotos;
	}
	public void setFotos(Collection<FotoDTO> fotos) {
		this.fotos = fotos;
	}
	public Collection<UnidadParcelaDTO> getUnidadesParcelas() {
		return unidadesParcelas;
	}
	public void setUnidadesParcelas(Collection<UnidadParcelaDTO> parcelas) {
		this.unidadesParcelas = parcelas;
	}
	public TipoParcelaDTO getTipoParcela() {
		return tipoParcela;
	}
	public void setTipoParcela(TipoParcelaDTO tipoParcela) {
		this.tipoParcela = tipoParcela;
	}
	public CiudadDTO getCiudad() {
		return ciudad;
	}
	public void setCiudad(CiudadDTO ciudad) {
		this.ciudad = ciudad;
	}
	public DepartamentoDTO getDepartamento() {
		return departamento;
	}
	public void setDepartamento(DepartamentoDTO departamento) {
		this.departamento = departamento;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
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
	
	
}
