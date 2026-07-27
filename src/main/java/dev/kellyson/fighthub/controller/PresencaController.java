package dev.kellyson.fighthub.controller;

import dev.kellyson.fighthub.dto.AssiduidadeResponse;
import dev.kellyson.fighthub.service.PresencaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/presencas")
@RequiredArgsConstructor
public class PresencaController {

    private final PresencaService presencaService;

    @PostMapping("{matriculaId}")
    public ResponseEntity<Void> registrarPresenca(@PathVariable Long matriculaId) {
        presencaService.confirmarPresenca(matriculaId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{matriculaId}/assiduidade")
    public ResponseEntity<AssiduidadeResponse> consultarAssiduidade(@PathVariable Long matriculaId) {
        AssiduidadeResponse assiduidade = presencaService.consultarAssiduidade(matriculaId);
        return ResponseEntity.ok(assiduidade);
    }
}
