package br.com.gabrielcaio.academic_management_system.domain.model;

import br.com.gabrielcaio.academic_management_system.domain.enums.StatusMatricula;
import jakarta.persistence.*;


@Entity
@Table(name = "tb_matriculas", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "aluno_id", "turma_id" })
})
public class Matricula {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nota_final")
    private Double notaFinal;

    @Column(nullable = false)
    private Double frequencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMatricula status = StatusMatricula.ATIVA;

    public Matricula() {}

    public Matricula(Long id, Double notaFinal, Double frequencia, Aluno aluno, Turma turma, StatusMatricula status) {
        this.id = id;
        this.notaFinal = notaFinal;
        this.frequencia = frequencia;
        this.aluno = aluno;
        this.turma = turma;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getNotaFinal() { return notaFinal; }
    public void setNotaFinal(Double notaFinal) { this.notaFinal = notaFinal; }
    public Double getFrequencia() { return frequencia; }
    public void setFrequencia(Double frequencia) { this.frequencia = frequencia; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public Turma getTurma() { return turma; }
    public void setTurma(Turma turma) { this.turma = turma; }
    public StatusMatricula getStatus() { return status; }
    public void setStatus(StatusMatricula status) { this.status = status; }

    public String getSituacaoFinal() {
        if (status != StatusMatricula.ATIVA) {
            return status.name();
        }
        if (notaFinal == null || frequencia == null) {
            return "EM ANDAMENTO";
        }
        if (frequencia < 75.0) {
            return "REPROVADO POR FREQUÊNCIA";
        }
        if (notaFinal >= 7.0) {
            return "APROVADO";
        } else if (notaFinal >= 5.0) {
            return "RECUPERAÇÃO";
        } else {
            return "REPROVADO";
        }
    }
}
