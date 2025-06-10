package com.plataforma.dao;

import com.plataforma.database.DatabaseConnection;
import com.plataforma.model.Aluno;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    
    public boolean inserir(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, cpf, data_cadastro) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setDate(3, Date.valueOf(aluno.getDataCadastro()));
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir aluno: " + e.getMessage());
            return false;
        }
    }
    
    public List<Aluno> listarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno ORDER BY nome";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setIdAluno(rs.getInt("id_aluno"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setDataCadastro(rs.getDate("data_cadastro").toLocalDate());
                alunos.add(aluno);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar alunos: " + e.getMessage());
        }
        
        return alunos;
    }
    
    public boolean atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, cpf = ?, data_cadastro = ? WHERE id_aluno = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setDate(3, Date.valueOf(aluno.getDataCadastro()));
            stmt.setInt(4, aluno.getIdAluno());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluir(int idAluno) {
        String sql = "DELETE FROM aluno WHERE id_aluno = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idAluno);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir aluno: " + e.getMessage());
            return false;
        }
    }
    
    public Aluno buscarPorId(int idAluno) {
        String sql = "SELECT * FROM aluno WHERE id_aluno = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idAluno);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setIdAluno(rs.getInt("id_aluno"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setDataCadastro(rs.getDate("data_cadastro").toLocalDate());
                return aluno;
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar aluno: " + e.getMessage());
        }
        
        return null;
    }
}
