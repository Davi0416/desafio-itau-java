package dev.davi.itau_transactions_api.transacoes;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TransacaoRequestDTO(
        @NotNull Double valor,
        @NotNull LocalDateTime dataHora
) {
}
