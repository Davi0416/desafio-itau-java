package dev.davi.itau_transactions_api.services;

import dev.davi.itau_transactions_api.dto.TransacaoRequestDTO;
import dev.davi.itau_transactions_api.models.Transacao;
import dev.davi.itau_transactions_api.repositories.TransacoesRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransacoesService {

    final TransacoesRepository repository;

    public TransacoesService(TransacoesRepository repository) {
        this.repository = repository;
    }

    public Transacao criarTransacao(TransacaoRequestDTO t) {
        if (t.dataHora().isAfter(LocalDateTime.now()) || t.valor() < 0) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT);
        }
        Transacao transacao = new Transacao();
        transacao.setValor(t.valor());
        transacao.setDataHora(t.dataHora());
        return repository.save(transacao);
    }

    public List<Transacao> mostrarTransacoes() {
        return repository.findAll();
    }

    public void delete(UUID id) {
        if (repository.findUn(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.delete(id);
    }

    public void deletarTodasTransacoes() {
        if (repository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        repository.clearAll();
    }
}
