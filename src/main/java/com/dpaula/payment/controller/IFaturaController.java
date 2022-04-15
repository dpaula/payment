package com.dpaula.payment.controller;

import com.dpaula.payment.dto.ComprovanteDTO;
import com.dpaula.payment.dto.FaturaDTO;
import com.dpaula.payment.request.PagamentoFaturaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fernando de Lima on 12/04/22
 */
@RestController
@RequestMapping("/faturas/{cpf}")
public interface IFaturaController {

    @GetMapping()
    ResponseEntity<List<FaturaDTO>> findAll(@PathVariable String cpf);

    @GetMapping("{dataVencimento}")
    ResponseEntity<FaturaDTO> findByDataVencimento(@PathVariable String cpf, @PathVariable LocalDate dataVencimento);

    @PostMapping("gerar-fatura")
    ResponseEntity<String> gerarFatura(@PathVariable String cpf);

    @PostMapping("pagar-fatura")
    ResponseEntity<ComprovanteDTO> pagarFatura(@PathVariable String cpf, @RequestBody PagamentoFaturaRequest pagamentoFaturaInput);
}
