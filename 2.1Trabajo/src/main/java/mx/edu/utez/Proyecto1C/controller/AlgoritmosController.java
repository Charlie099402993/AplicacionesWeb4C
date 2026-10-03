package mx.edu.utez.Proyecto1C.controller;

import jakarta.validation.Valid;
import mx.edu.utez.Proyecto1C.DTO.CotizadorDTO;
import mx.edu.utez.Proyecto1C.Service.CotizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cotizador")
public class AlgoritmosController {
    @Autowired
    private CotizadorService cotizadorService;

    @PostMapping("/calcular")
    public ResponseEntity<Map<String, Object>> calcularCotizacion(@Valid @RequestBody CotizadorDTO cotizacionDTO) {
        Map<String, Object> respuesta = cotizadorService.calcularCostoEnvio(cotizacionDTO);
        return ResponseEntity.ok(respuesta);

}
     }
