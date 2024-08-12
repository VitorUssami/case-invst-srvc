package br.com.itau.transacao_financeira.application.port.out;

public interface AtualizarSaldoConta {

    void atualizar(long idConta, double saldo);
}
