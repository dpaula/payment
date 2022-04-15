package com.dpaula.payment.controller;

import com.dpaula.payment.dto.CartaoCreditoDTO;
import com.dpaula.payment.enuns.EnCartaoBandeira;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Fernando de Lima on 12/04/22
 */
@RestController
@RequestMapping("/cartoes")
public interface ICartaoController {

    @GetMapping()
    ResponseEntity<List<CartaoCreditoDTO>> findAll(@RequestParam(value = "bandeira", required = false) EnCartaoBandeira bandeira,
                                                   @RequestParam(value = "disponivel", required = false) Boolean disponivel);

    @PostMapping("save-list")
    ResponseEntity<String> save(@RequestBody List<CartaoCreditoDTO> cartaoCreditoDTOList);
}
