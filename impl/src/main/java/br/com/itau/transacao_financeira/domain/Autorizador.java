package br.com.itau.transacao_financeira.domain;

import br.com.itau.transacao_financeira.domain.exception.ContaInexistenteException;
import br.com.itau.transacao_financeira.domain.exception.SaldoinsuficienteException;

public class Autorizador {

    public void transferir(Conta origem, Conta destino, double valor) {
        
        validar(origem, destino, valor);
        
        origem.debito(valor);
        destino.credito(valor);
    }
    
    private void validar(Conta origem, Conta destino, double valor) {
        
        if(origem == null) {
            throw new ContaInexistenteException("Conta origem nao existe");
        }
        
        if(destino == null) {
            throw new ContaInexistenteException("Conta destino nao existe");
        }
        
        if (origem.getSaldo() < valor) {
            throw new SaldoinsuficienteException(String.format("Falta de saldo na conta %s", origem.getId()));
        }
    }
}
