package dev.davi.itau_transactions_api.controllers;

import dev.davi.itau_transactions_api.dto.TransacaoRequestDTO;
import dev.davi.itau_transactions_api.models.Transacao;
import dev.davi.itau_transactions_api.services.TransacoesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/desafio")
public class TransacaoController {
    final TransacoesService service;

    public TransacaoController(TransacoesService service) {
        this.service = service;
    }

    @PostMapping("/transacao")
    public ResponseEntity<Transacao> addTransacao(@RequestBody @Valid TransacaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarTransacao(dto));
    }

    @GetMapping("/transacao")
    public ResponseEntity<List<Transacao>> showAllTransacoes() {
        return ResponseEntity.ok(service.mostrarTransacoes());
    }

    @DeleteMapping("/transacao/{id}")
    public ResponseEntity<Void> deleteTransacao(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deleteAll() {
        service.deletarTodasTransacoes();
        return ResponseEntity.ok().build();
    }
}
