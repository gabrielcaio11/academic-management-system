package br.com.gabrielcaio.academic_management_system.repositories;

import br.com.gabrielcaio.academic_management_system.domain.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
