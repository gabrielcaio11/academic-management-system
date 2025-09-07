
package br.com.gabrielcaio.academic_management_system.service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.gabrielcaio.academic_management_system.domain.model.Aluno;
import br.com.gabrielcaio.academic_management_system.domain.model.Matricula;
import br.com.gabrielcaio.academic_management_system.domain.model.Turma;
import br.com.gabrielcaio.academic_management_system.repositories.AlunoRepository;
import br.com.gabrielcaio.academic_management_system.repositories.MatriculaRepository;
import br.com.gabrielcaio.academic_management_system.repositories.TurmaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatriculaService {

    public List<Matricula> historicoAcademico(Long alunoId) {
        return matriculaRepository.findAll().stream()
                .filter(m -> m.getAluno().getId().equals(alunoId))
                .collect(Collectors.toList());
    }

    public List<String> notasEFrequencia(Long alunoId) {
        return matriculaRepository.findAll().stream()
                .filter(m -> m.getAluno().getId().equals(alunoId))
                .map(m -> {
                    String disciplina = m.getTurma().getDisciplina().getNome();
                    String nota = m.getNotaFinal() != null ? m.getNotaFinal().toString() : "-";
                    String freq = m.getFrequencia() != null ? m.getFrequencia().toString() : "-";
                    return disciplina + ": Nota = " + nota + ", Frequência = " + freq;
                })
                .collect(Collectors.toList());
    }

    public Double calcularCR(Long alunoId) {
        List<Matricula> matriculas = matriculaRepository.findAll().stream()
                .filter(m -> m.getAluno().getId().equals(alunoId) && m.getNotaFinal() != null)
                .collect(Collectors.toList());
        if (matriculas.isEmpty()) return 0.0;
        double soma = matriculas.stream().mapToDouble(Matricula::getNotaFinal).sum();
        return soma / matriculas.size();
    }

    public String gerarDeclaracaoMatricula(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        List<Matricula> matriculas = historicoAcademico(alunoId);
        StringBuilder sb = new StringBuilder();
        sb.append("Declaração de Matrícula\n");
        sb.append("Aluno: ").append(aluno.getNome()).append("\n");
        sb.append("Matrícula: ").append(aluno.getMatricula()).append("\n");
        sb.append("Disciplinas matriculadas:\n");
        for (Matricula m : matriculas) {
            sb.append("- ").append(m.getTurma().getDisciplina().getNome()).append(" (Ano: ")
              .append(m.getTurma().getAno()).append(", Semestre: ")
              .append(m.getTurma().getSemestre()).append(")\n");
        }
        return sb.toString();
    }
    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository, AlunoRepository alunoRepository, TurmaRepository turmaRepository) {
        this.matriculaRepository = matriculaRepository;
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
    }

    @Transactional
    public Matricula matricularAlunoEmTurma(Long alunoId, Long turmaId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        if (turma.getMatriculas().size() >= turma.getVagas()) {
            throw new RuntimeException("Turma lotada: número de vagas excedido");
        }
        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setTurma(turma);
        matricula.setFrequencia(0.0);
        matricula.setNotaFinal(null);
        return matriculaRepository.save(matricula);
    }

    @Transactional
    public Matricula lancarNotaEFrequencia(Long matriculaId, Double nota, Double frequencia) {
        Matricula matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
        matricula.setNotaFinal(nota);
        matricula.setFrequencia(frequencia);
        return matriculaRepository.save(matricula);
    }
    @Transactional
    public Matricula alterarStatus(Long matriculaId, String status) {
        Matricula matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
        matricula.setStatus(br.com.gabrielcaio.academic_management_system.domain.enums.StatusMatricula.valueOf(status.toUpperCase()));
        return matriculaRepository.save(matricula);
    }

    public String getSituacaoFinal(Long matriculaId) {
        Matricula matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
        return matricula.getSituacaoFinal();
    }
}
