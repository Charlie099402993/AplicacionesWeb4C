package mx.edu.utez.Proyecto1C.Controller.dto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
public class RequestBody {



    @RestController
    public class AlgoritmosController {

        private final String nombreAlumno = "Carlos Apreza Gutierrez";


        @GetMapping("/fizzbuzz")
        public String fizzBuzz(@RequestParam int n) {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 == 0) {
                    System.out.println("FizzBuzz");
                } else if (i % 3 == 0) {
                    System.out.println("Fizz");
                } else if (i % 5 == 0) {
                    System.out.println("Buzz");
                } else {
                    System.out.println(i);
                }
            }
            return nombreAlumno;
        }


        @GetMapping("/fibonacci")
        public String fibonacci(@RequestParam int n) {
            if (n <= 0) return nombreAlumno;

            long a = 0, b = 1;
            for (int i = 1; i <= n; i++) {
                System.out.println(a);
                long sig = a + b;
                a = b;
                b = sig;
            }
            return nombreAlumno;
        }


        @GetMapping("/envio")
        public ResponseEntity calcularEnvio(
                @RequestParam double peso,
                @RequestParam double largo,
                @RequestParam double ancho,
                @RequestParam double alto,
                @RequestParam String tipoEnvio,
                @RequestParam double valorDeclarado
        ) {
            double volumen = largo * ancho * alto;

            if (peso > 50) {
                return ResponseEntity.badRequest().body("El paquete se rechaza: Peso superior a 50 kg");
            }
            if (largo > 150 || ancho > 150 || alto > 150) {
                return ResponseEntity.badRequest().body("El paquete se rechaza: Alguna dimensión supera 150 cm");
            }
            if (volumen > 1000000) {
                return ResponseEntity.badRequest().body("El paquete se rechaza: Volumen superior a 1,000,000 cm³");
            }

            double subtotal = 80.0;
            subtotal += peso * 12.0;

            if (volumen > 50000) {
                subtotal += 100.0;
            }

            double total = subtotal;

            if ("EXPRESS".equalsIgnoreCase(tipoEnvio)) {
                total += subtotal * 0.40; 
            } else if ("MISMO_DIA".equalsIgnoreCase(tipoEnvio)) {
                total += subtotal * 0.70;
            }

            if (valorDeclarado > 10000) {
                total += valorDeclarado * 0.02;
            }

            return ResponseEntity.ok(total);
        }
    }
}