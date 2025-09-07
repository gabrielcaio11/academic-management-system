package br.com.gabrielcaio.academic_management_system.repositories;

import br.com.gabrielcaio.academic_management_system.domain.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
