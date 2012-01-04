package fing.satode.dominio;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fing.satode.data.DepositoDTO;


@Entity
@Table(name="depositos")
public class Deposito {

	@Id @GeneratedValue
	private Long id;
	
	private String direccion;
	private String telefono;
	private String responsable;
	private String mail;
	private Float area2;
	private Float area3;
	 
	 @ManyToOne
	 @JoinColumn(name="ciudad_id")
	private Ciudad ciudad;
	 
		@ManyToOne
	    @JoinColumn(name="departamento_id")
		private Departamento departamento;
		 
	 
	
	public Deposito(){}
	
	public Deposito(DepositoDTO dto){
		id= dto.getId();
		direccion = dto.getDireccion();
		telefono = dto.getTelefono();
		responsable = dto.getResponsable();
		mail = dto.getMail();
		area2 = dto.getArea2();
		area3 = dto.getArea3();
		ciudad = new Ciudad(dto.getCiudad());
		departamento = new Departamento(dto.getDepartamento());
	
	}

	public String getDireccion() {
		return direccion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Float getArea2() {
		return area2;
	}

	public void setArea2(Float area2) {
		this.area2 = area2;
	}

	public Float getArea3() {
		return area3;
	}

	public void setArea3(Float area3) {
		this.area3 = area3;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	public DepositoDTO getDTO() {
		DepositoDTO dto= new DepositoDTO();
		dto.setId(id);
		dto.setDireccion(direccion);
		dto.setTelefono(telefono);
		dto.setMail(mail);
		dto.setArea2(area2);
		dto.setArea3(area3);
		dto.setCiudad(ciudad.getDTO());
		dto.setDepartamento(departamento.getDTO());
		dto.setResponsable(responsable);
		return dto;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	
	
}
