package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PF5 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int POCOSINMUEBLESASEGURADOS=1;
	public final static int SEGUROSOBLIGATORIOSNOINF=2;
	public final static int SEGUROSBINESPUBLICOS=3;
	public final static int PROGRMAASEGURAMINETO=4;
	public final static int ESTRETEGIASRETENCION=5;
	
	
	public static String POCOSINMUEBLESASEGURADOS_TXT= "Muy pocos inmuebles públicos de la naci\u00F3n est\u00E1n asegurados y excepcionalmente algunos a nivel local.";
	public static String SEGUROSOBLIGATORIOSNOINF_TXT= "Disposiciones de aseguramiento de bienes públicos de obligatorio cumplimiento; deficiente aseguramiento de la infraestructura.";
	public static String SEGUROSBINESPUBLICOS_TXT= "Progresivo aseguramiento de bienes públicos e infraestructura del nivel nacional y de algunas ciudades.";
	public static String PROGRMAASEGURAMINETO_TXT= "Dise\u00F1o de programas de aseguramiento colectivo de edificios, infraestructura pública o en concesi\u00F3n en la mayor\u00EDa de ciudades.";
	public static String ESTRETEGIASRETENCION_TXT= "An\u00E1lisis e implantaci\u00F3n generalizada de estrategias de retenci\u00F3n y transferencia de p\u00E9rdidas sobre los activos públicos, considerando consorcios de reaseguro, titularizaci\u00F3n de riesgo, bonos cat, etc.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return POCOSINMUEBLESASEGURADOS_TXT;
		case 2: return SEGUROSOBLIGATORIOSNOINF_TXT;
		case 3: return SEGUROSBINESPUBLICOS_TXT;
		case 4: return PROGRMAASEGURAMINETO_TXT;
		case 5: return ESTRETEGIASRETENCION_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(POCOSINMUEBLESASEGURADOS, getTXT(POCOSINMUEBLESASEGURADOS)));
		res.add(new ItemConstante(SEGUROSOBLIGATORIOSNOINF, getTXT(SEGUROSOBLIGATORIOSNOINF)));
		res.add(new ItemConstante(SEGUROSBINESPUBLICOS, getTXT(SEGUROSBINESPUBLICOS)));
		res.add(new ItemConstante(PROGRMAASEGURAMINETO, getTXT(PROGRMAASEGURAMINETO)));
		res.add(new ItemConstante(ESTRETEGIASRETENCION, getTXT(ESTRETEGIASRETENCION)));
		
		return res;
	}
}
