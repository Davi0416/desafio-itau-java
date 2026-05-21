package dev.davi.itau_transactions_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {
    private UUID id;
    private Double valor;
    private LocalDateTime dataHora;
}
