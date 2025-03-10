package Academico;

public class Curso {
    public String nome;
    private int creditos = 100;
    private Curriculo curriculo;

    public Curso(String nome, Curriculo curriculo){
        this.nome = nome;
        this.curriculo = curriculo;
    }

    public void cadastrarCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

    @Override
    public String toString(){
        return nome + "," + creditos + "," + curriculo.toString();
    }
}
