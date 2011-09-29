package services;
import data.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LikeExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PerfilService extends ServiceBase {
	
	// So Spring can inject the session factory
	
	
	public List<Perfil> findAll(){
		Criteria criteria=sess().createCriteria(Perfil.class);
		
		return criteria.list();
	}
	
	public List<Perfil> findLikeNombre(String name){
		Criteria criteria=sess().createCriteria(Perfil.class);
		criteria.add(Restrictions.like("nombre", name+"%"));
		return criteria.list();
	}

	public void nuevo(Perfil perfil) {
		// TODO Auto-generated method stub
		sess().save(perfil);
	}

	public List<Permiso> findAllPermisos() {
		Criteria criteria=sess().createCriteria(Permiso.class);
		
		return criteria.list();
	}
}
