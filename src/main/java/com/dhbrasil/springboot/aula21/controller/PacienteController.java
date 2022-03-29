package com.dhbrasil.springboot.aula21.controller;

import com.dhbrasil.springboot.aula21.model.Paciente;
import com.dhbrasil.springboot.aula21.resources.config.ConfigJDBC;
import com.dhbrasil.springboot.aula21.resources.impl.PacienteDaoH2;
import com.dhbrasil.springboot.aula21.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private ConfigJDBC configJDBC;
    private PacienteService pacienteService = new PacienteService(
            new PacienteDaoH2());

    @PostMapping
    public ResponseEntity<Paciente> salvar(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.salvar(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(pacienteService.buscar(id).orElse(null));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos() {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<Paciente> atualizar(@RequestBody Paciente paciente) {
        ResponseEntity<Paciente> response = null;
        if (paciente.getId() != null && pacienteService.buscar(paciente.getId()).isPresent()) {
            response = ResponseEntity.ok(pacienteService.atualizar(paciente));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        if (pacienteService.buscar(id).isPresent()) {
            pacienteService.excluir(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    "Paciente excluído");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

}
