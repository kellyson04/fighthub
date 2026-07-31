package dev.kellyson.fighthub.service;

import dev.kellyson.fighthub.dto.AlunoRequest;
import dev.kellyson.fighthub.entity.Aluno;
import dev.kellyson.fighthub.exception.TelefoneJaCadastradoException;
import dev.kellyson.fighthub.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public void cadastrarAluno(AlunoRequest alunoRequest) {
        if (alunoRepository.existsByTelefone(alunoRequest.telefone())) {
            // Exemplo log formato texto
            log.warn("Tentativa de cadastro com telefone já existente. telefone={}",
                    alunoRequest.telefone());
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

        try {
            alunoRepository.save(aluno);
        } catch (DataAccessException ex) {
            // Exemplo log estruturado (para Observabilidade)
            log.atError()
                    .setCause(ex)
                    .addKeyValue("event", "cadastrarAluno")
                    .addKeyValue("status", "erro")
                    .addKeyValue("erro_tipo", ex.getClass().getSimpleName())
                    .addKeyValue("telefone_final", finaisTelefone(alunoRequest.telefone()))
                    .log("Falha ao persistir aluno");
            throw ex;
        }
    }

    private String finaisTelefone(String telefone) {
        return telefone == null || telefone.length() < 4 ? "****" : telefone.substring(telefone.length() - 4);
    }

}
