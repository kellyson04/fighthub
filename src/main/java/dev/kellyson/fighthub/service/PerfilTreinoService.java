package dev.kellyson.fighthub.service;

import dev.kellyson.fighthub.dto.PerfilTreinoRequest;
import dev.kellyson.fighthub.entity.Aluno;
import dev.kellyson.fighthub.entity.PerfilTreino;
import dev.kellyson.fighthub.enums.CategoriaTreino;
import dev.kellyson.fighthub.enums.Objetivo;
import dev.kellyson.fighthub.repository.AlunoRepository;
import dev.kellyson.fighthub.repository.PerfilTreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfilTreinoService {

    private final PerfilTreinoRepository perfilTreinoRepository;
    private final AlunoRepository alunoRepository;

    public void definirPerfilDeTreino(Long alunoId, PerfilTreinoRequest perfilTreinoRequest) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o ID: " + alunoId));

        if (!aluno.isAtivo()) {
            throw new RuntimeException("Aluno está inativo e não é possivel definir perfil de treino.");
        }

        if (perfilTreinoRepository.existsByAlunoId(alunoId)) {
            throw new RuntimeException("Aluno já possui um perfil de treino.");
        }

        PerfilTreino perfilTreino = new PerfilTreino();
        perfilTreino.setAluno(aluno);
        perfilTreino.setNivelTecnico(perfilTreinoRequest.nivelTecnico());
        perfilTreino.setExperienciaPrevia(perfilTreinoRequest.experienciaPrevia());
        perfilTreino.setFaixaPeso(perfilTreinoRequest.faixaPeso());
        perfilTreino.setRestricoesFisicas(perfilTreinoRequest.restricoesFisicas());
        perfilTreino.setCategoriaTreino(definirCategoriaTreino(aluno.getObjetivo()));


        perfilTreinoRepository.save(perfilTreino);
    }

    private CategoriaTreino definirCategoriaTreino(Objetivo objetivo) {
        return switch (objetivo) {
            case CONDICIONAMENTO_FISICO, EMAGRECIMENTO ->
                    CategoriaTreino.CONDICIONAMENTO;

            case COMPETICAO ->
                    CategoriaTreino.COMPETICAO;

            case DEFESA_PESSOAL ->
                    CategoriaTreino.TECNICA;
        };
    }


}
