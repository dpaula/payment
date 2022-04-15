package com.dpaula.payment.dto;

/**
 * @author Fernando de Lima on 12/04/22
 */
public record ComprovanteDTO(
        String descricao,
        String valor,
        String dataPagamento,
        String pagador,
        String conta
) {
}
