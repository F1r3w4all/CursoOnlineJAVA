package com.plataforma.service;

import com.plataforma.dao.CursoDAO;
import com.plataforma.model.Curso;
import java.util.List;

public class CursoService {
    private CursoDAO cursoDAO;
    
    public CursoService() {
        this.cursoDAO = new CursoDAO();
    }
    
    public boolean cadastrarCurso(String titulo, int cargaHoraria, String instrutor) {
        // Validações de negócio
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título é obrigatório");
        }
        
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser positiva");
        }
        
        if (cargaHoraria > 1000) {
            throw new IllegalArgumentException("Carga horária não pode exceder 1000 horas");
        }
        
        if (instrutor == null || instrutor.trim().isEmpty()) {
            throw new IllegalArgumentException("Instrutor é obrigatório");
        }
        
        Curso curso = new Curso(titulo.trim(), cargaHoraria, instrutor.trim());
        return cursoDAO.inserir(curso);
    }
    
    public List<Curso> listarTodosCursos() {
        return cursoDAO.listarTodos();
    }
    
    public boolean atualizarCurso(int id, String titulo, int cargaHoraria, String instrutor) {
        Curso curso = cursoDAO.buscarPorId(id);
        if (curso == null) {
            throw new IllegalArgumentException("Curso não encontrado");
        }
        
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título é obrigatório");
        }
        
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser positiva");
        }
        
        if (cargaHoraria > 1000) {
            throw new IllegalArgumentException("Carga horária não pode exceder 1000 horas");
        }
        
        if (instrutor == null || instrutor.trim().isEmpty()) {
            throw new IllegalArgumentException("Instrutor é obrigatório");
        }
        
        curso.setTitulo(titulo.trim());
        curso.setCargaHoraria(cargaHoraria);
        curso.setInstrutor(instrutor.trim());
        
        return cursoDAO.atualizar(curso);
    }
    
    public boolean excluirCurso(int id) {
        Curso curso = cursoDAO.buscarPorId(id);
        if (curso == null) {
            throw new IllegalArgumentException("Curso não encontrado");
        }
        
        return cursoDAO.excluir(id);
    }
    
    public Curso buscarCursoPorId(int id) {
        return cursoDAO.buscarPorId(id);
    }
    
    public boolean validarCargaHoraria(int cargaHoraria) {
        return cargaHoraria > 0 && cargaHoraria <= 1000;
    }
}
