package dev.kellyson.fighthub.service;

import dev.kellyson.fighthub.dto.AutorizacaoSparringRequest;
import dev.kellyson.fighthub.dto.CondicoesSparringResponse;
import dev.kellyson.fighthub.entity.Aluno;
import dev.kellyson.fighthub.entity.AutorizacaoSparring;
import dev.kellyson.fighthub.entity.Matricula;
import dev.kellyson.fighthub.entity.PerfilTreino;
import dev.kellyson.fighthub.repository.AlunoRepository;
import dev.kellyson.fighthub.repository.AutorizacaoSparringRepository;
import dev.kellyson.fighthub.repository.MatriculaRepository;
import dev.kellyson.fighthub.repository.PerfilTreinoRepository;
import dev.kellyson.fighthub.repository.PresencaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AutorizacaoSparringService {

    private static final long PRESENCA_MINIMA = 8;

    private final AlunoRepository alunoRepository;
    private final PerfilTreinoRepository perfilTreinoRepository;
    private final MatriculaRepository matriculaRepository;
    private final PresencaRepository presencaRepository;
    private final AutorizacaoSparringRepository autorizacaoSparringRepository;

    public CondicoesSparringResponse consultarCondicoes(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        if (!aluno.isAtivo()) {
            throw new RuntimeException("Aluno inativo não pode participar de sparring.");
        }

        PerfilTreino perfilTreino = perfilTreinoRepository.findByAlunoId(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não possui perfil de treino."));

        Matricula matricula = matriculaRepository.findByAlunoId(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não possui matrícula."));

        LocalDate hoje = LocalDate.now();
        LocalDate inicioDoMes = hoje.withDayOfMonth(1);
        LocalDate fimDoMes = hoje.withDayOfMonth(hoje.lengthOfMonth());

        long quantidadePresencas = presencaRepository
                .countByMatriculaIdAndDataPresencaBetween(matricula.getId(), inicioDoMes, fimDoMes);

        return new CondicoesSparringResponse(
                perfilTreino.getNivelTecnico(),
                perfilTreino.getRestricoesFisicas(),
                quantidadePresencas,
                quantidadePresencas >= PRESENCA_MINIMA
        );
    }

    public void liberarParaSparring(Long alunoId, AutorizacaoSparringRequest request) {
        CondicoesSparringResponse condicoes = consultarCondicoes(alunoId);

        if (!condicoes.temPresencaMinima()) {
            throw new RuntimeException("Aluno não possui o mínimo de 8 presenças no mês.");
        }

        if (!request.equipamentosConfirmados()) {
            throw new RuntimeException("O uso dos equipamentos obrigatórios deve ser confirmado.");
        }

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        AutorizacaoSparring autorizacaoSparring = new AutorizacaoSparring();
        autorizacaoSparring.setAluno(aluno);
        autorizacaoSparring.setInstrutor(request.instrutor());
        autorizacaoSparring.setDataDoSparring(LocalDate.now());

        autorizacaoSparringRepository.save(autorizacaoSparring);
    }
}
