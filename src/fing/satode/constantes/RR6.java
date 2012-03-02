package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RR6 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int ESPORADICAADECUACION=1;
	public final static int EXPEDICIONNORMAS=2;
	public final static int PROGRAMASMASIVOS=3;
	public final static int EDIFICACIONESREFORZADAS=4;
	public final static int MASIFICACIONREFUERZOS=5;
	
	
	public static String ESPORADICAADECUACION_TXT= "Refuerzo y adecuaci\u00F3n espor\u00E1dica de edificaciones y l\u00EDneas vitales por remodelaciones o cambios de uso o por modificaciones.";
	public static String EXPEDICIONNORMAS_TXT= "Expedici\u00F3n de normas de intervenci\u00F3n de la vulnerabilidad de edificios existentes; refuerzo de algunos edificios esenciales como hospitales o considerados de car\u00E1cter indispensable.";
	public static String PROGRAMASMASIVOS_TXT= "Algunos programas masivos de evaluaci\u00F3n de vulnerabilidad, rehabilitaci\u00F3n y refuerzo de hospitales, escuelas y edificios de control de l\u00EDneas vitales; obligatoriedad de reforzamientos.";
	public static String EDIFICACIONESREFORZADAS_TXT= "Progresivo n\u00FAmero de edificios p\u00FAblicos reforzados, l\u00EDneas vitales intervenidas; algunos edificios del sector privado reforzados por iniciativa propia o por est\u00EDmulos fiscales ofrecidos por el gobierno.";
	public static String MASIFICACIONREFUERZOS_TXT= "Masificaci\u00F3n del refuerzo de los principales edificios p\u00FAblicos y privados; programas permanentes de incentivos para rehabilitaci\u00F3n de vivienda de estratos socio-econ\u00F3micos de bajos ingresos.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return ESPORADICAADECUACION_TXT;
		case 2: return EXPEDICIONNORMAS_TXT;
		case 3: return PROGRAMASMASIVOS_TXT;
		case 4: return EDIFICACIONESREFORZADAS_TXT;
		case 5: return MASIFICACIONREFUERZOS_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(ESPORADICAADECUACION, getTXT(ESPORADICAADECUACION)));
		res.add(new ItemConstante(EXPEDICIONNORMAS, getTXT(EXPEDICIONNORMAS)));
		res.add(new ItemConstante(PROGRAMASMASIVOS, getTXT(PROGRAMASMASIVOS)));
		res.add(new ItemConstante(EDIFICACIONESREFORZADAS, getTXT(EDIFICACIONESREFORZADAS)));
		res.add(new ItemConstante(MASIFICACIONREFUERZOS, getTXT(MASIFICACIONREFUERZOS)));
		
		return res;
	}
}
