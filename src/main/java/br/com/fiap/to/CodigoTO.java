package br.com.fiap.to;

import java.time.LocalDate;

public class CodigoTO {

    private int id;
    private String codigoResgate;
    private LocalDate dataValidade;
    private String status;
    private LocalDate dataResgate;
    private int idItem;
    private int idParceria;

    public CodigoTO() {
    }

    public CodigoTO(int id, String codigoResgate, LocalDate dataValidade, String status, LocalDate dataResgate, int idItem, int idParceria) {
        this.id = id;
        this.codigoResgate = codigoResgate;
        this.dataValidade = dataValidade;
        this.status = status;
        this.dataResgate = dataResgate;
        this.idItem = idItem;
        this.idParceria = idParceria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoResgate() {
        return codigoResgate;
    }

    public void setCodigoResgate(String codigoResgate) {
        this.codigoResgate = codigoResgate;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataResgate() {
        return dataResgate;
    }

    public void setDataResgate(LocalDate dataResgate) {
        this.dataResgate = dataResgate;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdParceria() {
        return idParceria;
    }

    public void setIdParceria(int idParceria) {
        this.idParceria = idParceria;
    }
}
