package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CodigoTO {

    private Long id;
    @NotBlank
    @Size(max = 20)
    private String codigoResgate;
    @NotNull
    private LocalDate dataValidade;
    @Pattern(regexp = "DISPONIVEL|RESGATADO")
    private String status;
    private LocalDate dataResgate;
    @NotNull
    private Long idItem;
    @NotNull
    private Long idParceria;

    public CodigoTO() {
    }

    public CodigoTO(Long id, String codigoResgate, LocalDate dataValidade, String status, LocalDate dataResgate, Long idItem, Long idParceria) {
        this.id = id;
        this.codigoResgate = codigoResgate;
        this.dataValidade = dataValidade;
        this.status = status;
        this.dataResgate = dataResgate;
        this.idItem = idItem;
        this.idParceria = idParceria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public Long getIdParceria() {
        return idParceria;
    }

    public void setIdParceria(Long idParceria) {
        this.idParceria = idParceria;
    }
}
