package com.plataforma.service;

import com.plataforma.dao.AvaliacaoDAO;
import com.plataforma.dao.MatriculaDAO;
import com.plataforma.model.Avaliacao;
import java.util.List;

public class AvaliacaoService {
    private AvaliacaoDAO avaliacaoDAO;
    private MatriculaDAO matriculaDAO;
    
    public AvaliacaoService() {
        this.avaliacaoDAO = new AvaliacaoDAO();
        this.matriculaDAO = new MatriculaDAO();
    }
    
    public boolean registrarAvaliacao(int idAluno, int idCurso, double nota, String feedback) {
        // Validações de negócio
        if (!matriculaDAO.verificarMatriculaExistente(idAluno, idCurso)) {
            throw new IllegalArgumentException("Aluno não está matriculado neste curso");
        }
        
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("Nota deve estar entre 0 e 10");
        }
        
        if (feedback == null) {
            feedback = "";
        }
        
        // Verificar se já existe avaliação para este aluno/curso
        List<Avaliacao> avaliacoes = avaliacaoDAO.listarTodas();
        boolean jaAvaliado = avaliacoes.stream()
                .anyMatch(av -> av.getIdAluno() == idAluno && av.getIdCurso() == idCurso);
        
        if (jaAvaliado) {
            throw new IllegalArgumentException("Aluno já foi avaliado neste curso");
        }
        
        Avaliacao avaliacao = new Avaliacao(idAluno, idCurso, nota, feedback.trim());
        return avaliacaoDAO.inserir(avaliacao);
    }
    
    public List<Avaliacao> listarTodasAvaliacoes() {
        return avaliacaoDAO.listarTodas();
    }
    
    public boolean atualizarAvaliacao(int idAvaliacao, double nota, String feedback) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("Nota deve estar entre 0 e 10");
        }
        
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setIdAvaliacao(idAvaliacao);
        avaliacao.setNota(nota);
        avaliacao.setFeedback(feedback != null ? feedback.trim() : "");
        
        return avaliacaoDAO.atualizar(avaliacao);
    }
    
    public boolean excluirAvaliacao(int idAvaliacao) {
        return avaliacaoDAO.excluir(idAvaliacao);
    }
    
    public double calcularMediaAluno(int idAluno) {
        List<Avaliacao> avaliacoes = avaliacaoDAO.listarTodas();
        return avaliacoes.stream()
                .filter(av -> av.getIdAluno() == idAluno)
                .mapToDouble(Avaliacao::getNota)
                .average()
                .orElse(0.0);
    }
    
    public double calcularMediaCurso(int idCurso) {
        List<Avaliacao> avaliacoes = avaliacaoDAO.listarTodas();
        return avaliacoes.stream()
                .filter(av -> av.getIdCurso() == idCurso)
                .mapToDouble(Avaliacao::getNota)
                .average()
                .orElse(0.0);
    }
    
    public List<Avaliacao> listarAvaliacoesPorAluno(int idAluno) {
        List<Avaliacao> todasAvaliacoes = avaliacaoDAO.listarTodas();
        return todasAvaliacoes.stream()
                .filter(av -> av.getIdAluno() == idAluno)
                .toList();
    }
    
    public List<Avaliacao> listarAvaliacoesPorCurso(int idCurso) {
        List<Avaliacao> todasAvaliacoes = avaliacaoDAO.listarTodas();
        return todasAvaliacoes.stream()
                .filter(av -> av.getIdCurso() == idCurso)
                .toList();
    }
    
    public String obterConceito(double nota) {
        if (nota >= 9.0) return "Excelente";
        if (nota >= 8.0) return "Muito Bom";
        if (nota >= 7.0) return "Bom";
        if (nota >= 6.0) return "Regular";
        return "Insuficiente";
    }
}
