package mx.edu.utez.Proyecto1C.controller;

import jakarta.validation.Valid;
import mx.edu.utez.Proyecto1C.DTO.CotizadorDTO;
import mx.edu.utez.Proyecto1C.Service.CotizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cotizador-hospedaje")
public class AlgoritmosController {
    @Autowired
    private CotizadorService cotizadorHospedajeService;

    @PostMapping("/calcular")
    public ResponseEntity<Map<String, Object>> calcularRenta(@Valid @RequestBody CotizadorDTO dto) {
        Map<String, Object> respuesta = cotizadorHospedajeService.calcularHospedaje(dto);
        return ResponseEntity.ok(respuesta);
    }

}

