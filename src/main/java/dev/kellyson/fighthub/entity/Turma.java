package dev.kellyson.fighthub.entity;

import dev.kellyson.fighthub.enums.DiaDaSemana;
import dev.kellyson.fighthub.enums.NivelTecnico;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_turmas")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalTime horario;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_da_semana", nullable = false, length = 20)
    private DiaDaSemana diaDaSemana;

    @Column(nullable = false, length = 100)
    private String instrutor;

    @Column(name = "limite_alunos", nullable = false)
    private Integer limiteAlunos;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_turma", nullable = false, length = 20)
    private NivelTecnico nivelTurma;

    @Column(name = "aberta_para_matricula", nullable = false)
    private boolean abertaParaMatricula;
}
