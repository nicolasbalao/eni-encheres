package fr.eni.projet.eniencheres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
public class EniEncheresApplication {

    public static void main(String[] args) {
        SpringApplication.run(EniEncheresApplication.class, args);
    }
}
