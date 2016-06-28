package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;

public interface IRetirosLogica {
	
	public void grabar(Retiros retiros)throws Exception;
	public void modificar(Retiros retiros)throws Exception;
	public void borrar(Retiros retiros)throws Exception;
	public Retiros consultarRetiros(RetirosId id)throws Exception;
	public List<Retiros> consultarTodos()throws Exception;

}
