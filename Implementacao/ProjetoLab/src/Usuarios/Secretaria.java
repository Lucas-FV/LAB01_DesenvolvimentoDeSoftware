package Usuarios;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Secretaria extends User {

    private static final String caminhoDosUsuarios = "src/Data/usuarios.txt";
    private List<User> usuarios;
    private static Secretaria secretaria;

    private Secretaria() {
        super("Secretaria", "123");
        usuarios = new ArrayList<>();
        carregarUsuarios();
    }

    public static Secretaria getInstance() {
        if (secretaria == null) {
            secretaria = new Secretaria();
        }
        return secretaria;
    }

    private void carregarUsuarios() {
        File arquivo = new File(caminhoDosUsuarios);
        if (!arquivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 3) {
                    usuarios.add(new User(partes[1], partes[2]));
                }
            }
            User.setID();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void adicionarUsuarioArquivo(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoDosUsuarios, true))) {
            bw.write(user.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarUsuarios() {
        try {
            List<String> linhas = usuarios.stream()
                    .map(User::toString)
                    .collect(Collectors.toList());

            Files.write(Paths.get(caminhoDosUsuarios), linhas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer localizarUltimoID(){
        File arquivo = new File(caminhoDosUsuarios);

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            String aux = "";
            while ((linha = br.readLine()) != null) {
                aux = linha;
            }
            String[] partes = aux.split(",");
        return Integer.parseInt(partes[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean removerUsuario(Integer login) {
        boolean removido = usuarios.removeIf(user -> user.login.equals(login));

        if (removido) {
            salvarUsuarios();
        }

        return removido;
    }


    public void registarUser(User user) {
        usuarios.add(user);
        adicionarUsuarioArquivo(user);
    }

    public User buscarUsuarioPorlogin(Integer login) {
        return usuarios.stream()
            .filter(user -> user.login.equals(login))
            .findFirst()
            .orElse(null);
    }

    public void cadastrarMateria(String nome, Professor prof) {
        // Implementação do cadastro de matéria
    }
}
