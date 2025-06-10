package com.plataforma.model;

public class Curso {
    private int idCurso;
    private String titulo;
    private int cargaHoraria;
    private String instrutor;
    
    public Curso() {}
    
    public Curso(String titulo, int cargaHoraria, String instrutor) {
        this.titulo = titulo;
        this.cargaHoraria = cargaHoraria;
        this.instrutor = instrutor;
    }
    
    public Curso(int idCurso, String titulo, int cargaHoraria, String instrutor) {
        this.idCurso = idCurso;
        this.titulo = titulo;
        this.cargaHoraria = cargaHoraria;
        this.instrutor = instrutor;
    }
    
    // Getters e Setters
    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    
    public String getInstrutor() { return instrutor; }
    public void setInstrutor(String instrutor) { this.instrutor = instrutor; }
    
    @Override
    public String toString() {
        return titulo + " - " + instrutor + " (" + cargaHoraria + "h)";
    }
}
