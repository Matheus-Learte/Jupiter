package jupiter;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Instituto {
    private Map<String,Curso> cursos;
    int numCursos;
    private String nome;

    Instituto(String nome){
        this.nome=nome;
        this.numCursos=0;
        this.cursos= new HashMap<>();
    }

    public void setCurso(String nome, String unidade, int ideal, int min, int max) {
        this.cursos.put(nome, new Curso(nome, unidade, ideal, min, max));
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

    @Override
    public String toString(){
        String aux="";

        return aux;
    }
}
