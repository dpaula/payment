package com.dpaula.payment.repository;

import com.dpaula.payment.entity.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Fernando de Lima on 13/04/22
 */
@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
    Optional<Cliente> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
