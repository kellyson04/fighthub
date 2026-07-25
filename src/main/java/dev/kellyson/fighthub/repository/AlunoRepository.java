package dev.kellyson.fighthub.repository;

import dev.kellyson.fighthub.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
