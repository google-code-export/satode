package fing.satode.data;

import java.io.Serializable;
import java.util.Date;


import com.google.gwt.user.client.rpc.IsSerializable;


public class CalculoIndiceDTO  implements Serializable, IsSerializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date fecha;
	private float valor;
	private float valorVivindaSocial;
	private float hectariaDeCultivo;
	private String observaciones;
	private UsuarioDTO usuario;
	private int ir1;
	private int ir2;
	private int ir3;
	private int ir4;
	private int ir5;
	private int ir6;
	
	private int rr1;
	private int rr2;
	private int rr3;
	private int rr4;
	private int rr5;
	private int rr6;
	
	private int md1;
	private int md2;
	private int md3;
	private int md4;
	private int md5;
	private int md6;
	
	private int pf1;
	private int pf2;
	private int pf3;
	private int pf4;
	private int pf5;
	private int pf6;
	
	private int tipo;

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public float getValorVivindaSocial() {
		return valorVivindaSocial;
	}

	public void setValorVivindaSocial(float valorVivindaSocial) {
		this.valorVivindaSocial = valorVivindaSocial;
	}

	public float getHectariaDeCultivo() {
		return hectariaDeCultivo;
	}

	public void setHectariaDeCultivo(float hectariaDeCultivo) {
		this.hectariaDeCultivo = hectariaDeCultivo;
	}

	public int getIr1() {
		return ir1;
	}

	public void setIr1(int ir1) {
		this.ir1 = ir1;
	}

	public int getIr2() {
		return ir2;
	}

	public void setIr2(int ir2) {
		this.ir2 = ir2;
	}

	public int getIr3() {
		return ir3;
	}

	public void setIr3(int ir3) {
		this.ir3 = ir3;
	}

	public int getIr4() {
		return ir4;
	}

	public void setIr4(int ir4) {
		this.ir4 = ir4;
	}

	public int getIr5() {
		return ir5;
	}

	public void setIr5(int ir5) {
		this.ir5 = ir5;
	}

	public int getIr6() {
		return ir6;
	}

	public void setIr6(int ir6) {
		this.ir6 = ir6;
	}

	public int getRr1() {
		return rr1;
	}

	public void setRr1(int rr1) {
		this.rr1 = rr1;
	}

	public int getRr2() {
		return rr2;
	}

	public void setRr2(int r2) {
		this.rr2 = r2;
	}

	public int getRr3() {
		return rr3;
	}

	public void setRr3(int rr3) {
		this.rr3 = rr3;
	}

	public int getRr4() {
		return rr4;
	}

	public void setRr4(int rr4) {
		this.rr4 = rr4;
	}

	public int getRr5() {
		return rr5;
	}

	public void setRr5(int rr5) {
		this.rr5 = rr5;
	}

	public int getRr6() {
		return rr6;
	}

	public void setRr6(int rr6) {
		this.rr6 = rr6;
	}

	public int getMd1() {
		return md1;
	}

	public void setMd1(int md1) {
		this.md1 = md1;
	}

	public int getMd2() {
		return md2;
	}

	public void setMd2(int md2) {
		this.md2 = md2;
	}

	public int getMd3() {
		return md3;
	}

	public void setMd3(int md3) {
		this.md3 = md3;
	}

	public int getMd4() {
		return md4;
	}

	public void setMd4(int md4) {
		this.md4 = md4;
	}

	public int getMd5() {
		return md5;
	}

	public void setMd5(int md5) {
		this.md5 = md5;
	}

	public int getMd6() {
		return md6;
	}

	public void setMd6(int md6) {
		this.md6 = md6;
	}

	public int getPf1() {
		return pf1;
	}

	public void setPf1(int pf1) {
		this.pf1 = pf1;
	}

	public int getPf2() {
		return pf2;
	}

	public void setPf2(int pf2) {
		this.pf2 = pf2;
	}

	public int getPf3() {
		return pf3;
	}

	public void setPf3(int pf3) {
		this.pf3 = pf3;
	}

	public int getPf4() {
		return pf4;
	}

	public void setPf4(int pf4) {
		this.pf4 = pf4;
	}

	public int getPf5() {
		return pf5;
	}

	public void setPf5(int pf5) {
		this.pf5 = pf5;
	}

	public int getPf6() {
		return pf6;
	}

	public void setPf6(int pf6) {
		this.pf6 = pf6;
	}
	
	
	
	
}
