package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.RetirosId;

@Repository
@Scope("singleton")
public class RetiroIdHibernateDAO implements IRetiroIdDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void grabar(RetirosId retirosId) {
		sessionFactory.getCurrentSession().save(retirosId);
	}

	@Override
	public void modificar(RetirosId retirosId) {
		sessionFactory.getCurrentSession().update(retirosId);
	}

	@Override
	public void borrar(RetirosId retirosId) {
		sessionFactory.getCurrentSession().delete(retirosId);
	}

	@Override
	public RetirosId consultarRetirosId(long retCodigo) {
		return sessionFactory.getCurrentSession().get(RetirosId.class, retCodigo);
	}

	@Override
	public List<RetirosId> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(RetirosId.class).list();
	}

}
