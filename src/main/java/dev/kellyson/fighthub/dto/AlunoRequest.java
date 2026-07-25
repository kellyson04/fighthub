package dev.kellyson.fighthub.dto;

import dev.kellyson.fighthub.enums.Objetivo;
import dev.kellyson.fighthub.enums.PlanoDesejado;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AlunoRequest(
        @NotBlank
        @Size(min = 3, max = 100)
        String nome,

        @NotBlank
        @Pattern(regexp = "^\\+?[0-9]{10,15}$")
        String telefone,

        @NotNull
        @Past
        LocalDate dataDeNascimento,

        @NotNull
        @DecimalMin("30.00")
        @Digits(integer = 3, fraction = 2)
        BigDecimal peso,

        @NotNull
        Objetivo objetivo,

        @NotNull
        PlanoDesejado planoDesejado
) {
}
