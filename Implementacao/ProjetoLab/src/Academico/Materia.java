package Academico;
import java.util.ArrayList;
import java.util.List;

import Usuarios.*;

public class Materia {
    private String nome;
    private Professor responsavel;
    private List<Matricula> matriculados = new ArrayList<>();

    public Materia(String nome, Professor prof){
        this.nome = nome;
        responsavel = prof;
    }

    public String consultarAlunos() {
        return matriculados.toString();
    }

    public boolean adicionarAluno(Aluno aluno) {
        if (matriculados.size() >= 60) {
            return false;
        }
        matriculados.add(new Matricula(aluno, this));
        return true;
    }

     public String getNome() {
        return nome;
    }
    
    public boolean verificarAtivacao() {
        return matriculados.size() >= 3;
    }
    

    @Override
    public String toString(){
        return nome + "_" + responsavel.getNome() + "_" + matriculados.size();
    }
}
