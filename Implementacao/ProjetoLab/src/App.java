import Usuarios.*;
import java.util.Scanner;

public class App {
    static Secretaria sec = Secretaria.getInstance();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Fazer Login");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarUsuario(scanner);
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    fazerLogin(scanner);
                    break;
                case 0:
                    executando = false;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void cadastrarUsuario(Scanner scanner) {
        System.out.print("Tipo de usuário: \n 1. Aluno \n 2. Professor");
        int tipo = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome do Usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        User user = null;

        if(tipo == 1){
            user = new Aluno(nome, senha);
        }else if(tipo == 2){
            user = new Professor(nome, senha);
        }else System.out.println("Tipo não é válido");
        System.out.println("Seu login é: " + user.getLogin());
    }


    private static void listarUsuarios() {
        System.out.println("\n === LISTA DE USUÁRIOS ===");

        System.out.println("\n Alunos:");
        sec.getAlunos();

        System.out.println("\n Professores:");
        sec.getProfessores();

        System.out.println("\n Agentes Financeiros:");
        sec.getAgenteFinanceiro();
    }

    private static void fazerLogin(Scanner scanner) {
        System.out.print("Login: ");
        Integer login = Integer.parseInt(scanner.nextLine());
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if(!User.autenticar(login, senha)) System.out.println("Usuário ou senha incorretos!");
        else System.out.println("Login efetuado com sucesso!");
    }
}