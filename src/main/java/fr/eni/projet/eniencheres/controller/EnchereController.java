package fr.eni.projet.eniencheres.controller;

import fr.eni.projet.eniencheres.bll.Encheres.EncheresService;
import fr.eni.projet.eniencheres.bo.Enchere;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EnchereController {
    private EncheresService encheresService;

    public EnchereController(EncheresService encheresService) {
        super();
        this.encheresService = encheresService;
    }

    @GetMapping("/enchere/{id}")
    public String EnchereDetails(Model model, @PathVariable("id") Long id, Authentication auth) {
        Enchere enchere = encheresService.consulterEnchere(id);
        if (enchere == null) {
            return "redirect:/";
        }

        model.addAttribute("enchere", enchere);
        model.addAttribute("titre_enchere_details", this.get_title(enchere, auth));
        model.addAttribute("vente_close", false);
        return "enchere-details";
    }

    @PostMapping("/clotureEnchere")
    public String cloture_enchere(@RequestParam(required = true) Long id, Model model, Authentication auth) {
        try {
            Enchere enchere = encheresService.livrerEnchere(id);
            model.addAttribute("enchere", enchere);
            model.addAttribute("titre_enchere_details", this.get_title(enchere, auth));
            model.addAttribute("vente_close", true);

            return "enchere-details";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/";
        }
    }

    @PostMapping("/encherir")
    public String encherir(@RequestParam(required = true) Long idArticle, @RequestParam(required = true) int montant, Model model, Authentication auth) {
        String pseudoAcheteur = auth.getName();

        try {
            Enchere enchere = encheresService.encherire(pseudoAcheteur, idArticle, montant);
            model.addAttribute("enchere", enchere);
            model.addAttribute("titre_enchere_details", this.get_title(enchere, auth));
            model.addAttribute("vente_close", false);
            return "enchere-details";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/";
        }

    }

    public String get_title(Enchere enchere, Authentication auth) {
        String titre_enchere_details = "";
        String userPseudo = auth.getName();
        Number statut_enchere = enchere.getArticleAVendre().getStatut().getCode();

        if (statut_enchere.equals(2) && enchere.getAcquereur().getPseudo().equals(userPseudo)) {
            titre_enchere_details = "Vous avez remporté la vente";
        } else if (statut_enchere.equals(2) && !enchere.getAcquereur().getPseudo().equals(userPseudo)) {
            titre_enchere_details = enchere.getAcquereur().getPseudo() + " a remporté la vente";
        } else if (statut_enchere.equals(3) && !enchere.getAcquereur().getPseudo().equals(userPseudo)) {
            titre_enchere_details = enchere.getAcquereur().getPseudo() + " a remporté la vente. La vente est livrée.";
        } else {
            titre_enchere_details = "Détail d'une vente";
        }
        return titre_enchere_details;
    }
}
