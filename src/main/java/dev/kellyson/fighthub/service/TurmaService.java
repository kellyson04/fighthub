package dev.kellyson.fighthub.service;

import dev.kellyson.fighthub.dto.TurmaRequest;
import dev.kellyson.fighthub.entity.Aluno;
import dev.kellyson.fighthub.entity.MatriculaTurma;
import dev.kellyson.fighthub.entity.PerfilTreino;
import dev.kellyson.fighthub.entity.Turma;
import dev.kellyson.fighthub.repository.AlunoRepository;
import dev.kellyson.fighthub.repository.MatriculaTurmaRepository;
import dev.kellyson.fighthub.repository.PerfilTreinoRepository;
import dev.kellyson.fighthub.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final AlunoRepository alunoRepository;
    private final PerfilTreinoRepository perfilTreinoRepository;
    private final MatriculaTurmaRepository matriculaTurmaRepository;

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

    public void matricularAlunoNaTurma(Long turmaId, Long alunoId) {
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada com o ID: " + turmaId));

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o ID: " + alunoId));

        if (!aluno.isAtivo()) {
            throw new RuntimeException("Aluno inativo não pode ser matriculado em uma turma.");
        }

        if (!turma.isAbertaParaMatricula()) {
            throw new RuntimeException("Turma não está aberta para matrícula.");
        }

        PerfilTreino perfilTreino = perfilTreinoRepository.findByAlunoId(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não possui perfil de treino."));

        if (perfilTreino.getNivelTecnico() != turma.getNivelTurma()) {
            throw new RuntimeException("Nível do aluno não é compatível com o nível da turma.");
        }

        if (matriculaTurmaRepository.existsByAlunoIdAndTurmaId(alunoId, turmaId)) {
            throw new RuntimeException("Aluno já está matriculado nesta turma.");
        }

        long quantidadeAlunos = matriculaTurmaRepository.countByTurmaId(turmaId);

        if (quantidadeAlunos >= turma.getLimiteAlunos()) {
            throw new RuntimeException("Turma atingiu o limite de alunos.");
        }

        MatriculaTurma matriculaTurma = new MatriculaTurma();
        matriculaTurma.setAluno(aluno);
        matriculaTurma.setTurma(turma);

        matriculaTurmaRepository.save(matriculaTurma);
    }
}
