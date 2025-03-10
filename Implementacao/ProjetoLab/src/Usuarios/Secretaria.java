package Usuarios;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Academico.Curriculo;
import Academico.Curso;
import Academico.Materia;

public class Secretaria extends User { //PARA ESCLARECIMENTO É SECRETARIA E NÃO SECRETÁRIA
    private static final Secretaria Instancia = new Secretaria();

    private static final String caminhoDosUsuarios = "src/Data/usuarios.txt";
    private List<User> usuarios;
    private List<User> alunos;
    private List<User> professors;
    private List<User> cobradores;

    private static final String caminhoDosCursos = "src/Data/cursos.txt";
    private static final String caminhoDasMaterias = "src/Data/materias.txt";
    private List<Curso> cursos;
    private List<Materia> todasMaterias;


    private Secretaria() {
        super("Secretaria", "123", 0);
        usuarios = new ArrayList<>();
        alunos = new ArrayList<>();
        professors = new ArrayList<>();
        cobradores = new ArrayList<>();
        cursos = new ArrayList<>();
        todasMaterias = new ArrayList<>();
    
        try {
            carregarUsuarios();
            carregarCursos();
            carregarMaterias();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar dados na Secretaria. Verifique os arquivos.");
        }
    }
    

    public static Secretaria getInstance() {
        return Instancia;
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

    public Integer localizarUltimoID() {
    try (Stream<String> linhas = Files.lines(Paths.get(caminhoDosUsuarios))) {
        return linhas.reduce((first, second) -> second)  // Pega a última linha
                .map(l -> Integer.parseInt(l.split(",")[0])) // Extrai o ID
                .orElse(0);
    } catch (IOException | NumberFormatException e) {
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
    
    public String getAlunos() {
        separarUsuarios();
        return alunos.stream()
                     .map(User::toString)
                     .collect(Collectors.joining("\n"));
    }
    
    public String getProfessores() {
        separarUsuarios();
        return professors.stream()
                         .map(User::toString)
                         .collect(Collectors.joining("\n"));
    }
    
    public String getAgenteFinanceiro() {
        separarUsuarios();
        return cobradores.stream()
                         .map(User::toString)
                         .collect(Collectors.joining("\n"));
    }
    
    private void separarUsuarios() {
        alunos.clear();
        professors.clear();
        cobradores.clear();
    
        for (User user : usuarios) {
            if (user.TIPO == 1) alunos.add(user);
            else if (user.TIPO == 2) professors.add(user); 
            else if (user.TIPO == 3) cobradores.add(user);
        }
    }   

    private User getProfessorByName(String nome){
        separarUsuarios();
        return professors.stream().filter(u -> u.getNome().equals(nome)).findFirst().orElse(null);
    }

    private void carregarCursos() {
        File arquivo = new File(caminhoDosCursos);
        if (!arquivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 3) {
                    Curriculo curriculo = new Curriculo();
                    String[] materias = partes[2].split(" - ");

                    for(int i=0; i < materias.length; i++){
                        String[] mat = materias[i].split("_");
                        Materia m = new Materia(mat[0], (Professor)getProfessorByName(mat[1]));
                        curriculo.cadastrarMateria(m);
                        todasMaterias.add(m);
                    }

                    cursos.add(new Curso(partes[0], curriculo));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void adicionarCursoArquivo(Curso curso) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoDosCursos, true))) {
            bw.write(curso.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarCursos() {
        try {
            List<String> linhas = cursos.stream()
                    .map(Curso::toString)
                    .collect(Collectors.toList());

            Files.write(Paths.get(caminhoDosCursos), linhas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarMaterias(){
        File arquivo = new File(caminhoDasMaterias);
        if (!arquivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                    String[] mat = linha.split("_");
                    Materia m = new Materia(mat[0], (Professor)getProfessorByName(mat[1]));
                    todasMaterias.add(m);

                }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void adicionarMateriaArquivo(Materia materia) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoDasMaterias, true))) {
            bw.write(materia.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cadastrarMateria(String nome, Professor prof) {
        Materia m = new Materia(nome, prof);
        todasMaterias.add(m);
        adicionarMateriaArquivo(m);
    }

    public void cadastrarCurso(String nome, Curriculo curriculo){
        adicionarCursoArquivo(new Curso(nome, curriculo));
    }

    public boolean removerCurso(String nome) {
        boolean removido = cursos.removeIf(user -> user.nome.equals(nome));

        if (removido) {
            salvarCursos();
        }

        return removido;
    }

}
