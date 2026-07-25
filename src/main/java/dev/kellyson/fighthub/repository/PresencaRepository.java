package dev.kellyson.fighthub.repository;

import dev.kellyson.fighthub.entity.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresencaRepository extends JpaRepository<Presenca,Long> {
    long countByMatriculaTurmaId(Long matriculaId);
}
