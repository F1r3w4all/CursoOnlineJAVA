package com.plataforma.gui;

import com.plataforma.controller.PlataformaController;
import com.plataforma.dao.AlunoDAO;
import com.plataforma.dao.CursoDAO;
import com.plataforma.dao.MatriculaDAO;
import com.plataforma.model.Aluno;
import com.plataforma.model.Curso;
import com.plataforma.model.Matricula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class MatriculaPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<Aluno> alunoCombo;
    private JComboBox<Curso> cursoCombo;
    private JTextField dataField;
    private JButton btnMatricular, btnExcluir, btnLimpar;
    private PlataformaController controller;
    private AlunoDAO alunoDAO;
    private CursoDAO cursoDAO;
    private int selectedRow = -1;
    
    public MatriculaPanel() {
        controller = new PlataformaController();
        alunoDAO = new AlunoDAO();
        cursoDAO = new CursoDAO();
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        carregarCombos();
        atualizarTabela();
    }
    
    private void initializeComponents() {
        // Combos
        alunoCombo = new JComboBox<>();
        cursoCombo = new JComboBox<>();
        dataField = new JTextField(10);
        dataField.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        // Botões
        btnMatricular = new JButton("Matricular");
        btnExcluir = new JButton("Excluir Matrícula");
        btnLimpar = new JButton("Limpar");
        
        btnExcluir.setEnabled(false);
        
        // Tabela
        String[] colunas = {"ID", "Aluno", "Curso", "Data Matrícula"};
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
        formPanel.setBorder(BorderFactory.createTitledBorder("Nova Matrícula"));
        
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
        
        // Data
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Data (dd/MM/yyyy):"), gbc);
        gbc.gridx = 1;
        formPanel.add(dataField, gbc);
        
        // Botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnMatricular);
        buttonPanel.add(btnExcluir);
        buttonPanel.add(btnLimpar);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        
        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    
    private void setupEventHandlers() {
        btnMatricular.addActionListener(e -> matricularAluno());
        btnExcluir.addActionListener(e -> excluirMatricula());
        btnLimpar.addActionListener(e -> limparCampos());
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = table.getSelectedRow();
                btnExcluir.setEnabled(selectedRow >= 0);
            }
        });
    }
    
    private void carregarCombos() {
        // Carregar alunos
        alunoCombo.removeAllItems();
        List<Aluno> alunos = controller.listarTodosAlunos();
        for (Aluno aluno : alunos) {
            alunoCombo.addItem(aluno);
        }
        
        // Carregar cursos
        cursoCombo.removeAllItems();
        List<Curso> cursos = controller.listarTodosCursos();
        for (Curso curso : cursos) {
            cursoCombo.addItem(curso);
        }
    }
    
    private void matricularAluno() {
        if (validarCampos()) {
            try {
                Aluno aluno = (Aluno) alunoCombo.getSelectedItem();
                Curso curso = (Curso) cursoCombo.getSelectedItem();
                LocalDate data = parseData(dataField.getText());
            
                // Verificar se já existe matrícula
                if (controller.verificarMatriculaExistente(aluno.getIdAluno(), curso.getIdCurso())) {
                    JOptionPane.showMessageDialog(this, "Este aluno já está matriculado neste curso!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            
                if (controller.realizarMatricula(aluno.getIdAluno(), curso.getIdCurso(), data)) {
                    JOptionPane.showMessageDialog(this, "Matrícula realizada com sucesso!");
                    limparCampos();
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao realizar matrícula!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void excluirMatricula() {
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Tem certeza que deseja excluir esta matrícula?", 
                "Confirmar Exclusão", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                int id = (Integer) tableModel.getValueAt(selectedRow, 0);
                if (controller.excluirMatricula(id)) {
                    JOptionPane.showMessageDialog(this, "Matrícula excluída com sucesso!");
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir matrícula!", "Erro", JOptionPane.ERROR_MESSAGE);
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
        dataField.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        table.clearSelection();
        selectedRow = -1;
        btnExcluir.setEnabled(false);
    }
    
    public void atualizarTabela() {
        tableModel.setRowCount(0);
        List<Matricula> matriculas = controller.listarTodasMatriculas();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        for (Matricula matricula : matriculas) {
            Object[] row = {
                matricula.getIdMatricula(),
                matricula.getNomeAluno(),
                matricula.getTituloCurso(),
                matricula.getDataMatricula().format(formatter)
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
