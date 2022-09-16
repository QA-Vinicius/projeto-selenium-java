package wcaquino.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {
	
	private WebDriver driver;
	
	@Before
	public void inicializar() {
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().setSize(new Dimension(1200, 765));
	}
	
	@Test
	public void teste() {		
//		System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver.exe"); --> Mapeando para execução no Mozilla
//		WebDriver driver = new FirefoxDriver(); --> Instanciando driver para o Mozilla
		
		driver.manage().window().setSize(new Dimension(1200,765));
		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
	}
	
	@After
	public void FecharInstancia() {
		driver.quit();
	}
}
