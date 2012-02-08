package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EstadoNecesidad implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int INGRESADA=1;
	public final static int EN_PROCESO=2;
	public final static int ACEPTADA=3;
	public final static int RECHAZADA=4;
	
	
	public static String INGRESADA_TXT= "INGRESADA";
	public static String EN_PROCESO_TXT= "EN PROCESO";
	public static String ACEPTADA_TXT= "ACEPTADA";
	public static String RECHAZADA_TXT= "RECHAZADA";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return INGRESADA_TXT;
		case 2: return EN_PROCESO_TXT;
		case 3: return ACEPTADA_TXT;
		case 4: return RECHAZADA_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(INGRESADA, getTXT(INGRESADA)));
		res.add(new ItemConstante(EN_PROCESO, getTXT(EN_PROCESO)));
		res.add(new ItemConstante(ACEPTADA, getTXT(ACEPTADA)));
		res.add(new ItemConstante(RECHAZADA, getTXT(RECHAZADA)));
		
		return res;
	}
}
