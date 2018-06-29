package br.edu.iff.pooa20181.trabalho02_2018_1;

public class Candidato {

    private String nome;
    private String partido;
    private String numeroNaUrna;
    private String cargo;
    private int numeroDeVotos;
    private String estado;
    private String municipio;

    public Candidato(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }


    public String getCargo() {
        return cargo;
    }

    public String getNumeroNaUrna() {
        return numeroNaUrna;
    }

    public void setNumeroNaUrna(String numeroNaUrna) {
        this.numeroNaUrna = numeroNaUrna;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getNumeroDeVotos() {
        return numeroDeVotos;
    }

    public void setNumeroDeVotos(int numeroDeVotos) {
        this.numeroDeVotos = numeroDeVotos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
