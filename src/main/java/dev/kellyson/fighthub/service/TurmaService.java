package dev.kellyson.fighthub.service;

import dev.kellyson.fighthub.dto.TurmaRequest;
import dev.kellyson.fighthub.entity.Turma;
import dev.kellyson.fighthub.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;

    public void cadastrarTurma(TurmaRequest turmaRequest) {
        Turma turma = new Turma();

        if (turmaRepository.existsByInstrutorIgnoreCaseAndDiaDaSemanaAndHorario(turmaRequest.instrutor().trim(),
                                                                                turmaRequest.diaDaSemana(),
                                                                                turmaRequest.horario())) {
            throw new RuntimeException("Já existe uma turma com o mesmo instrutor e horário.");
        }

        turma.setHorario(turmaRequest.horario());
        turma.setDiaDaSemana(turmaRequest.diaDaSemana());
        turma.setInstrutor(turmaRequest.instrutor().trim());
        turma.setLimiteAlunos(turmaRequest.limiteAlunos());
        turma.setNivelTurma(turmaRequest.nivelTurma());
        turma.setAbertaParaMatricula(true);

        turmaRepository.save(turma);
    }
}
