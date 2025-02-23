package Academico;
import java.util.ArrayList;
import java.util.List;

import Usuarios.*;

public class Materia {
    private String nome;
    private Professor responsavel;
    private List<Matricula> matriculados = new ArrayList<>();

    public String consultarAlunos() {
        return matriculados.toString();
    }

    public void cadastrarAluno(Aluno aluno) {
        matriculados.add(new Matricula(this));
    }
}
