package fing.satode.pl.propiedadesSiniestradas;

import java.util.ArrayList;
import java.util.List;


import fing.satode.constantes.TipoPuntoReferencia;
import fing.satode.data.ParcelaDTO;
import fing.satode.dominio.Bomberos;
import fing.satode.dominio.Caminera;
import fing.satode.dominio.Cuartel;
import fing.satode.dominio.Foto;
import fing.satode.dominio.Hospital;
import fing.satode.dominio.PROtros;
import fing.satode.dominio.Parcela;
import fing.satode.dominio.Policia;
import fing.satode.dominio.PuntoReferencia;
import fing.satode.dominio.Refugio;
import fing.satode.pl.base.DAOBase;

public class PropiedadesSiniestradasDAO extends DAOBase {

	private static PropiedadesSiniestradasDAO instance;
	
	private PropiedadesSiniestradasDAO(){}
	
	public static PropiedadesSiniestradasDAO getInstance(){
		if(instance==null){
			instance = new PropiedadesSiniestradasDAO();
		}
		
		return instance;
	}
	
		public ArrayList<Parcela> listaParcelas() {
		List list=sess().createQuery("from Parcela order by departamento.nombre").list();
		ArrayList<Parcela> res= new ArrayList<Parcela>(list);
		
		return res;
	}
	
	public void nuevaParcela(Parcela parcela) {
		sess().save(parcela);
		
	}
	
	
	public void modificarParcela(Parcela parcela) {
		parcela=(Parcela)sess().merge(parcela);
		sess().update(parcela);
	}

	public void eliminarParcela(Parcela parcela) {
		parcela= (Parcela)sess().get(Parcela.class,parcela.getId());
		sess().delete(parcela);
	}

	public Parcela getParcela(Long id) {
		return (Parcela)sess().get(Parcela.class,id);
	}

	public Foto getFoto(Long id) {
		return (Foto)sess().get(Foto.class,id);
		
	}

	public void borrarFotos(Long idParcela, ArrayList<Foto> fotosAEliminar) {
		Parcela parcela= (Parcela)sess().get(Parcela.class,idParcela);
		for(Foto f : fotosAEliminar){
			Foto fs=null;
			for(Foto fi:parcela.getFotos()){
				if(fi.getId().equals(f.getId())){
					fs=fi;
					break;
				}
			}
			parcela.getFotos().remove(fs);
		}
		sess().update(parcela);
	}
		
	

}
