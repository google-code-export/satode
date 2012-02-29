package fing.satode.data;

public class IDLX {

	private float valorXem; //Suma de evento e para el municipio m
	private float valorXm; // Suma de todos los eventos en el municipio m
	private float valorXec; 
	private float valorXc;
	private float n;
	

	private IDLX[] valoresPorDepartamento;
	
	public IDLX[] getValoresPorDepartamento() {
		return valoresPorDepartamento;
	}

	public void setValoresPorDepartamento(IDLX[] valoresPorDepartamento) {
		this.valoresPorDepartamento = valoresPorDepartamento;
	}

	
	public float getN() {
		return n;
	}

	public void setN(float n) {
		this.n = n;
	}

	public float getValorXem() {
		return valorXem;
	}

	public void setValorXem(float valorXem) {
		this.valorXem = valorXem;
	}

	public float getValorXm() {
		return valorXm;
	}

	public void setValorXm(float valorXm) {
		this.valorXm = valorXm;
	}

	public float getValorXec() {
		return valorXec;
	}

	public void setValorXec(float valorXec) {
		this.valorXec = valorXec;
	}

	public float getValorXc() {
		return valorXc;
	}

	public void setValorXc(float valorXc) {
		this.valorXc = valorXc;
	}

	
	
	
}
