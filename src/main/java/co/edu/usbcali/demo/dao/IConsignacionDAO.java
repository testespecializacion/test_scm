package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.ConsignacionesId;


public interface IConsignacionDAO {
	public void grabar(Consignaciones Consignaciones);
	public void modificar(Consignaciones Consignaciones);
	public void borrar(Consignaciones Consignaciones);
	public Consignaciones consultarConsignacionesId (ConsignacionesId Id);
	public List<Consignaciones> consultarTodos();

}
