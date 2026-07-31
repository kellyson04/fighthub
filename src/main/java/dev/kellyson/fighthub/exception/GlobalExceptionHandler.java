package dev.kellyson.fighthub.exception;

import dev.kellyson.fighthub.dto.ErroResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TelefoneJaCadastradoException.class)
    public ResponseEntity<ErroResponse> tratarTelefoneJaCadastrado(
            TelefoneJaCadastradoException exception
    ) {
        ErroResponse erroResponse = new ErroResponse(
                HttpStatus.CONFLICT.value(),
                exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(erroResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> tratarErroInterno(Exception exception,
                                                          HttpServletRequest request) {

        ErroResponse erroResponse = new ErroResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro interno."
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erroResponse);
    }
}
