package fing.satode.ui.indices.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fing.satode.data.CalculoIndiceDTO;
import fing.satode.data.IdlDTO;
import fing.satode.data.IgrDTO;

public interface IIndicesAsync {

	void buscarCalculoIndice(int tipo,
			AsyncCallback<ArrayList<CalculoIndiceDTO>> callback);

	void calcularIDL(IdlDTO dto, AsyncCallback<Void> callback);

	void exportarGraficosIDL(AsyncCallback<Void> callback);

	void calcularIGR(IgrDTO dto, AsyncCallback<Void> asyncCallback);

	void getCalculoIndice(Long id, AsyncCallback<CalculoIndiceDTO> callback);


}
