package com.plataforma.dao;

import com.plataforma.database.DatabaseConnection;
import com.plataforma.model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    
    public boolean inserir(Curso curso) {
        String sql = "INSERT INTO curso (titulo, carga_horaria, instrutor) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, curso.getTitulo());
            stmt.setInt(2, curso.getCargaHoraria());
            stmt.setString(3, curso.getInstrutor());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir curso: " + e.getMessage());
            return false;
        }
    }
    
    public List<Curso> listarTodos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso ORDER BY titulo";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setTitulo(rs.getString("titulo"));
                curso.setCargaHoraria(rs.getInt("carga_horaria"));
                curso.setInstrutor(rs.getString("instrutor"));
                cursos.add(curso);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar cursos: " + e.getMessage());
        }
        
        return cursos;
    }
    
    public boolean atualizar(Curso curso) {
        String sql = "UPDATE curso SET titulo = ?, carga_horaria = ?, instrutor = ? WHERE id_curso = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, curso.getTitulo());
            stmt.setInt(2, curso.getCargaHoraria());
            stmt.setString(3, curso.getInstrutor());
            stmt.setInt(4, curso.getIdCurso());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar curso: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluir(int idCurso) {
        String sql = "DELETE FROM curso WHERE id_curso = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idCurso);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir curso: " + e.getMessage());
            return false;
        }
    }
    
    public Curso buscarPorId(int idCurso) {
        String sql = "SELECT * FROM curso WHERE id_curso = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idCurso);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setTitulo(rs.getString("titulo"));
                curso.setCargaHoraria(rs.getInt("carga_horaria"));
                curso.setInstrutor(rs.getString("instrutor"));
                return curso;
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar curso: " + e.getMessage());
        }
        
        return null;
    }
}
