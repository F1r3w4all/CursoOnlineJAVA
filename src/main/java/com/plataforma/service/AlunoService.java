package com.plataforma.service;

import com.plataforma.dao.AlunoDAO;
import com.plataforma.model.Aluno;
import java.time.LocalDate;
import java.util.List;

public class AlunoService {
    private AlunoDAO alunoDAO;
    
    public AlunoService() {
        this.alunoDAO = new AlunoDAO();
    }
    
    public boolean cadastrarAluno(String nome, String cpf, LocalDate dataCadastro) {
        // Validações de negócio
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        
        if (cpf == null || !validarCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }
        
        if (dataCadastro.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de cadastro não pode ser futura");
        }
        
        Aluno aluno = new Aluno(nome.trim(), cpf, dataCadastro);
        return alunoDAO.inserir(aluno);
    }
    
    public List<Aluno> listarTodosAlunos() {
        return alunoDAO.listarTodos();
    }
    
    public boolean atualizarAluno(int id, String nome, String cpf, LocalDate dataCadastro) {
        Aluno aluno = alunoDAO.buscarPorId(id);
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não encontrado");
        }
        
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        
        if (cpf == null || !validarCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }
        
        if (dataCadastro.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de cadastro não pode ser futura");
        }
        
        aluno.setNome(nome.trim());
        aluno.setCpf(cpf);
        aluno.setDataCadastro(dataCadastro);
        
        return alunoDAO.atualizar(aluno);
    }
    
    public boolean excluirAluno(int id) {
        Aluno aluno = alunoDAO.buscarPorId(id);
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não encontrado");
        }
        
        return alunoDAO.excluir(id);
    }
    
    public Aluno buscarAlunoPorId(int id) {
        return alunoDAO.buscarPorId(id);
    }
    
    private boolean validarCPF(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");
        
        // Verifica se tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }
        
        // Verifica se não são todos os dígitos iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        
        // Validação básica - em produção usar algoritmo completo
        return true;
    }
}
