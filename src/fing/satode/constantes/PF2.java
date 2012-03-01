package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PF2 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int FONDONACIONAL=1;
	public final static int REGLAMENTACIONFONDO=2;
	public final static int APOYOECONOMICO=3;
	public final static int PROGRESIVOFONDOSRESERVAS=4;
	public final static int INGENIERIAFINANCIERA=5;
	
	
	public static String FONDONACIONAL_TXT= "Existencia de un fondo nacional de desastres o calamidades y de algunos fondos locales en algunas ciudades.";
	public static String REGLAMENTACIONFONDO_TXT= "Reglamentaci\u00F3n de fondos de reservas existentes o creaci\u00F3n de nuevos fondos para cofinanciar proyectos de gesti\u00F3n de riesgos a nivel local.";
	public static String APOYOECONOMICO_TXT= "Apoyo econ\u00F3mico nacional y gesti\u00F3n de recursos internacionales para el desarrollo institucional y fortalecimiento de la gesti\u00F3n de riesgos en todo el territorio.";
	public static String PROGRESIVOFONDOSRESERVAS_TXT= "Progresiva creaci\u00F3n de fondos de reservas en los municipios para la cofinanciaci\u00F3n de proyectos, fortalecimiento institucional y recuperaci\u00F3n en caso de desastres.";
	public static String INGENIERIAFINANCIERA_TXT= "Ingenier\u00EDa financiera para el dise\u00F1o de instrumentos de retenci\u00F3n y transferencia de riesgos a nivel nacional; fondos de reservas funcionando en la mayor\u00EDa de ciudades.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return FONDONACIONAL_TXT;
		case 2: return REGLAMENTACIONFONDO_TXT;
		case 3: return APOYOECONOMICO_TXT;
		case 4: return PROGRESIVOFONDOSRESERVAS_TXT;
		case 5: return INGENIERIAFINANCIERA_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(FONDONACIONAL, getTXT(FONDONACIONAL)));
		res.add(new ItemConstante(REGLAMENTACIONFONDO, getTXT(REGLAMENTACIONFONDO)));
		res.add(new ItemConstante(APOYOECONOMICO, getTXT(APOYOECONOMICO)));
		res.add(new ItemConstante(PROGRESIVOFONDOSRESERVAS, getTXT(PROGRESIVOFONDOSRESERVAS)));
		res.add(new ItemConstante(INGENIERIAFINANCIERA, getTXT(INGENIERIAFINANCIERA)));
		
		return res;
	}
}
