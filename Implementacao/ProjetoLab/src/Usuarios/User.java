package Usuarios;

import java.util.Objects;

public class User {
    protected static int id;
    protected Integer login;
    public void setLogin(Integer login) {
        this.login = login;
    }

    protected String nome;
    protected String password;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected static final int ADM = 0, ALUNO = 1, PROF = 2, COBRADOR = 3;
    protected final int TIPO;


    public int getTIPO() {
        return TIPO;
    }

    protected User(String nome, String password, int tipo){
        this.nome = nome;
        this.password = password;
        login = id++;
        this.TIPO = tipo;
    }

    protected User(Integer login, String nome, String senha, int tipo){
        this.nome = nome;
        this.login = login;
        this.password = senha;
        this.TIPO = tipo;
    }


     public String getNome() {
        return nome;
    }

    protected static void setID(){
        id = Secretaria.getInstance().localizarUltimoID() + 1;
    }

    public static boolean autenticar(Integer login, String senha) {
        User user = Secretaria.getInstance().buscarUsuarioPorlogin(login);
        return user != null && Objects.equals(user.password, senha);
    }

    public Integer getLogin(){
    return login;
    }

    @Override
    public String toString() {
        return login + "," + nome + "," + password + "," + TIPO;
    }



}