package fr.eni.projet.eniencheres.controller;

import fr.eni.projet.eniencheres.bll.interfaces.UtilisateurService;
import fr.eni.projet.eniencheres.bo.Utilisateur;
import fr.eni.projet.eniencheres.exception.BusinessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// TODO: See if we split this controller into 2 -> 1 for my profile & 1 for users profile
@Controller
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(final UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/profile")
    public String myProfilePage(Model model, Authentication auth) {
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/login";
        }
        String pseudo = auth.getName();
        try {
            Utilisateur user = utilisateurService.displayProfile(pseudo);
            model.addAttribute("profile", user);
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/error";
        }

        return "user/profile";
    }


}
