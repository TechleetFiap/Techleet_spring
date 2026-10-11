package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class ItemTO {
    private Long id;
    @NotBlank
    @Size(max = 80)
    private String nome;
    @NotBlank
    @Pattern(regexp = "CABELO|ROUPA DE CIMA INTERNA|ROUPA DE CIMA EXTERNA|ROUPA DE BAIXO|CALCADO|ACESSORIO")
    private String modelo;
    @PositiveOrZero
    private Integer valorPontos;
    @NotBlank
    @Pattern(regexp = "NORMAL|EXCLUSIVO")
    private String tipo;

    public ItemTO() {
    }

    public ItemTO(Long id, String nome, String modelo, Integer valorPontos, String tipo) {
        this.id = id;
        this.nome = nome;
        this.modelo = modelo;
        this.valorPontos = valorPontos;
        this.tipo = tipo;
    }

    public boolean verificarCompatibilidade(String modeloDesejado) {
        return this.modelo != null
                && modeloDesejado != null
                && this.modelo.equalsIgnoreCase(modeloDesejado);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getValorPontos() {
        return this.valorPontos;
    }

    public void setValorPontos(Integer valorPontos) {
        this.valorPontos = valorPontos;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

