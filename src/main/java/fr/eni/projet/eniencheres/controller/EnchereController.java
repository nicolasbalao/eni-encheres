package fr.eni.projet.eniencheres.controller;

import fr.eni.projet.eniencheres.bll.Encheres.EncheresService;
import fr.eni.projet.eniencheres.bo.Enchere;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("titre_enchere_details", this.get_title(enchere, auth));
        model.addAttribute("vente_close", false);
        return "enchere-details";
    }

    @PostMapping("/clotureEnchere")
    public String cloture_enchere(@RequestParam(required = false) Long id, Model model, Authentication auth) {
        if (id == null) {
            return "redirect:/";
        }
        try {
            Enchere enchere = encheresService.livrerEnchere(id);
            model.addAttribute("enchere", enchere);
            model.addAttribute("titre_enchere_details", this.get_title(enchere, auth));
            model.addAttribute("vente_close", true);

            return "enchere-details";
        }
        catch (Exception e) {
            return "redirect:/";
        }

    }

    public String get_title(Enchere enchere, Authentication auth) {
        String titre_enchere_details = "";
        String userPseudo = auth.getName();
        Number statut_enchere = enchere.getArticleAVendre().getStatut();

        if(statut_enchere.equals(2) && enchere.getAcquereur().getPseudo().equals(userPseudo)) {
            titre_enchere_details = "Vous avez remporté la vente";
        }
        else if (statut_enchere.equals(2) && !enchere.getAcquereur().getPseudo().equals(userPseudo)){
            titre_enchere_details = enchere.getAcquereur().getPseudo() + " a remporté la vente";
        }
        else if (statut_enchere.equals(3) && !enchere.getAcquereur().getPseudo().equals(userPseudo)){
            titre_enchere_details = enchere.getAcquereur().getPseudo() + " a remporté la vente. La vente est livrée.";
        }
        else {
            titre_enchere_details = "Détail d'une vente";
        }
        return titre_enchere_details;
    }

}
