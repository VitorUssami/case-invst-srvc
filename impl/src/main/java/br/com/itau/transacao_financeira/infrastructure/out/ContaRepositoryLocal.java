package br.com.itau.transacao_financeira.infrastructure.out;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import br.com.itau.transacao_financeira.application.port.in.RecuperarConta;
import br.com.itau.transacao_financeira.application.port.out.AtualizarSaldoConta;
import br.com.itau.transacao_financeira.infrastructure.dto.ContaDTO;

public class ContaRepositoryLocal implements RecuperarConta, AtualizarSaldoConta {

    private List<ContaDTO> contas = new LinkedList<ContaDTO>();
    
    public ContaRepositoryLocal() {
        
        contas.add(ContaDTO.builder().id(938485762l).saldo(180).build());
        contas.add(ContaDTO.builder().id(347586970l).saldo(1200).build());
        contas.add(ContaDTO.builder().id(2147483649l).saldo(0).build());
        contas.add(ContaDTO.builder().id(675869708l).saldo(4900).build());
        contas.add(ContaDTO.builder().id(238596054l).saldo(478).build());
        contas.add(ContaDTO.builder().id(573659065l).saldo(787).build());
        contas.add(ContaDTO.builder().id(210385733l).saldo(10).build());
        contas.add(ContaDTO.builder().id(674038564l).saldo(400).build());
        contas.add(ContaDTO.builder().id(563856300l).saldo(1200).build());
    }
    
    @Override
    public Optional<ContaDTO> recuperarConta(long idConta) {
        return contas.stream().filter(c -> c.getId() == idConta).findFirst();
    }

    @Override
    public void atualizar(long idConta, double saldo) {
        contas.stream().filter(c -> c.getId() == idConta).findFirst().get().setSaldo(saldo);
    }
}
