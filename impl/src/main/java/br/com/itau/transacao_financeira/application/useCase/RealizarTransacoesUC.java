package br.com.itau.transacao_financeira.application.useCase;

import java.util.List;
import java.util.Optional;

import br.com.itau.transacao_financeira.application.port.in.RealizarTransacoes;
import br.com.itau.transacao_financeira.application.port.in.RecuperarConta;
import br.com.itau.transacao_financeira.application.port.out.AtualizarSaldoConta;
import br.com.itau.transacao_financeira.domain.Autorizador;
import br.com.itau.transacao_financeira.domain.Conta;
import br.com.itau.transacao_financeira.domain.exception.SaldoinsuficienteException;
import br.com.itau.transacao_financeira.infrastructure.dto.ContaDTO;
import br.com.itau.transacao_financeira.infrastructure.dto.TransacaoFinanceiraDTO;
import br.com.itau.transacao_financeira.infrastructure.out.ContaRepositoryLocal;

public class RealizarTransacoesUC implements RealizarTransacoes {

    private Autorizador autorizador;
    private RecuperarConta recuperarConta;
    private AtualizarSaldoConta atualizarSaldoConta;

    // instancia local para facilitar o acesso a lista in memory, no mundo real as
    // interfaces RecuperarConta e AtualizarSaldoConta acessariam um DB
    private ContaRepositoryLocal contaRepositoryLocal = new ContaRepositoryLocal();

    public RealizarTransacoesUC() {
        this.recuperarConta = contaRepositoryLocal;
        this.atualizarSaldoConta = contaRepositoryLocal;
        this.autorizador = new Autorizador();
    }

    @Override
    public void executar(List<TransacaoFinanceiraDTO> transacoes) {

        if (transacoes != null && !transacoes.isEmpty()) {
            transacoes.stream().forEach(transacao -> executarTransacao(transacao));
        }
    }

    private void executarTransacao(TransacaoFinanceiraDTO transacao) {

        try {

            Conta origem = recuperarConta(transacao.getIdContaOrigem());
            Conta destino = recuperarConta(transacao.getIdContaDestino());

            autorizador.transferir(origem, destino, transacao.getValor());

            atualizarSaldoConta.atualizar(origem.getId(), origem.getSaldo());
            atualizarSaldoConta.atualizar(destino.getId(), destino.getSaldo());

            System.out.println(String.format(
                    "Transacao numero %s foi efetivada com sucesso! Novos saldos: Conta Origem: %s | Conta Destino: %s",
                    transacao.getCorrelationId(), origem.getSaldo(), destino.getSaldo()));

        } catch (SaldoinsuficienteException e) {
            System.err
                    .println(String.format("Erro na transacao %s | %s", transacao.getCorrelationId(), e.getMessage()));
        }
    }

    private Conta recuperarConta(long idConta) {
        Optional<ContaDTO> contaDTO = recuperarConta.recuperarConta(idConta);
        return parseToDomain(contaDTO.get());
    }

    private Conta parseToDomain(ContaDTO contaDTO) {
        return Conta.builder().id(contaDTO.getId()).saldo(contaDTO.getSaldo()).build();
    }

}
