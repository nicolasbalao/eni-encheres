package fr.eni.projet.eniencheres.controller;

import fr.eni.projet.eniencheres.bll.interfaces.AuthService;
import fr.eni.projet.eniencheres.bo.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new Utilisateur());
        return "auth/register";
    }


    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") Utilisateur user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "auth/register";
        }

        try {
            authService.register(user);
        } catch (Exception e) {
            ObjectError error = new ObjectError("globalError", e.getMessage());
            bindingResult.addError(error);
            return "auth/register";
        }

        return "redirect:/";
    }
}
