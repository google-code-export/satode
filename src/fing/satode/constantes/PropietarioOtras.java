package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PropietarioOtras implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int NOTIENE=1;
	public final static int TERRENOENBARRIO=2;
	public final static int TERRENOTROBARRIO=3;
	public final static int TERRENOOTRALOCALIDAD=4;
	public final static int OTRAVIVIENDAMISMOBARRIO=5;
	public final static int OTRAVIVIENDAOTROBARRIO=6;
	public final static int OTRAVIVIENDAOTRALOCALIDAD=7;
	public final static int OTRASITUACION=8;
	public final static int NOSABENOCONTESTA=9;
	
	
		
	public static String NOTIENE_TXT= "No tiene";
	public static String TERRENOENBARRIO_TXT= "Terreno, en este barrio";
	public static String TERRENOTROBARRIO_TXT= "Terreno, en otro barrio";
	public static String TERRENOOTRALOCALIDAD_TXT= "Terreno, en otra localidad";
	public static String OTRAVIVIENDAMISMOBARRIO_TXT= "Otra vivienda en esta localidad (mismo barrio)";
	public static String OTRAVIVIENDAOTROBARRIO_TXT= "Otra vivienda en esta localidad (otro barrio)";
	public static String OTRAVIVIENDAOTRALOCALIDAD_TXT= "Otra vivienda en otra localidad";
	public static String OTRASITUACION_TXT= "Otra situaci\u00F3n";
	public static String NOSABENOCONTESTA_TXT= "ns/nc";
		
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return NOTIENE_TXT;
		case 2: return TERRENOENBARRIO_TXT;
		case 3: return TERRENOTROBARRIO_TXT;
		case 4: return TERRENOOTRALOCALIDAD_TXT;
		case 5: return OTRAVIVIENDAMISMOBARRIO_TXT;
		case 6: return OTRAVIVIENDAOTROBARRIO_TXT;
		case 7: return OTRAVIVIENDAOTRALOCALIDAD_TXT;
		case 8: return OTRASITUACION_TXT;
		case 9: return NOSABENOCONTESTA_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(NOTIENE, getTXT(NOTIENE)));
		res.add(new ItemConstante(TERRENOENBARRIO, getTXT(TERRENOENBARRIO)));
		res.add(new ItemConstante(TERRENOTROBARRIO, getTXT(TERRENOTROBARRIO)));
		res.add(new ItemConstante(TERRENOOTRALOCALIDAD, getTXT(TERRENOOTRALOCALIDAD)));
		res.add(new ItemConstante(OTRAVIVIENDAMISMOBARRIO, getTXT(OTRAVIVIENDAMISMOBARRIO)));
		res.add(new ItemConstante(OTRAVIVIENDAOTROBARRIO, getTXT(OTRAVIVIENDAOTROBARRIO)));
		res.add(new ItemConstante(OTRAVIVIENDAOTRALOCALIDAD, getTXT(OTRAVIVIENDAOTRALOCALIDAD)));
		res.add(new ItemConstante(OTRASITUACION, getTXT(OTRASITUACION)));
		res.add(new ItemConstante(NOSABENOCONTESTA, getTXT(NOSABENOCONTESTA)));
		
		return res;
	}
}
