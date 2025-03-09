package Academico;

public  class PeriodoMatricula {
    private boolean aberto = false;

    public void abrirMatriculas() {
        aberto = true;
    }

    public void fecharMatriculas() {
        aberto = false;
    }

    public boolean isAberto() {
        return aberto;
    }
}

