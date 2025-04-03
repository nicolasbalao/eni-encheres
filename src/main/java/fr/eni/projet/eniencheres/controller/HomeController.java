package fr.eni.projet.eniencheres.controller;

import fr.eni.projet.eniencheres.bll.Encheres.EncheresService;
import fr.eni.projet.eniencheres.bo.Enchere;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.lang.Integer.parseInt;

@Controller
public class HomeController {
    private EncheresService encheresService;

    public HomeController(EncheresService encheresService) {
        super();
        this.encheresService = encheresService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Enchere> encheres = encheresService.consulterEncheres();

        model.addAttribute("encheres", encheres);
        return "index";
    }
    @PostMapping("/")
    public String handleForm(
            @RequestParam(required = false) String searchBar,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) boolean typeChoix,
            @RequestParam(required = false) String achatSelect,
            @RequestParam(required = false) String venteSelect,
            Model model,
            Authentication auth
    ) {
        String userPseudo = auth.getName();

        List<Enchere> encheres = encheresService.consulterEncheres(searchBar, category, typeChoix, parseInt((achatSelect == null) ? "0" : achatSelect), parseInt((venteSelect == null) ? "0" : venteSelect), userPseudo);

        model.addAttribute("encheres", encheres);
        return "index";
    }
}
