package dev.kellyson.fighthub.controller;

import dev.kellyson.fighthub.dto.AlunoRequest;
import dev.kellyson.fighthub.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Void> cadastrarAluno(@RequestBody @Valid AlunoRequest alunoRequest) {
        alunoService.cadastrarAluno(alunoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
