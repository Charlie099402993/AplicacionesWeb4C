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
    public Map<String, Object> calcularRentaVehiculo(CotizadorDTO dto) {


        if (dto.getEdadConductor() < 18) {
            throw new ErrorPersonalizado("El conductor debe ser mayor o igual a 18 años");
        }

        if (dto.getDiasRenta() > 30) {
            throw new ErrorPersonalizado("La renta no puede superar los 30 días");
        }

        if (dto.getKilometrosEstimados() > 5000) {
            throw new ErrorPersonalizado("Los kilómetros estimados no pueden superar los 5,000 km");
        }

        if ("CAMIONETA".equalsIgnoreCase(dto.getTipoVehiculo()) && dto.getEdadConductor() < 25) {
            throw new ErrorPersonalizado("Para rentar una CAMIONETA el conductor debe tener al menos 25 años");
        }


        double costoDiario = 0.0;
        switch (dto.getTipoVehiculo().toUpperCase()) {
            case "COMPACTO":
                costoDiario = 550.0;
                break;
            case "SEDAN":
                costoDiario = 700.0;
                break;
            case "SUV":
                costoDiario = 950.0;
                break;
            case "CAMIONETA":
                costoDiario = 1200.0;
                break;
        }

        double costoRenta = costoDiario * dto.getDiasRenta();

        if (dto.getDiasRenta() >= 7) {
            costoRenta -= costoRenta * 0.10;
        }

        double kilometrosIncluidos = dto.getDiasRenta() * 100.0;
        double cargoKmAdicionales = 0.0;
        if (dto.getKilometrosEstimados() > kilometrosIncluidos) {
            double kmExtra = dto.getKilometrosEstimados() - kilometrosIncluidos;
            cargoKmAdicionales = kmExtra * 4.0;
        }

        double cargoEdad = 0.0;
        if (dto.getEdadConductor() >= 18 && dto.getEdadConductor() <= 24) {
            cargoEdad = (costoRenta + cargoKmAdicionales) * 0.15;
        }

        double cargoSeguro = 0.0;
        if (Boolean.TRUE.equals(dto.getSeguroCompleto())) {
            cargoSeguro = dto.getDiasRenta() * 180.0;
        }

        double importeTotal = costoRenta + cargoKmAdicionales + cargoEdad + cargoSeguro;

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("nombreCliente", dto.getNombreCliente());
        respuesta.put("tipoVehiculo", dto.getTipoVehiculo());
        respuesta.put("diasRenta", dto.getDiasRenta());
        respuesta.put("costoRenta", costoRenta);
        respuesta.put("cargoKmAdicionales", cargoKmAdicionales);
        respuesta.put("cargoEdad", cargoEdad);
        respuesta.put("cargoSeguro", cargoSeguro);
        respuesta.put("importeTotal", importeTotal);

        return respuesta;
    }
}
