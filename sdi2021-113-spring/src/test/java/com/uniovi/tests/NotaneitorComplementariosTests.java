package com.uniovi.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterTeacher;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorComplementariosTests {
	
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\ramon\\geckodriver024win64.exe";

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();

		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() throws Exception {
		driver.navigate().to(URL);
	}

	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {

	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}
	
	// PR01. Registro de profesores con datos válidos. 
	@Test
	public void PR01() {
		PO_PrivateView.login(driver, "99999988F", "123456", "99999988F");
        PO_RegisterTeacher.register(driver, "123456789A", "Ramón", "Vila", "Matemáticas");
	}

	// PR02. Registro de profesores con datos inválidos (nombre  y  categoría  inválidos). 
	@Test
	public void PR02() {
		PO_PrivateView.login(driver, "99999988F", "123456", "99999988F");
        PO_RegisterTeacher.register(driver, "123456789B", "Pepe", "Botella", "Física");
        
        // Comprobar que hay errores
        PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());
        PO_RegisterView.checkKey(driver, "Error.signup.categoria.length", PO_Properties.getSPANISH());
	}

	// PR03. Verificar que solo los usuarios autorizados pueden dar de alta un profesor
	@Test
	public void PR03() {
		PO_PrivateView.login(driver, "99999988F", "123456", "99999988F");
        PO_RegisterTeacher.register(driver, "123456789B", "Pepe", "Botella", "Física");
        
        List<WebElement> elementos;
     
        elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'teachers-menu')]/a");
        elementos.get(0).click();
        elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'teacher/add')]");
        
        assertTrue(elementos.size() == 0);
	}
}
