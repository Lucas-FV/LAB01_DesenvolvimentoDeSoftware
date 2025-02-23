package Usuarios;

import java.util.ArrayList;
import java.util.List;

import Academico.*;

public class Professor extends User {
    private List<Materia> materias = new ArrayList<>();

    public void atribuirMateria(Materia materia) {
        materias.add(materia);
    }
}
