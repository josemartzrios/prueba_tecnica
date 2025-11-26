package afore.coppel.api_prueba_tecnica.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/saludos")
@RestController

public class HolaMundoController {

    @GetMapping("/hola)")
        public String holaMundo(){
            return "Hola Mundo desde mi segunda api";
    }

}
