package com.dpaula.payment.service;


import com.dpaula.payment.entity.Fatura;
import org.springframework.stereotype.Service;

/**
 * @author Fernando de Lima on 12/04/22
 */
@Service
public class FaturaService {

    public static Fatura createFatura(final Fatura fatura) {
        return new Fatura();
    }

    public static Fatura getFatura(final Long id) {
        return new Fatura();
    }
}
