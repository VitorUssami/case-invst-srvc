package br.com.itau.transacao_financeira.application.port.out;

import br.com.itau.transacao_financeira.infrastructure.dto.TransacaoFinanceiraDTO;

public interface AtualizarTransacao {

    void atualizar(TransacaoFinanceiraDTO transacao);

}
