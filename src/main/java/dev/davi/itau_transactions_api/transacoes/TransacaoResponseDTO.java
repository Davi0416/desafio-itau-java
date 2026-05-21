package dev.davi.itau_transactions_api.transacoes;

import java.time.LocalDateTime;
import java.util.UUID;

public record TransacaoResponseDTO(
        UUID id,
        Double preco,
        LocalDateTime data
) {
}
