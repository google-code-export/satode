package fing.satode.bl.deposito;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.constantes.EstadoSolicitudEnvio;
import fing.satode.data.CuentaCorrienteSuministroDTO;
import fing.satode.data.DepositoDTO;
import fing.satode.data.DonacionDTO;
import fing.satode.data.SolicitudEnvioDTO;
import fing.satode.data.SolicitudEnvioSuministroDTO;
import fing.satode.data.SuministroDTO;
import fing.satode.data.TipoSuministroDTO;
import fing.satode.dominio.CuentaCorrienteSuministro;
import fing.satode.dominio.Deposito;
import fing.satode.dominio.Donacion;
import fing.satode.dominio.SolicitudEnvio;
import fing.satode.dominio.Suministro;
import fing.satode.dominio.TipoSuministro;
import fing.satode.pl.deposito.CuentaCorrienteSuministroDAO;
import fing.satode.pl.deposito.DepositoDAO;
import fing.satode.pl.deposito.DonacionDAO;
import fing.satode.pl.deposito.SolicitudEnvioDAO;
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
			CuentaCorrienteSuministro cuenta= CuentaCorrienteSuministroDAO.getInstance().getCuentaCorriente(s,donacion.getDeposito());
			cuenta.setCantidad(cuenta.getCantidad()+s.getCantidad());
			CuentaCorrienteSuministroDAO.getInstance().modificarCuentaCorriente(cuenta);
		}
		DonacionDAO.getInstance().modificarDonacion(donacion);
	}

	public ArrayList<CuentaCorrienteSuministroDTO> buscarCuentaCorrienteSuministro(
			Long idDeposito, Long idTipoSuministro) {
		ArrayList<CuentaCorrienteSuministroDTO> listaDTOS= new ArrayList<CuentaCorrienteSuministroDTO>();
		ArrayList<CuentaCorrienteSuministro> lista= CuentaCorrienteSuministroDAO.getInstance().buscarCuentaCorrienteSuministro(idDeposito,idTipoSuministro);
		for(CuentaCorrienteSuministro d: lista){
			listaDTOS.add(d.getDTO());
		}
		return listaDTOS;
	}

	public void modificarCuentaCorrienteSuministro(CuentaCorrienteSuministroDTO dto) {
		CuentaCorrienteSuministroDAO.getInstance().modificarCuentaCorriente(new CuentaCorrienteSuministro(dto));
		
	}

	public ArrayList<SolicitudEnvioDTO> buscarSolicitudesEnvio(	Long idPuntoEntrega, Long idDeposito, int estado) {
		ArrayList<SolicitudEnvioDTO> listaDTOS= new ArrayList<SolicitudEnvioDTO>();
		ArrayList<SolicitudEnvio> lista= SolicitudEnvioDAO.getInstance().buscarSolicitudesEnvio(idPuntoEntrega, idDeposito,  estado);
		for(SolicitudEnvio d: lista){
			listaDTOS.add(d.getDTO());
		}
		return listaDTOS;
	}

	public void enviarSolicitudEnvio(SolicitudEnvioDTO dto) {
		// TODO Auto-generated method stub
		dto.setEstado(EstadoSolicitudEnvio.ENVIADA);
		SolicitudEnvioDAO.getInstance().modificarSolicitudEnvio(new SolicitudEnvio(dto));
		
		for(SolicitudEnvioSuministroDTO s :dto.getSolicitudesEnvioSuministros()){
			CuentaCorrienteSuministro cuenta=CuentaCorrienteSuministroDAO.getInstance().getCuentaCorriente(dto.getDeposito().getId(),s.getTipoSuministro().getId());			
			cuenta.setCantidad(cuenta.getCantidad()-s.getCantidad());
		}
	}

	public void recibirSolicitudEnvio(SolicitudEnvioDTO dto) {
		dto.setEstado(EstadoSolicitudEnvio.RECIBIDA_OK);
		if(dto.getObservacionesEntrega()!=null && dto.getObservacionesEntrega().trim().length()!=0){
			dto.setEstado(EstadoSolicitudEnvio.RECIBIDA_OBS);
		}
		SolicitudEnvioDAO.getInstance().modificarSolicitudEnvio(new SolicitudEnvio(dto));
		
	}
	
}
