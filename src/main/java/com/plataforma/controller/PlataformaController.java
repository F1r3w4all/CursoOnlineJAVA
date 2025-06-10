package com.plataforma.controller;

import com.plataforma.service.*;
import com.plataforma.model.*;
import java.time.LocalDate;
import java.util.List;

public class PlataformaController {
    private AlunoService alunoService;
    private CursoService cursoService;
    private MatriculaService matriculaService;
    private AvaliacaoService avaliacaoService;
    
    public PlataformaController() {
        this.alunoService = new AlunoService();
        this.cursoService = new CursoService();
        this.matriculaService = new MatriculaService();
        this.avaliacaoService = new AvaliacaoService();
    }
    
    // ==================== MÉTODOS PARA ALUNOS ====================
    
    public boolean cadastrarAluno(String nome, String cpf, LocalDate dataCadastro) {
        try {
            return alunoService.cadastrarAluno(nome, cpf, dataCadastro);
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar aluno: " + e.getMessage());
            throw e;
        }
    }
    
    public List<Aluno> listarAlunos() {
        return alunoService.listarTodosAlunos();
    }
    
    public List<Aluno> listarTodosAlunos() {
        return alunoService.listarTodosAlunos();
    }
    
    public boolean atualizarAluno(int id, String nome, String cpf, LocalDate dataCadastro) {
        try {
            return alunoService.atualizarAluno(id, nome, cpf, dataCadastro);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
            throw e;
        }
    }
    
    public boolean excluirAluno(int id) {
        try {
            return alunoService.excluirAluno(id);
        } catch (Exception e) {
            System.err.println("Erro ao excluir aluno: " + e.getMessage());
            throw e;
        }
    }
    
    public Aluno buscarAlunoPorId(int id) {
        return alunoService.buscarAlunoPorId(id);
    }
    
    // ==================== MÉTODOS PARA CURSOS ====================
    
    public boolean cadastrarCurso(String titulo, int cargaHoraria, String instrutor) {
        try {
            return cursoService.cadastrarCurso(titulo, cargaHoraria, instrutor);
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar curso: " + e.getMessage());
            throw e;
        }
    }
    
    public List<Curso> listarCursos() {
        return cursoService.listarTodosCursos();
    }
    
    public List<Curso> listarTodosCursos() {
        return cursoService.listarTodosCursos();
    }
    
    public boolean atualizarCurso(int id, String titulo, int cargaHoraria, String instrutor) {
        try {
            return cursoService.atualizarCurso(id, titulo, cargaHoraria, instrutor);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar curso: " + e.getMessage());
            throw e;
        }
    }
    
    public boolean excluirCurso(int id) {
        try {
            return cursoService.excluirCurso(id);
        } catch (Exception e) {
            System.err.println("Erro ao excluir curso: " + e.getMessage());
            throw e;
        }
    }
    
    public Curso buscarCursoPorId(int id) {
        return cursoService.buscarCursoPorId(id);
    }
    
    // ==================== MÉTODOS PARA MATRÍCULAS ====================
    
    public boolean realizarMatricula(int idAluno, int idCurso, LocalDate dataMatricula) {
        try {
            return matriculaService.realizarMatricula(idAluno, idCurso, dataMatricula);
        } catch (Exception e) {
            System.err.println("Erro ao realizar matrícula: " + e.getMessage());
            throw e;
        }
    }
    
    public List<Matricula> listarMatriculas() {
        return matriculaService.listarTodasMatriculas();
    }
    
    public List<Matricula> listarTodasMatriculas() {
        return matriculaService.listarTodasMatriculas();
    }
    
    public boolean excluirMatricula(int idMatricula) {
        try {
            return matriculaService.cancelarMatricula(idMatricula);
        } catch (Exception e) {
            System.err.println("Erro ao cancelar matrícula: " + e.getMessage());
            throw e;
        }
    }
    
    public boolean verificarMatriculaExistente(int idAluno, int idCurso) {
        return matriculaService.verificarMatriculaExistente(idAluno, idCurso);
    }
    
    public List<Matricula> listarMatriculasPorAluno(int idAluno) {
        return matriculaService.listarMatriculasPorAluno(idAluno);
    }
    
    public List<Matricula> listarMatriculasPorCurso(int idCurso) {
        return matriculaService.listarMatriculasPorCurso(idCurso);
    }
    
    // ==================== MÉTODOS PARA AVALIAÇÕES ====================
    
    public boolean registrarAvaliacao(int idAluno, int idCurso, double nota, String feedback) {
        try {
            return avaliacaoService.registrarAvaliacao(idAluno, idCurso, nota, feedback);
        } catch (Exception e) {
            System.err.println("Erro ao registrar avaliação: " + e.getMessage());
            throw e;
        }
    }
    
    public List<Avaliacao> listarAvaliacoes() {
        return avaliacaoService.listarTodasAvaliacoes();
    }
    
    public boolean atualizarAvaliacao(int idAvaliacao, double nota, String feedback) {
        try {
            return avaliacaoService.atualizarAvaliacao(idAvaliacao, nota, feedback);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar avaliação: " + e.getMessage());
            throw e;
        }
    }
    
    public boolean excluirAvaliacao(int idAvaliacao) {
        try {
            return avaliacaoService.excluirAvaliacao(idAvaliacao);
        } catch (Exception e) {
            System.err.println("Erro ao excluir avaliação: " + e.getMessage());
            throw e;
        }
    }
    
    // ==================== MÉTODOS DE RELATÓRIO ====================
    
    public double calcularMediaAluno(int idAluno) {
        return avaliacaoService.calcularMediaAluno(idAluno);
    }
    
    public double calcularMediaCurso(int idCurso) {
        return avaliacaoService.calcularMediaCurso(idCurso);
    }
    
    public List<Avaliacao> listarAvaliacoesPorAluno(int idAluno) {
        return avaliacaoService.listarAvaliacoesPorAluno(idAluno);
    }
    
    public List<Avaliacao> listarAvaliacoesPorCurso(int idCurso) {
        return avaliacaoService.listarAvaliacoesPorCurso(idCurso);
    }
    
    public String obterConceitoNota(double nota) {
        return avaliacaoService.obterConceito(nota);
    }
    
    // ==================== MÉTODOS DE VALIDAÇÃO ====================
    
    public boolean validarCargaHoraria(int cargaHoraria) {
        return cursoService.validarCargaHoraria(cargaHoraria);
    }
    
    // ==================== MÉTODOS DE ESTATÍSTICAS ====================
    
    public int contarTotalAlunos() {
        return listarAlunos().size();
    }
    
    public int contarTotalCursos() {
        return listarCursos().size();
    }
    
    public int contarTotalMatriculas() {
        return listarMatriculas().size();
    }
    
    public int contarTotalAvaliacoes() {
        return listarAvaliacoes().size();
    }
    
    public double calcularMediaGeralNotas() {
        List<Avaliacao> avaliacoes = listarAvaliacoes();
        return avaliacoes.stream()
                .mapToDouble(Avaliacao::getNota)
                .average()
                .orElse(0.0);
    }
}
