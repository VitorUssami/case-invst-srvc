package br.com.itau.transacao_financeira.infrastructure.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransacaoFinanceiraDTO {

    private int correlationId;
    private LocalDateTime dataTransacao;
    private long idContaOrigem;
    private long idContaDestino;
    private double valor;
    
}
