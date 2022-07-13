package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.FormularioPage;
import runner.Run;

public class EncontreSeuImovel {
	Run run = new Run();
	FormularioPage form = new FormularioPage();

	@Given("que eu esteja na {string}")
	public void que_eu_esteja_na(String url) {
	    run.openBrowser(url);
	}

	@When("eu clicar enviar")
	public void eu_clicar_enviar() {
	    form.enviar();
	}

	@Given("preencher os dados invalidos")
	public void preencher_os_dados_invalidos() {
	    form.PreencherForm("incorreto");
	}

	@Then("validar mensagem de erro")
	public void validar_mensagem_de_erro() {
		form.scrShot("CT01 - Campos invalidos");
	    form.validar("incorreto");
	    run.closeBrowser();
	}

	@Then("validar mensagem de campo vazio")
	public void validar_mensagem_de_campo_vazio() {
	    form.validar("vazio");
	    form.scrShot("CT02 - Campo vazio");
	    run.closeBrowser();
	}

}
