package com.dpaula.payment.entity;

import com.dpaula.payment.enuns.EnCartaoBandeira;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Fernando de Lima on 12/04/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("cartoes_credito")
public class CartaoCredito {

    @Id
    private String id;

    private String numero;
    private Boolean disponivel;
    private EnCartaoBandeira bandeira;
    private Integer cvv;
}
