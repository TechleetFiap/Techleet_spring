package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class MissaoUsuarioTO {

    private Long id;
    @Pattern(regexp = "DISPONIVEL|EXPIRADA")
    private String status;
    private LocalDate dataRealizacao;
    @NotNull
    private Long idUsuario;
    @NotNull
    private Long idMissao;
    private LocalDate dataInicio;
    @NotNull
    private LocalDate dataFim;

    public MissaoUsuarioTO() {
    }

    public MissaoUsuarioTO(Long id, String status, LocalDate dataRealizacao, Long idUsuario, Long idMissao, LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.status = status;
        this.dataRealizacao = dataRealizacao;
        this.idUsuario = idUsuario;
        this.idMissao = idMissao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(LocalDate dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdMissao() {
        return idMissao;
    }

    public void setIdMissao(Long idMissao) {
        this.idMissao = idMissao;
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
