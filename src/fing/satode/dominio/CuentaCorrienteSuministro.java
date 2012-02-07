package fing.satode.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fing.satode.data.CuentaCorrienteSuministroDTO;

@Entity
@Table(name="cuentacorrientesuministros")
public class CuentaCorrienteSuministro {

		@Id @GeneratedValue
		private Long id;
		
		@ManyToOne
		@JoinColumn(name="tiposuministro_id")
		private TipoSuministro tipoSuministro;
		
		@ManyToOne
		@JoinColumn(name="deposito_id")
		private Deposito deposito;
		
		private float cantidad;
		
		public CuentaCorrienteSuministro(){}
		
		public CuentaCorrienteSuministro(CuentaCorrienteSuministroDTO dto){
			id=dto.getId();
			cantidad= dto.getCantidad();
			tipoSuministro= new TipoSuministro(dto.getTipoSuministro());
			deposito=new Deposito(dto.getDeposito());
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public TipoSuministro getTipoSuministro() {
			return tipoSuministro;
		}

		public void setTipoSuministro(TipoSuministro tipoSuministro) {
			this.tipoSuministro = tipoSuministro;
		}

		public Deposito getDeposito() {
			return deposito;
		}

		public void setDeposito(Deposito deposito) {
			this.deposito = deposito;
		}

		public float getCantidad() {
			return cantidad;
		}

		public void setCantidad(float cantidad) {
			this.cantidad = cantidad;
		}
		
		public CuentaCorrienteSuministroDTO getDTO(){
			CuentaCorrienteSuministroDTO dto = new CuentaCorrienteSuministroDTO();
			dto.setId(id);
			dto.setCantidad(cantidad);
			dto.setTipoSuministro(tipoSuministro.getDTO());
			dto.setDeposito(deposito.getDTO());
			
			return dto;
		}
		
		
		

	}
