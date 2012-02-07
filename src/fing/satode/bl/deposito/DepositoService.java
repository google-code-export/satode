package fing.satode.bl.deposito;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.data.DepositoDTO;
import fing.satode.data.DonacionDTO;
import fing.satode.data.SuministroDTO;
import fing.satode.data.TipoSuministroDTO;
import fing.satode.dominio.CuentaCorrienteSuministro;
import fing.satode.dominio.Deposito;
import fing.satode.dominio.Donacion;
import fing.satode.dominio.Suministro;
import fing.satode.dominio.TipoSuministro;
import fing.satode.pl.deposito.DepositoDAO;
import fing.satode.pl.deposito.DonacionDAO;
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
	

	public ArrayList<DonacionDTO> listaDonacion() {
		ArrayList<DonacionDTO> listaDTOS= new ArrayList<DonacionDTO>();
		ArrayList<Donacion> lista= DonacionDAO.getInstance().listaDonaciones();
		for(Donacion d: lista){
			listaDTOS.add(d.getDTO());
		}
		return listaDTOS;
	}
	
	public void nuevoDonacion(DonacionDTO dto) {
		Donacion donacion= new Donacion(dto);
		//Collection<Suministro> sum=donacion.getSuministros();
		//donacion.setSuministros(null);
		DonacionDAO.getInstance().nuevoDonacion(donacion);
		//donacion.setSuministros(sum);
		//DonacionDAO.getInstance().modificarDonacion(donacion);
		
	}

	public void modificarDonacion(DonacionDTO dto) {
		Donacion suministro= new Donacion(dto);
		DonacionDAO.getInstance().modificarDonacion(suministro);
	}

	public void eliminarDonacion(DonacionDTO dto) {
		Donacion deposito= new Donacion(dto);
		DonacionDAO.getInstance().eliminarDonacion(deposito);
	}

	public void confirmar(DonacionDTO dto) {
		// TODO Auto-generated method stub
		Donacion donacion= new Donacion(dto);
		donacion.setImpactarCuentas(true);
		for(Suministro s: donacion.getSuministros()){
			CuentaCorrienteSuministro cuenta= DonacionDAO.getInstance().getCuentaCorriente(s,donacion.getDeposito());
			cuenta.setCantidad(cuenta.getCantidad()+s.getCantidad());
			DonacionDAO.getInstance().modificarCuentaCorriente(cuenta);
		}
		DonacionDAO.getInstance().modificarDonacion(donacion);
	}
	
}
