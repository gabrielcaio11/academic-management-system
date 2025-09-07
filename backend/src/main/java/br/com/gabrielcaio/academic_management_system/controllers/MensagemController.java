package br.com.gabrielcaio.academic_management_system.controllers;

import br.com.gabrielcaio.academic_management_system.domain.model.Mensagem;
import br.com.gabrielcaio.academic_management_system.service.MensagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {
    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @PostMapping
    public ResponseEntity<Mensagem> enviar(@RequestParam Long remetenteId, @RequestParam Long destinatarioId, @RequestBody String conteudo) {
        Mensagem mensagem = mensagemService.enviarMensagem(remetenteId, destinatarioId, conteudo);
        return ResponseEntity.ok(mensagem);
    }

    @GetMapping("/enviadas/{remetenteId}")
    public ResponseEntity<List<Mensagem>> enviadas(@PathVariable Long remetenteId) {
        return ResponseEntity.ok(mensagemService.mensagensEnviadas(remetenteId));
    }

    @GetMapping("/recebidas/{destinatarioId}")
    public ResponseEntity<List<Mensagem>> recebidas(@PathVariable Long destinatarioId) {
        return ResponseEntity.ok(mensagemService.mensagensRecebidas(destinatarioId));
    }
}
