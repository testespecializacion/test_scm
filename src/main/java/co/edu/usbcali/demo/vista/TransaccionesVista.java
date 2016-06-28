package co.edu.usbcali.demo.vista;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.demo.delegado.IDelegadoDeNegocio;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.Usuarios;

@ViewScoped
@ManagedBean
public class TransaccionesVista {
	
	private final static Logger log=LoggerFactory.getLogger(TransaccionesVista.class);
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	//private List<Usuarios> losUsuarios;
	private List<SelectItem> losUsuarios;
	
	//private List<SelectItem> losTiposDocumentoItems;
	
	private InputText txtNumeroCuenta;
	private InputText txtCliente;
	private InputText txtValor;
	
	private SelectOneMenu somUsuario;
	
	private CommandButton btnConsignar;
	private CommandButton btnRetirar;
	private CommandButton btnLimpiar;
	
	
	public String consignarAction(){
		log.info("Ingreso a consignar");
		
		try {
			delegadoDeNegocio.realizarConsignacion(txtNumeroCuenta.getValue().toString().trim(), 
												   Integer.parseInt(txtCliente.getValue().toString().trim()), 
												   Integer.parseInt(somUsuario.getValue().toString().trim()), 
												   new BigDecimal((txtValor.getValue().toString().trim())));
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "La transaccion se realizó con exito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}
	
	public String retirarAction(){
		log.info("Ingreso a retirar");
		
		try {
			delegadoDeNegocio.realizarRetiro(txtNumeroCuenta.getValue().toString().trim(), 
												   Integer.parseInt(txtCliente.getValue().toString().trim()), 
												   Integer.parseInt(somUsuario.getValue().toString().trim()), 
												   new BigDecimal(txtValor.getValue().toString().trim()));
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "La transaccion se realizó con exito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}
	
	public String limpiarAction(){
		log.info("Ingreso a limpiar");
		return "";
	}
	
	public List<SelectItem> getLosUsuarios() {
		try {
			if(losUsuarios==null){
				losUsuarios=new ArrayList<SelectItem>();
				List<Usuarios> losEntity=delegadoDeNegocio.consultarTodosUsuarios();
				for (Usuarios usuarios : losEntity) {
					losUsuarios.add(new SelectItem(usuarios.getUsuCedula(),usuarios.getUsuLogin()));
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return losUsuarios;
	}
	
	
	public void txtClienteListener(){
		Clientes entity = null;
		
		try {
			Long id=Long.parseLong(txtCliente.getValue().toString().trim());
			entity = delegadoDeNegocio.consultarClinetePorId(id);
		} catch (Exception e) {
		}
		
		if(entity==null){
			txtNumeroCuenta.resetValue();
			txtValor.resetValue();
			somUsuario.setValue("-1");
			
			btnConsignar.setDisabled(true);
			btnRetirar.setDisabled(true);
			btnLimpiar.setDisabled(false);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario "+txtCliente.getValue().toString().trim()+" no existe", ""));
		}else{
			
	        btnConsignar.setDisabled(false);
			btnRetirar.setDisabled(false);
			btnLimpiar.setDisabled(false);
		}
		
	}
	
	public void txtCuentaListener(){
		Cuentas entity = null;
		Clientes clientes = null;
		
		try {
			String cuenta = txtNumeroCuenta.getValue().toString().trim();
			entity = delegadoDeNegocio.consultarCuenta(cuenta);
		} catch (Exception e) {
		}
		
		if(entity==null){
			txtValor.resetValue();
			somUsuario.setValue("-1");
			
			btnConsignar.setDisabled(true);
			btnRetirar.setDisabled(true);
			btnLimpiar.setDisabled(false);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "La cuenta "+txtNumeroCuenta.getValue().toString().trim()+" no existe", ""));
		}else{
			
			try {
				Long id=Long.parseLong(txtCliente.getValue().toString().trim());
				clientes = delegadoDeNegocio.consultarClinetePorId(id);
			} catch (Exception e) {
			}
			
			if(clientes==null){
				btnConsignar.setDisabled(true);
				btnRetirar.setDisabled(true);
				btnLimpiar.setDisabled(false);
			}
			else
			 btnConsignar.setDisabled(false);
			 btnRetirar.setDisabled(false);
			 btnLimpiar.setDisabled(false);
			}
		}
		
	
	
	public void setLosUsuarios(List<SelectItem> losUsuarios) {
		this.losUsuarios = losUsuarios;
	}
	
	public InputText getTxtNumeroCuenta() {
		return txtNumeroCuenta;
	}
	
	public void setTxtNumeroCuenta(InputText txtNumeroCuenta) {
		this.txtNumeroCuenta = txtNumeroCuenta;
	}
	
	public InputText getTxtCliente() {
		return txtCliente;
	}
	
	public void setTxtCliente(InputText txtCliente) {
		this.txtCliente = txtCliente;
	}
	
	public InputText getTxtValor() {
		return txtValor;
	}
	
	public void setTxtValor(InputText txtValor) {
		
		this.txtValor = txtValor;
	}
	
	public SelectOneMenu getSomUsuario() {
		return somUsuario;
	}
	
	public void setSomUsuario(SelectOneMenu somUsuario) {
		this.somUsuario = somUsuario;
	}
	
	public CommandButton getBtnConsignar() {
		return btnConsignar;
	}
	
	public void setBtnConsignar(CommandButton btnConsignar) {
		this.btnConsignar = btnConsignar;
	}
	
	public CommandButton getBtnRetirar() {
		return btnRetirar;
	}
	
	public void setBtnRetirar(CommandButton btnRetirar) {
		this.btnRetirar = btnRetirar;
	}
	
	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}
	
	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}
	
	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}

}
