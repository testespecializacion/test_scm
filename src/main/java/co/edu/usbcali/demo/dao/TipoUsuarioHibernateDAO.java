package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.TiposUsuarios;

@Repository
@Scope("singleton")
public class TipoUsuarioHibernateDAO implements ITipoUsuarioDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void grabar(TiposUsuarios tipoUsuarios) {
		sessionFactory.getCurrentSession().save(tipoUsuarios);		
	}

	@Override
	public void modificar(TiposUsuarios tipoUsuarios) {
		sessionFactory.getCurrentSession().update(tipoUsuarios);		
	}

	@Override
	public void borrar(TiposUsuarios tipoUsuarios) {
		sessionFactory.getCurrentSession().delete(tipoUsuarios);
	}

	@Override
	public TiposUsuarios consultarTiposUsuarios(long tusuCodigo) {
		return sessionFactory.getCurrentSession().get(TiposUsuarios.class, tusuCodigo);
	}

	@Override
	public List<TiposUsuarios> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(TiposUsuarios.class).list();
	}

}
