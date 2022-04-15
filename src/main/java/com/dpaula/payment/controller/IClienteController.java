package com.dpaula.payment.controller;

import com.dpaula.payment.dto.ClienteDTO;
import com.dpaula.payment.enuns.EnCartaoBandeira;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Fernando de Lima on 12/04/22
 */
@RestController
@RequestMapping("/clientes")
public interface IClienteController {

    record DadosSolicitacaoCartaoInput(@NotBlank String cpf, @NotBlank Integer diaVencimento,
                                       @NotBlank EnCartaoBandeira bandeira,
                                       @NotBlank String nomeImpresso) {

    }

    @GetMapping()
    ResponseEntity<List<ClienteDTO>> findAll();

    @GetMapping("/{cpf}")
    ResponseEntity<ClienteDTO> findById(@PathVariable("cpf") String cpf);

    @PostMapping()
    ResponseEntity<ClienteDTO> save(@RequestBody ClienteDTO cliente);

    @PostMapping("/solicitar-cartao")
    ResponseEntity<ClienteDTO> solicitarCartao(@RequestBody @Valid DadosSolicitacaoCartaoInput dadosSolicitacaoCartaoInput);
}
