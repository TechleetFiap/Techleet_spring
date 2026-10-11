package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AvatarTO {

    private Long id;
    @NotBlank
    @Size(max = 50)
    private String nome;
    @NotNull
    private Long idCabelo;
    @NotNull
    private Long idRoupaCimaInt;
    @NotNull
    private Long idRoupaCimaExt;
    @NotNull
    private Long idRoupaBaixo;
    @NotNull
    private Long idCalcado;
    @NotNull
    private Long idAcessorio;
    @NotNull
    private Long idUsuario;

    public AvatarTO() {
    }

    public AvatarTO(Long id, String nome, Long idCabelo, Long idRoupaCimaInt, Long idRoupaCimaExt, Long idRoupaBaixo, Long idCalcado, Long idAcessorio, Long idUsuario) {
        this.id = id;
        this.nome = nome;
        this.idCabelo = idCabelo;
        this.idRoupaCimaInt = idRoupaCimaInt;
        this.idRoupaCimaExt = idRoupaCimaExt;
        this.idRoupaBaixo = idRoupaBaixo;
        this.idCalcado = idCalcado;
        this.idAcessorio = idAcessorio;
        this.idUsuario = idUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdCabelo() {
        return idCabelo;
    }

    public void setIdCabelo(Long idCabelo) {
        this.idCabelo = idCabelo;
    }

    public Long getIdRoupaCimaInt() {
        return idRoupaCimaInt;
    }

    public void setIdRoupaCimaInt(Long idRoupaCimaInt) {
        this.idRoupaCimaInt = idRoupaCimaInt;
    }

    public Long getIdRoupaCimaExt() {
        return idRoupaCimaExt;
    }

    public void setIdRoupaCimaExt(Long idRoupaCimaExt) {
        this.idRoupaCimaExt = idRoupaCimaExt;
    }

    public Long getIdRoupaBaixo() {
        return idRoupaBaixo;
    }

    public void setIdRoupaBaixo(Long idRoupaBaixo) {
        this.idRoupaBaixo = idRoupaBaixo;
    }

    public Long getIdCalcado() {
        return idCalcado;
    }

    public void setIdCalcado(Long idCalcado) {
        this.idCalcado = idCalcado;
    }

    public Long getIdAcessorio() {
        return idAcessorio;
    }

    public void setIdAcessorio(Long idAcessorio) {
        this.idAcessorio = idAcessorio;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
