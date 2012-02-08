package fing.satode.constantes;

public class ItemConstante {

	private int codigo;
	private String texto;
	
	public ItemConstante(int cod,String txt){
		codigo=cod;
		texto=txt;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
}
