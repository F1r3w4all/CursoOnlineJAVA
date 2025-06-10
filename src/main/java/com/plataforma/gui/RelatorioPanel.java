package com.plataforma.gui;

import com.plataforma.controller.PlataformaController;
import com.plataforma.model.Aluno;
import com.plataforma.model.Avaliacao;
import com.plataforma.model.Curso;
import com.plataforma.model.Matricula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RelatorioPanel extends JPanel {
    private PlataformaController controller;
    private JComboBox<String> tipoRelatorioCombo;
    private JComboBox<Aluno> alunoCombo;
    private JComboBox<Curso> cursoCombo;
    private JButton btnGerarRelatorio;
    private JTable tabelaRelatorio;
    private DefaultTableModel modeloTabela;
    private JTextArea areaEstatisticas;
    
    public RelatorioPanel() {
        controller = new PlataformaController();
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        carregarCombos();
    }
    
    private void initializeComponents() {
        // ComboBox para tipo de relatório
        String[] tiposRelatorio = {
            "Selecione o tipo de relatório",
            "Relatório por Aluno",
            "Relatório por Curso",
            "Estatísticas Gerais",
            "Matrículas por Aluno",
            "Matrículas por Curso",
            "Avaliações por Aluno",
            "Avaliações por Curso"
        };
        tipoRelatorioCombo = new JComboBox<>(tiposRelatorio);
        
        // ComboBoxes para seleção
        alunoCombo = new JComboBox<>();
        cursoCombo = new JComboBox<>();
        
        // Botão para gerar relatório
        btnGerarRelatorio = new JButton("Gerar Relatório");
        
        // Tabela para exibir resultados
        modeloTabela = new DefaultTableModel();
        tabelaRelatorio = new JTable(modeloTabela);
        tabelaRelatorio.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        // Área para estatísticas
        areaEstatisticas = new JTextArea(8, 30);
        areaEstatisticas.setEditable(false);
        areaEstatisticas.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaEstatisticas.setBackground(new Color(245, 245, 245));
        
        // Inicialmente ocultar combos específicos
        alunoCombo.setVisible(false);
        cursoCombo.setVisible(false);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Painel superior - Controles
        JPanel painelControles = new JPanel(new GridBagLayout());
        painelControles.setBorder(BorderFactory.createTitledBorder("Configuração do Relatório"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Tipo de relatório
        gbc.gridx = 0; gbc.gridy = 0;
        painelControles.add(new JLabel("Tipo de Relatório:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        painelControles.add(tipoRelatorioCombo, gbc);
        
        // Aluno
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        JLabel labelAluno = new JLabel("Aluno:");
        painelControles.add(labelAluno, gbc);
        gbc.gridx = 1;
        painelControles.add(alunoCombo, gbc);
        
        // Curso
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel labelCurso = new JLabel("Curso:");
        painelControles.add(labelCurso, gbc);
        gbc.gridx = 1;
        painelControles.add(cursoCombo, gbc);
        
        // Botão
        gbc.gridx = 2; gbc.gridy = 1; gbc.gridheight = 2;
        painelControles.add(btnGerarRelatorio, gbc);
        
        add(painelControles, BorderLayout.NORTH);
        
        // Painel central - Tabela e Estatísticas
        JPanel painelCentral = new JPanel(new BorderLayout());
        
        // Tabela
        JScrollPane scrollTabela = new JScrollPane(tabelaRelatorio);
        scrollTabela.setBorder(BorderFactory.createTitledBorder("Resultados"));
        painelCentral.add(scrollTabela, BorderLayout.CENTER);
        
        // Estatísticas
        JScrollPane scrollEstatisticas = new JScrollPane(areaEstatisticas);
        scrollEstatisticas.setBorder(BorderFactory.createTitledBorder("Estatísticas"));
        scrollEstatisticas.setPreferredSize(new Dimension(0, 200));
        painelCentral.add(scrollEstatisticas, BorderLayout.SOUTH);
        
        add(painelCentral, BorderLayout.CENTER);
    }
    
    private void setupEventHandlers() {
        tipoRelatorioCombo.addActionListener(e -> {
            String tipoSelecionado = (String) tipoRelatorioCombo.getSelectedItem();
            atualizarVisibilidadeCombos(tipoSelecionado);
        });
        
        btnGerarRelatorio.addActionListener(e -> gerarRelatorio());
    }
    
    private void atualizarVisibilidadeCombos(String tipo) {
        boolean mostrarAluno = tipo.contains("Aluno") && !tipo.equals("Selecione o tipo de relatório");
        boolean mostrarCurso = tipo.contains("Curso") && !tipo.equals("Selecione o tipo de relatório");
        
        alunoCombo.setVisible(mostrarAluno);
        cursoCombo.setVisible(mostrarCurso);
        
        revalidate();
        repaint();
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
    
    private void gerarRelatorio() {
        String tipoRelatorio = (String) tipoRelatorioCombo.getSelectedItem();
        
        if (tipoRelatorio.equals("Selecione o tipo de relatório")) {
            JOptionPane.showMessageDialog(this, "Selecione um tipo de relatório!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            switch (tipoRelatorio) {
                case "Relatório por Aluno":
                    gerarRelatorioPorAluno();
                    break;
                case "Relatório por Curso":
                    gerarRelatorioPorCurso();
                    break;
                case "Estatísticas Gerais":
                    gerarEstatisticasGerais();
                    break;
                case "Matrículas por Aluno":
                    gerarMatriculasPorAluno();
                    break;
                case "Matrículas por Curso":
                    gerarMatriculasPorCurso();
                    break;
                case "Avaliações por Aluno":
                    gerarAvaliacoesPorAluno();
                    break;
                case "Avaliações por Curso":
                    gerarAvaliacoesPorCurso();
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar relatório: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void gerarRelatorioPorAluno() {
        if (alunoCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Aluno aluno = (Aluno) alunoCombo.getSelectedItem();
        
        // Configurar tabela
        String[] colunas = {"Curso", "Instrutor", "Data Matrícula", "Nota", "Conceito", "Feedback"};
        modeloTabela.setColumnIdentifiers(colunas);
        modeloTabela.setRowCount(0);
        
        // Buscar matrículas do aluno
        List<Matricula> matriculas = controller.listarMatriculasPorAluno(aluno.getIdAluno());
        List<Avaliacao> avaliacoes = controller.listarAvaliacoesPorAluno(aluno.getIdAluno());
        
        for (Matricula matricula : matriculas) {
            Curso curso = controller.buscarCursoPorId(matricula.getIdCurso());
            
            // Buscar avaliação correspondente
            Avaliacao avaliacao = avaliacoes.stream()
                    .filter(av -> av.getIdCurso() == matricula.getIdCurso())
                    .findFirst()
                    .orElse(null);
            
            Object[] linha = {
                curso != null ? curso.getTitulo() : "N/A",
                curso != null ? curso.getInstrutor() : "N/A",
                matricula.getDataMatricula().toString(),
                avaliacao != null ? String.format("%.2f", avaliacao.getNota()) : "Não avaliado",
                avaliacao != null ? controller.obterConceitoNota(avaliacao.getNota()) : "N/A",
                avaliacao != null ? avaliacao.getFeedback() : "N/A"
            };
            modeloTabela.addRow(linha);
        }
        
        // Gerar estatísticas
        double mediaAluno = controller.calcularMediaAluno(aluno.getIdAluno());
        StringBuilder stats = new StringBuilder();
        stats.append("=== RELATÓRIO DO ALUNO ===\n");
        stats.append("Nome: ").append(aluno.getNome()).append("\n");
        stats.append("CPF: ").append(aluno.getCpf()).append("\n");
        stats.append("Data de Cadastro: ").append(aluno.getDataCadastro()).append("\n\n");
        stats.append("=== ESTATÍSTICAS ===\n");
        stats.append("Total de Matrículas: ").append(matriculas.size()).append("\n");
        stats.append("Total de Avaliações: ").append(avaliacoes.size()).append("\n");
        stats.append("Média Geral: ").append(String.format("%.2f", mediaAluno)).append("\n");
        stats.append("Conceito Geral: ").append(controller.obterConceitoNota(mediaAluno)).append("\n");
        
        areaEstatisticas.setText(stats.toString());
    }
    
    private void gerarRelatorioPorCurso() {
        if (cursoCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um curso!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Curso curso = (Curso) cursoCombo.getSelectedItem();
        
        // Configurar tabela
        String[] colunas = {"Aluno", "CPF", "Data Matrícula", "Nota", "Conceito", "Feedback"};
        modeloTabela.setColumnIdentifiers(colunas);
        modeloTabela.setRowCount(0);
        
        // Buscar matrículas do curso
        List<Matricula> matriculas = controller.listarMatriculasPorCurso(curso.getIdCurso());
        List<Avaliacao> avaliacoes = controller.listarAvaliacoesPorCurso(curso.getIdCurso());
        
        for (Matricula matricula : matriculas) {
            Aluno aluno = controller.buscarAlunoPorId(matricula.getIdAluno());
            
            // Buscar avaliação correspondente
            Avaliacao avaliacao = avaliacoes.stream()
                    .filter(av -> av.getIdAluno() == matricula.getIdAluno())
                    .findFirst()
                    .orElse(null);
            
            Object[] linha = {
                aluno != null ? aluno.getNome() : "N/A",
                aluno != null ? aluno.getCpf() : "N/A",
                matricula.getDataMatricula().toString(),
                avaliacao != null ? String.format("%.2f", avaliacao.getNota()) : "Não avaliado",
                avaliacao != null ? controller.obterConceitoNota(avaliacao.getNota()) : "N/A",
                avaliacao != null ? avaliacao.getFeedback() : "N/A"
            };
            modeloTabela.addRow(linha);
        }
        
        // Gerar estatísticas
        double mediaCurso = controller.calcularMediaCurso(curso.getIdCurso());
        StringBuilder stats = new StringBuilder();
        stats.append("=== RELATÓRIO DO CURSO ===\n");
        stats.append("Título: ").append(curso.getTitulo()).append("\n");
        stats.append("Instrutor: ").append(curso.getInstrutor()).append("\n");
        stats.append("Carga Horária: ").append(curso.getCargaHoraria()).append("h\n\n");
        stats.append("=== ESTATÍSTICAS ===\n");
        stats.append("Total de Matrículas: ").append(matriculas.size()).append("\n");
        stats.append("Total de Avaliações: ").append(avaliacoes.size()).append("\n");
        stats.append("Média do Curso: ").append(String.format("%.2f", mediaCurso)).append("\n");
        stats.append("Conceito Médio: ").append(controller.obterConceitoNota(mediaCurso)).append("\n");
        
        areaEstatisticas.setText(stats.toString());
    }
    
    private void gerarEstatisticasGerais() {
        // Configurar tabela com resumo geral
        String[] colunas = {"Métrica", "Valor"};
        modeloTabela.setColumnIdentifiers(colunas);
        modeloTabela.setRowCount(0);
        
        int totalAlunos = controller.contarTotalAlunos();
        int totalCursos = controller.contarTotalCursos();
        int totalMatriculas = controller.contarTotalMatriculas();
        int totalAvaliacoes = controller.contarTotalAvaliacoes();
        double mediaGeral = controller.calcularMediaGeralNotas();
        
        Object[][] dados = {
            {"Total de Alunos", totalAlunos},
            {"Total de Cursos", totalCursos},
            {"Total de Matrículas", totalMatriculas},
            {"Total de Avaliações", totalAvaliacoes},
            {"Média Geral de Notas", String.format("%.2f", mediaGeral)},
            {"Conceito Geral", controller.obterConceitoNota(mediaGeral)}
        };
        
        for (Object[] linha : dados) {
            modeloTabela.addRow(linha);
        }
        
        // Estatísticas detalhadas
        StringBuilder stats = new StringBuilder();
        stats.append("=== ESTATÍSTICAS GERAIS DO SISTEMA ===\n\n");
        stats.append("📊 RESUMO GERAL:\n");
        stats.append("• Alunos cadastrados: ").append(totalAlunos).append("\n");
        stats.append("• Cursos disponíveis: ").append(totalCursos).append("\n");
        stats.append("• Matrículas realizadas: ").append(totalMatriculas).append("\n");
        stats.append("• Avaliações registradas: ").append(totalAvaliacoes).append("\n\n");
        
        stats.append("📈 DESEMPENHO:\n");
        stats.append("• Média geral: ").append(String.format("%.2f", mediaGeral)).append("\n");
        stats.append("• Conceito geral: ").append(controller.obterConceitoNota(mediaGeral)).append("\n");
        
        if (totalMatriculas > 0) {
            double taxaAvaliacao = (double) totalAvaliacoes / totalMatriculas * 100;
            stats.append("• Taxa de avaliação: ").append(String.format("%.1f%%", taxaAvaliacao)).append("\n");
        }
        
        if (totalAlunos > 0) {
            double mediaMatriculasPorAluno = (double) totalMatriculas / totalAlunos;
            stats.append("• Média de matrículas por aluno: ").append(String.format("%.1f", mediaMatriculasPorAluno)).append("\n");
        }
        
        areaEstatisticas.setText(stats.toString());
    }
    
    private void gerarMatriculasPorAluno() {
        if (alunoCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Aluno aluno = (Aluno) alunoCombo.getSelectedItem();
        
        // Configurar tabela
        String[] colunas = {"Curso", "Instrutor", "Carga Horária", "Data Matrícula"};
        modeloTabela.setColumnIdentifiers(colunas);
        modeloTabela.setRowCount(0);
        
        List<Matricula> matriculas = controller.listarMatriculasPorAluno(aluno.getIdAluno());
        
        for (Matricula matricula : matriculas) {
            Curso curso = controller.buscarCursoPorId(matricula.getIdCurso());
            
            Object[] linha = {
                curso != null ? curso.getTitulo() : "N/A",
                curso != null ? curso.getInstrutor() : "N/A",
                curso != null ? curso.getCargaHoraria() + "h" : "N/A",
                matricula.getDataMatricula().toString()
            };
            modeloTabela.addRow(linha);
        }
        
        // Estatísticas
        StringBuilder stats = new StringBuilder();
        stats.append("=== MATRÍCULAS DO ALUNO ===\n");
        stats.append("Aluno: ").append(aluno.getNome()).append("\n");
        stats.append("Total de Matrículas: ").append(matriculas.size()).append("\n");
        
        areaEstatisticas.setText(stats.toString());
    }
    
    private void gerarMatriculasPorCurso() {
        if (cursoCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um curso!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Curso curso = (Curso) cursoCombo.getSelectedItem();
        
        // Configurar tabela
        String[] colunas = {"Aluno", "CPF", "Data Cadastro", "Data Matrícula"};
        modeloTabela.setColumnIdentifiers(colunas);
        modeloTabela.setRowCount(0);
        
        List<Matricula> matriculas = controller.listarMatriculasPorCurso(curso.getIdCurso());
        
        for (Matricula matricula : matriculas) {
            Aluno aluno = controller.buscarAlunoPorId(matricula.getIdAluno());
            
            Object[] linha = {
                aluno != null ? aluno.getNome() : "N/A",
                aluno != null ? aluno.getCpf() : "N/A",
                aluno != null ? aluno.getDataCadastro().toString() : "N/A",
                matricula.getDataMatricula().toString()
            };
            modeloTabela.addRow(linha);
        }
        
        // Estatísticas
        StringBuilder stats = new StringBuilder();
        stats.append("=== MATRÍCULAS DO CURSO ===\n");
        stats.append("Curso: ").append(curso.getTitulo()).append("\n");
        stats.append("Instrutor: ").append(curso.getInstrutor()).append("\n");
        stats.append("Total de Matrículas: ").append(matriculas.size()).append("\n");
        
        areaEstatisticas.setText(stats.toString());
    }
    
    private void gerarAvaliacoesPorAluno() {
        if (alunoCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Aluno aluno = (Aluno) alunoCombo.getSelectedItem();
        
        // Configurar tabela
        String[] colunas = {"Curso", "Nota", "Conceito", "Feedback"};
        modeloTabela.setColumnIdentifiers(colunas);
        modeloTabela.setRowCount(0);
        
        List<Avaliacao> avaliacoes = controller.listarAvaliacoesPorAluno(aluno.getIdAluno());
        
        for (Avaliacao avaliacao : avaliacoes) {
            Curso curso = controller.buscarCursoPorId(avaliacao.getIdCurso());
            
            Object[] linha = {
                curso != null ? curso.getTitulo() : "N/A",
                String.format("%.2f", avaliacao.getNota()),
                controller.obterConceitoNota(avaliacao.getNota()),
                avaliacao.getFeedback()
            };
            modeloTabela.addRow(linha);
        }
        
        // Estatísticas
        double mediaAluno = controller.calcularMediaAluno(aluno.getIdAluno());
        StringBuilder stats = new StringBuilder();
        stats.append("=== AVALIAÇÕES DO ALUNO ===\n");
        stats.append("Aluno: ").append(aluno.getNome()).append("\n");
        stats.append("Total de Avaliações: ").append(avaliacoes.size()).append("\n");
        stats.append("Média: ").append(String.format("%.2f", mediaAluno)).append("\n");
        stats.append("Conceito: ").append(controller.obterConceitoNota(mediaAluno)).append("\n");
        
        areaEstatisticas.setText(stats.toString());
    }
    
    private void gerarAvaliacoesPorCurso() {
        if (cursoCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um curso!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Curso curso = (Curso) cursoCombo.getSelectedItem();
        
        // Configurar tabela
        String[] colunas = {"Aluno", "Nota", "Conceito", "Feedback"};
        modeloTabela.setColumnIdentifiers(colunas);
        modeloTabela.setRowCount(0);
        
        List<Avaliacao> avaliacoes = controller.listarAvaliacoesPorCurso(curso.getIdCurso());
        
        for (Avaliacao avaliacao : avaliacoes) {
            Aluno aluno = controller.buscarAlunoPorId(avaliacao.getIdAluno());
            
            Object[] linha = {
                aluno != null ? aluno.getNome() : "N/A",
                String.format("%.2f", avaliacao.getNota()),
                controller.obterConceitoNota(avaliacao.getNota()),
                avaliacao.getFeedback()
            };
            modeloTabela.addRow(linha);
        }
        
        // Estatísticas
        double mediaCurso = controller.calcularMediaCurso(curso.getIdCurso());
        StringBuilder stats = new StringBuilder();
        stats.append("=== AVALIAÇÕES DO CURSO ===\n");
        stats.append("Curso: ").append(curso.getTitulo()).append("\n");
        stats.append("Instrutor: ").append(curso.getInstrutor()).append("\n");
        stats.append("Total de Avaliações: ").append(avaliacoes.size()).append("\n");
        stats.append("Média: ").append(String.format("%.2f", mediaCurso)).append("\n");
        stats.append("Conceito: ").append(controller.obterConceitoNota(mediaCurso)).append("\n");
        
        areaEstatisticas.setText(stats.toString());
    }
    
    public void atualizarDados() {
        carregarCombos();
    }
}
