package model;

public class Cartao {
    private int id;
    private int usuarioId;
    private String nomeCartao;
    private String numeroCartao;
    private String bandeira;
    private double limite;

    public Cartao() {}

    public Cartao(int id, int usuarioId, String nomeCartao, String numeroCartao, String bandeira, double limite) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nomeCartao = nomeCartao;
        this.numeroCartao = numeroCartao;
        this.bandeira = bandeira;
        this.limite = limite;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    public String getNomeCartao() {
        return nomeCartao;
    }
    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }
    public String getNumeroCartao() {
        return numeroCartao;
    }
    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
    public String getBandeira() {
        return bandeira;
    }
    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }
    public double getLimite() {
        return limite;
    }
    public void setLimite(double limite) {
        this.limite = limite;
    }
}
