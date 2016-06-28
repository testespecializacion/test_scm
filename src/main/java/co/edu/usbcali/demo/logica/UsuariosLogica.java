package co.edu.usbcali.demo.logica;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.IUsuarioDAO;
import co.edu.usbcali.demo.modelo.Usuarios;

@Service
@Scope("singleton")
public class UsuariosLogica implements IUsuariosLogica {
	
	private Logger log=LoggerFactory.getLogger(ClienteLogica.class);
	
	@Autowired
	private IUsuarioDAO UsuariosDAO;
	
	@Autowired
	private Validator validator;

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void grabar(Usuarios usuarios) throws Exception {

		StringBuilder stringBuilder=new StringBuilder();
		
		Set<ConstraintViolation<Usuarios>> constraintViolations=validator.validate(usuarios);
		if(constraintViolations.size()>0){
			for (ConstraintViolation<Usuarios> constraintViolation : constraintViolations) {
				log.error(constraintViolation.getPropertyPath().toString());
				log.error(constraintViolation.getMessage());
				stringBuilder.append(constraintViolation.getPropertyPath().toString());
				stringBuilder.append("-");
				stringBuilder.append(constraintViolation.getMessage());
				stringBuilder.append(",");
			}
			throw new Exception(stringBuilder.toString());
		}
		
		UsuariosDAO.grabar(usuarios);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificar(Usuarios usuarios) throws Exception {

		StringBuilder stringBuilder=new StringBuilder();
		
		Set<ConstraintViolation<Usuarios>> constraintViolations=validator.validate(usuarios);
		if(constraintViolations.size()>0){
			for (ConstraintViolation<Usuarios> constraintViolation : constraintViolations) {
				log.error(constraintViolation.getPropertyPath().toString());
				log.error(constraintViolation.getMessage());
				stringBuilder.append(constraintViolation.getPropertyPath().toString());
				stringBuilder.append("-");
				stringBuilder.append(constraintViolation.getMessage());
				stringBuilder.append(",");
			}
			throw new Exception(stringBuilder.toString());
		}
		UsuariosDAO.modificar(usuarios);

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void borrar(Usuarios usuarios) throws Exception {
		
		Usuarios usuariosId = UsuariosDAO.consultarUsuariosPorCedula(usuarios.getUsuCedula());
		if(usuariosId==null){
			throw new Exception("El usuario que desea eliminar no existe");
		}
		
		UsuariosDAO.borrar(usuariosId);

	}

	@Override
	@Transactional(readOnly=true)
	public Usuarios consultarUsuariosPorCedula(long usuCedula) throws Exception {
		return UsuariosDAO.consultarUsuariosPorCedula(usuCedula);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuarios> consultarTodos() throws Exception {		
		return UsuariosDAO.consultarTodos();
	}



}
