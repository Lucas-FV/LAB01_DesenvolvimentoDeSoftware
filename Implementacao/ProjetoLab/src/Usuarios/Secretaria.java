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
    private static Secretaria instancia;

    private static final String caminhoDosCursos = "C:\\Users\\Lucas\\Documents\\GitHub\\AEDS-II\\LAB01_DesenvolvimentoDeSoftware\\Implementacao\\ProjetoLab\\src\\Data\\cursos.txt";
    private static final String caminhoDasMaterias = "C:\\Users\\Lucas\\Documents\\GitHub\\AEDS-II\\LAB01_DesenvolvimentoDeSoftware\\Implementacao\\ProjetoLab\\src\\Data\\materias.txt";
    private static final String caminhoDosUsuarios = "C:\\Users\\Lucas\\Documents\\GitHub\\AEDS-II\\LAB01_DesenvolvimentoDeSoftware\\Implementacao\\ProjetoLab\\src\\Data\\usuarios.txt";

    private List<User> usuarios;
    private List<User> alunos;
    private List<User> professors;
    private List<User> cobradores;

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
        if(instancia == null) instancia = new Secretaria();
        return instancia;
    }

    public void carregarUsuarios() {
        File arquivo = new File(caminhoDosUsuarios);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de usuários não encontrado.");
            return;
        }
    
        usuarios.clear(); // Limpa a lista antes de carregar os dados
    
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
    
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
    
                if (partes.length < 4) {
                    System.out.println("Linha inválida ignorada: " + linha);
                    continue;  // Ignora linhas inválidas
                }
    
                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                String senha = partes[2];
                int tipo = Integer.parseInt(partes[3]);
    
                User usuario = new User(id, nome, senha, tipo);
                usuarios.add(usuario);
            }
            System.out.println("Usuários carregados com sucesso!");
    
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void listarUsuarios() {
        carregarUsuarios(); // Garante que os usuários estão carregados antes da listagem
    
        System.out.println("\n === LISTA DE USUÁRIOS ===\n");
    
        System.out.println(" Alunos:");
        for (User u : usuarios) {
            if (u.getTIPO() == 1) {
                System.out.println("  - " + u.getLogin() + " | " + u.getNome());
            }
        }
    
        System.out.println("\n Professores:");
        for (User u : usuarios) {
            if (u.getTIPO() == 2) {
                System.out.println("  - " + u.getLogin() + " | " + u.getNome());
            }
        }
    
        System.out.println("\n Agentes Financeiros:");
        for (User u : usuarios) {
            if (u.getTIPO() == 3) {
                System.out.println("  - " + u.getLogin() + " | " + u.getNome());
            }
        }
    }
    
    

    private void adicionarUsuarioArquivo(User user) {
        try (FileWriter fw = new FileWriter(caminhoDosUsuarios, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
    
                out.println(user.getLogin() + "," + user.getNome() + "," + user.getPassword() + "," + user.getTIPO());
            System.out.println("Usuário salvo no arquivo: " + user);
    
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
        File arquivo = new File(caminhoDosUsuarios);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de usuários não encontrado. Retornando ID 0.");
            return 0;
        }
    
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            String ultimaLinha = null;
    
            while ((linha = br.readLine()) != null) {
                ultimaLinha = linha;
            }
    
            if (ultimaLinha == null) {
                System.out.println("Arquivo vazio. Retornando ID 0.");
                return 0;
            }
    
            String[] partes = ultimaLinha.split(",");
            int ultimoID = Integer.parseInt(partes[0]);
            System.out.println("Último ID encontrado: " + ultimoID);
            return ultimoID;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean removerUsuario(Integer login) {
        boolean removido = usuarios.removeIf(user -> user.login.equals(login));

        if (removido) {
            salvarUsuarios();
        }

        return removido;
    }

    public void registarUser(User user) {
        int novoID = localizarUltimoID() + 1;  // Garante que o ID é único
        user.setLogin(novoID);  // Atualiza o ID do usuário antes de salvar
        usuarios.add(user);
        adicionarUsuarioArquivo(user);
        System.out.println("Novo usuário cadastrado com ID: " + novoID);
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
