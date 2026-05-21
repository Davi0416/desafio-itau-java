package dev.davi.itau_transactions_api.Transacoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TransacoesService {
    final TransacoesRepository repository;
    public TransacoesService(TransacoesRepository transacoesRepository) {
        this.repository = transacoesRepository;
    }

    public Transacao criarTransacao(TransacaoRequestDTO t) {
        Transacao transacao = new Transacao();
        if (t.dataHora().isAfter(LocalDateTime.now())) {
            new ResponseEntity<>(HttpStatus.UNPROCESSABLE_CONTENT);
        }
        if (t.valor() < 0) {
            new ResponseEntity<>(HttpStatus.UNPROCESSABLE_CONTENT);
        }
        transacao.setValor(t.valor());
        transacao.setDataHora(t.dataHora());
        return repository.save(transacao);
    }

    public List<Transacao> mostrarTransacoes() {
        return repository.findAll();
    }

    public Transacao mostrarTransacao(UUID id) {
        return repository.findUn(id);
    }

    public void deletarTodasTransacoes() {
        repository.clearAll();
    }
}
