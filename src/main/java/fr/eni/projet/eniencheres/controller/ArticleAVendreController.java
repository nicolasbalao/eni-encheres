package fr.eni.projet.eniencheres.controller;

import fr.eni.projet.eniencheres.bll.articleAVendre.ArticleAVendreService;
import fr.eni.projet.eniencheres.bo.Adresse;
import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import fr.eni.projet.eniencheres.bo.Categorie;
import fr.eni.projet.eniencheres.bo.Utilisateur;
import fr.eni.projet.eniencheres.dal.adresse.AdresseRepository;
import fr.eni.projet.eniencheres.dal.categorie.CategorieRepository;
import fr.eni.projet.eniencheres.exception.BusinessException;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/articles")
public class ArticleAVendreController {

    private final CategorieRepository categorieRepository;
    private final AdresseRepository adresseRepository;
    private final ArticleAVendreService articleAVendreService;

    public ArticleAVendreController(final CategorieRepository categorieRepository, AdresseRepository adresseRepository, ArticleAVendreService articleAVendreService) {
        this.categorieRepository = categorieRepository;
        this.adresseRepository = adresseRepository;
        this.articleAVendreService = articleAVendreService;
    }

    @ModelAttribute("categories")
    public List<Categorie> categories() {
        return this.categorieRepository.getCategories();
    }

    // TODO: Add user adresse to
    @ModelAttribute("adresses")
    public List<Adresse> adresses(Authentication authentication) {
        List<Adresse> eniAdresses = this.adresseRepository.findEniAdresse();
        Adresse userAdresse = this.adresseRepository.findAdresseByUtilisateurPseudo(authentication.getName());

        Set<Adresse> adresses = new HashSet<>(eniAdresses);
        adresses.add(userAdresse);
        return new ArrayList<>(adresses);
    }

    @GetMapping("/sell")
    public String sellArticlePage(Model model) {
        model.addAttribute("articleAVendre", new ArticleAVendre());
        return "article/sell";
    }

    @PostMapping("/sell")
    public String sellArticle(@Valid @ModelAttribute("articleAVendre") ArticleAVendre articleAVendre, BindingResult bindingResult, Model model, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("articleAVendre", articleAVendre);
            return "article/sell";
        }

        try {
            articleAVendre.setVendeur(new Utilisateur());
            articleAVendre.getVendeur().setPseudo(authentication.getName());
            articleAVendreService.sell(articleAVendre);
        } catch (BusinessException e) {
            ObjectError error = new ObjectError("globalError", e.getMessage());
            bindingResult.addError(error);
            return "article/sell";
        }

        return "redirect:/";
    }
}
