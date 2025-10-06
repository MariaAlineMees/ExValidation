package com.example.ExValidation.controllers;


import com.example.ExValidation.dtos.FuncionarioDTO;
import com.example.ExValidation.services.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){

        try {
            FuncionarioDTO dto = service.buscarPorId(id);

            if (dto == null) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado com o ID: " + id);
            }

            return ResponseEntity.ok(dto);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> criar(@Valid @RequestBody FuncionarioDTO dto){
        FuncionarioDTO criado =  service.criar(dto);


        return ResponseEntity.created(URI.create("/api/funcionarios/" + criado.getId())).body(criado);
    }

}