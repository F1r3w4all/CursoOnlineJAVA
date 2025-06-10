package com.plataforma.dao;

import com.plataforma.database.DatabaseConnection;
import com.plataforma.model.Matricula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {
    
    public boolean inserir(Matricula matricula) {
        String sql = "INSERT INTO matricula (id_aluno, id_curso, data_matricula) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, matricula.getIdAluno());
            stmt.setInt(2, matricula.getIdCurso());
            stmt.setDate(3, Date.valueOf(matricula.getDataMatricula()));
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir matrícula: " + e.getMessage());
            return false;
        }
    }
    
    public List<Matricula> listarTodas() {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = """
            SELECT m.*, a.nome as nome_aluno, c.titulo as titulo_curso 
            FROM matricula m 
            JOIN aluno a ON m.id_aluno = a.id_aluno 
            JOIN curso c ON m.id_curso = c.id_curso 
            ORDER BY m.data_matricula DESC
            """;
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Matricula matricula = new Matricula();
                matricula.setIdMatricula(rs.getInt("id_matricula"));
                matricula.setIdAluno(rs.getInt("id_aluno"));
                matricula.setIdCurso(rs.getInt("id_curso"));
                matricula.setDataMatricula(rs.getDate("data_matricula").toLocalDate());
                matricula.setNomeAluno(rs.getString("nome_aluno"));
                matricula.setTituloCurso(rs.getString("titulo_curso"));
                matriculas.add(matricula);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar matrículas: " + e.getMessage());
        }
        
        return matriculas;
    }
    
    public boolean excluir(int idMatricula) {
        String sql = "DELETE FROM matricula WHERE id_matricula = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idMatricula);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir matrícula: " + e.getMessage());
            return false;
        }
    }
    
    public boolean verificarMatriculaExistente(int idAluno, int idCurso) {
        String sql = "SELECT COUNT(*) FROM matricula WHERE id_aluno = ? AND id_curso = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idAluno);
            stmt.setInt(2, idCurso);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao verificar matrícula: " + e.getMessage());
        }
        
        return false;
    }
}
