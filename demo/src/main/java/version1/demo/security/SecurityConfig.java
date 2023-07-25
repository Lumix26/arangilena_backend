package version1.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl uImpl;

    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(uImpl);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        /*http.
            authorizeHttpRequests(
                authorize -> authorize.requestMatchers("/produzione/**").permitAll()
                .requestMatchers("/").permitAll()
                .anyRequest().authenticated()
            ).formLogin(
                login -> login.defaultSuccessUrl("/provaSicurezza")
            );*/
        /*http.authorizeRequests().requestMatchers("/").permitAll().
        anyRequest().authenticated().and().formLogin().permitAll().defaultSuccessUrl("/");
        return http.build();*/
        return http.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(
            authorize -> authorize.requestMatchers("/vetrina").permitAll()
                            .requestMatchers("/vetrina/registrazione").permitAll()
                            .requestMatchers("/vetrina/**").hasAuthority("GUEST")
                            .requestMatchers("/produzione/customerAPI/createCustomer").permitAll()
                            .requestMatchers("/produzione/**").hasAuthority("MEDIUM_LEVEL")
                            .requestMatchers("/provaSicurezza").hasAuthority("LOW_LEVEL")
                            .requestMatchers("/carrelloAPI/**").hasAuthority("GUEST")
                            .requestMatchers("/img/**").permitAll()
                            .requestMatchers("/style.css").permitAll()
                            .requestMatchers("/errore/**").permitAll()
                            .anyRequest().authenticated()
        ).formLogin(
            form -> form.defaultSuccessUrl("/vetrina").permitAll()
        ).logout(
            logout -> logout.logoutUrl("/carrelloAPI/logout").logoutSuccessUrl("/vetrina").permitAll()
        ).build();
        /*return http.authorizeHttpRequests(
            authorize -> authorize.requestMatchers("/vetrina/**").permitAll()
                            .requestMatchers("/produzione/**").authenticated()
                            .anyRequest().authenticated()
        ).formLogin(
            form -> form.permitAll().defaultSuccessUrl("/")
        ).build();*/

    }
}
