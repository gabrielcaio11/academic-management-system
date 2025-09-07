package br.com.gabrielcaio.academic_management_system.controllers;

import br.com.gabrielcaio.academic_management_system.domain.model.Matricula;
import br.com.gabrielcaio.academic_management_system.service.MatriculaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping
    public ResponseEntity<Matricula> matricularAluno(@RequestParam Long alunoId, @RequestParam Long turmaId) {
        Matricula matricula = matriculaService.matricularAlunoEmTurma(alunoId, turmaId);
        return ResponseEntity.ok(matricula);
    }

    @PutMapping("/{matriculaId}/nota-frequencia")
    public ResponseEntity<Matricula> lancarNotaEFrequencia(@PathVariable Long matriculaId, @RequestParam Double nota, @RequestParam Double frequencia) {
        Matricula matricula = matriculaService.lancarNotaEFrequencia(matriculaId, nota, frequencia);
        return ResponseEntity.ok(matricula);
    }

    @PutMapping("/{matriculaId}/status")
    public ResponseEntity<Matricula> alterarStatus(@PathVariable Long matriculaId, @RequestParam String status) {
        Matricula matricula = matriculaService.alterarStatus(matriculaId, status);
        return ResponseEntity.ok(matricula);
    }

    @GetMapping("/{matriculaId}/situacao-final")
    public ResponseEntity<String> getSituacaoFinal(@PathVariable Long matriculaId) {
        String situacao = matriculaService.getSituacaoFinal(matriculaId);
        return ResponseEntity.ok(situacao);
    }
}
