package dev.kellyson.fighthub.entity;

import dev.kellyson.fighthub.enums.CategoriaTreino;
import dev.kellyson.fighthub.enums.FaixaPeso;
import dev.kellyson.fighthub.enums.NivelTecnico;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_perfis_treino")
public class PerfilTreino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "aluno_id", nullable = false, unique = true)
    private Aluno aluno;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_tecnico", nullable = false, length = 20)
    private NivelTecnico nivelTecnico;

    @Column(name = "experiencia_previa", nullable = false, length = 500)
    private String experienciaPrevia;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_treino", nullable = false, length = 30)
    private CategoriaTreino categoriaTreino;

    @Enumerated(EnumType.STRING)
    @Column(name = "faixa_peso", nullable = false, length = 30)
    private FaixaPeso faixaPeso;

    @Column(name = "restricoes_fisicas", columnDefinition = "TEXT")
    private String restricoesFisicas;
}
