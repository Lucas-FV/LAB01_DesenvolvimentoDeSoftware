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

    public boolean adicionarAluno(Aluno aluno) {
        if (matriculados.size() >= 60) {
            System.out.println("Matéria cheia! Matrícula não realizada.");
            return false;
        }
        matriculados.add(new Matricula(this));
        return true;
    }
    
    public boolean verificarAtivacao() {
        return matriculados.size() >= 3;
    }
    
}
