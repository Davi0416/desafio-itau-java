package dev.davi.itau_transactions_api.Transacoes;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
