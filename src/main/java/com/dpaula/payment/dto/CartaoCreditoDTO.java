package com.dpaula.payment.dto;

import com.dpaula.payment.entity.CartaoCredito;
import com.dpaula.payment.enuns.EnCartaoBandeira;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

public record CartaoCreditoDTO(String numero, EnCartaoBandeira bandeira, Integer cvv, Boolean disponivel) {

    @Builder public CartaoCreditoDTO {}

    public static CartaoCreditoDTO parse(final CartaoCredito cartaoCredito) {
        if(isNull(cartaoCredito)) {
            return null;
        }
        return CartaoCreditoDTO.builder()
                .numero(cartaoCredito.getNumero())
                .bandeira(cartaoCredito.getBandeira())
                .cvv(cartaoCredito.getCvv())
                .disponivel(cartaoCredito.getDisponivel())
                .build();
    }

    public static List<CartaoCreditoDTO> parseAll(final List<CartaoCredito> cartaoCreditos) {
        return cartaoCreditos.stream()
                .map(CartaoCreditoDTO::parse)
                .filter(Objects::nonNull)
                .toList();
    }

    public static CartaoCredito toEntity(final CartaoCreditoDTO cartaoCreditoDTO) {
        if(isNull(cartaoCreditoDTO)) {
            return null;
        }
        return CartaoCredito.builder()
                .numero(cartaoCreditoDTO.numero())
                .bandeira(cartaoCreditoDTO.bandeira())
                .cvv(cartaoCreditoDTO.cvv())
                .build();
    }

    public static List<CartaoCredito> toEntityList(final List<CartaoCreditoDTO> cartaoCreditoDTOs) {
        return cartaoCreditoDTOs.stream()
                .map(CartaoCreditoDTO::toEntity)
                .filter(Objects::nonNull)
                .toList();
    }
}
