// ...existing code...
package br.com.gabrielcaio.academic_management_system.service;

import br.com.gabrielcaio.academic_management_system.domain.model.Aluno;
import br.com.gabrielcaio.academic_management_system.repositories.AlunoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.Random;
import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    @Transactional
    public Aluno cadastrarAluno(Aluno aluno) {
        if (aluno.getMatricula() == null || aluno.getMatricula().isEmpty()) {
            aluno.setMatricula(gerarMatricula());
        }
        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno atualizarAluno(Long id, Aluno novosDados) {
        Optional<Aluno> optAluno = alunoRepository.findById(id);
        if (optAluno.isEmpty()) {
            throw new RuntimeException("Aluno não encontrado");
        }
        Aluno aluno = optAluno.get();
        aluno.setNome(novosDados.getNome());
        aluno.setEmail(novosDados.getEmail());
        aluno.setCpf(novosDados.getCpf());
        aluno.setDataNascimento(novosDados.getDataNascimento());
        aluno.setEndereco(novosDados.getEndereco());
        return alunoRepository.save(aluno);
    }

    private String gerarMatricula() {
        // Exemplo: AAAA2025XXXX (4 letras + ano + 4 dígitos aleatórios)
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(letras.charAt(random.nextInt(letras.length())));
        }
        sb.append("2025");
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
