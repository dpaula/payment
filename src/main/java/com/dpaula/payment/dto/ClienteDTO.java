package com.dpaula.payment.dto;

import com.dpaula.payment.entity.Cliente;
import lombok.Builder;

import java.util.List;

public record ClienteDTO(
        String cpf,
        String nome,
        String email,
        String telefone,
        CartaoDTO cartao
) {
    @Builder
    public ClienteDTO {
    }

    public static List<ClienteDTO> parseAll(List<Cliente> clientes) {
        return clientes.stream()
                .map(ClienteDTO::parse)
                .toList();
    }

    public static ClienteDTO parse(Cliente cliente) {
        return ClienteDTO.builder()
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .cartao(CartaoDTO.parse(cliente.getCartao()))
                .build();
    }

    public Cliente toEntity() {
        return Cliente.builder()
                .cpf(cpf)
                .nome(nome)
                .email(email)
                .telefone(telefone)
                .build();
    }
}
