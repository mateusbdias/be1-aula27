package com.dhbrasil.springboot.aula21.service;

import com.dhbrasil.springboot.aula21.dao.IDao;
import com.dhbrasil.springboot.aula21.model.Endereco;

import java.util.List;
import java.util.Optional;

public class EnderecoService {

    private IDao<Endereco> enderecoIDao;

    public EnderecoService(IDao<Endereco> enderecoIDao) {
        this.enderecoIDao = enderecoIDao;
    }

    public Endereco salvar(Endereco endereco) {
        return enderecoIDao.salvar(endereco);
    }

    public Optional<Endereco> buscar(Integer id) {
        return enderecoIDao.buscar(id);
    }

    public List<Endereco> buscarTodos() {
        return enderecoIDao.buscarTodos();
    }

    public Endereco atualizar(Endereco endereco) {
        return enderecoIDao.atualizar(endereco);
    }

    public void excluir(Integer id) {
        enderecoIDao.excluir(id);
    }

}
