package fr.eni.projet.eniencheres.controller;

import fr.eni.projet.eniencheres.bll.Encheres.EncheresService;
import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import fr.eni.projet.eniencheres.bo.Enchere;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import org.springframework.ui.Model;

@Controller
public class HomeController {
    private EncheresService articlesAVendreService;

    public HomeController(EncheresService articlesAVendreService) {
        super();
        this.articlesAVendreService = articlesAVendreService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Enchere> encheres = articlesAVendreService.consulterArticlesAVendre();

        model.addAttribute("encheres", encheres);
        return "index";
    }
}
