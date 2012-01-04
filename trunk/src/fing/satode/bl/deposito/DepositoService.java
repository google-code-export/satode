package fing.satode.bl.deposito;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.data.DepositoDTO;
import fing.satode.data.TipoSuministroDTO;
import fing.satode.dominio.Deposito;
import fing.satode.dominio.TipoSuministro;
import fing.satode.pl.deposito.DepositoDAO;
import fing.satode.pl.deposito.TipoSuministroDAO;


@Transactional
public class DepositoService extends ServiceBase {
	
	public ArrayList<DepositoDTO> listaDepositos() {
		// TODO Auto-generated method stub
		ArrayList<DepositoDTO> listaDTOS= new ArrayList<DepositoDTO>();
		ArrayList<Deposito> listaDes= DepositoDAO.getInstance().listaDepositos();
		for(Deposito d: listaDes){
			listaDTOS.add(d.getDTO());
		}
		return listaDTOS;
	}

	public void nuevoDeposito(DepositoDTO dto) {
		// TODO Auto-generated method stub
		Deposito deposito= new Deposito(dto);
		DepositoDAO.getInstance().nuevoDeposito(deposito);
	}

	public void modificarDeposito(DepositoDTO dto) {
		// TODO Auto-generated method stub
		Deposito deposito= new Deposito(dto);
		DepositoDAO.getInstance().modificarDeposito(deposito);
	}

	public void eliminarDeposito(DepositoDTO dto) {
		// TODO Auto-generated method stub
		Deposito deposito= new Deposito(dto);
		DepositoDAO.getInstance().eliminarDeposito(deposito);
	}

	public ArrayList<TipoSuministroDTO> listaTipoSuministros() {
		// TODO Auto-generated method stub
		ArrayList<TipoSuministroDTO> listaDTOS= new ArrayList<TipoSuministroDTO>();
		ArrayList<TipoSuministro> listaDes= TipoSuministroDAO.getInstance().listaTipoSuministros();
		for(TipoSuministro d: listaDes){
			listaDTOS.add(d.getDTO());
		}
		return listaDTOS;
	}

	public void nuevoTipoSuministro(TipoSuministroDTO dto) {
		// TODO Auto-generated method stub
		TipoSuministro tiposuministro= new TipoSuministro(dto);
		TipoSuministroDAO.getInstance().nuevoTipoSuministro(tiposuministro);
	}

	public void modificarTipoSuministro(TipoSuministroDTO dto) {
		// TODO Auto-generated method stub
		TipoSuministro tiposuministro= new TipoSuministro(dto);
		TipoSuministroDAO.getInstance().modificarTipoSuministro(tiposuministro);
	}

	public void eliminarTipoSuministro(TipoSuministroDTO dto) {
		// TODO Auto-generated method stub
		TipoSuministro tiposuministro= new TipoSuministro(dto);
		TipoSuministroDAO.getInstance().eliminarTipoSuministro(tiposuministro);
	}
	
}
