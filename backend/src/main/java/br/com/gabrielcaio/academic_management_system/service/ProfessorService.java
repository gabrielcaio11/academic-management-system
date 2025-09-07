package br.com.gabrielcaio.academic_management_system.service;

import br.com.gabrielcaio.academic_management_system.domain.model.Professor;
import br.com.gabrielcaio.academic_management_system.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarPorId(Long id) {
        return professorRepository.findById(id);
    }

    @Transactional
    public Professor cadastrarProfessor(Professor professor) {
    return professorRepository.save(professor);
    }

    @Transactional
    public Professor atualizarProfessor(Long id, Professor novosDados) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    professor.setNome(novosDados.getNome());
    professor.setEmail(novosDados.getEmail());
    professor.setCpf(novosDados.getCpf());
    professor.setTitulacao(novosDados.getTitulacao());
    professor.setEspecialidades(novosDados.getEspecialidades());
    return professorRepository.save(professor);
    }
}
