package Usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Academico.*;

public class Aluno extends User {
    private List<Matricula> matriculas;
    private Map<Materia, Long> notas;

    protected Aluno(String nome, String password) {
        super(nome, password);
        matriculas = new ArrayList<>();
        notas = new HashMap<>();
    }

}