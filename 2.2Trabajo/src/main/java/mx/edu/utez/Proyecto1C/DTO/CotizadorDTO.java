package mx.edu.utez.Proyecto1C.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CotizadorDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotNull(message = "La edad del conductor es obligatoria")
    private Integer edadConductor;

    @NotBlank(message = "El tipo de vehículo es obligatorio")
    @Pattern(
            regexp = "^(COMPACTO|SEDAN|SUV|CAMIONETA)$",
            message = "El tipo de vehículo debe ser COMPACTO, SEDAN, SUV o CAMIONETA"
    )
    private String tipoVehiculo;

    @NotNull(message = "El número de días de renta es obligatorio")
    private Integer diasRenta;

    @NotNull(message = "Los kilómetros estimados son obligatorios")
    private Double kilometrosEstimados;

    @NotNull(message = "La indicación de seguro completo es obligatoria")
    private Boolean seguroCompleto;
}

