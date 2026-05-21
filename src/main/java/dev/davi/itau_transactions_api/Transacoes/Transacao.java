package dev.davi.itau_transactions_api.Transacoes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {
    private double valor;
    private LocalDateTime dataHora;
}
