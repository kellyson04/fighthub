package dev.kellyson.fighthub.controller;

import dev.kellyson.fighthub.dto.AlunoRequest;
import dev.kellyson.fighthub.dto.AutorizacaoSparringRequest;
import dev.kellyson.fighthub.dto.CondicoesSparringResponse;
import dev.kellyson.fighthub.dto.PerfilTreinoRequest;
import dev.kellyson.fighthub.entity.AvaliacaoTecnica;
import dev.kellyson.fighthub.entity.ControlePeso;
import dev.kellyson.fighthub.service.AlunoService;
import dev.kellyson.fighthub.service.AvaliacaoTecnicaService;
import dev.kellyson.fighthub.service.AutorizacaoSparringService;
import dev.kellyson.fighthub.service.PerfilTreinoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;
    private final PerfilTreinoService perfilTreinoService;
    private final AvaliacaoTecnicaService avaliacaoTecnicaService;
    private final AutorizacaoSparringService autorizacaoSparringService;

    @PostMapping
    public ResponseEntity<Void> cadastrarAluno(@RequestBody @Valid AlunoRequest alunoRequest) {
        alunoService.cadastrarAluno(alunoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{alunoId}/perfil-treino")
    public ResponseEntity<Void> definirPerfilDeTreino(@PathVariable Long alunoId,
                                                      @RequestBody @Valid PerfilTreinoRequest perfilTreinoRequest) {
        perfilTreinoService.definirPerfilDeTreino(alunoId, perfilTreinoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{alunoId}/pesagens")
    public ResponseEntity<Void> registrarNovaPesagem(@PathVariable Long alunoId,
                                                     @RequestBody ControlePeso controlePeso) {
        alunoService.registrarNovaPesagem(alunoId, controlePeso);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{alunoId}/avaliacoes-tecnicas")
    public ResponseEntity<Void> registrarAvaliacaoTecnica(@PathVariable Long alunoId,
                                                          @RequestBody AvaliacaoTecnica avaliacaoTecnica) {
        avaliacaoTecnicaService.registrarAvaliacao(alunoId, avaliacaoTecnica);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{alunoId}/condicoes-sparring")
    public ResponseEntity<CondicoesSparringResponse> consultarCondicoesSparring(@PathVariable Long alunoId) {
        CondicoesSparringResponse condicoes = autorizacaoSparringService.consultarCondicoes(alunoId);
        return ResponseEntity.ok(condicoes);
    }

    @PostMapping("/{alunoId}/autorizacoes-sparring")
    public ResponseEntity<Void> liberarParaSparring(@PathVariable Long alunoId,
                                                    @RequestBody AutorizacaoSparringRequest request) {
        autorizacaoSparringService.liberarParaSparring(alunoId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
