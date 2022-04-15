package com.dpaula.payment.entity;

import com.dpaula.payment.enuns.EnCartaoBandeira;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Fernando de Lima on 12/04/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("cartoes")
public class Cartao {

    @Indexed(unique = true)
    private String numero;
    private String nome;
    private LocalDate validade;
    private EnCartaoBandeira bandeira;
    private BigDecimal limite;
    private Integer vencimento;
    private Integer cvv;

    private List<Fatura> faturas;
}
