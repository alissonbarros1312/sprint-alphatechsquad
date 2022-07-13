package page;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import navegadores.DriverFactory;

public class FormularioPage extends DriverFactory {
	
	public void selectModalidade(String modal) {
		
		if(modal.equalsIgnoreCase("compra")) {
			driver.findElement(By.xpath("//button[@class='btn-360 btn-360-primary active']")).click();
		} else if(modal.equalsIgnoreCase("alugar")) {
			driver.findElement(By.xpath("//button[@class='btn-360 btn-360-default']")).click();
		}
	}
	
	private void preencher(String nome, String sobrenome, String email, String telefone, String cidade, String bairro, String valor) {
		driver.findElement(By.xpath("//input[@name='nome']")).sendKeys(nome);
		driver.findElement(By.xpath("//input[@name='segundo_nome']")).sendKeys(sobrenome);
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='celular_whatsapp']")).sendKeys(telefone);
		driver.findElement(By.xpath("//input[@name='cidade']")).sendKeys(cidade);
		driver.findElement(By.xpath("//input[@name='bairro']")).sendKeys(bairro);
		driver.findElement(By.xpath("//input[@name='valor_aproximado']")).sendKeys(valor);
	}
	
	public void PreencherForm(String tipo) {
		
		if(tipo.equalsIgnoreCase("correto")) {
			
			preencher("jose", "silva", "jo@silva.com", "99999999999", "Sao Paulo", "guarulhos", "120000");
			driver.findElement(By.id("tipo_do_imovel")).click();
			driver.findElement(By.xpath("//option[@value='Residencial'] ")).click();
			driver.findElement(By.id("qtd_de_quartos")).click();
			driver.findElement(By.xpath("//*[@id=\"qtd_de_quartos\"]/option[2]")).click();
			driver.findElement(By.xpath("//div[@class='checkmark']")).click();
			
		} else if(tipo.equalsIgnoreCase("incorreto")) {
			
			preencher("1234", "&*(*", "josilva.com", "99999999999999", "87685657", "teste", "hagdjfhga");
			driver.findElement(By.id("tipo_do_imovel")).click();
			driver.findElement(By.xpath("//option[@value='Residencial'] ")).click();
			driver.findElement(By.id("qtd_de_quartos")).click();
			driver.findElement(By.xpath("//*[@id=\"qtd_de_quartos\"]/option[2]")).click();
			driver.findElement(By.xpath("//div[@class='checkmark']")).click();
			
		}
		
		
	}

	public void enviar() {
		driver.findElement(By.id("encontreMeuImovel_submit-action-button")).click();
	}
	
	public void validar(String tipo) {
		String textExpected = "https://visaoimoveisindaiatuba.com.br/obrigado/?target=encontre-meu-imovel";
		
		if(tipo.equalsIgnoreCase("correto")) {
			assertTrue(textExpected.contains(driver.getTitle()));
		} else if (tipo.equalsIgnoreCase("incorreto")) {
			assertFalse(textExpected.contains(driver.getTitle()));
		} else if(tipo.equalsIgnoreCase("vazio")) {
			assertFalse(textExpected.contains(driver.getTitle()));
		}
		
	}
	public void scrShot(String name) {
		TakesScreenshot scrShot = (TakesScreenshot) driver;
		File scrFile = scrShot.getScreenshotAs(OutputType.FILE);
		File scrDest = new File("./evidences/"+name+".png");
		try {
			FileUtils.copyFile(scrFile, scrDest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
