package dev.kellyson.fighthub.service;

import dev.kellyson.fighthub.dto.AlunoRequest;
import dev.kellyson.fighthub.entity.Aluno;
import dev.kellyson.fighthub.entity.ControlePeso;
import dev.kellyson.fighthub.entity.PerfilTreino;
import dev.kellyson.fighthub.enums.FaixaPeso;
import dev.kellyson.fighthub.repository.AlunoRepository;
import dev.kellyson.fighthub.repository.ControlePesoRepository;
import dev.kellyson.fighthub.repository.PerfilTreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final PerfilTreinoRepository perfilTreinoRepository;
    private final ControlePesoRepository controlePesoRepository;

    public void cadastrarAluno(AlunoRequest alunoRequest) {
        Aluno aluno = new Aluno(
                null,
                alunoRequest.nome(),
                alunoRequest.telefone(),
                alunoRequest.dataDeNascimento(),
                alunoRequest.peso(),
                alunoRequest.objetivo(),
                alunoRequest.planoDesejado(),
                true
        );

        alunoRepository.save(aluno);
    }

    public void registrarNovaPesagem(Long alunoId, ControlePeso controlePeso) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        PerfilTreino perfilTreino = perfilTreinoRepository.findByAlunoId(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não possui perfil de treino"));

        FaixaPeso faixaAnterior = perfilTreino.getFaixaPeso();
        FaixaPeso faixaAtual = definirFaixaPeso(controlePeso.getPeso());

        if (faixaAnterior != faixaAtual) {
            perfilTreino.setFaixaPeso(faixaAtual);
        }

        aluno.setPeso(controlePeso.getPeso());
        controlePeso.setAluno(aluno);
        controlePeso.setDataDaMedicao(LocalDate.now());

        alunoRepository.save(aluno);
        perfilTreinoRepository.save(perfilTreino);
        controlePesoRepository.save(controlePeso);
    }

    private FaixaPeso definirFaixaPeso(BigDecimal peso) {
        if (peso.compareTo(new BigDecimal("50.802")) <= 0) {
            return FaixaPeso.MOSCA;
        }

        if (peso.compareTo(new BigDecimal("53.525")) <= 0) {
            return FaixaPeso.GALO;
        }

        if (peso.compareTo(new BigDecimal("57.153")) <= 0) {
            return FaixaPeso.PENA;
        }

        if (peso.compareTo(new BigDecimal("61.235")) <= 0) {
            return FaixaPeso.LEVE;
        }

        if (peso.compareTo(new BigDecimal("66.678")) <= 0) {
            return FaixaPeso.MEIO_MEDIO;
        }

        if (peso.compareTo(new BigDecimal("72.574")) <= 0) {
            return FaixaPeso.MEDIO;
        }

        if (peso.compareTo(new BigDecimal("79.378")) <= 0) {
            return FaixaPeso.MEIO_PESADO;
        }

        return FaixaPeso.PESADO;
    }
}
