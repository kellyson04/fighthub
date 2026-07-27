package dev.kellyson.fighthub.dto;

import dev.kellyson.fighthub.enums.NivelTecnico;

public record CondicoesSparringResponse(
        NivelTecnico nivelTecnico,
        String restricoesFisicas,
        long quantidadePresencas,
        boolean temPresencaMinima
) {
}
