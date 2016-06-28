package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.TiposUsuarios;

public interface ITipoUsuarioDAO {
	
	public void grabar(TiposUsuarios tipoUsuarios);
	public void modificar(TiposUsuarios tipoUsuarios);
	public void borrar(TiposUsuarios tipoUsuarios);
	public TiposUsuarios consultarTiposUsuarios(long tusuCodigo);
	public List<TiposUsuarios> consultarTodos();
}
