package dev.davi.itau_transactions_api.estatisticas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estatistica {
    private Double soma;
    private Double min;
    private Double max;
    private Double media;
    private Long quantidade;
}
