package br.com.gabrielcaio.academic_management_system.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_disciplinas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;


    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;

    @Lob
    private String ementa;

    @ManyToMany
    @JoinTable(
        name = "tb_disciplina_prerequisito",
        joinColumns = @JoinColumn(name = "disciplina_id"),
        inverseJoinColumns = @JoinColumn(name = "prerequisito_id")
    )
    private List<Disciplina> preRequisitos = new ArrayList<>();

    @Lob
    private String bibliografiaBasica;

    @Lob
    private String bibliografiaComplementar;

    @Lob
    private String sistemaAvaliacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @JsonIgnore
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private List<Turma> turmas = new ArrayList<>();
}
