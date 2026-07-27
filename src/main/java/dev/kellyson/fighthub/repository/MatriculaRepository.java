package dev.kellyson.fighthub.repository;

import dev.kellyson.fighthub.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    boolean existsByAlunoId(Long alunoId);

    long countByTurmaId(Long turmaId);

    Optional<Matricula> findByAlunoId(Long alunoId);
}
