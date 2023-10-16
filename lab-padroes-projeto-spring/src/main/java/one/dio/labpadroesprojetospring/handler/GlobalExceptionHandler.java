package one.dio.labpadroesprojetospring.handler;

import java.lang.reflect.UndeclaredThrowableException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.annotation.Resource;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Resource
    private MessageSource messageSource;

    /**
     * Retorna um objeto do tipo Headers;
     * 
     * @return
     */
    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    /**
     * retorna o corpo do erro da aplicação
     * 
     * @param message
     * @param statusCode
     * @return
     */
    private ResponseError responseError(String message, HttpStatus statusCode) {
        ResponseError responseError = new ResponseError();
        responseError.setStatus("error");
        responseError.setError(message);
        responseError.setStatusCode(statusCode.value());
        return responseError;
    }

    /**
     * intercepta as exceções do sistema e verifica se é uma exceção genérica ou de
     * negócio;
     * 
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleGeneral(Exception e, WebRequest request) {
        if (e.getClass().isAssignableFrom(UndeclaredThrowableException.class)) {
            UndeclaredThrowableException exception = (UndeclaredThrowableException) e;
            return handleBusinessException((BusinessException) exception.getUndeclaredThrowable(), request);
        } else {
            String message = messageSource.getMessage("error.server", new Object[] { e.getMessage() }, null);
            ResponseError error = responseError(message, HttpStatus.INTERNAL_SERVER_ERROR);
            return handleExceptionInternal(e, error, headers(), HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }

    /**
     * é destinado a criar um ResponseEntity contendo o nosso ResponseError
     * devidamente estruturado.
     * Toda exceção de negócio vai retornar uma resposta HTTP com status code 409 -
     * CONFLICT, pois consideramos exceções de negócio como conflitos de fluxo.
     * 
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler({ BusinessException.class })
    private ResponseEntity<Object> handleBusinessException(BusinessException e, WebRequest request) {
        ResponseError error = responseError(e.getMessage(), HttpStatus.CONFLICT);
        return handleExceptionInternal(e, error, headers(), HttpStatus.CONFLICT, request);
    }
}