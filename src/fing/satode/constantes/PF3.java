package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PF3 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int PRESUPUESTOLIMITADO=1;
	public final static int PRESUPUESTOSLEGALES=2;
	public final static int DESTINADOPORLEY=3;
	public final static int PREGRESIVAASIGNACION=4;
	public final static int RESPALDONACIONAL=5;
	
		
	public static String PRESUPUESTOLIMITADO_TXT= "Asignaci\u00F3n limitada de partidas del presupuesto nacional a instituciones competentes, para atenci\u00F3n de emergencias.";
	public static String PRESUPUESTOSLEGALES_TXT= "Disposiciones legales estableciendo la destinaci\u00F3n de presupuesto a entidades del orden nacional, con fines de gesti\u00F3n de riesgos.";
	public static String DESTINADOPORLEY_TXT= "Destinaci\u00F3n por ley de transferencias espec\u00EDficas para la gesti\u00F3n de riesgos a nivel municipal y realizaci\u00F3n frecuente de convenios interadministrativos para la ejecuci\u00F3n de proyectos de prevenci\u00F3n.";
	public static String PREGRESIVAASIGNACION_TXT= "Progresiva asignaci\u00F3n de partidas del gasto discrecional tanto nacional como municipal para la reducci\u00F3n de la vulnerabilidad; creaci\u00F3n de incentivos y tasas de protecci\u00F3n y seguridad ambiental.";
	public static String RESPALDONACIONAL_TXT= "Orientaci\u00F3n y respaldo nacional de empr\u00E9stitos gestionados por los municipios y entidades subnacionales y locales ante organismos multilaterales de cr\u00E9dito.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return PRESUPUESTOLIMITADO_TXT;
		case 2: return PRESUPUESTOSLEGALES_TXT;
		case 3: return DESTINADOPORLEY_TXT;
		case 4: return PREGRESIVAASIGNACION_TXT;
		case 5: return RESPALDONACIONAL_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(PRESUPUESTOLIMITADO, getTXT(PRESUPUESTOLIMITADO)));
		res.add(new ItemConstante(PRESUPUESTOSLEGALES, getTXT(PRESUPUESTOSLEGALES)));
		res.add(new ItemConstante(DESTINADOPORLEY, getTXT(DESTINADOPORLEY)));
		res.add(new ItemConstante(PREGRESIVAASIGNACION, getTXT(PREGRESIVAASIGNACION)));
		res.add(new ItemConstante(RESPALDONACIONAL, getTXT(RESPALDONACIONAL)));
		
		return res;
	}
}
