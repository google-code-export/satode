package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RR5 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int USOVOLUNTARIONORMAS=1;
	public final static int ALGUNOSREQUISITOS=2;
	public final static int EXPEDICIONNORMAS=3;
	public final static int ACTUALIZACIONTECNOLOGICA=4;
	public final static int ACTUALIZACIONPERMANENTE=5;
	
		
	public static String USOVOLUNTARIONORMAS_TXT= "Uso voluntario normas y c\u00F3digos de construcci\u00F3n de otros pa\u00EDses sin mayores adecuaciones y ajustes.";
	public static String ALGUNOSREQUISITOS_TXT= "Adaptaci\u00F3n de algunos requisitos y especificaciones de acuerdo con algunos criterios y particularidades nacionales y locales.";
	public static String EXPEDICIONNORMAS_TXT= "Expedici\u00F3n y actualizaci\u00F3n de normas nacionales de obligatorio cumplimiento con base en normativas internacionales, modificadas y ajustadas de acuerdo con la evaluaci\u00F3n de amenazas en el pa\u00EDs.";
	public static String ACTUALIZACIONTECNOLOGICA_TXT= "Actualizaci\u00F3n tecnol\u00F3gica de la mayor\u00EDa de normas de seguridad y de c\u00F3digos de construcci\u00F3n de edificaciones nuevas y existentes, con requisitos especiales para edificios y l\u00EDneas vitales esenciales.";
	public static String ACTUALIZACIONPERMANENTE_TXT= "Actualizaci\u00F3n permanente de c\u00F3digos y requisitos de seguridad; implantaci\u00F3n de reglamentos locales de construcci\u00F3n en la mayor\u00EDa de las ciudades, con base en microzonificaciones; estricto control de su cumplimiento.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return USOVOLUNTARIONORMAS_TXT;
		case 2: return ALGUNOSREQUISITOS_TXT;
		case 3: return EXPEDICIONNORMAS_TXT;
		case 4: return ACTUALIZACIONTECNOLOGICA_TXT;
		case 5: return ACTUALIZACIONPERMANENTE_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(USOVOLUNTARIONORMAS, getTXT(USOVOLUNTARIONORMAS)));
		res.add(new ItemConstante(ALGUNOSREQUISITOS, getTXT(ALGUNOSREQUISITOS)));
		res.add(new ItemConstante(EXPEDICIONNORMAS, getTXT(EXPEDICIONNORMAS)));
		res.add(new ItemConstante(ACTUALIZACIONTECNOLOGICA, getTXT(ACTUALIZACIONTECNOLOGICA)));
		res.add(new ItemConstante(ACTUALIZACIONPERMANENTE, getTXT(ACTUALIZACIONPERMANENTE)));
		
		return res;
	}
}
