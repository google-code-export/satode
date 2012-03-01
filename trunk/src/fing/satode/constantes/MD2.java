package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MD2 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int PLANESBASICOS=1;
	public final static int DISPOSICIONESLEGALES=2;
	public final static int PROTOCOLOS=3;
	public final static int PLANESEMERGENCIA=4;
	public final static int PREPARACION=5;
	
		
	public static String PLANESBASICOS_TXT= "Planes b\u00E1sicos de emergencia y contingencia con listas de chequeo e informaci\u00F3n del personal disponible.";
	public static String DISPOSICIONESLEGALES_TXT= "Disposiciones legales que establecen la obligatoriedad de planes de emergencia; algunas ciudades con planes operativos; articulaci\u00F3n con entidades que producen informaci\u00F3n t\u00E9cnica a nivel nacional.";
	public static String PROTOCOLOS_TXT= "Protocolos y procedimientos operativos bien definidos a nivel nacional y subnacional, y en las principales ciudades; varios sistemas de pron\u00F3stico y alerta operando en forma continua.";
	public static String PLANESEMERGENCIA_TXT= "Planes de emergencia y contingencia completos y asociados a sistemas de informaci\u00F3n y alerta en la mayor\u00EDa de ciudades.";
	public static String PREPARACION_TXT= "Preparaci\u00F3n para la respuesta operativa con base en escenarios probables en todo el territorio; uso de tecnolog\u00EDa de la informaci\u00F3n para la activaci\u00F3n de procedimientos autom\u00E1ticos de respuesta.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return PLANESBASICOS_TXT;
		case 2: return DISPOSICIONESLEGALES_TXT;
		case 3: return PROTOCOLOS_TXT;
		case 4: return PLANESEMERGENCIA_TXT;
		case 5: return PREPARACION_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(PLANESBASICOS, getTXT(PLANESBASICOS)));
		res.add(new ItemConstante(DISPOSICIONESLEGALES, getTXT(DISPOSICIONESLEGALES)));
		res.add(new ItemConstante(PROTOCOLOS, getTXT(PROTOCOLOS)));
		res.add(new ItemConstante(PLANESEMERGENCIA, getTXT(PLANESEMERGENCIA)));
		res.add(new ItemConstante(PREPARACION, getTXT(PREPARACION)));
		
		return res;
	}
}
