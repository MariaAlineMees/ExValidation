package com.example.ExValidation.services;

import com.example.ExValidation.dtos.FuncionarioDTO;
import com.example.ExValidation.models.Funcionario;

import java.util.List;

public interface FuncionarioService {
    FuncionarioDTO criar(FuncionarioDTO dto);
    FuncionarioDTO buscarPorId(Long id);
    List<FuncionarioDTO>  listarTodos();




}
