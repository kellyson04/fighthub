package dev.kellyson.fighthub.controller;

import dev.kellyson.fighthub.dto.TurmaRequest;
import dev.kellyson.fighthub.service.TurmaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turmas")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService turmaService;

    @PostMapping
    public ResponseEntity<Void> cadastrarTurma(@RequestBody @Valid TurmaRequest turmaRequest) {
        turmaService.cadastrarTurma(turmaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{turmaId}/alunos/{alunoId}")
    public ResponseEntity<Void> matricularAlunoNaTurma(@PathVariable Long turmaId,
                                                       @PathVariable Long alunoId) {
        turmaService.matricularAlunoNaTurma(turmaId, alunoId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
