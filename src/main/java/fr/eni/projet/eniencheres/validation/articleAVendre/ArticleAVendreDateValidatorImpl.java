package fr.eni.projet.eniencheres.validation.articleAVendre;

import fr.eni.projet.eniencheres.bo.ArticleAVendre;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ArticleAVendreDateValidatorImpl implements ConstraintValidator<ArticleAVendreDateValidator, ArticleAVendre> {


    @Override
    public boolean isValid(ArticleAVendre articleAVendre, ConstraintValidatorContext constraintValidatorContext) {
        if (articleAVendre.getDateDebutEncheres() == null || articleAVendre.getDateFinEncheres() == null) {
            return true;
        }
        return !articleAVendre.getDateDebutEncheres().isAfter(articleAVendre.getDateFinEncheres());
    }
}
