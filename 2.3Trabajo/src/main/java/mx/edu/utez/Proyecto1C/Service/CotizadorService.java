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

    public Map calcularHospedaje(CotizadorDTO dto) {


        if (dto.getNumeroNoches() > 30) {
            throw new ErrorPersonalizado("El número de noches no puede superar los 30");
        }

        if ("INDIVIDUAL".equalsIgnoreCase(dto.getTipoHabitacion()) && dto.getNumeroHuespedes() > 1) {
            throw new ErrorPersonalizado("La habitación INDIVIDUAL solo permite un máximo de 1 huésped");
        }

        if ("DOBLE".equalsIgnoreCase(dto.getTipoHabitacion()) && dto.getNumeroHuespedes() > 2) {
            throw new ErrorPersonalizado("La habitación DOBLE solo permite un máximo de 2 huéspedes");
        }

        if ("SUITE".equalsIgnoreCase(dto.getTipoHabitacion()) && dto.getNumeroHuespedes() > 4) {
            throw new ErrorPersonalizado("La habitación SUITE solo permite un máximo de 4 huéspedes");
        }


        double costoPorNoche = 0.0;
        switch (dto.getTipoHabitacion().toUpperCase()) {
            case "INDIVIDUAL":
                costoPorNoche = 700.0;
                break;
            case "DOBLE":
                costoPorNoche = 1100.0;
                break;
            case "SUITE":
                costoPorNoche = 1800.0;
                break;
        }

        double costoHospedaje = costoPorNoche * dto.getNumeroNoches();

        if ("BAJA".equalsIgnoreCase(dto.getTemporada())) {
            costoHospedaje -= costoHospedaje * 0.10;
        } else if ("ALTA".equalsIgnoreCase(dto.getTemporada())) {
            costoHospedaje += costoHospedaje * 0.25;
        }

        if (dto.getNumeroNoches() >= 7) {
            costoHospedaje -= costoHospedaje * 0.08;
        }

        double costoDesayuno = 0.0;
        if (Boolean.TRUE.equals(dto.getIncluyeDesayuno())) {
            costoDesayuno = dto.getNumeroHuespedes() * dto.getNumeroNoches() * 150.0;
        }

        double costoEstacionamiento = 0.0;
        if (Boolean.TRUE.equals(dto.getIncluyeEstacionamiento())) {
            costoEstacionamiento = dto.getNumeroNoches() * 100.0;
        }

        double subtotal = costoHospedaje + costoDesayuno + costoEstacionamiento;

        double impuestoHospedaje = subtotal * 0.04;

        // Regla 9: Total final
        double total = subtotal + impuestoHospedaje;

        // Respuesta armada
        Map respuesta = new HashMap<>();
        respuesta.put("nombreHuesped", dto.getNombreHuesped());
        respuesta.put("tipoHabitacion", dto.getTipoHabitacion());
        respuesta.put("costoHospedaje", costoHospedaje);
        respuesta.put("costoDesayuno", costoDesayuno);
        respuesta.put("costoEstacionamiento", costoEstacionamiento);
        respuesta.put("subtotal", subtotal);
        respuesta.put("impuestoHospedaje", impuestoHospedaje);
        respuesta.put("total", total);

        return respuesta;
    }
}
