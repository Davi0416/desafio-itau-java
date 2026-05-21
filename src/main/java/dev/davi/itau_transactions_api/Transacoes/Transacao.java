package dev.davi.itau_transactions_api.Transacoes;

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
    private double valor;
    private LocalDateTime dataHora;
}
