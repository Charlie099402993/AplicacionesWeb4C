package mx.edu.utez.Proyecto1C.Service;
import mx.edu.utez.Proyecto1C.DTO.CotizadorDTO;
import mx.edu.utez.Proyecto1C.Exception.ErrorPersonalizado;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Service
public class CotizadorService {
    public Map<String, Object> calcularCostoEnvio(CotizadorDTO dto) {
        double volumen = dto.getLargoCm() * dto.getAnchoCm() * dto.getAltoCm();

        // Validación de regla de volumen máximo permitido (1,000,000 cm³)
        if (volumen > 1000000) {
            throw new ErrorPersonalizado("El paquete supera el volumen máximo permitido de 1,000,000 cm³");
        }

        double costoTotal = 80.0;

        costoTotal += dto.getPesoKg() * 12.0;

        if (volumen > 50000) {
            costoTotal += 100.0;
        }

        if ("EXPRESS".equalsIgnoreCase(dto.getTipoEnvio())) {
            costoTotal += costoTotal * 0.40;
        } else if ("MISMO_DIA".equalsIgnoreCase(dto.getTipoEnvio())) {
            costoTotal += costoTotal * 0.70;
        }

        if (dto.getValorDeclarado() > 10000) {
            costoTotal += dto.getValorDeclarado() * 0.02;
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("codigoPostal", dto.getCodigoPostal());
        resultado.put("volumenCm3", volumen);
        resultado.put("tipoEnvio", dto.getTipoEnvio());
        resultado.put("costoTotal", costoTotal);

        return resultado;
    }
}
