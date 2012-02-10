package fing.satode.dominio;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import fing.satode.data.CiudadDTO;
import fing.satode.data.FotoDTO;
@Entity
@Table(name="fotos")
public class Foto implements Serializable{
	
	   private static final long serialVersionUID = 1L;

		@Id @GeneratedValue
		private Long id;
		
		private ArrayList<Byte> datos;
		
		private boolean antes;
		
		
		 
		public Foto(){}
		
		public Foto(FotoDTO dto){
			setId(dto.getId());
			setDatos(dto.getDatos());
			setAntes(dto.isAntes());
			
		}
		 
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		
			 
		public ArrayList<Byte> getDatos() {
			return datos;
		}

		public void setDatos(ArrayList<Byte> datos) {
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
			return dto;
		}
}
