package jupiter;

import java.util.List;
import java.util.ArrayList;

public class Disciplina{
    private String codigo, nome, teorico;
    private int creditos_aula, creditos_trabalho, carga, carga_estagio, carga_curriculares;
    private List<String> cursos = new ArrayList<>();

    Disciplina(String codigo, String nome, String teorico, int cred_aula, int cred_work, int carga, int carga_est, int carga_curr, String curso) {
        this.codigo = codigo;
        this.nome = nome;
        this.teorico = teorico;
        this.creditos_aula = cred_aula;
        this.creditos_trabalho = cred_work;
        this.carga = carga;
        this.carga_estagio = carga_est;
        this.carga_curriculares = carga_curr;
        
        if (!busca(curso)) {
            this.cursos.add(curso);
        }
    }

    private boolean busca(String nome){
        for(String i : this.cursos){
            if(nome.equals(i)){
                return true;
            }
        }
    
        return false;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTeorico() {
        return this.teorico;
    }

    public void setTeorico(String teorico) {
        this.teorico = teorico;
    }

    public int getCreditos_aula() {
        return this.creditos_aula;
    }

    public void setCreditos_aula(int creditos_aula) {
        this.creditos_aula = creditos_aula;
    }

    public int getCreditos_trabalho() {
        return this.creditos_trabalho;
    }

    public void setCreditos_trabalho(int creditos_trabalho) {
        this.creditos_trabalho = creditos_trabalho;
    }

    public int getCarga() {
        return this.carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

    public int getCarga_estagio() {
        return this.carga_estagio;
    }

    public void setCarga_estagio(int carga_estagio) {
        this.carga_estagio = carga_estagio;
    }

    public int getCarga_curriculares() {
        return this.carga_curriculares;
    }

    public void setCarga_curriculares(int carga_curriculares) {
        this.carga_curriculares = carga_curriculares;
    }

    public List<String> getCursos(int i){
        return this.cursos;
    }

    @Override
    public String toString(){
        String aux="";

        return aux;
    }
}
