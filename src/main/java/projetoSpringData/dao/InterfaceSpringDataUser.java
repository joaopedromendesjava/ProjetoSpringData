package projetoSpringData.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import projetoSpringData.model.UsuarioSpringData;

@Repository // para fazer o serviço de persistence
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long> {  //extende o crud e passa a classe e o id 

		
}


