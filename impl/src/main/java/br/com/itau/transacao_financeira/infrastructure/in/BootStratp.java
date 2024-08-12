package br.com.itau.transacao_financeira.infrastructure.in;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import br.com.itau.transacao_financeira.application.port.in.RealizarTransacoes;
import br.com.itau.transacao_financeira.application.useCase.RealizarTransacoesUC;
import br.com.itau.transacao_financeira.infrastructure.dto.TransacaoFinanceiraDTO;

public class BootStratp {

    private RealizarTransacoes realizarTransacoes;

    public BootStratp() {
        this.realizarTransacoes = new RealizarTransacoesUC();
    }

    public void execute() {
        realizarTransacoes.executar(recuperarTransacoes());
    }

    private List<TransacaoFinanceiraDTO> recuperarTransacoes() {
        
        return Arrays.asList(buildTransacao(1, "2023-09-09T14:15:00", 938485762l, 2147483649l, 150),
                            buildTransacao(2, "2023-09-09T14:15:05", 2147483649l, 210385733l, 149),
                            buildTransacao(3, "2023-09-09T14:15:29", 347586970l, 238596054l, 1100),
                            buildTransacao(4, "2023-09-09T14:17:00", 675869708l, 210385733l, 5300),
                            buildTransacao(5, "2023-09-09T14:18:00", 238596054l, 674038564l, 1489),
                            buildTransacao(6, "2023-09-09T14:18:20", 573659065l, 563856300l, 49),  
                            buildTransacao(7, "2023-09-09T14:19:00", 938485762l, 2147483649l, 44),               
                            buildTransacao(8, "2023-09-09T14:19:01", 573659065l, 675869708l, 150)               
                );
        
    }
                    
    private TransacaoFinanceiraDTO buildTransacao(int correlationId, String dateTimeTransacao, long idContaOrigem, long idContaDestino, double valor) {
        return TransacaoFinanceiraDTO.builder()
                .correlationId(correlationId)
                .dataTransacao(LocalDateTime.parse(dateTimeTransacao))
                .idContaOrigem(idContaOrigem)
                .idContaDestino(idContaDestino)
                .valor(valor)
                .build();
    }

}