package dev.kellyson.fighthub.service;

import dev.kellyson.fighthub.dto.AlunoRequest;
import dev.kellyson.fighthub.entity.Aluno;
import dev.kellyson.fighthub.enums.Objetivo;
import dev.kellyson.fighthub.enums.PlanoDesejado;
import dev.kellyson.fighthub.exception.TelefoneJaCadastradoException;
import dev.kellyson.fighthub.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    private AlunoService alunoService;
    private AlunoRequest alunoRequest;

    @BeforeEach
    void setUp() {
        alunoService = new AlunoService(alunoRepository);
        alunoRequest = new AlunoRequest(
                "Kellyson Silva",
                "5511999999999",
                LocalDate.of(2000, 1, 1),
                new BigDecimal("75.00"),
                Objetivo.CONDICIONAMENTO_FISICO,
                PlanoDesejado.MENSAL
        );
    }

    @Test
    void deveCadastrarAlunoQuandoTelefoneNaoEstiverCadastrado() {
        // Arrange
        when(alunoRepository.existsByTelefone(alunoRequest.telefone())).thenReturn(false);

        // Act
        alunoService.cadastrarAluno(alunoRequest);

        // Assert
        verify(alunoRepository).save(any(Aluno.class));
    }

    @Test
    void naoDeveCadastrarAlunoQuandoTelefoneJaEstiverCadastrado() {
        // Arrange
        when(alunoRepository.existsByTelefone(alunoRequest.telefone())).thenReturn(true);

        // Act e Assert
        assertThrows(
                TelefoneJaCadastradoException.class,
                () -> alunoService.cadastrarAluno(alunoRequest)
        );

        // Assert
        verify(alunoRepository, never()).save(any(Aluno.class));
    }
}
