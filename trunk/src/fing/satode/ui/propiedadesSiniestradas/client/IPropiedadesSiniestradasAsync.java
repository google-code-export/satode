package fing.satode.ui.propiedadesSiniestradas.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fing.satode.data.CuentaCorrienteSuministroDTO;
import fing.satode.data.DepositoDTO;
import fing.satode.data.DonacionDTO;
import fing.satode.data.ParcelaDTO;
import fing.satode.data.SuministroDTO;
import fing.satode.data.TipoSuministroDTO;

public interface IPropiedadesSiniestradasAsync {


	void modificarParcela(ParcelaDTO dto, AsyncCallback<Void> callback);

	void listaParcelas(AsyncCallback<ArrayList<ParcelaDTO>> callback);

	void nuevaParcela(ParcelaDTO dto, AsyncCallback<Void> callback);

	void eliminarParcela(ParcelaDTO dto, AsyncCallback<Void> callback);

	

}
