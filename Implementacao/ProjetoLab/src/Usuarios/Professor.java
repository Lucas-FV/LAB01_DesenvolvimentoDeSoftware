package Usuarios;

import java.util.ArrayList;
import java.util.List;

import Academico.*;

public class Professor extends User {
    private List<Materia> materias;

    
    protected Professor(String nome, String password) {
            super(nome, password);
            materias = new ArrayList<>();
        }
    

    public void atribuirMateria(Materia materia) {
        materias.add(materia);
    }
}
