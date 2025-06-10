package com.plataforma.gui;

import com.plataforma.controller.PlataformaController;
import com.plataforma.model.Aluno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class AlunoPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nomeField, cpfField, dataField;
    private JButton btnAdicionar, btnAtualizar, btnExcluir, btnLimpar;
    private PlataformaController controller;
    private int selectedRow = -1;
    
    public AlunoPanel() {
        controller = new PlataformaController();
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        atualizarTabela();
    }
    
    private void initializeComponents() {
        // Campos de entrada
        nomeField = new JTextField(20);
        cpfField = new JTextField(15);
        dataField = new JTextField(10);
        dataField.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        // Botões
        btnAdicionar = new JButton("Adicionar");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");
        
        btnAtualizar.setEnabled(false);
        btnExcluir.setEnabled(false);
        
        // Tabela
        String[] colunas = {"ID", "Nome", "CPF", "Data Cadastro"};
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
        formPanel.setBorder(BorderFactory.createTitledBorder("Dados do Aluno"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Nome
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        formPanel.add(nomeField, gbc);
        
        // CPF
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("CPF:"), gbc);
        gbc.gridx = 1;
        formPanel.add(cpfField, gbc);
        
        // Data
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Data (dd/MM/yyyy):"), gbc);
        gbc.gridx = 1;
        formPanel.add(dataField, gbc);
        
        // Botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnAtualizar);
        buttonPanel.add(btnExcluir);
        buttonPanel.add(btnLimpar);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        
        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    
    private void setupEventHandlers() {
        btnAdicionar.addActionListener(e -> adicionarAluno());
        btnAtualizar.addActionListener(e -> atualizarAluno());
        btnExcluir.addActionListener(e -> excluirAluno());
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
    
    private void adicionarAluno() {
        if (validarCampos()) {
            try {
                String nome = nomeField.getText().trim();
                String cpf = cpfField.getText().trim();
                LocalDate data = parseData(dataField.getText());
            
                if (controller.cadastrarAluno(nome, cpf, data)) {
                    JOptionPane.showMessageDialog(this, "Aluno adicionado com sucesso!");
                    limparCampos();
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao adicionar aluno!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void atualizarAluno() {
        if (selectedRow >= 0 && validarCampos()) {
            try {
                int id = (Integer) tableModel.getValueAt(selectedRow, 0);
                String nome = nomeField.getText().trim();
                String cpf = cpfField.getText().trim();
                LocalDate data = parseData(dataField.getText());
            
                if (controller.atualizarAluno(id, nome, cpf, data)) {
                    JOptionPane.showMessageDialog(this, "Aluno atualizado com sucesso!");
                    limparCampos();
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar aluno!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void excluirAluno() {
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Tem certeza que deseja excluir este aluno?", 
                "Confirmar Exclusão", 
                JOptionPane.YES_NO_OPTION);
        
            if (confirm == JOptionPane.YES_OPTION) {
                int id = (Integer) tableModel.getValueAt(selectedRow, 0);
                if (controller.excluirAluno(id)) {
                    JOptionPane.showMessageDialog(this, "Aluno excluído com sucesso!");
                    limparCampos();
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir aluno!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private void limparCampos() {
        nomeField.setText("");
        cpfField.setText("");
        dataField.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        table.clearSelection();
        selectedRow = -1;
        btnAtualizar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }
    
    private void preencherCampos() {
        if (selectedRow >= 0) {
            nomeField.setText((String) tableModel.getValueAt(selectedRow, 1));
            cpfField.setText((String) tableModel.getValueAt(selectedRow, 2));
            dataField.setText((String) tableModel.getValueAt(selectedRow, 3));
        }
    }
    
    public void atualizarTabela() {
        tableModel.setRowCount(0);
        List<Aluno> alunos = controller.listarAlunos();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        for (Aluno aluno : alunos) {
            Object[] row = {
                aluno.getIdAluno(),
                aluno.getNome(),
                aluno.getCpf(),
                aluno.getDataCadastro().format(formatter)
            };
            tableModel.addRow(row);
        }
    }
    
    private boolean validarCampos() {
        if (nomeField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            nomeField.requestFocus();
            return false;
        }
        
        if (cpfField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "CPF é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            cpfField.requestFocus();
            return false;
        }
        
        try {
            parseData(dataField.getText());
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Data inválida! Use o formato dd/MM/yyyy", "Erro", JOptionPane.ERROR_MESSAGE);
            dataField.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private LocalDate parseData(String dataStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dataStr, formatter);
    }
}
