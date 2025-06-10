package com.plataforma.model;

import java.time.LocalDate;

public class Matricula {
    private int idMatricula;
    private int idAluno;
    private int idCurso;
    private LocalDate dataMatricula;
    private String nomeAluno;
    private String tituloCurso;
    
    public Matricula() {}
    
    public Matricula(int idAluno, int idCurso, LocalDate dataMatricula) {
        this.idAluno = idAluno;
        this.idCurso = idCurso;
        this.dataMatricula = dataMatricula;
    }
    
    public Matricula(int idMatricula, int idAluno, int idCurso, LocalDate dataMatricula) {
        this.idMatricula = idMatricula;
        this.idAluno = idAluno;
        this.idCurso = idCurso;
        this.dataMatricula = dataMatricula;
    }
    
    // Getters e Setters
    public int getIdMatricula() { return idMatricula; }
    public void setIdMatricula(int idMatricula) { this.idMatricula = idMatricula; }
    
    public int getIdAluno() { return idAluno; }
    public void setIdAluno(int idAluno) { this.idAluno = idAluno; }
    
    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }
    
    public LocalDate getDataMatricula() { return dataMatricula; }
    public void setDataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; }
    
    public String getNomeAluno() { return nomeAluno; }
    public void setNomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; }
    
    public String getTituloCurso() { return tituloCurso; }
    public void setTituloCurso(String tituloCurso) { this.tituloCurso = tituloCurso; }
}
