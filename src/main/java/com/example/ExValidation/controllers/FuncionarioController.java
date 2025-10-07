package com.example.ExValidation.controllers;


import com.example.ExValidation.dtos.FuncionarioDTO;
import com.example.ExValidation.exceptions.ResourceNotFoundException;
import com.example.ExValidation.services.FuncionarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

            return ResponseEntity.ok(dto);

        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Usuario não encontrado: " + e.getMessage());

        }
        catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> criar(@Valid @RequestBody FuncionarioDTO dto){
        FuncionarioDTO criado =  service.criar(dto);


        return ResponseEntity.created(URI.create("/api/funcionarios/" + criado.getId())).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> atualizar(@PathVariable Long id, @Valid @RequestBody FuncionarioDTO dto){
        FuncionarioDTO atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }


    @PatchMapping("/{id}/cargo")
    public ResponseEntity<FuncionarioDTO> mudarCargo(
            @PathVariable Long id,
            @RequestParam @NotBlank(message = "O novo cargo não pode ser vazio") String novoCargo) {

        FuncionarioDTO atualizado = service.mudarCargo(id, novoCargo);
        return ResponseEntity.ok(atualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}