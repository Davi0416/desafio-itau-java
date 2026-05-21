package dev.davi.itau_transactions_api.services;

import dev.davi.itau_transactions_api.models.Estatistica;
import dev.davi.itau_transactions_api.models.Transacao;
import dev.davi.itau_transactions_api.repositories.TransacoesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EstatisticasService {

    final TransacoesRepository repository;

    public EstatisticasService(TransacoesRepository transacoesRepository) {
        this.repository = transacoesRepository;
    }

    public Estatistica showInTime() {
        if (repository.findByTime().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

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
