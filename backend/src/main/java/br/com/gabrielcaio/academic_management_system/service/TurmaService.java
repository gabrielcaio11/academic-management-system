package br.com.gabrielcaio.academic_management_system.service;

import br.com.gabrielcaio.academic_management_system.domain.model.Turma;
import br.com.gabrielcaio.academic_management_system.domain.model.Disciplina;
import br.com.gabrielcaio.academic_management_system.repositories.TurmaRepository;
import br.com.gabrielcaio.academic_management_system.repositories.DisciplinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TurmaService {

    public String getHorario(Long turmaId) {
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        return turma.getHorario();
    }

    public String getLocal(Long turmaId) {
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        return turma.getLocal();
    }

    public String getVagasInfo(Long turmaId) {
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        int ocupadas = turma.getMatriculas().size();
        int vagas = turma.getVagas();
        return "Ocupadas: " + ocupadas + " / Vagas: " + vagas;
    }
    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;

    public TurmaService(TurmaRepository turmaRepository, DisciplinaRepository disciplinaRepository) {
        this.turmaRepository = turmaRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    public List<Turma> listarTodas() {
        return turmaRepository.findAll();
    }

    @Transactional
    public Turma criarTurma(Turma turma, Long disciplinaId) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
        turma.setDisciplina(disciplina);
        return turmaRepository.save(turma);
    }

    @Transactional
    public Turma atualizarTurma(Long turmaId, Turma novosDados) {
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        turma.setAno(novosDados.getAno());
        turma.setSemestre(novosDados.getSemestre());
        // Não permite trocar disciplina diretamente
        return turmaRepository.save(turma);
    }

    @Transactional
    public Turma atualizarPlanoDeEnsino(Long turmaId, String planoDeEnsino) {
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        turma.setPlanoDeEnsino(planoDeEnsino);
        return turmaRepository.save(turma);
    }

    public List<String> listarAlunosDaTurma(Long turmaId) {
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        return turma.getMatriculas().stream()
                .map(m -> m.getAluno().getNome() + " (" + m.getAluno().getMatricula() + ")")
                .toList();
    }

    public List<String> emitirDiarioDeClasse(Long turmaId) {
    Turma turma = turmaRepository.findById(turmaId)
        .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    return turma.getMatriculas().stream()
        .map(m -> m.getAluno().getNome() + " | Matrícula: " + m.getAluno().getMatricula() +
            " | Nota: " + (m.getNotaFinal() != null ? m.getNotaFinal() : "-") +
            " | Frequência: " + (m.getFrequencia() != null ? m.getFrequencia() : "-"))
        .toList();
    }
}
