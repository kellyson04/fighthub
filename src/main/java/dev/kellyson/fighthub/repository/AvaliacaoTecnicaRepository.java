package dev.kellyson.fighthub.repository;

import dev.kellyson.fighthub.entity.AvaliacaoTecnica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoTecnicaRepository extends JpaRepository<AvaliacaoTecnica, Long> {

    List<AvaliacaoTecnica> findAllByAlunoIdOrderByDataDaAvaliacaoDesc(Long alunoId);
}
