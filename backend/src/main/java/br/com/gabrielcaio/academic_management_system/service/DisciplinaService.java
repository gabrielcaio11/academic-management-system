package br.com.gabrielcaio.academic_management_system.service;

import br.com.gabrielcaio.academic_management_system.domain.model.Disciplina;
import br.com.gabrielcaio.academic_management_system.domain.model.Professor;
import br.com.gabrielcaio.academic_management_system.repositories.DisciplinaRepository;
import br.com.gabrielcaio.academic_management_system.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DisciplinaService {
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    public List<Disciplina> listarTodas() {
        return disciplinaRepository.findAll();
    }

    @Transactional
    public Disciplina vincularProfessor(Long disciplinaId, Long professorId) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        disciplina.setProfessor(professor);
        return disciplinaRepository.save(disciplina);
    }
        @Transactional
    public Disciplina cadastrarDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    @Transactional
    public Disciplina atualizarDisciplina(Long id, Disciplina novosDados) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
        disciplina.setNome(novosDados.getNome());
        disciplina.setCargaHoraria(novosDados.getCargaHoraria());
        disciplina.setEmenta(novosDados.getEmenta());
        disciplina.setPreRequisitos(novosDados.getPreRequisitos());
        disciplina.setBibliografiaBasica(novosDados.getBibliografiaBasica());
        disciplina.setBibliografiaComplementar(novosDados.getBibliografiaComplementar());
        disciplina.setSistemaAvaliacao(novosDados.getSistemaAvaliacao());
        return disciplinaRepository.save(disciplina);
    }
}
