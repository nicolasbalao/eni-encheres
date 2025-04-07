package fr.eni.projet.eniencheres.controller;

import fr.eni.projet.eniencheres.bo.Toast;
import org.springframework.stereotype.Controller;

@Controller
public class ToastController {
    public static Toast showToast(Toast.statut status, String message) {
        return new Toast(status, message, "toast-" +status.name().toLowerCase());
    }
}
