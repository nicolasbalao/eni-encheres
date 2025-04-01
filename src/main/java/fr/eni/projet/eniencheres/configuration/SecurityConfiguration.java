package fr.eni.projet.eniencheres.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        // TODO: if needed add column for enable instead of 1
        userDetailsManager.setUsersByUsernameQuery("SELECT pseudo, mot_de_passe, 1 FROM UTILISATEURS WHERE pseudo = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery(
                """
                                    SELECT u.pseudo, r.role
                                    FROM utilisateurs u
                                    JOIN roles r ON (u.administrateur = r.IS_ADMIN)
                                    WHERE u.pseudo = ?;
                        """
        );
        return userDetailsManager;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/").permitAll()
                .requestMatchers("/static/**").permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/images/**").permitAll()
                .requestMatchers("/uploads/**").permitAll());

        http.formLogin(form -> form.loginPage("/login").permitAll());


        return http.build();
    }
}
