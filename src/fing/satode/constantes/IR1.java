package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class IR1 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int DATOSBASICOS=1;
	public final static int CATALOGOSINCOMPLETOS=2;
	public final static int CATALOGOSCOMPLETOS=3;
	public final static int INVENTARIOCOMPLETO=4;
	public final static int INVENTARIODETALLADO=5;
	

	
	public static String DATOSBASICOS_TXT= "Algunos datos b\u00E1sicos y superficiales de eventos hist\u00F3ricos.";
	public static String CATALOGOSINCOMPLETOS_TXT= "Registro continuo de eventos actuales, cat\u00E1logos incompletos de ocurrencia de algunos fen\u00F3menos e informaci\u00F3n limitada de efectos y p\u00E9rdidas.";
	public static String CATALOGOSCOMPLETOS_TXT= "Algunos cat\u00E1logos completos a nivel nacional y en las regiones, sistematizaci\u00F3n generalizada de eventos actuales y de sus efectos econ\u00F3micos, sociales y ambientales.";
	public static String INVENTARIOCOMPLETO_TXT= "Inventario completo y m\u00FAltiples cat\u00E1logos de eventos; registro y sistematizaci\u00F3n detallada de efectos y p\u00E9rdidas a nivel nacional.";
	public static String INVENTARIODETALLADO_TXT= "Inventario detallado de eventos y efectos para todo tipo de amenaza existente y bases de datos a nivel subnacional y local.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return DATOSBASICOS_TXT;
		case 2: return CATALOGOSINCOMPLETOS_TXT;
		case 3: return CATALOGOSCOMPLETOS_TXT;
		case 4: return INVENTARIOCOMPLETO_TXT;
		case 5: return INVENTARIODETALLADO_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(DATOSBASICOS, getTXT(DATOSBASICOS)));
		res.add(new ItemConstante(CATALOGOSINCOMPLETOS, getTXT(CATALOGOSINCOMPLETOS)));
		res.add(new ItemConstante(CATALOGOSCOMPLETOS, getTXT(CATALOGOSCOMPLETOS)));
		res.add(new ItemConstante(INVENTARIOCOMPLETO, getTXT(INVENTARIOCOMPLETO)));
		res.add(new ItemConstante(INVENTARIODETALLADO, getTXT(INVENTARIODETALLADO)));
		
		return res;
	}
}
