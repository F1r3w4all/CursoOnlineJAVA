package com.plataforma;

import com.plataforma.gui.MainFrame;
import com.plataforma.database.DatabaseConnection;
import com.plataforma.controller.PlataformaController;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Configurar Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Erro ao configurar Look and Feel: " + e.getMessage());
        }
        
        // Exibir informações da arquitetura
        System.out.println("=== PLATAFORMA DE CURSOS ONLINE ===");

        System.out.println("=====================================");
        
        // Testar conexão com banco
        SwingUtilities.invokeLater(() -> {
            try {
                Connection conn = DatabaseConnection.getConnection();
                if (conn != null && !conn.isClosed()) {
                    System.out.println(" Conexão com banco de dados estabelecida!");
                    
                    // Testar controller
                    PlataformaController controller = new PlataformaController();
                    System.out.println(" Controller inicializado com sucesso!");
                    System.out.println(" Total de alunos: " + controller.contarTotalAlunos());
                    System.out.println(" Total de cursos: " + controller.contarTotalCursos());
                    System.out.println(" Total de matrículas: " + controller.contarTotalMatriculas());
                    System.out.println(" Total de avaliações: " + controller.contarTotalAvaliacoes());
                    
                    // Iniciar aplicação
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                    
                    System.out.println(" Aplicação iniciada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, 
                        " Erro ao conectar com o banco de dados!\n" +
                        "Verifique se o MySQL está rodando e as configurações estão corretas.", 
                        "Erro de Conexão", 
                        JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                    " Erro ao inicializar aplicação: " + e.getMessage(),
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                System.exit(1);
            }
        });
    }
}
