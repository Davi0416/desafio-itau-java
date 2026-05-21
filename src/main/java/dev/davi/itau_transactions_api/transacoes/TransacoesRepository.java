package dev.davi.itau_transactions_api.transacoes;

import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class TransacoesRepository {
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

    public List<Map.Entry<UUID, Transacao>> findByTime() {
        List<Map.Entry<UUID, Transacao>> list = new ArrayList<>();
        for (Map.Entry<UUID, Transacao> transacoes : transacaoMap.entrySet()) {
            if (transacaoMap.get(transacoes.getKey()).getDataHora().isBefore(LocalDateTime.now().plusSeconds(-60))) {
                list.add(transacoes);
            }
        }
        return list;
    }
}
