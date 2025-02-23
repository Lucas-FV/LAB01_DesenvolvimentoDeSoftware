package Usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Academico.*;

public class Aluno extends User {
    private List<Matricula> matriculas = new ArrayList<>();
    private Map<Materia, Long> notas = new HashMap<>();
}