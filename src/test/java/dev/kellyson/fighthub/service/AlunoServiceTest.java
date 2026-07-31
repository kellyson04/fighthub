package dev.kellyson.fighthub.service;

import dev.kellyson.fighthub.dto.AlunoRequest;
import dev.kellyson.fighthub.entity.Aluno;
import dev.kellyson.fighthub.enums.ObjetivoEnum;
import dev.kellyson.fighthub.enums.PlanoDesejadoEnum;
import dev.kellyson.fighthub.exception.TelefoneJaCadastradoException;
import dev.kellyson.fighthub.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessResourceFailureException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
                ObjetivoEnum.CONDICIONAMENTO_FISICO,
                PlanoDesejadoEnum.MENSAL
        );
    }

    @Test
    void deveCadastrarAlunoQuandoTelefoneNaoEstiverCadastrado() {
        // Arrange
        when(alunoRepository.existsByTelefone(alunoRequest.telefone())).thenReturn(false);

        // Act
        alunoService.cadastrarAluno(alunoRequest);

        // Assert
        ArgumentCaptor<Aluno> captor = ArgumentCaptor.forClass(Aluno.class);
        verify(alunoRepository).save(captor.capture());

        Aluno salvo = captor.getValue();
        assertNull(salvo.getId());
        assertEquals(alunoRequest.nome(), salvo.getNome());
        assertEquals(alunoRequest.telefone(), salvo.getTelefone());
        assertEquals(alunoRequest.dataDeNascimento(), salvo.getDataDeNascimento());
        assertEquals(alunoRequest.peso(), salvo.getPeso());
        assertEquals(alunoRequest.objetivo(), salvo.getObjetivo());
        assertEquals(alunoRequest.planoDesejado(), salvo.getPlanoDesejado());
        assertTrue(salvo.isAtivo());
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

    @Test
    void devePropagarErroQuandoFalharAoPersistirAluno() {
        when(alunoRepository.existsByTelefone(alunoRequest.telefone())).thenReturn(false);
        when(alunoRepository.save(any(Aluno.class)))
                .thenThrow(new DataAccessResourceFailureException("falha banco"));

        assertThrows(
                DataAccessResourceFailureException.class,
                () -> alunoService.cadastrarAluno(alunoRequest)
        );
    }
}
