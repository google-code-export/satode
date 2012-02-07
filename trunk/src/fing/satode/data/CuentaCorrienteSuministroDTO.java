package fing.satode.data;

import java.io.Serializable;
import com.google.gwt.user.client.rpc.IsSerializable;

public class CuentaCorrienteSuministroDTO implements Serializable, IsSerializable {

	
	private static final long serialVersionUID = 1L;
	private Long id;
	private TipoSuministroDTO tipoSuministro;
	private DepositoDTO deposito;
	private float cantidad;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TipoSuministroDTO getTipoSuministro() {
		return tipoSuministro;
	}
	public void setTipoSuministro(TipoSuministroDTO tipoSuministro) {
		this.tipoSuministro = tipoSuministro;
	}
	public DepositoDTO getDeposito() {
		return deposito;
	}
	public void setDeposito(DepositoDTO deposito) {
		this.deposito = deposito;
	}
	public float getCantidad() {
		return cantidad;
	}
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	
		
}
