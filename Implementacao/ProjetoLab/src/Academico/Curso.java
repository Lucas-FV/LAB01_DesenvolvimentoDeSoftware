package Academico;

public class Curso {
    private String nome;
    private int creditos;
    private Curriculo curriculo;

    public void cadastrarCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }
}
