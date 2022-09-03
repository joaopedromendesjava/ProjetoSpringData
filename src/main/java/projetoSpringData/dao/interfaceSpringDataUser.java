package projetoSpringData.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import projetoSpringData.model.usuarioSpringData;

@Repository // para fazer o serviço de persistence
public interface interfaceSpringDataUser extends CrudRepository<usuarioSpringData, Long> {  //extende o crud e passa a classe e o id 

		
}


