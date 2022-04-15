package com.dpaula.payment.handler;

import com.dpaula.payment.error.ConflictException;
import com.dpaula.payment.error.ObjectNotFoundException;
import com.dpaula.payment.error.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Fernando de Lima
 */
@ControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RestExceptionHandler {

    public static final String ERRO_INTERNO_NO_SERVIDOR = "Erro interno no servidor!";
    public static final String INFORMACOES_INCONSISTENTES = "Informações inconsistentes!";
    public static final String CONFLITO_NAS_INFORMACOES = "Não foi possível salvar as informações!";
    public static final String INFORMACOES_NAO_ENCONTRADAS = "Não encontrada informações solicitadas!";
    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(HttpServletRequest req, Exception exception) {

        if (exception instanceof HttpMessageNotReadableException except) {

            var details = buildErrorDetails(except, req, INFORMACOES_INCONSISTENTES, HttpStatus.BAD_REQUEST,
                except.getMessage());
            return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
        }

        var details = buildErrorDetails(exception, req, ERRO_INTERNO_NO_SERVIDOR,
            HttpStatus.INTERNAL_SERVER_ERROR,
            exception.getMessage());

        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<?> handleUnprocessableEntityException(HttpServletRequest req,
                                                                UnprocessableEntityException exception) {
        var details = buildErrorDetails(exception, req, exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY,
            exception.getMessage());
        return new ResponseEntity<>(details, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(HttpServletRequest req,
                                                                   MethodArgumentNotValidException exception) {

        var mensagem = exception.getMessage();

        var fieldErrors = exception.getBindingResult()
            .getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            var e = fieldErrors.get(0);
            mensagem = e.getField() + " " + messageSource.getMessage(e, LocaleContextHolder.getLocale());
        }
        var details = buildErrorDetails(exception, req, INFORMACOES_INCONSISTENTES, HttpStatus.BAD_REQUEST,
            mensagem);
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflictException(HttpServletRequest req,
                                                     ConflictException exception) {
        var details = buildErrorDetails(exception, req, CONFLITO_NAS_INFORMACOES, HttpStatus.CONFLICT,
            exception.getMessage());
        return new ResponseEntity<>(details, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<?> handleObjectNotFoundException(HttpServletRequest req,
                                                           ObjectNotFoundException exception) {
        var details = buildErrorDetails(exception, req, INFORMACOES_NAO_ENCONTRADAS, HttpStatus.NOT_FOUND,
            exception.getMessage());
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    private static ErrorDetails buildErrorDetails(Exception exception, HttpServletRequest req,
                                                  String message,
                                                  HttpStatus httpStatus, String detailveloperMessage) {
        return ErrorDetails.builder()
            .message(message)
            .status(httpStatus.value())
            .timestamp(new Date().getTime())
            .url(req.getRequestURI())
            .detailDeveloper(DetailDeveloperMessage.builder()
                .exceptionClass(exception.getClass()
                    .getName())
                .error(detailveloperMessage)
                .stackTrace(Arrays.toString(exception.getStackTrace()))
                .build())
            .build();
    }
}
