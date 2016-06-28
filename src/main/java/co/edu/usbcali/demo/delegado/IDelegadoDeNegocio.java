package co.edu.usbcali.demo.delegado;

import java.math.BigDecimal;
import java.util.List;

import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.TiposDocumentos;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;

public interface IDelegadoDeNegocio {
	
	public void grabarClientes(Clientes clientes)throws Exception;
	public void modificarClientes(Clientes clientes)throws Exception;
	public void borrarClientes(Clientes clientes)throws Exception;
	public Clientes consultarClinetePorId(long cliId)throws Exception;
	public List<Clientes> consultarTodosClientes()throws Exception;
	
	
	public void grabarTiposDocumentos(TiposDocumentos entity)throws  Exception;
	public void modificarTiposDocumentos(TiposDocumentos entity)throws  Exception;
	public void borrarTiposDocumentos(TiposDocumentos entity)throws  Exception;
	public TiposDocumentos consultarTipoDocumentoId(long tdocCodigo)throws  Exception;
	public List<TiposDocumentos> consultarTodosTiposDocumentos()throws Exception;
	
	public void realizarConsignacion(String numeroCuenta, Integer clienteConsginacion, Integer usuarioConsginacion, BigDecimal valorConsginacion) throws Exception;
	
	public void realizarRetiro(String numeroRetiro, Integer clienteRetiro, Integer usuarioRetiro, BigDecimal valorRetiro) throws Exception;
	
	
	public void grabarUsuarios(Usuarios usuarios)throws Exception;
	public void modificarUsuarios(Usuarios usuarios)throws Exception;
	public void borrarUsuarios(Usuarios usuarios)throws Exception;
	public Usuarios consultarUsuariosPorCedula(long usuCedula)throws Exception;
	public List<Usuarios> consultarTodosUsuarios()throws Exception;
	
	
	public void grabarCuentas(Cuentas cuentas)throws Exception;
	public void modificarCuentas(Cuentas cuentas)throws Exception;
	public void borrarCuentas(Cuentas cuentas)throws Exception;
	public Cuentas consultarCuenta(String cueNumero)throws Exception;
	public List<Cuentas> consultarTodosCuentas()throws Exception;
	
	public void grabarTiposUsuarios(TiposUsuarios tiposUsuarios)throws Exception;
	public void modificarTiposUsuarios(TiposUsuarios tiposUsuarios)throws Exception;
	public void borrarTiposUsuarios(TiposUsuarios tiposUsuarios)throws Exception;
	public TiposUsuarios consultarTiposUsuarios(long tusuCodigo) throws Exception;
	public List<TiposUsuarios> consultarTodosTiposUsuarios()throws Exception;

}
