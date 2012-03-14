package fing.satode.bl.indices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.bl.base.ServiceFactory;
import fing.satode.data.CalculoIndiceDTO;
import fing.satode.data.IDLX;
import fing.satode.data.IdlDTO;
import fing.satode.data.IdlDepartamentoDTO;
import fing.satode.data.IgrDTO;
import fing.satode.dominio.CalculoIndice;
import fing.satode.dominio.Departamento;
import fing.satode.dominio.Evento;
import fing.satode.dominio.IDL;
import fing.satode.dominio.IGR;
import fing.satode.dominio.IdlDepartamento;
import fing.satode.dominio.IdlEvento;
import fing.satode.dominio.IdlTipoEvento;
import fing.satode.dominio.TipoEvento;
import fing.satode.pl.basicos.DepartamentoDAO;
import fing.satode.pl.indices.IndicesDAO;
import fing.satode.pl.registros.EventoDAO;

@Transactional
public class IndicesService extends ServiceBase {

	public ArrayList<CalculoIndiceDTO> listaCalculoIndice() {
		ArrayList<CalculoIndiceDTO> listaDTOS= new ArrayList<CalculoIndiceDTO>();
		ArrayList<CalculoIndice> listaDes= IndicesDAO.getInstance().listaCalculoIndice();
		for(CalculoIndice d: listaDes){
			listaDTOS.add(d.getDTOSimple());
		}
		return listaDTOS;
	}

	public CalculoIndiceDTO getCalculoIndice(Long id) {
		CalculoIndice calculo= IndicesDAO.getInstance().getCalculoIndice(id);
		return calculo.getDTO();
	}

	
	public void nuevoCalculoIndice(CalculoIndiceDTO dto) {
		CalculoIndice necesidad= new CalculoIndice(dto);
		IndicesDAO.getInstance().nuevoCalculoIndice(necesidad);
	}

	public void modificarCalculoIndice(CalculoIndiceDTO dto) {
		CalculoIndice necesidad= new CalculoIndice(dto);
		IndicesDAO.getInstance().modificarCalculoIndice(necesidad);
	}

	public void eliminarCalculoIndice(CalculoIndiceDTO dto) {
		CalculoIndice necesidad= new CalculoIndice(dto);
		IndicesDAO.getInstance().eliminarCalculoIndice(necesidad);
	}

	public ArrayList<CalculoIndiceDTO> buscarCalculoIndice(int tipo){
		ArrayList<CalculoIndiceDTO> listaDTOS= new ArrayList<CalculoIndiceDTO>();
		ArrayList<CalculoIndice> listaDes= IndicesDAO.getInstance().buscarCalculoIndice(tipo);
		for(CalculoIndice d: listaDes){
			listaDTOS.add(d.getDTOSimple());
		}
		return listaDTOS;
	}

	public void calcularIDL(IdlDTO dto) {
		
		IDLX xsKDyF= calcularXs("K","DyF",dto);
		IDLX xsKST= calcularXs("K","ST",dto);
		IDLX xsKIyT= calcularXs("K","IyT",dto);
		IDLX xsKOtros= calcularXs("K","Otros",dto);
		
		IDLX xsADyF= calcularXs("A","DyF",dto);
		IDLX xsAST= calcularXs("A","ST",dto);
		IDLX xsAIyT= calcularXs("A","IyT",dto);
		IDLX xsAOtros= calcularXs("A","Otros",dto);
	
		IDLX xsLDyF= calcularXs("L","DyF",dto);
		IDLX xsLST= calcularXs("L","ST",dto);
		IDLX xsLIyT= calcularXs("L","IyT",dto);
		IDLX xsLOtros= calcularXs("L","Otros",dto);
		
		float[] CLemKDyF=new float[20];
		float[] CLemKST=new float[20];
		float[] CLemKIyT=new float[20];
		float[] CLemKOtros=new float[20];
		
		float[] CLemADyF=new float[20];
		float[] CLemAST=new float[20];
		float[] CLemAIyT=new float[20];
		float[] CLemAOtros=new float[20];
		
		float[] CLemLDyF=new float[20];
		float[] CLemLST=new float[20];
		float[] CLemLIyT=new float[20];
		float[] CLemLOtros=new float[20];
		
		for(Departamento d:DepartamentoDAO.getInstance().listaDepartamentos()){
			CLemKDyF[Integer.valueOf(d.getId().toString())]=(xsKDyF.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXem()*xsKDyF.getValorXec()*xsKDyF.getN())/(xsKDyF.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()==0?1:xsKDyF.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()*xsKDyF.getValorXc()==0?1:xsKDyF.getValorXc());
			CLemKST[Integer.valueOf(d.getId().toString())]=(xsKST.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXem()*xsKST.getValorXec()*xsKST.getN())/(xsKST.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()==0?1:xsKST.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()*xsKST.getValorXc()==0?1:xsKST.getValorXc());
			CLemKIyT[Integer.valueOf(d.getId().toString())]=(xsKIyT.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXem()*xsKIyT.getValorXec()*xsKIyT.getN())/(xsKIyT.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()==0?1:xsKIyT.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()*xsKIyT.getValorXc()==0?1:xsKIyT.getValorXc());
			CLemKOtros[Integer.valueOf(d.getId().toString())]=(xsKOtros.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXem()*xsKOtros.getValorXec()*xsKOtros.getN())/(xsKOtros.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()==0?1:xsKOtros.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()*xsKOtros.getValorXc()==0?1:xsKOtros.getValorXc());

			CLemADyF[Integer.valueOf(d.getId().toString())]=(xsADyF.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXem()*xsADyF.getValorXec()*xsADyF.getN())/(xsADyF.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()==0?1:xsADyF.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()*xsADyF.getValorXc()==0?1:xsADyF.getValorXc());
			CLemAST[Integer.valueOf(d.getId().toString())]=(xsAST.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXem()*xsAST.getValorXec()*xsAST.getN())/(xsAST.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()==0?1:xsAST.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()*xsAST.getValorXc()==0?1:xsAST.getValorXc());
			CLemAIyT[Integer.valueOf(d.getId().toString())]=(xsAIyT.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXem()*xsAIyT.getValorXec()*xsAIyT.getN())/(xsAIyT.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()==0?1:xsAIyT.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()*xsAIyT.getValorXc()==0?1:xsAIyT.getValorXc());
			CLemAOtros[Integer.valueOf(d.getId().toString())]=(xsAOtros.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXem()*xsAOtros.getValorXec()*xsAOtros.getN())/(xsAOtros.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()==0?1:xsAOtros.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()*xsAOtros.getValorXc()==0?1:xsAOtros.getValorXc());

			CLemLDyF[Integer.valueOf(d.getId().toString())]=(xsLDyF.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXem()*xsLDyF.getValorXec()*xsLDyF.getN())/(xsLDyF.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()==0?1:xsLDyF.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()*xsLDyF.getValorXc()==0?1:xsLDyF.getValorXc());
			CLemLST[Integer.valueOf(d.getId().toString())]=(xsLST.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXem()*xsLST.getValorXec()*xsLST.getN())/(xsLST.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()==0?1:xsLST.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()*xsLST.getValorXc()==0?1:xsLST.getValorXc());
			CLemLIyT[Integer.valueOf(d.getId().toString())]=(xsLIyT.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXem()*xsLIyT.getValorXec()*xsLIyT.getN())/(xsLIyT.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()==0?1:xsLIyT.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()*xsLIyT.getValorXc()==0?1:xsLIyT.getValorXc());
			CLemLOtros[Integer.valueOf(d.getId().toString())]=(xsLOtros.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXem()*xsLOtros.getValorXec()*xsLOtros.getN())/(xsLOtros.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()==0?1:xsLOtros.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].getValorXm()*xsLOtros.getValorXc()==0?1:xsLOtros.getValorXc());
		}
		
		float IPKDyF=0;
		float IPKST=0;
		float IPKIyT=0;
		float IPKOtros=0;
		
		float IPADyF=0;
		float IPAST=0;
		float IPAIyT=0;
		float IPAOtros=0;
		
		float IPLDyF=0;
		float IPLST=0;
		float IPLIyT=0;
		float IPLOtros=0;
		
		HashMap<Integer, IdlDepartamento> deptos=new HashMap<Integer, IdlDepartamento>();
		
				
		for(int i=1;i<20;i++){
			IPKDyF+=CLemKDyF[i];
			IPKST+=CLemKST[i];
			IPKIyT+=CLemKIyT[i];
			IPKOtros+=CLemKOtros[i];
			
			IPADyF+=CLemADyF[i];
			IPAST+=CLemAST[i];
			IPAIyT+=CLemAIyT[i];
			IPAOtros+=CLemAOtros[i];
			
			IPLDyF+=CLemLDyF[i];
			IPLST+=CLemLST[i];
			IPLIyT+=CLemLIyT[i];
			IPLOtros+=CLemLOtros[i];
			
			if(!deptos.containsKey(i))
			{
				IdlDepartamento depto=new IdlDepartamento();
				depto.setDepartamento(DepartamentoDAO.getInstance().buscarPorId(Long.valueOf(Integer.valueOf(i).toString())));
				deptos.put(Integer.valueOf(i),depto);
			}
			
			IdlDepartamento depto= deptos.get(Integer.valueOf(i));
			depto.setPorcentaje(CLemKDyF[i]+CLemKST[i]+CLemKIyT[i]+CLemKOtros[i]+CLemADyF[i]+CLemAST[i]+CLemAIyT[i]+CLemAOtros[i]+CLemLDyF[i]+CLemLST[i]+CLemLIyT[i]+CLemLOtros[i]);
		}
		
		float total=IPKDyF+IPKST+IPKIyT+IPKOtros+IPADyF+IPAST+IPAIyT+IPAOtros+IPLDyF+IPLST+IPLIyT+IPLOtros;
		
		//Registo en la base
		IPKDyF*=100;
		IPKST*=100;
		IPKIyT*=100;
		IPKOtros*=100;
		
		IPADyF*=100;
		IPAST*=100;
		IPAIyT*=100;
		IPAOtros*=100;
		
		IPLDyF*=100;
		IPLST*=100;
		IPLIyT*=100;
		IPLOtros*=100;


		Set<IdlDepartamentoDTO> deptosDTOs=new HashSet<IdlDepartamentoDTO>();
		ArrayList<Evento> eventosPais=EventoDAO.getInstance().listaEventosPorDepratamentoYTiposDeEvento(dto.getFechaFino(),dto.getFechaInicio(), 0L,idsDeEventos());
		
		for(IdlDepartamento d: deptos.values())
		{
			
			ArrayList<Evento> eventos=EventoDAO.getInstance().listaEventosPorDepratamentoYTiposDeEvento(dto.getFechaFino(),dto.getFechaInicio(), d.getDepartamento().getId(),idsDeEventos());
			if(total>0)
				d.setPorcentaje((d.getPorcentaje()/total)*100);
			else
				d.setPorcentaje(0);
		
			ArrayList<TipoEvento> tipoEventos=EventoDAO.getInstance().listaTiposEventos();
			Set<IdlTipoEvento> tiposEventos=new HashSet<IdlTipoEvento>();
			
			for(TipoEvento t:tipoEventos){
				ArrayList<Long> tipoE=new ArrayList<Long>();
				tipoE.add(t.getId());
				IdlTipoEvento idlTipoEvento=new IdlTipoEvento();
				Set<IdlEvento> eventosIdl=new HashSet<IdlEvento>();
				for(Evento e: EventoDAO.getInstance().listaEventosPorDepratamentoYTiposDeEvento(dto.getFechaFino(),dto.getFechaInicio(), d.getDepartamento().getId() ,tipoE))
				{
					eventosIdl.add(new IdlEvento(e.getDTO()));
				}
				if(eventos.size()>0)
					idlTipoEvento.setPocentaje((Float.valueOf(eventosIdl.size())/Float.valueOf(eventos.size()))*100);
				
				idlTipoEvento.setEventos(eventosIdl);
				idlTipoEvento.setTipoEvento(t);
				tiposEventos.add(idlTipoEvento);
			}
			
			d.setTiposEventos(tiposEventos);
			deptosDTOs.add(d.getDTO());
		}
		
		//Fin registro en la base
		
		
		float IPK=IPKDyF+IPKST+IPKIyT+IPKOtros;
		float IPA=IPADyF+IPAST+IPAIyT+IPAOtros;
		float IPL=IPLDyF+IPLST+IPLIyT+IPLOtros;
		
		//Esto es para que no de NaN en las siguientes cuentas
		IPK=IPK!=0?IPK:1;
		IPA=IPA!=0?IPA:1;
		IPL=IPL!=0?IPL:1;
		
		float IDLK=(1-((IPKDyF/IPK)*(IPKDyF/IPK)+(IPKST/IPK)*(IPKST/IPK)+(IPKIyT/IPK)*(IPKIyT/IPK)+(IPKOtros/IPK)*(IPKOtros/IPK)));
		float IDLA=(1-((IPADyF/IPA)*(IPADyF/IPA)+(IPAST/IPA)*(IPAST/IPA)+(IPAIyT/IPA)*(IPAIyT/IPA)+(IPAOtros/IPA)*(IPAOtros/IPA)));
		float IDLL=(1-((IPLDyF/IPL)*(IPLDyF/IPL)+(IPLST/IPL)*(IPLST/IPL)+(IPLIyT/IPL)*(IPLIyT/IPL)+(IPLOtros/IPL)*(IPLOtros/IPL)));
		
		float IDL=(IDLK+IDLA+IDLL)/3;
		
		dto.setValor(IDL);
		dto.setDepartamentos(deptosDTOs);
		
		IndicesDAO.getInstance().nuevoCalculoIndice(new IDL(dto));
		
	}

	private IDLX calcularXs(String xValor,String evento ,IdlDTO dto) {
		
		ArrayList<Long> tipoeventosdeslizamientosyflujos= idsDeEventosDeslizamientosYFlujos();
		ArrayList<Long> tipoeventossismotectonicos= idsDeEventosSismoTectonicos();
		ArrayList<Long> tipoeventosinundacionesytormentas= idsDeEventosInundacionesYTormentas();
		ArrayList<Long> tipoeventosotros= idsDeEventosOtros();
		
		
		IDLX xs=new IDLX();
		
		if(xValor.equals("K") && evento.equals("DyF")){
			//Fallecidos,
			xs=calcularXsDadoTipoEventos(xValor,tipoeventosdeslizamientosyflujos,dto);
		}
		
		if(xValor.equals("K") && evento.equals("ST")){
			 //Fallecidos
			xs=calcularXsDadoTipoEventos(xValor,tipoeventossismotectonicos,dto);
		}
		
		
		if(xValor.equals("K") && evento.equals("IyT")){
			 //Fallecidos
			xs=calcularXsDadoTipoEventos(xValor,tipoeventosinundacionesytormentas,dto);
		}
		
		
		if(xValor.equals("K") && evento.equals("Otros")){
			 //Fallecidos
			xs=calcularXsDadoTipoEventos(xValor,tipoeventosotros,dto);
		}
		
		if(xValor.equals("K") && evento.equals("DyF")){
			//Fallecidos,
			xs=calcularXsDadoTipoEventos(xValor,tipoeventosdeslizamientosyflujos,dto);
		}
		
		if(xValor.equals("K") && evento.equals("ST")){
			 //Fallecidos
			xs=calcularXsDadoTipoEventos(xValor,tipoeventossismotectonicos,dto);
		}
		
		
		if(xValor.equals("K") && evento.equals("IyT")){
			 //Fallecidos
			xs=calcularXsDadoTipoEventos(xValor,tipoeventosinundacionesytormentas,dto);
		}
		
		
		if(xValor.equals("K") && evento.equals("Otros")){
			 //Fallecidos
			xs=calcularXsDadoTipoEventos(xValor,tipoeventosotros,dto);
		}
		
		if(xValor.equals("A") && evento.equals("DyF")){
			//Afectados,
			xs=calcularXsDadoTipoEventos(xValor,tipoeventosdeslizamientosyflujos,dto);
		}
		
		if(xValor.equals("A") && evento.equals("ST")){
			 //Afectados
			xs=calcularXsDadoTipoEventos(xValor,tipoeventossismotectonicos,dto);
		}
		
		
		if(xValor.equals("A") && evento.equals("IyT")){
			 //Afectados
			xs=calcularXsDadoTipoEventos(xValor,tipoeventosinundacionesytormentas,dto);
		}
		
		if(xValor.equals("A") && evento.equals("Otros")){
			 //Afectados
			xs=calcularXsDadoTipoEventos(xValor,tipoeventosotros,dto);
		}
	
		if(xValor.equals("L") && evento.equals("DyF")){
			//Perdidas,
			xs=calcularXsDadoTipoEventos(xValor,tipoeventosdeslizamientosyflujos,dto);
		}
		
		if(xValor.equals("L") && evento.equals("ST")){
			 //Perdidas
			xs=calcularXsDadoTipoEventos(xValor,tipoeventossismotectonicos,dto);
		}
		
		
		if(xValor.equals("L") && evento.equals("IyT")){
			 //Perdidas
			xs=calcularXsDadoTipoEventos(xValor,tipoeventosinundacionesytormentas,dto);
		}
		
		if(xValor.equals("L") && evento.equals("Otros")){
			 //Perdidas
			xs=calcularXsDadoTipoEventos(xValor,tipoeventosotros,dto);
		}
		
		return xs;
	}

	private IDLX calcularXsDadoTipoEventos(String xValor,ArrayList<Long> tipoeventosindicado,IdlDTO dto) {
		
		IDLX xs= new IDLX();
		xs.setValoresPorDepartamento(new IDLX[20]);
		for(int i=0;i<20;i++){
			xs.getValoresPorDepartamento()[i]=new IDLX();
		}
		
		ArrayList<Long> tipoeventos= idsDeEventos();
		if(xValor.equals("K")){
			
			float totalFallecidos=0;
			float totalFallecidosTodsLosEventos=0;
			float totalN=0;
			for(Departamento d:DepartamentoDAO.getInstance().listaDepartamentos()){
				int fallecidos=0;	
				ArrayList<Evento> eventos= EventoDAO.getInstance().listaEventosPorDepratamentoYTiposDeEvento(dto.getFechaFino(),dto.getFechaInicio(), d.getId(),tipoeventosindicado);
				for(Evento e:eventos){
					fallecidos+=e.getMuertos();
				}
				totalFallecidos+=fallecidos;
				xs.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].setValorXem(Float.valueOf(String.valueOf(fallecidos)));
				//Xm  la suma total de X para todos los tipos de eventos considerados en el municipio m;  
	
				fallecidos=0;
				eventos= EventoDAO.getInstance().listaEventosPorDepratamentoYTiposDeEvento(dto.getFechaFino(),dto.getFechaInicio(),d.getId(),tipoeventos);
				for(Evento e:eventos){
					fallecidos+=e.getMuertos();
				}
				if(eventos.size()>0){
					totalN++;
				}
				totalFallecidosTodsLosEventos+=fallecidos;
				xs.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].setValorXm(Float.valueOf(String.valueOf(fallecidos)));
			}
			xs.setValorXec(totalFallecidos);
			xs.setValorXc(totalFallecidosTodsLosEventos);
			if(totalN>0)
				xs.setN(4/totalN);
			else
				xs.setN(0);
		}
		if(xValor.equals("A")){
			
			float totalAfectados=0;
			float totalAfectadosTodsLosEventos=0;
			float totalN=0;
			for(Departamento d:DepartamentoDAO.getInstance().listaDepartamentos()){
				int afectados=0;	
				ArrayList<Evento> eventos= EventoDAO.getInstance().listaEventosPorDepratamentoYTiposDeEvento(dto.getFechaFino(),dto.getFechaInicio(),d.getId(),tipoeventosindicado);
				for(Evento e:eventos){
					afectados+=e.getAfectados();
				}
				totalAfectados+=afectados;
				xs.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].setValorXem(Float.valueOf(String.valueOf(afectados)));
				//Xm  la suma total de X para todos los tipos de eventos considerados en el municipio m;  
	
				afectados=0;
				eventos= EventoDAO.getInstance().listaEventosPorDepratamentoYTiposDeEvento(dto.getFechaFino(),dto.getFechaInicio(),d.getId(),tipoeventos);
				for(Evento e:eventos){
					afectados+=e.getAfectados();
				}
				if(eventos.size()>0){
					totalN++;
				}
				totalAfectadosTodsLosEventos+=afectados;
				xs.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].setValorXm(Float.valueOf(String.valueOf(afectados)));
			}
			xs.setValorXec(totalAfectados);
			xs.setValorXc(totalAfectadosTodsLosEventos);
			if(totalN>0)
				xs.setN(4/totalN);
			else
				xs.setN(0);
		}
		if(xValor.equals("L")){
			
			float totalPerdidas=0;
			float totalPerdidasTodsLosEventos=0;
			float totalN=0;
			for(Departamento d:DepartamentoDAO.getInstance().listaDepartamentos()){
				int perdidas=0;	
				ArrayList<Evento> eventos= EventoDAO.getInstance().listaEventosPorDepratamentoYTiposDeEvento(dto.getFechaFino(),dto.getFechaInicio(),d.getId(),tipoeventosindicado);
				for(Evento e:eventos){
					perdidas+=e.getVivAfectadas()*0.25*dto.getValorVivindaSocial()+e.getVivDestruida()*dto.getValorVivindaSocial()+e.getCultivosBosques()*dto.getHectariaDeCultivo();
				}
				totalPerdidas+=perdidas;
				xs.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].setValorXem(Float.valueOf(String.valueOf(perdidas)));
				//Xm  la suma total de X para todos los tipos de eventos considerados en el municipio m;  
	
				perdidas=0;
				eventos= EventoDAO.getInstance().listaEventosPorDepratamentoYTiposDeEvento(dto.getFechaFino(),dto.getFechaInicio(),d.getId(),tipoeventos);
				for(Evento e:eventos){
					perdidas+=e.getVivAfectadas()*0.25*dto.getValorVivindaSocial()+e.getVivDestruida()*dto.getValorVivindaSocial()+e.getCultivosBosques()*dto.getHectariaDeCultivo();
				}
				if(eventos.size()>0){
					totalN++;
				}
				totalPerdidasTodsLosEventos+=perdidas;
				xs.getValoresPorDepartamento()[Integer.valueOf(d.getId().toString())].setValorXm(Float.valueOf(String.valueOf(perdidas)));
			}
			xs.setValorXec(totalPerdidas);
			xs.setValorXc(totalPerdidasTodsLosEventos);
			if(totalN>0)
				xs.setN(4/totalN);
			else
				xs.setN(0);
		}
		return xs;
	}

	private ArrayList<Long> idsDeEventos() {
		ArrayList<Long> ids=new ArrayList<Long>();
		for(Long i=1L; i<34;i++){
			ids.add(i);
		}
		return ids;
	}

	private ArrayList<Long> idsDeEventosOtros() {
		ArrayList<Long> ids=new ArrayList<Long>();
		for(Long i=1L; i<34;i++){
			if(i!=2 && i!=3 && i!=16  && i!=17  && i!=18 && i!=27 && i!=28 && i!=30 && i!=26  ){
				ids.add(i);
			}
		}
		return ids;
	}

	private ArrayList<Long> idsDeEventosDeslizamientosYFlujos() {
		ArrayList<Long> ids=new ArrayList<Long>();
		ids.add(2L);	
		ids.add(3L);	
		return ids;
	}

	private ArrayList<Long> idsDeEventosInundacionesYTormentas() {
		ArrayList<Long> ids=new ArrayList<Long>();
		ids.add(16L);
		ids.add(17L);
		ids.add(18L);
		ids.add(27L);
		ids.add(28L);
		ids.add(30L);
		return ids;
	}

	private ArrayList<Long> idsDeEventosSismoTectonicos() {
		ArrayList<Long> ids=new ArrayList<Long>();
		ids.add(26L);
		return ids;
	}

	public void calcularIGR(IgrDTO  dto) {
		Float valIr = Float.valueOf((dto.getIr1() + dto.getIr2() + dto.getIr3()+ dto.getIr4()+ dto.getIr5()+ dto.getIr6())/6);
		Float valRr = Float.valueOf((dto.getRr1() + dto.getRr2() + dto.getRr3()+ dto.getRr4()+ dto.getRr5()+ dto.getRr6())/6);
		Float valMd = Float.valueOf((dto.getMd1() + dto.getMd2() + dto.getMd3()+ dto.getMd4()+ dto.getMd5()+ dto.getMd6())/6);
		Float valPf = Float.valueOf((dto.getPf1() + dto.getPf2() + dto.getPf3()+ dto.getPf4()+ dto.getPf5()+ dto.getPf6())/6);
		Float val = Float.valueOf((valIr + valRr + valMd + valPf)/4);
		val= val/5;
		dto.setValor(val);
		
		IndicesDAO.getInstance().nuevoCalculoIndice(new IGR(dto));
		
	}

}
