package fr.eni.projet.eniencheres.controller;

import fr.eni.projet.eniencheres.bll.ArticlesAVendre.ArticlesAVendreService;
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
    private ArticlesAVendreService articlesAVendreService;

    public HomeController(ArticlesAVendreService articlesAVendreService) {
        super();
        this.articlesAVendreService = articlesAVendreService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<ArticleAVendre> aav = articlesAVendreService.consulterArticlesAVendre();

        model.addAttribute("articlesAVendre", aav);
        return "index";
    }
}
