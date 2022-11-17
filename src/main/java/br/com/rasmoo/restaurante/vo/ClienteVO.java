package br.com.rasmoo.restaurante.vo;

public class ClienteVO {

    private String cpf;

    private String nome;

    public ClienteVO(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "ClienteVO{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
