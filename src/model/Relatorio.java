package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Relatorio extends JFrame {

    // Variáveis para conexão com o banco de dados
    private final String url = "jdbc:mysql://localhost:3306/biblioteca_db";
    private final String user = "root";
    private final String password = "qwerqwer";

    private JTable jTable1;
    private JButton bntGerarRelatório;

    public Relatorio() {
        initComponents();
        carregarRelatorio();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Relatório de Empréstimos");

        // Tabela para exibir os dados
        jTable1 = new JTable();
        jTable1.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Código do Livro", "ID Autor", "Data Empréstimo", "Data Devolução"}
        ));
        JScrollPane jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(jTable1);

        // Botão para gerar relatório
        bntGerarRelatório = new JButton("Gerar Relatório");
        bntGerarRelatório.addActionListener((ActionEvent evt) -> {
            bntGerarRelatórioActionPerformed(evt);
        });

        // Painel principal para organizar os componentes
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(jScrollPane1, BorderLayout.CENTER);
        jPanel1.add(bntGerarRelatório, BorderLayout.SOUTH);

        getContentPane().add(jPanel1, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    // Método para carregar dados do banco de dados e preencher a tabela
    private void carregarRelatorio() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT codigo_livro, id_autor, data_emprestimo, data_devolucao FROM emprestimo";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Limpa a tabela antes de adicionar novos dados
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            // Preenche a tabela com os dados do ResultSet
            while (rs.next()) {
                String codigoLivro = rs.getString("codigo_livro");
                String idAutor = rs.getString("id_autor");
                String dataEmprestimo = rs.getString("data_emprestimo");
                String dataDevolucao = rs.getString("data_devolucao");

                model.addRow(new Object[]{codigoLivro, idAutor, dataEmprestimo, dataDevolucao});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar relatório: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Fechar recursos
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // Método para lidar com o evento do botão "Gerar Relatório"
    private void bntGerarRelatórioActionPerformed(ActionEvent evt) {
        carregarRelatorio();
    }

    public static void main(String args[]) {
        // Definindo o Look and Feel
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Relatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Criando e exibindo a interface
        java.awt.EventQueue.invokeLater(() -> {
            new Relatorio().setVisible(true);
        });
    }
}
