
package br.com.gabrielcaio.academic_management_system.controllers;

import br.com.gabrielcaio.academic_management_system.domain.model.Turma;
import br.com.gabrielcaio.academic_management_system.service.TurmaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping
    public ResponseEntity<List<Turma>> list() {
        return ResponseEntity.ok(turmaService.listarTodas());
    }

    @PostMapping
    public ResponseEntity<Turma> create(@RequestBody Turma turma, @RequestParam Long disciplinaId) {
        Turma saved = turmaService.criarTurma(turma, disciplinaId);
        return ResponseEntity.created(URI.create("/turmas/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> update(@PathVariable Long id, @RequestBody Turma turma) {
        Turma atualizado = turmaService.atualizarTurma(id, turma);
        return ResponseEntity.ok(atualizado);
    }

    @PutMapping("/{id}/plano-ensino")
    public ResponseEntity<Turma> atualizarPlanoDeEnsino(@PathVariable Long id, @RequestBody String planoDeEnsino) {
        Turma turma = turmaService.atualizarPlanoDeEnsino(id, planoDeEnsino);
        return ResponseEntity.ok(turma);
    }

    @GetMapping("/{id}/alunos")
    public ResponseEntity<List<?>> listarAlunosDaTurma(@PathVariable Long id) {
        List<?> alunos = turmaService.listarAlunosDaTurma(id);
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}/diario-classe")
    public ResponseEntity<List<?>> emitirDiarioDeClasse(@PathVariable Long id) {
        List<?> diario = turmaService.emitirDiarioDeClasse(id);
        return ResponseEntity.ok(diario);
    }

    @GetMapping("/{id}/horario")
    public ResponseEntity<String> getHorario(@PathVariable Long id) {
        String horario = turmaService.getHorario(id);
        return ResponseEntity.ok(horario);
    }

    @GetMapping("/{id}/local")
    public ResponseEntity<String> getLocal(@PathVariable Long id) {
        String local = turmaService.getLocal(id);
        return ResponseEntity.ok(local);
    }

    @GetMapping("/{id}/vagas")
    public ResponseEntity<String> getVagas(@PathVariable Long id) {
        String vagas = turmaService.getVagasInfo(id);
        return ResponseEntity.ok(vagas);
    }
}
