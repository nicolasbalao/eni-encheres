package fr.eni.projet.eniencheres.controller;

import fr.eni.projet.eniencheres.bll.articleAVendre.ArticleAVendreService;
import fr.eni.projet.eniencheres.bo.*;
import fr.eni.projet.eniencheres.dal.adresse.AdresseRepository;
import fr.eni.projet.eniencheres.dal.categorie.CategorieRepository;
import fr.eni.projet.eniencheres.exception.BusinessException;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

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

    @GetMapping("{id}/sale")
    public String saleArticlePage(Model model, @PathVariable("id") Long id, Authentication authentication) {
        // TODO: handle 404
        ArticleAVendre articleAVendre = articleAVendreService.getArticleAVendre(id);
        model.addAttribute("articleAVendre", articleAVendre);

        // TODO: should process verifications here ? (Service ?)
        if (articleAVendre.getStatut() == StatutEnchere.ANNULEE) {
            return "redirect:/";
        }

        if (!Objects.equals(authentication.getName(), articleAVendre.getVendeur().getPseudo())) {
            // TODO: redirect to the sale details for public
            return "redirect:/";
        }

        if (!articleAVendre.getDateDebutEncheres().isAfter(LocalDate.now())) {
            // TODO: redirect to the sale details wihtout modification
            return "redirect:/";
        }

        return "article/sell";
    }

    @PostMapping("{id}/sale/cancel")
    public String cancelSaleArticle(@PathVariable("id") Long id, Authentication authentication) {
        // TODO: Handle 404 articleAVendre not found
        // TODO: try to delete ducplicated code
        ArticleAVendre articleAVendre = articleAVendreService.getArticleAVendre(id);
        if (!Objects.equals(authentication.getName(), articleAVendre.getVendeur().getPseudo())) {
            return "redirect:/";
        }

        if (!articleAVendre.getDateDebutEncheres().isAfter(LocalDate.now())) {
            return "redirect:/";
        }

        articleAVendreService.cancel(articleAVendre);

        return "redirect:/";

    }


}
