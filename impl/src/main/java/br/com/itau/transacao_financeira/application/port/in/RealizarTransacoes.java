package br.com.itau.transacao_financeira.application.port.in;

import java.util.List;

import br.com.itau.transacao_financeira.infrastructure.dto.TransacaoFinanceiraDTO;

public interface RealizarTransacoes {

    void executar(List<TransacaoFinanceiraDTO> transacoes);
    
}
