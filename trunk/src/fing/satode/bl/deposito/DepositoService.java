package fing.satode.bl.deposito;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.data.DepositoDTO;
import fing.satode.data.SuministroDTO;
import fing.satode.data.TipoSuministroDTO;
import fing.satode.dominio.Deposito;
import fing.satode.dominio.Suministro;
import fing.satode.dominio.TipoSuministro;
import fing.satode.pl.deposito.DepositoDAO;
import fing.satode.pl.deposito.SuministroDAO;
import fing.satode.pl.deposito.TipoSuministroDAO;


@Transactional
public class DepositoService extends ServiceBase {
	
	public ArrayList<DepositoDTO> listaDepositos() {
		ArrayList<DepositoDTO> listaDTOS= new ArrayList<DepositoDTO>();
		ArrayList<Deposito> listaDes= DepositoDAO.getInstance().listaDepositos();
		for(Deposito d: listaDes){
			listaDTOS.add(d.getDTO());
		}
		return listaDTOS;
	}

	public void nuevoDeposito(DepositoDTO dto) {
		Deposito deposito= new Deposito(dto);
		DepositoDAO.getInstance().nuevoDeposito(deposito);
	}

	public void modificarDeposito(DepositoDTO dto) {
		Deposito deposito= new Deposito(dto);
		DepositoDAO.getInstance().modificarDeposito(deposito);
	}

	public void eliminarDeposito(DepositoDTO dto) {
		Deposito deposito= new Deposito(dto);
		DepositoDAO.getInstance().eliminarDeposito(deposito);
	}

	public ArrayList<TipoSuministroDTO> listaTipoSuministros() {
		ArrayList<TipoSuministroDTO> listaDTOS= new ArrayList<TipoSuministroDTO>();
		ArrayList<TipoSuministro> listaDes= TipoSuministroDAO.getInstance().listaTipoSuministros();
		for(TipoSuministro d: listaDes){
			listaDTOS.add(d.getDTO());
		}
		return listaDTOS;
	}

	public void nuevoTipoSuministro(TipoSuministroDTO dto) {
		TipoSuministro tiposuministro= new TipoSuministro(dto);
		TipoSuministroDAO.getInstance().nuevoTipoSuministro(tiposuministro);
	}

	public void modificarTipoSuministro(TipoSuministroDTO dto) {
		TipoSuministro tiposuministro= new TipoSuministro(dto);
		TipoSuministroDAO.getInstance().modificarTipoSuministro(tiposuministro);
	}

	public void eliminarTipoSuministro(TipoSuministroDTO dto) {
		TipoSuministro tiposuministro= new TipoSuministro(dto);
		TipoSuministroDAO.getInstance().eliminarTipoSuministro(tiposuministro);
	}
	
	
	
	public ArrayList<SuministroDTO> listaSuministros() {
		ArrayList<SuministroDTO> listaDTOS= new ArrayList<SuministroDTO>();
		ArrayList<Suministro> lista= SuministroDAO.getInstance().listaSuministros();
		for(Suministro d: lista){
			listaDTOS.add(d.getDTO());
		}
		return listaDTOS;
	}
	
	public void nuevoSuministro(SuministroDTO dto) {
		Suministro suministro= new Suministro(dto);
		SuministroDAO.getInstance().nuevoSuministro(suministro);
	}

	public void modificarSuministro(SuministroDTO dto) {
		Suministro suministro= new Suministro(dto);
		SuministroDAO.getInstance().modificarSuministro(suministro);
	}

	public void eliminarSuministro(SuministroDTO dto) {
		Suministro suministro= new Suministro(dto);
		SuministroDAO.getInstance().eliminarSuministro(suministro);
	}
	
}
