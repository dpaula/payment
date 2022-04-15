package com.dpaula.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Fernando de Lima on 12/04/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("clientes")
public class Cliente {

    @Id
    private String id;

    @Indexed(unique = true)
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private Cartao cartao;
}
