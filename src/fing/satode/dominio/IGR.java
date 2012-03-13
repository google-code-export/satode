package fing.satode.dominio;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


import fing.satode.data.CalculoIndiceDTO;
import fing.satode.data.IgrDTO;

@Entity @Table(name="indice_igr")
@PrimaryKeyJoinColumn(name="calculoIndice_Id")
public class IGR extends CalculoIndice {

	private static final long serialVersionUID = 1L;

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

	public IGR(){}
	
	public IGR(IgrDTO dto)
	{
		super(dto);
		
		ir1=dto.getIr1();
		ir2=dto.getIr2();
		ir3=dto.getIr3();
		ir4=dto.getIr4();
		ir5=dto.getIr5();
		ir6=dto.getIr6();
		
		rr1=dto.getRr1();
		rr2=dto.getRr2();
		rr3=dto.getRr3();
		rr4=dto.getRr4();
		rr5=dto.getRr5();
		rr6=dto.getRr6();
		
		md1=dto.getMd1();
		md2=dto.getMd2();
		md3=dto.getMd3();
		md4=dto.getMd4();
		md5=dto.getMd5();
		md6=dto.getMd6();
		
		pf1=dto.getPf1();
		pf2=dto.getPf2();
		pf3=dto.getPf3();
		pf4=dto.getPf4();
		pf5=dto.getPf5();
		pf6=dto.getPf6();

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

	public IgrDTO getDTO()
	{
		IgrDTO dto=new IgrDTO();
		
		dto.setId(getId());
		dto.setFecha(getFecha());
		dto.setObservaciones(getObservaciones());
		dto.setUsuario(getUsuario().getDTO());
		dto.setValor(getValor());
		dto.setTipo(getTipo());
		
		dto.setIr1(ir1);
		dto.setIr2(ir2);
		dto.setIr3(ir3);
		dto.setIr4(ir4);
		dto.setIr5(ir5);
		dto.setIr6(ir6);
		
		dto.setMd1(md1);
		dto.setMd2(md2);
		dto.setMd3(md3);
		dto.setMd4(md4);
		dto.setMd5(md5);
		dto.setMd6(md6);

		dto.setPf1(pf1);
		dto.setPf2(pf2);
		dto.setPf3(pf3);
		dto.setPf4(pf4);
		dto.setPf5(pf5);
		dto.setPf6(pf6);
		
		dto.setRr1(rr1);
		dto.setRr2(rr2);
		dto.setRr3(rr3);
		dto.setRr4(rr4);
		dto.setRr5(rr5);
		dto.setRr6(rr6);
		
		
		return dto;
	}
	
	public CalculoIndiceDTO getDTOSimple() {
		IgrDTO dto=new IgrDTO();
		
		dto.setId(getId());
		dto.setFecha(getFecha());
		dto.setObservaciones(getObservaciones());
		dto.setUsuario(getUsuario().getDTO());
		dto.setValor(getValor());
		dto.setTipo(getTipo());
		
		dto.setIr1(ir1);
		dto.setIr2(ir2);
		dto.setIr3(ir3);
		dto.setIr4(ir4);
		dto.setIr5(ir5);
		dto.setIr6(ir6);
		
		dto.setMd1(md1);
		dto.setMd2(md2);
		dto.setMd3(md3);
		dto.setMd4(md4);
		dto.setMd5(md5);
		dto.setMd6(md6);

		dto.setPf1(pf1);
		dto.setPf2(pf2);
		dto.setPf3(pf3);
		dto.setPf4(pf4);
		dto.setPf5(pf5);
		dto.setPf6(pf6);
		
		dto.setRr1(rr1);
		dto.setRr2(rr2);
		dto.setRr3(rr3);
		dto.setRr4(rr4);
		dto.setRr5(rr5);
		dto.setRr6(rr6);
		
		
		return dto;
			
	}
}
