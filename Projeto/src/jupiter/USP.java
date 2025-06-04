package jupiter;

import java.util.ArrayList;
import java.util.List;

public class USP {
    List<Curso> cursos;

    USP() {
        cursos = new ArrayList<>();
    }

    public void setCurso(String nome, String unidade, int ideal, int min, int max) {
        cursos.add(new Curso(nome, unidade, ideal, min, max));
    }

    public Curso getCurso(int i) {
        return this.cursos.get(i);
    }
}