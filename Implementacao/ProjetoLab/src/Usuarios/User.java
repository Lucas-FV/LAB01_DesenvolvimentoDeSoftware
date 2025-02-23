package Usuarios;

abstract class User {
    protected Long id;
    protected String nome;
    protected String password;

    public void login() {
        // Implementação do login
    }

    public void cadastrar(String nome, String password) {
        this.nome = nome;
        this.password = password;
    }
}