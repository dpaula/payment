package com.dpaula.payment.controller.imp;

import com.dpaula.payment.controller.IClienteController;
import com.dpaula.payment.dto.ClienteDTO;
import com.dpaula.payment.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Fernando de Lima on 12/04/22
 */
@Component
@RequiredArgsConstructor
public class ClienteController implements IClienteController {

    private final ClienteService clienteService;

    @Override
    public ResponseEntity<List<ClienteDTO>> findAll() {
        var clientes = ClienteDTO.parseAll(clienteService.findAll());
        return ResponseEntity.ok(clientes);
    }

    @Override
    public ResponseEntity<ClienteDTO> findById(String cpf) {
        var cliente = ClienteDTO.parse(clienteService.findById(cpf));
        return ResponseEntity.ok(cliente);
    }

    @Override
    public ResponseEntity<ClienteDTO> save(ClienteDTO clienteDTO) {
        var cliente = ClienteDTO.parse(clienteService.save(clienteDTO.toEntity()));
        return ResponseEntity.ok(cliente);
    }

    @Override
    public ResponseEntity<ClienteDTO> solicitarCartao(DadosSolicitacaoCartaoInput dadosSolicitacaoCartaoInput) {
        var cliente = ClienteDTO.parse(clienteService.solicitarCartao(dadosSolicitacaoCartaoInput));
        return ResponseEntity.ok(cliente);
    }
}
