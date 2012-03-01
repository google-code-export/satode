package fing.satode.ui.indices.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fing.satode.data.CalculoIndiceDTO;

public interface IIndicesAsync {

	void buscarCalculoIndice(int tipo,
			AsyncCallback<ArrayList<CalculoIndiceDTO>> callback);

	void calcularIDL(CalculoIndiceDTO dto, AsyncCallback<Void> callback);

	void exportarGraficosIDL(AsyncCallback<Void> callback);


}
