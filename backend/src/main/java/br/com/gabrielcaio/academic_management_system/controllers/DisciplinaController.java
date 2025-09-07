
package br.com.gabrielcaio.academic_management_system.controllers;

import br.com.gabrielcaio.academic_management_system.domain.model.Disciplina;
import br.com.gabrielcaio.academic_management_system.service.DisciplinaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @PostMapping
    public ResponseEntity<Disciplina> create(@RequestBody Disciplina disciplina) {
        Disciplina saved = disciplinaService.cadastrarDisciplina(disciplina);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable Long id, @RequestBody Disciplina disciplina) {
        Disciplina atualizado = disciplinaService.atualizarDisciplina(id, disciplina);
        return ResponseEntity.ok(atualizado);
    }
    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @GetMapping
    public ResponseEntity<List<Disciplina>> list() {
        return ResponseEntity.ok(disciplinaService.listarTodas());
    }

    @PutMapping("/{disciplinaId}/professor/{professorId}")
    public ResponseEntity<Disciplina> vincularProfessor(@PathVariable Long disciplinaId, @PathVariable Long professorId) {
        Disciplina disciplina = disciplinaService.vincularProfessor(disciplinaId, professorId);
        return ResponseEntity.ok(disciplina);
    }
}
