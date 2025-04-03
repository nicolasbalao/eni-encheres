package fr.eni.projet.eniencheres.controller;

import fr.eni.projet.eniencheres.bll.Encheres.EncheresService;
import fr.eni.projet.eniencheres.bo.Enchere;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
        if(enchere == null) {
            return "redirect:/";
        }

        model.addAttribute("enchere", enchere);

        String userPseudo = auth.getName();
        Number statut_enchere = enchere.getArticleAVendre().getStatut();
        String titre_enchere_details = "";

        if(statut_enchere.equals(2) && enchere.getAcquereur().getPseudo().equals(userPseudo)) {
            titre_enchere_details = "Vous avez remporté la vente";
        }
        else if (statut_enchere.equals(2) && !enchere.getAcquereur().getPseudo().equals(userPseudo)){
            titre_enchere_details = enchere.getAcquereur().getPseudo() + " a remporté la vente";
        }
        else {
            titre_enchere_details = "Détail d'une vente";
        }

        model.addAttribute("titre_enchere_details", titre_enchere_details);
        return "enchere-details";
    }
}
