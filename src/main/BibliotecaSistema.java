package main;

import model.Livro;
import service.LivroService;

import java.sql.SQLException;
import java.util.List;

public class BibliotecaSistema {
    public static void main(String[] args) {
        LivroService livroService = new LivroService();

        // Cadastrar um livro
        Livro livro = new Livro();
        livro.setTitulo("Java Programming");
        livro.setAutor("John Doe");
        livro.setEditora("Tech Books");
        livro.setAnoPublicacao(2023);
        livro.setStatus("Disponível");

        try {
            livroService.cadastrarLivro(livro);
            System.out.println("Livro cadastrado com sucesso!");

            // Atualizar um livro
            livro.setId(1); // Suponha que o ID do livro cadastrado seja 1
            livro.setTitulo("Advanced Java Programming");
            livroService.atualizarLivro(livro);
            System.out.println("Livro atualizado com sucesso!");

            // Consultar livros
            List<Livro> livros = livroService.consultarLivros();
            for (Livro l : livros) {
                System.out.println("ID: " + l.getId() + ", Título: " + l.getTitulo());
            }

            // Excluir um livro
            livroService.excluirLivro(1); // Suponha que o ID do livro a ser excluído seja 1
            System.out.println("Livro excluído com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
