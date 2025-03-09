package Usuarios;

public abstract class User {
    private Long id;
    private String nome;
    private String password;

    public User (Long id, String nome, String password){
        this.id = id;
        this.nome = nome;
        this.password = password;
    }

    public Long getId (){
        return id;
    }

    public void setId (Long id){
        this.id = id;
    }

    public String getNome (){
        return nome;
    }

    public void setNome (String nome){
        this.nome = nome;
    }

    public String getPassword (){
        return password;
    }

    public void setPassword (String password){
        this.password = password;
    }

    public boolean login(String nome, String password) {
        // Implementação do login
        return this.nome.equals(nome) && this.password.equals(password);
    }

    public static User cadastrar(String tipo, String nome, String password){
        Long idGerado = System.currentTimeMillis();

        switch (tipo.toLowerCase()) {
            case "aluno":
                return new Aluno(idGerado, nome, password);
            
            case "professor":
                return new Professor(idGerado, nome, password);
            
            case "secretaria": 
                return new Secretaria(idGerado, nome, password);
            default:
            throw new IllegalArgumentException("Tipo de usuário inválido!");
        }
    }
}