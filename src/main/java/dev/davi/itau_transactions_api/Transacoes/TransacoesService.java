package dev.davi.itau_transactions_api.Transacoes;

import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacoesService {
    final TransacoesRepository repository;

    public TransacoesService(TransacoesRepository transacoesRepository) {
        this.repository = transacoesRepository;
    }

    public void criarTransacao(TransacaoRequestDTO t) {
        Transacao transacao = new Transacao();
        transacao.setValor(t.valor());
        transacao.setDataHora(t.dataHora());
        repository.save(transacao);
    }

    public void deletarTodasTransacoes() {
        repository.clearAll();
    }
}
