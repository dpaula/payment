package com.dpaula.payment.controller.imp;

import com.dpaula.payment.controller.IFaturaController;
import com.dpaula.payment.dto.ComprovanteDTO;
import com.dpaula.payment.dto.FaturaDTO;
import com.dpaula.payment.request.PagamentoFaturaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fernando de Lima on 12/04/22
 */
@Component
public class FaturaController implements IFaturaController {

    @Override
    public ResponseEntity<List<FaturaDTO>> findAll(final String cpf) {
        return null;
    }

    @Override
    public ResponseEntity<FaturaDTO> findByDataVencimento(final String cpf, final LocalDate dataVencimento) {
        return null;
    }

    @Override
    public ResponseEntity<String> gerarFatura(final String cpf) {
        return null;
    }

    @Override
    public ResponseEntity<ComprovanteDTO> pagarFatura(final String cpf, final PagamentoFaturaRequest pagamentoFaturaInput) {
        return null;
    }
}
