package fing.satode.data;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MedidaTiempo implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public static int SEGUNDOS=1;
	public static int MINUTOS=2;
	public static int HORAS=3;
	public static int DIAS=4;
	public static int SEMANAS=5;
	
	private static String HORAS_TXT="Horas";
	private static String MINUTOS_TXT="Minutos";
	private static String SEGUNDOS_TXT="Segundos";
	private static String DIAS_TXT="Dias";
	private static String SEMANAS_TXT="Semanas";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return HORAS_TXT;
		case 2: return MINUTOS_TXT;
		case 3: return SEGUNDOS_TXT;
		case 4: return DIAS_TXT;
		case 5: return SEMANAS_TXT;
		default:
			return "";
		}
	}
	
}
