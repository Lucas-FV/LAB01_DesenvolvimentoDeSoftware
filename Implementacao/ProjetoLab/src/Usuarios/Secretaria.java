package Usuarios;

import java.util.ArrayList;
import java.util.List;

public class Secretaria extends User {
    private List<User> usuarios = new ArrayList<>();

    public Secretaria(Long id, String nome, String password){
        super(id, nome, password);
    }

    public void cadastrarMateria(String nome, Professor prof) {
        // Implementação do cadastro de matéria
    }
}
