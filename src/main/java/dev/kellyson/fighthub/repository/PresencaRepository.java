package dev.kellyson.fighthub.repository;

import dev.kellyson.fighthub.entity.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PresencaRepository extends JpaRepository<Presenca,Long> {
    long countByMatriculaId(Long matriculaId);

    long countByMatriculaIdAndDataPresencaBetween(Long matriculaId, LocalDate inicio, LocalDate fim);
}
