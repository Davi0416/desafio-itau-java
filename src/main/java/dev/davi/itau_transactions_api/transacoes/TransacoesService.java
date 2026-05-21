package dev.davi.itau_transactions_api.transacoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransacoesService {
    final TransacoesRepository repository;
    public TransacoesService(TransacoesRepository transacoesRepository) {
        this.repository = transacoesRepository;
    }

    public Transacao criarTransacao(TransacaoRequestDTO t) {
        if (t.dataHora().isAfter(LocalDateTime.now()) || t.valor() < 0) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Transacao transacao = new Transacao();
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

    public void delete(UUID id) {
        if (repository.findUn(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.delete(id);
    }

    public void deletarTodasTransacoes() {
        repository.clearAll();
    }
}
