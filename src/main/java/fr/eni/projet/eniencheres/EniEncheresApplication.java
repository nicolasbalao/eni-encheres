package fr.eni.projet.eniencheres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class EniEncheresApplication {

    public static void main(String[] args) {
        SpringApplication.run(EniEncheresApplication.class, args);
    }
}
