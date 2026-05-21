package dev.davi.itau_transactions_api.controllers;

import dev.davi.itau_transactions_api.models.Estatistica;
import dev.davi.itau_transactions_api.services.EstatisticasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticaController {

    final EstatisticasService service;

    public EstatisticaController(EstatisticasService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Estatistica> showAllTransacoes() {
        return ResponseEntity.ok(service.showInTime());
    }
}
