package br.com.itau.transacao_financeira.application.port.in;

import java.util.Optional;

import br.com.itau.transacao_financeira.infrastructure.dto.ContaDTO;

public interface RecuperarConta {

    Optional<ContaDTO> recuperarConta(long idConta);
}
