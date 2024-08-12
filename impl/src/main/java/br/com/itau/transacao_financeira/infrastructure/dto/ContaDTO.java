package br.com.itau.transacao_financeira.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContaDTO {

    private long id;
    private double saldo;
}
