package fing.satode.data;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.gwt.user.client.rpc.IsSerializable;

import fing.satode.dominio.Ciudad;

public class DepositoDTO implements Serializable, IsSerializable {

		private static final long serialVersionUID = 1L;
		private Long id;
		
		private String direccion;
		private String telefono;
		private String responsable;
		private String mail;
		private Float area2;
		private Float area3;
		
		private CiudadDTO ciudad;
		private DepartamentoDTO departamento;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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
		
		public String toString(){
			return id+"-"+departamento.getNombre()+"-"+ciudad.getNombre()+"-"+direccion;
		}

}
