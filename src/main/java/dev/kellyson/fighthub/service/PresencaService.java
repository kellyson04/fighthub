package dev.kellyson.fighthub.service;

import dev.kellyson.fighthub.entity.MatriculaTurma;
import dev.kellyson.fighthub.entity.Presenca;
import dev.kellyson.fighthub.repository.MatriculaTurmaRepository;
import dev.kellyson.fighthub.repository.PresencaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PresencaService {

    private final PresencaRepository presencaRepository;
    private final MatriculaTurmaRepository matriculaTurmaRepository;

    public void confirmarPresenca(Long matriculaId) {
        Presenca presenca = new Presenca();
        MatriculaTurma matriculaTurma = matriculaTurmaRepository.findById(matriculaId)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        presenca.setMatriculaTurma(matriculaTurma);
        presenca.setDataPresenca(LocalDate.now());

        presencaRepository.save(presenca);
    }
}
