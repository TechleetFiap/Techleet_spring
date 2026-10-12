package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class MissaoTO {

    private Long id;
    @NotBlank
    @Size(max = 100)
    private String titulo;
    @NotBlank
    @Size(max = 150)
    private String descricao;
    @PositiveOrZero
    private int pontos;
    @NotBlank
    @Pattern(regexp = "DIARIA|SEMANAL|ESPECIAL")
    private String tipo;

    public MissaoTO() {
    }

    public MissaoTO(Long id, String titulo, String descricao, int pontos, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.pontos = pontos;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
