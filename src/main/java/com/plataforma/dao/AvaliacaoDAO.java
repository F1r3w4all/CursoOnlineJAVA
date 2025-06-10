package com.plataforma.dao;

import com.plataforma.database.DatabaseConnection;
import com.plataforma.model.Avaliacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDAO {
    
    public boolean inserir(Avaliacao avaliacao) {
        String sql = "INSERT INTO avaliacao (id_aluno, id_curso, nota, feedback) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, avaliacao.getIdAluno());
            stmt.setInt(2, avaliacao.getIdCurso());
            stmt.setDouble(3, avaliacao.getNota());
            stmt.setString(4, avaliacao.getFeedback());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir avaliação: " + e.getMessage());
            return false;
        }
    }
    
    public List<Avaliacao> listarTodas() {
        List<Avaliacao> avaliacoes = new ArrayList<>();
        String sql = """
            SELECT av.*, a.nome as nome_aluno, c.titulo as titulo_curso 
            FROM avaliacao av 
            JOIN aluno a ON av.id_aluno = a.id_aluno 
            JOIN curso c ON av.id_curso = c.id_curso 
            ORDER BY av.nota DESC
            """;
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Avaliacao avaliacao = new Avaliacao();
                avaliacao.setIdAvaliacao(rs.getInt("id_avaliacao"));
                avaliacao.setIdAluno(rs.getInt("id_aluno"));
                avaliacao.setIdCurso(rs.getInt("id_curso"));
                avaliacao.setNota(rs.getDouble("nota"));
                avaliacao.setFeedback(rs.getString("feedback"));
                avaliacao.setNomeAluno(rs.getString("nome_aluno"));
                avaliacao.setTituloCurso(rs.getString("titulo_curso"));
                avaliacoes.add(avaliacao);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar avaliações: " + e.getMessage());
        }
        
        return avaliacoes;
    }
    
    public boolean atualizar(Avaliacao avaliacao) {
        String sql = "UPDATE avaliacao SET nota = ?, feedback = ? WHERE id_avaliacao = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDouble(1, avaliacao.getNota());
            stmt.setString(2, avaliacao.getFeedback());
            stmt.setInt(3, avaliacao.getIdAvaliacao());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar avaliação: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluir(int idAvaliacao) {
        String sql = "DELETE FROM avaliacao WHERE id_avaliacao = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idAvaliacao);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir avaliação: " + e.getMessage());
            return false;
        }
    }
}
