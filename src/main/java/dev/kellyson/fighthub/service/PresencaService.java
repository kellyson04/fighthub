package dev.kellyson.fighthub.service;

import dev.kellyson.fighthub.dto.AssiduidadeResponse;
import dev.kellyson.fighthub.entity.Matricula;
import dev.kellyson.fighthub.entity.Presenca;
import dev.kellyson.fighthub.repository.MatriculaRepository;
import dev.kellyson.fighthub.repository.PresencaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PresencaService {

    private final PresencaRepository presencaRepository;
    private final MatriculaRepository matriculaRepository;

    public void confirmarPresenca(Long matriculaId) {
        Presenca presenca = new Presenca();
        Matricula matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        presenca.setMatricula(matricula);
        presenca.setDataPresenca(LocalDate.now());

        presencaRepository.save(presenca);
    }

    public AssiduidadeResponse consultarAssiduidade(Long matriculaId) {
        Matricula matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        long totalPresencas = presencaRepository.countByMatriculaId(matricula.getId());

        return new AssiduidadeResponse(totalPresencas);
    }
}
