package br.com.gabrielcaio.academic_management_system.service;

import br.com.gabrielcaio.academic_management_system.domain.model.Aluno;
import br.com.gabrielcaio.academic_management_system.domain.model.Mensagem;
import br.com.gabrielcaio.academic_management_system.domain.model.Professor;
import br.com.gabrielcaio.academic_management_system.repositories.AlunoRepository;
import br.com.gabrielcaio.academic_management_system.repositories.MensagemRepository;
import br.com.gabrielcaio.academic_management_system.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MensagemService {
    private final MensagemRepository mensagemRepository;
    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;

    public MensagemService(MensagemRepository mensagemRepository, ProfessorRepository professorRepository, AlunoRepository alunoRepository) {
        this.mensagemRepository = mensagemRepository;
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public Mensagem enviarMensagem(Long remetenteId, Long destinatarioId, String conteudo) {
        Professor remetente = professorRepository.findById(remetenteId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        Aluno destinatario = alunoRepository.findById(destinatarioId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Mensagem mensagem = new Mensagem();
        mensagem.setRemetente(remetente);
        mensagem.setDestinatario(destinatario);
        mensagem.setConteudo(conteudo);
        mensagem.setDataHora(LocalDateTime.now());
        return mensagemRepository.save(mensagem);
    }

    public List<Mensagem> mensagensEnviadas(Long remetenteId) {
        return mensagemRepository.findByRemetenteId(remetenteId);
    }

    public List<Mensagem> mensagensRecebidas(Long destinatarioId) {
        return mensagemRepository.findByDestinatarioId(destinatarioId);
    }
}
