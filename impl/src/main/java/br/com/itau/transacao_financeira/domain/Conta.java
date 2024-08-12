package br.com.itau.transacao_financeira.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Conta {

    private long id;
    private double saldo;
    
    
    public void debito(double valor) {
        saldo -= valor;
    }
    
    public void credito(double valor) {
        saldo += valor;
    }
}
