package Usuarios;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Secretaria extends User { //PARA ESCLARECIMENTO É SECRETARIA E NÃO SECRETÁRIA

    private static final String caminhoDosUsuarios = "src/Data/usuarios.txt";
    private List<User> usuarios;
    private static Secretaria secretaria;
    private List<User> alunos;
    private List<User> professors;
    private List<User> cobradores;
    private String userString = "";

    private Secretaria() {
        super("Secretaria", "123", 0);
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
                if (partes.length == 4) {
                    usuarios.add(new User(Integer.parseInt(partes[0]), partes[1], partes[2], Integer.parseInt(partes[3])));
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

    public String getAlunos(){
        separarUsuarios();

        alunos.stream().forEach(u -> userString += "\n" + u.toString()); 
        return userString;
    }

    public String getProfessores(){
        separarUsuarios();

        professors.stream().forEach(u -> userString += "\n" + u.toString()); 
        return userString;
    }

    public String getAgenteFinanceiro(){
        separarUsuarios();

        cobradores.stream().forEach(u -> userString += "\n" + u.toString()); 
        return userString;
    }

    private void separarUsuarios(){
        for(User user : usuarios){
            if(user.TIPO == 1) alunos.add(user);
            else if(user.TIPO == 2) professors.add(user);
            else if(user.TIPO == 3) cobradores.add(user);
        }
    }

    public void cadastrarMateria(String nome, Professor prof) {
        // Implementação do cadastro de matéria
    }
}
