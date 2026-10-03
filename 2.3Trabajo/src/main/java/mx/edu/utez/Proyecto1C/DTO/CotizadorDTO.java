package mx.edu.utez.Proyecto1C.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CotizadorDTO {

    @NotBlank(message = "El nombre del huésped es obligatorio")
        private String nombreHuesped;

        @NotBlank(message = "El tipo de habitación es obligatorio")
        @Pattern(
                regexp = "^(INDIVIDUAL|DOBLE|SUITE)$",
                message = "El tipo de habitación debe ser INDIVIDUAL, DOBLE o SUITE"
        )
        private String tipoHabitacion;

        @NotNull(message = "El número de noches es obligatorio")
        private Integer numeroNoches;

        @NotNull(message = "El número de huéspedes es obligatorio")
        private Integer numeroHuespedes;

        @NotBlank(message = "La temporada es obligatoria")
        @Pattern(
                regexp = "^(BAJA|REGULAR|ALTA)$",
                message = "La temporada debe ser BAJA, REGULAR o ALTA"
        )
        private String temporada;

        @NotNull(message = "Debe indicar si incluye desayuno")
        private Boolean incluyeDesayuno;

        @NotNull(message = "Debe indicar si incluye estacionamiento")
        private Boolean incluyeEstacionamiento;

    }



