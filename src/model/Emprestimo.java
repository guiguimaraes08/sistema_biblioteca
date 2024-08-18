package model;

import java.util.Date;

public class Emprestimo {
    private int id;
    private int idUsuario;
    private int codigoLivro;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private String status;
    private double multa;

    // Construtores
    public Emprestimo() {}

    public Emprestimo(int id, int idUsuario, int codigoLivro, Date dataEmprestimo, Date dataDevolucao, String status, double multa) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.codigoLivro = codigoLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
        this.multa = multa;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getCodigoLivro() {
        return codigoLivro;
    }

    public void setCodigoLivro(int codigoLivro) {
        this.codigoLivro = codigoLivro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    // Métodos adicionais podem ser implementados posteriormente
}
