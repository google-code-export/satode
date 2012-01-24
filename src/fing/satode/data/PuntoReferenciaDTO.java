package fing.satode.data;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;



public class PuntoReferenciaDTO implements Serializable, IsSerializable {

		private static final long serialVersionUID = 1L;
		private Long id;
		
		private boolean puntoEntrada;
		
		private boolean puntoEntrega;
		
		private CiudadDTO ciudad;
		 
		private DepartamentoDTO departamento;
		
		private String direccion;
		
		private String telefono;
		
		private int tipo;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public boolean isPuntoEntrada() {
			return puntoEntrada;
		}

		public void setPuntoEntrada(boolean puntoEntrada) {
			this.puntoEntrada = puntoEntrada;
		}

		public boolean isPuntoEntrega() {
			return puntoEntrega;
		}

		public void setPuntoEntrega(boolean puntoEntrega) {
			this.puntoEntrega = puntoEntrega;
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

		public int getTipo() {
			return tipo;
		}

		public void setTipo(int tipo) {
			this.tipo = tipo;
		}

		

}
