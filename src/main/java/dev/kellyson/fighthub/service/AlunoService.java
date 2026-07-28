package dev.kellyson.fighthub.service;

import dev.kellyson.fighthub.dto.AlunoRequest;
import dev.kellyson.fighthub.entity.Aluno;
import dev.kellyson.fighthub.exception.TelefoneJaCadastradoException;
import dev.kellyson.fighthub.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public void cadastrarAluno(AlunoRequest alunoRequest) {
        if (alunoRepository.existsByTelefone(alunoRequest.telefone())) {
            throw new TelefoneJaCadastradoException("Telefone já cadastrado.");
        }

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
}
