package com.example.ExValidation.dtos;

import jakarta.validation.constraints.*;


public class FuncionarioDTO {

    private Long id;

    @NotNull
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 120)
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email
    @Size(max = 60)
    private String email;

    @Size(max = 20)
    private String telefone;


    @Pattern(regexp = "\\d{11}", message = "O CPF deve ter 11 dígitos numéricos")
    @Size(max = 40)
    private String documento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
