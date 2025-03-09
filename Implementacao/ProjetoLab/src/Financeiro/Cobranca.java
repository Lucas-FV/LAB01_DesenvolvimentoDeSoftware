package Financeiro;

import Usuarios.*;

public class Cobranca {
    private static int id;
    private Double valor;
    private final Double VALOR_MATERIA = 500.00;

    public double calcularValor(Aluno aluno) {
        int qtdDisciplinas = aluno.getMatriculas().size();
        valor = qtdDisciplinas * VALOR_MATERIA;
        return valor;
    }
}
