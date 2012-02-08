package fing.satode.data;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.gwt.user.client.rpc.IsSerializable;

import fing.satode.dominio.Ciudad;

public class UnidadParcelaDTO implements Serializable, IsSerializable {

		private static final long serialVersionUID = 1L;
		private Long id;
		
		private String descripcion;
		private Float nivelAgua;
		private int metros2afectados;
		private int nivelPiso;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public Float getNivelAgua() {
			return nivelAgua;
		}
		public void setNivelAgua(Float nivelAgua) {
			this.nivelAgua = nivelAgua;
		}
		public int getMetros2afectados() {
			return metros2afectados;
		}
		public void setMetros2afectados(int metros2afectados) {
			this.metros2afectados = metros2afectados;
		}
		public int getNivelPiso() {
			return nivelPiso;
		}
		public void setNivelPiso(int nivelPiso) {
			this.nivelPiso = nivelPiso;
		}
		
		
}
