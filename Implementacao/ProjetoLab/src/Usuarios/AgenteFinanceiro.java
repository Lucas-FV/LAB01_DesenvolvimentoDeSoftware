package Usuarios;

import Financeiro.Cobranca;

public class AgenteFinanceiro extends User {

    protected AgenteFinanceiro(String nome, String password) {
        super(nome, password, 3);
    }

    public void gerarBoleto(Aluno aluno) {
        Cobranca cobranca = new Cobranca(aluno);
        System.out.println("Boleto gerado para o aluno " + aluno.getNome() + " no valor de R$ " + cobranca.getValor());
    }
}
