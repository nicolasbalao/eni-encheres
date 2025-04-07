package fr.eni.projet.eniencheres.controller;

import fr.eni.projet.eniencheres.bll.Encheres.EncheresService;
import fr.eni.projet.eniencheres.bo.Enchere;
import fr.eni.projet.eniencheres.bo.Toast;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String cloture_enchere(@RequestParam(required = true) Long id, Model model, Authentication auth, RedirectAttributes redirectAttributes) {
        try {
            Enchere enchere = encheresService.livrerEnchere(id);
            model.addAttribute("enchere", enchere);
            model.addAttribute("titre_enchere_details", this.get_title(enchere, auth));
            model.addAttribute("vente_close", true);

            Toast toastSucess = ToastController.showToast(Toast.statut.SUCCESS, "Retrait correctement éffectué.");
            redirectAttributes.addFlashAttribute("toast", toastSucess);
            return "redirect:/enchere/"+id;
        }
        catch (Exception e) {
            Toast toastError = ToastController.showToast(Toast.statut.DANGER, e.getMessage());
            redirectAttributes.addFlashAttribute("toast", toastError);
            return "redirect:/enchere/"+id;
        }
    }

    @PostMapping("/encherir")
    public String encherir(@RequestParam(required = true) Long idArticle, @RequestParam(required = true) int montant, Model model, Authentication auth, RedirectAttributes redirectAttributes) {
        String pseudoAcheteur = auth.getName();

        try {
            Enchere enchere = encheresService.encherire(pseudoAcheteur, idArticle, montant);
            model.addAttribute("enchere", enchere);
            model.addAttribute("titre_enchere_details", this.get_title(enchere, auth));
            model.addAttribute("vente_close", false);

            Toast toastSucess = ToastController.showToast(Toast.statut.SUCCESS, "Votre enchère à bien été prise en compte.");
            redirectAttributes.addFlashAttribute("toast", toastSucess);
            return "redirect:/enchere/"+idArticle;
        }
        catch (Exception e) {
            Toast toastError = ToastController.showToast(Toast.statut.DANGER, e.getMessage());
            redirectAttributes.addFlashAttribute("toast", toastError);
            return "redirect:/enchere/"+idArticle;
        }

    }

    public String get_title(Enchere enchere, Authentication auth) {
        String titre_enchere_details = "";
        String userPseudo = auth.getName();
        Number statut_enchere = enchere.getArticleAVendre().getStatut().getCode();

        if (statut_enchere.equals(2) && enchere.getAcquereur().getPseudo().equals(userPseudo)) {
            titre_enchere_details = "Vous avez remporté la vente";
        } else if (statut_enchere.equals(2) && !enchere.getAcquereur().getPseudo().equals(userPseudo)) {
            titre_enchere_details = "<a class='text-blue-500 underline' href='/users/" +enchere.getAcquereur().getPseudo() + "/profile'> " + enchere.getAcquereur().getPseudo() + "</a> a remporté la vente";
        } else if (statut_enchere.equals(3) && !enchere.getAcquereur().getPseudo().equals(userPseudo)) {
            titre_enchere_details = "<a class='text-blue-500 underline' href='/users/" +enchere.getAcquereur().getPseudo() + "/profile'> " + enchere.getAcquereur().getPseudo() + "</a> a remporté la vente. La vente est livrée.";
        } else {
            titre_enchere_details = "Détail d'une vente";
        }
        return titre_enchere_details;
    }
}
