package com.example.ExValidation.services;

import com.example.ExValidation.dtos.FuncionarioDTO;
import com.example.ExValidation.exceptions.ResourceNotFoundException;
import com.example.ExValidation.models.Funcionario;
import com.example.ExValidation.repositories.FuncionarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioServiceImpl(FuncionarioRepository repository) {
        this.repository = repository;
    }

    private FuncionarioDTO toDto(Funcionario f) {
        if (f == null) return null;
        FuncionarioDTO dto = new FuncionarioDTO();
        BeanUtils.copyProperties(f, dto);
        return dto;
    }

    private Funcionario toEntity(FuncionarioDTO dto) {
        Funcionario f = new Funcionario();
        BeanUtils.copyProperties(dto, f, "id");
        return f;
    }


    @Override
    public FuncionarioDTO criar(FuncionarioDTO dto) {
        Funcionario f = toEntity(dto);
        Funcionario salvo = repository.save(f);
        return toDto(salvo);
    }

    @Override
    public FuncionarioDTO atualizar(Long id, FuncionarioDTO dto) {
        Funcionario f = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado"));

        BeanUtils.copyProperties(dto, f, "id");

        Funcionario atualizado = repository.save(f);
        return toDto(atualizado);
    }

    @Transactional(readOnly = true)
    @Override
    public FuncionarioDTO buscarPorId(Long id) {
        Funcionario f = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Funcionario não encontrado"));
        return toDto(f);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FuncionarioDTO> listarTodos() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Funcionário não encontrado com o id " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public FuncionarioDTO mudarCargo(Long id, String novoCargo) {
        Funcionario f = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado"));

        f.setCargo(novoCargo);

        Funcionario atualizado = repository.save(f);
        return toDto(atualizado);
    }

}