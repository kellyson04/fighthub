package dev.kellyson.fighthub.dto;

import dev.kellyson.fighthub.enums.FaixaPeso;
import dev.kellyson.fighthub.enums.NivelTecnico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PerfilTreinoRequest(
        @NotNull
        NivelTecnico nivelTecnico,

        @NotBlank
        @Size(max = 500)
        String experienciaPrevia,

        @NotNull
        FaixaPeso faixaPeso,

        @Size(max = 500)
        String restricoesFisicas
) {
}
