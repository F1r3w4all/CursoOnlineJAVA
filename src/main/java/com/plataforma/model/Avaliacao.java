package com.plataforma.model;

public class Avaliacao {
    private int idAvaliacao;
    private int idAluno;
    private int idCurso;
    private double nota;
    private String feedback;
    private String nomeAluno;
    private String tituloCurso;
    
    public Avaliacao() {}
    
    public Avaliacao(int idAluno, int idCurso, double nota, String feedback) {
        this.idAluno = idAluno;
        this.idCurso = idCurso;
        this.nota = nota;
        this.feedback = feedback;
    }
    
    public Avaliacao(int idAvaliacao, int idAluno, int idCurso, double nota, String feedback) {
        this.idAvaliacao = idAvaliacao;
        this.idAluno = idAluno;
        this.idCurso = idCurso;
        this.nota = nota;
        this.feedback = feedback;
    }
    
    // Getters e Setters
    public int getIdAvaliacao() { return idAvaliacao; }
    public void setIdAvaliacao(int idAvaliacao) { this.idAvaliacao = idAvaliacao; }
    
    public int getIdAluno() { return idAluno; }
    public void setIdAluno(int idAluno) { this.idAluno = idAluno; }
    
    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }
    
    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }
    
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
    
    public String getNomeAluno() { return nomeAluno; }
    public void setNomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; }
    
    public String getTituloCurso() { return tituloCurso; }
    public void setTituloCurso(String tituloCurso) { this.tituloCurso = tituloCurso; }
}
