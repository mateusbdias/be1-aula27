package com.dhbrasil.springboot.aula21.model;

public class Dentista {

    private Integer id;
    private String nome;
    private String email;
    private Integer numMatricula;
    private Integer atendeConvenio;

    public Dentista() {
    }

    public Dentista(Integer id, String nome, String email, Integer numMatricula, Integer atendeConvenio) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.numMatricula = numMatricula;
        this.atendeConvenio = atendeConvenio;
    }

    public Dentista(String nome, String email, Integer numMatricula, Integer atendeConvenio) {
        this.nome = nome;
        this.email = email;
        this.numMatricula = numMatricula;
        this.atendeConvenio = atendeConvenio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(Integer numMatricula) {
        this.numMatricula = numMatricula;
    }

    public Integer getAtendeConvenio() {
        return atendeConvenio;
    }

    public void setAtendeConvenio(Integer atendeConvenio) {
        this.atendeConvenio = atendeConvenio;
    }

    @Override
    public String toString() {
        return "Dentista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", numMatricula=" + numMatricula +
                ", atendeConvenio=" + atendeConvenio +
                '}';
    }
}
