package wcaquino.test;
import static wcaquino.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import wcaquino.core.DSL;
import wcaquino.core.DriverFactory;

public class TesteFramesEJanelas {

	private DSL dsl;
	
	@Before
	public void inicializar() {
		getDriver().get("https://wcaquino.me/selenium/componentes.html");
		
		dsl = new DSL();
	}
	
	@Test
	public void interacaoFrame() {		
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);

		dsl.sairFrame();
		dsl.escrever("elementosForm:nome", msg);
	}	
	
	@Test
	public void interacaoJanelaComIdentificador() {		
		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		getDriver().close(); //Comando para fechar somente a popup. Se fosse o getDriver().quit() fecharia todas as instancias que o programa esta gerenciando
		
		dsl.trocarJanela("");
		dsl.escrever(By.tagName("textarea"), "e agora?");
	}
	
	@Test
	public void interacaoJanelaSemIdentificador() {
		dsl.clicarBotao("buttonPopUpHard");
		System.out.println(getDriver().getWindowHandle()); //Devolve o identificador da janela principal
		System.out.println(getDriver().getWindowHandles()); //Devolve um vetor com os identificadores de todas as janelas
		
		dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[1]); //Captura o identificador da segunda janela com o toArray 
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[0]);
		dsl.escrever(By.tagName("textarea"), "E agora?");
	}
	
	@After
	public void FecharInstancia() {
		DriverFactory.killDriver(); //Fecha todas as instâncias do Chrome abertas e gerenciadas pela automacao 
	}
}
