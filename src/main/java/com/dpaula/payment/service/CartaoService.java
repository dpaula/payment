package com.dpaula.payment.service;

import com.dpaula.payment.controller.IClienteController;
import com.dpaula.payment.entity.Cartao;
import com.dpaula.payment.entity.CartaoCredito;
import com.dpaula.payment.enuns.EnCartaoBandeira;
import com.dpaula.payment.error.ObjectNotFoundException;
import com.dpaula.payment.repository.CartaoCreditoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Fernando de Lima on 13/04/22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoCreditoRepository repository;

    public List<CartaoCredito> findAll(final EnCartaoBandeira bandeira, final Boolean disponivel) {
        log.info("Buscando cartões de crédito");
        return repository.findAll();
    }

    public List<CartaoCredito> saveAll(final List<CartaoCredito> cartoes) {
        log.info("Salvando {} cartões de crédito", cartoes.size());

        cartoes.forEach(cartao -> cartao.setDisponivel(true));
        return repository.saveAll(cartoes);
    }

    public Cartao solicitarCartao(final IClienteController.DadosSolicitacaoCartaoInput dadosSolicitacaoCartaoInput) {

        final var cartao = gerarNumeroCartao(dadosSolicitacaoCartaoInput.bandeira());

        final var novoCartao = Cartao.builder()
                .bandeira(dadosSolicitacaoCartaoInput.bandeira())
                .numero(cartao.getNumero())
                .nomeImpresso(dadosSolicitacaoCartaoInput.nomeImpresso())
                .limite(new BigDecimal("1000"))
                .diaVencimento(dadosSolicitacaoCartaoInput.diaVencimento())
                .cvv(cartao.getCvv())
                .validade(getDataValidade())
                .build();

        marcarCartaoCreditoComoIndisponivel(cartao);

        return novoCartao;
    }

    private static LocalDate getDataValidade() {
        return LocalDate.now().plusYears(5);
    }

    private void marcarCartaoCreditoComoIndisponivel(final CartaoCredito cartao) {
        log.info("Marcando cartão de crédito como indisponível");
        cartao.setDisponivel(false);
        repository.save(cartao);
    }

    private CartaoCredito gerarNumeroCartao(@NotBlank final EnCartaoBandeira bandeira) {
        log.info("Gerando número do cartão");

        final var cartoesDisponiveis = repository.findAllByBandeiraAndDisponivel(bandeira, true);

        return cartoesDisponiveis.stream()
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Não existe cartão disponível para a bandeira informada"));
    }
}
