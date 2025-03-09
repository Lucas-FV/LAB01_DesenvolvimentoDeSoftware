

import Usuarios.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static List<Aluno> alunos = new ArrayList<>();
    private static List<Professor> professores = new ArrayList<>();
    private static List<Secretaria> secretarias = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Cadastrar Professor");
            System.out.println("3. Cadastrar Secretária");
            System.out.println("4. Listar Usuários");
            System.out.println("5. Fazer Login");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarAluno(scanner);
                    break;
                case 2:
                    cadastrarProfessor(scanner);
                    break;
                case 3:
                    cadastrarSecretaria(scanner);;
                    break;
                case 4:
                    listarUsuarios();
                    break;
                case 5:
                    fazerLogin(scanner);
                    break;
                case 6:
                    executando = false;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void cadastrarAluno(Scanner scanner) {
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Senha do aluno: ");
        String senha = scanner.nextLine();

        Aluno novoAluno = new Aluno(System.currentTimeMillis(), nome, senha);
        alunos.add(novoAluno);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    private static void cadastrarSecretaria(Scanner scanner){
        System.out.print("Nome da secretaria: ");
        String nome = scanner.nextLine();
        System.out.print("Senha da secretaria: ");
        String senha = scanner.nextLine();

        Secretaria novaSecretaria = new Secretaria(System.currentTimeMillis(), nome, senha);
        secretarias.add(novaSecretaria);
        System.out.println("Secretaria cadastrada com sucesso!");
    }

    private static void cadastrarProfessor(Scanner scanner) {
        System.out.print("Nome do professor: ");
        String nome = scanner.nextLine();
        System.out.print("Senha do professor: ");
        String senha = scanner.nextLine();

        Professor novoProfessor = new Professor(System.currentTimeMillis(), nome, senha);
        professores.add(novoProfessor);
        System.out.println("Professor cadastrado com sucesso!");
    }

    private static void listarUsuarios() {
        System.out.println("\n=== LISTA DE USUÁRIOS ===");

        System.out.println("\nAlunos:");
        for (Aluno aluno : alunos) {
            System.out.println("- " + aluno.getNome());
        }

        System.out.println("\nProfessores:");
        for (Professor professor : professores) {
            System.out.println("- " + professor.getNome());
        }
    }

    private static void fazerLogin(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Aluno aluno : alunos) {
            if (aluno.login(nome, senha)) {
                System.out.println("Login bem-sucedido como Aluno!");
                return;
            }
        }
        for (Professor professor : professores) {
            if (professor.login(nome, senha)) {
                System.out.println("Login bem-sucedido como Professor!");
                return;
            }
        }
        for(Secretaria secretaria : secretarias){
            if(secretaria.login(nome,senha)){
                System.out.println("Login bem-sucedido como secretaria");
                return;
            }
        }

        System.out.println("Usuário ou senha incorretos!");
    }
}