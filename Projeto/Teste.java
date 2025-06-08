import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.time.Duration;

import org.openqa.selenium.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Teste {
    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebElement clicable;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://uspdigital.usp.br/jupiterweb/jupCarreira.jsp?codmnu=8275");

        Select institutos = new Select(driver.findElement(By.id("comboUnidade")));
        institutos.selectByVisibleText("Instituto Oceanográfico - ( IO )");
        
        
        Select curso = new Select(driver.findElement(By.id("comboCurso")));
        curso.selectByVisibleText("Bacharelado em Oceanografia - integral");

        clicable = driver.findElement(By.id("enviar"));
        clicable.click();

        clicable = driver.findElement(By.id("step4-tab"));
        clicable.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gradeCurricular")));
        WebElement infos = driver.findElement(By.id("step4"));
        //WebElement disciplinas = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gradeCurricular")));

        String html = infos.getAttribute("innerHTML");
        System.out.println(html);
        //String html = disciplinas.getAttribute("innerHTML");

        driver.quit();

        Document doc = Jsoup.parse(html);

        Elements divs = doc.select("table").select("tr").select("td").select("div");
        Elements tabelas = divs.select("table");

        System.out.println("Unidade: "+divs.get(0).select("span").text());
        System.out.println("Curso: "+divs.get(1).select("span").text());
        System.out.println("Ideal: "+divs.get(3).select("span").get(0).text());
        System.out.println("Min: "+divs.get(3).select("span").get(1).text());
        System.out.println("Max: "+divs.get(3).select("span").get(2).text());

        for (Element tabela : tabelas) {
            System.out.println("=== Nova Tabela ===");

            Elements linhas = tabela.select("tr");
            System.out.println(linhas.get(0).text());

            for (Element linha : linhas) {
                Elements colunas = linha.select("td, th");

                if(colunas.get(0).attr("colspan").equals("2") || colunas.get(0).attr("colspan").equals("8")){
                    continue;
                }else{
                    System.out.print(colunas.get(0).text() + " | ");
                    System.out.print(colunas.get(1).text() + " | ");
                    System.out.print(colunas.get(2).text() + " | ");
                    System.out.print(colunas.get(3).text() + " | ");
                    System.out.print(colunas.get(4).text() + " | ");
                    System.out.print(colunas.get(5).text() + " | ");
                    System.out.print(colunas.get(6).text() + " | ");
                    System.out.println(colunas.get(7).text() + " | ");
                }

                /*                int cont=0;
                for (Element coluna : colunas) {
                    if(coluna.attr("colspan").equals("2") || coluna.attr("colspan").equals("8") || cont > 4){
                        break;
                    }
                    
                    System.out.print(coluna.text() + " | ");
                    cont++;
                }

                System.out.println(); */
        
            }
        }
    } 
}