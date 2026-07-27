package dev.kellyson.fighthub.repository;

import dev.kellyson.fighthub.entity.ControlePeso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ControlePesoRepository extends JpaRepository<ControlePeso, Long> {

    List<ControlePeso> findAllByAlunoIdOrderByDataDaMedicaoDesc(Long alunoId);
}
