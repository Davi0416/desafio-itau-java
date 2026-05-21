package dev.davi.itau_transactions_api.Transacoes;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TransacaoRequestDTO(
        @NotNull double valor,
        @NotNull LocalDateTime dataHora
) {
}
