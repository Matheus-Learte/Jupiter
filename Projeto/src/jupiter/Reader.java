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
import org.openqa.selenium.TimeoutException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.time.Duration;

public class Reader {
    @SuppressWarnings("deprecation")
    public static USP readWeb(int total){
        USP aux = new USP();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
        driver.get("https://uspdigital.usp.br/jupiterweb/jupCarreira.jsp?codmnu=8275");
    
        Select institutos = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("comboUnidade"))));
        List<WebElement> options = institutos.getOptions();
        options.remove(0);
        
        System.out.print("\033[H\033[2J");
        System.out.flush();

        for (WebElement option : options) {
            if(total==0){
                break;
            }

            System.out.print("Coletando os dados. Espere...");

            String nome_inst = option.getText();
            institutos.selectByVisibleText(nome_inst);
            aux.setInstituto(nome_inst);
    
            wait.until(driver1 -> {
                Select cursosTemp = new Select(driver1.findElement(By.id("comboCurso")));
                return cursosTemp.getOptions().size() > 1;
            });
    
            Select cursos = new Select(driver.findElement(By.id("comboCurso")));
            List<WebElement> opcoes = cursos.getOptions();
            opcoes.remove(0);
    
            for (WebElement opcao : opcoes) {
                cursos.selectByVisibleText(opcao.getText());
    
                WebElement clicable = wait.until(ExpectedConditions.elementToBeClickable(By.id("enviar")));
                clicable.click();
    
                try {
                    WebDriverWait waitPopup = new WebDriverWait(driver, Duration.ofMillis(500));
                    WebElement popup = waitPopup.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.//span[text()='Fechar']]")));
                    popup.click();
                    continue;
                } catch (TimeoutException e) {
                    // Nenhum popup, segue normal
                }
    
                clicable = wait.until(ExpectedConditions.elementToBeClickable(By.id("step4-tab")));
                clicable.click();
    
                WebElement infos = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("step4")));
                

                clicable = driver.findElement(By.id("step1-tab"));
                try {
                    clicable.click();
                } catch (Exception e) {
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("blockUI")));
                    clicable.click();
                }
    
                parcionamento(infos.getAttribute("innerHTML"), aux.getIntituto(nome_inst));

            }
            total--;
            
            System.out.print("\033[H\033[2J");
            System.out.flush();
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