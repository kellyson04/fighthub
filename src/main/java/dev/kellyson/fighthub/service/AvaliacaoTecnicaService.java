package dev.kellyson.fighthub.service;

import dev.kellyson.fighthub.entity.Aluno;
import dev.kellyson.fighthub.entity.AvaliacaoTecnica;
import dev.kellyson.fighthub.repository.AlunoRepository;
import dev.kellyson.fighthub.repository.AvaliacaoTecnicaRepository;
import dev.kellyson.fighthub.repository.PerfilTreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AvaliacaoTecnicaService {

    private final AvaliacaoTecnicaRepository avaliacaoTecnicaRepository;
    private final AlunoRepository alunoRepository;
    private final PerfilTreinoRepository perfilTreinoRepository;

    public void registrarAvaliacao(Long alunoId, AvaliacaoTecnica avaliacaoTecnica) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        if (!aluno.isAtivo()) {
            throw new RuntimeException("Aluno inativo não pode receber avaliação técnica.");
        }

        if (!perfilTreinoRepository.existsByAlunoId(alunoId)) {
            throw new RuntimeException("Aluno precisa possuir perfil de treino para ser avaliado.");
        }

        avaliacaoTecnica.setAluno(aluno);
        avaliacaoTecnica.setDataDaAvaliacao(LocalDate.now());

        avaliacaoTecnicaRepository.save(avaliacaoTecnica);
    }
}
