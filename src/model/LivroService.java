package service;

import model.DatabaseConnection;
import model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroService {

    // Método para cadastrar um livro
    public void cadastrarLivro(Livro livro) throws SQLException {
        String sql = "INSERT INTO livros (titulo, autor, editora, ano_publicacao, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAnoPublicacao());
            stmt.setString(5, livro.getStatus());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar o livro: " + e.getMessage());
        }
    }

    // Método para atualizar um livro
    public void atualizarLivro(Livro livro) throws SQLException {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, editora = ?, ano_publicacao = ?, status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAnoPublicacao());
            stmt.setString(5, livro.getStatus());
            stmt.setInt(6, livro.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar o livro: " + e.getMessage());
        }
    }

    // Método para excluir um livro
    public void excluirLivro(int id) throws SQLException {
        String sql = "DELETE FROM livros WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir o livro: " + e.getMessage());
        }
    }

    // Método para consultar todos os livros
    public List<Livro> consultarLivros() throws SQLException {
        String sql = "SELECT * FROM livros";
        List<Livro> livros = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setEditora(rs.getString("editora"));
                livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
                livro.setStatus(rs.getString("status"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao consultar os livros: " + e.getMessage());
        }

        return livros;
    }
}
