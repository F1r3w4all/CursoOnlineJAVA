package com.plataforma.gui;

import com.plataforma.controller.PlataformaController;
import com.plataforma.model.Aluno;
import com.plataforma.model.Avaliacao;
import com.plataforma.model.Curso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AvaliacaoPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<Aluno> alunoCombo;
    private JComboBox<Curso> cursoCombo;
    private JTextField notaField;
    private JTextArea feedbackArea;
    private JButton btnAdicionar, btnAtualizar, btnExcluir, btnLimpar;
    private PlataformaController controller;
    private int selectedRow = -1;
    
    public AvaliacaoPanel() {
        controller = new PlataformaController();
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        carregarCombos();
        atualizarTabela();
    }
    
    private void initializeComponents() {
        // Combos e campos
        alunoCombo = new JComboBox<>();
        cursoCombo = new JComboBox<>();
        notaField = new JTextField(10);
        feedbackArea = new JTextArea(3, 30);
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);
        
        // Botões
        btnAdicionar = new JButton("Adicionar");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");
        
        btnAtualizar.setEnabled(false);
        btnExcluir.setEnabled(false);
        
        // Tabela
        String[] colunas = {"ID", "Aluno", "Curso", "Nota", "Feedback"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Painel de formulário
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Dados da Avaliação"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Aluno
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Aluno:"), gbc);
        gbc.gridx = 1;
        formPanel.add(alunoCombo, gbc);
        
        // Curso
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Curso:"), gbc);
        gbc.gridx = 1;
        formPanel.add(cursoCombo, gbc);
        
        // Nota
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Nota (0-10):"), gbc);
        gbc.gridx = 1;
        formPanel.add(notaField, gbc);
        
        // Feedback
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(new JLabel("Feedback:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        formPanel.add(new JScrollPane(feedbackArea), gbc);
        
        // Botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnAtualizar);
        buttonPanel.add(btnExcluir);
        buttonPanel.add(btnLimpar);
        
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);
        
        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    
    private void setupEventHandlers() {
        btnAdicionar.addActionListener(e -> adicionarAvaliacao());
        btnAtualizar.addActionListener(e -> atualizarAvaliacao());
        btnExcluir.addActionListener(e -> excluirAvaliacao());
        btnLimpar.addActionListener(e -> limparCampos());
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    preencherCampos();
                    btnAtualizar.setEnabled(true);
                    btnExcluir.setEnabled(true);
                } else {
                    btnAtualizar.setEnabled(false);
                    btnExcluir.setEnabled(false);
                }
            }
        });
    }
    
    private void carregarCombos() {
        // Carregar alunos
        alunoCombo.removeAllItems();
        List<Aluno> alunos = controller.listarAlunos();
        for (Aluno aluno : alunos) {
            alunoCombo.addItem(aluno);
        }
        
        // Carregar cursos
        cursoCombo.removeAllItems();
        List<Curso> cursos = controller.listarCursos();
        for (Curso curso : cursos) {
            cursoCombo.addItem(curso);
        }
    }
    
    private void adicionarAvaliacao() {
        if (validarCampos()) {
            try {
                Aluno aluno = (Aluno) alunoCombo.getSelectedItem();
                Curso curso = (Curso) cursoCombo.getSelectedItem();
                
                int idAluno = aluno.getIdAluno();
                int idCurso = curso.getIdCurso();
                double nota = Double.parseDouble(notaField.getText().trim());
                String feedback = feedbackArea.getText().trim();

                if (controller.registrarAvaliacao(idAluno, idCurso, nota, feedback)) {
                    JOptionPane.showMessageDialog(this, "Avaliação adicionada com sucesso!");
                    limparCampos();
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao adicionar avaliação!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void atualizarAvaliacao() {
        if (selectedRow >= 0 && validarCampos()) {
            try {
                int id = (Integer) tableModel.getValueAt(selectedRow, 0);
                double nota = Double.parseDouble(notaField.getText().trim());
                String feedback = feedbackArea.getText().trim();
                
                if (controller.atualizarAvaliacao(id, nota, feedback)) {
                    JOptionPane.showMessageDialog(this, "Avaliação atualizada com sucesso!");
                    limparCampos();
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar avaliação!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void excluirAvaliacao() {
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Tem certeza que deseja excluir esta avaliação?", 
                "Confirmar Exclusão", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                int id = (Integer) tableModel.getValueAt(selectedRow, 0);
                if (controller.excluirAvaliacao(id)) {
                    JOptionPane.showMessageDialog(this, "Avaliação excluída com sucesso!");
                    limparCampos();
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir avaliação!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private void limparCampos() {
        if (alunoCombo.getItemCount() > 0) {
            alunoCombo.setSelectedIndex(0);
        }
        if (cursoCombo.getItemCount() > 0) {
            cursoCombo.setSelectedIndex(0);
        }
        notaField.setText("");
        feedbackArea.setText("");
        table.clearSelection();
        selectedRow = -1;
        btnAtualizar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }
    
    private void preencherCampos() {
        if (selectedRow >= 0) {
            notaField.setText(tableModel.getValueAt(selectedRow, 3).toString());
            feedbackArea.setText((String) tableModel.getValueAt(selectedRow, 4));
        }
    }
    
    public void atualizarTabela() {
        tableModel.setRowCount(0);
        List<Avaliacao> avaliacoes = controller.listarAvaliacoes();
        
        for (Avaliacao avaliacao : avaliacoes) {
            Object[] row = {
                avaliacao.getIdAvaliacao(),
                avaliacao.getNomeAluno(),
                avaliacao.getTituloCurso(),
                String.format("%.2f", avaliacao.getNota()),
                avaliacao.getFeedback()
            };
            tableModel.addRow(row);
        }
        
        // Atualizar combos também
        carregarCombos();
    }
    
    private boolean validarCampos() {
        if (alunoCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (cursoCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um curso!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (notaField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nota é obrigatória!", "Erro", JOptionPane.ERROR_MESSAGE);
            notaField.requestFocus();
            return false;
        }
        
        try {
            double nota = Double.parseDouble(notaField.getText().trim());
            if (nota < 0 || nota > 10) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nota deve ser um número entre 0 e 10!", "Erro", JOptionPane.ERROR_MESSAGE);
            notaField.requestFocus();
            return false;
        }
        
        return true;
    }
}
