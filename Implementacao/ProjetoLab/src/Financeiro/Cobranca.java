package Financeiro;

import Usuarios.*;

public class Cobranca {
    private static int idCounter = 0;
    private int id;
    private double valor;
    private static final double VALOR_MATERIA = 500.00;

    public Cobranca(Aluno aluno) {
        this.id = ++idCounter;
        this.valor = calcularValor(aluno);
    }

    private double calcularValor(Aluno aluno) {
        if (aluno.getMatriculas() == null) {
            return 0.0;
        }
        return aluno.getMatriculas().size() * VALOR_MATERIA;
    }

    public double getValor() {
        return valor;
    }

    public int getId() {
        return id;
    }
}
