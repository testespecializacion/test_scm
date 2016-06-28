package co.edu.usbcali.demo.vista;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.edu.usbcali.demo.delegado.IDelegadoDeNegocio;
import co.edu.usbcali.demo.modelo.Cuentas;

@ViewScoped
@ManagedBean
public class CuentaVista {
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Cuentas> losClientes;
	
	public List<Cuentas> getLosClientes() {
		if(losClientes==null){
			try {
				losClientes=delegadoDeNegocio.consultarTodosCuentas();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return losClientes;
	}

	public void setLosClientes(List<Cuentas> losClientes) {
		this.losClientes = losClientes;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	
	
	
	

}
