package fr.eni.projet.eniencheres.controller;

import fr.eni.projet.eniencheres.bll.Encheres.EncheresService;
import fr.eni.projet.eniencheres.bo.Enchere;
import fr.eni.projet.eniencheres.bo.Toast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
public class EnchereController {
    private EncheresService encheresService;
    @Autowired
    private MessageSource messageSource;

    public EnchereController(EncheresService encheresService) {
        super();
        this.encheresService = encheresService;
    }

    @GetMapping("/encheres/{id}")
    public String EnchereDetails(Model model, @PathVariable("id") Long id, Authentication auth, Locale locale) {
        Enchere enchere = encheresService.consulterEnchere(id);
        if (enchere == null) {
            return "redirect:/";
        }

        model.addAttribute("enchere", enchere);
        model.addAttribute("titre_enchere_details", this.get_title(enchere, auth, locale));
        model.addAttribute("vente_close", false);
        return "enchere-details";
    }

    @PostMapping("/clotureEnchere")
    public String cloture_enchere(@RequestParam(required = true) Long id, Model model, Authentication auth, RedirectAttributes redirectAttributes, Locale locale) {
        try {
            Enchere enchere = encheresService.livrerEnchere(id);
            model.addAttribute("enchere", enchere);
            model.addAttribute("titre_enchere_details", this.get_title(enchere, auth, locale));
            model.addAttribute("vente_close", true);

            Toast toastSucess = ToastController.showToast(Toast.statut.SUCCESS, messageSource.getMessage("enchereDetails.response.delivery.ok", null, locale));
            redirectAttributes.addFlashAttribute("toast", toastSucess);

            return "redirect:/encheres/" + id;
        } catch (Exception e) {
            Toast toastError = ToastController.showToast(Toast.statut.DANGER, messageSource.getMessage(e.getMessage(), null, locale));
            redirectAttributes.addFlashAttribute("toast", toastError);
            return "redirect:/encheres/" + id;
        }
    }

    @PostMapping("/encherir")
    public String encherir(@RequestParam(required = true) Long idArticle, @RequestParam(required = true) int montant, Model model, Authentication auth, RedirectAttributes redirectAttributes, Locale locale) {
        String pseudoAcheteur = auth.getName();

        try {
            Enchere enchere = encheresService.encherire(pseudoAcheteur, idArticle, montant);
            model.addAttribute("enchere", enchere);
            model.addAttribute("titre_enchere_details", this.get_title(enchere, auth, locale));
            model.addAttribute("vente_close", false);

            Toast toastSucess = ToastController.showToast(Toast.statut.SUCCESS, messageSource.getMessage("enchereDetails.response.buy.ok", null, locale));
            redirectAttributes.addFlashAttribute("toast", toastSucess);

            return "redirect:/encheres/" + idArticle;
        } catch (Exception e) {
            Toast toastError = ToastController.showToast(Toast.statut.DANGER, messageSource.getMessage(e.getMessage(), null, locale));

            redirectAttributes.addFlashAttribute("toast", toastError);
            return "redirect:/encheres/" + idArticle;
        }

    }

    public String get_title(Enchere enchere, Authentication auth, Locale locale) {
        String titre_enchere_details = "";
        String userPseudo = auth.getName();
        Number statut_enchere = enchere.getArticleAVendre().getStatut().getCode();

        if (statut_enchere.equals(2) && enchere.getAcquereur().getPseudo().equals(userPseudo)) {
            titre_enchere_details = messageSource.getMessage("enchereDetails.label.you.win", null, locale);
        } else if (statut_enchere.equals(2) && !enchere.getAcquereur().getPseudo().equals(userPseudo)) {
            titre_enchere_details = "<a class='text-blue-500 underline' href='/users/" + enchere.getAcquereur().getPseudo() + "/profile'> " + enchere.getAcquereur().getPseudo() + "</a> " + messageSource.getMessage("enchereDetails.label.other.win", null, locale);
        } else if (statut_enchere.equals(3) && !enchere.getAcquereur().getPseudo().equals(userPseudo)) {
            titre_enchere_details = "<a class='text-blue-500 underline' href='/users/" + enchere.getAcquereur().getPseudo() + "/profile'> " + enchere.getAcquereur().getPseudo() + "</a> " + messageSource.getMessage("enchereDetails.label.other.win.and.close", null, locale);
        } else {
            titre_enchere_details = messageSource.getMessage("enchereDetails.label.default", null, locale);
        }
        return titre_enchere_details;
    }
}
