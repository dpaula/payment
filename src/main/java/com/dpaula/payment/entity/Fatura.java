package com.dpaula.payment.entity;

import com.dpaula.payment.enuns.EnStatusFatura;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Fernando de Lima on 12/04/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("faturas")
public class Fatura{
    private String descricao;
    private EnStatusFatura status;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
}
