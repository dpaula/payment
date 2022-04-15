package com.dpaula.payment.request;

import java.math.BigInteger;
import java.time.LocalDate;

/**
 * @author Fernando de Lima on 12/04/22
 */
public record PagamentoFaturaRequest(
        LocalDate dataVencimento,
        BigInteger codigoTransacao
) {
}
