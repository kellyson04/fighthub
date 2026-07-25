package dev.kellyson.fighthub.repository;

import dev.kellyson.fighthub.entity.Turma;
import dev.kellyson.fighthub.enums.DiaDaSemana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    boolean existsByInstrutorIgnoreCaseAndDiaDaSemanaAndHorario(String instrutor,
                                                                DiaDaSemana diaDaSemana,
                                                                LocalTime horario);
}
