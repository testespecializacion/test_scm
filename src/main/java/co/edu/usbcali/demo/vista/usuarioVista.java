package co.edu.usbcali.demo.vista;

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
import org.primefaces.component.password.Password;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.demo.delegado.IDelegadoDeNegocio;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;

@ViewScoped
@ManagedBean
public class usuarioVista {
	
	private final static Logger log=LoggerFactory.getLogger(usuarioVista.class);
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<SelectItem> losUsuariosTipos;
	
	private InputText txtCedula;
	private InputText txtNombre;
	private InputText txtLogin;
	private Password pswClave;
	
	private SelectOneMenu somTipo;
	
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;
	
	
	public List<SelectItem> getLosUsuarios() {
		if(losUsuariosTipos == null){
			List<TiposUsuarios> losUsuarios;
			try{
				losUsuariosTipos = new ArrayList<>();
				losUsuarios = delegadoDeNegocio.consultarTodosTiposUsuarios();
				for(TiposUsuarios tiposUsuarios : losUsuarios){
					losUsuariosTipos.add(new SelectItem(tiposUsuarios.getTusuCodigo(), tiposUsuarios.getTusuNombre()));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return losUsuariosTipos;
	}

	public void setLosUsuarios(List<SelectItem> losUsuarios) {
		this.losUsuariosTipos = losUsuarios;
	}
	
	public String crearAction(){
		log.info("Ingreso a crear");
		
		try {
			Usuarios usuarios=new Usuarios();
			usuarios.setUsuCedula(Long.parseLong(txtCedula.getValue().toString().trim()));
			usuarios.setUsuNombre(txtNombre.getValue().toString().trim());
			usuarios.setUsuLogin(txtLogin.getValue().toString().trim());
			usuarios.setUsuClave(pswClave.getValue().toString().trim());
			
			TiposUsuarios tiposUsuarios = delegadoDeNegocio.consultarTiposUsuarios(Long.parseLong(somTipo.getValue().toString().trim()));
			
			usuarios.setTiposUsuarios(tiposUsuarios);
			
			delegadoDeNegocio.grabarUsuarios(usuarios);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario se creo con exito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}
	
	public String modificarAction(){
		log.info("Ingreso a modificar");
		try {
			Usuarios usuarios=new Usuarios();
			usuarios.setUsuCedula(Long.parseLong(txtCedula.getValue().toString().trim()));
			usuarios.setUsuNombre(txtNombre.getValue().toString().trim());
			usuarios.setUsuLogin(txtLogin.getValue().toString().trim());
			usuarios.setUsuClave(pswClave.getValue().toString().trim());
			
			TiposUsuarios tiposUsuarios = delegadoDeNegocio.consultarTiposUsuarios(Long.parseLong(somTipo.getValue().toString().trim()));
			
			usuarios.setTiposUsuarios(tiposUsuarios);
			
			delegadoDeNegocio.modificarUsuarios(usuarios);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario se modifico con exito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}
	
	public String borrarAction(){
		log.info("Ingreso a borrar");
		try {
			Usuarios usuarios=new Usuarios();
			usuarios.setUsuCedula(Long.parseLong(txtCedula.getValue().toString().trim()));
			
			delegadoDeNegocio.borrarUsuarios(usuarios);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario se elimino con exito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}
	
	public String limpiarAction(){
		log.info("Ingreso a limpiar");
		return "";
	}
	
	
	public void txtCedulaListener(){
		Usuarios entity=null;
				
		try {
			Long id=Long.parseLong(txtCedula.getValue().toString().trim());
			entity=delegadoDeNegocio.consultarUsuariosPorCedula(id);
		} catch (Exception e) {
		}

		if(entity==null){
			txtNombre.resetValue();
			txtLogin.resetValue();
			pswClave.resetValue();
			somTipo.setValue("-1");
			
			btnBorrar.setDisabled(true);
			btnCrear.setDisabled(false);
			btnModificar.setDisabled(true);
		}else{
			
			txtNombre.setValue(entity.getUsuNombre());
			txtLogin.setValue(entity.getUsuLogin());
			pswClave.setValue(entity.getUsuClave());
			somTipo.setValue(entity.getTiposUsuarios().getTusuCodigo());
			
			btnBorrar.setDisabled(false);
			btnCrear.setDisabled(true);
			btnModificar.setDisabled(false);
			
		}
		
	}
	
	
	public InputText getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(InputText txtCedula) {
		this.txtCedula = txtCedula;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputText getTxtLogin() {
		return txtLogin;
	}

	public void setTxtLogin(InputText txtLogin) {
		this.txtLogin = txtLogin;
	}

	public Password getPswClave() {
		return pswClave;
	}

	public void setPswClave(Password pswClave) {
		this.pswClave = pswClave;
	}

	public SelectOneMenu getSomTipo() {
		return somTipo;
	}

	public void setSomTipo(SelectOneMenu somTipo) {
		this.somTipo = somTipo;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public CommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public CommandButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(CommandButton btnBorrar) {
		this.btnBorrar = btnBorrar;
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
