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

     public void atribuirNota(Aluno aluno, Materia materia, Long nota) {
        if (materias.contains(materia)) {
        
            if (aluno.getMatriculas().stream().anyMatch(matricula -> matricula.getMateria().equals(materia))) {
                aluno.getNotas().put(materia, nota);  
                System.out.println("Nota " + nota + " atribuída ao aluno " + aluno.getNome() + " na matéria " + materia.getNome());
            } else {
                System.out.println("O aluno " + aluno.getNome() + " não está matriculado na matéria " + materia.getNome());
            }
        } else {
            System.out.println("O professor não leciona a matéria " + materia.getNome());
        }
    }
}
