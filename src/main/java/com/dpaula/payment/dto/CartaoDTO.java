package com.dpaula.payment.dto;

import com.dpaula.payment.entity.Cartao;
import com.dpaula.payment.enuns.EnCartaoBandeira;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;

/**
 * @author Fernando de Lima on 12/04/22
 */
public record CartaoDTO(String numero,
                        String nome,
                        String validade,
                        Integer cvv,
                        EnCartaoBandeira bandeira,
                        BigDecimal limite,
                        Integer vencimento) {
    @Builder
    public CartaoDTO {
    }

    public static CartaoDTO parse(final Cartao cartao) {
        if(isNull(cartao)) {
            return null;
        }

        return CartaoDTO.builder()
                .numero(cartao.getNumero())
                .nome(cartao.getNomeImpresso())
                .validade(getDataValidade(cartao.getValidade()))
                .cvv(cartao.getCvv())
                .bandeira(cartao.getBandeira())
                .limite(cartao.getLimite())
                .vencimento(cartao.getDiaVencimento())
                .build();
    }

    private static String getDataValidade(final LocalDate validade) {
        final var isoDate = DateTimeFormatter.ofPattern("MM/yy");
        return validade.format(isoDate);
    }
}
