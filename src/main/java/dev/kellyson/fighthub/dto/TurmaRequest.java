package dev.kellyson.fighthub.dto;

import dev.kellyson.fighthub.enums.DiaDaSemana;
import dev.kellyson.fighthub.enums.NivelTecnico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

public record TurmaRequest(
        @NotNull
        LocalTime horario,

        @NotNull
        DiaDaSemana diaDaSemana,

        @NotBlank
        @Size(max = 100)
        String instrutor,

        @NotNull
        @Positive
        Integer limiteAlunos,

        @NotNull
        NivelTecnico nivelTurma
) {
}
