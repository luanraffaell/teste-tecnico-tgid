package com.tgid.api.exceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.tgid.services.exceptions.EntidadeNaoEncontradaException;
import com.tgid.services.exceptions.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Throwable rootCause = ex;
		rootCause = ex.getCause();
        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, (HttpStatus)status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, (HttpStatus)status, request);
        }

        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisição está invalido. Verifique erro de sintaxe.";
        Problem problem = createProblemBuilder((HttpStatus) status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
        BindingResult bidingResult =  ex.getBindingResult();

        List<Problem.Field> problemFields = bidingResult.getFieldErrors().stream()
                .map(fildErrors -> {
                    String message = messageSource.getMessage(fildErrors, LocaleContextHolder.getLocale());
                    return Problem.Field.builder()
                            .name(fildErrors.getField())
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        ProblemType problemType = ProblemType.DADOS_INVALIDOS;
        Problem problem = createProblemBuilder((HttpStatus) status, problemType, detail).fields(problemFields).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatusCode status, WebRequest request) {
        String path = ex.getRequestURL();
        String detail = String.format("O recurso '%s', que você tentou acessar é, inexistente.",path );
        Problem problem = createProblemBuilder((HttpStatus) status, ProblemType.RECURSO_NAO_ENCONTRADO, detail).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatusCode status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers,
                    (HttpStatus) status, request);
        }
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
                                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        String path = ex.getParameter().getParameterName();
        String value = ex.getValue().toString();
        String type = ex.getRequiredType().getSimpleName();
        String details = String.format(
                "O parametro de URL '%s' recebeu o valor '%s' "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo '%s'.",
                path, value, type);
        Problem problem = createProblemBuilder(status, problemType, details).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
        String details = String.format(
                "A propriedade '%s' não existe. " + "Corrija ou remova essa propriedade e tente novamente.", path);
        Problem problem = createProblemBuilder(status, problemType, details).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
        String detail = String.format(
                "A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido"
                        + ". Corrija e informe um valor compatível com o tipo '%s'.",
                path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaughy(Exception ex, WebRequest request){
        ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String detail = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.";
        Problem problem = createProblemBuilder(status, problemType, detail).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEstadoNaoEncontradoException(EntidadeNaoEncontradaException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        Problem problem = createProblemBuilder(status, problemType, ex.getMessage()).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Problem problem = createProblemBuilder(status, ProblemType.ERRO_NEGOCIO, ex.getMessage()).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }



    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Problem.builder().title(status.getReasonPhrase()).status(status.value()).build();
        } else if (body instanceof String) {
            body = Problem.builder().title((String) body).status(status.value()).build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
        return Problem.builder().status(status.value()).type(problemType.getUri()).title(problemType.getTitle())
                .detail(detail).timeStamp(LocalDateTime.now());
    }
}
