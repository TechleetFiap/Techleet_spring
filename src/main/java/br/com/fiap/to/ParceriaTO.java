package br.com.fiap.to;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ParceriaTO {

    private Long id;
    @NotBlank
    @Pattern(regexp = "INFLUENCER|MARCA")
    private String tipo;
    @NotBlank
    @Size(max = 100)
    private String nome;
    @Pattern(regexp = "ATIVA|INATIVA")
    private String status;
    @NotNull
    @PositiveOrZero
    private BigDecimal custoMensal;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public ParceriaTO() {
    }

    public ParceriaTO(Long id, String tipo, String nome, String status, BigDecimal custoMensal, LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.status = status;
        this.custoMensal = custoMensal;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getCustoMensal() {
        return custoMensal;
    }

    public void setCustoMensal(BigDecimal custoMensal) {
        this.custoMensal = custoMensal;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
