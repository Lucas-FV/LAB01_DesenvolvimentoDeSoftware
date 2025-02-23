package Academico;

public class Matricula {
    private Materia materia;

    public Matricula(Materia materia) {
        this.materia = materia;
    }

    public void matricular(Materia materia) {
        this.materia = materia;
    }

    public void cancelarMatricula() {
        this.materia = null;
    }
}
