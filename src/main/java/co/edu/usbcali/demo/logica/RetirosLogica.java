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

import co.edu.usbcali.demo.dao.IRetiroDAO;
import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;

@Service
@Scope("singleton")
public class RetirosLogica implements IRetirosLogica {
	
	private Logger log=LoggerFactory.getLogger(RetirosLogica.class);
	
	@Autowired
	private IRetiroDAO retirosDAO;
	
	@Autowired
	private Validator validator;

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void grabar(Retiros retiros) throws Exception {

		StringBuilder stringBuilder=new StringBuilder();
		
		Set<ConstraintViolation<Retiros>> constraintViolations=validator.validate(retiros);
		if(constraintViolations.size()>0){
			for (ConstraintViolation<Retiros> constraintViolation : constraintViolations) {
				log.error(constraintViolation.getPropertyPath().toString());
				log.error(constraintViolation.getMessage());
				stringBuilder.append(constraintViolation.getPropertyPath().toString());
				stringBuilder.append("-");
				stringBuilder.append(constraintViolation.getMessage());
				stringBuilder.append(",");
			}
			throw new Exception(stringBuilder.toString());
		}
		
		retirosDAO.grabar(retiros);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificar(Retiros retiros) throws Exception {

		StringBuilder stringBuilder=new StringBuilder();
		
		Set<ConstraintViolation<Retiros>> constraintViolations=validator.validate(retiros);
		if(constraintViolations.size()>0){
			for (ConstraintViolation<Retiros> constraintViolation : constraintViolations) {
				log.error(constraintViolation.getPropertyPath().toString());
				log.error(constraintViolation.getMessage());
				stringBuilder.append(constraintViolation.getPropertyPath().toString());
				stringBuilder.append("-");
				stringBuilder.append(constraintViolation.getMessage());
				stringBuilder.append(",");
			}
			throw new Exception(stringBuilder.toString());
		}
		
		retirosDAO.modificar(retiros);

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void borrar(Retiros retiros) throws Exception {

		Retiros retirosId = retirosDAO.consultarRetiros(retiros.getId());
		if(retirosId==null){
			throw new Exception("El retiro que desea eliminar no existe");
		}
		
		retirosDAO.borrar(retirosId);

	}

	@Override
	@Transactional(readOnly=true)
	public Retiros consultarRetiros(RetirosId Id) throws Exception {
		return retirosDAO.consultarRetiros(Id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Retiros> consultarTodos() throws Exception {		
		return retirosDAO.consultarTodos();
	}


}
