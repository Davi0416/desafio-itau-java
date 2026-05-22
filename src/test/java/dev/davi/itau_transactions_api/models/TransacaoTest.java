package dev.davi.itau_transactions_api.models;

import dev.davi.itau_transactions_api.dto.TransacaoRequestDTO;
import dev.davi.itau_transactions_api.repositories.TransacoesRepository;
import dev.davi.itau_transactions_api.services.EstatisticasService;
import dev.davi.itau_transactions_api.services.TransacoesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.UUID;

class TransacaoTest {

    TransacoesRepository r;
    TransacoesService s;
    EstatisticasService estatisticasService;

    @BeforeEach
    void setUp() throws Exception {
        r = new TransacoesRepository();


        Field segundosField = TransacoesRepository.class.getDeclaredField("segundos");
        segundosField.setAccessible(true);
        segundosField.set(r, 60);

        s = new TransacoesService(r);
        estatisticasService = new EstatisticasService(r);
    }

    @Test
    @DisplayName("Deve criar uma nova transação")
    void deveCriarTransacao() {
        Transacao t = new Transacao();
        t.setValor(12.);
        t.setDataHora(LocalDateTime.now());

        TransacaoRequestDTO rq = new TransacaoRequestDTO(t.getValor(), t.getDataHora());
        Transacao result = s.criarTransacao(rq);
        t.setId(result.getId());

        Assertions.assertEquals(t, result);
    }

    @Test
    @DisplayName("Deve excluir uma transação")
    void deveExcluirTransacao() {
        TransacaoRequestDTO rq = new TransacaoRequestDTO(12., LocalDateTime.now());
        Transacao result = s.criarTransacao(rq);
        s.delete(result.getId());

        Assertions.assertNull(r.findUn(result.getId()));
    }

    @Test
    @DisplayName("Deve excluir todas as transações")
    void deveExcluirTodasTransacoes() {
        s.criarTransacao(new TransacaoRequestDTO(10., LocalDateTime.now()));
        s.criarTransacao(new TransacaoRequestDTO(20., LocalDateTime.now()));
        s.deletarTodasTransacoes();

        Assertions.assertTrue(r.findAll().isEmpty());
    }

    @Test
    @DisplayName("Deve lançar erro ao criar transação com data futura")
    void deveLancarErroComDataFutura() {
        TransacaoRequestDTO rq = new TransacaoRequestDTO(12., LocalDateTime.now().plusHours(1));

        Assertions.assertThrows(ResponseStatusException.class, () -> s.criarTransacao(rq));
    }

    @Test
    @DisplayName("Deve lançar erro ao criar transação com valor negativo")
    void deveLancarErroComValorNegativo() {
        TransacaoRequestDTO rq = new TransacaoRequestDTO(-5., LocalDateTime.now());

        Assertions.assertThrows(ResponseStatusException.class, () -> s.criarTransacao(rq));
    }

    @Test
    @DisplayName("Deve lançar erro ao deletar transação inexistente")
    void deveLancarErroAoDeletarInexistente() {
        Assertions.assertThrows(ResponseStatusException.class, () -> s.delete(UUID.randomUUID()));
    }

    @Test
    @DisplayName("Deve mostrar todas as transações")
    void deveMostrarTodasTransacoes() {
        s.criarTransacao(new TransacaoRequestDTO(10., LocalDateTime.now()));
        s.criarTransacao(new TransacaoRequestDTO(20., LocalDateTime.now()));

        Assertions.assertEquals(2, s.mostrarTransacoes().size());
    }

    @Test
    @DisplayName("Deve retornar estatísticas corretas")
    void deveRetornarEstatisticas() {
        s.criarTransacao(new TransacaoRequestDTO(10., LocalDateTime.now()));
        s.criarTransacao(new TransacaoRequestDTO(20., LocalDateTime.now()));

        Estatistica result = estatisticasService.showInTime();

        Assertions.assertEquals(30.0, result.getSoma());
        Assertions.assertEquals(15.0, result.getMedia());
        Assertions.assertEquals(20.0, result.getMax());
        Assertions.assertEquals(10.0, result.getMin());
        Assertions.assertEquals(2L, result.getQuantidade());
    }
}
