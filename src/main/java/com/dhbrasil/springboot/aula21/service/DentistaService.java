package com.dhbrasil.springboot.aula21.service;

import com.dhbrasil.springboot.aula21.dao.IDao;
import com.dhbrasil.springboot.aula21.model.Dentista;

import java.util.List;
import java.util.Optional;

public class DentistaService {

    private IDao<Dentista> dentistaIDao;

    public DentistaService(IDao<Dentista> dentistaIDao) {
        this.dentistaIDao = dentistaIDao;
    }

    public Dentista salvar(Dentista dentista) {
        return dentistaIDao.salvar(dentista);
    }

    public Optional<Dentista> buscar(Integer id) {
        return dentistaIDao.buscar(id);
    }

    public List<Dentista> buscarTodos() {
        return dentistaIDao.buscarTodos();
    }

    public Dentista atualizar(Dentista dentista) {
        return dentistaIDao.atualizar(dentista);
    }

    public void excluir(Integer id) {
        dentistaIDao.excluir(id);
    }

}
