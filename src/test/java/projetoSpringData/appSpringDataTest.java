package projetoSpringData;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projetoSpringData.dao.InterfaceSpringDataUser;
import projetoSpringData.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class) // para poder integrar o spring junto com o junit
@ContextConfiguration(locations = {"classpath:META-INF/SpringConfig.xml"}) // lendo arquivo de config
public class appSpringDataTest {
	
	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser; // permite usar a interface para o crud
	
	@Test
	public void testeInsert() { // metodo de insert
		
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		
		usuarioSpringData.setEmail("joaopedromendessilva@gmail.com");
		usuarioSpringData.setIdade(19);
		usuarioSpringData.setNome("Nathalia Sarah Ribeiro Santos");					// seta os dados 
		usuarioSpringData.setSenha("123");
		usuarioSpringData.setLogin("joaomendes");
		
		interfaceSpringDataUser.save(usuarioSpringData); // objeto da interface salvando no banco
		
		System.out.println("Usuarios cadastrados --> " + interfaceSpringDataUser.count()); // imprime em console quantos usuarios tem cadastrados no sistema
		
	}
	@Test
	public void testeConsulta() { // metodo de consulta
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L); // pega o id para consultar cadastro
		
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getLogin());  		// buscando o dado setado de cada campo atraves do id
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getSenha());
		
	}
	

}


