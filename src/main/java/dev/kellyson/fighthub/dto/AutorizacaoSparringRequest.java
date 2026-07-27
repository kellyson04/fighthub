package dev.kellyson.fighthub.dto;

public record AutorizacaoSparringRequest(
        String instrutor,
        boolean equipamentosConfirmados
) {
}
