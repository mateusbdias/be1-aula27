package com.dhbrasil.springboot.aula21.controller;

import com.dhbrasil.springboot.aula21.dao.impl.UsuarioDaoH2;
import com.dhbrasil.springboot.aula21.model.Usuario;
import com.dhbrasil.springboot.aula21.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService = new UsuarioService(
            new UsuarioDaoH2());

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.salvar(usuario));
    }

}
