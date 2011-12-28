package fing.satode.ui.general.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fing.satode.data.DepartamentoDTO;

public interface IBasicosAsync {

	void listaDepartamentos(AsyncCallback<ArrayList<DepartamentoDTO>> callback);

}
