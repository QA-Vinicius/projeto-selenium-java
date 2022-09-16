package wcaquino.test;
import static wcaquino.core.DriverFactory.getDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import wcaquino.core.DSL;
import wcaquino.core.DriverFactory;

public class TesteSincronismo {
	
	private DSL dsl;

	@Before
	public void inicializar() {
		getDriver().get("https://wcaquino.me/selenium/componentes.html");
		
		dsl = new DSL();
	}
	
	@After 
	public void FecharInstancia() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void testeComEsperaFixa() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000);
		dsl.escrever("novoCampo", "Deu certo?");
	}
	
	@Test
	public void testeComEsperaImplicita() throws InterruptedException {
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //Pode ser usado no Before para aplicar a todos os steps
		dsl.clicarBotao("buttonDelay");		
		dsl.escrever("novoCampo", "Deu certo?");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //Pode ser usado no Before para aplicar a todos os steps
	}
	
	@Test
	public void testeComEsperaExplicita() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escrever("novoCampo", "Deu certo?");
	}
}
