
package br.com.gabrielcaio.academic_management_system.controllers;

import br.com.gabrielcaio.academic_management_system.service.MatriculaService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import br.com.gabrielcaio.academic_management_system.domain.model.Aluno;
import br.com.gabrielcaio.academic_management_system.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.net.URI;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;
    private final MatriculaService matriculaService;

    public AlunoController(AlunoService alunoService, MatriculaService matriculaService) {
        this.alunoService = alunoService;
        this.matriculaService = matriculaService;
    }
    // Histórico acadêmico do aluno
    @GetMapping("/{id}/historico")
    public ResponseEntity<?> historico(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.historicoAcademico(id));
    }

    // Notas e frequência do aluno
    @GetMapping("/{id}/notas-frequencia")
    public ResponseEntity<?> notasFrequencia(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.notasEFrequencia(id));
    }

    // Cálculo do CR
    @GetMapping("/{id}/cr")
    public ResponseEntity<Double> calcularCR(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.calcularCR(id));
    }

    // Declaração de matrícula (simples, texto)
    @GetMapping("/{id}/declaracao-matricula")
    public ResponseEntity<String> declaracaoMatricula(@PathVariable Long id) {
        String declaracao = matriculaService.gerarDeclaracaoMatricula(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=declaracao_matricula_" + id + ".txt");
        return ResponseEntity.ok().headers(headers).body(declaracao);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> list() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody Aluno aluno) {
        Aluno saved = alunoService.cadastrarAluno(aluno);
        return ResponseEntity.created(URI.create("/alunos/" + saved.getId())).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getById(@PathVariable Long id) {
        Aluno aluno = alunoService.buscarPorId(id);
        return ResponseEntity.ok(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody Aluno aluno) {
        Aluno atualizado = alunoService.atualizarAluno(id, aluno);
        return ResponseEntity.ok(atualizado);
    }
}
