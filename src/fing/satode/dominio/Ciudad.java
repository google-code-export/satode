package fing.satode.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import fing.satode.data.CiudadDTO;
@Entity
@Table(name="ciudades")
public class Ciudad implements Serializable{
	
	   private static final long serialVersionUID = 1L;

		@Id @GeneratedValue
		private Long id;
		
		private String nombre;
		 
		public Ciudad(CiudadDTO dto){
			setId(dto.getId());
			setNombre(dto.getNombre());
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

			 
		public CiudadDTO getDTO(){
			CiudadDTO dto = new CiudadDTO();
			dto.setId(id);
			dto.setNombre(nombre);
			return dto;
		}
}
