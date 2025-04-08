package fr.eni.projet.eniencheres.controller;

import fr.eni.projet.eniencheres.bll.articleAVendre.ArticleAVendreService;
import fr.eni.projet.eniencheres.bo.*;
import fr.eni.projet.eniencheres.dal.adresse.AdresseRepository;
import fr.eni.projet.eniencheres.dal.categorie.CategorieRepository;
import fr.eni.projet.eniencheres.exception.BusinessException;
import jakarta.validation.Valid;
import org.springframework.core.env.Environment;
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
    private final Environment environment;

    public ArticleAVendreController(final CategorieRepository categorieRepository, AdresseRepository adresseRepository, ArticleAVendreService articleAVendreService, Environment environment) {
        this.categorieRepository = categorieRepository;
        this.adresseRepository = adresseRepository;
        this.articleAVendreService = articleAVendreService;
        this.environment = environment;
    }

    @ModelAttribute("categories")
    public List<Categorie> categories() {
        return this.categorieRepository.getCategories();
    }

    @ModelAttribute("adresses")
    public List<Adresse> adresses(Authentication authentication) {
        List<Adresse> eniAdresses = this.adresseRepository.findEniAdresse();
        Adresse userAdresse = this.adresseRepository.findAdresseByUtilisateurPseudo(authentication.getName());

        Set<Adresse> adresses = new HashSet<>(eniAdresses);
        adresses.add(userAdresse);
        return new ArrayList<>(adresses);
    }

    @ModelAttribute("enchereStatus")
    public StatutEnchere[] enchereStatus() {
        if (!Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
            return null;
        }
        return StatutEnchere.values();
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

        ArticleAVendre article = articleAVendreService.getArticleAVendre(id);
        if (article == null) {
            // TODO: retourner une page 404 custom ou utiliser une exception
            return "redirect:/404";
        }

        // Redirige vers l'accueil si l'enchère a été annulée
        if (article.getStatut() == StatutEnchere.ANNULEE) {
            return "redirect:/";
        }

        if (!canEditArticle(article)) {
            return redirectToPublicSale(id);
        }
        model.addAttribute("articleAVendre", article);
        return "article/sell";

    }

    @PostMapping("{id}/sale/edit")
    public String editSaleArticle(@PathVariable("id") Long id, Authentication authentication, @Valid @ModelAttribute("articleAvendre") ArticleAVendre articleAVendre, BindingResult bindingResult, Model model) {
        // TODO: Handle 404 articleAVendre not found
        // TODO: try to delete ducplicated code

        if (bindingResult.hasErrors()) {
            model.addAttribute("articleAVendre", articleAVendre);
            return "article/sell";
        }

        if (!canEditArticle(articleAVendre)) {
            return redirectToPublicSale(id);
        }

        try {
            // TODO: maybe move this to service
            ArticleAVendre originalArticleAVendre = articleAVendreService.getArticleAVendre(id);
            if (!Objects.equals(authentication.getName(), originalArticleAVendre.getVendeur().getPseudo())) {
                return "redirect:/";
            }

            articleAVendreService.update(articleAVendre);
        } catch (BusinessException e) {
            ObjectError error = new ObjectError("globalError", e.getMessage());
            bindingResult.addError(error);
            return "article/sell";
        }

        return "redirect:/";
    }

    @PostMapping("{id}/sale/cancel")
    public String cancelSaleArticle(@PathVariable("id") Long id, Authentication authentication) {
        // TODO: Handle 404 articleAVendre not found
        // TODO: try to delete ducplicated code
        ArticleAVendre articleAVendre = articleAVendreService.getArticleAVendre(id);

        if (articleAVendre.getStatut() == StatutEnchere.ANNULEE) {
            return "redirect:/";
        }

        if (!Objects.equals(authentication.getName(), articleAVendre.getVendeur().getPseudo())) {
            return "redirect:/";
        }

        if (!canEditArticle(articleAVendre)) {
            return redirectToPublicSale(id);
        }

        articleAVendreService.cancel(articleAVendre);

        return "redirect:/";

    }

    private String redirectToPublicSale(Long id) {
        return "redirect:/encheres/" + id;
    }

    private boolean canEditArticle(ArticleAVendre article) {
        return article.getDateDebutEncheres().isAfter(LocalDate.now()) && article.getStatut() == StatutEnchere.NON_COMMENCEE;
    }


}
