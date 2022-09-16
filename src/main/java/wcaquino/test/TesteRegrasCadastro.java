package wcaquino.test;
import static wcaquino.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import wcaquino.core.BaseTest;
import wcaquino.core.DSL;
import wcaquino.page.CampoTreinamentoPage;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro extends BaseTest {
	
	public DSL dsl;
	public CampoTreinamentoPage page;
	
	@Parameter(value=0)
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String[] esportes;
	@Parameter(value=5)
	public String msg;
	
	@Before
	public void inicializar() {
		getDriver().get("https://wcaquino.me/selenium/componentes.html");
		
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}

	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][]{
			{"","","", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
			{"Vinícius","","", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
			{"Vinícius","Alves","", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
			{"Vinícius","Alves","Masculino", Arrays.asList("Carne", "Vegetariana"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
			{"Vinícius","Alves","Masculino", Arrays.asList("Carne"), new String[]{"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"},
		});
	}
	
	@Test
	public void validaRegras () throws InterruptedException { //Usuario não pode marcar um esporte e em seguida a opção O que eh um esporte		
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		if(sexo.equals("Masculino")) {
			page.setSexoMasc();
		} 
		if(sexo.equals("Feminino")) {
			page.setSexoFem();
		}	
		if(comidas.contains("Carne")) page.setComidaCarne();
		if(comidas.contains("Vegetariana")) page.setComidaVegetariana();
		page.setEsportes(esportes);
		page.cadastrar();

		System.out.println(msg);
		Assert.assertEquals(msg, dsl.alertaObterTextoEAceita());
	} 
}
