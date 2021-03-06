package com.uniovi.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.runners.MethodSorters;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.*;
import com.uniovi.tests.util.SeleniumUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorTests {

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

	// PR01. Acceder a la página principal /
	@Test
	public void PR01() {
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
	}

	// PR02. OPción de navegación. Pinchar en el enlace Registro en la página home
	@Test
	public void PR02() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
	}

	// PR03. OPción de navegación. Pinchar en el enlace Identificate en la página
	// home
	@Test
	public void PR03() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	}

	// PR04. OPción de navegación. Cambio de idioma de Español a Ingles y vuelta a
	// Español
	@Test
	public void PR04() {
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());
		// SeleniumUtils.esperarSegundos(driver, 2);
	}

	// PR05. Prueba del formulario de registro. registro con datos correctos
	@Test
	public void PR05() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "77777778A", "Josefo", "Perez", "77777", "77777");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}

	// PR06. Prueba del formulario de registro. DNI repetido en la BD, Nombre corto,
	// .... pagination pagination-centered,Error.signup.dni.length
	@Test
	public void PR06() {

		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");

		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "99999990A", "Josefo", "Perez", "77777", "77777");
		PO_View.getP();

		// COmprobamos el error de DNI repetido.
		PO_RegisterView.checkKey(driver, "Error.signup.dni.duplicate", PO_Properties.getSPANISH());

		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "99999990B", "Jose", "Perez", "77777", "77777");

		// COmprobamos el error de Nombre corto .
		PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());

		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "99999990B", "Josefo", "Per", "77777", "77777");
	}

	// PRN. Loguearse con exito desde el ROl de Usuario, 99999990D, 123456
	@Test
	public void PR07() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999990A", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}

	// PR08 Acceder de manera válida con rol de profesor
	@Test
	public void PR08() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999993D", "123456");
		// Comprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "modificar");
	}

	// PR09 Acceder con credenciales válidas de administrador
	@Test
	public void PR09() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "99999988F", "123456");
		PO_View.checkElement(driver, "text", "Gestión de Usuarios");
	}

	// PR10 Acceder con credenciales inválidas de alumno (??)
	@Test
	public void PR10() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "99999990A", "123456");
		PO_View.checkElement(driver, "text", "Bienvenidos a la pagina principal");
	}

	// PR11 Acceder con credenciales válidas de usuario normal y hacer logout
	@Test
	public void PR11() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "99999990A", "123456");
		PO_View.checkElement(driver, "text", "Esta es una zona privada la web");
		PO_HomeView.clickOption(driver, "logout", "text", "Identifícate");
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	// PR12. Loguearse, comprobar que se visualizan 4 filas de notas y desconectarse
	// usando el rol de

	@Test
	public void PR12() {
		// Login
		PO_PrivateView.login(driver, "99999990A", "123456", "Notas del usuario");
		
		// Contamos el número de filas de notas
		List<WebElement> elementos = 
				SeleniumUtils.EsperaCargaPagina( driver, "free", "//tbody/tr", PO_View.getTimeout() );
		assertTrue(elementos.size() == 4);
		
		PO_PrivateView.logout(driver);
	}

	// PR13. Loguearse como estudiante y ver los detalles de la nota con Descripcion
	// = Nota A2.
	// P13. Ver la lista de Notas.
	@Test
	public void PR13() {
		
		// Vamos al formulario de logueo.
		PO_PrivateView.login(driver, "99999990A", "123456", "Notas del usuario");
		
		SeleniumUtils.esperarSegundos(driver, 1);
		
		// Contamos las notas
		By enlace = By.xpath("//td[contains(text(), 'Nota A2')]/followingsibling::*[2]");
		driver.findElement(enlace).click();
		SeleniumUtils.esperarSegundos(driver, 1);
		
		// Esperamos por la ventana de detalle
		PO_View.checkElement(driver, "text", "Detalles de la nota");
		SeleniumUtils.esperarSegundos(driver, 1);
		
		PO_PrivateView.logout(driver);
	}

	// P14. Loguearse como profesor y Agregar Nota A2.
	@Test
	public void PR14() {
		
		// Login
		PO_PrivateView.login(driver, "99999993D", "123456", "99999993D");
		
		
		List<WebElement> elementos = PO_PrivateView.openMarksMenu(driver, "mark/add");
		
		// Ahora vamos a rellenar la nota. //option[contains(@value, '4')]
		PO_PrivateView.fillFormAddMark(driver, 3, "Nota Nueva 1", "8");
		
		PO_PrivateView.goToLastPage(driver, "page-link");
		
		// Comprobamos que aparece la nota en la pagina
		elementos = PO_View.checkElement(driver, "text", "Nota Nueva 1");
		
		PO_PrivateView.logout(driver);
	}

	// PRN. Loguearse como profesor, vamos a la ultima página y Eliminamos la Nota
	@Test
	public void PR15() {
		
		// Login
		PO_PrivateView.login(driver, "99999993D", "123456", "99999993D");
		
		List<WebElement> elementos = PO_PrivateView.openMarksMenu(driver, "mark/list");
		
		// Esperamos a que se muestren los enlaces de paginacion la lista de notas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'pagelink')]");
		
		// Nos vamos a la última página
		elementos.get(3).click();
		
		// Esperamos a que aparezca la Nueva nota en la ultima pagina
		// Y Pinchamos en el enlace de borrado de la Nota "Nota Nueva 1"
		// td[contains(text(), 'Nota Nueva 1')]/following-sibling::*/a[contains(text(), 'mark/delete')]"
		elementos = PO_View.checkElement (
				driver, "free", "//td[contains(text(), 'Nota Nueva 1')]/following-sibling::*/a[contains(@href, 'mark/delete')]");
		elementos.get(0).click();
		
		PO_PrivateView.goToLastPage(driver, "pagelink");
		
		// Y esperamos a que NO aparezca la ultima "Nueva Nota 1"
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Nota Nueva 1", PO_View.getTimeout());
		
		PO_PrivateView.logout(driver);
	}
}