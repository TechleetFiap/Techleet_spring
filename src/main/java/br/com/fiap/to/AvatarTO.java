package br.com.fiap.to;

public class AvatarTO {

    private int id;
    private String nome;
    private int idCabelo;
    private int idRoupaCimaInt;
    private int idRoupaCimaExt;
    private int idRoupaBaixo;
    private int idCalcado;
    private int idAcessorio;
    private int idUsuario;

    public AvatarTO() {
    }

    public AvatarTO(int id, String nome, int idCabelo, int idRoupaCimaInt, int idRoupaCimaExt, int idRoupaBaixo, int idCalcado, int idAcessorio, int idUsuario) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdCabelo() {
        return idCabelo;
    }

    public void setIdCabelo(int idCabelo) {
        this.idCabelo = idCabelo;
    }

    public int getIdRoupaCimaInt() {
        return idRoupaCimaInt;
    }

    public void setIdRoupaCimaInt(int idRoupaCimaInt) {
        this.idRoupaCimaInt = idRoupaCimaInt;
    }

    public int getIdRoupaCimaExt() {
        return idRoupaCimaExt;
    }

    public void setIdRoupaCimaExt(int idRoupaCimaExt) {
        this.idRoupaCimaExt = idRoupaCimaExt;
    }

    public int getIdRoupaBaixo() {
        return idRoupaBaixo;
    }

    public void setIdRoupaBaixo(int idRoupaBaixo) {
        this.idRoupaBaixo = idRoupaBaixo;
    }

    public int getIdCalcado() {
        return idCalcado;
    }

    public void setIdCalcado(int idCalcado) {
        this.idCalcado = idCalcado;
    }

    public int getIdAcessorio() {
        return idAcessorio;
    }

    public void setIdAcessorio(int idAcessorio) {
        this.idAcessorio = idAcessorio;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
