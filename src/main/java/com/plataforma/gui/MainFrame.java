package com.plataforma.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private AlunoPanel alunoPanel;
    private CursoPanel cursoPanel;
    private MatriculaPanel matriculaPanel;
    private AvaliacaoPanel avaliacaoPanel;
    private RelatorioPanel relatorioPanel;
    
    public MainFrame() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }
    
    private void initializeComponents() {
        setTitle("Plataforma de Cursos Online - Sistema Completo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        // Criar painéis
        alunoPanel = new AlunoPanel();
        cursoPanel = new CursoPanel();
        matriculaPanel = new MatriculaPanel();
        avaliacaoPanel = new AvaliacaoPanel();
        relatorioPanel = new RelatorioPanel();
        
        // Criar abas
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab(" Alunos", alunoPanel);
        tabbedPane.addTab(" Cursos", cursoPanel);
        tabbedPane.addTab(" Matrículas", matriculaPanel);
        tabbedPane.addTab(" Avaliações", avaliacaoPanel);
        tabbedPane.addTab(" Relatórios", relatorioPanel);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52, 152, 219));
        headerPanel.setPreferredSize(new Dimension(0, 70));
        
        JLabel titleLabel = new JLabel("PLATAFORMA DE CURSOS ONLINE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel subtitleLabel = new JLabel("Sistema de Gestão Acadêmica");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(subtitleLabel, BorderLayout.SOUTH);
        
        add(headerPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
        
        // Footer
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(44, 62, 80));
        footerPanel.setPreferredSize(new Dimension(0, 35));
        
        JLabel footerLabel = new JLabel("Sistema de Gestão de Cursos Ambiente de Dados PROF. Pedro Pinheiro");
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);
    }
    
    private void setupEventHandlers() {
        // Atualizar dados quando trocar de aba
        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            switch (selectedIndex) {
                case 0 -> alunoPanel.atualizarTabela();
                case 1 -> cursoPanel.atualizarTabela();
                case 2 -> matriculaPanel.atualizarTabela();
                case 3 -> avaliacaoPanel.atualizarTabela();
                case 4 -> relatorioPanel.atualizarDados();
            }
        });
    }
}
