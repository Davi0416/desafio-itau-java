package dev.davi.itau_transactions_api.services;

import dev.davi.itau_transactions_api.models.Estatistica;
import dev.davi.itau_transactions_api.models.Transacao;
import dev.davi.itau_transactions_api.repositories.TransacoesRepository;
import org.springframework.stereotype.Service;

@Service
public class EstatisticasService {

    final TransacoesRepository repository;

    public EstatisticasService(TransacoesRepository transacoesRepository) {
        this.repository = transacoesRepository;
    }

    public Estatistica showInTime() {
        Estatistica estatistica = new Estatistica();
        var transacoes = repository.findByTime();
        var stats = transacoes.stream()
                .mapToDouble(Transacao::getValor)
                .summaryStatistics();

        estatistica.setSoma(stats.getSum());
        estatistica.setMedia(stats.getAverage());
        estatistica.setMax(stats.getMax());
        estatistica.setMin(stats.getMin());
        estatistica.setQuantidade(stats.getCount());
        return estatistica;
    }
}
