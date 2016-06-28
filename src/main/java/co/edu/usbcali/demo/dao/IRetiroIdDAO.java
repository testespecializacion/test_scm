package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.RetirosId;

public interface IRetiroIdDAO {
	
	public void grabar(RetirosId retirosId);
	public void modificar(RetirosId retirosId);
	public void borrar(RetirosId retirosId);
	public RetirosId consultarRetirosId(long retCodigo);
	public List<RetirosId> consultarTodos();
	

}
