package dev.davi.itau_transactions_api.estatisticas;

import dev.davi.itau_transactions_api.transacoes.Transacao;
import dev.davi.itau_transactions_api.transacoes.TransacoesRepository;
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
