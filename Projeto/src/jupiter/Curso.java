package jupiter;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Curso {
    private String nome, unidade;
    private int ideal, min, max;
    private Map<String,Disciplina> obrigatorias, livres, eletivas;
    Instituto inst;

    Curso(String nome, String unidade, int ideal, int min, int max, Instituto inst) {
        this.nome = nome;
        this.unidade = unidade;
        this.ideal = ideal;
        this.min = min;
        this.max = max;
        this.inst = inst;

        this.obrigatorias = new HashMap<>();
        this.livres = new HashMap<>();
        this.eletivas = new HashMap<>();
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

    
    public void setDisciplina(char flag, String codigo, String nome, int cred_aula, int cred_work, int carga, int carga_est, int carga_curr, int carga_pratica) {
        switch (flag) {
            case 'O':
                this.obrigatorias.put(nome, new Disciplina(codigo, nome, cred_aula, cred_work, carga, carga_est, carga_curr, carga_pratica, this.getNome()));
                this.inst.setDisciplinas(this.nome, nome);
                break;

            case 'E':
                this.eletivas.put(nome, new Disciplina(codigo, nome, cred_aula, cred_work, carga, carga_est, carga_curr, carga_pratica, this.getNome()));
                this.inst.setDisciplinas(this.nome, nome);
                break;
            
            case 'L':
                this.livres.put(nome, new Disciplina(codigo, nome, cred_aula, cred_work, carga, carga_est, carga_curr, carga_pratica, this.getNome()));
                this.inst.setDisciplinas(this.nome, nome);
                break;
            
            default:
                break;
        }
    }
    
    public Disciplina getDisciplina(char flag, String i){
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

    public List<String> getDisciplinas(char flag){
        switch (flag) {
            case 'O':
                return new ArrayList<>(this.obrigatorias.keySet());
            case 'E':
                return new ArrayList<>(this.eletivas.keySet());
            case 'L':
                return new ArrayList<>(this.livres.keySet());
            default:
                return null;
        }
    }

    public String toString(){
        String aux="";

        aux+="Nome do curso: "+this.nome+"\n"
            +"Nome da unidade: "+this.unidade+"\n"
            +"Periodo ideal: "+this.ideal+"     "
            +"Periodo minimo: "+this.min+"      "
            +"Periodo máximo: "+this.max+"\n"
            +"- Disciplinas Obrigatórias:"+"\n"
            +imprime_disciplinas(this.obrigatorias)+"\n"
            +"- Disciplinas Livres:"+"\n"
            +imprime_disciplinas(this.livres)+"\n"
            +"- Disciplinas Eletivas:"+"\n"
            +imprime_disciplinas(this.eletivas)+"\n";

        return aux;
    }

    private String imprime_disciplinas(Map<String, Disciplina> disciplinas){
        String aux ="";

        for(Map.Entry<String, Disciplina> i : disciplinas.entrySet()){
            aux+="  - "+i.getKey()+"\n";
        }

        return aux;
    }
}