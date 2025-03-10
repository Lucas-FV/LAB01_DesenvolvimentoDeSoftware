package Academico;
import Usuarios.*;

public class Matricula {
    private Materia materia;
    @SuppressWarnings("unused")
    private Aluno aluno;

    public Matricula(Aluno aluno, Materia materia) {
        this.aluno = aluno;
        try {
            if (!this.matricular(materia)) {
                System.out.println("Não foi possível realizar a matrícula. O período de matrícula está fechado.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar matricular o aluno: " + e.getMessage());
        }
    }

    private boolean matricular(Materia materia) {
        if (PeriodoMatricula.isAberto()) {
            this.materia = materia;
            return true;
        } else {
            return false;
        }
    }

    public void cancelarMatricula() {
        this.materia = null;
    }

    public Materia getMateria() {
        return materia;
    }
}
