package co.edu.usbcali.demo.logica;

import java.math.BigDecimal;
import java.util.Date;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.IConsignacionDAO;
import co.edu.usbcali.demo.dao.ICuentasDAO;
import co.edu.usbcali.demo.dao.IRetiroDAO;
import co.edu.usbcali.demo.dao.IUsuarioDAO;
import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.ConsignacionesId;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;
import co.edu.usbcali.demo.modelo.Usuarios;

@Service
@Scope("singleton")
public class TransaccionesLogica implements ITransaccionesLogica {
	
	//private Logger log=LoggerFactory.getLogger(TransaccionesLogica.class);
	
	@Autowired
	private IConsignacionDAO consignacionDAO;
	
	@Autowired
	private IRetiroDAO retiroDAO;
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	private ICuentasDAO cuentasDAO;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void realizarConsignacion(String numeroCuenta, Integer clienteConsginacion, Integer usuarioConsginacion, BigDecimal valorConsginacion) throws Exception {
		Cuentas cuentas = cuentasDAO.consultarCuenta(numeroCuenta);
		Integer value = valorConsginacion.compareTo(new BigDecimal(1));
		Integer maximo = valorConsginacion.compareTo(new BigDecimal(9999999.99));
		Integer consecutivo = consignacionDAO.consultarTodos().size() + 1;
		BigDecimal valor = cuentas.getCueSaldo(); 
		BigDecimal suma = valor.add(valorConsginacion);
		Integer total = suma.compareTo(new BigDecimal(9999999.99));
		
		
		if(value == -1){
			throw new Exception("El Valor de la consignación debe ser mayor que cero");
		}
		
		if(maximo == 1){
			throw new Exception("El Valor de la consignación excede el valor maximo permitido");
		}
		
		if(total == 1){
			throw new Exception("El Valor del saldo excede el valor maximo permitido por cuenta");
		}
		Consignaciones consignaciones = new Consignaciones();
		Usuarios usuarios = usuarioDAO.consultarUsuariosPorCedula(usuarioConsginacion); 
	
		
		ConsignacionesId consignacionesId = new ConsignacionesId(consecutivo,cuentas.getCueNumero());
		
		consignaciones.setUsuarios(usuarios);
		consignaciones.setId(consignacionesId);
		consignaciones.setConFecha(new Date());
		consignaciones.setConValor(valorConsginacion);
		consignaciones.setConDescripcion("CONSGINACION POR VALOR DE "+valorConsginacion);
		consignaciones.setCuentas(cuentas);
		
		consignacionDAO.grabar(consignaciones);
		
		BigDecimal valorT = cuentas.getCueSaldo(); 
		valorT = valorT.add(valorConsginacion);
		cuentas.setCueSaldo(valorT);
		
		cuentasDAO.modificar(cuentas);
		
	}
	
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void realizarRetiro(String numeroRetiro, Integer clienteRetiro, Integer usuarioRetiro, BigDecimal valorRetiro) throws Exception {
		Cuentas cuentas = cuentasDAO.consultarCuenta(numeroRetiro);
		BigDecimal valor = cuentas.getCueSaldo(); 
		Integer value = valorRetiro.compareTo(new BigDecimal(10000));
		Integer saldo = valor.compareTo(valorRetiro);
		Integer consecutivo = retiroDAO.consultarTodos().size() + 1;
		
		if(value == -1){
			throw new Exception("El Valor del retiro debe ser mayor que 10.000");
		}
		
		if(saldo == -1){
			throw new Exception("El Valor del retiro es mayor que el saldo de su cuenta");
		}
		
		Retiros retiros = new Retiros();
		Usuarios usuarios = usuarioDAO.consultarUsuariosPorCedula(usuarioRetiro); 
		RetirosId retirosId = new RetirosId(consecutivo, numeroRetiro);
	
		retiros.setId(retirosId);
		retiros.setUsuarios(usuarios);
		retiros.setCuentas(cuentas);
		retiros.setRetFecha(new Date());
		retiros.setRetDescripcion("RETIRO POR VALOR DE "+valorRetiro);
		retiros.setRetValor(valorRetiro);
		
		retiroDAO.grabar(retiros);
		
		valor = valor.subtract(valorRetiro);
		cuentas.setCueSaldo(valor);
		
		cuentasDAO.modificar(cuentas);
		
	}

}
