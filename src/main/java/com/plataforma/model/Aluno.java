package com.plataforma.model;

import java.time.LocalDate;

public class Aluno {
    private int idAluno;
    private String nome;
    private String cpf;
    private LocalDate dataCadastro;
    
    public Aluno() {}
    
    public Aluno(String nome, String cpf, LocalDate dataCadastro) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataCadastro = dataCadastro;
    }
    
    public Aluno(int idAluno, String nome, String cpf, LocalDate dataCadastro) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.cpf = cpf;
        this.dataCadastro = dataCadastro;
    }
    
    // Getters e Setters
    public int getIdAluno() { return idAluno; }
    public void setIdAluno(int idAluno) { this.idAluno = idAluno; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }
    
    @Override
    public String toString() {
        return nome + " - " + cpf;
    }
}
