import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import org.openqa.selenium.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Teste {
    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebElement clicable;

        driver.get("https://uspdigital.usp.br/jupiterweb/jupCarreira.jsp?codmnu=8275");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        Select institutos = new Select(driver.findElement(By.id("comboUnidade")));
        institutos.selectByVisibleText("Instituto de Ciências Matemáticas e de Computação - ( ICMC )");

        Select curso = new Select(driver.findElement(By.id("comboCurso")));
        curso.selectByVisibleText("Bacharelado em Ciência de Dados - integral");

        clicable = driver.findElement(By.id("enviar"));
        clicable.click();

        clicable = driver.findElement(By.id("step4-tab"));
        clicable.click();

        String html = driver.getPageSource();

        System.out.println(html);

        driver.quit();
    }
}
