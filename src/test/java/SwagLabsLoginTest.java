import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SwagLabsLoginTest {

    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    private static final String URL_INVENTORY = "https://www.saucedemo.com/inventory.html";

    @Test
    public void LoginComSucesso(){

        // define o caminho do driver
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        // inicia o browser
        WebDriver driver = new ChromeDriver();

        // navega até a URL especificada
        driver.get(URL_LOGIN);
        // localiza os elementos
        WebElement campoUsername = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement campoPassword = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement botaoLogin = driver.findElement(By.xpath("//input[@id='login-button']"));

        // preenche os campos
        campoUsername.sendKeys("standard_user");
        campoPassword.sendKeys("secret_sauce");

        // clica
        botaoLogin.click();

        // localiza o elemento
        WebElement textoProducts = driver.findElement(By.xpath("//span[.='Products']"));


        // valida se o elemento está sendo exibido
        Assert.assertTrue(textoProducts.isDisplayed());
        // valida se a URL é diferente
        Assert.assertNotEquals(URL_LOGIN, driver.getCurrentUrl());

        // fecha o browser
        driver.quit();
    }

    @Test
    public void LoginSemSucesso(){

        //define o caminho do driver
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        // inicia o browser
        WebDriver driver = new ChromeDriver();

        // navega até a URL especificada
        driver.get(URL_LOGIN);
        // localiza os elementos
        WebElement campoUsername = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement campoPassword = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement botaoLogin = driver.findElement(By.xpath("//input[@id='login-button']"));

        // preenche os campos
        campoUsername.sendKeys("usuario_invalido");
        campoPassword.sendKeys("senha_incorreta");
        // clica
        botaoLogin.click();

        // valida se a URL é igual
        Assert.assertEquals(URL_LOGIN, driver.getCurrentUrl());
        // localiza o elemento
        WebElement mensagemErro = driver.findElement(By.xpath("//input[@id='login-button']"));
        // valida se o elemento está sendo exibido
        Assert.assertTrue(mensagemErro.isDisplayed());

        // fecha o browser
        driver.quit();

    }

    @Test
    public void LogoutComSucesso() {

        //define o caminho do driver
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        // inicia o browser
        WebDriver driver = new ChromeDriver();

        // navega até a URL especificada
        driver.get(URL_LOGIN);
        // localiza os elementos
        WebElement campoUsername = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement campoPassword = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement botaoLogin = driver.findElement(By.xpath("//input[@id='login-button']"));

        // preenche os campos
        campoUsername.sendKeys("standard_user");
        campoPassword.sendKeys("secret_sauce");
        // clica
        botaoLogin.click();

        // localiza o elemento
        WebElement menu = driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']"));
        // clica
        menu.click();

        // localiza o elemento
        WebElement opcaoLogout = driver.findElement(By.xpath("//a[@id='logout_sidebar_link']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(opcaoLogout));
        // clica
        opcaoLogout.click();

        // valida se a URL é igual
        Assert.assertEquals(URL_LOGIN, driver.getCurrentUrl());

        // fecha o browser
        driver.quit();
    }

    @Test
    public void AcessarUrlRestritaSemLogar() {

        //define o caminho do driver
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        // inicia o browser
        WebDriver driver = new ChromeDriver();

        // navega até a URL especificada
        driver.get(URL_INVENTORY);
        // valida se a URL é igual
        Assert.assertEquals(URL_LOGIN, driver.getCurrentUrl());

        // fecha o browser
        driver.quit();

    }

}