package fing.satode.ui.deposito.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fing.satode.data.CuentaCorrienteSuministroDTO;
import fing.satode.data.DepositoDTO;
import fing.satode.data.DonacionDTO;
import fing.satode.data.SuministroDTO;
import fing.satode.data.TipoSuministroDTO;

public interface IDepositoAsync {

	void eliminarDeposito(DepositoDTO dto, AsyncCallback<Void> callback);

	void listaDepositos(AsyncCallback<ArrayList<DepositoDTO>> callback);

	void modificarDeposito(DepositoDTO dto, AsyncCallback<Void> callback);

	void nuevoDeposito(DepositoDTO dto, AsyncCallback<Void> callback);

	void listaTipoSuministros(
			AsyncCallback<ArrayList<TipoSuministroDTO>> callback);

	void modificarTipoSuministro(TipoSuministroDTO dto,
			AsyncCallback<Void> callback);

	void nuevoTipoSuministro(TipoSuministroDTO dto, AsyncCallback<Void> callback);

	void eliminarTipoSuministro(TipoSuministroDTO dto,
			AsyncCallback<Void> callback);

	void listaSuministros(AsyncCallback<ArrayList<SuministroDTO>> callback);

	void eliminarSuministro(SuministroDTO dto, AsyncCallback<Void> callback);

	void modificarSuministro(SuministroDTO dto, AsyncCallback<Void> callback);

	void nuevoSuministro(SuministroDTO dto, AsyncCallback<Void> callback);

	void listaDonaciones(AsyncCallback<ArrayList<DonacionDTO>> callback);

	void nuevoDonacion(DonacionDTO dto, AsyncCallback<Void> callback);

	void modificarDonacion(DonacionDTO dto, AsyncCallback<Void> callback);

	void eliminarDonacion(DonacionDTO dto, AsyncCallback<Void> callback);

	void confirmar(DonacionDTO dto, AsyncCallback<Void> callback);

	void buscarCuentaCorrienteSuministro(Long idDeposito,
			Long idTipoSuministro,
			AsyncCallback<ArrayList<CuentaCorrienteSuministroDTO>> callback);

	

}
