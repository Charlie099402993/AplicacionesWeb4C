package mx.edu.utez.Proyecto1C.Controller.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

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