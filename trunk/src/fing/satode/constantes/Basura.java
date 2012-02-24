package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Basura implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int SERVICIOMUNICIPAL=1;
	public final static int CONTENEDORES=2;
	public final static int SERVICIOVECINOS=3;
	public final static int OTROS=4;
	
	
	public static String SERVICIOMUNICIPAL_TXT= "Servicio de recolecci\u00F3n municipal";
	public static String CONTENEDORES_TXT= "Contenedores";
	public static String SERVICIOVECINOS_TXT= "Servicio organizado por los vecinos";
	public static String OTROS_TXT= "Otro";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return SERVICIOMUNICIPAL_TXT;
		case 2: return CONTENEDORES_TXT;
		case 3: return SERVICIOVECINOS_TXT;
		case 4: return OTROS_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(SERVICIOMUNICIPAL, getTXT(SERVICIOMUNICIPAL)));
		res.add(new ItemConstante(CONTENEDORES, getTXT(CONTENEDORES)));
		res.add(new ItemConstante(SERVICIOVECINOS, getTXT(SERVICIOVECINOS)));
		res.add(new ItemConstante(OTROS, getTXT(OTROS)));
		
		return res;
	}
}
