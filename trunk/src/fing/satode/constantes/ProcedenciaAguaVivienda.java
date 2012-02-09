package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ProcedenciaAguaVivienda implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int CANIERIADENTROVIVIENDA=1;
	public final static int CANIERIAFUERAVIVIENDA=2;
	public final static int OTROSMEDIOS=3;
	public final static int NOACCESO100M=4;
	public final static int NOACCESOMAS100M=5;
	
	
	public static String CANIERIADENTROVIVIENDA_TXT= "Cañería dentro de la vivienda ";
	public static String CANIERIAFUERAVIVIENDA_TXT= "Cañería fuera de la vivienda...";
	public static String OTROSMEDIOS_TXT= "Otros medios";
	public static String NOACCESO100M_TXT= "Acceso fuera de la vivienda a menos de 100m";
	public static String NOACCESOMAS100M_TXT= "Acceso fuera de la vivienda a más de 100m";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return CANIERIADENTROVIVIENDA_TXT;
		case 2: return CANIERIAFUERAVIVIENDA_TXT;
		case 3: return OTROSMEDIOS_TXT;
		case 4: return NOACCESO100M_TXT;
		case 5: return NOACCESOMAS100M_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(CANIERIADENTROVIVIENDA, getTXT(CANIERIADENTROVIVIENDA)));
		res.add(new ItemConstante(CANIERIAFUERAVIVIENDA, getTXT(CANIERIAFUERAVIVIENDA)));
		res.add(new ItemConstante(OTROSMEDIOS, getTXT(OTROSMEDIOS)));
		res.add(new ItemConstante(NOACCESO100M, getTXT(NOACCESO100M)));
		res.add(new ItemConstante(NOACCESOMAS100M, getTXT(NOACCESOMAS100M)));
		
		return res;
	}
}
