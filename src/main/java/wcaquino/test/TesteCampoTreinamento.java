package wcaquino.test;
import static wcaquino.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import wcaquino.core.DSL;
import wcaquino.core.DriverFactory;

public class TesteCampoTreinamento {

	private DSL dsl;
	
	@Before
	public void inicializar() {
		getDriver().get("https://wcaquino.me/selenium/componentes.html");
		
		dsl = new DSL();
	}

	@Test
	public void testeTextField() {
		dsl.escrever("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void testeTextArea() {
		dsl.escrever("elementosForm:sugestoes", "Teste area!");
		Assert.assertEquals("Teste area!", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void testeRadio() {
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}
	
	@Test
	public void testeCheckBox() {
		dsl.clicarCheck("elementosForm:comidaFavorita:1");
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:1"));
	}
	
	@Test
	public void testeComboBox() {
		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
		
		Assert.assertEquals("Superior", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void verificaValoresCombo() {
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));	
	}
	
	@Test
	public void verificaValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
				
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Futebol");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "Corrida")));
	}
	
	@Test
	public void interagirComBotao() {
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterTexto("value"));
	}
	
	@Test
	@Ignore  //<-- Na hora de executar os testes, esse será ignorado, por não ter sido concluido.
	public void interagirComLink() {
		dsl.clicarLink("Voltar");
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
				dsl.obterTexto(By.className("facilAchar")));
		
		//Assert.fail(); <-- Maneira de não deixar o teste passar sem fazer nada. Com isso vc sempre lembra que tem que termina-lo
	}
	
	@After
	public void FecharInstancia() {
		DriverFactory.killDriver();
	}	
}
