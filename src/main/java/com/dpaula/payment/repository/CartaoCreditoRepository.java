package com.dpaula.payment.repository;

import com.dpaula.payment.entity.CartaoCredito;
import com.dpaula.payment.enuns.EnCartaoBandeira;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Fernando de Lima on 13/04/22
 */
@Repository
public interface CartaoCreditoRepository extends MongoRepository<CartaoCredito, String> {
    List<CartaoCredito> findAllByBandeiraAndDisponivel(EnCartaoBandeira bandeira, boolean disponivel);
}
