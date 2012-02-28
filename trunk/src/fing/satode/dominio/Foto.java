package fing.satode.dominio;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;

import fing.satode.data.CiudadDTO;
import fing.satode.data.FotoDTO;
@Entity
@Table(name="fotos")
public class Foto implements Serializable{
	
	   private static final long serialVersionUID = 1L;

		@Id @GeneratedValue
		private Long id;
		
		@Column(length=5200000)
		private byte[] datos;
		
		private boolean antes;
		
		private String nombre;
		
		 
		public Foto(){}
		
		public Foto(FotoDTO dto){
			setId(dto.getId());
			setDatos(dto.getDatos());
			setAntes(dto.isAntes());
			setNombre(dto.getNombre());
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		
			 
		public  byte[]  getDatos() {
			return datos;
		}

		public void setDatos( byte[]  datos) {
			this.datos = datos;
		}

		public boolean isAntes() {
			return antes;
		}

		public void setAntes(boolean antes) {
			this.antes = antes;
		}

		public FotoDTO getDTO(){
			FotoDTO dto = new FotoDTO();
			dto.setId(id);
			dto.setDatos(datos);
			dto.setAntes(antes);
			dto.setNombre(nombre);
			return dto;
		}
}
