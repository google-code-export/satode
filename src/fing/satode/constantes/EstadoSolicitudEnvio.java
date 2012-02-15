package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EstadoSolicitudEnvio implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int CREADA=0;
	public final static int NUEVA=1;
	public final static int ENVIADA=2;
	public final static int RECIBIDA_OK=3;
	public final static int RECIBIDA_OBS=3;
	
	public static String NUEVA_TXT="NUEVA";
	public static String ENVIADA_TXT="ENVIADA";
	public static String RECIBIDA_OK_TXT="RECIBIDA OK";
	public static String RECIBIDA_OBS_TXT="RECIBIDA CON OBSERVACIONES";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return NUEVA_TXT;
		case 2: return ENVIADA_TXT;
		case 3: return RECIBIDA_OK_TXT;
		case 4: return RECIBIDA_OBS_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(NUEVA, getTXT(NUEVA)));
		res.add(new ItemConstante(ENVIADA, getTXT(ENVIADA)));
		res.add(new ItemConstante(RECIBIDA_OK, getTXT(RECIBIDA_OK)));
		res.add(new ItemConstante(RECIBIDA_OBS, getTXT(RECIBIDA_OBS)));
		return res;
	}
}
