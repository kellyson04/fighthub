package dev.kellyson.fighthub.repository;

import dev.kellyson.fighthub.entity.MatriculaTurma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaTurmaRepository extends JpaRepository<MatriculaTurma, Long> {

    boolean existsByAlunoIdAndTurmaId(Long alunoId, Long turmaId);

    long countByTurmaId(Long turmaId);
}
