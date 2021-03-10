package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {
	
	static public void fillFormAddMark( WebDriver driver, int userOrder, String descriptionp, String scorep ) {
		
		// Esperamos 5 segundos a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		
		// Seleccionamos el alumno userOrder
		new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
		
		// Rellenamos el campo de descripción
		WebElement description = driver.findElement(By.name("description"));
		
		description.clear();
		description.sendKeys(descriptionp);
		
		WebElement score = driver.findElement(By.name("score"));
		
		score.click();
		score.clear();
		score.sendKeys(scorep);
		
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	static public void login (WebDriver driver, String username, String password, String textCondition) {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, username, password);
		
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", textCondition);
	}
	
	static public List<WebElement> openMarksMenu(WebDriver driver, String hrefCondition) {
		// Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'marks-menu')]/a");
		elementos.get(0).click();
		
		// Pinchamos en la opción de lista de notas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'" + hrefCondition + "')]");
		elementos.get(0).click();
		
		return elementos;
	}
	
	static public void logout(WebDriver driver) {
		// Ahora nos desconectamos
		clickOption(driver, "logout", "text", "Identifícate");
	}
	
	static public List<WebElement> goToLastPage(WebDriver driver, String hrefClassName) {
		List<WebElement> elementos;
		
		// Volvemos a la última pagina
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, "+ hrefClassName +")]");
		elementos.get(3).click();
		
		return elementos;
	}
}
