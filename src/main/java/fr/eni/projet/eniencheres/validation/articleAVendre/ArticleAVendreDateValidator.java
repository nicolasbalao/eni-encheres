package fr.eni.projet.eniencheres.validation.articleAVendre;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ArticleAVendreDateValidatorImpl.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ArticleAVendreDateValidator {
    String message() default "La date de début doit être avant la date de fin";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
