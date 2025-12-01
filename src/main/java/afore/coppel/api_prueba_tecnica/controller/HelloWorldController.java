package afore.coppel.api_prueba_tecnica.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 1. La anotación @RestController combina @Controller y @ResponseBody.
// Indica que esta clase es un controlador que devuelve datos directamente (como JSON o texto).
@RestController
public class HelloWorldController {

    // 2. La anotación @GetMapping mapea peticiones GET a la ruta especificada.
    @GetMapping("/helloMyFriend")
    public String sayHello() {
        // 3. Este método se ejecutará cuando alguien acceda a http://localhost:8080/helloMyFriend
        return "¡Hola Mundo desde Spring Boot!";
    }
}