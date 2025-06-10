package com.plataforma.service;

import com.plataforma.dao.MatriculaDAO;
import com.plataforma.dao.AlunoDAO;
import com.plataforma.dao.CursoDAO;
import com.plataforma.model.Matricula;
import java.time.LocalDate;
import java.util.List;

public class MatriculaService {
    private MatriculaDAO matriculaDAO;
    private AlunoDAO alunoDAO;
    private CursoDAO cursoDAO;
    
    public MatriculaService() {
        this.matriculaDAO = new MatriculaDAO();
        this.alunoDAO = new AlunoDAO();
        this.cursoDAO = new CursoDAO();
    }
    
    public boolean realizarMatricula(int idAluno, int idCurso, LocalDate dataMatricula) {
        // Validações de negócio
        if (alunoDAO.buscarPorId(idAluno) == null) {
            throw new IllegalArgumentException("Aluno não encontrado");
        }
        
        if (cursoDAO.buscarPorId(idCurso) == null) {
            throw new IllegalArgumentException("Curso não encontrado");
        }
        
        if (matriculaDAO.verificarMatriculaExistente(idAluno, idCurso)) {
            throw new IllegalArgumentException("Aluno já matriculado neste curso");
        }
        
        if (dataMatricula.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de matrícula não pode ser futura");
        }
        
        // Verificar se o aluno não excede o limite de matrículas simultâneas
        if (contarMatriculasAtivas(idAluno) >= 5) {
            throw new IllegalArgumentException("Aluno não pode ter mais de 5 matrículas ativas");
        }
        
        Matricula matricula = new Matricula(idAluno, idCurso, dataMatricula);
        return matriculaDAO.inserir(matricula);
    }
    
    public List<Matricula> listarTodasMatriculas() {
        return matriculaDAO.listarTodas();
    }
    
    public boolean cancelarMatricula(int idMatricula) {
        return matriculaDAO.excluir(idMatricula);
    }
    
    public boolean verificarMatriculaExistente(int idAluno, int idCurso) {
        return matriculaDAO.verificarMatriculaExistente(idAluno, idCurso);
    }
    
    private int contarMatriculasAtivas(int idAluno) {
        List<Matricula> matriculas = matriculaDAO.listarTodas();
        return (int) matriculas.stream()
                .filter(m -> m.getIdAluno() == idAluno)
                .count();
    }
    
    public List<Matricula> listarMatriculasPorAluno(int idAluno) {
        List<Matricula> todasMatriculas = matriculaDAO.listarTodas();
        return todasMatriculas.stream()
                .filter(m -> m.getIdAluno() == idAluno)
                .toList();
    }
    
    public List<Matricula> listarMatriculasPorCurso(int idCurso) {
        List<Matricula> todasMatriculas = matriculaDAO.listarTodas();
        return todasMatriculas.stream()
                .filter(m -> m.getIdCurso() == idCurso)
                .toList();
    }
}
