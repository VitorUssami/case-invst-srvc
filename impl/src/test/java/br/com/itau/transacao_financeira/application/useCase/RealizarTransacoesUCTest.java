package br.com.itau.transacao_financeira.application.useCase;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.itau.transacao_financeira.infrastructure.dto.TransacaoFinanceiraDTO;

public class RealizarTransacoesUCTest {

    private RealizarTransacoesUC realizarTransacoesUC;
    
    @BeforeEach
    private void init() {
        realizarTransacoesUC = new  RealizarTransacoesUC();
    }
    
    @Test
    public void testListaNula() {
        
        Assertions.assertAll(() ->  realizarTransacoesUC.executar(null));
    }
    
    @Test
    public void testListaVazia() {
        
        Assertions.assertAll(() ->  realizarTransacoesUC.executar(new LinkedList<TransacaoFinanceiraDTO>()));
    }
    
    @Test
    public void testSucesso() {
        
        Assertions.assertAll(() -> {
            
            var transacoes = Arrays.asList(
                    TransacaoFinanceiraDTO.builder().correlationId(1).dataTransacao(LocalDateTime.parse("2023-09-09T14:15:00"))
                  .idContaOrigem(938485762l).idContaDestino(2147483649l).valor(100).build()
                    );
            realizarTransacoesUC.executar(transacoes);
        });
    }
    
    @Test
    public void testErro() {
        
        Assertions.assertAll(() -> {
            
            var transacoes = Arrays.asList(
                    TransacaoFinanceiraDTO.builder().correlationId(1).dataTransacao(LocalDateTime.parse("2023-09-09T14:15:00"))
                    .idContaOrigem(938485762l).idContaDestino(2147483649l).valor(500).build()
                    );
            realizarTransacoesUC.executar(transacoes);
        });
    }
    
    @Test
    public void testErroOrigemInexistente() {
        
        Assertions.assertThrows(NoSuchElementException.class ,() -> {
            
            var transacoes = Arrays.asList(
                    TransacaoFinanceiraDTO.builder().correlationId(1).dataTransacao(LocalDateTime.parse("2023-09-09T14:15:00"))
                    .idContaOrigem(123l).idContaDestino(2147483649l).valor(500).build()
                    );
            realizarTransacoesUC.executar(transacoes);
        });
    }
    
    @Test
    public void testErroDestinoInexistente() {
        
        Assertions.assertThrows(NoSuchElementException.class ,() -> {
            
            var transacoes = Arrays.asList(
                    TransacaoFinanceiraDTO.builder().correlationId(1).dataTransacao(LocalDateTime.parse("2023-09-09T14:15:00"))
                    .idContaOrigem(938485762l).idContaDestino(123l).valor(500).build()
                    );
            realizarTransacoesUC.executar(transacoes);
        });
    }
}
