package mx.edu.utez.Proyecto1C.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public org.springframework.http.ResponseEntity validar(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        java.util.Map errores = new java.util.LinkedHashMap<>();

        for (org.springframework.validation.FieldError err : ex.getBindingResult().getFieldErrors()) {
            errores.put(err.getField(), err.getDefaultMessage());
        }

        return org.springframework.http.ResponseEntity.badRequest().body(errores);
    }
}
