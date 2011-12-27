package fing.satode.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fing.satode.data.CiudadDTO;
@Entity
@Table(name="ciudades")
public class Ciudad {
	
		
		@Id @GeneratedValue
		private Long id;
		
		private String nombre;
		 
		@ManyToOne
		@JoinColumn(name="departamento_id")
		private Departamento departamento;

		public Ciudad(CiudadDTO dto){
			setId(dto.getId());
			setNombre(dto.getNombre());
			setDepartamento(new Departamento(dto.getDepartamento()));
		}
		 
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public Departamento getDepartamento() {
			return departamento;
		}

		public void setDepartamento(Departamento departamento) {
			this.departamento = departamento;
		}
		 
		public CiudadDTO getDTO(){
			CiudadDTO dto = new CiudadDTO();
			dto.setId(id);
			dto.setNombre(nombre);
			dto.setDepartamento(departamento.getDTO());
			
			return dto;
		}
}
