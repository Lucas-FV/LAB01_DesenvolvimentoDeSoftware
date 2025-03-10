package Academico;

public class PeriodoMatricula {
    private static boolean aberto = false;

    public void abrirMatriculas() {
        aberto = true;
    }

    public void fecharMatriculas() {
        aberto = false;
    }

    public static boolean isAberto() {
        return aberto;
    }
}

