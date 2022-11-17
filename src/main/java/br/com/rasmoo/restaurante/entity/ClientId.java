package br.com.rasmoo.restaurante.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ClientId implements Serializable {

    private String email;

    private String cpf;

    public ClientId() {
    }

    public ClientId(String email, String cpf) {
        this.email = email;
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "ClientId{" +
                "email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
