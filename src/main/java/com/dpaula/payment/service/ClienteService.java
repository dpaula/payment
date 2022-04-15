package com.dpaula.payment.service;

import com.dpaula.payment.entity.Cliente;
import com.dpaula.payment.error.ConflictException;
import com.dpaula.payment.repository.ClienteRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.dpaula.payment.controller.IClienteController.DadosSolicitacaoCartaoInput;

/**
 * @author Fernando de Lima on 12/04/22
 */
@Data
@Slf4j
@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final CartaoService cartaoService;

    public List<Cliente> findAll() {
        log.info("Buscando todos os clientes");
        return repository.findAll();
    }

    private Cliente getCliente(final String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow();
    }

    public Cliente findById(final String cpf) {
        log.info("Buscando cliente com cpf {}", cpf);
        return getCliente(cpf);
    }

    public Cliente save(final Cliente cliente) {
        log.info("Salvando cliente {}", cliente);

        if (repository.existsByCpf(cliente.getCpf())) {
            log.error("Cliente com cpf {} já existe", cliente.getCpf());
            throw new ConflictException("Cliente já existe para o cpf informado");
        }

        return repository.save(cliente);
    }

    public Cliente solicitarCartao(final DadosSolicitacaoCartaoInput dadosSolicitacaoCartaoInput) {
        log.info("Solicitando cartão para cliente {}", dadosSolicitacaoCartaoInput.cpf());

        final var cliente = getCliente(dadosSolicitacaoCartaoInput.cpf());

        final var cartao = cartaoService.solicitarCartao(dadosSolicitacaoCartaoInput);

        cliente.setCartao(cartao);

        return repository.save(cliente);
    }
}
