package co.edu.usbcali.demo.delegado;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.edu.usbcali.demo.logica.IClienteLogica;
import co.edu.usbcali.demo.logica.ICuentasLogica;
import co.edu.usbcali.demo.logica.ITiposDocumentosLogica;
import co.edu.usbcali.demo.logica.ITiposUsuariosLogica;
import co.edu.usbcali.demo.logica.ITransaccionesLogica;
import co.edu.usbcali.demo.logica.IUsuariosLogica;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.TiposDocumentos;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;


@Scope("singleton")
@Component("delegadoDeNegocio")
public class DelegadoDeNegocio implements IDelegadoDeNegocio {
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ITiposDocumentosLogica tiposDocumentosLogica;
	
	@Autowired
	private IUsuariosLogica usuariosLogica;

	@Autowired
	private ITransaccionesLogica transaccionesLogica;
	
	@Autowired
	private ICuentasLogica cuentasLogica;
	
	@Autowired
	private ITiposUsuariosLogica tiposUsuariosLogica;
	
	
	
	@Override
	public void realizarConsignacion(String numeroCuenta, Integer clienteConsginacion, Integer usuarioConsginacion, BigDecimal valorConsginacion) throws Exception {
		transaccionesLogica.realizarConsignacion(numeroCuenta, clienteConsginacion, usuarioConsginacion, valorConsginacion);
	}
	
	@Override
	public void realizarRetiro(String numeroRetiro, Integer clienteRetiro, Integer usuarioRetiro,
			BigDecimal valorRetiro) throws Exception {
		transaccionesLogica.realizarRetiro(numeroRetiro, clienteRetiro, usuarioRetiro, valorRetiro);
		
	}

	@Override
	public void grabarClientes(Clientes clientes) throws Exception {
		clienteLogica.grabar(clientes);
	}

	@Override
	public void modificarClientes(Clientes clientes) throws Exception {
		clienteLogica.modificar(clientes);
		
	}

	@Override
	public void borrarClientes(Clientes clientes) throws Exception {
		clienteLogica.borrar(clientes);
		
	}

	@Override
	public Clientes consultarClinetePorId(long cliId) throws Exception {		
		return clienteLogica.consultarClinetePorId(cliId);
	}

	@Override
	public List<Clientes> consultarTodosClientes() throws Exception {
		return clienteLogica.consultarTodos();
	}

	@Override
	public void grabarTiposDocumentos(TiposDocumentos entity) throws Exception {
		tiposDocumentosLogica.grabar(entity);		
	}

	@Override
	public void modificarTiposDocumentos(TiposDocumentos entity) throws Exception {
		tiposDocumentosLogica.modificar(entity);
		
	}

	@Override
	public void borrarTiposDocumentos(TiposDocumentos entity) throws Exception {
		tiposDocumentosLogica.borrar(entity);
		
	}

	@Override
	public TiposDocumentos consultarTipoDocumentoId(long tdocCodigo) throws Exception {
		return tiposDocumentosLogica.consultarTipoDocumentoId(tdocCodigo);
	}

	@Override
	public List<TiposDocumentos> consultarTodosTiposDocumentos() throws Exception {
		return tiposDocumentosLogica.consultarTodos();
	}


	

	@Override
	public void grabarUsuarios(Usuarios usuarios) throws Exception {
		usuariosLogica.grabar(usuarios);
		
	}

	@Override
	public void modificarUsuarios(Usuarios usuarios) throws Exception {
		usuariosLogica.modificar(usuarios);
		
	}



	@Override
	public void borrarUsuarios(Usuarios usuarios) throws Exception {
		usuariosLogica.borrar(usuarios);	
		
	}



	@Override
	public Usuarios consultarUsuariosPorCedula(long usuCedula) throws Exception {
		return usuariosLogica.consultarUsuariosPorCedula(usuCedula);
	}



	@Override
	public List<Usuarios> consultarTodosUsuarios() throws Exception {
		return usuariosLogica.consultarTodos();
	}

	
	
	
	@Override
	public void grabarCuentas(Cuentas cuentas) throws Exception {
		cuentasLogica.grabar(cuentas);		
		
	}

	@Override
	public void modificarCuentas(Cuentas cuentas) throws Exception {
		cuentasLogica.modificar(cuentas);	
		
	}

	@Override
	public void borrarCuentas(Cuentas cuentas) throws Exception {
		cuentasLogica.borrar(cuentas);	
		
	}

	@Override
	public Cuentas consultarCuenta(String cueNumero) throws Exception {
		return cuentasLogica.consultarCuenta(cueNumero);
	}

	@Override
	public List<Cuentas> consultarTodosCuentas() throws Exception {
		return cuentasLogica.consultarTodos();
	}

	@Override
	public void grabarTiposUsuarios(TiposUsuarios tiposUsuarios) throws Exception {
		tiposUsuariosLogica.grabar(tiposUsuarios);
		
	}

	@Override
	public void modificarTiposUsuarios(TiposUsuarios tiposUsuarios) throws Exception {
		tiposUsuariosLogica.modificar(tiposUsuarios);
		
	}

	@Override
	public void borrarTiposUsuarios(TiposUsuarios tiposUsuarios) throws Exception {
		tiposUsuariosLogica.borrar(tiposUsuarios);
		
	}

	@Override
	public TiposUsuarios consultarTiposUsuarios(long tusuCodigo) throws Exception {
		return tiposUsuariosLogica.consultarTiposUsuarios(tusuCodigo);
	}

	@Override
	public List<TiposUsuarios> consultarTodosTiposUsuarios() throws Exception {
		return tiposUsuariosLogica.consultarTodos();
	}

	


}
