package dev.davi.itau_transactions_api.Transacoes;

import java.time.LocalDateTime;

public record TransacaoResponseDTO(
        double preco,
        LocalDateTime data
) {
}
