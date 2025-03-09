package Usuarios;

import java.util.ArrayList;
import java.util.List;

import Academico.*;

public class Professor extends User {
    private List<Materia> materias;

    
    public Professor(String nome, String password) {
            super(nome, password, 2);
            materias = new ArrayList<>();
        }
    

    public void atribuirMateria(Materia materia) {
        materias.add(materia);
    }
}
