package fing.satode.data;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EstadoPared implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int TODASREVOCADAS=1;
	public final static int MAYORIAREVOCADAS=2;
	public final static int MAYORIASINREVOCAR=3;
	public final static int TODASSINREVOCAR=4;
	
	
	public static String TODASREVOCADAS_TXT= "Todas revocadas";
	public static String MAYORIAREVOCADAS_TXT= "Mayoria revocadas";
	public static String MAYORIASINREVOCAR_TXT= "Mayoria sin revocar";
	public static String TODASSINREVOCAR_TXT= "Todas sin revocar";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return TODASREVOCADAS_TXT;
		case 2: return MAYORIAREVOCADAS_TXT;
		case 3: return MAYORIASINREVOCAR_TXT;
		case 4: return TODASSINREVOCAR_TXT;
		default:
			return "";
		}
	}
}
