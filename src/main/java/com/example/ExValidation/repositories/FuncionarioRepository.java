package com.example.ExValidation.repositories;

import com.example.ExValidation.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
