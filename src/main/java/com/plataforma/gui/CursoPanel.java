package com.plataforma.gui;

import com.plataforma.controller.PlataformaController;
import com.plataforma.model.Curso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CursoPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField tituloField, cargaHorariaField, instrutorField;
    private JButton btnAdicionar, btnAtualizar, btnExcluir, btnLimpar;
    private PlataformaController controller;
    private int selectedRow = -1;
    
    public CursoPanel() {
        controller = new PlataformaController();
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        atualizarTabela();
    }
    
    private void initializeComponents() {
        // Campos de entrada
        tituloField = new JTextField(25);
        cargaHorariaField = new JTextField(10);
        instrutorField = new JTextField(20);
        
        // Botões
        btnAdicionar = new JButton("Adicionar");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");
        
        btnAtualizar.setEnabled(false);
        btnExcluir.setEnabled(false);
        
        // Tabela
        String[] colunas = {"ID", "Título", "Carga Horária", "Instrutor"};
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
        formPanel.setBorder(BorderFactory.createTitledBorder("Dados do Curso"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Título
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        formPanel.add(tituloField, gbc);
        
        // Carga Horária
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Carga Horária:"), gbc);
        gbc.gridx = 1;
        formPanel.add(cargaHorariaField, gbc);
        
        // Instrutor
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Instrutor:"), gbc);
        gbc.gridx = 1;
        formPanel.add(instrutorField, gbc);
        
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
        btnAdicionar.addActionListener(e -> adicionarCurso());
        btnAtualizar.addActionListener(e -> atualizarCurso());
        btnExcluir.addActionListener(e -> excluirCurso());
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
    
    private void adicionarCurso() {
        if (validarCampos()) {
            try {
                String titulo = tituloField.getText().trim();
                int cargaHoraria = Integer.parseInt(cargaHorariaField.getText().trim());
                String instrutor = instrutorField.getText().trim();

                if (controller.cadastrarCurso(titulo, cargaHoraria, instrutor)) {
                    JOptionPane.showMessageDialog(this, "Curso adicionado com sucesso!");
                    limparCampos();
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao adicionar curso!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void atualizarCurso() {
        if (selectedRow >= 0 && validarCampos()) {
            try {
                int id = (Integer) tableModel.getValueAt(selectedRow, 0);
                String titulo = tituloField.getText().trim();
                int cargaHoraria = Integer.parseInt(cargaHorariaField.getText().trim());
                String instrutor = instrutorField.getText().trim();

                if (controller.atualizarCurso(id, titulo, cargaHoraria, instrutor)) {
                    JOptionPane.showMessageDialog(this, "Curso atualizado com sucesso!");
                    limparCampos();
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar curso!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void excluirCurso() {
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Tem certeza que deseja excluir este curso?", 
                "Confirmar Exclusão", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                int id = (Integer) tableModel.getValueAt(selectedRow, 0);
                if (controller.excluirCurso(id)) {
                    JOptionPane.showMessageDialog(this, "Curso excluído com sucesso!");
                    limparCampos();
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir curso!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private void limparCampos() {
        tituloField.setText("");
        cargaHorariaField.setText("");
        instrutorField.setText("");
        table.clearSelection();
        selectedRow = -1;
        btnAtualizar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }
    
    private void preencherCampos() {
        if (selectedRow >= 0) {
            tituloField.setText((String) tableModel.getValueAt(selectedRow, 1));
            cargaHorariaField.setText(tableModel.getValueAt(selectedRow, 2).toString());
            instrutorField.setText((String) tableModel.getValueAt(selectedRow, 3));
        }
    }
    
    public void atualizarTabela() {
        tableModel.setRowCount(0);
        List<Curso> cursos = controller.listarCursos();
        
        for (Curso curso : cursos) {
            Object[] row = {
                curso.getIdCurso(),
                curso.getTitulo(),
                curso.getCargaHoraria(),
                curso.getInstrutor()
            };
            tableModel.addRow(row);
        }
    }
    
    private boolean validarCampos() {
        if (tituloField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Título é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            tituloField.requestFocus();
            return false;
        }
        
        if (cargaHorariaField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Carga horária é obrigatória!", "Erro", JOptionPane.ERROR_MESSAGE);
            cargaHorariaField.requestFocus();
            return false;
        }
        
        try {
            int carga = Integer.parseInt(cargaHorariaField.getText().trim());
            if (carga <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Carga horária deve ser um número positivo!", "Erro", JOptionPane.ERROR_MESSAGE);
            cargaHorariaField.requestFocus();
            return false;
        }
        
        if (instrutorField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Instrutor é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            instrutorField.requestFocus();
            return false;
        }
        
        return true;
    }
}
