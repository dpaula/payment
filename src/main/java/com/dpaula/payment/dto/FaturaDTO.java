package com.dpaula.payment.dto;

import com.dpaula.payment.enuns.EnStatusFatura;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Fernando de Lima on 12/04/22
 */
public record FaturaDTO(String descricao,
                        EnStatusFatura status,
                        LocalDate dataVencimento,
                        LocalDate dataPagamento,
                        BigDecimal valor) {
}
