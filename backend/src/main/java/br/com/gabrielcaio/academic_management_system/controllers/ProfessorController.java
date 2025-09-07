package br.com.gabrielcaio.academic_management_system.controllers;

import br.com.gabrielcaio.academic_management_system.domain.model.Professor;
import br.com.gabrielcaio.academic_management_system.service.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<List<Professor>> list() {
        return ResponseEntity.ok(professorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getById(@PathVariable Long id) {
        return professorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Professor> create(@RequestBody Professor professor) {
        Professor saved = professorService.cadastrarProfessor(professor);
        return ResponseEntity.created(URI.create("/professores/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> update(@PathVariable Long id, @RequestBody Professor professor) {
        Professor atualizado = professorService.atualizarProfessor(id, professor);
        return ResponseEntity.ok(atualizado);
    }
}
