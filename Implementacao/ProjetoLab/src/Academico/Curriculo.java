package Academico;

import java.util.ArrayList;
import java.util.List;

public class Curriculo {
    private List<Materia> disciplinas = new ArrayList<>();

    public void cadastrarMateria(Materia materia) {
        disciplinas.add(materia);
    }
}
