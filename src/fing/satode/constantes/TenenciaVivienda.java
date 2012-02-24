package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TenenciaVivienda implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int PROPIETARIOSEHIZOLAVIV=1;
	public final static int PROPIETARIOPAGANDO=2;
	public final static int PROPIETARIOPAGO=3;
	public final static int PROPIETARIOHERENCIA=4;
	public final static int INQUILINO=5;
	public final static int OCUPANTERELACIONDEPENDENCIA=6;
	public final static int OCUPANTEPRESTAMO=7;
	public final static int OCUPANTESINPERMISO=8;
	public final static int OTRASITUACION=9;
	public final static int NOSABENOCONTESTA=10;
	
	
		
	public static String PROPIETARIOSEHIZOLAVIV_TXT= "Propietario  se hizo la vivienda";
	public static String PROPIETARIOPAGANDO_TXT= "Propietario la est\u00E1 pagando";
	public static String PROPIETARIOPAGO_TXT= "Propietario ya la pag\u00F3";
	public static String PROPIETARIOHERENCIA_TXT= "Propietario le donaron o hered\u00F3 la vivienda";
	public static String INQUILINO_TXT= "Inquilino";
	public static String OCUPANTERELACIONDEPENDENCIA_TXT= "Ocupante con relaci\u00F3n de dependencia.";
	public static String OCUPANTEPRESTAMO_TXT= "Ocupante pr\u00E9stamo, permiso, cesi\u00F3n";
	public static String OCUPANTESINPERMISO_TXT= "Ocupante de hecho (sin permiso del propietario)";
	public static String OTRASITUACION_TXT= "Otra situaci\u00F3n";
	public static String NOSABENOCONTESTA_TXT= "ns/nc";
		
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return PROPIETARIOSEHIZOLAVIV_TXT;
		case 2: return PROPIETARIOPAGANDO_TXT;
		case 3: return PROPIETARIOPAGO_TXT;
		case 4: return PROPIETARIOHERENCIA_TXT;
		case 5: return INQUILINO_TXT;
		case 6: return OCUPANTERELACIONDEPENDENCIA_TXT;
		case 7: return OCUPANTEPRESTAMO_TXT;
		case 8: return OCUPANTESINPERMISO_TXT;
		case 9: return OTRASITUACION_TXT;
		case 10: return NOSABENOCONTESTA_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(PROPIETARIOSEHIZOLAVIV, getTXT(PROPIETARIOSEHIZOLAVIV)));
		res.add(new ItemConstante(PROPIETARIOPAGANDO, getTXT(PROPIETARIOPAGANDO)));
		res.add(new ItemConstante(PROPIETARIOPAGO, getTXT(PROPIETARIOPAGO)));
		res.add(new ItemConstante(PROPIETARIOHERENCIA, getTXT(PROPIETARIOHERENCIA)));
		res.add(new ItemConstante(INQUILINO, getTXT(INQUILINO)));
		res.add(new ItemConstante(OCUPANTERELACIONDEPENDENCIA, getTXT(OCUPANTERELACIONDEPENDENCIA)));
		res.add(new ItemConstante(OCUPANTEPRESTAMO, getTXT(OCUPANTEPRESTAMO)));
		res.add(new ItemConstante(OCUPANTESINPERMISO, getTXT(OCUPANTESINPERMISO)));
		res.add(new ItemConstante(OTRASITUACION, getTXT(OTRASITUACION)));
		res.add(new ItemConstante(NOSABENOCONTESTA, getTXT(NOSABENOCONTESTA)));
		
		return res;
	}
}
