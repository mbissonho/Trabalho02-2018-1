package br.edu.iff.pooa20181.trabalho02_2018_1;

import java.util.Date;

public class Eleitor {

    private String nome;
    private String nomeDaMãe;
    private Date dataDeNascimento;
    private String numeroDoTitulo;
    private int zona;
    private int secao;
    private String municipio;

    public Eleitor(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeDaMãe() {
        return nomeDaMãe;
    }

    public void setNomeDaMãe(String nomeDaMãe) {
        this.nomeDaMãe = nomeDaMãe;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getNumeroDoTitulo() {
        return numeroDoTitulo;
    }

    public void setNumeroDoTitulo(String numeroDoTitulo) {
        this.numeroDoTitulo = numeroDoTitulo;
    }

    public int getZona() {
        return zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public int getSecao() {
        return secao;
    }

    public void setSecao(int secao) {
        this.secao = secao;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
