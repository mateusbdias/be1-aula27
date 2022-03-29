package com.dhbrasil.springboot.aula21.model;

import java.util.Date;

public class Consulta {

    private Integer id;
    private Date dataCad;
    private Date dataAtend;
    private Paciente paciente;
    private Dentista dentista;
    private Usuario usuario;

    public Consulta() {
    }

    public Consulta(Integer id, Date dataCad, Date dataAtend, Paciente paciente, Dentista dentista, Usuario usuario) {
        this.id = id;
        this.dataCad = dataCad;
        this.dataAtend = dataAtend;
        this.paciente = paciente;
        this.dentista = dentista;
        this.usuario = usuario;
    }

    public Consulta(Date dataCad, Date dataAtend, Paciente paciente, Dentista dentista, Usuario usuario) {
        this.dataCad = dataCad;
        this.dataAtend = dataAtend;
        this.paciente = paciente;
        this.dentista = dentista;
        this.usuario = usuario;
    }

    public Consulta(Integer id, Date dataAtend, Paciente paciente, Dentista dentista, Usuario usuario) {
        this.id = id;
        this.dataAtend = dataAtend;
        this.paciente = paciente;
        this.dentista = dentista;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataCad() {
        return dataCad;
    }

    public void setDataCad(Date dataCad) {
        this.dataCad = dataCad;
    }

    public Date getDataAtend() {
        return dataAtend;
    }

    public void setDataAtend(Date dataAtend) {
        this.dataAtend = dataAtend;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", dataCad=" + dataCad +
                ", dataAtend=" + dataAtend +
                ", paciente=" + paciente +
                ", dentista=" + dentista +
                ", usuario=" + usuario +
                '}';
    }
}
