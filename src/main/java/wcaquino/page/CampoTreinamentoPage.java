package wcaquino.page;
import org.openqa.selenium.By;

import wcaquino.core.BasePage;

public class CampoTreinamentoPage extends BasePage {

	public void setNome(String nome) {
		dsl.escrever("elementosForm:nome", nome);		
	}
	
	public void setSobrenome(String sobrenome) {
		dsl.escrever("elementosForm:sobrenome", sobrenome);	
	}
	
	public void setSexoMasc() {
		dsl.clicarRadio("elementosForm:sexo:0");		
	}
	
	public void setSexoFem() {
		dsl.clicarRadio("elementosForm:sexo:1");		
	}
	
	public void setComidaCarne() {
		dsl.clicarCheck("elementosForm:comidaFavorita:0");		
	}
	
	public void setComidaVegetariana() {
		dsl.clicarCheck("elementosForm:comidaFavorita:3");		
	}
	
	public void setEscolaridade(String valor) {
		dsl.selecionarCombo("elementosForm:escolaridade", valor);	
	}
	
	public void setEsportes(String... valores) {
		for(String valor: valores)
			dsl.selecionarCombo("elementosForm:esportes", valor);
	}
	
	public void cadastrar() {
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	public String obterResultadoCad() {
		return dsl.obterTexto(By.xpath("//*[@id='resultado']/span"));
	}
	
	public String obterNomeCad() {
		return dsl.obterTexto(By.xpath("//*[@id='descNome']/span"));
	}
	
	public String obterSobrenomeCad() {
		return dsl.obterTexto(By.xpath("//*[@id='descSobrenome']/span"));
	}
	
	public String obterSexoCad() {
		return dsl.obterTexto(By.xpath("//*[@id='descSexo']/span"));
	}
	
	public String obterComidaCad() {
		return dsl.obterTexto(By.xpath("//*[@id='descComida']/span"));
	}
	
	public String obterEscolaridadeCad() {
		return dsl.obterTexto(By.xpath("//*[@id='descEscolaridade']/span"));
	}
	
	public String obterEsporteCad() {
		return dsl.obterTexto(By.xpath("//*[@id='descEsportes']/span"));
	}
}
