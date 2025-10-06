package com.example.ExValidation.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "funcionarios")
@Data
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @Column(length = 20)
    private String telefone;

    @Column(name = "documento", length = 40)
    private String documento;




}
