package jupiter;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    String nome, unidade;
    int ideal, min, max;
    List<Disciplina> obrigatorias, livres, eletivas;

    Curso(String nome, String unidade, int ideal, int min, int max) {
        this.nome = nome;
        this.unidade = unidade;
        this.ideal = ideal;
        this.min = min;
        this.max = max;

        this.obrigatorias = new ArrayList<>();
        this.livres = new ArrayList<>();
        this.eletivas = new ArrayList<>();
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return this.unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public int getIdeal() {
        return this.ideal;
    }

    public void setIdeal(int ideal) {
        this.ideal = ideal;
    }

    public int getMin() {
        return this.min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    
    public void setDisciplina(char flag, String codigo, String nome, String teorico, int cred_aula, int cred_work, int carga, int carga_est, int carga_curr) {
        switch (flag) {
            case 'O':
                this.obrigatorias.add(new Disciplina(codigo, nome, teorico, cred_aula, cred_work, carga, carga_est, carga_curr));
                break;

            case 'E':
                this.eletivas.add(new Disciplina(codigo, nome, teorico, cred_aula, cred_work, carga, carga_est, carga_curr));
                break;
            
            case 'L':
                this.livres.add(new Disciplina(codigo, nome, teorico, cred_aula, cred_work, carga, carga_est, carga_curr));
                break;
            
            default:
                break;
        }
    }
    
    public Disciplina getDisciplina(char flag, int i){
        switch (flag) {
            case 'O':
                return this.obrigatorias.get(i);
            case 'E':
                return this.eletivas.get(i);
            case 'L':
                return this.livres.get(i);
            default:
                return null;
        }
    }

    @Override
    public String toString(){
        String aux="";

        return aux;
    }
}