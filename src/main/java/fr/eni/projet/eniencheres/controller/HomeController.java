package fr.eni.projet.eniencheres.controller;

import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import fr.eni.projet.eniencheres.bo.Enchere;
import fr.eni.projet.eniencheres.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {

        List<Enchere> encheres = new ArrayList<>();



        for (int i = 1; i <= 10; i++) {
            Utilisateur utilisateur = new Utilisateur("JPP le fou", "Gardon", "Jean-marie", "jpp@gmail.com", "+33214758324", "", 19000, false);

            ArticleAVendre article = new ArticleAVendre(
                    (long) i,
                    "Article " + i,
                    "Description de l'article " + i,
                    LocalDate.now(),
                    LocalDate.now().plusDays(7),
                    0,
                    100,
                    150
            );

            article.setVendeur(utilisateur);

            Enchere enchere = new Enchere(LocalDate.now(), 10+i, article);

            encheres.add(enchere);
        }

        model.addAttribute("encheres", encheres);
        return "index";
    }
}
