package dev.davi.itau_transactions_api.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TransacaoRequestDTO(
        @NotNull Double valor,
        @NotNull LocalDateTime dataHora
) {
}
