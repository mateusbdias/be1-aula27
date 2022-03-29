package com.dhbrasil.springboot.aula21.resources.impl;

import com.dhbrasil.springboot.aula21.resources.IDao;
import com.dhbrasil.springboot.aula21.resources.config.ConfigJDBC;
import com.dhbrasil.springboot.aula21.model.Endereco;
import com.dhbrasil.springboot.aula21.model.Paciente;
import com.dhbrasil.springboot.aula21.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PacienteDaoH2 implements IDao<Paciente> {

    private ConfigJDBC configJDBC;
    private EnderecoDaoH2 enderecoDaoH2;

    public PacienteDaoH2() {
        this.configJDBC = new ConfigJDBC();
        this.enderecoDaoH2 = new EnderecoDaoH2();
    }

    @Override
    public Paciente salvar(Paciente paciente) {
        Connection conn = configJDBC.connectToDB();
        PreparedStatement ps = null;

        paciente.setEndereco(enderecoDaoH2.salvar(paciente.getEndereco()));

        String query = String.format("INSERT INTO pacientes " +
                "(nome, sobrenome, cpf, dataCad, id_endereco) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s');",
                paciente.getNome(),
                paciente.getSobrenome(),
                paciente.getCpf(),
                Util.dateToTimestamp(paciente.getDataCad()),
                paciente.getEndereco().getId());

        try {
            ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next())
                paciente.setId(keys.getInt(1));
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return paciente;
    }

    @Override
    public Optional<Paciente> buscar(Integer id) {
        Connection conn = configJDBC.connectToDB();
        Statement st = null;

        String query = String.format(
                "SELECT id, nome, sobrenome, cpf, dataCad, id_endereco " +
                "FROM pacientes WHERE id = '%s';", id);
        Paciente paciente = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                paciente = criarObjetoPaciente(rs);
            }
            st.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return paciente != null ? Optional.of(paciente) : Optional.empty();
    }

    @Override
    public void excluir(Integer id) {
        Connection conn = configJDBC.connectToDB();
        Statement st = null;

        String query = String.format(
                "DELETE FROM pacientes WHERE id = '%s';", id);
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
            st.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Paciente> buscarTodos() {
        Connection conn = configJDBC.connectToDB();
        PreparedStatement ps = null;

        String query = "SELECT * FROM pacientes;";
        List<Paciente> pacientes = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pacientes.add(criarObjetoPaciente(rs));
            }
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    @Override
    public Paciente atualizar(Paciente paciente) {
        Connection conn = configJDBC.connectToDB();

        // Precisamos atualizar o endereço também
        if (paciente.getEndereco() != null && paciente.getId() != null) {
            enderecoDaoH2.atualizar(paciente.getEndereco());
        }

        String query = String.format(
                "UPDATE pacientes SET nome = '%s', sobrenome = '%s', " +
                        "cpf = '%s' WHERE id = '%s';",
                paciente.getNome(),
                paciente.getSobrenome(),
                paciente.getCpf(),
                paciente.getId());
        execute(conn, query);
        return paciente;
    }

    private Paciente criarObjetoPaciente(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String nome = rs.getString("nome");
        String sobrenome = rs.getString("sobrenome");
        String cpf = rs.getString("cpf");
        Date dataCad = rs.getDate("dataCad");
        Endereco endereco = enderecoDaoH2.buscar(
                rs.getInt("id_endereco")).orElse(null);

        return new Paciente(id, nome, sobrenome, cpf, dataCad, endereco);
    }

    private void execute(Connection conn, String query) {
        try {
            PreparedStatement ps = null;
            ps = conn.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
