package Academico;

import java.util.ArrayList;
import java.util.List;

public class Curriculo {
    private List<Materia> disciplinas;
    private String disciplinasStr;


    public Curriculo(){
        disciplinas = new ArrayList<>();
    }

    public void cadastrarMateria(Materia materia) {
        disciplinas.add(materia);
    }



    @Override
    public String toString(){
        disciplinas.forEach(m -> disciplinasStr += m.toString() + " - ");
        return disciplinasStr;
    }
}
