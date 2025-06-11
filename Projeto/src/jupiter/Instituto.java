package jupiter;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Instituto{
    private Map<String,Curso> cursos;
    Map<String, Set<String>> disciplinas;
    private int numCursos;
    private String nome;

    Instituto(String nome){
        this.nome=nome;
        this.numCursos=0;
        this.cursos= new HashMap<>();
        this.disciplinas = new HashMap<>();
    }

    public void setCurso(String nome, String unidade, int ideal, int min, int max) {
        this.cursos.put(nome, new Curso(nome, unidade, ideal, min, max, this));
        this.numCursos++;
    }

    public Curso getCurso(String i) {
        return this.cursos.get(i);
    }

    public List<String> getChaves(){
        return new ArrayList<>(this.cursos.keySet());
    }

    public void setNome(String aux){
        this.nome=aux;
    }

    public String getNome(){
        return this.nome;
    }

    public int getNumCursos(){
        return this.numCursos;
    }

    protected void setDisciplinas(String curso, String disciplina){
        if(!disciplinas.containsKey(disciplina)){
            disciplinas.put(disciplina, new HashSet<>());
        }
        disciplinas.get(disciplina).add(curso);
    }

    public Map<String, Set<String>> getDisciplinasMap(){
        return this.disciplinas;
    }

}
