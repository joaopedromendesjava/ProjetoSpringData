package projetoSpringData.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import projetoSpringData.model.Telefone;

@Repository
public interface InterfaceTelefone extends CrudRepository<Telefone, Long> {
	
	

}
