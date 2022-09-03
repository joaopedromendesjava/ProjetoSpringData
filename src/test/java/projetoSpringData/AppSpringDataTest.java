package projetoSpringData;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projetoSpringData.dao.InterfaceSpringDataUser;
import projetoSpringData.dao.InterfaceTelefone;
import projetoSpringData.model.Telefone;
import projetoSpringData.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class) // para poder integrar o spring junto com o junit
@ContextConfiguration(locations = {"classpath:META-INF/SpringConfig.xml"}) // lendo arquivo de config
public class AppSpringDataTest {
	
	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser; // permite usar a interface para o crud
	
	@Autowired 
	private InterfaceTelefone interfaceTelefone;
	
	@Test
	public void testeInsert() { // metodo de insert
		
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		
		usuarioSpringData.setEmail("cris@gmail.com");
		usuarioSpringData.setIdade(19);
		usuarioSpringData.setNome("cristiano ronaldo");					// seta os dados 
		usuarioSpringData.setSenha("123");
		usuarioSpringData.setLogin("joaomendes");
		
		interfaceSpringDataUser.save(usuarioSpringData); // objeto da interface salvando no banco
		
		System.out.println("Usuarios cadastrados --> " + interfaceSpringDataUser.count()); // imprime em console quantos usuarios tem cadastrados no sistema
		
	}
	
	@Test
	public void testeInsertVarios() { // metodo de inserindo varios dados
		
		for (int i = 0; i < 5; i++) {  // for para salvar uma quantidade de vezes determinada
		
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		
		usuarioSpringData.setEmail("joaopedromendessilva@gmail.com");
		usuarioSpringData.setIdade(20);
		usuarioSpringData.setNome("Pedro Mendes Silva");					// seta os dados 
		usuarioSpringData.setSenha("12345");
		usuarioSpringData.setLogin("joaomendes");
		 
			  interfaceSpringDataUser.save(usuarioSpringData); // objeto da interface salvando no banco
		    }
		
		System.out.println("Usuarios cadastrados --> " + interfaceSpringDataUser.count()); // imprime em console quantos usuarios tem cadastrados no sistema
	}
	
	
	@Test
	public void testeConsulta() { // metodo de consulta por id
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(5L); // pega o id para consultar cadastro
		
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getLogin());  		// buscando o dado setado de cada campo atraves do id
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getSenha());
		System.out.println(usuarioSpringData.get().getId());
		
		for (Telefone telefone: usuarioSpringData.get().getTelefones()) {
			System.out.println(telefone.getNumero());
			System.out.println(telefone.getTipo());
			System.out.println(telefone.getId());
			System.out.println(telefone.getUsuarioSpringData().getNome());
			
		}
	}
	
	@Test
	public void testeConsultaTodos() { // metodo para consultar todos
	
		Iterable<UsuarioSpringData> list = interfaceSpringDataUser.findAll(); // find all precisa de um iterable para retornar a lista de users
		
		for (UsuarioSpringData usuarioSpringData : list) {
			
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());			// pegando dados de cada cadastro percorrido pelo for
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getId());
			
			System.out.println("---------------------------------------Próximo User-------------------------------");
			
		}
	}
	
	@Test
	public void testeUpdate() { // metodo de atualizar
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(4L); //busca id que quer atualizar 
		
		UsuarioSpringData data = usuarioSpringData.get(); // busca os objetos
		
		data.setNome("Nathalia Sarah Ribeiro Santos"); // seta o novo nome
		data.setIdade(24);
		
		interfaceSpringDataUser.save(data); //salva
	}
	
	@Test
	public void testeDelete() { //metodo de delete 
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L);
			
		
		System.out.println("Sera deletado o usuario de nome " + 
				usuarioSpringData.get().getNome() + " e de ID = " + 
					usuarioSpringData.get().getId()); // aviso o  usuario que será deletado 
		
		interfaceSpringDataUser.delete(usuarioSpringData.get()); // deleto o usuario
		
	}
	
	@Test
	public void testeConsultaEmail() { // metodo de consulta por email
		
		List<UsuarioSpringData> list = interfaceSpringDataUser.buscaPorEmail(""); // retorna uma lista e pega a query especifica criada, passando os valores
			
				for (UsuarioSpringData usuarioSpringData : list) { // varre a lista e imprime em console
					
					System.out.println(usuarioSpringData.getEmail());
					System.out.println(usuarioSpringData.getIdade());			// pegando dados de cada cadastro percorrido pelo for
					System.out.println(usuarioSpringData.getLogin());
					System.out.println(usuarioSpringData.getNome());
					System.out.println(usuarioSpringData.getSenha());
					System.out.println(usuarioSpringData.getId());
					
					System.out.println("---------------------------------------Próximo User-------------------------------");
					
		}
	}
	@Test
	public void testeConsultaNomeParam() { //metodo de consulta por nome passando parametro
			
			UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParam("Nathalia");// busca esse nome, só aceita retornar somente um dado
			
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());			// pegando dados de cada cadastro percorrido pelo for
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getId());
			
			System.out.println("---------------------------------------Próximo User-------------------------------");
			
	}
	
	@Test
	public void testeDeletePorNome() { //metodo de delete por nome condicional
		
		interfaceSpringDataUser.deletePorNome("cristiano ronaldo");
	}
	
	@Test
	public void testeUpdateEmailPorNome() { // metodo de atualizar com 2 param
		
		interfaceSpringDataUser.updateEmailPorNome("joaopedromendessilva@gmail.com", "cristiano ronaldo");
		
	}
	
	@Test
	public void testeInsertTelefone() { // metodo de insert de telefone
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(5L); // busca usuario no qual vai setar telefone
		
		Telefone telefone = new Telefone();
		telefone.setTipo("casa");
		telefone.setNumero("3133120738");
		telefone.setUsuarioSpringData(usuarioSpringData.get());
		
		interfaceTelefone.save(telefone);
	}
	
	
	
}


