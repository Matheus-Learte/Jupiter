package jupiter;

import java.util.List;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.time.Duration;

public class Reader {
    public static USP readWeb(){
        USP aux = new USP();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebElement clicable;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int i=0;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://uspdigital.usp.br/jupiterweb/jupCarreira.jsp?codmnu=8275");

        Select institutos = new Select(driver.findElement(By.id("comboUnidade")));
        List<WebElement> options = institutos.getOptions();

        for(WebElement option : options){
            String nome_inst = option.getText();
            institutos.selectByVisibleText(nome_inst);
            aux.setInstituto(nome_inst);

            Select cursos = new Select(driver.findElement(By.id("comboCurso")));
            List<WebElement> opcoes = cursos.getOptions();

            for(WebElement opcao : opcoes){
                cursos.selectByVisibleText(opcao.getText());

                clicable = driver.findElement(By.id("enviar"));
                clicable.click();

                clicable = driver.findElement(By.id("step4-tab"));
                clicable.click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gradeCurricular")));
                WebElement infos = driver.findElement(By.id("step4"));
                
                clicable = driver.findElement(By.id("step1-tab"));
                clicable.click();

                parcionamento(infos.getAttribute("innerHTML"), aux.getIntitutos(i));

            }
            i++;
        }
        driver.quit();
        return aux;
    }

    private static void parcionamento(String html, Instituto instituto){
        Document doc = Jsoup.parse(html);
        Elements divs = doc.select("table").select("tr").select("td").select("div");
        Elements tabelas = divs.select("table");
        char flag;
        String curso_nome = divs.get(1).select("span").text();
        Element div_duracao = divs.get(3);

        instituto.setCurso(curso_nome, divs.get(0).select("span").text(), Integer.parseInt(div_duracao.select("span").get(0).text()), Integer.parseInt(div_duracao.select("span").get(1).text()), Integer.parseInt(div_duracao.select("span").get(2).text()));
        Curso course = instituto.getCurso(curso_nome);

        for(Element tabela : tabelas){
            Elements linhas = tabela.select("tr");
            String disciplina_type = linhas.get(0).text();

            if(disciplina_type.equals("Disciplinas Obrigatórias")){
                flag = 'O';
            }else if(disciplina_type.equals("Disciplinas Optativas Livres")){
                flag = 'L';
            }else{
                flag = 'E';
            }

            for(Element linha : linhas){
                Elements colunas = linha.select("td, th");

                if(colunas.get(0).attr("colspan").equals("2") || colunas.get(0).attr("colspan").equals("8")){
                    continue;
                }else{
                    int cred_aula= -1;
                    int cred_trab = -1;
                    int carga = -1;
                    int carga_estagio = -1;
                    int carga_curr = -1;
                    int carga_pratica = -1;

                    String aux = colunas.get(2).text();
                    if(aux.length()>0) cred_aula = Integer.parseInt(aux);
                    
                    aux = colunas.get(2).text();
                    if(aux.length()>0) cred_aula = Integer.parseInt(aux);

                    aux = colunas.get(2).text();
                    if(aux.length()>0) cred_aula = Integer.parseInt(aux);

                    aux = colunas.get(2).text();
                    if(aux.length()>0) cred_aula = Integer.parseInt(aux);

                    aux = colunas.get(2).text();
                    if(aux.length()>0) cred_aula = Integer.parseInt(aux);

                    aux = colunas.get(2).text();
                    if(aux.length()>0) cred_aula = Integer.parseInt(aux);

                    course.setDisciplina(flag, colunas.get(0).text(), colunas.get(1).text(), cred_aula, cred_trab, carga, carga_estagio, carga_curr, carga_pratica);
                }
            }
        }
    }
}
