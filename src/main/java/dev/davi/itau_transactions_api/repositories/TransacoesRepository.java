package dev.davi.itau_transactions_api.repositories;

import dev.davi.itau_transactions_api.models.Transacao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class TransacoesRepository {
    @Value("${estatistica.segundos}")
    Integer segundos;

    private Map<UUID, Transacao> transacaoMap = new HashMap<>();

    public Transacao save(Transacao t) {
        Transacao transacao = new Transacao();
        transacao.setId(UUID.randomUUID());
        transacao.setValor(t.getValor());
        transacao.setDataHora(t.getDataHora());
        transacaoMap.put(transacao.getId(), transacao);

        return transacao;
    }

    public Transacao editar(UUID id, Transacao transacao) {
        return transacaoMap.replace(id, transacao);
    }

    public List<Transacao> findAll() {
        return new ArrayList<>(transacaoMap.values());
    }

    public Transacao findUn(UUID id) {
        return transacaoMap.get(id);
    }

    public void delete(UUID id) {
        transacaoMap.remove(id);
    }

    public void clearAll() {
        transacaoMap.clear();
    }

    public List<Transacao> findByTime() {
        return new ArrayList<>(transacaoMap.values()).stream()
                .filter(t -> t.getDataHora().isAfter(LocalDateTime.now().minusSeconds(segundos)))
                .sorted(Comparator.comparing(Transacao::getDataHora))
                .toList();
    }
}
