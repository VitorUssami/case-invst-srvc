package br.com.itau.transacao_financeira.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.itau.transacao_financeira.domain.exception.ContaInexistenteException;
import br.com.itau.transacao_financeira.domain.exception.SaldoinsuficienteException;

public class AutorizadorTest {

    @Test
    public void testSucesso() {
        
        Conta origem = new Conta(1, 100);
        Conta destino = new Conta(2, 100);
        
        Autorizador autorizador = new Autorizador();
        autorizador.transferir(origem, destino, 10);
        
        Assertions.assertEquals(90, origem.getSaldo());
        Assertions.assertEquals(110, destino.getSaldo());
    }
    
    @Test
    public void testErroSaldo() {
        
        Conta origem = new Conta(1, 100);
        Conta destino = new Conta(2, 100);
        
        Assertions.assertThrows(SaldoinsuficienteException.class, () -> new Autorizador().transferir(origem, destino, 200));
    }
    
    @Test
    public void testErroOrigem() {
        
        Conta origem = null;
        Conta destino = new Conta(2, 100);
        
        Assertions.assertThrows(ContaInexistenteException.class, () -> new Autorizador().transferir(origem, destino, 10));
    }
    
    @Test
    public void testErroDestino() {
        
        Conta origem = new Conta(1, 100);
        Conta destino = null;
        
        Assertions.assertThrows(ContaInexistenteException.class, () -> new Autorizador().transferir(origem, destino, 10));
    }
}