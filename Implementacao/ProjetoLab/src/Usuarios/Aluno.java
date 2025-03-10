package Usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Academico.*;

public class Aluno extends User {
    private List<Matricula> matriculas;
    private Map<Materia, Long> notas;

    public Aluno(String nome, String password) {
        super(nome, password, 1);
        matriculas = new ArrayList<>();
        notas = new HashMap<>();
    }

    public String getNome() {
        return super.getNome();
    }

    public List<Matricula> getMatriculas(){
        return matriculas;
    }

     public Map<Materia, Long> getNotas() {
        return notas;
    }

}