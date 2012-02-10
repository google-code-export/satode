package fing.satode.ui.general.data;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.TextBox;

import fing.satode.data.DepositoDTO;

public class DepositoCantidad implements Serializable, IsSerializable {

	private static final long serialVersionUID = 1L;
	
	public DepositoDTO getDeposito() {
		return deposito;
	}
	public void setDeposito(DepositoDTO deposito) {
		this.deposito = deposito;
	}
	public TextBox getCantidad() {
		return cantidad;
	}
	public void setCantidad(TextBox cantidad) {
		this.cantidad = cantidad;
	}
	private DepositoDTO deposito;
	private TextBox cantidad;
	
	
	
}
