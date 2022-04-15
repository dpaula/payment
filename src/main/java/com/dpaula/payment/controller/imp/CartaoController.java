package com.dpaula.payment.controller.imp;

import com.dpaula.payment.controller.ICartaoController;
import com.dpaula.payment.dto.CartaoCreditoDTO;
import com.dpaula.payment.entity.CartaoCredito;
import com.dpaula.payment.enuns.EnCartaoBandeira;
import com.dpaula.payment.service.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Fernando de Lima on 12/04/22
 */
@Component
@RequiredArgsConstructor
public class CartaoController implements ICartaoController {

    private final CartaoService service;

    @Override
    public ResponseEntity<List<CartaoCreditoDTO>> findAll(final EnCartaoBandeira bandeira, final Boolean disponivel) {
        final List<CartaoCredito> cartoes = service.findAll(bandeira, disponivel);
        return ResponseEntity.ok(CartaoCreditoDTO.parseAll(cartoes));
    }

    @Override
    public ResponseEntity<String> save(final List<CartaoCreditoDTO> cartaoCreditoDTOList) {

        final List<CartaoCredito> cartoes = CartaoCreditoDTO.toEntityList(cartaoCreditoDTOList);

        final List<CartaoCredito> cartoesSalvos = service.saveAll(cartoes);

        return ResponseEntity.ok("Cartões salvos com sucesso: " + cartoesSalvos.size());
    }
}
