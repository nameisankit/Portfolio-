package in.ankitparmar.portfolio.config;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
           .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/home", "/css/**", "/js/**", "/images/**",
                     "/h2-console/**", "/webjars/**", "/error", "/login").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
           .anyRequest().permitAll()
           )

            .formLogin(form -> form
                .loginPage("/login")              // custom login page
                .defaultSuccessUrl("/admin", true) // login ke baad admin dashboard
                .permitAll()
            )
            .logout(logout -> logout
               // GET /logout ko bhi logout request treat kare
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
        .logoutSuccessUrl("/")   // logout ke baad portfolio home
        .permitAll()
)
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            );

        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("nameisankit_07")
                .password("An@230903")   
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
}
