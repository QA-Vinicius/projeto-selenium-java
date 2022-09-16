package wcaquino.test;
import static wcaquino.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import wcaquino.core.DSL;
import wcaquino.core.DriverFactory;


public class TesteAlert {

	private DSL dsl;
	
	@Before
	public void inicializar() {
		getDriver().get("https://wcaquino.me/selenium/componentes.html");
		
		dsl = new DSL();
	}
	
	@Test
	public void interacaoAlertSimples() {
		dsl.clicarBotao("alert");
		String texto = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Alert Simples", texto);
		dsl.escrever("elementosForm:nome", texto);
	}
	
	@Test
	public void interacaoAlertConfirm() {		
		//Teste confirmar
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());
		
		//Teste negar
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
		Assert.assertEquals("Negado", dsl.alertaObterTextoENega());
		
	}
	
	@Test
	public void interacaoPrompt() {
		dsl.clicarBotao("prompt");
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		dsl.alertaEscrever("12");
		Assert.assertEquals("Era 12?",  dsl.alertaObterTextoEAceita());
		Assert.assertEquals(":D",  dsl.alertaObterTextoEAceita());
	}
	
	@After
	public void FecharInstancia() {
		DriverFactory.killDriver();
	}
}
