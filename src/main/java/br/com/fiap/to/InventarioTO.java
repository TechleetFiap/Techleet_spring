package br.com.fiap.to;

import java.time.LocalDate;

public class InventarioTO {
    private int id;
    private int idUsuario;
    private int idItem;
    private String origem;
    private LocalDate dataAquisicao;

    public InventarioTO() {
    }

    public InventarioTO(int id, int idUsuario, int idItem, String origem, LocalDate dataAquisicao) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idItem = idItem;
        this.origem = origem;
        this.dataAquisicao = dataAquisicao;
    }

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

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }
}
