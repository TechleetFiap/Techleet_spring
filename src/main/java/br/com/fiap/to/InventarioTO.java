package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class InventarioTO {
    private Long id;
    @NotNull
    private Long idUsuario;
    @NotNull
    private Long idItem;
    @NotBlank
    @Pattern(regexp = "COMPRA|RESGATE")
    private String origem;
    private LocalDate dataAquisicao;

    public InventarioTO() {
    }

    public InventarioTO(Long id, Long idUsuario, Long idItem, String origem, LocalDate dataAquisicao) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idItem = idItem;
        this.origem = origem;
        this.dataAquisicao = dataAquisicao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
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
