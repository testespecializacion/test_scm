package co.edu.usbcali.demo.logica;

import java.math.BigDecimal;

public interface ITransaccionesLogica {
	
	public void realizarConsignacion(String numeroCuenta, Integer clienteConsginacion, Integer usuarioConsginacion,
			BigDecimal valorConsginacion) throws Exception;
	
	public void realizarRetiro(String numeroRetiro, Integer clienteRetiro, Integer usuarioRetiro, BigDecimal valorRetiro) throws Exception;

}
