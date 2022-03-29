package com.dhbrasil.springboot.aula21.resources.impl;

import com.dhbrasil.springboot.aula21.model.Consulta;
import com.dhbrasil.springboot.aula21.resources.IDao;
import com.dhbrasil.springboot.aula21.resources.config.ConfigJDBC;

import java.util.List;
import java.util.Optional;

public class ConsultaDaoH2 implements IDao<Consulta> {

    private ConfigJDBC configJDBC;
    private PacienteDaoH2 pacienteDaoH2;
    private DentistaDaoH2 dentistaDaoH2;
    private UsuarioDaoH2 usuarioDaoH2;

    public ConsultaDaoH2() {
        this.configJDBC = new ConfigJDBC();
        this.pacienteDaoH2 = new PacienteDaoH2();
        this.dentistaDaoH2 = new DentistaDaoH2();
        this.usuarioDaoH2 = new UsuarioDaoH2();
    }


    @Override
    public Consulta salvar(Consulta consulta) {
        return null;
    }

    @Override
    public Optional<Consulta> buscar(Integer id) {
        return Optional.empty();
    }

    @Override
    public void excluir(Integer id) {

    }

    @Override
    public List<Consulta> buscarTodos() {
        return null;
    }

    @Override
    public Consulta atualizar(Consulta consulta) {
        return null;
    }
}
