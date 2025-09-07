package br.com.gabrielcaio.academic_management_system.repositories;

import br.com.gabrielcaio.academic_management_system.domain.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
}


