package br.com.gabrielcaio.academic_management_system.repositories;

import br.com.gabrielcaio.academic_management_system.domain.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
