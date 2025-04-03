package fr.eni.projet.eniencheres.controller;

import fr.eni.projet.eniencheres.bll.utilisateur.UtilisateurService;
import fr.eni.projet.eniencheres.bo.Utilisateur;
import fr.eni.projet.eniencheres.dto.UpdatePasswordRequestDto;
import fr.eni.projet.eniencheres.exception.BusinessException;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "user/profile";
    }

    @GetMapping("/users/{pseudo}/profile")
    public String userProfilePage(@PathVariable("pseudo") String pseudo, Model model) {
        try {
            Utilisateur user = utilisateurService.displayProfile(pseudo);
            model.addAttribute("profile", user);
        } catch (BusinessException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "user/profile";
    }

    @GetMapping("/profile/edit")
    public String editMyProfilePage(Model model, Authentication auth) {
        // TODO: refactor this delete duplicate code & see if needed because security is auth
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/login";
        }
        String pseudo = auth.getName();
        try {
            Utilisateur user = utilisateurService.displayProfileDetails(pseudo);
            model.addAttribute("profile", user);
        } catch (BusinessException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "user/edit-profile";
    }

    @PostMapping("/profile/edit")
    public String editMyProfile(@Valid @ModelAttribute("profile") Utilisateur user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("profile", user);
            return "user/edit-profile";
        }

        try {
            utilisateurService.update(user);
        } catch (BusinessException e) {
            ObjectError error = new ObjectError("globalError", e.getMessage());
            bindingResult.addError(error);
            return "user/edit-profile";

        }

        return "redirect:/profile";
    }

    @GetMapping("/profile/edit/password")
    public String editMyPasswordPage(Model model, Authentication auth) {
        UpdatePasswordRequestDto updatePasswordRequestDto = new UpdatePasswordRequestDto();
        updatePasswordRequestDto.setPseudo(auth.getName());
        model.addAttribute("updatePasswordRequestDto", updatePasswordRequestDto);
        return "user/edit-password";
    }

    @PostMapping("/profile/edit/password")
    public String editMyPassword(@Valid @ModelAttribute UpdatePasswordRequestDto updatePasswordDto, BindingResult bindingResult, Model model, Authentication auth) {

        if (!auth.getName().equals(updatePasswordDto.getPseudo())) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("updatePasswordRequestDto", updatePasswordDto);
            return "user/edit-password";
        }
        try {
            utilisateurService.updatePassword(updatePasswordDto.getPseudo(), updatePasswordDto.getOldPassword(), updatePasswordDto.getNewPassword());
        } catch (BusinessException e) {
            ObjectError error = new ObjectError("globalError", e.getMessage());
            bindingResult.addError(error);
            return "user/edit-password";
        }
        return "redirect:/profile";
    }

}
