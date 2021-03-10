package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_RegisterTeacher extends PO_NavView {
	
	static private void fillForm(WebDriver driver, String dnip, String namep, String lastnamep, String categotiap) {
	
		WebElement dni = driver.findElement(By.name("dni"));
		
		dni.click();
		dni.clear();
		dni.sendKeys(dnip);
		
		WebElement name = driver.findElement(By.name("nombre"));
		name.click();
		name.clear();
		name.sendKeys(namep);
		
		WebElement lastname = driver.findElement(By.name("apellidos"));
		lastname.click();
		lastname.clear();
		lastname.sendKeys(lastnamep);

		WebElement password = driver.findElement(By.name("categoria"));
		password.click();
		password.clear();
		password.sendKeys(categotiap);
		
		// Alta
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	static public void register(WebDriver driver, String dni, String nombre, String apellido, String asignatura) {
		List<WebElement> elementos;
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'teachers-menu')]/a");
        elementos.get(0).click();
        elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'teacher/add')]");
        elementos.get(0).click();

        fillForm(driver, dni, nombre, apellido, asignatura);
	}
}