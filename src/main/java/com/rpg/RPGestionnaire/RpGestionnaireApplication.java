package com.rpg.RPGestionnaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@SpringBootApplication
public class RpGestionnaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpGestionnaireApplication.class, args);
	}

	@Controller
	class RootRedirectController {
		@GetMapping("/")
		public RedirectView rootRedirect() {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null && auth.isAuthenticated() && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_Admin"))) {
				return new RedirectView("/admin");
			}
			return new RedirectView("/index");
		}
	}

}
