package fr.eni.projet.eniencheres.security;

import javax.sql.DataSource;

import org.apache.commons.logging.*;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class EniSecurityConfig {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Bean
	UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT pseudo, password, 1 FROM utilisateur WHERE pseudo =?");
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT pseudo, role FROM roles WHERE pseudo =?");
		
		return jdbcUserDetailsManager;
	};
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());
		
		http.authorizeHttpRequests(auth -> {auth
			// 🔹 Page d'accueil (accès public)
		    .requestMatchers(HttpMethod.GET, "/").permitAll();

			//Permettre à tous les utilisateurs d'afficher correctement les images et la css
			auth.requestMatchers("/**").permitAll();
			//Toutes autres url et méthodes HTTP ne sont pas permises
			auth.anyRequest().denyAll();
		});

		return http.build();
	}
}
