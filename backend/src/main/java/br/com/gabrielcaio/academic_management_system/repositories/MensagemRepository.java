package br.com.gabrielcaio.academic_management_system.repositories;

import br.com.gabrielcaio.academic_management_system.domain.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findByRemetenteId(Long remetenteId);
    List<Mensagem> findByDestinatarioId(Long destinatarioId);
}
