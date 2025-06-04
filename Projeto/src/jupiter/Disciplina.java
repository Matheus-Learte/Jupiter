package jupiter;

public class Disciplina {
    String codigo, nome, teorico;
    int creditos_aula, creditos_trabalho, carga, carga_estagio, carga_curriculares;

    Disciplina(String codigo, String nome, String teorico, int cred_aula, int cred_work, int carga, int carga_est, int carga_curr) {
        this.codigo = codigo;
        this.nome = nome;
        this.teorico = teorico;
        this.creditos_aula = cred_aula;
        this.creditos_trabalho = cred_work;
        this.carga = carga;
        this.carga_estagio = carga_est;
        this.carga_curriculares = carga_curr;
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
}
