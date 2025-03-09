package Usuarios;

import java.util.Objects;

public class User {
    protected static int id;
    protected Integer login;
    protected String nome;
    protected String password;

    protected User(String nome, String password){
        this.nome = nome;
        this.password = password;
        login = id++;
        Secretaria.getInstance().registarUser(this);
    }

    protected static void setID(){
        id = Secretaria.getInstance().localizarUltimoID() + 1;
    }

    public static boolean autenticar(Integer login, String senha) {
        User user = Secretaria.getInstance().buscarUsuarioPorlogin(login);
        return user != null && Objects.equals(user.password, senha);
    }

    @Override
    public String toString() {
        return login + "," + nome + "," + password;
    }
}