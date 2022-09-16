package wcaquino.test;
import static wcaquino.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import wcaquino.core.BaseTest;
import wcaquino.page.CampoTreinamentoPage;

public class TesteCadastro extends BaseTest {
	
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializar() {
		getDriver().get("https://wcaquino.me/selenium/componentes.html");
		page = new CampoTreinamentoPage();
	}

	@Test
	public void realizarCadastroSucesso () {
		page.setNome("Vinícius");
		page.setSobrenome("Alves");
		page.setSexoMasc();
		page.setComidaCarne();
		page.setEscolaridade("Superior");
		page.setEsportes("Futebol");
		page.cadastrar();
		
		Assert.assertEquals("Cadastrado!", page.obterResultadoCad());
		Assert.assertEquals("Vinícius", page.obterNomeCad());
		Assert.assertEquals("Alves", page.obterSobrenomeCad());
		Assert.assertEquals("Masculino", page.obterSexoCad());
		Assert.assertEquals("Carne", page.obterComidaCad());
		Assert.assertEquals("superior", page.obterEscolaridadeCad());
		Assert.assertEquals("Futebol", page.obterEsporteCad());
	}	
}
