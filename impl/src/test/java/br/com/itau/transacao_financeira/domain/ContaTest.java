package br.com.itau.transacao_financeira.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContaTest {

    @Test
    public void testDebitoDeValor50() {
        
        Conta conta = new Conta(1, 100);
        conta.debito(50);
        
        Assertions.assertEquals(50, conta.getSaldo());
    }
    
    @Test
    public void testCreditoDeValor50() {
        
        Conta conta = new Conta(1, 100);
        conta.credito(50);
        
        Assertions.assertEquals(150, conta.getSaldo());
    }
}