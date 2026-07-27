package dev.kellyson.fighthub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_avaliacoes_tecnicas")
public class AvaliacaoTecnica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String golpes;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String defesa;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String movimentacao;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String condicionamento;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Column(name = "data_da_avaliacao", nullable = false)
    private LocalDate dataDaAvaliacao;

    @Column(name = "responsavel_pela_avaliacao", nullable = false, length = 100)
    private String responsavelPelaAvaliacao;
}
