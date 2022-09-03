package projetoSpringData.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projetoSpringData.model.UsuarioSpringData;


@Repository // para fazer o serviço de persistence
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long> {  //extende o crud e passa a classe e o id 
	
	@Transactional(readOnly = true)
	@Query(value = "select u from UsuarioSpringData u where u.email='nathalia@gmail.com'")
	public List<UsuarioSpringData> buscaPorEmail (String email); // faz query específica retornando lista da classe com os parametros desejados
	
	@Lock(LockModeType.READ) //bloqueia enquanto estiver em uso
	@Transactional(readOnly = true) //define que o metodo nunca faça alteração, somente leitura
	@Query(value = "select u from UsuarioSpringData u where u.nome = :paramnome") // query específica por parametro recebido
	public UsuarioSpringData buscaPorNomeParam (@Param("paramnome") String paramnome);
	
	default <S extends UsuarioSpringData> S saveAtual(S entity) {
		// processa qualquer coisa, pode fazer rotina, sobrescreve o metodo
		return save(entity);
	}
	
	@Modifying // identifica que vai fazer uma modificação no banco
	@Transactional(rollbackFor = SQLException.class) // se lançar algum erro desfaz o delete 
	@Query(value = "delete from UsuarioSpringData u where u.nome = ?1")
	public void deletePorNome(String nome);
	
	@Modifying
	@Transactional //starta uma transação abre um processo
	@Query(value = "update UsuarioSpringData u set u.email = ?1 where u.nome = ?2 ")
	public void updateEmailPorNome(String email, String nome);
	
	



}



