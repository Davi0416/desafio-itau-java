package dev.davi.itau_transactions_api.Transacoes;

import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacoesRepository {
    private List<Transacao> transacaoList = new ArrayList<>();

    public void save(Transacao transacao) {
        transacaoList.add(transacao);
    }

    public Transacao editar(int id, Transacao transacao) {
        return transacaoList.set(id, transacao);
    }

    public List<Transacao> findAll() {
        return new ArrayList<>(transacaoList);
    }

    public void delete(int id) {
        transacaoList.remove(id);
    }

    public void clearAll() {
        transacaoList.clear();
    }
}
