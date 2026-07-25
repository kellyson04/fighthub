package dev.kellyson.fighthub.repository;

import dev.kellyson.fighthub.entity.PerfilTreino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilTreinoRepository extends JpaRepository<PerfilTreino, Long> {

    boolean existsByAlunoId(Long alunoId);
}
